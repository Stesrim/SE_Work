package mazemaker;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ResourceBundle.Control;

import javax.swing.ImageIcon;

public class ControlPortalPoint implements Serializable{
	public Portals parent;
	Panel N, S, W, E, SW, SE, NE, NW;
	Point fp=null;
	Point orige=null;
    Dimension os;
    ImageIcon tempIcon;
	ControlPortalPoint(Portals p)
	{
		parent=p;
		E = new Panel();
		E.setBackground(Color.RED);
		E.setSize(9,9);
		parent.parent.add(E);
		
		E.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x,
                    ControlPortalPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPortalPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPortalPoint.this.parent.getHeight();
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPortalPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPortalPoint.this.parent.getHeight();
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		E.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.E.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.E.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});



		W = new Panel();
		W.setBackground(Color.RED);
		W.setSize(9,9);
		parent.parent.add(W);

		W.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x+ControlPortalPoint.this.parent.getWidth(),
									ControlPortalPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPortalPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPortalPoint.this.parent.getHeight();
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPortalPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPortalPoint.this.parent.getHeight();
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		W.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.W.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.W.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});

		
		S = new Panel();
		S.setBackground(Color.RED);
		S.setSize(9,9);
		parent.parent.add(S);

		S.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x,
									ControlPortalPoint.this.parent.getLocation().y);
					int x = ControlPortalPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = ControlPortalPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = ControlPortalPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = ControlPortalPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
													DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		S.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.S.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.S.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});


		N = new Panel();
		N.setBackground(Color.RED);
		N.setSize(9,9);
		parent.parent.add(N);

		N.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x,
									ControlPortalPoint.this.parent.getLocation().y+ControlPortalPoint.this.parent.getHeight());
					int x = ControlPortalPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = ControlPortalPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = ControlPortalPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = ControlPortalPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		N.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.N.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.N.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});


		SE = new Panel();
		SE.setBackground(Color.RED);
		SE.setSize(9,9);
		parent.parent.add(SE);
		
		SE.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x,ControlPortalPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;

				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));

                    ControlPortalPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		SE.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.SE.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.SE.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});
		
		
		
		

		SW = new Panel();
		SW.setBackground(Color.RED);
		SW.setSize(9,9);
		parent.parent.add(SW);

		SW.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x+ControlPortalPoint.this.parent.getWidth(),ControlPortalPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));

                    ControlPortalPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		SW.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.SW.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.SW.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});
		


		NE = new Panel();
		NE.setBackground(Color.RED);
		NE.setSize(9,9);
		parent.parent.add(NE);

		NE.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x,ControlPortalPoint.this.parent.getLocation().y+ControlPortalPoint.this.parent.getHeight());
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;

				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));

                    ControlPortalPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		NE.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.NE.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.NE.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});


		NW = new Panel();
		NW.setBackground(Color.RED);
		NW.setSize(9,9);
		parent.parent.add(NW);

		NW.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPortalPoint.this.parent.getLocation().x+ControlPortalPoint.this.parent.getWidth(),
									ControlPortalPoint.this.parent.getLocation().y+ControlPortalPoint.this.parent.getHeight());
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPortalPoint.this.parent.status=State.resizing;
				    ControlPortalPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPortalPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
                    ControlPortalPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab1(Makemap.attributes, ControlPortalPoint.this.parent);
                    ControlPortalPoint.this.parent.Pbg = new ImageIcon(getClass().getResource("/images/object image/indicator-round-b.png"));
                    ControlPortalPoint.this.parent.Pbg.setImage(ControlPortalPoint.this.parent.Pbg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPortalPoint.this.parent.setIcon(ControlPortalPoint.this.parent.Pbg);
				}	
			}
		});
		
		NW.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPortalPoint.this.parent.status==State.ready2resize)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPortalPoint.this.NW.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPortalPoint.this.NW.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.active)
				{
					
					os = ControlPortalPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPortalPoint.this.parent.parent.activePRectangle.closeControlPoint();
					ControlPortalPoint.this.parent.parent.activePRectangle=null;
					ControlPortalPoint.this.parent.parent.repaint();
					
					ControlPortalPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPortalPoint.this.parent.status==State.resizing)
				{
					ControlPortalPoint.this.parent.parent.activePRectangle=ControlPortalPoint.this.parent;
					ControlPortalPoint.this.parent.parent.repaint();
					ControlPortalPoint.this.parent.status=State.active;
				}
			}
			
		});

		parent.parent.setComponentZOrder(N, 0);
		parent.parent.setComponentZOrder(S, 0);
		parent.parent.setComponentZOrder(E, 0);
		parent.parent.setComponentZOrder(W, 0);
		parent.parent.setComponentZOrder(NE, 0);
		parent.parent.setComponentZOrder(NW, 0);
		parent.parent.setComponentZOrder(SE, 0);
		parent.parent.setComponentZOrder(SW, 0);

	}
	
	public void show()
	{
        N.setLocation(parent.getLocation().x + parent.getSize().width/2-4, 
				       parent.getLocation().y -4-4);
        S.setLocation(parent.getLocation().x + parent.getSize().width/2-4, 
				       parent.getLocation().y + parent.getSize().height-4+8-4);
        E.setLocation(parent.getLocation().x + parent.getSize().width-4+8-4, 
                       parent.getLocation().y + parent.getSize().height/2-4);
        W.setLocation(parent.getLocation().x -4-4, 
                       parent.getLocation().y + parent.getSize().height/2-4);
        SE.setLocation(parent.getLocation().x + parent.getSize().width, 
				       parent.getLocation().y + parent.getSize().height-4+8-4);
        NE.setLocation(parent.getLocation().x + parent.getSize().width-4+8-4, 
				       parent.getLocation().y -4-4);
        SW.setLocation(parent.getLocation().x -4-4, 
				       parent.getLocation().y + parent.getSize().height-4+8-4);
        NW.setLocation(parent.getLocation().x -4-4, 
				       parent.getLocation().y -4-4);
        N.setVisible(true);
        S.setVisible(true);
        W.setVisible(true);
        E.setVisible(true);
        NE.setVisible(true);
        NW.setVisible(true);
        SE.setVisible(true);
        SW.setVisible(true);

	}
    public void close()
    {
        N.setVisible(false);
        S.setVisible(false);
        W.setVisible(false);
        E.setVisible(false);
        NE.setVisible(false);
        NW.setVisible(false);
        SE.setVisible(false);
        SW.setVisible(false);

    }
	

}
