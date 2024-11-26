package mazemaker;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Portals extends Portal implements Serializable{
    public DrawPanel parent;
    State status;

    Point ol, lp = null;

    Portals(DrawPanel parent) {
        super();
        this.parent = parent;
        this.setBackground(Color.BLACK);
        this.status = State.active; // 初始化為選中
        this.setOpaque(true);

        // 滑鼠拖曳事件
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Portals.this.status == State.ready2Move) {
                    Portals.this.status = State.moving;
                }
                if (Portals.this.status == State.moving) {
                    Portals.this.setLocation(
                        ol.x + (e.getXOnScreen() - lp.x),
                        ol.y + (e.getYOnScreen() - lp.y)
                    );
                }
                DrawPanel.addTab1(makemap.attributes, Portals.this);
            }
        });

        // 滑鼠點擊事件
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // 點擊時選中該矩形
                if (Portals.this.status == State.inactive) {
                    if (Portals.this.parent.activePRectangle != null) {
                        // 取消其他矩形的選中狀態
                        Portals.this.parent.activePRectangle.status = State.inactive;
                        Portals.this.parent.activePRectangle = null;
                    }

                    // 設置當前矩形為選中狀態
                    Portals.this.status = State.active;
                    Portals.this.parent.activePRectangle = Portals.this;
                    //叫標籤出來
                    DrawPanel.addTab1(makemap.attributes, Portals.this);
                    
                    Portals.this.parent.activeORectangle = null;
                    Portals.this.validate();
                    Portals.this.parent.repaint();
                } else if (Portals.this.status == State.active) {
                    // 準備移動該矩形
                    ol = Portals.this.getLocation();
                    if (lp == null) {
                        lp = new Point();
                    }

                    lp.x = e.getXOnScreen();
                    lp.y = e.getYOnScreen();

                    Portals.this.status = State.ready2Move;
                    Portals.this.parent.activePRectangle = null; // 移動時清空 activeRectangle
                    Portals.this.parent.repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                // 放開時根據狀態進行處理
                if (Portals.this.status == State.ready2Move || Portals.this.status == State.moving) {
                    Portals.this.status = State.active;
                    Portals.this.parent.activePRectangle = Portals.this;
                    Portals.this.parent.repaint();
                    Portals.this.parent.activeORectangle = null;
                    DrawPanel.addTab1(makemap.attributes, Portals.this);

                }
            }
        });
    }
}
