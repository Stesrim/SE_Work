package mazemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainWindow extends JFrame {
    JButton exitBtn;
    JButton gamestart;
    JButton makemap;
    JButton defaultmap;
    JButton importmap;
    JButton returntitle;
    ImageIcon bg;
    JLabel backgroundLabel = new JLabel();

    MainWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(null);
        this.setBackground(Color.darkGray);
        
        // 加載背景圖
        bg = new ImageIcon(getClass().getResource("/images/background/index.jpg"));
        Image img = bg.getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        bg = new ImageIcon(img);
        
        // 設置背景圖片到 JLabel
        backgroundLabel.setIcon(bg);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // 背景圖覆蓋整個窗口
        this.add(backgroundLabel); // 添加背景标签到窗口

        // 初始畫按鈕
        exitBtn = new JButton("離開");
        gamestart = new JButton("遊戲開始");
        makemap = new JButton("設計端");
        returntitle = new JButton("返回");
        defaultmap = new JButton("預設地圖");
        importmap = new JButton("導入地圖");

        // 設置按鈕字體
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
        
        // 設置窗口為最大化
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // 設置按鈕位置
        gamestart.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.5),200,75);
        makemap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.6),200,75);
        exitBtn.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.7),200,75);
        defaultmap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.5),200,75);
        importmap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.6),200,75);
        returntitle.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.7),200,75);
        
        // 默認隱藏部分按鈕
        returntitle.setVisible(false);
        defaultmap.setVisible(false);
        importmap.setVisible(false);
        
        // 添加按鈕監聽事件
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
                new Makemap();
            }
        });

        defaultmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 指定檔案的具體位置
                File defaultDir = new File(System.getProperty("user.dir"), "demo/project");
                // 您可以將這裡的路徑修改為固定的檔案路徑
                String filePath = defaultDir.getPath() + "/defaultmap";  // 存放的是預設地圖
        
                File selectedFile = new File(filePath);
        
                // 檢查檔案是否存在
                if (selectedFile.exists()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                        DrawPanel loadedPanel = (DrawPanel) ois.readObject();
                        if (loadedPanel.CheckCollison() == true) {
                            GameMap window = new GameMap();
                            window.setMazesize(loadedPanel.width, loadedPanel.height);
                            window.setBG(loadedPanel.getBg());
                            window.setTimeleft(loadedPanel.getTimeLeft());
                            for (Obstables obstable : loadedPanel.Orectangles) {
                                window.addObstable(obstable, obstable.getType());
                            }
                            for (Portals portal : loadedPanel.Prectangles) {
                                window.addPortal(portal);
                            }
                        } else {
                            JLabel ErrorJLabel = new JLabel();
                            ErrorJLabel.setText("編譯不通過，請再檢查一下");
                            JOptionPane.showMessageDialog(null, ErrorJLabel, "Fail", JOptionPane.WARNING_MESSAGE);
                        }
        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "載入失敗:" + ex.getMessage());
                        ex.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "檔案不存在，請確認檔案路徑！");
                }
            }
        });
        
        importmap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				
				
                //抓到放檔案的相對位置
                File defaultDir = new File(System.getProperty("user.dir"), "demo/project");
                JFileChooser fileChooser = new JFileChooser(defaultDir);
                fileChooser.setDialogTitle("選擇載入路徑位置");
                int userSelection = fileChooser.showOpenDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(fileChooser.getSelectedFile()))) {
                        DrawPanel loadedPanel = (DrawPanel) ois.readObject();
                        if (loadedPanel.CheckCollison() == true){
							GameMap window = new GameMap();
							window.setMazesize(loadedPanel.width, loadedPanel.height);
        					window.setBG(loadedPanel.getBg());
        					window.setTimeleft(loadedPanel.getTimeLeft());
							for (Obstables obstable: loadedPanel.Orectangles) {
								window.addObstable(obstable, obstable.getType());
							}
							for (Portals portal: loadedPanel.Prectangles) {
								window.addPortal(portal);
							}
                        }else{
							JLabel ErrorJLabel = new JLabel();
							ErrorJLabel.setText("編譯不通過，請再檢查一下");
							JOptionPane.showMessageDialog(null,ErrorJLabel,"Fail",JOptionPane.WARNING_MESSAGE);
						}
                        
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "載入失敗:" + ex.getMessage());
                        ex.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
				
			}
		});
    }
}
