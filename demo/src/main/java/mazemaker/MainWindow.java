package mazemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;



public class MainWindow extends JFrame {
	JButton exitBtn;
    JButton gamestart;
    JButton makemap;
    JButton defaultmap;
    JButton importmap;
    JButton returntitle;

	private defaultgame gameWindow;
	MainWindow()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBackground(Color.darkGray);
		
		exitBtn = new JButton("離開");
	    gamestart = new JButton("遊戲開始");
	    makemap = new JButton("設計端");
	    returntitle = new JButton("返回");
	    defaultmap = new JButton("預設地圖");
	    importmap = new JButton("導入地圖");
		
		exitBtn.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
		gamestart.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
		makemap.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
		returntitle.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
		defaultmap.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
		importmap.setFont(new Font("Microsoft JhengHei", Font.BOLD, 35));
	    this.add(gamestart);
	    this.add(makemap);
	    this.add(exitBtn);
	    this.add(defaultmap);
	    this.add(importmap);
	    this.add(returntitle);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    gamestart.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.5),200,75);
	    makemap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.6),200,75);
	    exitBtn.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.7),200,75);
	    defaultmap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.5),200,75);
	    importmap.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.6),200,75);
	    returntitle.setBounds((int)(screenSize.getWidth ()/2-100),(int)(screenSize.getHeight()*0.7),200,75);
	    
	    returntitle.setVisible(false); 
	    defaultmap.setVisible(false); 
	    importmap.setVisible(false); 
		
	    gamestart.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    defaultmap.setVisible(true); 
	    	    importmap.setVisible(true); 
	    	    returntitle.setVisible(true); 
	    	    gamestart.setVisible(false); 
	    	    makemap.setVisible(false); 
	    	    exitBtn.setVisible(false); 	        }
	    });
	    returntitle.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    defaultmap.setVisible(false);
	    	    importmap.setVisible(false);
	    	    returntitle.setVisible(false);
	    	    gamestart.setVisible(true);
	    	    makemap.setVisible(true);
	    	    exitBtn.setVisible(true); 	        
			}
	    });
	    

	    
	    exitBtn.addMouseListener(new MouseAdapter()
	    {
	    	public void mouseClicked(MouseEvent e)
	    	{
	    		System.exit(0);
	    	}
	    });
	   
	    makemap.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        	new makemap();
	        }
	    });
	    defaultmap.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
				if (gameWindow == null || !gameWindow.window.isVisible()) {
                    gameWindow = new defaultgame();
                }else {
					gameWindow.window.toFront();  
				}
	        }
	    });
	    importmap.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
 
	        }
	    });


		
	}

}
