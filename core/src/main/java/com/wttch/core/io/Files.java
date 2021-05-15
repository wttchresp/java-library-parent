package com.wttch.core.io;

import com.wttch.core.common.Strings;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;

public class Files {

  /** 文件路径的分割符号 */
  public static final String FILE_SEPARATOR = "/";

  /** 全类名的包分割符号 */
  public static final String CLASS_SEPARATOR = ".";

  /** 匿名内部类类名的正则表达式 */
  private static final Pattern ANONYMOUS_INNER_CLASS_PATTERN =
      Pattern.compile("^.*\\$\\d+\\.class$");

  /** class 文件名后缀 */
  public static final String CLASS_FILE_SUFFIX = ".class";

  /** 文件类型的 url protocol */
  public static final String URL_PROTOCOL_FILE = "file";

  /**
   * 判断 url 是否为本地文件
   *
   * @param url 要检查的 url 路径
   * @return 如果是本地文件 true; 否则 false
   */
  public static boolean isFile(URL url) {
    return Objects.nonNull(url) && URL_PROTOCOL_FILE.equals(url.getProtocol());
  }

  /**
   * 判断文件名称是否是 class 文件.
   *
   * @param fileName 要判断的文件名称
   * @return 如果文件名称为 class 文件返回 true; 否则 false
   */
  public static boolean isClass(String fileName) {
    return Strings.isNotBlack(fileName) && fileName.endsWith(CLASS_FILE_SUFFIX);
  }

  /**
   * 判断给定的类名字符串是否是匿名内部类
   *
   * @param className 类名字符串
   * @return 如果是匿名内部类返回true; 否则 false
   */
  public static boolean isAnonymousInnerClass(String className) {
    return Strings.isNotBlack(className)
        && ANONYMOUS_INNER_CLASS_PATTERN.matcher(className).matches();
  }

  /**
   * 将全类名路径转换为文件路径
   *
   * @param packageName 要转换的全类名
   * @return 包名作为文件夹的文件路径
   */
  public static String package2Path(String packageName) {
    if (Strings.isBlack(packageName)) {
      return packageName;
    }
    return packageName.replace(CLASS_SEPARATOR, FILE_SEPARATOR);
  }

  /**
   * 将文件路径转换为全类名
   *
   * @param pathName 包路径
   * @return 全类名
   */
  public static String path2Package(String pathName) {
    if (Strings.isBlack(pathName)) {
      return pathName;
    }
    return pathName.replaceAll(FILE_SEPARATOR, CLASS_SEPARATOR);
  }

  /**
   * 将class文件名称改为类名称
   *
   * @param classFileName 类文件名称
   * @return 类名
   */
  public static String classFile2ClassName(String classFileName) {
    if (Strings.isBlack(classFileName)) {
      return classFileName;
    }
    if (classFileName.endsWith(CLASS_FILE_SUFFIX)) {
      int length = classFileName.length();
      return classFileName.substring(0, length - CLASS_FILE_SUFFIX.length());
    }
    return classFileName;
  }
}
