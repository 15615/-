package com.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;

public class Decision_layer extends JDialog {//决策层
	static String[] strArr;
	private final JPanel contentPanel = new JPanel();
	public static zhongjian[] zj=new zhongjian[20];
	/**
	 * Launch the application.启动应用程序。
	 */
	public static void main(String[] args) {
		try {
			String a = "1 3 5";
			Decision_layer dialog = new Decision_layer(5,a);//界面5个3数组
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);///设置用户在此对话框上启动“关闭”时默认情况下将发生的操作。处置窗口默认窗口关闭操作。
			//setDefaultCloseOperation（）是用来设定窗口被关闭时候（比如点击了右上角的"x"）的行为的。 
			//DISPOSE_ON_CLOSE在窗口被关闭的时候会dispose这个窗口。 
			dialog.setVisible(true);//显示或隐藏此对话框的值取决于参数 .真正为显示
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.创建对话框。
	 */
	public Decision_layer(int n,String m) {
		strArr= m.split(" ");//空格为分隔符，这里将第二个参数设置为-1
//		final int[] array = new int[20];
		
		setTitle("\u51B3\u7B56\u5C42\u5224\u65AD\u77E9\u9635");//设置对话框的标题。    决策层判断矩阵
		setBounds(100, 100, 450, 300);//构造一个新的矩形，其左上角指定为(x,y)，其宽度和高度由同名的参数指定
		getContentPane().add(contentPanel, BorderLayout.CENTER);//中间
		JPanel shagnceng = new JPanel();//创建容器
		shagnceng.setLayout(new FlowLayout());//将面板的布局设置为FlowLayout,面板上增加的组件默认是按照从左到右顺序排列
		contentPanel.setLayout(new BorderLayout());//设置布局为边框布局，边框布局分东南西北中5个方位来添加控件。
		{
			JLabel lblNewLabel = new JLabel("\u51B3\u7B56\u5C42\u76F8\u5BF9\u4E8E\u51C6\u5219\u5C42\u77E9\u9635\u8F93\u5165");//决策层相对于准则层矩阵输入
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);//设置水平对齐方式  居中
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 21));//字体
			lblNewLabel.setBounds(76, 13, 285, 40);
			contentPanel.add(lblNewLabel,BorderLayout.NORTH);//lblNewLabel在布局的北方向
		
		}
		contentPanel.add(shagnceng,BorderLayout.CENTER);//shagnceng容器在整个contentPanel容器中是中间位置
		
		JButton[] buttons = new JButton[n];
		for( int i=0; i<buttons.length; i++)
			buttons[i] = new JButton("相对于第"+(i+1)+"准则层" );
		for( int i=0; i<buttons.length; i++)
			shagnceng.add( buttons[i] );//显示按钮的名称
		
		//解决匿名内部类访问外部的变量
		for( int i=0; i<buttons.length; i++) {
			buttons[i].addActionListener(new ActionListener() {//监听
			private int ii;//匿名类内部的参数
			private int a_in;//匿名类内部的参数
			public void actionPerformed(ActionEvent e) {//actionPerformed为接口ActionListener的方法
				try {
				//	zj[ii] = new zhongjian(m,ii);
					zj[ii] = new zhongjian(Integer.parseInt(strArr[ii]),ii);
					zj[ii].setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//调用任意已注册 WindowListener 的对象后自动隐藏并释放该窗体。
					zj[ii].setVisible(true);//显示或隐藏此对话框的值取决于参数 .真正为显示
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			public ActionListener accept(int str) {
                this.ii = str;
                return this;
            }
			
			}.accept(i));
		}
//		buttons[1].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				zhongjian zj_1;
//				try {
//					zj_1 = new zhongjian(3);
//					zj_1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					zj_1.setVisible(true);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				}
//			});
//		buttons[2].addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				zhongjian zj_1;
//				try {
//					zj_1 = new zhongjian(3);
//					zj_1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					zj_1.setVisible(true);
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				}
//			});
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));//组件按照设置的对齐方式进行排列  右对齐
			getContentPane().add(buttonPane, BorderLayout.SOUTH);//容器划分为东、西、南、北、中五个区域，每个区域只能放置一个组件。 南
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//	for(int i=0;i<n;i++)
						//	for(int j = 0;j<m;j++)
						//	System.out.println("第"+(i+1)+"层"+zj[i].ww[j]);
					}
				});
				{
					JButton btnNewButton = new JButton("New button");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});

					buttonPane.add(btnNewButton);
				}
				okButton.setActionCommand("OK");//按钮的操作指令
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);//用来设置默认的按钮，回车后直接触发
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {//监听
					public void actionPerformed(ActionEvent e) {////actionPerformed为接口ActionListener的方法
						dispose();//关闭窗体，关释放资源 
					}
				});
				cancelButton.setActionCommand("Cancel");//按钮的操作指令
				buttonPane.add(cancelButton);
			}
		}
	}

}
