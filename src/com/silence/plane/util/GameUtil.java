package com.silence.plane.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @author jiazhikai
 */
public class GameUtil {
    /**
     * 构造器私有，防止别人创建此类的对象
     */
    private GameUtil(){

    }

    public static Image getImage(String path){
        BufferedImage img = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


    public static void main(String[] args) {
        Image image = GameUtil.getImage("images/bg.jpg");
        System.out.println(image);

    }
}
