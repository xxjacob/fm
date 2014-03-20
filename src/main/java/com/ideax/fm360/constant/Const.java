package com.ideax.fm360.constant;

public class Const {

	// 歌单类型
	public static byte SONG_LIST_TYPE_DEFAULT = 0;
	public static byte SONG_LIST_TYPE_THUMBUP = 1;
	public static byte SONG_LIST_TYPE_THUMBDOWN = 2;
	public static byte SONG_LIST_TYPE_TIRED = 3;

	public static String[] SONG_LIST_NAMES = { "", "Thumbup", "Thumbdown", "Tired" };

	// thumb status
	public static byte THUMB_NO = 0;

	// 第三方平台
	public static String PLATFORM_QQ = "qq";
	public static String PLATFORM_WEIBO = "weibo";
	public static String PLATFORM_DOUBAN = "douban";
}
