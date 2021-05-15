package com.wttch.core.io;

import java.net.URL;
import java.util.Objects;

/** jar文件相关的工具类 */
public class Jars {

  /** jar 文件url协议名称 */
  public static final String URL_PROTOCOL_JAR = "jar";

  /** jar 文件后缀名称 */
  public static final String JAR_FILE_SUFFIX = ".jar";

  /**
   * 判断 url 是否是jar
   *
   * @param url url路径
   * @return 如果是jar文件返回true; 否则 false
   */
  public static boolean isJar(URL url) {
    return Objects.nonNull(url) && URL_PROTOCOL_JAR.equals(url.getProtocol());
  }

  /**
   * 从url中获取jar真实路径.
   *
   * <p>Note:使用前应确认 url 为 jar 协议.
   *
   * <p>jar文件url：<br>
   * jar:file:/home/user/../test.jar!/org
   *
   * <p>真实文件路径为 /home/user/../test.jar
   *
   * @param url jar的url路径
   * @return jar 文件的真实文件路径
   */
  public static String jarPathFormUrl(URL url) {
    String file = url.getFile();
    return file.substring(0, file.lastIndexOf("!")).replaceFirst("file:", "");
  }
}
