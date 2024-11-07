package mazemaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarWithMenuExample {

    public static void main(String[] args) {
        // 創建主框架
        JFrame frame = new JFrame("工具列與菜單範例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 創建工具列
        JToolBar toolBar = new JToolBar();
        
        // 創建按鈕並加入工具列
        JButton openButton = new JButton("開啟");
        JButton saveButton = new JButton("保存");
        JButton printButton = new JButton("打印");
        toolBar.add(openButton);
        toolBar.add(saveButton);
        toolBar.addSeparator(); // 添加分隔符
        toolBar.add(printButton);

        // 創建菜單
        JMenu fileMenu = new JMenu("文件");
        JMenuItem openMenuItem = new JMenuItem("開啟");
        JMenuItem saveMenuItem = new JMenuItem("保存");
        JMenuItem exitMenuItem = new JMenuItem("退出");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // 創建菜單欄
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);

        // 添加工具列與菜單欄
        frame.setJMenuBar(menuBar); // 添加菜單欄
        toolBar.setFloatable(true);  // 使工具列可移動
        frame.add(toolBar, BorderLayout.NORTH);  // 添加工具列到視窗

        // 事件處理
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("開啟文件");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("保存文件");
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("打印文件");
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 退出程序
            }
        });

        // 顯示框架
        frame.setVisible(true);
    }
}
