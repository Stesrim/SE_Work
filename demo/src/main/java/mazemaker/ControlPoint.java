package mazemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Point;
import java.io.Serializable;

public class ControlPoint implements Serializable{
	public Obstables parent;
	Panel N, S, W, E, SW, SE, NE, NW;
	Point lp = null;
    Dimension os;
	ControlPoint(Obstables p)
	{
		parent=p;
		N = new Panel();
		N.setBackground(Color.RED);
		N.setSize(9,9);
		S = new Panel();
		S.setBackground(Color.RED);
		S.setSize(9,9);
		W = new Panel();
		W.setBackground(Color.RED);
		W.setSize(9,9);
		E = new Panel();
		E.setBackground(Color.RED);
		E.setSize(9,9);
		SW = new Panel();
		SW.setBackground(Color.RED);
		SW.setSize(9,9);
		SE = new Panel();
		SE.setBackground(Color.RED);
		SE.setSize(9,9);
		NE = new Panel();
		NE.setBackground(Color.RED);
		NE.setSize(9,9);
		NW = new Panel();
		NW.setBackground(Color.RED);
		NW.setSize(9,9);
		parent.parent.add(N);
		parent.parent.add(S);
		parent.parent.add(E);
		parent.parent.add(W);
		parent.parent.add(SW);
		parent.parent.add(SE);
		parent.parent.add(NE);
		parent.parent.add(NW);
        // SE.addMouseMotionListener(new MouseAdapter() {
        //     public void mouseDragged(MouseEvent e)
        //     {
        //         if (Makemap.st == State.ready2resize)
        //         {
        //             Makemap.st = State.resizing;
        //             ControlPoint.this.parent.setSize(
        //                 os.width+e.getXOnScreen() - lp.x,
        //                 os.height+e.getYOnScreen()-lp.y);                    
        //         }
        //         else if (Makemap.st == State.resizing)
        //         {

        //         }
        //     }

        // });
        
        // SE.addMouseListener(new MouseAdapter ()
        // {
        //     public void mouseEntered(MouseEvent e)
        //     {

        //     }
        //     public void mousePressed(MouseEvent e)
        //     {
                
        //     }
        //     public void mouseExited(MouseEvent e)
        //     {
                
        //     }
        // }
        // );
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
        SE.setLocation(parent.getLocation().x + parent.getSize().width-4, 
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
