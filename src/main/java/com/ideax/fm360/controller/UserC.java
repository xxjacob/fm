package com.ideax.fm360.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.ideax.common.Util;
import com.ideax.common.exception.EC;
import com.ideax.common.exception.IllegalException;
import com.ideax.fm360.constant.Const;
import com.ideax.fm360.pojo.SongList;
import com.ideax.fm360.pojo.User;
import com.ideax.fm360.service.AlbumService;
import com.ideax.fm360.service.IPassportService;
import com.ideax.fm360.service.SongListService;
import com.ideax.fm360.service.SongService;
import com.ideax.fm360.service.UserService;

@Controller
public class UserC {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SongService songService;

	@Autowired
	AlbumService albumService;

	@Autowired
	UserService userService;

	@Autowired
	SongListService songListService;

	@Autowired
	IPassportService passportService;

	@Value("${open.qq.client_id}")
	String clientId;

	@Value("${open.qq.client_secret}")
	String clientSecret;

	@Value("${open.qq.redirect_uri}")
	String redirectUri;

	@Value("${open.douban.client_id}")
	String doubanClientId;

	@Value("${open.douban.client_secret}")
	String doubanClientSecret;

	@Value("${open.douban.redirect_uri}")
	String doubanRedirectUri;

	@Value("${open.weibo.client_id}")
	String weiboClientId;

	@Value("${open.weibo.client_secret}")
	String weiboClientSecret;

	@Value("${open.weibo.redirect_uri}")
	String weiboRedirectUri;

	@RequestMapping("dologin")
	@ResponseBody
	public Object login(@RequestParam String email, @RequestParam String password, HttpServletResponse resp) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("err_no", EC.OK);
		try {
			User user = userService.getUserByNamePwd(email, Util.md5Encoding(password));
			if (user == null) {
				result.put("login", false);
				return result;
			}
			passportService.login(user, resp);
			result.put("login", true);
			result.put("user", user);
			result.put("playLists", songListService.getSongListsByUser(user.getId()));
		} catch (IllegalException e) {
			result.put("err_no", e.getErrorCode());
		}
		return result;
	}

	@RequestMapping("my")
	@ResponseBody
	public Object myInfo(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("err_no", EC.OK);
		User user = passportService.getLoginUser(req);
		if (user == null) {
			result.put("login", false);
			return result;
		}
		result.put("login", true);
		result.put("user", user);
		List<SongList> list = songListService.getSongListsByUser(user.getId());
		result.put("playLists", list);
		return result;
	}

	@RequestMapping("/3rd/success")
	@ResponseBody
	public Object thirdPatrySuccess(@RequestParam String code, HttpServletResponse response) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("code", code);
		params.put("state", "login");
		params.put("redirect_uri", redirectUri);
		String str = Util.getHttpResponseString("https://graph.qq.com/oauth2.0/token", params);
		if (str == null) {
			return "ungeilivable! cannot connet to qq!";
		}
		String accessToken = null;
		for (String s : str.split("&")) {
			String kvs[] = s.split("=");
			if (kvs.length == 2) {
				if ("access_token".equals(kvs[0])) {
					accessToken = kvs[1];
				}
			}
		}
		if (accessToken == null) {
			logger.error("get access_token error response:" + str);
			return "get access_token error response:" + str;
		}
		params.clear();
		params.put("access_token", accessToken);
		String openidStr = Util.getHttpResponseString("https://graph.qq.com/oauth2.0/me", params);
		if (openidStr == null) {
			return "ungeilivable! cannot connet to qq!";
		}

		String openid = getJsonValue("openid", str);

		// whether user exists
		User u = userService.getUserByOpenid(Const.PLATFORM_QQ, openid);
		if (u == null) {
			params.clear();
			params.put("access_token", accessToken);
			params.put("oauth_consumer_key", clientId);
			params.put("openid", openid);
			String userinfoStr = Util.getHttpResponseString("https://graph.qq.com/user/get_user_info", params);
			if (userinfoStr == null) {
				return "ungeilivable! cannot connet to qq!";
			}

			JsonNode node;
			try {
				node = Util.mapper.readTree(userinfoStr);
			} catch (Exception e) {
				return "ungeilivable! qq!" + userinfoStr;
			}
			int ret = node.get("ret").asInt();
			if (ret != 0) {
				return "ungeilivable!  qq return error!";
			} else {
				u = new User();
				u.setNickname(node.get("nickname").asText());
				u.setQqId(openid);
				u.setFigureurl(node.get("figureurl_qq_1").asText());
				u.setFigureurl2(node.get("figureurl_2").asText());
				u.setGender("男".equals(node.get("gender").asText()) ? (byte) 0 : (byte) 1);
				u.setQqToken(accessToken);
				u.setQqId(openid);
				u.setQqTokenTime((int) (System.currentTimeMillis() / 1000L));
				int id = userService.addUser(u);
				u.setId(id);

				// 新建 thumb列表
				SongList up = new SongList();
				up.setUid(u.getId());
				up.setName("Thumbups");
				up.setType(Const.SONG_LIST_TYPE_THUMBUP);
				songListService.addSongList(up);

				SongList down = new SongList();
				down.setUid(u.getId());
				down.setName("~Thumbdowns");
				down.setType(Const.SONG_LIST_TYPE_THUMBDOWN);
				songListService.addSongList(down);
			}
		} else {
			User update = new User();
			update.setQqToken(accessToken);
			update.setQqTokenTime((int) (System.currentTimeMillis() / 1000L));
			update.setId(u.getId());
			userService.updateUser(update);
		}

		passportService.login(u, response);
		HttpHeaders heads = new HttpHeaders();
		heads.setContentType(MediaType.TEXT_HTML);
		String jumpHtml = "<!DOCTYPE html><html><head></head><body>login success , closing ...<script>window.opener.login();window.close()</script></body></html>";
		HttpEntity<String> entity = new HttpEntity<String>(jumpHtml, heads);
		return entity;
	}

	/**
	 * douban登陆回调
	 * 
	 * @param code
	 * @param response
	 * @return
	 */
	@RequestMapping("/3rd/douban")
	@ResponseBody
	public Object doubanLoginSuccess(@RequestParam String code, HttpServletResponse response) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("client_id", doubanClientId);
		params.put("client_secret", doubanClientSecret);
		params.put("code", code);
		// params.put("state", "login");
		params.put("redirect_uri", doubanRedirectUri);
		String str = Util.getHttpResponseString("https://www.douban.com/service/auth2/token", "post", params, null);
		if (str == null) {
			logger.error("ungeilivable! cannot connet to douban!" + str);
			return "ungeilivable! cannot connet to douban!" + str;
		}
		String accessToken = getJsonValue("access_token", str);
		String refresh_token = getJsonValue("refresh_token", str);
		if (accessToken == null) {
			logger.error("get access_token error response:" + str);
			return "get access_token error response:" + str;
		}
		params.clear();
		params.put("access_token", accessToken);
		Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", "Bearer " + accessToken);
		String userinfoStr = Util.getHttpResponseString("https://api.douban.com/v2/user/~me", "get", params, header);
		if (userinfoStr == null) {
			return "ungeilivable! get user info error douban";
		}
		JsonNode node;
		try {
			node = Util.mapper.readTree(userinfoStr);
		} catch (Exception e) {
			return "ungeilivable! qq!" + userinfoStr;
		}
		JsonNode idN = node.get("id");
		JsonNode codeN = node.get("code");
		if (idN == null || (codeN != null && codeN.asInt() > 0)) {
			return "ungeilivable! qq!" + userinfoStr;
		}

		String doubanId = idN.asText();
		User u = userService.getUserByOpenid("douban", doubanId);

		if (u == null) {
			u = new User();
			u.setNickname(node.get("name").asText());
			u.setDoubanId(doubanId);
			u.setFigureurl(node.get("avatar").asText());
			u.setDoubanToken(accessToken);
			u.setDoubanTokenTime((int) (System.currentTimeMillis() / 1000L));
			u.setDoubanRefreshToken(refresh_token);
			int id = userService.addUser(u);
			u.setId(id);

			addDefaultPlayLists(u);
		} else {
			User update = new User();
			update.setDoubanToken(accessToken);
			update.setDoubanTokenTime((int) (System.currentTimeMillis() / 1000L));
			update.setId(u.getId());
			userService.updateUser(update);
		}

		passportService.login(u, response);
		HttpHeaders heads = new HttpHeaders();
		heads.setContentType(MediaType.TEXT_HTML);
		String jumpHtml = "<!DOCTYPE html><html><head></head><body>login success , closing ...<script>window.opener.login();window.close()</script></body></html>";
		HttpEntity<String> entity = new HttpEntity<String>(jumpHtml, heads);
		return entity;
	}

	/**
	 * douban登陆回调
	 * 
	 * @param code
	 * @param response
	 * @return
	 */
	@RequestMapping("/3rd/weibo")
	@ResponseBody
	public Object weiboLoginSuccess(@RequestParam String code, HttpServletResponse response) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("client_id", weiboClientId);
		params.put("client_secret", weiboClientSecret);
		params.put("code", code);
		// params.put("state", "login");
		params.put("redirect_uri", weiboRedirectUri);
		String str = Util.getHttpResponseString("https://api.weibo.com/oauth2/access_token", "post", params, null);
		if (str == null) {
			return "ungeilivable! cannot connet to weibo!";
		}
		String accessToken = getJsonValue("access_token", str);
		if (accessToken == null) {
			logger.error("get access_token error response:" + str);
			return "get access_token error response:" + str;
		}

		// 获取uid
		params.clear();
		params.put("access_token", accessToken);
		String uidStr = Util.getHttpResponseString("https://api.weibo.com/2/account/get_uid.json", params);
		if (uidStr == null) {
			return "ungeilivable! cannot get_uid weibo! token info " + accessToken;
		}
		String uid = getJsonValue("uid", uidStr);
		if (uid == null) {
			logger.error("get uid error response:" + uidStr);
			return "get uid error response:" + uidStr;
		}

		params.clear();
		params.put("access_token", accessToken);
		params.put("uid", uid);
		String userinfoStr = Util.getHttpResponseString("https://api.weibo.com/2/users/show.json", params);
		JsonNode node;
		try {
			node = Util.mapper.readTree(userinfoStr);
		} catch (Exception e) {
			return "ungeilivable! weibo!" + userinfoStr;
		}
		JsonNode idN = node.get("id");
		if (idN == null) {
			return "ungeilivable! weibo!" + userinfoStr;
		}

		String weiboId = idN.asText();
		User u = userService.getUserByOpenid("weibo", weiboId);

		if (u == null) {
			u = new User();
			u.setNickname(node.get("name").asText());
			u.setWeiboId(weiboId);
			u.setFigureurl(node.get("profile_image_url").asText());
			u.setWeiboToken(accessToken);
			u.setGender(node.get("profile_image_url").asText().equals("m") ? (byte) 0 : (byte) 1);
			u.setWeiboTokenTime((int) (System.currentTimeMillis() / 1000L));
			int id = userService.addUser(u);
			u.setId(id);

			addDefaultPlayLists(u);
		} else {
			User update = new User();
			update.setWeiboToken(accessToken);
			update.setWeiboTokenTime((int) (System.currentTimeMillis() / 1000L));
			update.setId(u.getId());
			userService.updateUser(update);
		}

		passportService.login(u, response);
		HttpHeaders heads = new HttpHeaders();
		heads.setContentType(MediaType.TEXT_HTML);
		String jumpHtml = "<!DOCTYPE html><html><head></head><body>login success , closing ...<script>window.opener.login();window.close()</script></body></html>";
		HttpEntity<String> entity = new HttpEntity<String>(jumpHtml, heads);
		return entity;
	}

	private void addDefaultPlayLists(User u) {
		// 新建 thumb列表
		SongList up = new SongList();
		up.setUid(u.getId());
		up.setName("Thumbups");
		up.setType(Const.SONG_LIST_TYPE_THUMBUP);
		songListService.addSongList(up);

		SongList down = new SongList();
		down.setUid(u.getId());
		down.setName("~Thumbdowns");
		down.setType(Const.SONG_LIST_TYPE_THUMBDOWN);
		songListService.addSongList(down);
	}

	/**
	 * simplest way to get a value from json string
	 * 
	 * @param key
	 * @param json
	 * @return
	 */
	private String getJsonValue(String key, String json) {
		JsonNode node;
		try {
			node = Util.mapper.readTree(json);
		} catch (Exception e) {
			logger.error("ungeilivable!!" + json);
			return null;
		}
		JsonNode idN = node.get(key);
		if (idN == null) {
			return null;
		}
		return idN.asText();
	}

	@RequestMapping("logout")
	@ResponseBody
	public Object register(HttpServletRequest request, HttpServletResponse resp) {
		User user = passportService.getLoginUser(request);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("err_no", EC.OK);
		try {
			passportService.logout(user, request, resp);
		} catch (IllegalException e) {
			result.put("err_no", e.getErrorCode());
		}
		return result;
	}
}
