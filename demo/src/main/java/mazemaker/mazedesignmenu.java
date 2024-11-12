package mazemaker;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class mazedesignmenu extends JFrame{


	
    	mazedesignmenu(makemap parent){
		
        JButton returntitle = new JButton("重返主頁面");
	    parent.designWin.add(returntitle);
		
		JMenu fileMenu = new JMenu("檔案");
        JMenuItem openMenuItem = new JMenuItem("新增");
        JMenuItem saveMenuItem = new JMenuItem("保存");
        JMenuItem exitMenuItem = new JMenuItem("開啟");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        fileMenu.addSeparator();

		JMenu runMenu = new JMenu("執行");
		JMenuItem Comp = new JMenuItem("編譯");
		JMenuItem CompAndRun = new JMenuItem("編譯並執行");
		runMenu.add(Comp);
		runMenu.add(CompAndRun);

		JMenu editmMenu = new JMenu("編輯");
		JMenuItem settime = new JMenuItem("設定遊玩時間");
		JMenuItem setbackground = new JMenuItem("設定背景圖片");
		JMenuItem setmapsize = new JMenuItem("設地圖大小");
		editmMenu.add(settime);
		editmMenu.add(setbackground);
		editmMenu.add(setmapsize);

        JMenuBar menuBar = new JMenuBar();
		menuBar.add(returntitle);
        menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(editmMenu);


        parent.designWin.setJMenuBar(menuBar); 
	
		
		// Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	    // returntitle.setBounds((int)(screenSize.getWidth ()/2-75),(int)(screenSize.getHeight()*0.7),150,50);

	    returntitle.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    dispose();
	    	    new title();
	        
	        
	        }
	    });

    }
}
