package com.wttch.core.common;

import com.wttch.core.io.Files;
import com.wttch.core.io.Jars;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ClassScanner {
  private final String basePackage;
  private final ClassScannerFilter filter;

  public ClassScanner(String basePackage) {
    this(basePackage, ClassScannerFilter.DEFAULT);
  }

  public ClassScanner(String basePackage, ClassScannerFilter filter) {
    this.basePackage = basePackage;
    this.filter = filter;
  }

  /** 开始扫描 */
  public List<Class<?>> scan() {
    List<Class<?>> classList = new LinkedList<>();
    try {
      // 包名转化为路径名
      String pathName = Files.package2Path(basePackage);
      // 获取路径下URL
      Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(pathName);
      // 循环扫描路径
      while (urls.hasMoreElements()) {
        URL url = urls.nextElement();
        if (Files.isFile(url)) {
          String path = URLDecoder.decode(url.getFile(), "UTF-8");
          classList.addAll(recursiveScan4Path(basePackage, path));
        } else if (Jars.isJar(url)) {
          String jarPath = Jars.jarPathFormUrl(url);
          classList.addAll(recursiveScan4Jar(jarPath));
        }
      }
    } catch (IOException e) {
      System.err.println("Warning: Can not scan package：" + basePackage);
    }

    return classList;
  }

  /**
   * 递归扫描指定文件路径下的Class文件
   *
   * @param pkg 包名
   * @param filePath 文件路径
   * @return 扫描到的Class列表
   */
  private List<Class<?>> recursiveScan4Path(String pkg, String filePath) {
    List<Class<?>> classList = new LinkedList<>();

    File file = new File(filePath);
    if (!file.exists() || !file.isDirectory()) {
      return classList;
    }

    // 处理类文件
    File[] classes = file.listFiles(child -> Files.isClass(child.getName()));
    if (Objects.nonNull(classes)) {
      for (File child : classes) {
        String className = Files.classFile2ClassName(pkg + "." + child.getName());
        try {
          Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(className);
          if (filter.test(clz)) {
            classList.add(clz);
          }
        } catch (ClassNotFoundException | LinkageError e) {
          System.err.println("Warning: Can not load class:" + className);
        }
      }
    }

    // 如果是目录递归
    File[] dirs = file.listFiles(File::isDirectory);
    if (Objects.nonNull(dirs)) {
      for (File child : dirs) {
        String childPackageName = pkg + "." + child.getName();
        String childPath = filePath + "/" + child.getName();
        classList.addAll(recursiveScan4Path(childPackageName, childPath));
      }
    }

    return classList;
  }

  /**
   * 递归扫描Jar文件内的Class类
   *
   * @param jarPath jar文件路径
   * @return 扫描到的Class列表
   * @throws IOException jar 文件不存在等io异常
   */
  private List<Class<?>> recursiveScan4Jar(String jarPath) throws IOException {
    List<Class<?>> classList = new LinkedList<>();

    JarInputStream jin = new JarInputStream(new FileInputStream(jarPath));
    JarEntry entry = jin.getNextJarEntry();
    while (entry != null) {
      String name = entry.getName();
      entry = jin.getNextJarEntry();

      if (name.contains(Files.package2Path(basePackage))
          && Files.isClass(name)
          && !Files.isAnonymousInnerClass(name)) {
        String className = Files.classFile2ClassName(Files.path2Package(name));
        try {
          Class<?> clz = Thread.currentThread().getContextClassLoader().loadClass(className);
          if (filter.test(clz)) {
            classList.add(clz);
          }
        } catch (ClassNotFoundException | LinkageError e) {
          System.err.println("Warning: Can not load class:" + className);
        }
      }
    }

    return classList;
  }
}
