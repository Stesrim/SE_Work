package mazemaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;




public class DrawPanel extends JPanel implements Serializable{
	private int Background;// 預設為0
	private int TimeLeft;// 預設為60
    //編譯要用到的長寬
    public int width;
    public int height;
    //序列化後不會保留
	transient private static JTextField xField, yField, widthField, heightField, ID, PID;
    
    transient public Point lp, sp;
    boolean isDraw = false;
    boolean isDrawRect = false;
    //放置圖片的地方
    public Vector <Portals> Prectangles = new Vector<Portals>();
    public Portals activePRectangle = null;
	public Vector <Obstables> Orectangles = new Vector<Obstables>();
    public Obstables activeORectangle = null;
    public JLabel BgJLabel;

    public DrawPanel() {
            width = Page.width;
            height = Page.height;
            Background = 0;
            TimeLeft = 60;
            setLayout(new BorderLayout());
            Makemap.st = State.active;
            this.setLayout(null);
            lp = null;
            //設定背景圖片
            BgJLabel = new JLabel();
            BgJLabel.setBounds(0, 0, Page.width, Page.height);
            BgJLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            BgJLabel.setOpaque(true);
            this.add(BgJLabel);
            this.restoreListeners();
            BGLabelset();
    }
    public void paint(Graphics g)
    {

        super.paint(g);  // 呼叫父類的 paint() 方法來保持 JPanel 的正常繪製
    

            if(this.activePRectangle!=null)
            {
                g.setXORMode(new Color(0,255,255));
                
                g.drawRect(activePRectangle.getLocation().x-4,
                activePRectangle.getLocation().y-4,
                activePRectangle.getSize().width+8,
                activePRectangle.getSize().height+8);
                
            }
            if(this.activeORectangle!=null)
            {
                g.setXORMode(new Color(0,255,255));
                
                g.drawRect(activeORectangle.getLocation().x-4,
                activeORectangle.getLocation().y-4,
                activeORectangle.getSize().width+8,
                activeORectangle.getSize().height+8);
                activeORectangle.showControlPoint();
            }
        
    }
    public static void addTab(JTabbedPane tabbedPane, Obstables label) {
        if (tabbedPane.getTabCount() > 0) {
            tabbedPane.remove(0); // 只移除第一個標籤，保留其他
        }

        // 創建一個新的標籤頁面板
        JPanel tabPanel = new JPanel(new BorderLayout());
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        // 數值編輯區域
        xField = new JTextField(10);
        xField.setPreferredSize(new Dimension(100, 50));
        xField.setText(String.valueOf(label.getX()));
        xField.setFont(new Font("Arial", Font.PLAIN, 20));

        yField = new JTextField(10);
        yField.setPreferredSize(new Dimension(100, 50));
        yField.setText(String.valueOf(label.getY()));
        yField.setFont(new Font("Arial", Font.PLAIN, 20));

        widthField = new JTextField(10);
        widthField.setPreferredSize(new Dimension(100, 50));
        widthField.setText(String.valueOf(label.getWidth()));
        widthField.setFont(new Font("Arial", Font.PLAIN, 20));
   

        heightField = new JTextField(10);
        heightField.setPreferredSize(new Dimension(100, 50)); 
        heightField.setText(String.valueOf(label.getHeight()));
        heightField.setFont(new Font("Arial", Font.PLAIN, 20));
        

        // X座標
        JLabel xLabel = new JLabel("X 座標:");
        xLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0;
        editPanel.add(xLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        editPanel.add(xField, gbc);

        // Y座標
        JLabel yLabel = new JLabel("Y 座標:");
        yLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));  
        gbc.gridx = 0; gbc.gridy = 1;
        editPanel.add(yLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        editPanel.add(yField, gbc);

        // 寬度
        JLabel widthLabel = new JLabel("寬度:");
        widthLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 2;
        editPanel.add(widthLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        editPanel.add(widthField, gbc);

        // 高度
        JLabel heightLabel = new JLabel("高度:");
        heightLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 3;
        editPanel.add(heightLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        editPanel.add(heightField, gbc);
        tabPanel.add(editPanel, BorderLayout.NORTH);

        // 提交按鈕區域
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("儲存");
        saveButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        JButton deleteButton = new JButton("刪除");
        deleteButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));

        // 刪除按鈕的行為
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawPanel parentPanel = (DrawPanel) label.getParent();
                label.getParent().remove(label);
                parentPanel.Orectangles.remove(label);
                parentPanel.activeORectangle = null;
                tabbedPane.removeAll();
                parentPanel.revalidate();
                parentPanel.repaint();
            }
        });
        // 儲存按鈕的行為
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 驗證輸入是否為有效數字
                    int newX = Integer.parseInt(xField.getText());
                    int newY = Integer.parseInt(yField.getText());
                    int newWidth = Integer.parseInt(widthField.getText());
                    int newHeight = Integer.parseInt(heightField.getText());

                    // 更新 JLabel 屬性
                    label.setBounds(newX, newY, newWidth, newHeight);
                    ImageIcon tempIcon = (ImageIcon)label.getIcon();
                    tempIcon.setImage(tempIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    label.setIcon(tempIcon);
                    label.getParent().revalidate();
                    label.getParent().repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tabPanel, "請輸入有效的數字！", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        tabPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 自訂標籤標題
        String tabTitle = "障礙物";
        tabbedPane.addTab(tabTitle, tabPanel);
        tabbedPane.setSelectedComponent(tabPanel);
        tabbedPane.revalidate();
        tabbedPane.repaint();
    }
    public static void addTab1(JTabbedPane tabbedPane, Portals label) {
        if (tabbedPane.getTabCount() > 0) {
            tabbedPane.remove(0); // 只移除第一個標籤，保留其他
        }
        // 創建一個新的標籤頁面板
        JPanel tabPanel = new JPanel(new BorderLayout());
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // 數值編輯區域
        xField = new JTextField(10);
        xField.setPreferredSize(new Dimension(100, 50));
        xField.setText(String.valueOf(label.getX()));
        xField.setFont(new Font("Arial", Font.PLAIN, 20));

        yField = new JTextField(10);
        yField.setPreferredSize(new Dimension(100, 50));
        yField.setText(String.valueOf(label.getY()));
        yField.setFont(new Font("Arial", Font.PLAIN, 20));

        widthField = new JTextField(10);
        widthField.setPreferredSize(new Dimension(100, 50));
        widthField.setText(String.valueOf(label.getWidth()));
        widthField.setFont(new Font("Arial", Font.PLAIN, 20));
   

        heightField = new JTextField(10);
        heightField.setPreferredSize(new Dimension(100, 50)); 
        heightField.setText(String.valueOf(label.getHeight()));
        heightField.setFont(new Font("Arial", Font.PLAIN, 20));

        ID = new JTextField(10);
        ID.setPreferredSize(new Dimension(100, 50)); 
        ID.setText(String.valueOf(label.getId()));
        ID.setFont(new Font("Arial", Font.PLAIN, 20));
        
        PID = new JTextField(10);
        PID.setPreferredSize(new Dimension(100, 50)); 
        PID.setText(String.valueOf(label.getPortalId()));
        PID.setFont(new Font("Arial", Font.PLAIN, 20));

        // X座標
        JLabel xLabel = new JLabel("X 座標:");
        xLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        gbc.gridx = 0; gbc.gridy = 0;
        editPanel.add(xLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        editPanel.add(xField, gbc);


        // Y座標
        JLabel yLabel = new JLabel("Y 座標:");
        yLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));  
        gbc.gridx = 0; gbc.gridy = 1;
        editPanel.add(yLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        editPanel.add(yField, gbc);

        // 寬度
        JLabel widthLabel = new JLabel("寬度:");
        widthLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        gbc.gridx = 0; gbc.gridy = 2;
        editPanel.add(widthLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        editPanel.add(widthField, gbc);

        // 高度
        JLabel heightLabel = new JLabel("高度:");
        heightLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        gbc.gridx = 0; gbc.gridy = 3;
        editPanel.add(heightLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        editPanel.add(heightField, gbc);
        
        //傳送門ID
        JLabel IDLabel = new JLabel("傳送門ID:");
        IDLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        gbc.gridx = 0; gbc.gridy = 4;
        editPanel.add(IDLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        editPanel.add(ID, gbc);
        //目標傳送門
        JLabel PIDLabel = new JLabel("目標傳送門:");
        PIDLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        gbc.gridx = 0; gbc.gridy = 5;
        editPanel.add(PIDLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        editPanel.add(PID, gbc);
        
        tabPanel.add(editPanel, BorderLayout.NORTH);

        // 提交按鈕區域
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("儲存");
        saveButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        JButton deleteButton = new JButton("刪除");
        deleteButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
        // 刪除按鈕的行為
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawPanel parentPanel = (DrawPanel) label.getParent();
                label.getParent().remove(label);
                parentPanel.Prectangles.remove(label);
                parentPanel.activePRectangle = null;
                tabbedPane.removeAll();
                parentPanel.revalidate();
                parentPanel.repaint();
            }
        });
        // 儲存按鈕的行為
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 驗證輸入是否為有效數字
                    int newX = Integer.parseInt(xField.getText());
                    int newY = Integer.parseInt(yField.getText());
                    int newWidth = Integer.parseInt(widthField.getText());
                    int newHeight = Integer.parseInt(heightField.getText());
                    int newID = Integer.parseInt(ID.getText());
                    int newPID = Integer.parseInt(PID.getText());
                    // 更新 JLabel 屬性
                    label.setBounds(newX, newY, newWidth, newHeight);
                    ImageIcon tempIcon = (ImageIcon)label.getIcon();
                    tempIcon.setImage(tempIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    label.setIcon(tempIcon);
                    label.setId(newID);
                    label.setPortalId(newPID);
                    label.getParent().revalidate();
                    label.getParent().repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tabPanel, "請輸入有效的數字！", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        tabPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 自訂標籤標題
        String tabTitle = "傳送門";
        tabbedPane.addTab(tabTitle, tabPanel);
        tabbedPane.setSelectedComponent(tabPanel);
        tabbedPane.revalidate();
        tabbedPane.repaint();
    }
	//新增圖片的時候要把背景圖移動到最下面
    public void BGLabelset() {
        this.setComponentZOrder(BgJLabel, this.getComponentCount() - 1);
        this.repaint();
    }
    public int getTimeLeft(){
		return TimeLeft;
	}
	public void setTimeLeft(int time){
		TimeLeft = time;
	}
	public int getBg(){
		return Background;
	}
	public void setBg(int Bg){
		Background = Bg;
        ImageIcon a;
        if (Bg == 0){ 
            BgJLabel.setIcon(null);
        }else if (Bg == 1){
            a = new ImageIcon(getClass().getResource("/images/background/grass.jpg"));
            a.setImage(a.getImage().getScaledInstance(Page.width, Page.height, Image.SCALE_AREA_AVERAGING));
            BgJLabel.setIcon(a);
        }else if (Bg == 2){
            a = new ImageIcon(getClass().getResource("/images/background/snow.jpg"));
            a.setImage(a.getImage().getScaledInstance(Page.width, Page.height, Image.SCALE_AREA_AVERAGING));
            BgJLabel.setIcon(a);
        }else if (Bg ==3){
            a = new ImageIcon(getClass().getResource("/images/background/desert.jpg"));
            a.setImage(a.getImage().getScaledInstance(Page.width, Page.height, Image.SCALE_AREA_AVERAGING));
            BgJLabel.setIcon(a);
        }
        //重新繪圖不然會被遮住
        BGLabelset();
	}
    public void restoreListeners() {
        Makemap.st = State.active;    
        lp = null;
        sp = null;
        this.activeORectangle = null;
        this.activePRectangle = null;
        this.removeAll();
        this.add(BgJLabel);
        //繪圖區的拖曳事件
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Makemap.st == State.creatingRectangle) {
                    Graphics g = DrawPanel.this.getGraphics();

                    // 清除之前的矩形繪製
                    if (lp != null) {
                        
                        g.setXORMode(new Color(0, 255, 255));
                        int x = Math.min(DrawPanel.this.sp.x, lp.x);
                        int y = Math.min(DrawPanel.this.sp.y, lp.y);
                        int width = Math.abs(lp.x - DrawPanel.this.sp.x);
                        int height = Math.abs(lp.y - DrawPanel.this.sp.y);
                        g.drawRect(x, y,width, height);
                    }

                    g.setXORMode(new Color(0, 255, 255));

                    // 計算矩形的大小和位置，支持四個象限
                    int x = Math.min(DrawPanel.this.sp.x, e.getX());
                    int y = Math.min(DrawPanel.this.sp.y, e.getY());
                    int width = Math.abs(e.getX() - DrawPanel.this.sp.x);
                    int height = Math.abs(e.getY() - DrawPanel.this.sp.y);
                    
                    g.drawRect(x, y, width, height);

                    lp = e.getPoint();
                }
            }
        });
        //繪圖區的點擊放開事件
        this.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                if (Makemap.st == State.ready2drawRectangle) {
                    Makemap.st = State.creatingRectangle;
                    DrawPanel.this.sp = e.getPoint();
                    DrawPanel.this.lp = null;
                }
                if (DrawPanel.this.activePRectangle != null) {
                    if (DrawPanel.this.activePRectangle.status == State.active) {
                        DrawPanel.this.activePRectangle.status = State.inactive;
                        DrawPanel.this.activePRectangle = null;
                        Makemap.attributes.removeAll();
                        DrawPanel.this.validate();
                        DrawPanel.this.repaint();

                    }
                }
                if (DrawPanel.this.activeORectangle != null) {
                    if (DrawPanel.this.activeORectangle.status == State.active) {
                        DrawPanel.this.activeORectangle.status = State.inactive;
                        DrawPanel.this.activeORectangle = null;
                        Makemap.attributes.removeAll();
                        DrawPanel.this.validate();
                        DrawPanel.this.repaint();

                    }
                }
            }
            
            public void mouseReleased(MouseEvent e) {

                if (Makemap.st == State.creatingRectangle && lp != null) {
                    Graphics g = DrawPanel.this.getGraphics();
                    g.setXORMode(new Color(0, 255, 255));
                    g.drawRect(Math.min(DrawPanel.this.sp.x,lp.x), Math.min(DrawPanel.this.sp.y,lp.y),
                    Math.abs(lp.x - DrawPanel.this.sp.x),Math.abs( lp.y - DrawPanel.this.sp.y));

                    // 創建矩形對象並設置大小和位置
                    Portals Ptemp = new Portals(DrawPanel.this);
                    Obstables Otemp = new Obstables(DrawPanel.this);
                    // 計算矩形的正確大小和位置
                    int x = Math.min(DrawPanel.this.sp.x, e.getX());
                    int y = Math.min(DrawPanel.this.sp.y, e.getY());
                    int width = Math.abs(e.getX() - DrawPanel.this.sp.x);
                    int height = Math.abs(e.getY() - DrawPanel.this.sp.y);

                    
                    if (Makemap.sta == State.portalstate && width!= 0 && height != 0)
                    {
                        
                        Ptemp.setSize(width, height);
                        Ptemp.setLocation(x, y);
                        DrawPanel.this.add(Ptemp);
                        DrawPanel.this.Prectangles.add(Ptemp);
                        DrawPanel.this.activePRectangle = Ptemp;
                        Ptemp.Pbg.setImage(Ptemp.Pbg.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                        Ptemp.setIcon(Ptemp.Pbg);
                        addTab1(Makemap.attributes, Ptemp);
                        activeORectangle = null;
                        //把背景圖放到最下面
                        BGLabelset();
                    }
                    else if(Makemap.sta == State.obstablestate && width!= 0 && height != 0)
                    {
                        Otemp.setSize(width, height);
                        Otemp.setLocation(x, y);
                        DrawPanel.this.add(Otemp);
                        DrawPanel.this.Orectangles.add(Otemp);
                        activeORectangle = Otemp;
                        Otemp.setObg(Makemap.obstacletype);
                        Otemp.Obg.setImage(Otemp.Obg.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
                        Otemp.setIcon(Otemp.Obg);
                        //設定他的障礙物類別
                        Otemp.setPassable(Makemap.ispass);
                        Otemp.setType(Makemap.jtype);
                        addTab(Makemap.attributes, Otemp);
                        activePRectangle = null;
                        //把背景圖放到最下面
                        BGLabelset();
                    }
                    
                    DrawPanel.this.validate();
                    DrawPanel.this.repaint();
                    
                    Makemap.st = State.ready2drawRectangle;
                }else if (Makemap.st == State.creatingRectangle && lp == null){
                    //如果只點一下的話 要把它恢復成準備畫圖的型態
                    Makemap.st = State.ready2drawRectangle;
                }
            }
        });
        //把portal的事件加載回來
        for (Portals portal : Prectangles) {
            portal.status = State.inactive;
            this.add(portal);
            this.activePRectangle = portal;
            this.activeORectangle = null;
            portal.addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (portal.status == State.ready2Move) {
                        portal.status = State.moving;
                    }
                    if (portal.status == State.moving) {
                        portal.setLocation(
                            portal.ol.x + (e.getXOnScreen() - lp.x),
                            portal.ol.y + (e.getYOnScreen() - lp.y)
                        );
                    }
                    addTab1(Makemap.attributes, portal);
                }
            });
            portal.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    // 點擊時選中該矩形
                    if (portal.status == State.inactive) {
                        if (portal.parent.activePRectangle != null) {
                            // 取消其他矩形的選中狀態
                            portal.parent.activePRectangle.status = State.inactive;
                            portal.parent.activePRectangle = null;
                        }
    
                        // 設置當前矩形為選中狀態
                        portal.status = State.active;
                        portal.parent.activePRectangle = portal;
                        //叫標籤出來
                        addTab1(Makemap.attributes, portal);
                        
                        portal.parent.activeORectangle = null;
                        portal.validate();
                        portal.parent.repaint();
                    } else if (portal.status == State.active) {
                        // 準備移動該矩形
                        portal.ol = portal.getLocation();
                        if (lp == null) {
                            lp = new Point();
                        }
    
                        lp.x = e.getXOnScreen();
                        lp.y = e.getYOnScreen();
    
                        portal.status = State.ready2Move;
                        portal.parent.activePRectangle = null; // 移動時清空 activeRectangle
                        portal.parent.repaint();
                    }
                }
    
                public void mouseReleased(MouseEvent e) {
                    // 放開時根據狀態進行處理
                    if (portal.status == State.ready2Move || portal.status == State.moving) {
                        portal.status = State.active;
                        portal.parent.activePRectangle = portal;
                        portal.parent.repaint();
                        portal.parent.activeORectangle = null;
                        addTab1(Makemap.attributes, portal);
    
                    }
                }
            });
            
        }
        for (Obstables obstacle : Orectangles) {
            obstacle.status = State.inactive;//將它設定成不選取
            this.add(obstacle);
            activePRectangle = null;
            activeORectangle = obstacle;
            // 滑鼠拖曳事件
            obstacle.addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (obstacle.status == State.ready2Move) {
                        obstacle.status = State.moving;
                    }

                    if (obstacle.status == State.moving) {
                        obstacle.setLocation(
                            obstacle.ol.x + (e.getXOnScreen() - lp.x),
                            obstacle.ol.y + (e.getYOnScreen() - lp.y)
                        );
                    }
                    addTab(Makemap.attributes, obstacle);
                }
            });

            // 滑鼠點擊事件
            obstacle.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    // 點擊時選中該矩形
                    if (obstacle.status == State.inactive) {
                        obstacle.status=State.active;
                        if (obstacle.parent.activeORectangle != null) {
                            // 取消其他矩形的選中狀態
                            obstacle.parent.activeORectangle.status = State.inactive;
                        }

                        // 設置當前矩形為選中狀態
                        obstacle.parent.activeORectangle = obstacle;
                        //叫標籤出來
                        addTab(Makemap.attributes, obstacle);

                        obstacle.parent.activePRectangle = null;

                        obstacle.validate();
                        obstacle.parent.repaint();
                    } else if (obstacle.status == State.active) {
                        // 準備移動該矩形
                        obstacle.ol = obstacle.getLocation();
                        if (lp == null) {
                            lp = new Point();
                        }

                        lp.x = e.getXOnScreen();
                        lp.y = e.getYOnScreen();

                        obstacle.status = State.ready2Move;
                        obstacle.parent.activeORectangle = null; // 移動時清空 activeRectangle
                        obstacle.parent.repaint();
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    // 放開時根據狀態進行處理
                    if (obstacle.status == State.ready2Move || obstacle.status == State.moving) {
                        obstacle.status = State.active;
                        obstacle.parent.activeORectangle = obstacle;
                        obstacle.parent.repaint();
                        obstacle.parent.activePRectangle = null;
                        addTab(Makemap.attributes, obstacle);
                    }
                }
            });
            
            
        }
        BGLabelset();
        DrawPanel.this.activePRectangle = null;
        DrawPanel.this.activeORectangle = null;
    }
    //確認每個障礙物都沒有碰觸到與確認每個傳送門都可以傳送到目標傳送門
    public boolean CheckCollison(){
        int Playercount = 0;
        boolean EndSpot = false;
        //這邊是不能讓玩家跟其他物件有碰撞的情況
        for (Obstables obstable : Orectangles){
            if (obstable.getType() == 2){
                EndSpot = true;
            }
            //玩家
            if (obstable.getType() == 0) {
                System.out.println("implayer");
                Playercount++;
                Rectangle firstBounds = obstable.getBounds();
                for (Obstable temp : Orectangles){
                    if (obstable != temp){
                        Rectangle obstableBounds = temp.getBounds();
                        if (firstBounds.intersects(obstableBounds)) {
                            System.out.println("error1");
                            return false;
                        }
                    }
                }
                System.out.println("checkpoint");
                for (Portals portal : Prectangles){
                    Rectangle portalBounds = portal.getBounds();
                    if (firstBounds.intersects(portalBounds)) {
                        return false;
                    }
                }
            }
        }
        //有多個或0個玩家||沒有放置終點
        if (Playercount != 1 || EndSpot == false){
            return false;
        }
        for (Portals portal : Prectangles) {
            //傳送門要傳的id
            boolean check = false;
            int PorId = portal.getPortalId();
            for (Portals temp : Prectangles) {
                if (portal != temp) {
                    if(PorId == temp.getId()){
                        check = true;
                    }
                }
            }
            if (check == false) {
                return false;
            }
        }
        return true;
    }
}
