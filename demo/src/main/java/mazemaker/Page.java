package mazemaker;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
public class Page extends JPanel{
    public static int height;
    public static int width;
    public boolean[] pageNumber = {false, false, false, false, false};
    public JTabbedPane pageArray;
    public Page() {
        pageArray = new JTabbedPane();
        pageArray.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                // 把旁邊的屬性砍掉
                Makemap.attributes.removeAll();
            }
        });

        setLayout(new BorderLayout());
        add(pageArray, BorderLayout.CENTER);
        
    }
    //刪除頁面
    public void removeCurrentTab() {
    int pageNum = pageArray.getSelectedIndex();
        if (pageNum >= 0) {
            // 抓取目前頁面的名稱
            String title = pageArray.getTitleAt(pageNum);
            // \\D為所有非數字 取得該頁數的數字
            String numberPart = title.replaceAll("\\D+", "");
            pageNumber[Integer.parseInt(numberPart) - 1] = false;
            pageArray.removeTabAt(pageNum);
        }
    }
    //新增頁面
    public void addNewTab() {
        width = this.getWidth();
        height = this.getHeight();
        for (int i = 0 ; i < pageNumber.length; i++){
            if (pageNumber[i] == false){
                DrawPanel panel = new DrawPanel();
                // 將新頁面添加到 JTabbedPane
                pageArray.addTab("頁面 " + (i +1), panel);
                pageNumber[i] = true;
                System.out.println("i");
                break;
               
            }
        }
    }
    //新增頁面 載入專屬
    public void addNewTab2(DrawPanel panel) {
        width = this.getWidth();
        height = this.getHeight();
        for (int i = 0 ; i < pageNumber.length; i++){
            if (pageNumber[i] == false){
                // 將新頁面添加到 JTabbedPane
                pageArray.addTab("頁面 " + (i +1), panel);
                pageNumber[i] = true;
                break;
               
            }
        }
    }

    public void checkandRun(){
        int pageNum = pageArray.getSelectedIndex();
                if (pageNum >= 0){
                    //shallow copy temp
                    DrawPanel ScTemp = (DrawPanel)pageArray.getComponentAt(pageNum);
                    try {
                        // 用 ByteArrayOutputStream 創建一個內存流
                        ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
           
                        // 用 ObjectOutputStream 來序列化物件到這個流
                        ObjectOutputStream oos = new ObjectOutputStream(BAOS);
                        oos.writeObject(ScTemp);
                        oos.flush();  // 確保寫入所有數據
           
                        // 用 ByteArrayInputStream 來讀取這些字節數據
                        ByteArrayInputStream BAIS = new ByteArrayInputStream(BAOS.toByteArray());
           
                        // 用 ObjectInputStream 來反序列化數據，並返回新物件
                        ObjectInputStream ois = new ObjectInputStream(BAIS);
                        //這個物件是deepcopy的不會影響到原本的DrawPanel
                        DrawPanel Temp = (DrawPanel)ois.readObject();
                        if (Temp.CheckCollison() == true){
                            GameMap window = new GameMap();
                            window.setMazesize(Temp.width, Temp.height);
                            window.setBG(Temp.getBg());
                            window.setTimeleft(Temp.getTimeLeft());
                            for (Obstables obstable:Temp.Orectangles) {
                                window.addObstable(obstable, obstable.getType());
                            }
                            for (Portals portal:Temp.Prectangles) {
                                window.addPortal(portal);
                            }
 
                        }else{
                            JLabel ErrorJLabel = new JLabel();
                            ErrorJLabel.setText("檢查不通過，請再檢查一下");
                            // ErrorJLabel.setFont(dialogFont);
                            JOptionPane.showMessageDialog(null,ErrorJLabel,"Fail",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException ex ) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                   
                }else{
                    JLabel ErrorJLabel = new JLabel();
                    ErrorJLabel.setText("請先建立一個新的頁面");
                    // ErrorJLabel.setFont(dialogFont);
                    JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
                }
    }
}

