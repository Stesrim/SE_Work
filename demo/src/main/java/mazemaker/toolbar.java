package mazemaker;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class Toolbar extends JPanel {
    private JToolBar toolBar;  // 工具條
    private JPanel contentPanel;  // 內容區域面板，顯示不同的物件
    private JPanel toolPanel;  // 用來顯示 JList 的面板
    private JButton obstacleButton; // 障礙物按鈕
    private JButton supportbtn;
    private JButton decoratebtn;
    private CardLayout cardLayout;  // 用來切換顯示的布局
    private ImageIcon listIcon;
    Toolbar(Makemap parent) {
        // 創建工具條並設置為垂直
        toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);
        // 添加工具按鈕
        obstacleButton = new JButton("障礙物");
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 18);  // 创建字体，大小为18
        obstacleButton.setFont(font);
        obstacleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(createObstacleListPanel(), "障礙物選項");
                contentPanel.add(toolBar, BorderLayout.EAST);
                contentPanel.revalidate();
                contentPanel.repaint();
                Makemap.st = State.active;
                // showObstacleList();  // 顯示障礙物選項

                
            }
        });
        toolBar.add(obstacleButton);
        supportbtn = new JButton("輔助物");
        supportbtn.setFont(font);
        supportbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(createSupportListPanel(), "輔助物選項");
                contentPanel.add(toolBar, BorderLayout.EAST);
                contentPanel.revalidate();
                contentPanel.repaint();
                Makemap.st = State.active;
                // showSupportList();  
            }
        });
        
        toolBar.add(supportbtn);
        // decoratebtn = new JButton("裝飾物");
        // decoratebtn.setFont(font);
        // toolBar.add(decoratebtn);
        decoratebtn = new JButton("裝飾物");
        decoratebtn.setFont(font);
        decoratebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(createDecoratebtnListPanel(), "裝飾物選項");
                contentPanel.add(toolBar, BorderLayout.EAST);
                contentPanel.revalidate();
                contentPanel.repaint();
                Makemap.st = State.active;
            }
        });
        
        toolBar.add(decoratebtn);
        // 設置工具條的位置

        // 創建卡片布局來管理內容區域
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        // 初始化 JList 面板
        
        // toolPanel = new JPanel();
        // toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
        // toolPanel.add(toolBar,BorderLayout.NORTH);
        // 將 JList 面板放入卡片布局中
        contentPanel.add(createObstacleListPanel(), "障礙物選項");
        toolBar.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPanel.add(toolBar, BorderLayout.NORTH);
        // 設置內容區域
        parent.designWin.add(contentPanel, BorderLayout.EAST);        
        

        

        // 顯示框架
        this.setVisible(true);
    }

    // 創建顯示障礙物選項的 JList 面板
    public JPanel createObstacleListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 创建 ImageIcon 数组并加载图片
        ImageIcon[] listicon = new ImageIcon[3];
        listicon[0] = new ImageIcon(getClass().getResource("/images/object image/foliagePack_053.png"));
        listicon[1] = new ImageIcon(getClass().getResource("/images/object image/foliagePack_011.png"));
        listicon[2] = new ImageIcon(getClass().getResource("/images/object image/牆壁.png"));

        // 创建 JList，使用 ImageIcon 数组
        JList<ImageIcon> obstacleList = new JList<>(listicon);

        // 设置字体
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 18);  // 设置为微软雅黑字体，大小为18
        obstacleList.setFont(font);

        // 使用自定义的 ListCellRenderer 来根据图片尺寸调整单元格大小
        obstacleList.setCellRenderer(new ListCellRenderer<ImageIcon>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends ImageIcon> list, ImageIcon value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel() {
                    @Override
                    public Dimension getPreferredSize() {
                        // 根据图片的宽高设置单元格大小
                        int width = value.getIconWidth();
                        int height = value.getIconHeight();
                        return new Dimension(width + 10, height + 10);  // 增加一些额外空间，避免图片紧贴边缘
                    }
                };

                // 使用 JLabel 来显示图片
                JLabel label = new JLabel(value);
                panel.setLayout(new BorderLayout());
                panel.add(label, BorderLayout.CENTER);

                // 设置选中时的背景颜色
                if (isSelected) {
                    panel.setBackground(Color.LIGHT_GRAY);
                } else {
                    panel.setBackground(Color.WHITE);
                }

                // 设置边框，增加项之间的间隔
                panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                return panel;
            }
        });

        // 设置 JList 的选择模式（例如单选模式）
        obstacleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 包裹 JList 的滚动面板
        JScrollPane scrollPane = new JScrollPane(obstacleList);

        // 限制显示区域的大小，避免显示过多项目
        scrollPane.setPreferredSize(new Dimension(160, 150));  // 设置滚动区域的大小

        // 添加到面板中
        panel.add(scrollPane, BorderLayout.WEST);

        // 处理鼠标点击事件
        obstacleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 判断鼠标是否单击
                if (e.getClickCount() == 1) {
                    // 获取 JList 被点击的项目索引
                    int index = obstacleList.locationToIndex(e.getPoint());
                    Makemap.st = State.active;
                    // 根据点击的索引设置不同的障碍类型
                    if (Makemap.st == State.active) {
                        if (index == 0) {
                            Makemap.obstacletype = 0;
                            Makemap.jtype = 1;
                            Makemap.ispass = false;
                            Makemap.sta = State.obstablestate;
                            Makemap.st = State.ready2drawRectangle;
                        } else if (index == 1) {
                            Makemap.obstacletype = 1;
                            Makemap.jtype = 1;
                            Makemap.ispass = false;
                            Makemap.sta = State.obstablestate;
                            Makemap.st = State.ready2drawRectangle;
                        } else if (index == 2) {
                            Makemap.obstacletype = 2;
                            Makemap.jtype = 1;
                            Makemap.ispass = false;
                            Makemap.sta = State.obstablestate;
                            Makemap.st = State.ready2drawRectangle;
                        }
                    }
                }
            }
        });

        return panel;
    }


    public JPanel createSupportListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 创建 ImageIcon 数组并加载图片
        ImageIcon[] listicon = new ImageIcon[3];
        listicon[0] = new ImageIcon(getClass().getResource("/images/playerandendspot/player.png"));
        listicon[1] = new ImageIcon(getClass().getResource("/images/object image/flag.png"));
        listicon[2] = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));

        // 创建 JList，使用 ImageIcon 数组
        JList<ImageIcon> obstacleList = new JList<>(listicon);

        // 设置字体
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 18);  // 设置为微软雅黑字体，大小为18
        obstacleList.setFont(font);

        // 使用自定义的 ListCellRenderer 来根据图片尺寸调整单元格大小
        obstacleList.setCellRenderer(new ListCellRenderer<ImageIcon>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends ImageIcon> list, ImageIcon value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel() {
                    @Override
                    public Dimension getPreferredSize() {
                        // 根据图片的宽高设置单元格大小
                        int width = value.getIconWidth();
                        int height = value.getIconHeight();
                        return new Dimension(width + 10, height + 10);  // 增加一些额外空间，避免图片紧贴边缘
                    }
                };

                // 使用 JLabel 来显示图片
                JLabel label = new JLabel(value);
                panel.setLayout(new BorderLayout());
                panel.add(label, BorderLayout.CENTER);

                // 设置选中时的背景颜色
                if (isSelected) {
                    panel.setBackground(Color.LIGHT_GRAY);
                } else {
                    panel.setBackground(Color.WHITE);
                }

                // 设置边框，增加项之间的间隔
                panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                return panel;
            }
        });

        // 设置 JList 的选择模式（例如单选模式）
        obstacleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 包裹 JList 的滚动面板
        JScrollPane scrollPane = new JScrollPane(obstacleList);

        // 限制显示区域的大小，避免显示过多项目
        scrollPane.setPreferredSize(new Dimension(160, 150));  // 设置滚动区域的大小

        // 添加到面板中
        panel.add(scrollPane, BorderLayout.WEST);
        
        obstacleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 判斷鼠標是否單擊
                if (e.getClickCount() == 1) {
                    // 獲取 JList 被點擊的項目索引
                    int index = obstacleList.locationToIndex(e.getPoint());
                    Makemap.st = State.active;
                    // 確保點擊的索引有效
                    if (index == 0) {
                        if(Makemap.st == State.active){
                            Makemap.obstacletype = 3;
                            Makemap.jtype = 0; //type0為玩家
                            Makemap.ispass = false;
                            Makemap.sta = State.obstablestate;
	    			    	Makemap.st = State.ready2drawRectangle;
	    			    }
                    
                    }
                    else if (index == 1) {
                        if(Makemap.st == State.active){
                            Makemap.obstacletype = 4;
                            Makemap.jtype = 2;//type2為終點
                            Makemap.ispass = true;
                            Makemap.sta = State.obstablestate;
	    			    	Makemap.st = State.ready2drawRectangle;
	    			    }
                    }
                    else if (index == 2) {
                        if(Makemap.st == State.active){
                            Makemap.sta = State.portalstate;
                            Makemap.st = State.ready2drawRectangle;
                            System.out.println(Makemap.sta);
                        }
                    
                    }

                }
            }
        });

        return panel;
    }
    public JPanel createDecoratebtnListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 创建 ImageIcon 数组并加载图片
        ImageIcon[] listicon = new ImageIcon[1];
        listicon[0] = new ImageIcon(getClass().getResource("/images/object image/foliagePack_019.png"));


        // 创建 JList，使用 ImageIcon 数组
        JList<ImageIcon> obstacleList = new JList<>(listicon);

        // 设置字体
        Font font = new Font("Microsoft YaHei", Font.PLAIN, 18);  // 设置为微软雅黑字体，大小为18
        obstacleList.setFont(font);

        // 使用自定义的 ListCellRenderer 来根据图片尺寸调整单元格大小
        obstacleList.setCellRenderer(new ListCellRenderer<ImageIcon>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends ImageIcon> list, ImageIcon value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel() {
                    @Override
                    public Dimension getPreferredSize() {
                        // 根据图片的宽高设置单元格大小
                        int width = value.getIconWidth();
                        int height = value.getIconHeight();
                        return new Dimension(width + 10, height + 10);  // 增加一些额外空间，避免图片紧贴边缘
                    }
                };

                // 使用 JLabel 来显示图片
                JLabel label = new JLabel(value);
                panel.setLayout(new BorderLayout());
                panel.add(label, BorderLayout.CENTER);

                // 设置选中时的背景颜色
                if (isSelected) {
                    panel.setBackground(Color.LIGHT_GRAY);
                } else {
                    panel.setBackground(Color.WHITE);
                }

                // 设置边框，增加项之间的间隔
                panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                return panel;
            }
        });

        // 设置 JList 的选择模式（例如单选模式）
        obstacleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 包裹 JList 的滚动面板
        JScrollPane scrollPane = new JScrollPane(obstacleList);

        // 限制显示区域的大小，避免显示过多项目
        scrollPane.setPreferredSize(new Dimension(160, 150));  // 设置滚动区域的大小

        // 添加到面板中
        panel.add(scrollPane, BorderLayout.WEST);
        
        obstacleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 判斷鼠標是否單擊
                if (e.getClickCount() == 1) {
                    // 獲取 JList 被點擊的項目索引
                    int index = obstacleList.locationToIndex(e.getPoint());
                    Makemap.st = State.active;
                    // 確保點擊的索引有效
                    if (index == 0) {
                        if(Makemap.st == State.active){
                            Makemap.obstacletype = 5;
                            Makemap.jtype = 1; 
                            Makemap.ispass = true;
                            Makemap.sta = State.obstablestate;
	    			    	Makemap.st = State.ready2drawRectangle;
	    			    }
                    
                    }
                    

                }
            }
        });

        return panel;
    }

}