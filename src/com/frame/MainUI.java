package com.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField4;

	static Map<String,Integer> rmap=new HashMap<>();
	
	static Map<String,List<Javafile>> pkgmap=new HashMap<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//)EXIT_ON_CLOSE（在JFrame中定义）：使用System exit方法退出应用程序。仅在应用程序中使用。  
		setBounds(100, 100, 604, 283);//窗口大小
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSrc = new JLabel("src\u6587\u4EF6\u5939");//src文件夹
		lblSrc.setBounds(69, 101, 94, 35);
		contentPane.add(lblSrc);

		textField4 = new JTextField();
		textField4.setBounds(194, 101, 167, 35);
		contentPane.add(textField4);
		textField4.setColumns(10);

		JButton button = new JButton("\u9009\u62E9");//选择
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//以允许用户只选择文件、只选择目录，或者可选择文件和目录
				jfc.showDialog(new JLabel(), "选择");
				File file = jfc.getSelectedFile();//返回选中的文件
				if (file.isDirectory()) {
					textField4.setText(file.getAbsolutePath());//返回此抽象路径名的绝对路径名字符串。
				}
			}
		});
		button.setBounds(405, 107, 93, 23);
		contentPane.add(button);

		JLabel lblNewLabel = new JLabel("\u5C42\u6B21\u5206\u6790\u5DE5\u5177");//层次分析工具
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 19));
		lblNewLabel.setBounds(194, 37, 175, 35);
		contentPane.add(lblNewLabel);

		JButton button_1 = new JButton("计算");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parsejava(textField4.getText());
				
				StringBuffer sbf=new StringBuffer();
				for(String str:pkgmap.keySet()){//获取pkgmap全部的key值
					if(pkgmap.get(str).size()>0){
						sbf.append("�?:"+str+"分析").append("============\r\n");
						int abscount=0;
						int entitycount=0;
						int impcount=0;
						List<Javafile> jlist=pkgmap.get(str);
						for (int i = 0; i < jlist.size(); i++) {
							Javafile jf=jlist.get(i);
							sbf.append(jf.getClazzname()+"类共:"+jf.getLinecount()).append("行\r\n");
							if(jf.isIfabs()){
								abscount++;
							}else{
								entitycount++;
							}
							impcount+=jf.getImportcount();
						}
						sbf.append("抽象�?:"+abscount/Double.valueOf(abscount+entitycount)).append("\r\n");
						if(rmap.get(str)==null)rmap.put(str, 0);
						sbf.append("稳定程度:"+impcount/Double.valueOf(impcount+rmap.get(str))).append("\r\n");
					}
				}
				new ViewUI(sbf.toString()).setVisible(true);
			}
		});
		button_1.setBounds(194, 172, 93, 23);
		contentPane.add(button_1);
		this.setLocationRelativeTo(null);
	}

	public static void parsejava(String path) {
		File file = new File(path);
		if (file.exists()) {//文件是否存在
			File[] files = file.listFiles();//listFiles()方法是返回某个目录下所有文件和目录的绝对路径，返回的是File数组
			if (null != files) {
				for (File file2 : files) {
					if (file2.isDirectory()) {//检查一个对象是否是文件夹
						pkgmap.put(file2.getAbsolutePath().substring(file2.getAbsolutePath().indexOf("src")+4).replace("\\", "."), new ArrayList<Javafile>());
						//indexOf("src")+4在src出现的位置+4;indexOf() 方法可返回某个指定的字符串值在字符串中首次出现的位置。
						//substring() 方法用于提取字符串中介于两个指定下标之间的字符。
						//replace() 方法用于在字符串中用一些字符替换另一些字符，或替换一个与正则表达式匹配的子串。将“\\”换成“。”
						parsejava(file2.getAbsolutePath());
					} else {
						if (file2.getName().contains(".java")) {//contains当且仅当此字符串包含指定的char值序列时，返回true。
							Javafile javafile = Util.readJavafile(file2.getAbsolutePath(),rmap);
							if(javafile.getPkgname()!=null&&pkgmap.get(javafile.getPkgname())!=null){
								pkgmap.get(javafile.getPkgname()).add(javafile);
							}
							
						}
					}
				}
			}
		} else {
			System.out.println("文件不存�?!");
		}
	}
}
