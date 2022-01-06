package com.silence.plane;

import com.silence.plane.entity.Explode;
import com.silence.plane.entity.Plane;
import com.silence.plane.entity.Shell;
import com.silence.plane.util.GameUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import static com.silence.plane.common.Constant.GAME_HEIGHT;
import static com.silence.plane.common.Constant.GAME_WIDTH;

/**
 * @author jiazhikai
 * 游戏主窗口
 */
public class MyGameFrame extends Frame {

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    static int count = 0;

    Plane plane = new Plane(planeImg, 100, 100, 3);

    Shell[] shells = new Shell[50];

    Explode explode;

    Date start = new Date();
    Date end;
    long period = 0;


    @Override
    public void paint(Graphics g) {
        System.out.println("绘制窗口次数：" + count);
        count++;
        g.drawImage(bg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

        drawTime(g);
        plane.drawMySelf(g);
        //画炮弹
        for (Shell shell : shells) {
            shell.drawMySelf(g);
            //碰撞检测
            boolean boom = shell.getRec().intersects(plane.getRec());
            if (boom) {
                plane.live = false;
                //爆炸效果
                if (explode == null) {
                    explode = new Explode(plane.x, plane.y);
                }
                explode.drawMySelf(g);

            }

        }

    }

    public void drawTime(Graphics g){
        Color color = g.getColor();
        Font font = g.getFont();
        g.setColor(Color.GREEN);
        if (plane.live){
            period = (System.currentTimeMillis()-start.getTime())/1000;
            g.drawString("坚持："+period+"秒",30,50);
        }else {
            if (end==null){
                end = new Date();
                period = (end.getTime() - start.getTime())/1000;
            }
            g.setColor(Color.red);
            g.drawString("最终时间："+period,200,200);
        }
        g.setColor(color);
        g.setFont(font);
    }

    /**
     * 双缓冲技术，减少屏幕卡顿
     */
    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 初始化窗口
     */
    public void launchFrame() {
        //窗口标题
        this.setTitle("飞机大战-尚学堂");
        //窗口是否可见
        setVisible(true);
        //窗口大小
        setSize(GAME_WIDTH, GAME_HEIGHT);
        //窗口相对屏幕位置
        setLocation(400, 400);
        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); //正常退出
            }
        });
        //启动线程
        new PaintThread().start();

        this.addKeyListener(new KeyMonitor());
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    /**
     * 内部类，方便直接使用窗口类的相关方法
     * 重画窗口的线程类
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 键盘事件监听
     */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("按下：" + e.getKeyCode());
            plane.addDirection(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("抬起：" + e.getKeyCode());
            plane.minusDirection(e);
        }
    }

    public static void main(String[] args) {
        MyGameFrame gameFrame = new MyGameFrame();
        gameFrame.launchFrame();
    }

}
