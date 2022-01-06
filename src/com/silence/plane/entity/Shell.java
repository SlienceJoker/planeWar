package com.silence.plane.entity;

import com.silence.plane.common.Constant;

import java.awt.*;

/**
 * @author jiazhikai
 * 炮弹类
 */
public class Shell extends GameObject {
    /**
     * 角度
     */
    double degree;

    public Shell() {
        x = 200;
        y = 200;
        degree = Math.random() * Math.PI * 2;
        width = 10;
        height = 10;
        speed = 7;
    }

    @Override
    public void drawMySelf(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval((int) x, (int) y, width, height);
        g.setColor(color);
        //根据自己算法指定移动路径
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);
        //碰到边界改变方向
        if (y > Constant.GAME_HEIGHT-this.height || y < 40) {
            degree = -degree;
        }
        if (x < 0 || x > Constant.GAME_WIDTH-this.width) {
            degree = Math.PI - degree;
        }
    }
}
