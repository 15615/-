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

public class Decision_layer extends JDialog {//���߲�
	static String[] strArr;
	private final JPanel contentPanel = new JPanel();
	public static zhongjian[] zj=new zhongjian[20];
	/**
	 * Launch the application.����Ӧ�ó���
	 */
	public static void main(String[] args) {
		try {
			String a = "1 3 5";
			Decision_layer dialog = new Decision_layer(5,a);//����5��3����
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);///�����û��ڴ˶Ի������������رա�ʱĬ������½������Ĳ��������ô���Ĭ�ϴ��ڹرղ�����
			//setDefaultCloseOperation�����������趨���ڱ��ر�ʱ�򣨱����������Ͻǵ�"x"������Ϊ�ġ� 
			//DISPOSE_ON_CLOSE�ڴ��ڱ��رյ�ʱ���dispose������ڡ� 
			dialog.setVisible(true);//��ʾ�����ش˶Ի����ֵȡ���ڲ��� .����Ϊ��ʾ
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.�����Ի���
	 */
	public Decision_layer(int n,String m) {
		strArr= m.split(" ");//�ո�Ϊ�ָ��������ｫ�ڶ�����������Ϊ-1
//		final int[] array = new int[20];
		
		setTitle("\u51B3\u7B56\u5C42\u5224\u65AD\u77E9\u9635");//���öԻ���ı��⡣    ���߲��жϾ���
		setBounds(100, 100, 450, 300);//����һ���µľ��Σ������Ͻ�ָ��Ϊ(x,y)�����Ⱥ͸߶���ͬ���Ĳ���ָ��
		getContentPane().add(contentPanel, BorderLayout.CENTER);//�м�
		JPanel shagnceng = new JPanel();//��������
		shagnceng.setLayout(new FlowLayout());//�����Ĳ�������ΪFlowLayout,��������ӵ����Ĭ���ǰ��մ�����˳������
		contentPanel.setLayout(new BorderLayout());//���ò���Ϊ�߿򲼾֣��߿򲼾ֶַ���������5����λ����ӿؼ���
		{
			JLabel lblNewLabel = new JLabel("\u51B3\u7B56\u5C42\u76F8\u5BF9\u4E8E\u51C6\u5219\u5C42\u77E9\u9635\u8F93\u5165");//���߲������׼����������
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ˮƽ���뷽ʽ  ����
			lblNewLabel.setFont(new Font("����", Font.PLAIN, 21));//����
			lblNewLabel.setBounds(76, 13, 285, 40);
			contentPanel.add(lblNewLabel,BorderLayout.NORTH);//lblNewLabel�ڲ��ֵı�����
		
		}
		contentPanel.add(shagnceng,BorderLayout.CENTER);//shagnceng����������contentPanel���������м�λ��
		
		JButton[] buttons = new JButton[n];
		for( int i=0; i<buttons.length; i++)
			buttons[i] = new JButton("����ڵ�"+(i+1)+"׼���" );
		for( int i=0; i<buttons.length; i++)
			shagnceng.add( buttons[i] );//��ʾ��ť������
		
		//��������ڲ�������ⲿ�ı���
		for( int i=0; i<buttons.length; i++) {
			buttons[i].addActionListener(new ActionListener() {//����
			private int ii;//�������ڲ��Ĳ���
			private int a_in;//�������ڲ��Ĳ���
			public void actionPerformed(ActionEvent e) {//actionPerformedΪ�ӿ�ActionListener�ķ���
				try {
				//	zj[ii] = new zhongjian(m,ii);
					zj[ii] = new zhongjian(Integer.parseInt(strArr[ii]),ii);
					zj[ii].setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//����������ע�� WindowListener �Ķ�����Զ����ز��ͷŸô��塣
					zj[ii].setVisible(true);//��ʾ�����ش˶Ի����ֵȡ���ڲ��� .����Ϊ��ʾ
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));//����������õĶ��뷽ʽ��������  �Ҷ���
			getContentPane().add(buttonPane, BorderLayout.SOUTH);//��������Ϊ���������ϡ��������������ÿ������ֻ�ܷ���һ������� ��
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//	for(int i=0;i<n;i++)
						//	for(int j = 0;j<m;j++)
						//	System.out.println("��"+(i+1)+"��"+zj[i].ww[j]);
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
				okButton.setActionCommand("OK");//��ť�Ĳ���ָ��
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);//��������Ĭ�ϵİ�ť���س���ֱ�Ӵ���
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {//����
					public void actionPerformed(ActionEvent e) {////actionPerformedΪ�ӿ�ActionListener�ķ���
						dispose();//�رմ��壬���ͷ���Դ 
					}
				});
				cancelButton.setActionCommand("Cancel");//��ť�Ĳ���ָ��
				buttonPane.add(cancelButton);
			}
		}
	}

}
