package mazemaker;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class mazedesignmenu extends JFrame{
	private Page page;
    mazedesignmenu(makemap parent, Page tabpane){	
        this.page = tabpane;
		
		JButton returntitle = new JButton("重返主頁面");
	    parent.designWin.add(returntitle);
		Font menuFont = new Font("Microsoft JhengHei", Font.BOLD, 26);
		Font itemFont = new Font("Microsoft JhengHei", Font.BOLD, 20);
		JMenu fileMenu = new JMenu("檔案");
		returntitle.setFont(menuFont);

        JMenuItem openMenuItem = new JMenuItem("新增頁面");
		JMenuItem deleteMenuItem = new JMenuItem("刪除頁面");
        JMenuItem saveMenuItem = new JMenuItem("保存");
        JMenuItem exitMenuItem = new JMenuItem("開啟");

		openMenuItem.setFont(itemFont);
		deleteMenuItem.setFont(itemFont);
		saveMenuItem.setFont(itemFont);
		exitMenuItem.setFont(itemFont);

		fileMenu.setFont(menuFont);
        fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
        fileMenu.add(deleteMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        

		JMenu runMenu = new JMenu("執行");
		JMenuItem Comp = new JMenuItem("編譯");
		JMenuItem CompAndRun = new JMenuItem("編譯並執行");

		Comp.setFont(itemFont);
		CompAndRun.setFont(itemFont);

		runMenu.setFont(menuFont);
		runMenu.add(Comp);
		runMenu.addSeparator();
		runMenu.add(CompAndRun);

		JMenu editMenu = new JMenu("編輯");
		JMenuItem settime = new JMenuItem("設定遊玩時間");
		JMenuItem setbackground = new JMenuItem("設定背景圖片");
		JMenuItem setmapsize = new JMenuItem("設地圖大小");
		settime.setFont(itemFont);
		setbackground.setFont(itemFont);
		setmapsize.setFont(itemFont);

		editMenu.setFont(menuFont);
		editMenu.add(settime);
		editMenu.addSeparator();
		editMenu.add(setbackground);
		editMenu.addSeparator();
		editMenu.add(setmapsize);

        JMenuBar menuBar = new JMenuBar();
		menuBar.add(returntitle);
        menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(editMenu);


        parent.designWin.setJMenuBar(menuBar); 
	
		
		// Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	    openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.addNewTab();
            }
        });
		deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.removeCurrentTab();
            }
        });
	    returntitle.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    dispose();
	    	    new title();
	        }
	    });

    }
}
