package com.ssm.util.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;

/**
 * 生成Token的工具类
 */
public class TokenProcessor {

  private TokenProcessor(){};
  private static final TokenProcessor instance = new TokenProcessor();
  public static TokenProcessor getInstance() {
    return instance;
  }

  /**
   * 生成Token
   * @return
   */

  public String makeToken() {
    String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
    try {
      MessageDigest md = MessageDigest.getInstance("md5");
      byte md5[] =  md.digest(token.getBytes());
      Base64 encoder = new Base64();
      return encoder.encodeBase64String(md5);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

}