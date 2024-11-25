package mazemaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class DrawPanel extends JPanel {
	private int Background;// 預設為0
	private int TimeLeft;// 預設為60
	private static JTextField xField, yField, widthField, heightField, ID, PID;

    public Point lp, sp;
    boolean isDraw = false;
    boolean isDrawRect = false;
    //放置圖片的地方
    public static Vector<Portals> Prectangles = new Vector<Portals>();
    public static Portals activePRectangle = null;
	public static Vector<Obstables> Orectangles = new Vector<Obstables>();
    public static Obstables activeORectangle = null;
    public JLabel BgJLabel;

    public DrawPanel() {
            Background = 0;
            TimeLeft = 60;
            setLayout(new BorderLayout());
            makemap.st = State.active;
            this.setLayout(null);
            lp = null;
            //設定背景圖片
            BgJLabel = new JLabel();
            BgJLabel.setBounds(0, 0, Page.width, Page.height);
            BgJLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            BgJLabel.setOpaque(true);
            this.add(BgJLabel);
            BGLabelset();
            
    
    
            this.addMouseMotionListener(new MouseAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (makemap.st == State.creatingRectangle) {
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
            
            this.addMouseListener(new MouseAdapter() {
    
                public void mousePressed(MouseEvent e) {
                    if (makemap.st == State.ready2drawRectangle) {
                        makemap.st = State.creatingRectangle;
                        DrawPanel.this.sp = e.getPoint();
                        DrawPanel.this.lp = null;
                    }
                    if (DrawPanel.this.activePRectangle != null) {
                        if (DrawPanel.this.activePRectangle.status == State.active) {
                            DrawPanel.this.activePRectangle.status = State.inactive;
                            DrawPanel.this.activePRectangle = null;
                            makemap.attributes.removeAll();
                            DrawPanel.this.validate();
                            DrawPanel.this.repaint();
    
                        }
                    }
                    if (DrawPanel.this.activeORectangle != null) {
                        if (DrawPanel.this.activeORectangle.status == State.active) {
                            DrawPanel.this.activeORectangle.status = State.inactive;
                            DrawPanel.this.activeORectangle = null;
                            makemap.attributes.removeAll();
                            DrawPanel.this.validate();
                            DrawPanel.this.repaint();
    
                        }
                    }
                }
                
                public void mouseReleased(MouseEvent e) {
    
                    if (makemap.st == State.creatingRectangle && lp != null) {
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
    
                        
                        if (makemap.sta == State.portalstate)
                        {
                            
                            Ptemp.setSize(width, height);
                            Ptemp.setLocation(x, y);
                            DrawPanel.this.add(Ptemp);
                            DrawPanel.this.Prectangles.add(Ptemp);
                            DrawPanel.this.activePRectangle = Ptemp;
                            addTab1(makemap.attributes, Ptemp);
                            activeORectangle = null;
                            //把背景圖放到最下面
                            BGLabelset();
                            System.out.println("+1");
                        }
                        else if(makemap.sta == State.obstablestate)
                        {
                            Otemp.setSize(width, height);
                            Otemp.setLocation(x, y);
                            DrawPanel.this.add(Otemp);
                            DrawPanel.this.Orectangles.add(Otemp);
                            DrawPanel.this.activeORectangle = Otemp;
                            addTab(makemap.attributes, Otemp);
                            activePRectangle = null;
                            //把背景圖放到最下面
                            BGLabelset();
                            System.out.println("+2");
                        }
                        
                        DrawPanel.this.validate();
                        DrawPanel.this.repaint();
                        
                        makemap.st = State.ready2drawRectangle;
                    }else if (makemap.st == State.creatingRectangle && lp == null){
                        //如果只點一下的話 要把它恢復成準備畫圖的型態
                        makemap.st = State.ready2drawRectangle;
                    }
                }
            });
            
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

        // 數值編輯區域
        JPanel editPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        xField = new JTextField(String.valueOf(label.getX()));
        yField = new JTextField(String.valueOf(label.getY()));
        widthField = new JTextField(String.valueOf(label.getWidth()));
        heightField = new JTextField(String.valueOf(label.getHeight()));

        editPanel.add(new JLabel("X 座標:"));
        editPanel.add(xField);
        editPanel.add(new JLabel("Y 座標:"));
        editPanel.add(yField);
        editPanel.add(new JLabel("寬度:"));
        editPanel.add(widthField);
        editPanel.add(new JLabel("高度:"));
        editPanel.add(heightField);
        System.out.println("Portals");
        tabPanel.add(editPanel, BorderLayout.CENTER);

        // 提交按鈕區域
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("儲存");
        JButton deleteButton = new JButton("刪除");
        // 刪除按鈕的行為
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawPanel parentPanel = (DrawPanel) label.getParent();
                label.getParent().remove(label);
                Orectangles.remove(label);
                activeORectangle = null;
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

        // 數值編輯區域
        JPanel editPanel = new JPanel(new GridLayout(6, 2, 8, 8));
        xField = new JTextField(String.valueOf(label.getX()));
        yField = new JTextField(String.valueOf(label.getY()));
        widthField = new JTextField(String.valueOf(label.getWidth()));
        heightField = new JTextField(String.valueOf(label.getHeight()));
        ID = new JTextField(String.valueOf(label.getId()));
        PID = new JTextField(String.valueOf(label.getPortalId()));
        editPanel.add(new JLabel("X 座標:"));
        editPanel.add(xField);
        editPanel.add(new JLabel("Y 座標:"));
        editPanel.add(yField);
        editPanel.add(new JLabel("寬度:"));
        editPanel.add(widthField);
        editPanel.add(new JLabel("高度:"));
        editPanel.add(heightField);
        editPanel.add(new JLabel("傳送門ID:"));
        editPanel.add(ID);
        editPanel.add(new JLabel("目標傳送門:"));
        editPanel.add(PID);
        tabPanel.add(editPanel, BorderLayout.CENTER);

        // 提交按鈕區域
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("儲存");
        JButton deleteButton = new JButton("刪除");
        // 刪除按鈕的行為
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrawPanel parentPanel = (DrawPanel) label.getParent();
                label.getParent().remove(label);
                Prectangles.remove(label);
                activePRectangle = null;
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
            a.setImage(a.getImage().getScaledInstance(Page.width, Page.height, Image.SCALE_SMOOTH));
            BgJLabel.setIcon(a);
        }else if (Bg == 2){
            a = new ImageIcon(getClass().getResource("/images/background/snow.jpg"));
            a.setImage(a.getImage().getScaledInstance(Page.width, Page.height, Image.SCALE_SMOOTH));
            BgJLabel.setIcon(a);
        }else if (Bg ==3){
            a = new ImageIcon(getClass().getResource("/images/background/desert.jpg"));
            a.setImage(a.getImage().getScaledInstance(Page.width, Page.height, Image.SCALE_SMOOTH));
            BgJLabel.setIcon(a);
        }
        //重新繪圖不然會被遮住
        BGLabelset();
	}
}
