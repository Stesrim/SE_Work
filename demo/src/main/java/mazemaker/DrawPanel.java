package mazemaker;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.sampled.Line;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    public ArrayList<Portal> portals;
    public ArrayList<Obstable> obstables;
    public Point lp, sp;
	boolean isDraw = false;
	boolean isDrawRect = false;
	
	public Vector<Rectangle> rectangles;
	public Rectangle activeRectangle = null;
	// public State status;
    
    public DrawPanel() {
        this.portals = new ArrayList<>();
        this.obstables = new ArrayList<>();
        setLayout(new BorderLayout());

        makemap.st = State.active;
		
		
		this.setLayout(null);
		
		lp=null;
		rectangles=null;
		
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(makemap.st==State.creatingRectangle)
				{
					Graphics g = DrawPanel.this.getGraphics();
					
					if(lp!=null)
					{
						g.setXORMode(new Color(0,255,255));
						g.drawRect( DrawPanel.this.sp.x, 
                                    DrawPanel.this.sp.y, 
									lp.x-DrawPanel.this.sp.x, 
									lp.y-DrawPanel.this.sp.y);
					}
					
					g.setXORMode(new Color(0,255,255));
					//g.setColor(Color.red);
					
					if((e.getX()<sp.x) && (e.getY()>sp.y))
					{
						g.drawRect(e.getX(), 
                                    DrawPanel.this.sp.y, 
                                 DrawPanel.this.sp.x-e.getX(),
								   e.getY()-DrawPanel.this.sp.y);
					}
					else
					{
						g.drawRect( DrawPanel.this.sp.x, 
                                     DrawPanel.this.sp.y, 
									e.getX()-DrawPanel.this.sp.x, 
									e.getY()-DrawPanel.this.sp.y);
					}
					lp = e.getPoint();
				}
			}
		});
		
		this.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e)
			{
				if(makemap.st == State.ready2drawRectangle)
				{
					makemap.st = State.creatingRectangle;
					DrawPanel.this.sp = e.getPoint();
					DrawPanel.this.lp = null;
				}
				
				if(DrawPanel.this.activeRectangle!=null)
				{
					if(DrawPanel.this.activeRectangle.status==State.active)
					{
						DrawPanel.this.activeRectangle.status=State.inactive;
						DrawPanel.this.activeRectangle = null;
						DrawPanel.this.validate();
						DrawPanel.this.repaint();
					}
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(makemap.st == State.drawing)
				{
					lp=null;
					makemap.st = State.active;
				}
				else if(makemap.st == State.creatingRectangle)
				{
					Graphics g = DrawPanel.this.getGraphics();

					
					g.setXORMode(new Color(0,255,255));
					g.drawRect( DrawPanel.this.sp.x, 
								DrawPanel.this.sp.y, 
								lp.x-DrawPanel.this.sp.x, 
								lp.y-DrawPanel.this.sp.y);
					
					Rectangle temp=new Rectangle(DrawPanel.this);
					
					temp.setSize(e.getX()-DrawPanel.this.sp.x, e.getY()-DrawPanel.this.sp.y);
					temp.setLocation(DrawPanel.this.sp.x , DrawPanel.this.sp.y );
					
					DrawPanel.this.add(temp);
				
					
					if(rectangles==null)
						rectangles = new Vector<Rectangle>();
					
                        DrawPanel.this.rectangles.add(temp);
                        DrawPanel.this.activeRectangle = temp;
					
                        DrawPanel.this.validate();
                        DrawPanel.this.repaint();
					
                        makemap.st = State.active;
				}
			}
			
		});
		
		
		
	}
	
	// public void paint(Graphics g)
	// {

		

		
	// 	if(this.activeRectangle!=null)
	// 	{
	// 		g.setXORMode(new Color(0,255,255));
			
	// 		g.drawRect(activeRectangle.getLocation().x-4,
	// 				activeRectangle.getLocation().y-4,
	// 				activeRectangle.getSize().width+8,
	// 				activeRectangle.getSize().height+8);
			
	// 	}
    // }
}
