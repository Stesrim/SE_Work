package mazemaker;

import javax.swing.*;
import java.awt.*;

public class CardLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CardLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 創建 CardLayout 物件
        CardLayout cardLayout = new CardLayout();
        
        // 創建一個包含多個卡片的容器
        JPanel cardPanel = new JPanel(cardLayout);
        
        // 創建卡片
        JPanel card1 = new JPanel();
        card1.add(new JLabel("這是卡片 1"));
        
        JPanel card2 = new JPanel();
        card2.add(new JLabel("這是卡片 2"));
        
        JPanel card3 = new JPanel();
        card3.add(new JLabel("這是卡片 3"));
        
        // 添加卡片到容器中，指定每個卡片的名稱
        cardPanel.add(card1, "卡片 1");
        cardPanel.add(card2, "卡片 2");
        cardPanel.add(card3, "卡片 3");
        
        // 使用一個按鈕來切換卡片
        JButton nextButton = new JButton("下一個卡片");
        nextButton.addActionListener(e -> cardLayout.next(cardPanel));
        
        // 另一個按鈕用來顯示特定卡片
        JButton showCard2Button = new JButton("顯示卡片 2");
        showCard2Button.addActionListener(e -> cardLayout.show(cardPanel, "卡片 2"));
        
        // 將按鈕添加到框架
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(showCard2Button);
        
        // 將內容面板和按鈕面板添加到框架
        frame.add(cardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // 設置框架顯示
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
