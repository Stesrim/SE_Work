package mazemaker;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
public class Page extends JPanel{
    public static int height;
    public static int width;
    public boolean[] exists = {false, false, false, false, false};
    public JTabbedPane tabbedPane;
    public Page() {
        tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                // 把旁邊的屬性砍掉
                makemap.attributes.removeAll();
            }
        });

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
        width = this.getWidth();
        height = this.getHeight();
        for (int i = 0 ; i < exists.length; i++){
            if (exists[i] == false){
                DrawPanel panel = new DrawPanel();
                // 將新頁面添加到 JTabbedPane
                tabbedPane.addTab("頁面 " + (i +1), panel);
                exists[i] = true;
                System.out.println("i");
                break;
               
            }
        }
    }
    //新增頁面 載入專屬
    public void addNewTab2(DrawPanel panel) {
        width = this.getWidth();
        height = this.getHeight();
        for (int i = 0 ; i < exists.length; i++){
            if (exists[i] == false){
                // 將新頁面添加到 JTabbedPane
                tabbedPane.addTab("頁面 " + (i +1), panel);
                exists[i] = true;
                break;
               
            }
        }
    }
}