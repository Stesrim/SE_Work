package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
 
public class Page extends JPanel{  
    private boolean[] exists = {false, false, false, false, false};
    private JTabbedPane tabbedPane;
 
    public Page() {
        tabbedPane = new JTabbedPane();
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }
    //刪除頁面
    public void removeCurrentTab() {
        int index = tabbedPane.getSelectedIndex();
        if (index >= 0) {
            // 抓取目前頁面的名稱
            String title = tabbedPane.getTitleAt(index);
            // \\D為所有非數字 取得該頁數的數字
            String numberPart = title.replaceAll("\\D+", "");
            exists[Integer.parseInt(numberPart) - 1] = false;
            tabbedPane.removeTabAt(index);
        }
    }
    //新增頁面
    public void addNewTab() {
        for (int i = 0 ; i <= exists.length; i++){
            if (exists[i] == false){
                JPanel panel = new JPanel(new BorderLayout());
                JLabel label = new JLabel("這是頁面 " + (i +1)); 
                // 添加元件到頁面
                panel.add(label, BorderLayout.CENTER);
                // 將新頁面添加到 JTabbedPane
                tabbedPane.addTab("頁面 " + (i +1), panel);
                exists[i] = true;
                break;
               
            }
        }
    }
}