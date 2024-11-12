package mazemaker;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class makemap extends JFrame {

	public mazedesignwin designWin;
	public mazedesignmenu dm;
	public Page tabpane;
   	makemap(){
    	tabpane = new Page();
		designWin = new mazedesignwin(this); //創建設計端頁面
		dm = new mazedesignmenu(this); //創建設計端菜單&工具列
		
        designWin.add(tabpane, BorderLayout.CENTER); // 將 TabbedPaneDemo 面板添加到 JFrame

   	}
}