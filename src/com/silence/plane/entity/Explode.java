package com.silence.plane.entity;

import com.silence.plane.util.GameUtil;

import java.awt.*;

/**
 * @author jiazhikai
 * 爆炸类
 */
public class Explode {
    double x, y;

    static Image[] images = new Image[16];
    int count;

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = GameUtil.getImage("images/explode/e" + (i + 1) + ".gif");
            //解决懒加载问题
            images[i].getWidth(null);
        }
    }

    public void drawMySelf(Graphics g) {
        if (count < images.length) {
            g.drawImage(images[count], (int) x, (int) y, null);
            count++;
        }

    }

    public Explode() {
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
