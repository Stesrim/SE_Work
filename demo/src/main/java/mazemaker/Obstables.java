package mazemaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.ImageIcon;


// 有移動事件的Obstables

public class Obstables extends Obstable implements Serializable{
    public DrawPanel parent;
    State status;
    ControlPoint cp=null;
    Point ol, lp = null;
    public ImageIcon Obg;
    public int obstacletype;
    Obstables(DrawPanel parent) {
        super();
        obstacletype = makemap.obstacletype;
        this.parent = parent;
        // this.setBackground(Color.yellow);
        this.status = State.active; // 初始化為選中
        this.setOpaque(false);

        // 滑鼠拖曳事件
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Obstables.this.status == State.ready2Move) {
                    Obstables.this.status = State.moving;
                }

                if (Obstables.this.status == State.moving) {
                    Obstables.this.setLocation(
                        ol.x + (e.getXOnScreen() - lp.x),
                        ol.y + (e.getYOnScreen() - lp.y)
                    );
                }
                DrawPanel.addTab(makemap.attributes, Obstables.this);
            }
        });

        // 滑鼠點擊事件
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // 點擊時選中該矩形
                if (Obstables.this.status == State.inactive) {
                    Obstables.this.status=State.active;
                    
                    if (Obstables.this.parent.activeORectangle != null) {
                        // 取消其他矩形的選中狀態
                        Obstables.this.parent.activeORectangle.status = State.inactive;
                    }

                    // 設置當前矩形為選中狀態
                    Obstables.this.parent.activeORectangle = Obstables.this;
                    //叫標籤出來
                    DrawPanel.addTab(makemap.attributes, Obstables.this);

                    Obstables.this.parent.activePRectangle = null;

                    Obstables.this.validate();
                    Obstables.this.parent.repaint();
                } else if (Obstables.this.status == State.active) {
                    // 準備移動該矩形
                    ol = Obstables.this.getLocation();
                    if (lp == null) {
                        lp = new Point();
                    }

                    lp.x = e.getXOnScreen();
                    lp.y = e.getYOnScreen();

                    Obstables.this.status = State.ready2Move;
                    Obstables.this.parent.activeORectangle = null; // 移動時清空 activeRectangle
                    Obstables.this.parent.repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                // 放開時根據狀態進行處理
                if (Obstables.this.status == State.ready2Move || Obstables.this.status == State.moving) {
                    Obstables.this.status = State.active;
                    Obstables.this.parent.activeORectangle = Obstables.this;
                    Obstables.this.parent.repaint();
                    Obstables.this.parent.activePRectangle = null;
                    DrawPanel.addTab(makemap.attributes, Obstables.this);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 调用父类的绘制方法

        // 根據每個矩形的 obstacletype 設置背景圖片
        switch (this.obstacletype) {
            case 0: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/foliagePack_053.png"));
                Obstables.this.parent.repaint();
                break;
            case 1: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/foliagePack_011.png"));
                Obstables.this.parent.repaint();
                break;
            case 2: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/牆壁.png"));
                Obstables.this.parent.repaint();
                break;
            case 3: 
                Obg = new ImageIcon(getClass().getResource("/images/playerandendspot/player.png"));
                Obstables.this.parent.repaint();
                break;
            case 4: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/flag.png"));
                Obstables.this.parent.repaint();
                break;
            
        }

        // 如果背景圖片不為空，則繪製背景
        if (Obg != null) {
            Image img = Obg.getImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // 填充整个组件区域
        } else {
            // 如果没有图片，绘制默认背景颜色
            g.setColor(Color.YELLOW);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    public void showControlPoint()
    {
        // if(cp == null)
        // {
        //     // cp = new ControlPoint();
        // }
        // cp.show();
    }
}


