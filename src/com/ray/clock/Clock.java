package com.ray.clock;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Clock extends JFrameExt implements Runnable
{
    private static final long serialVersionUID = 1L;
    Thread clock;
    boolean start_flag = false;
    int transparent,form_x,form_y,form_width,form_height;
    int red,green,blue,font_transparent=255;
    public Clock(int form_width,int form_height,int form_x,int form_y,int form_transparent,int text_red,int text_green,int text_blue,int text_transparent)
    {
        this.form_width=form_width;
        this.form_height=form_height;
        this.form_x=form_x;
        this.form_y=form_y;
        this.transparent=form_transparent;
        red=text_red;
        green=text_green;
        blue=text_blue;
        font_transparent=text_transparent;
    }

    public void start() {
        if (clock == null) {
        setTitle("数字时钟"); // 调用父类的构造函数
        setIconImage(new ImageIcon("icons.png").getImage());
        setDefaultLookAndFeelDecorated(true);
        setFont(new Font("Times", Font.BOLD, 60)); // 显示调用时钟的

        setSize(form_width, form_height);// 设置界面大小
        setLocation(form_x,form_y);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setBackground(new Color(255, 255, 255, transparent)); // 窗口可视
        // setOpacity(0.1f);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口的时候退出程序
        setVisible(true);
            start_flag=true;
            clock = new Thread(this); // 实例化进程
            // jdk提供 一旦调用start方法，则会通过JVM找到run()方法
            clock.start(); // 开始进程
        }
    }

    // 运行进程
    public void run() {
        while (clock != null) {
            repaint(); // 重绘界面
            try {
                Thread.sleep(250); // 线程暂停1000毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) { // 重写组件的paint方法
        if(start_flag)
        {
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g2);
        Calendar now = new GregorianCalendar(); // 提供日历的系统
        String timeInfo = "";
        g.setColor(new Color(255, 255, 255,transparent)); // 设置颜色 白色

        Dimension dim = getSize(); // 得到窗口大小
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(new Color(red, green, blue,font_transparent)); // 橙色

         //System.out.println(red+" "+green+" "+blue);
        double t = Math.min(dim.width / 300.0, dim.height /70.0);
        Font font = new Font("黑体", Font.BOLD, (int) (65*t));
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int hour = now.get(Calendar.HOUR_OF_DAY); // 得到小时
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        if (hour <= 9) {
            timeInfo += "0" + hour + ":";
        } else {
            timeInfo += hour + ":";
        }

        if (minute <= 9) {
            timeInfo += "0" + minute + ":";
        } else {
            timeInfo += minute + ":";
        }

        if (second <= 9) {
            timeInfo += "0" + second;
        } else {
            timeInfo += second;
        }
        // g.setColor(this.getBackground());

        // super.paint(g2);
        // s=timeInfo;

        super.paint(g2);

        // Determine the X coordinate for the text
        int x = (dim.width - metrics.stringWidth(timeInfo)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java
        // 2d 0 is top of the screen)
        int y = ((dim.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(timeInfo, x, y);
    }
    }

    // 程序的入口
    
    public void settransparent(int n)
    {
        transparent = n;
        if(start_flag)
        setBackground(new Color(255, 255, 255, transparent));
    }
    public void setfonttransparent(int n)
    {
        font_transparent = n;
    }
    public void setred(int n)
    {
        red=n;
    }
    public void setblue(int n)
    {
        blue= n;
    }
    public void setgreen(int n)
    {
        green= n;
    }
    public int getformwidth()
    {
        Dimension dim = getSize();
        return dim.width;
    }
    public int getformheight()
    {
        Dimension dim = getSize();
        return dim.height;
    }
    public int getscreenx()
    {
        return getLocationOnScreen().x;
    }
    public int getscreeny()
    {
        return getLocationOnScreen().y;
    }
    public int getformtransparent()
    {
        return transparent;
    }
    public int getred()
    {
        return red;
    }
    public int getgreen()
    {
        return green;
    }
    public int getblue()
    {
        return blue;
    }
    public int gettexttransparent()
    {
        return font_transparent;
    }
    public boolean isstarted()
    {
        return start_flag;
    }
}
