package mazemaker;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rectangle extends Obstable {
    public DrawPanel parent;
    State status;

    Point ol, lp = null;

    Rectangle(DrawPanel parent) {
        this.parent = parent;
        this.setBackground(Color.yellow);
        this.status = State.inactive; // 初始化為未選中
        this.setOpaque(true);

        // 滑鼠拖曳事件
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (Rectangle.this.status == State.ready2Move) {
                    Rectangle.this.status = State.moving;
                }

                if (Rectangle.this.status == State.moving) {
                    Rectangle.this.setLocation(
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
                if (Rectangle.this.status == State.inactive) {
                    if (Rectangle.this.parent.activeRectangle != null) {
                        // 取消其他矩形的選中狀態
                        Rectangle.this.parent.activeRectangle.status = State.inactive;
                    }

                    // 設置當前矩形為選中狀態
                    Rectangle.this.status = State.active;
                    Rectangle.this.parent.activeRectangle = Rectangle.this;

                    Rectangle.this.validate();
                    Rectangle.this.parent.repaint();
                } else if (Rectangle.this.status == State.active) {
                    // 準備移動該矩形
                    ol = Rectangle.this.getLocation();
                    if (lp == null) {
                        lp = new Point();
                    }

                    lp.x = e.getXOnScreen();
                    lp.y = e.getYOnScreen();

                    Rectangle.this.status = State.ready2Move;
                    Rectangle.this.parent.activeRectangle = null; // 移動時清空 activeRectangle
                    Rectangle.this.parent.repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                // 放開時根據狀態進行處理
                if (Rectangle.this.status == State.ready2Move || Rectangle.this.status == State.moving) {
                    Rectangle.this.status = State.active;
                    Rectangle.this.parent.activeRectangle = Rectangle.this;
                    Rectangle.this.parent.repaint();
                }
            }
        });
    }
}
