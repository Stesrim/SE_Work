package mazemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame {
    JButton exitBtn;
    JButton gamestart;
    JButton makemap;
    JButton defaultmap;
    JButton importmap;
    JButton returntitle;
    ImageIcon bg;
    JLabel backgroundLabel = new JLabel();

    private defaultgame gameWindow;

    MainWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(null);
        this.setBackground(Color.darkGray);
        
        // 加载背景图
        bg = new ImageIcon(getClass().getResource("/images/background/index.jpg"));
        Image img = bg.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        bg = new ImageIcon(img);
        
        // 设置背景图片到 JLabel
        backgroundLabel.setIcon(bg);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // 背景图覆盖整个窗口
        this.add(backgroundLabel); // 添加背景标签到窗口

        // 初始化按钮
        exitBtn = new JButton("離開");
        gamestart = new JButton("遊戲開始");
        makemap = new JButton("設計端");
        returntitle = new JButton("返回");
        defaultmap = new JButton("預設地圖");
        importmap = new JButton("導入地圖");

        // 设置按钮字体
        Font buttonFont = new Font("Microsoft JhengHei", Font.BOLD, 35);
        exitBtn.setFont(buttonFont);
        gamestart.setFont(buttonFont);
        makemap.setFont(buttonFont);
        returntitle.setFont(buttonFont);
        defaultmap.setFont(buttonFont);
        importmap.setFont(buttonFont);

        // 添加按钮到窗口
        backgroundLabel.add(gamestart);
        backgroundLabel.add(makemap);
        backgroundLabel.add(exitBtn);
        backgroundLabel.add(defaultmap);
        backgroundLabel.add(importmap);
        backgroundLabel.add(returntitle);
        
        // 设置窗口为最大化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // 设置按钮位置
        gamestart.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.5),200,75);
        makemap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.6),200,75);
        exitBtn.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.7),200,75);
        defaultmap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.5),200,75);
        importmap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.6),200,75);
        returntitle.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.7),200,75);
        
        // 默认隐藏部分按钮
        returntitle.setVisible(false);
        defaultmap.setVisible(false);
        importmap.setVisible(false);
        
        // 添加按钮监听事件
        gamestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultmap.setVisible(true);
                importmap.setVisible(true);
                returntitle.setVisible(true);
                gamestart.setVisible(false);
                makemap.setVisible(false);
                exitBtn.setVisible(false);
            }
        });

        returntitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultmap.setVisible(false);
                importmap.setVisible(false);
                returntitle.setVisible(false);
                gamestart.setVisible(true);
                makemap.setVisible(true);
                exitBtn.setVisible(true);
            }
        });

        exitBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        makemap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new makemap();
            }
        });

        defaultmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameWindow == null || !gameWindow.window.isVisible()) {
                    gameWindow = new defaultgame();
                } else {
                    gameWindow.window.toFront();
                }
            }
        });

        importmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 导入地图功能的代码
            }
        });
    }
}
