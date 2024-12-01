package mazemaker;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ResourceBundle.Control;

import javax.swing.ImageIcon;

public class ControlPoint implements Serializable{
	public Obstables parent;
	Panel N, S, W, E, SW, SE, NE, NW;
	Point fp=null;
	Point orige=null;
    Dimension os;
    ImageIcon tempIcon;
	ControlPoint(Obstables p)
	{
		parent=p;
		E = new Panel();
		E.setBackground(Color.RED);
		E.setSize(9,9);
		parent.parent.add(E);
		
		E.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x,
									ControlPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPoint.this.parent.getHeight();
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPoint.this.parent.getHeight();
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
                    ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		E.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.E.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.E.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x+ControlPoint.this.parent.getWidth(),
									ControlPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPoint.this.parent.getHeight();
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  ControlPoint.this.parent.getLocation().y;
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = ControlPoint.this.parent.getHeight();
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
                    ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		W.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.W.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.W.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x,
									ControlPoint.this.parent.getLocation().y);
					int x = ControlPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = ControlPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = ControlPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = ControlPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
													DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
                    ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		S.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.S.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.S.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x,
									ControlPoint.this.parent.getLocation().y+ControlPoint.this.parent.getHeight());
					int x = ControlPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = ControlPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = ControlPoint.this.parent.getLocation().x;
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = ControlPoint.this.parent.getWidth();
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
					ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		N.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.N.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.N.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x,ControlPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;

				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));

                    ControlPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
					ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		SE.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.SE.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.SE.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x+ControlPoint.this.parent.getWidth(),ControlPoint.this.parent.getLocation().y);
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;

				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height+(e.getYOnScreen() - fp.y));

                    ControlPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
                    ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		SW.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.SW.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.SW.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x,ControlPoint.this.parent.getLocation().y+ControlPoint.this.parent.getHeight());
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;

				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width+(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));

                    ControlPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
                    ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		NE.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.NE.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.NE.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					orige = new Point(ControlPoint.this.parent.getLocation().x+ControlPoint.this.parent.getWidth(),
									ControlPoint.this.parent.getLocation().y+ControlPoint.this.parent.getHeight());
					int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
				    ControlPoint.this.parent.status=State.resizing;
				    ControlPoint.this.parent.setBounds(x,
                                                    y,
                                                    newWidth,
                                                    newHeight);
				}
				else if(ControlPoint.this.parent.status==State.resizing)
				{
                    int x = Math.min(orige.x,e.getXOnScreen()-300);
                    int y =  Math.min(orige.y,e.getYOnScreen()-100+9);
                    int newWidth = Math.abs(os.width-(e.getXOnScreen() - fp.x));
                    int newHeight = Math.abs(os.height-(e.getYOnScreen() - fp.y));
					if (newWidth == 0 )
						newWidth = 1;
					if (newHeight== 0 )
						newHeight =1;
                    ControlPoint.this.parent.setBounds(x,
                                                        y,
                                                        newWidth,
                                                        newHeight);
					DrawPanel.addTab(Makemap.attributes, ControlPoint.this.parent);
                    ControlPoint.this.parent.setObg(ControlPoint.this.parent.obstacletype);
                    ControlPoint.this.parent.Obg.setImage(ControlPoint.this.parent.Obg.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING));
                    ControlPoint.this.parent.setIcon(ControlPoint.this.parent.Obg);
				}	
			}
		});
		
		NW.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(ControlPoint.this.parent.status==State.ready2resize)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
				}
            }
			public void mouseEntered(MouseEvent e)
			{				
				ControlPoint.this.NW.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));	
			}
			
			public void mouseExited(MouseEvent e)
			{
				ControlPoint.this.NW.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.active)
				{
					
					os = ControlPoint.this.parent.getSize();	
					if(fp==null)
						fp = new Point();
					
					fp.x = e.getXOnScreen();
					fp.y = e.getYOnScreen();
					
					ControlPoint.this.parent.parent.activeORectangle.closeControlPoint();
					ControlPoint.this.parent.parent.activeORectangle=null;
					ControlPoint.this.parent.parent.repaint();
					
					ControlPoint.this.parent.status=State.ready2resize;	
				}
			}
			
			public void mouseReleased(MouseEvent e)
			{
				if(ControlPoint.this.parent.status==State.resizing)
				{
					ControlPoint.this.parent.parent.activeORectangle=ControlPoint.this.parent;
					ControlPoint.this.parent.parent.repaint();
					ControlPoint.this.parent.status=State.active;
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
