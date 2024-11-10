package mazemaker;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class mazedesignwin extends JFrame {
	
    mazedesignwin(makemap parent)
	{

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setBackground(Color.BLACK); // 設置內容面板背景色 
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

}
