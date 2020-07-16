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

public class zhongjian extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public Double[] ww;
	/**
	 * Launch the application.启动应用程序。
	 */
	public static void main(String[] args) {
		try {
			//int n=Integer.parseInt(textField.getText());
			zhongjian dialog = new zhongjian(2,2);//3矩阵
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
	public zhongjian(int n,int ii) throws Exception{
		Double[][] matrix=new Double[n][n];
		Double[] column=new Double[n];
		Double[][] matrixColumn= new Double[n][n];
		Double[] line=new Double[n];
        Double[] w=new Double[n];//特征向量
        Double[] bw=new Double[n];
        Double[] lamda = new Double[3]; //防止报错  匿名内部类和局部内部类只能引用外部的fianl变量
        //分别存储    最大特征值、一致性指标、一致性比率
        double RI[]={0,0,0.52,0.89,1.12,1.26,1.36,1.41,1.46,1.49,1.52,1.54,1.56,1.58,1.59};
        
        
        for(int i=0;i<n;i++){
            matrix[i][i]=1.0;
        }
		
		setTitle("第"+(ii+1)+"准则层判断矩阵");
		setBounds(100, 100, 450, 300);
		contentPanel.setLayout(new GridLayout(n,n, 0, 0));//设置为网格布局，n行n列，组件水平、垂直间距均为0
		getContentPane().add(contentPanel, BorderLayout.CENTER );//中间
		
		JLabel[] labels = new JLabel[n*n];
		JPanel[] panels = new JPanel[n*n];
		JTextField[] textField = new JTextField[n*n];
		

		for( int i=0; i<panels.length; i++)
			{panels[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());}//设置布局为边框布局，边框布局分东南西北中5个方位来添加控件。
			
		for( int i=0; i<labels.length; i++)
			labels[i] = new JLabel( "   a"+(i/n+1)+(i%n+1) );
		for( int i=0; i<panels.length; i++)
			panels[i].add( labels[i],BorderLayout.NORTH);//a数组在北边
		for( int i=0; i<panels.length; i++)
			{textField[i] = new JTextField();
			panels[i].add( textField[i],BorderLayout.CENTER);}//文本在中间
		
		for( int i=0; i<panels.length; i++)
			contentPanel.add( panels[i] );//在总的jpanel中对nxn的jpanel进行添加
		{
			
			JPanel buttonPane = new JPanel();
			
				JLabel lblLamda = new JLabel("lamda=           ");
				buttonPane.add(lblLamda);
			
			
				JLabel lblNewLabel = new JLabel("CR=              ");
				buttonPane.add(lblNewLabel);
				
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
;
						Double[][] matrix=new Double[n][n];
						Double[] column=new Double[n];
						Double[][] matrixColumn= new Double[n][n];
						Double[] line=new Double[n];
				        Double[] w=new Double[n];//特征向量
				        Double[] bw=new Double[n];
				        Double[] lamda = new Double[13];
						Double sum=0.0;
						Double  sumR=0.0;

						for( int i=0; i<n*n; i++)
						{	
							try {
								Object tem = engine.eval(textField[i].getText());
								String tem_String = String.valueOf(tem);
								matrix[i/n][i%n]=Double.valueOf(tem_String);
							} catch (ScriptException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						System.out.print(n+"阶矩阵");
						System.out.print("\n");
						for(int i=0;i<n;i++) {
							int sum1=0;
							sum1=sum1+i-1;
						
							for(int j=0;j<n;j++) {
							
								System.out.print(matrix[i][j]+"    ");//返回输入矩阵值
							}
						if(sum1 != i) {
							System.out.print("\n");
							}
							}
						
						//列求和
				        for(int j=0;j<n;j++){
				            for(int i=0;i<n;i++){
				                if(column[j]!=null){
				                    column[j]=column[j]+matrix[i][j];
				                }else{
				                    column[j]=matrix[i][j];
				                }
				            }
				            
				        }
						//矩阵列归一化
				        for(int j=0;j<n;j++){
				            for(int i=0;i<n;i++){
				                matrixColumn[i][j]=matrix[i][j]/column[j];
				            }
				        }
				        
				      //获得行数组（行求和）
				        for(int i=0;i<n;i++){
				            for(int j=0;j<n;j++){
				                if(line[i]!=null){
				                    line[i]=line[i]+matrixColumn[i][j];
				                }else{
				                    line[i]=matrixColumn[i][j];
				                }
				            }
				        }
				        
				        //行归一化获得特征向量

				        
				        for(int i=0;i<n;i++){
				            sum=sum+line[i];
				        }
				        for(int i=0;i<n;i++){
				            w[i]=line[i]/sum;                    //特征向量
				        }

				        for(int i=0;i<n;i++){
				            for(int j=0;j<n;j++){
				                if(bw[i]!=null){
				                    bw[i]=bw[i]+matrix[i][j]*w[j];
				                }else{
				                    bw[i]=matrix[i][j]*w[j];
				                }    
				            }
				        }
				        
				                                //最大特征跟R
				        for(int i=0;i<n;i++){
				            sumR = sumR+bw[i]/(n*w[i]);
				        }
				        lamda[0] = sumR; //即为最大特征值
				        Double ci=(sumR-n)/(n-1);                //矩阵一致性指标
				        System.out.println("计算出的矩阵一致性指标CI="+ci+"\n");
				        Double cr=ci/RI[n-1];                        //随机一致性比率 1.24为6阶矩阵的平均一致性指标
				      
				        lamda[1]=ci;
				        lamda[2]=cr;
				        if(cr>=0.1){
				            System.out.println("权重设置不合理");
				        }else{
				            //输出特征向量
				            for(int i=0;i<n;i++){
				                System.out.println("特征"+(i+1)+"的权重："+w[i]);
				            }
				        }
				        if(cr.equals(Double.NaN))
				        	cr = (double) 0;
				        BigDecimal b = new BigDecimal(sumR);  
						double sumR_2 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
						BigDecimal b_1 = new BigDecimal(cr);  
						double cr_2 = b_1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				        lblLamda.setText("lamda="+sumR_2+"     ");
				        lblNewLabel.setText("CR="+cr_2+"        ");
				        ww = w;
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
	public double jisuan() {
		System.out.println(this.ww);
		return 0;
		
	}
}
