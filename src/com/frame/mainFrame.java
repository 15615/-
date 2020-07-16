package com.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;



import javax.swing.JScrollPane;
import javax.swing.JTextArea;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mainFrame extends JFrame {//JFrame会有最小化、最大化、关闭这三个按钮

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static Criterion_layer dialog;
	public static Decision_layer dialog_2;
	public static Evaluation dialog_3;
	public static Double[] real_weight =new Double[100];
	public static int sum_decision;
	private JTextField textField_3;
	
	
	private JTextField textField4;//统计代码行数
static Map<String,Integer> rmap=new HashMap<>();
	
	static Map<String,List<Javafile>> pkgmap=new HashMap<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {//把这bai个事件（new Runnable(设置计算器可见))添加du到awt的事件处理线程当中去awt的事件处理线程会按照zhi队列的顺序依次调用每个dao待处理的事件来运行 使用该方式的原因是：awt是单线程模式的，所有awt的组件只能在(推荐方式)事件处理线程中访问，从而保证组件状态的可确定性。
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() throws Exception{
		for(int i=0;i<real_weight.length;i++)
			real_weight[i]=(double) 0;
		
		
		setTitle("\u5206\u6790\u5de5\u5177");//分析工具
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));//设置面板的边界bai，Border描述了面板四周的边界du（属于面板内部），EmptyBorder是一个空白的边zhi界；语句的意思是让contentPane内部边框dao为空，并且有5个像素的厚度，如果直接在contentPane上面添加一个按钮（设置为充满），那么按钮将铺满除了边框之外的内部矩形设置面板的边界bai，Border描述了面板四周的边界du（属于面板内部），EmptyBorder是一个空白的边zhi界；语句的意思是让contentPane内部边框dao为空，并且有5个像素的厚度，如果直接在contentPane上面添加一个按钮（设置为充满），那么按钮将铺满除了边框之外的内部矩形
		setContentPane(contentPane);//获得JFrame的内容面板：
		contentPane.setLayout(null);//设置为null即为清空布局管理器，之后添加组件
		
		
		//新的
		
		JLabel lblSrc = new JLabel("src\u6587\u4EF6\u5939");//src文件夹
		lblSrc.setBounds(45, 11, 94, 18);
		contentPane.add(lblSrc);
		
		textField4 = new JTextField();
		textField4.setBounds(153, 11, 132, 18);
		contentPane.add(textField4);
		textField4.setColumns(10);
		
		JButton button1 = new JButton("\u9009\u62E9");//选择
		button1.addMouseListener(new MouseAdapter() {
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
		button1.setBounds(425, 11, 113, 27);
		contentPane.add(button1);
		
		JButton button_5 = new JButton("计算");
		button_5.addMouseListener(new MouseAdapter() {
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
		button_5.setBounds(325, 11, 113, 27);//194, 172, 93, 23
		contentPane.add(button_5);
		this.setLocationRelativeTo(null);
		
		
		
		JLabel lblNewLabel = new JLabel("\u51C6\u5219\u5C42\u8F93\u5165\uFF1A");//准则层输入：
		lblNewLabel.setBounds(45, 70, 94, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u65B9\u6848\u5C42\u8F93\u5165\uFF1A");//方案层输入：
		label.setBounds(45, 116, 94, 18);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(153, 31, 132, 18);//textField容器位置坐标
		contentPane.add(textField);//在容器中加入textField
		textField.setColumns(10);//这是设宽度，每个单位宽度等于当前字体下 'm' 字符的宽度10 就是：10 * 这个单位宽度
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(153, 70, 132, 18);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u76EE\u6807\u5C42\u8F93\u5165\uFF1A");//目标层输入：
		label_1.setBounds(45, 31, 94, 18);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(153, 113, 132, 18);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u51B3\u7B56\u540D\u79F0");//决策名称
		lblNewLabel_1.setBounds(299, 31, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbln = new JLabel("/n\u51C6\u5219\u5C42\u4E2A\u6570");//准则层个数
		lbln.setBounds(299, 70, 93, 18);
		contentPane.add(lbln);
		
		JLabel lbln_1 = new JLabel("/n\u65B9\u6848\u5C42\u4E2A\u6570");//方案层个数
		lbln_1.setBounds(299, 113, 93, 18);
		contentPane.add(lbln_1);
		
		JButton btnNewButton = new JButton("\u5224\u65AD\u77E9\u9635");//判断矩阵
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dialog = new Criterion_layer(Integer.parseInt(textField_1.getText()));//获得文本内容
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
//					for(int i=0;i<dialog.ww.length;i++)
//						System.out.println(dialog.ww[i]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.setBounds(425, 66, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u5224\u65AD\u77E9\u9635");//判断矩阵
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				dialog_2 = new Decision_layer(Integer.parseInt(textField_1.getText()),Integer.parseInt(textField_2.getText()));//同时获得准则层和方案层的数据
//				dialog_2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog_2.setVisible(true);
				dialog_2 = new Decision_layer(Integer.parseInt(textField_1.getText()),textField_2.getText());
				dialog_2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog_2.setVisible(true);
				
			}
		});
		button.setBounds(425, 112, 113, 27);
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("\u8BA1\u7B97");//计算
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				for(int i=0;i<dialog.ww.length;i++)
//				System.out.println(dialog.ww[i]);
				
				String[] str= textField_2.getText().split(" ");//这里将第二个参数设置为-1
				String[] strr= new String[6];
				strr[0]="0" ;
				for (int i = 0; i < str.length; ++i){
					strr[i+1] =String.valueOf(Integer.parseInt(strr[i]) + Integer.parseInt(str[i]));
				}
		        for (int i = 0; i < str.length; ++i){
		        	sum_decision = sum_decision + Integer.parseInt(str[i]);
		        	}

		        for (int le = 0; le < str.length; ++le)
		        {
		        	for(int i = 0;i<Integer.parseInt(str[le]);i++)
					{
//						real_weight[i]=real_weight[i] + dialog_2.zj[j].ww[i] * dialog.ww[j];//完全层次_计算公式
		        		int p = Integer.parseInt(strr[le]);
						real_weight[i+p]= dialog.ww[le]*dialog_2.zj[le].ww[i];//不完全层次_计算公式
					System.out.println("方案层对目标层的组合权向量："+real_weight[i+p]);
					}
		        }
		        

				for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)
				System.out.println("准则层参数："+dialog.ww[j]);
				
//					for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)
//						for(int i = 0;i<sum_decision;i++)
//							System.out.println("决策层参数："+dialog_2.zj[j].ww[i]);
		        for (int le = 0; le < str.length; ++le)
		        {
		        	for(int i = 0;i<Integer.parseInt(str[le]);i++)
					{
						System.out.println("决策层参数："+dialog_2.zj[le].ww[i]);
					}
		        }
//				for(int i = 0;i<Integer.parseInt(textField_2.getText());i++)//方案层
//					{for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)//准则层
//						real_weight[i]=real_weight[i] + dialog_2.zj[j].ww[i] * dialog.ww[j];//准则层的w【i】乘以决策层的w【j】
//					System.out.println("方案层对目标层的组合权向量："+real_weight[i]);
//					}
//
//				for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)
//				System.out.println("参数"+dialog.ww[j]);
//				
//					for(int j = 0;j<Integer.parseInt(textField_1.getText());j++)
//						for(int i = 0;i<Integer.parseInt(textField_2.getText());i++)
//							System.out.println("参数"+dialog_2.zj[j].ww[i]);
//				
				textField_3.setText("计算结果\n");
				textField_3.setText(textField_3.getText()+"最终的组合权向量\n");
//				for(int i = 0;i<Integer.parseInt(textField_2.getText());i++)
//					textField_3.setText(textField_3.getText()+real_weight[i]+"     ");	
//			
				for(int i = 0;i<sum_decision;i++)
					textField_3.setText(textField_3.getText()+real_weight[i]+"     "+"\n");	
				
				
			}
		});
		btnNewButton_1.setBounds(231, 169, 113, 27);
		contentPane.add(btnNewButton_1);
		
//		JLabel lblNewLabel_2 = new JLabel("\u5185\u5B58\u9650\u5236\uFF0C\u65B9\u6848\u5C42\u4EC5\u9650\u4E8E3\u5C42");//内存限制，方案层仅限于3层
//		lblNewLabel_2.setBounds(228, 144, 198, 18);
//		contentPane.add(lblNewLabel_2);
//		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(51, 203, 475, 98);
		contentPane.add(textField_3);
		
		JButton button_1 = new JButton("\u7EFC\u5408\u8BC4\u4EF7");//综合评价
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//dialog_3 = new Evaluation(Integer.parseInt(textField_1.getText()),dialog.ww);
					dialog_3 = new Evaluation(sum_decision,real_weight);
					dialog_3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog_3.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(425, 163, 113, 27);
		contentPane.add(button_1);

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
