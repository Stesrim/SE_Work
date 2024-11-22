package mazemaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.sampled.Line;
import javax.swing.JPanel;

import sun.print.BackgroundLookupListener;

public class DrawPanel extends JPanel {
	private int Background;// 預設為-1
	private int TimeLeft;// 預設為60
    public Point lp, sp;
    boolean isDraw = false;
    boolean isDrawRect = false;
    public Vector<Portals> Prectangles = new Vector<Portals>();
    public Portals activePRectangle = null;
	public Vector<Obstables> Orectangles = new Vector<Obstables>();
    public Obstables activeORectangle = null;
	
    
    public DrawPanel() {
		Background = -1;
		TimeLeft = 60;
        setLayout(new BorderLayout());
		makemap.st = State.active;
        this.setLayout(null);
		lp = null;

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
				// if (makemap.sta == State.portalstate)
				// {
					if (DrawPanel.this.activePRectangle != null) {
						if (DrawPanel.this.activePRectangle.status == State.active) {
							DrawPanel.this.activePRectangle.status = State.inactive;
							DrawPanel.this.activePRectangle = null;
							DrawPanel.this.validate();
							DrawPanel.this.repaint();
							System.out.println("P");

						}
					}
				// }
				// else if(makemap.sta == State.obstablestate)
				// {
					if (DrawPanel.this.activeORectangle != null) {
						if (DrawPanel.this.activeORectangle.status == State.active) {
							DrawPanel.this.activeORectangle.status = State.inactive;
							DrawPanel.this.activeORectangle = null;
							DrawPanel.this.validate();
							DrawPanel.this.repaint();
							System.out.println("O");

						}
					}
				// }
            }
            
            public void mouseReleased(MouseEvent e) {

                if (makemap.st == State.creatingRectangle) {
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
						System.out.println(Ptemp.isPassable());
						activeORectangle = null;
					}
					else if(makemap.sta == State.obstablestate)
					{
						Otemp.setSize(width, height);
						Otemp.setLocation(x, y);
						DrawPanel.this.add(Otemp);
						DrawPanel.this.Orectangles.add(Otemp);
						DrawPanel.this.activeORectangle = Otemp;
						System.out.println(Otemp.isPassable());
						activePRectangle = null;
						
					}
					
                    DrawPanel.this.validate();
                    DrawPanel.this.repaint();
                    
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
	}
}
