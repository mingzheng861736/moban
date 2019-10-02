package com.mz.demo.util;

/**
 * 魔法值类
 * @author weishilei
 * @date 2019.09.17
 */
public class Constants {

    /**
     * 验证码
     */
    public static final String VALIDATE_CODE = "validateCode";

    /**
     * 生成Hash值的迭代次数
     */
    public static final int HASH_INTERATIONS = 1024;

    /**
     * shiro采用加密算法
     */
    public static final String HASH_ALGORITHM = "SHA-1";

    /**
     * 生成盐的长度
     */
    public static final int SALT_SIZE = 8;

    public final static String LOGIN_TYPE = "loginType";

    public enum LoginTypeEnum {
        PAGE, ADMIN;
    }

}
