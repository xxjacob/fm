package com.ideax.fm360.constant;

/**
 * redis keys
 * 
 * @author xuxin03
 * @since 2014年3月1日
 */
public class RedisKeys {

    /**
     * 歌曲列表的key
     * 
     * @param uid
     * @param type
     * @param id
     *            thumb up 和thunb down 的列表不需要这个参数
     * @return
     */
    public static String songListKey(int uid, byte type, int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("sl-").append(uid);
        return sb.toString();
    }
}
