package mazemaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;



public class GameMap extends JFrame implements KeyListener {
    private Obstable player;//玩家
    private ArrayList<Obstable> obstables;//存放障礙物、裝飾品、終點的陣列
    private ArrayList<Portal> portals;//存放傳送門的陣列
    //放置遊戲音效
    private Clip gameClip;
    //碰撞音效
    private Clip collisionClip;
    private boolean isSoundPlaying;
    //用於控制音量
    private FloatControl volumeControl;
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
        this.setLocationRelativeTo(null);
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

        //遊戲方面的音效
        isSoundPlaying = false;
        playBackgroundMusic("gaming.wav", 60);
        //遊戲被關閉時需要停止音樂
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                stopBackgroundMusic(); // 停止音樂
                stopCollisionMusic(); // 碰撞音樂關閉
            }
        });
        this.setVisible(true);
    }
    //改變音量&&音樂
    private void playBackgroundMusic(String fileName, int volumePercentage) {
        try {
            File defaultDir = new File(System.getProperty("user.dir"), "mazemaker/demo/music");
            String filePath = defaultDir.getPath() + "/" + fileName;
            File musicFile = new File(filePath);
            stopBackgroundMusic();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            gameClip = AudioSystem.getClip();
            gameClip.open(audioStream);

            // 獲取音量控制
            volumeControl = (FloatControl) gameClip.getControl(FloatControl.Type.MASTER_GAIN);

            // 設定音量
            if (volumeControl != null) {
                // 將百分比音量轉換為 dB
                float min = volumeControl.getMinimum();
                float max = volumeControl.getMaximum();
                float dB = min + (max - min) * (volumePercentage / 100.0f); // 計算 dB 值
                volumeControl.setValue(dB);
            }

            gameClip.start();
            gameClip.loop(Clip.LOOP_CONTINUOUSLY); // 循環播放
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to play the background music: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //碰撞和傳送的音效
    private void playingSound(String fileName, int volumePercentage) {
        try {
            File defaultDir = new File(System.getProperty("user.dir"), "mazemaker/demo/music");
            String filePath = defaultDir.getPath() + "/" + fileName;
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            collisionClip = AudioSystem.getClip();
            collisionClip.open(audioStream);
            // 獲取音量控制
            volumeControl = (FloatControl) collisionClip.getControl(FloatControl.Type.MASTER_GAIN);

            // 設定音量
            if (volumeControl != null) {
                // 將百分比音量轉換為 dB
                float min = volumeControl.getMinimum();
                float max = volumeControl.getMaximum();
                float dB = min + (max - min) * (volumePercentage / 100.0f); // 計算 dB 值
                volumeControl.setValue(dB);
            }
            collisionClip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        // 音效播放完畢，將標誌設為 false 以允許重新播放
                        isSoundPlaying = false;
                    }
                }
            });
            if (collisionClip != null && !isSoundPlaying) {
                collisionClip.setFramePosition(0); // 重置音效播放位置
                collisionClip.start(); // 播放音效
                isSoundPlaying = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //停止播放 在關螢幕才會用到
    private void stopBackgroundMusic() {
        if (gameClip != null ){
            if (gameClip.isRunning()) {
                gameClip.stop(); // 停止播放
            }

            gameClip.close(); // 關閉音效資源
            gameClip = null;  // 清理資源
        }
    }
    //停止播放碰撞音效 在關螢幕才會用到
    private void stopCollisionMusic() {
        if (collisionClip != null ){
            if (collisionClip.isRunning()) {
                collisionClip.stop(); // 停止播放
            }

            collisionClip.close(); // 關閉音效資源
            collisionClip = null;  // 清理資源
        }
    }
    //設定地圖實際大小
    public void setMazesize(int Width, int Height){
        this.mapWidth = Width;
        this.mapHeight = Height;
        mapPanel.setSize(mapWidth, mapHeight);
    }
    //設定背景畫面
    public void setBG(int type){
        backgroundLabel = new JLabel();
        if (type != 0){
            if (type == 1){
                backgroundIcon = new ImageIcon(GameMap.class.getResource("/images/background/grass.jpg"));
            }else if (type == 2){
                backgroundIcon = new ImageIcon(GameMap.class.getResource("/images/background/snow.jpg"));
            }else if (type == 3){
                backgroundIcon = new ImageIcon(GameMap.class.getResource("/images/background/desert.jpg"));
            }
            //把圖片大小改成跟地圖一樣
            backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(mapWidth, mapHeight, Image.SCALE_AREA_AVERAGING));
            backgroundLabel.setIcon(backgroundIcon);
        }
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
                    //失敗的音效
                    playBackgroundMusic("gameOver.wav", 60);
                    ((Timer)e.getSource()).stop();
                    if(timeLeft <= 0 ){
                        theendLabel.setText("You Lose !");
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
        //如果超出邊界將視角調到邊界 沒超出邊界就用原本的位置
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
                                //傳送音效
                                playingSound("teleport.wav", 60);
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
            //碰撞音效
            playingSound("collision.wav", 60);
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
            //調整成過關的音效
            playBackgroundMusic("gameVictory.wav", 60);
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
