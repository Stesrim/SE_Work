package mazemaker;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
 
public class WorkSpace extends JPanel{
    public static int height;
    public static int width;
    public boolean[] pageNumber = {false, false, false, false, false};
    public JTabbedPane pageArray;
    public WorkSpace() {
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
        Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
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
                            ErrorJLabel.setFont(dialogFont);
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

    public void Cheak()
    {
        Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
        int pageNum = pageArray.getSelectedIndex();
            if (pageNum >= 0){
                //抓DrawPanel裡面的資料
                DrawPanel Temp = (DrawPanel)pageArray.getComponentAt(pageNum);
                if (Temp.CheckCollison() == true){
                    JLabel PassJLabel = new JLabel();
                    PassJLabel.setText("檢查通過");
                    PassJLabel.setFont(dialogFont);
                    JOptionPane.showMessageDialog(null,PassJLabel);
                }else{
                    JLabel ErrorJLabel = new JLabel();
                    ErrorJLabel.setText("檢查不通過，請再檢查一下");
                    ErrorJLabel.setFont(dialogFont);
                    JOptionPane.showMessageDialog(null,ErrorJLabel,"Fail",JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JLabel ErrorJLabel = new JLabel();
                ErrorJLabel.setText("請先建立一個新的頁面");
                ErrorJLabel.setFont(dialogFont);
                JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
            }
    }

    public void saveFile()
    {
        Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
        int pageNum = pageArray.getSelectedIndex();
            if (pageNum >= 0){
                //抓到放檔案的相對位置
                File defaultDir = new File(System.getProperty("user.dir"), "mazemaker/demo/project");
                JFileChooser fileChooser = new JFileChooser(defaultDir);
                fileChooser.setDialogTitle("選擇保存路徑位置");
                //抓到目前所在的頁面
                DrawPanel Temp = (DrawPanel)pageArray.getComponentAt(pageNum);
                if (Temp.activeORectangle != null)
                {
                    if (Temp.activeORectangle.status == State.active) {
                        Temp.activeORectangle.status = State.inactive;
                        Temp.activeORectangle.closeControlPoint();
                        Temp.activeORectangle = null;
                        Makemap.attributes.removeAll();
                        Temp.validate();
                        Temp.repaint();

                    }
                }
                if (Temp.activePRectangle != null)
                {
                    if (Temp.activePRectangle.status == State.active) {
                        Temp.activePRectangle.status = State.inactive;
                        Temp.activePRectangle.closeControlPoint();
                        Temp.activePRectangle = null;
                        Makemap.attributes.removeAll();
                        Temp.validate();
                        Temp.repaint();

                    }
                }
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(fileChooser.getSelectedFile()))) {
                        oos.writeObject(Temp);
                        JOptionPane.showMessageDialog(null, "保存成功！");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "保存失敗:" + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }else{
                JLabel ErrorJLabel = new JLabel();
                ErrorJLabel.setText("請先建立一個新的頁面");
                ErrorJLabel.setFont(dialogFont);
                JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
            }
    }

    public void openFile()
    {
        Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
        int pageNum = pageArray.getTabCount();
        //只讓玩家開最多五個頁面
        if (pageNum <= 4){
            //抓到放檔案的相對位置
            File defaultDir = new File(System.getProperty("user.dir"), "mazemaker/demo/project");
            JFileChooser fileChooser = new JFileChooser(defaultDir);
            fileChooser.setDialogTitle("選擇載入路徑位置");

            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fileChooser.getSelectedFile()))) {
                    DrawPanel loadedPanel = (DrawPanel) ois.readObject();
                    //把監聽器載入回來
                    addNewTab2(loadedPanel);
                    loadedPanel.restoreListeners();
                    JOptionPane.showMessageDialog(null, "載入成功！");

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "載入失敗:" + ex.getMessage());
                    ex.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }else{
            JLabel ErrorJLabel = new JLabel();
            ErrorJLabel.setText("頁面過多無法載入");
            ErrorJLabel.setFont(dialogFont);
            JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setPlaytime(Makemap p)
    {
        Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
        int pageNum = pageArray.getSelectedIndex();
        if (pageNum >= 0){
            //初始化
            JDialog Tleftinput = new JDialog(p,"設置遊玩時間", true);
            Tleftinput.setSize(450, 175);
            Tleftinput.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
            JLabel Pt = new JLabel("請輸入遊玩時間 (s):");
            Pt.setFont(dialogFont);
            //抓DrawPanel裡面的資料
            DrawPanel Temp = (DrawPanel)pageArray.getComponentAt(pageNum);
            JTextField Tleft = new JTextField(5);
            Tleft.setText(String.valueOf(Temp.getTimeLeft()));
            Tleft.setFont(dialogFont);
            JButton Save = new JButton("儲存");
            Save.setFont(dialogFont);
            


            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int newTleft = Integer.parseInt(Tleft.getText());
                        // 更新DrawPanel.TimeLeft
                        if ( newTleft >0 ) {
                            Temp.setTimeLeft(newTleft);
                            Tleftinput.dispose();				
                        }else{
                            JOptionPane.showMessageDialog(p, "請輸入大於0的整數!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(p, "請輸入有效的數字!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });




            Tleftinput.add(Pt);
            Tleftinput.add(Tleft);
            Tleftinput.add(Save);
            //放到最中間
            Tleftinput.setLocationRelativeTo(p); 
            Tleftinput.setVisible(true);
        }else{
            JLabel ErrorJLabel = new JLabel();
            ErrorJLabel.setText("請先建立一個新的頁面");
            ErrorJLabel.setFont(dialogFont);
            JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setbackground(Makemap p)
    {
        Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
        int pageNum = pageArray.getSelectedIndex();
        if (pageNum >= 0){
            //初始化
            JDialog Bginput = new JDialog(p,"設置地圖", true);
            Bginput.setSize(450, 175);
            Bginput.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
            JLabel Pt = new JLabel("請選擇地圖種類:");
            Pt.setFont(dialogFont);
            //抓DrawPanel裡面的資料
            DrawPanel Temp = (DrawPanel)pageArray.getComponentAt(pageNum);
            JButton Save = new JButton("儲存");
            Save.setFont(dialogFont);
            String[] options = {"預設(無)", "草地", "雪地", "沙漠"};
            JComboBox<String> comboBox = new JComboBox<>(options);
            comboBox.setSelectedIndex(Temp.getBg());
            comboBox.setFont(dialogFont);
            Save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int newBg = comboBox.getSelectedIndex();
                    Temp.setBg(newBg);
                    Bginput.dispose();
                }
            });




            Bginput.add(Pt);
            Bginput.add(comboBox);
            Bginput.add(Save);
            //放到最中間
            Bginput.setLocationRelativeTo(p); 
            Bginput.setVisible(true);
        }else{
            JLabel ErrorJLabel = new JLabel();
            ErrorJLabel.setText("請先建立一個新的頁面");
            ErrorJLabel.setFont(dialogFont);
            JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
        }
    }
}



