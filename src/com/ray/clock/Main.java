package com.ray.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
    public static void main(String[] args) {
        File file = new File("setting.ini");
        int form_width = 300;
        int form_height = 100;
        int form_x = 0;
        int form_y = 0;
        int form_transparent = 100;
        int text_red = 255;
        int text_green = 200;
        int text_blue = 0;
        int text_transparent = 255;
        if (!file.exists()) {
            try {
                file.createNewFile();
                /*
                 * form width height x y form transparent text r g b transparent
                 */
                String content = "300 100 0 0\n100\n255 200 0 255\n";
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(content.getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Scanner scanner = new Scanner(new FileInputStream("setting.ini"));
                form_width = scanner.nextInt();
                form_height = scanner.nextInt();
                form_x = scanner.nextInt();
                form_y = scanner.nextInt();
                form_transparent = scanner.nextInt();
                text_red = scanner.nextInt();
                text_green = scanner.nextInt();
                text_blue = scanner.nextInt();
                text_transparent = scanner.nextInt();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        JFrame jf = new JFrame("数字时钟");
        jf.setSize(550, 350);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setIconImage(new ImageIcon("icons.png").getImage());

        GridBagLayout gridBagLayout = new GridBagLayout();
        jf.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        final Clock clock = new Clock(form_width, form_height, form_x, form_y, form_transparent, text_red, text_green,
                text_blue, text_transparent);

        JButton open = new JButton("开启");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clock.start();
            }
        });

        JLabel label = new JLabel("窗体透明度");

        final JSlider slider = new JSlider(0, 254, form_transparent);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                clock.settransparent(slider.getValue());
                ;
            }
        });

        JLabel label_font = new JLabel("字体颜色");

        JLabel label_red = new JLabel("R");

        final JSlider slider_red = new JSlider(0, 255, text_red);
        slider_red.setMajorTickSpacing(100);
        slider_red.setMinorTickSpacing(10);
        slider_red.setPaintTicks(true);
        slider_red.setPaintLabels(true);
        slider_red.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                clock.setred(slider_red.getValue());
                ;
            }
        });

        JLabel label_green = new JLabel("G");

        final JSlider slider_green = new JSlider(0, 255, text_green);
        slider_green.setMajorTickSpacing(100);
        slider_green.setMinorTickSpacing(10);
        slider_green.setPaintTicks(true);
        slider_green.setPaintLabels(true);
        slider_green.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                clock.setgreen(slider_green.getValue());
                ;
            }
        });

        JLabel label_blue = new JLabel("B");

        final JSlider slider_blue = new JSlider(0, 255, text_blue);
        slider_blue.setMajorTickSpacing(100);
        slider_blue.setMinorTickSpacing(10);
        slider_blue.setPaintTicks(true);
        slider_blue.setPaintLabels(true);
        slider_blue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                clock.setblue(slider_blue.getValue());
                ;
            }
        });

        JLabel label_font_transparent = new JLabel("字体透明度");

        final JSlider slider_font_transparent = new JSlider(0, 255, text_transparent);
        slider_font_transparent.setMajorTickSpacing(100);
        slider_font_transparent.setMinorTickSpacing(10);
        slider_font_transparent.setPaintTicks(true);
        slider_font_transparent.setPaintLabels(true);
        slider_font_transparent.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                clock.setfonttransparent(slider_font_transparent.getValue());
            }
        });

        JButton save = new JButton("保存当前状态");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content;
                if(clock.isstarted())
                {
                    content = clock.getformwidth()+" "+clock.getformheight()
                    +" "+clock.getscreenx()+" "+clock.getscreeny()+"\n"+
                    clock.getformtransparent()+"\n"+
                    clock.getred()+" "+clock.getgreen()+" "+clock.getblue()+" "+
                    clock.gettexttransparent()+"\n";
                }
                else
                {
                    content = clock.form_width+" "+clock.form_height
                    +" "+clock.form_x+" "+clock.form_y+"\n"+
                    clock.getformtransparent()+"\n"+
                    clock.getred()+" "+clock.getgreen()+" "+clock.getblue()+" "+
                    clock.gettexttransparent()+"\n";
                }
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(content.getBytes());
                    fileOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
            }
        });

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1;  
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(open, gridBagConstraints);

        gridBagConstraints.gridx=2;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1;  
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;
        gridBagLayout.setConstraints(save, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;            
        gridBagLayout.setConstraints(label, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=3;                                             
        gridBagConstraints.gridheight=1;
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;            
        gridBagLayout.setConstraints(slider, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=2;                                             
        gridBagConstraints.gridheight=1; 
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;           
        gridBagLayout.setConstraints(label_font, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;   
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;         
        gridBagLayout.setConstraints(label_red, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=3;
        gridBagConstraints.gridwidth=3;                                             
        gridBagConstraints.gridheight=1;   
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;         
        gridBagLayout.setConstraints(slider_red, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;   
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;         
        gridBagLayout.setConstraints(label_green, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=4;
        gridBagConstraints.gridwidth=3;                                             
        gridBagConstraints.gridheight=1;  
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;          
        gridBagLayout.setConstraints(slider_green, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=5;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;   
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;         
        gridBagLayout.setConstraints(label_blue, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=5;
        gridBagConstraints.gridwidth=3;                                             
        gridBagConstraints.gridheight=1;  
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;          
        gridBagLayout.setConstraints(slider_blue, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridwidth=1;                                             
        gridBagConstraints.gridheight=1;   
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;         
        gridBagLayout.setConstraints(label_font_transparent, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=6;
        gridBagConstraints.gridwidth=3;                                             
        gridBagConstraints.gridheight=1;  
        gridBagConstraints.weightx=0;
        gridBagConstraints.weighty=0;          
        gridBagLayout.setConstraints(slider_font_transparent, gridBagConstraints);

        jf.add(open);
        jf.add(save);
        jf.add(label);
        jf.add(slider);
        jf.add(label_font);
        jf.add(label_red);
        jf.add(slider_red);
        jf.add(label_green);
        jf.add(slider_green);
        jf.add(label_blue);
        jf.add(slider_blue);
        jf.add(label_font_transparent);
        jf.add(slider_font_transparent);
        jf.setVisible(true);
    }
}
