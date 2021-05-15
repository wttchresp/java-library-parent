package com.wttch.core.io;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

public class FilesTest {

  @Test
  public void testIsFile() throws MalformedURLException {
    Assert.assertTrue(Files.isFile(new URL("file:/A.jar")));
    Assert.assertFalse(Files.isFile(new URL("jar:file:/A.jar!/org")));
  }

  @Test
  public void testIsClass() {
    Assert.assertFalse(Files.isClass(""));
    Assert.assertFalse(Files.isClass(null));
    Assert.assertFalse(Files.isClass("a.txt"));
    Assert.assertTrue(Files.isClass("a.class"));
  }

  @Test
  public void testIsAnonymousInnerClass() {
    Assert.assertFalse(Files.isAnonymousInnerClass(null));
    Assert.assertFalse(Files.isAnonymousInnerClass(""));
    Assert.assertFalse(Files.isAnonymousInnerClass("A"));
    Assert.assertFalse(Files.isAnonymousInnerClass("A.class"));
    Assert.assertFalse(Files.isAnonymousInnerClass("A.test"));
    Assert.assertTrue(Files.isAnonymousInnerClass("A$1.class"));
    Assert.assertTrue(Files.isAnonymousInnerClass("A.A$1.class"));
  }

  @Test
  public void testPackage2Path() {
    Assert.assertEquals(Files.package2Path("A"), "A");
    Assert.assertEquals(Files.package2Path("A.A"), "A/A");
  }

  @Test
  public void testPath2Package() {
    Assert.assertEquals(Files.path2Package("A"), "A");
    Assert.assertEquals(Files.path2Package("A/A"), "A.A");
  }
}
