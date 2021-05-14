package com.wttch.awt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {

  public static void convertColor(BufferedImage image, int src, int dst) {
    int width = image.getWidth();
    int height = image.getHeight();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (image.getRGB(i, j) == src) {
          image.setRGB(i, j, dst);
        }
      }
    }
  }

  public static void main(String[] args) {
    try {
      String path = "/wttch/workspace/java-library-parent/A-test/";
      Color color = Color.decode("#6f80e8");
      Color color1 = Color.decode("#88a4ff");
      File[] a = new File(path).listFiles();
      for (File x : a) {
        if (x.isFile()) {
          BufferedImage img = ImageIO.read(x);
          convertColor(img, color.getRGB(), Color.decode("#cb8ebf").getRGB());
          convertColor(img, color1.getRGB(), Color.decode("#f8a3c7").getRGB());

          ImageIO.write(img, "PNG", new File(path + "out/" + x.getName()));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
