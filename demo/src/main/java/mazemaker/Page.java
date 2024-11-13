package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
 
public class Page extends JPanel{  
    private JTabbedPane tabbedPane;
    private int tabCount = 1;
 
    public Page() {
        // 初始化 JTabbedPane
        tabbedPane = new JTabbedPane(); 
        // 設定面板佈局並添加元件
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }
    //刪除頁面
    public void removeCurrentTab() {
        int index = tabbedPane.getSelectedIndex();
        if (index >= 0) {
            tabbedPane.removeTabAt(index);
        }
    }
    //新增頁面
    public void addNewTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("這是頁面 " + tabCount); 
        // 添加元件到頁面
        panel.add(label, BorderLayout.CENTER);
        // 將新頁面添加到 JTabbedPane
        tabbedPane.addTab("頁面 " + tabCount, panel);
        tabCount++; // 更新頁面計數
    }
}