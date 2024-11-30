package mazemaker;

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
    int bg;
    public ImageIcon Obg;
    public int obstacletype;
    Obstables(DrawPanel parent) {
        super();
        obstacletype = Makemap.obstacletype;
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
                DrawPanel.addTab(Makemap.attributes, Obstables.this);
                closeControlPoint();
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
                        Obstables.this.parent.activeORectangle.closeControlPoint();
                        Obstables.this.parent.activeORectangle.status = State.inactive;
                    }

                    // 設置當前矩形為選中狀態
                    Obstables.this.parent.activeORectangle = Obstables.this;
                    //叫標籤出來
                    DrawPanel.addTab(Makemap.attributes, Obstables.this);

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
                    DrawPanel.addTab(Makemap.attributes, Obstables.this);
                    showControlPoint();
                }
            }
        });
    }
    public void setObg(int obstacletype){
        switch (obstacletype) {
            case 0: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/foliagePack_053.png"));
                break;
            case 1: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/foliagePack_011.png"));
                break;
            case 2: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/牆壁.png"));
                break;
            case 3: 
                Obg = new ImageIcon(getClass().getResource("/images/playerandendspot/player.png"));
                break;
            case 4: 
                Obg = new ImageIcon(getClass().getResource("/images/object image/flag.png"));
                break;
            case 5: 
            Obg = new ImageIcon(getClass().getResource("/images/object image/foliagePack_019.png"));
            break;
        
    }
    
    
    
        
        // 根據每個矩形的 obstacletype 設置背景圖片
        

        // 如果背景圖片不為空，則繪製背景
        // if (Obg != null) {
        //     Image img = Obg.getImage();
        //     g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // 填充整個區域
        // } else {
        //     // 如果没有图片，绘制默认背景颜色
        //     g.setColor(Color.YELLOW);
        //     g.fillRect(0, 0, getWidth(), getHeight());
        // }
    }
    public void showControlPoint()
    {
        if(cp==null)
		{
			cp=new ControlPoint(Obstables.this);
		}
		cp.show();
    }
    public void closeControlPoint()
    {
        if(cp==null)
		{
			cp=new ControlPoint(Obstables.this);
		}
		cp.close();
    }
}


