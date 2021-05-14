package com.wttch.core.io;

import com.wttch.io.Jars;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

public class JarsTest {

  @Test
  public void testIsJar() throws MalformedURLException {
    Assert.assertTrue(Jars.isJar(new URL("jar:file:/A.jar!/org")));
  }

  @Test
  public void testJarPathFromUrl() throws MalformedURLException {
    Assert.assertEquals(Jars.jarPathFormUrl(new URL("jar:file:/A.jar!/org")), "/A.jar");
  }
}
