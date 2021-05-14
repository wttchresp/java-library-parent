package com.wttch.core.common;

/** 扫描过滤器, 扫描类时过滤 {@link #test(Class)} 返回 true 的类 */
@FunctionalInterface
public interface ClassScannerFilter {

  /** 默认过滤器, 所有都将被返回 */
  ClassScannerFilter DEFAULT = clazz -> true;

  boolean test(Class<?> clazz);

  /** 过滤器 and, 将当前过滤器和 {@code filter} 过滤器进行 and 操作, 两个过滤器都满足的类时候才会被保留 */
  default ClassScannerFilter and(ClassScannerFilter filter) {
    return clazz -> this.test(clazz) && filter.test(clazz);
  }

  /** 过滤器 or, 将当前过滤器和 {@code filter} 过滤器进行 or 操作, 满足任意一个过滤器的类都会被保留 */
  default ClassScannerFilter or(ClassScannerFilter filter) {
    return clazz -> this.test(clazz) || filter.test(clazz);
  }
}
