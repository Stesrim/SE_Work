package mazemaker;

import javax.swing.*;
import java.awt.event.*;

public class MouseListenerWithJList {
    public static void main(String[] args) {
        // 創建 JFrame 並設置基本屬性
        JFrame frame = new JFrame("JList with MouseListener Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        
        // 創建 JList 並設置資料
        String[] listData = {"Apple", "Banana", "Cherry", "Date", "Elderberry"};
        JList<String> jList = new JList<>(listData);
        
        // 添加 MouseListener 來監聽點擊事件
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 判斷鼠標是否單擊
                if (e.getClickCount() == 1) {
                    // 獲取 JList 被點擊的項目索引
                    int index = jList.locationToIndex(e.getPoint());
                    
                    // 確保點擊的索引有效
                    if (index >= 0) {
                        String selectedItem = jList.getModel().getElementAt(index);
                        // 顯示所選擇的項目
                        JOptionPane.showMessageDialog(frame, "You selected: " + selectedItem);
                    }
                }
            }
        });
        
        // 將 JList 添加到 JScrollPane 並放入 JFrame 中
        JScrollPane scrollPane = new JScrollPane(jList);
        frame.add(scrollPane);
        
        // 顯示視窗
        frame.setVisible(true);
    }
}
