package mazemaker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class GameMap extends JFrame implements KeyListener {
    private Obstable player;//玩家
    private ArrayList<Obstable> obstables;//存放障礙物的陣列
    private ArrayList<Portal> portals;//存放傳送門的陣列

    private JPanel mapPanel; //存放地圖的資料
    private JLabel countdownLabel; //放置時間的標籤
    
    //視窗實際大小    
    private int windowWidth = 800; 
    private int windowHeight = 600;
    //地圖實際大小    
    private int mapWidth = 2400;
    private int mapHeight = 1800;
    //邊界的緩衝區，讓玩家不要看到太多地圖邊界
    private int buffer = 100;
    //地圖實際的座標
    private int newmapX;
    private int newmapY;
    //默認時間
    private int timeLeft = 60; 

    GameMap() {
        //製作視窗
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.addKeyListener(this);

        obstables = new ArrayList<>();
        portals = new ArrayList<>();
        //宣告地圖大小
        mapPanel = new JPanel();
        mapPanel.setSize(mapWidth, mapHeight);
        mapPanel.setLayout(null);
        mapPanel.setBackground(Color.LIGHT_GRAY);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //將塗層優先順序宣告好
        JLayeredPane pane = getLayeredPane();//拿到視窗的塗層
        countdownLabel = new JLabel();
        pane.add (countdownLabel, 0);// ground
        pane.add(mapPanel, 1);// sky
        
        this.setVisible(true);
    }
    //倒數計時
    private void startCountdown() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                countdownLabel.setText("Time left: " + timeLeft + " s");
                //如果數到0會彈出視窗
                if (timeLeft <= 0) {
                    ((Timer)e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Time's up!");
                }
            }
        });
        timer.start();
    }
    //讓玩家自己設置時間 default:60s
    public void setTimeleft(int timeLeft){
        //初始化倒數計時的元素
        countdownLabel.setText("Time left: " + timeLeft + " s");
        countdownLabel.setFont(new Font("Arial", Font.BOLD, 20));
        countdownLabel.setForeground(Color.RED);
        countdownLabel.setBounds(windowWidth - 200, 10, 180, 30);
        
        startCountdown();
        this.timeLeft = timeLeft;
    }
    //檢查有沒有與傳送門碰撞
    public int checkPortal() {
        //模擬一個矩形 與玩家座標一模一樣 這邊使用玩家原座標是因為是用space來確認
        Rectangle playerBounds = player.getBounds();
        for (Portal portal : portals) {
            Rectangle portalBounds = portal.getBounds();
            if (playerBounds.intersects(portalBounds)) {
                return portal.getPortalId();
            }
        }
        return -1;
    }
    //檢查有沒有與障礙物碰撞
    public boolean checkCollision(int newX, int newY) {
        //模擬一個矩形 與玩家座標一模一樣 這邊不使用玩家原座標是因為玩家座標沒更新
        Rectangle playerBounds = new Rectangle(newX, newY, player.getWidth(), player.getHeight());
        //瀏覽每一個障礙物
        for (Obstable obstable : obstables) {
            //模擬一個矩形 與障礙物座標一模一樣
            Rectangle obstableBounds = obstable.getBounds();
            //如果碰撞的話 就會回傳true 
            if (playerBounds.intersects(obstableBounds)) {
                return true;
            }
        }
        return false;
    }
    //檢查是否走到邊界
    public boolean checkMargin(int newX, int newY) {
        return (newX < 0 || newX > mapWidth - player.getWidth() || newY < 0 || newY > mapHeight - player.getHeight());
    }
    //設定地圖的相對位置視角
    public void checkWindowMargin(int newmapX, int newmapY) {
        //左邊界
        int MiniumX = windowWidth / 2 - buffer - player.getWidth() / 2;
        //右邊界
        int MaximumX = windowWidth / 2 - mapWidth + buffer + player.getWidth() / 2;
        //上邊界
        int MiniumY = windowHeight / 2 - buffer - player.getHeight() / 2;
        //下邊界
        int MaximumY = windowHeight / 2 - mapHeight + buffer + player.getHeight() / 2;
        //如果超出邊界將視角調到邊界 沒超出邊界就用原本的邊界
        if (newmapX > MiniumX) {
            newmapX = MiniumX;
        } else if (newmapX < MaximumX) {
            newmapX = MaximumX;
        }
        if (newmapY > MiniumY) {
            newmapY = MiniumY;
        } else if (newmapY < MaximumY) {
            newmapY = MaximumY;
        }
        //更新地圖的實際視角
        mapPanel.setLocation(newmapX, newmapY);
    }
    //新增障礙物
    public void addObstable(Obstable obstable, int type) {
        //如果type = 0 -> 玩家
        if (type == 0) {
            player = obstable;
            //把玩家加到地圖
            mapPanel.add(player);
            //獲得玩家的實際位置
            int OriginX = player.getX();
            int OriginY = player.getY();
            int LimitMapX = windowWidth / 2;
            int LimitMapY = windowHeight / 2;
            //計算地圖視窗的實際位置 為了讓玩家居中
            newmapX = LimitMapX - OriginX - player.getWidth() / 2;
            newmapY = LimitMapY - OriginY - player.getHeight() / 2;
            
            if (OriginX < buffer)
                LimitMapX = LimitMapX - buffer - player.getWidth() / 2;
            else if (OriginX > mapWidth - buffer - player.getWidth())
                LimitMapX = LimitMapX - mapWidth + buffer + player.getWidth() / 2;
            else
                LimitMapX = LimitMapX - OriginX - player.getWidth() / 2;
            if (OriginY < buffer)
                LimitMapY = LimitMapY - buffer - player.getHeight() / 2;
            else if (OriginY > mapHeight - buffer - player.getHeight())
                LimitMapY = LimitMapY - mapHeight + buffer + player.getHeight() / 2;
            else
                LimitMapY = LimitMapY - OriginY - player.getHeight() / 2;
            //設定地圖的相對位置視角
            mapPanel.setLocation(LimitMapX, LimitMapY);
        } else {
            obstables.add(obstable);
            mapPanel.add(obstable);
        }

        this.repaint();
    }
    //新增傳送門
    public void addPortal(Portal portal) {
        portals.add(portal);
        mapPanel.add(portal);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //獲得玩家的座標與地圖座標
        int newX = player.getX();
        int newY = player.getY();
        int oldmapX = newmapX;
        int oldmapY = newmapY;
        //使用wasd控制人物
        switch (e.getKeyChar()) {
            case 'w':
                newY -= 10;
                newmapY += 10;
                break;
            case 's':
                newY += 10;
                newmapY -= 10;
                break;
            case 'a':
                newX -= 10;
                newmapX += 10;
                break;
            case 'd':
                newX += 10;
                newmapX -= 10;
                break;
            case ' ': 
                //判斷是否碰撞傳送門 如果有就傳送 沒有的話無事發生
                if (checkPortal() != -1) {
                    System.out.println("hi");
                }
                break;
        }
        if (!checkCollision(newX, newY) && !checkMargin(newX, newY)) {
            player.setLocation(newX, newY);
            checkWindowMargin(newmapX, newmapY);
        } else {
            newmapX = oldmapX;
            newmapY = oldmapY;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        //非必要 用來確認位置而已
        System.out.println("people Positon: " + player.getX() + ", " + player.getY());
        System.out.println("map Positon: " + mapPanel.getX() + ", " + mapPanel.getY());
        System.out.println("map Real Positon: " + newmapX + ", " + newmapY);
    }
}
