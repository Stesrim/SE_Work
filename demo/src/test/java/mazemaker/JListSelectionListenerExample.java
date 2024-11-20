package mazemaker;

import javax.swing.*;
import javax.swing.event.*;

public class JListSelectionListenerExample {
    public static void main(String[] args) {
        // 创建JList的数据模型
        String[] data = {"Item 1", "Item 2", "Item 3", "Item 4"};
        JList<String> list = new JList<>(data);
        
        // 为JList添加ListSelectionListener
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // 只有在用户完成选择时才触发
                if (!e.getValueIsAdjusting()) {
                    int index = list.getSelectedIndex();
                    if (index != -1) {
                        System.out.println("选中了项：" + data[index]);
                    }
                }
            }
        });
        
        // 将JList放入滚动面板中显示
        JScrollPane scrollPane = new JScrollPane(list);
        JFrame frame = new JFrame("JList Selection Listener Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
