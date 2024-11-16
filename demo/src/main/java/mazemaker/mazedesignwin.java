// // package mazemaker;

// // import java.awt.*;
// // import java.awt.event.MouseAdapter;
// // import java.awt.event.MouseEvent;
// // import javax.swing.*;
// // import java.awt.event.ActionListener;
// // import java.awt.event.ActionEvent;


// // public class mazedesignwin extends JFrame {

// //     mazedesignwin(makemap parent)
// // 	{

// // 		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
// // 		setLayout(new BorderLayout());
// // 		this.setVisible(true);
// // 		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// //         ////////////////////////////////////////////////////
// //         // 創建 JToolBar 並設置為垂直
// //         JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        
// //         // 添加一些工具按鈕
// //         toolBar.add(new JButton("障礙物"));
// //         toolBar.add(new JButton("輔助物"));
// //         toolBar.add(new JButton("裝飾物"));

        
// //         // 設置工具條位置
// //         this.add(toolBar, BorderLayout.EAST);
        
// //         // // 設置內容區域
// //         // JTextArea textArea = new JTextArea();
// //         // this.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
// //         // 顯示框架
// //         this.setVisible(true);
		
// // 	}

// // }


// package mazemaker;

// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.*;

// public class mazedesignwin extends JFrame {

//     private JToolBar toolBar;  // 工具條
//     private JPanel contentPanel;  // 內容區域面板，顯示不同的物件
//     private JPanel listPanel;  // 用來顯示 JList 的面板
//     private JButton obstacleButton; // 障礙物按鈕
//     private CardLayout cardLayout;  // 用來切換顯示的布局

//     mazedesignwin(makemap parent) {
//         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//         setLayout(new BorderLayout());
//         this.setVisible(true);
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // 創建工具條並設置為垂直
//         toolBar = new JToolBar(JToolBar.VERTICAL);

//         // 添加工具按鈕
//         obstacleButton = new JButton("障礙物");
//         obstacleButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 showObstacleList();  // 顯示障礙物選項
//             }
//         });
//         toolBar.add(obstacleButton);
        
//         toolBar.add(new JButton("輔助物"));
//         toolBar.add(new JButton("裝飾物"));

//         // 設置工具條的位置
//         this.add(toolBar, BorderLayout.EAST);

//         // 創建卡片布局來管理內容區域
//         cardLayout = new CardLayout();
//         contentPanel = new JPanel(cardLayout);

//         // 初始化 JList 面板
//         listPanel = createObstacleListPanel();

//         // 將 JList 面板放入卡片布局中
//         contentPanel.add(listPanel, "障礙物選項");

//         // 設置內容區域
//         this.add(contentPanel, BorderLayout.CENTER);

//         // 顯示框架
//         this.setVisible(true);
//     }

//     // 創建顯示障礙物選項的 JList 面板
//     private JPanel createObstacleListPanel() {
//         JPanel panel = new JPanel();
//         panel.setLayout(new BorderLayout());

//         // 創建 JList 並添加項目
//         String[] obstacleItems = {"石頭", "樹木", "水域"};
//         JList<String> obstacleList = new JList<>(obstacleItems);

//         // 滾動面板包裝 JList
//         JScrollPane scrollPane = new JScrollPane(obstacleList);

//         // 添加到面板中
//         panel.add(scrollPane, BorderLayout.CENTER);

//         return panel;
//     }

//     // 顯示障礙物選項（JList）
//     private void showObstacleList() {
//         // 切換到 "障礙物選項" 卡片
//         cardLayout.show(contentPanel, "障礙物選項");
//     }

// }

package mazemaker;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class mazedesignwin extends JFrame {

    

    mazedesignwin(makemap parent) {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }
}