package mazemaker;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


// 有移動事件的Obstables

public class Obstables extends Obstable {
    public DrawPanel parent;
    State status;
    ControlPoint cp=null;
    Point ol, lp = null;

    Obstables(DrawPanel parent) {
        super();
        this.parent = parent;
        this.setBackground(Color.yellow);
        this.status = State.active; // 初始化為選中
        this.setOpaque(true);

        // 滑鼠拖曳事件
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Obstables.this.status == State.ready2Move) {
                    Obstables.this.status = State.moving;
                    Obstables.this.setLocation(
                        ol.x + (e.getXOnScreen() - lp.x),
                        ol.y + (e.getYOnScreen() - lp.y));
                    
                }

                if (Obstables.this.status == State.moving) {
                    Obstables.this.setLocation(
                        ol.x + (e.getXOnScreen() - lp.x),
                        ol.y + (e.getYOnScreen() - lp.y)
                    );
                }
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
                        // Obstables.this.parent.activePRectangle.status = State.inactive;
                    }

                    // 設置當前矩形為選中狀態
                    Obstables.this.parent.activeORectangle = Obstables.this;
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

                }
            }
        });
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


