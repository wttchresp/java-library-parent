package com.wttch.core.common;

import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("ConstantConditions")
public class StringsTest {
  @Test
  public void testIsBlack() {
    Assert.assertTrue(Strings.isBlack(null));
    Assert.assertTrue(Strings.isBlack(""));
    Assert.assertFalse(Strings.isBlack("wttch"));
  }

  @Test
  public void testIsNotBlack() {
    Assert.assertFalse(Strings.isNotBlack(null));
    Assert.assertFalse(Strings.isNotBlack(""));
    Assert.assertTrue(Strings.isNotBlack("wttch"));
  }

  @Test
  public void testLength() {
    Assert.assertEquals(Strings.length(null), 0);
    Assert.assertEquals(Strings.length(""), 0);
    Assert.assertEquals(Strings.length("wttch"), 5);
  }
}
