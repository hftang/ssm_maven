package com.hftang.ssm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author hftang
 * @date 2019-06-13 17:35
 * @desc 加密的类工具
 */
public class BCryptPasswordEncoderUtils {

    static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //提供对密码的加密

    public static String encodePassword(String password) {

        return bCryptPasswordEncoder.encode(password);

    }
}
