package mazemaker;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

public class toolbar extends JPanel {
    private JToolBar toolBar;  // 工具條
    private JPanel contentPanel;  // 內容區域面板，顯示不同的物件
    private JPanel toolPanel;  // 用來顯示 JList 的面板
    private JButton obstacleButton; // 障礙物按鈕
    private JButton supportbtn;
    private CardLayout cardLayout;  // 用來切換顯示的布局

    toolbar(makemap parent) {
        // 創建工具條並設置為垂直
        toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);
        // 添加工具按鈕
        obstacleButton = new JButton("障礙物");
        obstacleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(createObstacleListPanel(), "障礙物選項");
                contentPanel.add(toolBar, BorderLayout.EAST);
                contentPanel.revalidate();
                contentPanel.repaint();
                showObstacleList();  // 顯示障礙物選項

                
            }
        });
        toolBar.add(obstacleButton);
        supportbtn = new JButton("輔助物");
        supportbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(createSupportListPanel(), "輔助物選項");
                contentPanel.add(toolBar, BorderLayout.EAST);
                contentPanel.revalidate();
                contentPanel.repaint();
                showSupportList();  
            }
        });
        toolBar.add(supportbtn);
        toolBar.add(new JButton("裝飾物"));

        // 設置工具條的位置

        // 創建卡片布局來管理內容區域
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        // 初始化 JList 面板
        
        // toolPanel = new JPanel();
        // toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
        // toolPanel.add(toolBar,BorderLayout.NORTH);
        // 將 JList 面板放入卡片布局中
        contentPanel.add(createObstacleListPanel(), "障礙物選項");
        toolBar.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPanel.add(toolBar, BorderLayout.NORTH);
        // 設置內容區域
        parent.designWin.add(contentPanel, BorderLayout.EAST);

        
        

        

        // 顯示框架
        this.setVisible(true);
    }

    // 創建顯示障礙物選項的 JList 面板
    private JPanel createObstacleListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 創建 JList 並添加項目
        String[] obstacleItems = {"石頭", "樹木", "水域"};
        JList<String> obstacleList = new JList<>(obstacleItems);

        // 設置 JList 單元格高度，縮小每個項目的高度
        obstacleList.setFixedCellHeight(25);  // 設置每個單元格的高度為 25 像素

        // 設置 JList 的寬度（如果需要）
        obstacleList.setFixedCellWidth(150);  // 設置每個單元格的寬度為 150 像素

        // 設置 JList 的選擇模式（例如單選模式）
        obstacleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 包裹 JList 的滾動面板
        JScrollPane scrollPane = new JScrollPane(obstacleList);

        // 限制顯示區域的大小，避免顯示過多項目
        scrollPane.setPreferredSize(new Dimension(160, 150));  // 設置滾動區域的大小

        // 添加到面板中
        panel.add(scrollPane, BorderLayout.WEST);
        

        return panel;
    }

    private JPanel createSupportListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 創建 JList 並添加項目
        String[] obstacleItems = {"終點", "傳送門"};
        JList<String> obstacleList = new JList<>(obstacleItems);

        // 設置 JList 單元格高度，縮小每個項目的高度
        obstacleList.setFixedCellHeight(25);  // 設置每個單元格的高度為 25 像素

        // 設置 JList 的寬度（如果需要）
        obstacleList.setFixedCellWidth(150);  // 設置每個單元格的寬度為 150 像素

        // 設置 JList 的選擇模式（例如單選模式）
        obstacleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 包裹 JList 的滾動面板
        JScrollPane scrollPane = new JScrollPane(obstacleList);

        // 限制顯示區域的大小，避免顯示過多項目
        scrollPane.setPreferredSize(new Dimension(160, 150));  // 設置滾動區域的大小

        // 添加到面板中
        panel.add(scrollPane, BorderLayout.WEST);
        

        return panel;
    }

    // 顯示障礙物選項（JList）
    private void showObstacleList() {
        // 切換到 "障礙物選項" 卡片
        cardLayout.show(contentPanel, "障礙物選項");
    }
    private void showSupportList() {
        // 切換到 "障礙物選項" 卡片
        cardLayout.show(contentPanel, "輔助物選項");
    }
}