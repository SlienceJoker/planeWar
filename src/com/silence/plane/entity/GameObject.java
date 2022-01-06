package com.silence.plane.entity;

import java.awt.*;

/**
 * @author jiazhikai
 * 游戏物体的根类
 */
public class GameObject {
    Image img;
    public double x;
    public double y;
    public int speed;
    int width;
    int height;

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }
    public GameObject(Image img,double x,double y,int speed){
        this.img=img;
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
    }

    public void drawMySelf(Graphics g){
        g.drawImage(img, (int) x, (int) y,width,height,null);
    }

    public Rectangle getRec(){
        return new Rectangle((int) x, (int) y,width,height);
    }

    public GameObject(){

    }



}
