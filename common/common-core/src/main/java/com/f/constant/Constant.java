package com.f.constant;

/**
 * 常量
 *
 * @author liuf
 * @date 2022/1/25 20:26
 */
public class Constant {

    /**
     * jwt key 信息
     */
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_KEY = "key";
    public static final String TOKEN = "token";

    /**
     * 是否
     */
    public static final int YES = 1;
    public static final int NO = 0;

    /**
     * 删除标志
     */
    public static final int DELETE_YES = YES;
    public static final int DELETE_NO = NO;

    /**
     * 默认字段
     */
    public static final String IS_DELETED = "is_delete";
    public static final String ID = "id";

    /**
     * 通用字符
     */
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String ADD = "+";
    public static final String STAR = "*";
    public static final String DOT = ".";
    public static final String SLASH = "/";
    public static final String COMMA = ",";
    public static final String UNDER = "_";
    public static final String VERTICAL = "|";
    public static final String AIT = "@";

    /**
     * mysql limit
     */
    public static final String LIMIT = " LIMIT 1";

    /**
     * 空白byte数组
     */
    public static final byte[] EMPTY_BYTES = new byte[0];

    /**
     * 数字常量 0-10
     * 100 1000 10000
     */
    public static final int ZERO = 0, ONE = 1, TWO = 2, THREE = 3, FOUR = 4, FIVE = 5, SIX = 6, SEVEN = 7, EIGHT = 8,
            NINE = 9, TEN = 10, HUNDRED = 100, K = 1000, W = 10000;

    /**
     * json
     */
    public static final String APPLICATION_JSON = "application/json;charset=utf-8";

    /**
     * 用户状态 1 正常 2 冻结 3 禁用
     */
    public static final int USER_STATUS_OK = 1;
    public static final int USER_STATUS_FREEZE = 2;
    public static final int USER_STATUS_DISABLE = 3;

    /**
     * 管理员id
     */
    public static final long ADMIN_ID = 1;

    /**
     * 异常thread key
     */
    public static final String EX = "ex";

    private Constant() {
    }
}
