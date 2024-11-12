package mazemaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Page extends JPanel {  // 改成繼承 JPanel
    private JTabbedPane tabbedPane;
    private int tabCount = 1; // 用來計數新增的頁面數量
 
    public Page() {
        // 初始化 JTabbedPane
        tabbedPane = new JTabbedPane();
 
        // 建立 "新增頁面" 按鈕
        JButton addButton = new JButton("新增頁面");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewTab();
            }
        });
 
        // 設定面板佈局並添加元件
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }
 
    // 方法：新增頁面
    private void addNewTab() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("這是頁面 " + tabCount);
        JButton deleteButton = new JButton("刪除頁面");
 
        // 設定刪除按鈕的事件處理
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tabbedPane.indexOfComponent(panel);
                if (index >= 0) {
                    tabbedPane.removeTabAt(index);
                }
            }
        });
 
        // 添加元件到頁面
        panel.add(label, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.SOUTH);
 
        // 將新頁面添加到 JTabbedPane
        tabbedPane.addTab("頁面 " + tabCount, panel);
        tabCount++; // 更新頁面計數
    }
}