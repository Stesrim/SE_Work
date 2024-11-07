package mazemaker;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class secondwindow extends JFrame {
	
	JButton exitBtn;
    JButton gamestart;
    JButton makemap;
    JButton defaultmap;
    JButton importmap;
    int click_count;
    JButton returntitle;
    secondwindow()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setBackground(Color.darkGray);
		
//		exitBtn = new JButton("離開");
//	    gamestart = new JButton("遊戲開始");
//	    makemap = new JButton("設計端");
	    returntitle = new JButton("重返主頁面");
//	    defaultmap = new JButton("預設地圖");
//	    importmap = new JButton("導入地圖");
//	    
//	    this.add(gamestart);
//	    this.add(makemap);
//	    this.add(exitBtn);
//	    this.add(defaultmap);
//	    this.add(importmap);
	    this.add(returntitle);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//	
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//	    gamestart.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.5),150,50);
//	    makemap.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.6),150,50);
//	    exitBtn.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.7),150,50);
//	    defaultmap.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.5),150,50);
//	    importmap.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.6),150,50);
	    returntitle.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.7),150,50);
//	    
//	    returntitle.setVisible(false); 
//	    defaultmap.setVisible(false); 
//	    importmap.setVisible(false); 
//
//
//	    
//	    
//
//	    
//
//	    gamestart.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	    	    defaultmap.setVisible(true); 
//	    	    importmap.setVisible(true); 
//	    	    returntitle.setVisible(true); 
//	    	    gamestart.setVisible(false); 
//	    	    makemap.setVisible(false); 
//	    	    exitBtn.setVisible(false); 	        }
//	    });
	    returntitle.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    dispose();
	    	    new title();
	        
	        
	        }
	    });
//	    
//
//	    
//	    exitBtn.addMouseListener(new MouseAdapter()
//	    {
//	    	public void mouseClicked(MouseEvent e)
//	    	{
//	    		System.exit(0);
//	    	}
//	    });
//	   
//	    makemap.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
//	        	new makemap();
//	        }
//	    });
//	    defaultmap.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
// 
//	        }
//	    });
//	    importmap.addActionListener(new ActionListener() {
//
//	        @Override
//	        public void actionPerformed(ActionEvent e) {
// 
//	        }
//	    });
//
//
//		
	}

}
