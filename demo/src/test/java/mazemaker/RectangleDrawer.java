package mazemaker;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
 
public class RectangleDrawer extends JPanel {
    private Point startPoint; // 拖曳起始點
    private JLabel currentRectLabel; // 用 JLabel 表示當前矩形
    private JLabel trashBin; // 垃圾桶
    private List<JLabel> rectangleLabels = new ArrayList<>(); // 儲存所有矩形
 
    public RectangleDrawer() {
        setLayout(null);
 
        // 初始化垃圾桶
        trashBin = new JLabel("Trash Bin");
        trashBin.setOpaque(true);
        trashBin.setBackground(Color.RED);
        trashBin.setBounds(650, 10, 100, 50); // 放在右上角
        add(trashBin);
 
        // 滑鼠事件偵測
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                // 創建當前矩形 JLabel
                currentRectLabel = new JLabel();
                currentRectLabel.setOpaque(true);
                currentRectLabel.setBackground(Color.BLUE);
                currentRectLabel.setBounds(startPoint.x, startPoint.y, 0, 0);
                add(currentRectLabel);
                repaint();
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentRectLabel != null) {
                    // 儲存矩形
                    rectangleLabels.add(currentRectLabel);
                    // 添加拖曳功能到生成的 JLabel
                    addDragFunctionality(currentRectLabel);
                    currentRectLabel = null; // 清除暫存的矩形
                }
                repaint();
            }
        });
 
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (startPoint != null && currentRectLabel != null) {
                    // 計算矩形大小
                    int width = Math.abs(e.getX() - startPoint.x);
                    int height = Math.abs(e.getY() - startPoint.y);
                    int x = Math.min(startPoint.x, e.getX());
                    int y = Math.min(startPoint.y, e.getY());
                    currentRectLabel.setBounds(x, y, width, height);
                    repaint();
                }
            }
        });
    }
 
    // 添加拖曳功能到 JLabel
    private void addDragFunctionality(JLabel rectLabel) {
        rectLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)); // 切換為移動游標
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor()); // 還原游標
                // 如果矩形拖到垃圾桶，刪除它
                if (trashBin.getBounds().intersects(rectLabel.getBounds())) {
                    remove(rectLabel);
                    rectangleLabels.remove(rectLabel);
                    repaint();
                }
            }
        });
 
        rectLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // 取得滑鼠相對於面板的座標
                Point mousePoint = SwingUtilities.convertPoint(rectLabel, e.getPoint(), RectangleDrawer.this);
               
                // 根據滑鼠的相對位置更新矩形的左上角位置
                rectLabel.setLocation(mousePoint.x - rectLabel.getWidth() / 2, mousePoint.y - rectLabel.getHeight() / 2);
            }
        });
    }
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Rectangle Drawer with Drag-to-Draw and Trash Bin");
        RectangleDrawer drawer = new RectangleDrawer();
        frame.add(drawer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}