package mazemaker;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class mazedesignmenu extends JFrame{
	private Page page;
    mazedesignmenu(makemap parent, Page tabpane){	
        this.page = tabpane;
		
		JButton returntitle = new JButton("重返主頁面");
	    parent.designWin.add(returntitle);
		Font menuFont = new Font("Microsoft JhengHei", Font.BOLD, 26);
		Font itemFont = new Font("Microsoft JhengHei", Font.BOLD, 20);
		Font dialogFont = new Font("Microsoft JhengHei", Font.BOLD, 21);
		JMenu fileMenu = new JMenu("檔案");
		returntitle.setFont(menuFont);

        JMenuItem openMenuItem = new JMenuItem("新增頁面");
		JMenuItem deleteMenuItem = new JMenuItem("刪除頁面");
        JMenuItem saveMenuItem = new JMenuItem("保存");
        JMenuItem exitMenuItem = new JMenuItem("開啟");

		openMenuItem.setFont(itemFont);
		deleteMenuItem.setFont(itemFont);
		saveMenuItem.setFont(itemFont);
		exitMenuItem.setFont(itemFont);

		fileMenu.setFont(menuFont);
        fileMenu.add(openMenuItem);
		fileMenu.addSeparator();
        fileMenu.add(deleteMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        

		JMenu runMenu = new JMenu("執行");
		JMenuItem Comp = new JMenuItem("編譯");
		JMenuItem CompAndRun = new JMenuItem("編譯並執行");

		Comp.setFont(itemFont);
		CompAndRun.setFont(itemFont);

		runMenu.setFont(menuFont);
		runMenu.add(Comp);
		runMenu.addSeparator();
		runMenu.add(CompAndRun);

		JMenu editMenu = new JMenu("編輯");
		JMenuItem settime = new JMenuItem("設定遊玩時間");
		JMenuItem setbackground = new JMenuItem("設定背景圖片");
		JMenuItem setmapsize = new JMenuItem("設地圖大小");
		settime.setFont(itemFont);
		setbackground.setFont(itemFont);
		setmapsize.setFont(itemFont);

		editMenu.setFont(menuFont);
		editMenu.add(settime);
		editMenu.addSeparator();
		editMenu.add(setbackground);
		editMenu.addSeparator();
		editMenu.add(setmapsize);

        JMenuBar menuBar = new JMenuBar();
		menuBar.add(returntitle);
        menuBar.add(fileMenu);
		menuBar.add(runMenu);
		menuBar.add(editMenu);


        parent.designWin.setJMenuBar(menuBar); 
	
		
		// Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int index = page.tabbedPane.getSelectedIndex();
				if (index != -1){
					//抓到放檔案的相對位置
					File defaultDir = new File(System.getProperty("user.dir"), "mazemaker/demo/project");
					JFileChooser fileChooser = new JFileChooser(defaultDir);
					fileChooser.setDialogTitle("選擇保存路徑位置");
					//抓到目前所在的頁面
					DrawPanel Temp = (DrawPanel)page.tabbedPane.getComponentAt(index);
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
		});
		exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int index = page.tabbedPane.getTabCount();
				//只讓玩家開最多五個頁面
				if (index < 4){
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
							page.addNewTab2(loadedPanel);
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
					ErrorJLabel.setText("請先建立一個新的頁面");
					ErrorJLabel.setFont(dialogFont);
					JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		settime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int index = page.tabbedPane.getSelectedIndex();
				if (index != -1){
					//初始化
                	JDialog Tleftinput = new JDialog(parent,"設置遊玩時間", true);
                	Tleftinput.setSize(450, 175);
					Tleftinput.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
					JLabel Pt = new JLabel("請輸入遊玩時間 (s):");
					Pt.setFont(dialogFont);
					//抓DrawPanel裡面的資料
					DrawPanel Temp = (DrawPanel)page.tabbedPane.getComponentAt(index);
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
									JOptionPane.showMessageDialog(parent, "請輸入大於0的整數!", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(parent, "請輸入有效的數字!", "Error", JOptionPane.ERROR_MESSAGE);
							}

						}
					});




					Tleftinput.add(Pt);
					Tleftinput.add(Tleft);
					Tleftinput.add(Save);
					//放到最中間
					Tleftinput.setLocationRelativeTo(parent); 
					Tleftinput.setVisible(true);
				}else{
					JLabel ErrorJLabel = new JLabel();
					ErrorJLabel.setText("請先建立一個新的頁面");
					ErrorJLabel.setFont(dialogFont);
					JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
				}
            }
        });
		setbackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				int index = page.tabbedPane.getSelectedIndex();
				if (index != -1){
					//初始化
                	JDialog Bginput = new JDialog(parent,"設置地圖", true);
                	Bginput.setSize(450, 175);
					Bginput.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
					JLabel Pt = new JLabel("請選擇地圖種類:");
					Pt.setFont(dialogFont);
					//抓DrawPanel裡面的資料
					DrawPanel Temp = (DrawPanel)page.tabbedPane.getComponentAt(index);
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
					Bginput.setLocationRelativeTo(parent); 
					Bginput.setVisible(true);
				}else{
					JLabel ErrorJLabel = new JLabel();
					ErrorJLabel.setText("請先建立一個新的頁面");
					ErrorJLabel.setFont(dialogFont);
					JOptionPane.showMessageDialog(null,ErrorJLabel,"Error",JOptionPane.WARNING_MESSAGE);
				}
            }
        });
	    openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.addNewTab();
            }
        });
		deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.removeCurrentTab();
            }
        });
	    returntitle.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	    	    parent.designWin.dispose();
	    	    new title();
	        }
	    });

    }
}
