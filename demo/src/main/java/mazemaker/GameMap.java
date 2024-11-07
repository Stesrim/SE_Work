package mazemaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;



public class GameMap extends JFrame implements KeyListener {
    private Obstable player;//玩家
    private ArrayList<Obstable> obstables;//存放障礙物、裝飾品、終點的陣列
    private ArrayList<Portal> portals;//存放傳送門的陣列

    //存放地圖的資料
    private JLayeredPane mapPanel;
    //放置時間的標籤
    private JLabel countdownLabel; 
    //放置結算的標籤 
    private JLabel theendLabel;

    //設定背景圖片和resize
    private ImageIcon backgroundIcon;
    JLabel backgroundLabel;
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
    //遊戲是否結束
    private boolean Gameover = false; 

    GameMap() {
        //製作視窗
        //關閉不等於程式結束##
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(windowWidth, windowHeight);
        this.setLayout(null);
        this.addKeyListener(this);

        obstables = new ArrayList<>();
        portals = new ArrayList<>();
        //宣告地圖大小
        mapPanel = new JLayeredPane();
        mapPanel.setSize(mapWidth, mapHeight);
        mapPanel.setLayout(null);
        theendLabel = new JLabel();
        theendLabel.setBounds(windowHeight / 2, windowWidth / 15, 300, 200);
        theendLabel.setFont(new Font("Arial", Font.BOLD, 40));
        theendLabel.setForeground(Color.RED);
        theendLabel.setText("You Win !");
        theendLabel.setVisible(false);
        //將塗層優先順序宣告好
        //拿到視窗的塗層
        JLayeredPane pane = getLayeredPane();
        countdownLabel = new JLabel();
        pane.add(mapPanel, JLayeredPane.DEFAULT_LAYER);
        pane.add(countdownLabel, JLayeredPane.PALETTE_LAYER);
        pane.add(theendLabel, JLayeredPane.DRAG_LAYER);
        
        
        this.setVisible(true);
    }
    //設定背景畫面
    public void setBG(int type){
        backgroundLabel = new JLabel();
        if (type == 1){
            backgroundIcon = new ImageIcon(GameMap.class.getResource("/images/background/all.jpg"));
        }else if (type == 2){
            backgroundIcon = new ImageIcon(GameMap.class.getResource("/images/background/sls.png"));
        }else if (type == 3){
            backgroundIcon = new ImageIcon(GameMap.class.getResource("/images/background/test.png"));
        }
        //把圖片大小改成跟地圖一樣
        backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(mapWidth, mapHeight, Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(backgroundIcon);
        backgroundLabel.setOpaque(false);
        backgroundLabel.setSize(mapWidth, mapHeight);
        backgroundLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        //背景圖片 默認層
        mapPanel.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
    }
    //倒數計時
    private void startCountdown() {
        //每100毫秒判斷一次
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft-= 100;
                countdownLabel.setText("Time left: " + timeLeft/1000 + " s");
                //如果數到0顯示出遊戲結果
                if (timeLeft <= 0 || Gameover) {
                    ((Timer)e.getSource()).stop();
                    if(timeLeft <= 0 ){
                        theendLabel.setText("You Loss !");
                    }
                    Gameover = true;
                    theendLabel.setVisible(true);
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
        this.timeLeft = timeLeft*1000;
        startCountdown();
        
    }
    //檢查有沒有接觸到終點碰撞
    public boolean checkEndSpot() {
        //模擬一個矩形 與玩家座標一模一樣 這邊使用玩家原座標是因為是用space來確認
        Rectangle playerBounds = player.getBounds();
        for (Obstable obstable : obstables) {
            if (obstable.getType() == 2){    
                Rectangle portalBounds = obstable.getBounds();
                if (playerBounds.intersects(portalBounds)) {
                    return true;
                }
            }
        }
        return false;
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
            if (obstable.isPassable() == false){
                Rectangle obstableBounds = obstable.getBounds();
                //如果碰撞的話 就會回傳true 
                if (playerBounds.intersects(obstableBounds)) {
                    return true;
                }
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
            mapPanel.add(player, JLayeredPane.MODAL_LAYER);
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
            mapPanel.add(obstable, JLayeredPane.PALETTE_LAYER);
        }

        this.repaint();
    }
    //新增傳送門
    public void addPortal(Portal portal) {
        portals.add(portal);
        mapPanel.add(portal, JLayeredPane.MODAL_LAYER);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int[] moveX={0, 0, -10, 10};
        int[] moveY={-10, 10, 0, 0};
        //獲得玩家的座標與地圖座標
        int newX = player.getX();
        int newY = player.getY();
        //獲得玩家實際按wasd的什麼
        int type = -1;
        //如果遊戲還沒結束 -> 則可以使用wasd控制人物
        if (!Gameover){
            switch (e.getKeyChar()) {
                case 'w': case 'W':
                    type = 0;    
                    newY += moveY[type];
                    newmapY -= moveY[type];
                    break;
                case 's': case 'S':
                    type = 1;
                    newY += moveY[type];
                    newmapY -= moveY[type];
                    break;
                case 'a': case 'A':
                    type = 2;    
                    newX += moveX[type];
                    newmapX -= moveX[type];
                    break;
                case 'd': case 'D':
                    type = 3;
                    newX += moveX[type];
                    newmapX -= moveX[type];
                    break;
                case ' ': 
                    //判斷是否碰撞傳送門 如果有就傳送 沒有的話無事發生
                    if (checkPortal() != -1) {
                        for(Portal portal : portals ){
                            if (checkPortal() == portal.getId()) {
                                newmapX -= portal.getX() + (portal.getWidth() - player.getWidth()) / 2 - newX;
                                newmapY -= portal.getY() + (portal.getHeight() - player.getHeight()) / 2 - newY;
                                newX = portal.getX() + (portal.getWidth() - player.getWidth()) / 2;
                                newY = portal.getY() + (portal.getHeight() - player.getHeight()) / 2;
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        //如果沒有接觸到障礙物和碰到邊界，則更新玩家位置以及更新地圖的相對位置視角
        if (!checkCollision(newX, newY) && !checkMargin(newX, newY)) {
            player.setLocation(newX, newY);
            checkWindowMargin(newmapX, newmapY);
        }else{
            //else 則慢慢減少位置間的距離差
            for (int i = 10 ;i >= 0; i--) {
                //逐步減少newX,newY的移動距離
                newX -= moveX[type] / 10;
                newY -= moveY[type] / 10;
                //慢慢恢復地圖相對位置視角
                newmapX += moveX[type] / 10; 
                newmapY += moveY[type] / 10;
                if (!checkCollision(newX, newY) && !checkMargin(newX, newY)) {
                    player.setLocation(newX, newY);
                    checkWindowMargin(newmapX, newmapY);
                    break;
                }
            }
        }
        //如果玩家與終點接觸到->遊戲結束
        if (checkEndSpot()){
            Gameover = true;
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
