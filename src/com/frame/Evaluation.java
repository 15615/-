package com.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Evaluation extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public Double[] ww;
	static Double score= (double) 0 ;
	/**
	 * Launch the application.启动应用程序。
	 */
	public static void main(String[] args) {
		try {
			Double[] real_weight =new Double[100];
			real_weight[0]=0.2636;
			real_weight[1]=0.4758;
			real_weight[2]=0.0538;
			real_weight[3]=0.0981;
			real_weight[4]=0.1087;
			Evaluation dialog = new Evaluation(5,real_weight);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Evaluation(int n,Double[] x) throws Exception{

        Double[] w=new Double[n];//分数
        
		setTitle("\u51C6\u5219\u5C42\u5224\u65AD\u77E9\u9635");//准则层判断矩阵
		setBounds(100, 100, 450, 300);
		contentPanel.setLayout(new FlowLayout());//设置为网格布局，n行n列，组件水平、垂直间距均为0
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel[] labels = new JLabel[n];
		JPanel[] panels = new JPanel[n];
		JTextField[] textField = new JTextField[n];
		

		for( int i=0; i<panels.length; i++)
			{panels[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());}////设置布局为边框布局，边框布局分东南西北中5个方位来添加控件。
			
//		for( int i=0; i<labels.length; i++)
//			labels[i] = new JLabel( "   指标"+(i+1)+"分值" );
//		for( int i=0; i<panels.length; i++)
//			panels[i].add( labels[i],BorderLayout.NORTH);
//		for( int i=0; i<panels.length; i++)
//			{textField[i] = new JTextField();
//			panels[i].add( textField[i],BorderLayout.CENTER);}
//		
		for( int i=0; i<labels.length; i++)
			labels[i] = new JLabel( "   决策"+(i+1)+"分值" );
		for( int i=0; i<panels.length; i++)
			panels[i].add( labels[i],BorderLayout.NORTH);
		for( int i=0; i<panels.length; i++)
			{textField[i] = new JTextField();
			panels[i].add( textField[i],BorderLayout.CENTER);}
		
		for( int i=0; i<panels.length; i++)
			contentPanel.add( panels[i] );//在总的jpanel中对n的jpanel进行添加
		{
			
			JPanel buttonPane = new JPanel();
			JLabel lblLamda = new JLabel("综合评价分数=                        ");
			buttonPane.add(lblLamda);
		
				
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						score= (double) 0 ;

						for( int i=0; i<n; i++)
						{	
								w[i] = Double.parseDouble(textField[i].getText());
						}
						
						for( int i=0; i<n; i++)
						{
							score =score+w[i]*x[i];//w[i](输入)乘以x[i]
						}
						BigDecimal b = new BigDecimal(score);  
						double score_4 = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
						lblLamda.setText("综合评价分数="+score_4+"                  ");
						
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		
	}
	public Double[] jisuan() {
		
		return ww;
		
	}
}
