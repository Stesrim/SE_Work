package mazemaker;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Obstables extends Obstable {
    public DrawPanel parent;
    State status;

    Point ol, lp = null;

    Obstables(DrawPanel parent) {
        this.parent = parent;
        this.setBackground(Color.yellow);
        this.status = State.inactive; // 初始化為未選中
        this.setOpaque(true);

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
            }
        });

        // 滑鼠點擊事件
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // 點擊時選中該矩形
                if (Obstables.this.status == State.inactive) {
                    if (Obstables.this.parent.activeORectangle != null) {
                        // 取消其他矩形的選中狀態
                        Obstables.this.parent.activeORectangle.status = State.inactive;
                    }

                    // 設置當前矩形為選中狀態
                    Obstables.this.status = State.active;
                    Obstables.this.parent.activeORectangle = Obstables.this;

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
                }
            }
        });
    }
}
