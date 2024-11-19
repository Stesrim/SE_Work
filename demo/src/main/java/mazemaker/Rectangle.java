package mazemaker;

import java.awt.*;
import java.awt.event.*;

public class Rectangle extends Panel 
{
	public DrawPanel parent;
	State status;
	
	Point ol, lp=null;
	
	
	Rectangle(DrawPanel parent)
	{
		this.setBackground(Color.yellow);
		status=State.active;
		
		
		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(Rectangle.this.status==State.ready2Move)
				{
					Rectangle.this.status=State.moving;
					
					Rectangle.this.setLocation( ol.x + (e.getXOnScreen()-lp.x)
							                   ,ol.y + (e.getYOnScreen()-lp.y));
					
				}
				else if(Rectangle.this.status==State.moving)
				{
					Rectangle.this.setLocation( ol.x + (e.getXOnScreen()-lp.x)
			                   				   ,ol.y + (e.getYOnScreen()-lp.y));
				
				}
			}
		});

		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
			{
				if(Rectangle.this.status==State.inactive)
				{
					Rectangle.this.status=State.active;
					
					if(Rectangle.this.parent.activeRectangle!=null)
					{
						Rectangle.this.parent.activeRectangle.status = State.inactive;
					}
					
					Rectangle.this.parent.activeRectangle = Rectangle.this;
					Rectangle.this.validate();
					Rectangle.this.parent.repaint();
					
				}
				else if(Rectangle.this.status==State.active)
				{
					ol = Rectangle.this.getLocation();
					
					if(lp==null)
						lp = new Point();
					
					lp.x = e.getXOnScreen();
					lp.y = e.getYOnScreen();
					
					Rectangle.this.parent.activeRectangle=null;
					Rectangle.this.parent.repaint();
					Rectangle.this.status=State.ready2Move;
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(Rectangle.this.status==State.ready2Move)
				{
					Rectangle.this.status=State.active;
				}
				else if(Rectangle.this.status==State.moving)
				{
					Rectangle.this.parent.activeRectangle=Rectangle.this;
					Rectangle.this.parent.repaint();
					Rectangle.this.status=State.active;
				}
			}
			

		});
		
		
	}

}
