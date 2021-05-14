package com.wttch.core.common;

import java.util.Objects;

public class Strings {

  /** 空的字符串 */
  public static final String EMPTY = "";

  /**
   * 判断字符串是否为 null, 或者为空串
   *
   * @param str 要判断的字符串
   * @return 如果字符串为 null 或者空串则返回 true, 否则返回 false
   */
  public static boolean isBlack(CharSequence str) {
    return Objects.isNull(str) || str.length() == 0;
  }

  /**
   * 判断字符串是否不为null,并且不为空串
   *
   * @param str 要判断的字符串
   * @return 如果字符串不为null并且不为空串则返回 true, 否则返回 false
   */
  public static boolean isNotBlack(CharSequence str) {
    return !isBlack(str);
  }

  /**
   * 获取 CharSequence 的长度, 如果 CharSequence 为 {@code nul} 则返回 {@code 0}.
   *
   * @param cs CharSequence 或者 {@code null}
   * @return CharSequence 的长度或者当 CharSequence 为{@code null} 的时候返回{@code 0}
   */
  public static int length(final CharSequence cs) {
    return Objects.isNull(cs) ? 0 : cs.length();
  }
}
