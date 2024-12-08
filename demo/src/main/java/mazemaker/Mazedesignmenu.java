package mazemaker;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class Mazedesignmenu extends JFrame{
	private WorkSpace page;
	public Makemap parent;

    Mazedesignmenu(Makemap parent, WorkSpace tabpane){	
        this.page = tabpane;
		// 新增重返主頁面按鈕並添加進designWin
		JButton returntitle = new JButton("重返主頁面");
	    parent.designWin.add(returntitle);
		// 設置字體
		Font menuFont = new Font("Microsoft JhengHei", Font.BOLD, 26);
		Font itemFont = new Font("Microsoft JhengHei", Font.BOLD, 20);
		// Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
		// 新增 檔案的JMenu與JMenuItem
		JMenu fileMenu = new JMenu("檔案");
		returntitle.setFont(menuFont);
        JMenuItem newPage = new JMenuItem("新增頁面");
		JMenuItem deletePage = new JMenuItem("刪除頁面");
        JMenuItem saveFile =new JMenuItem("保存");
        JMenuItem openFile = new JMenuItem("開啟");

		newPage.setFont(itemFont);
		deletePage.setFont(itemFont);
		saveFile.setFont(itemFont);
		openFile.setFont(itemFont);
		// 將JMenuItem添加至Jmenu
		fileMenu.setFont(menuFont);
        fileMenu.add(newPage);
		fileMenu.addSeparator();
        fileMenu.add(deletePage);
		fileMenu.addSeparator();
		fileMenu.add(saveFile);
		fileMenu.addSeparator();
        fileMenu.add(openFile);
        
		// 新增 執行的JMenu與JMenuItem
		JMenu runMenu = new JMenu("執行");
		JMenuItem Cheak = new JMenuItem("檢查");
		JMenuItem CheakAndTest = new JMenuItem("檢查並測試");

		Cheak.setFont(itemFont);
		CheakAndTest.setFont(itemFont);
		// 將JMenuItem添加至Jmenu
		runMenu.setFont(menuFont);
		runMenu.add(Cheak);
		runMenu.addSeparator();
		runMenu.add(CheakAndTest);
		// 新增 編輯的JMenu與JMenuItem
		JMenu editMenu = new JMenu("編輯");
		JMenuItem setPlaytime = new JMenuItem("設定遊玩時間");
		JMenuItem setbackground = new JMenuItem("設定背景圖片");
		setPlaytime.setFont(itemFont);
		setbackground.setFont(itemFont);
		// 將JMenuItem添加至Jmenu
		editMenu.setFont(menuFont);
		editMenu.add(setPlaytime);
		editMenu.addSeparator();
		editMenu.add(setbackground);
		// 新增JMenuBar 並將以上功能添加其中
        JMenuBar menuBar = new JMenuBar();
		menuBar.add(returntitle);
        menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(editMenu);


        parent.designWin.setJMenuBar(menuBar); 
	
		
		// Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Cheak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				page.Cheak();
            }
        });
		CheakAndTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.checkandRun();
            }
        });
		saveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				page.saveFile();
			}
		});
		openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				page.openFile();
			}
		});
		setPlaytime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				page.setPlaytime(parent);
            }
        });
		setbackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				page.setbackground(parent);
            }
        });
	    newPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.addNewTab();
            }
        });
		deletePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.removeCurrentTab();
            }
        });
	    returntitle.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    parent.designWin.dispose();
	    	    new Title();
	        }
	    });

    }
}
