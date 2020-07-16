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
//准则层
public class Criterion_layer extends JDialog {

	private final JPanel contentPanel = new JPanel();//是新创bai建一个JPanel对象，
	public Double[] ww;//数组
	/**
	 * Launch the application.启动应用程序
	 */
	public static void main(String[] args) {
		try {
			Criterion_layer dialog = new Criterion_layer(5);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//设置用户在此对话框上启动“关闭”时默认情况下将发生的操作。处置窗口默认窗口关闭操作。
			//setDefaultCloseOperation（）是用来设定窗口被关闭时候（比如点击了右上角的"x"）的行为的。 
			//DISPOSE_ON_CLOSE在窗口被关闭的时候会dispose这个窗口。 
			dialog.setVisible(true);//Shows or hides this Dialog depending on the value of parameter b. ture为显示
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.//创建对话框。
	 */
	public Criterion_layer(int n) throws Exception{
		Double[][] matrix=new Double[n][n];
		Double[] column=new Double[n];
		Double[][] matrixColumn= new Double[n][n];
		Double[] line=new Double[n];
        Double[] w=new Double[n];//特征向量
        Double[] bw=new Double[n];
        Double[] lamda = new Double[n]; //防止报错  匿名内部类和局部内部类只能引用外部的fianl变量
        //分别存储    最大特征值、一致性指标、一致性比率
        double RI[]={0,0,0.52,0.89,1.12,1.26,1.36,1.41,1.46,1.49,1.52,1.54,1.56,1.58,1.59};
        
        
        for(int i=0;i<n;i++){
            matrix[i][i]=1.0;//矩阵的斜对角为1    1  x  x
                                               //x  1  x
                                              // x  x  1
        }
		
		setTitle("\u51C6\u5219\u5C42\u5224\u65AD\u77E9\u9635");//设置对话框的标题。      准则层判断矩阵 
		setBounds(100, 100, 450, 300);//构造一个新的矩形，其左上角指定为(x,y)，其宽度和高度由同名的参数指定
		contentPanel.setLayout(new GridLayout(n,n, 0, 0));//设置为网格布局，n行n列，组件水平、垂直间距均为0
		getContentPane().add(contentPanel, BorderLayout.CENTER);//中间
		
		JLabel[] labels = new JLabel[n*n];//新建一个nxn的JLabel数组 
		JPanel[] panels = new JPanel[n*n];//创建数组
		JTextField[] textField = new JTextField[n*n];//文本框长度nxn
		

		for( int i=0; i<panels.length; i++)
			{panels[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());}//设置布局为边框布局，边框布局分东南西北中5个方位来添加控件。
			
		for( int i=0; i<labels.length; i++)
			labels[i] = new JLabel( "   a"+(i/n+1)+(i%n+1) );//axy，axy
		for( int i=0; i<panels.length; i++)
			panels[i].add( labels[i],BorderLayout.NORTH);//labels【i】在容器北边位置
		for( int i=0; i<panels.length; i++)
			{textField[i] = new JTextField();
			panels[i].add( textField[i],BorderLayout.CENTER  );}//text文本框在nxn的小容器的中间位置。
		
		for( int i=0; i<panels.length; i++)
			contentPanel.add( panels[i] );//在总的jpanel中对nxn的jpanel进行添加
		{
			
			JPanel buttonPane = new JPanel();//创建容器
			
				JLabel lblLamda = new JLabel("lamda=           ");//创建一个label组件数组，放了一个Jlabel的lamda对象
				buttonPane.add(lblLamda);
			
			
				JLabel lblNewLabel = new JLabel("CR=              ");//同上
				buttonPane.add(lblNewLabel);
				
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));//组件按照设置的对齐方式进行排列  右对齐
			getContentPane().add(buttonPane, BorderLayout.SOUTH);//容器划分为东、西、南、北、中五个区域，每个区域只能放置一个组件。 南
			{
				JButton okButton = new JButton("OK");//ok按钮
				okButton.addActionListener(new ActionListener() {//添加监听
					public void actionPerformed(ActionEvent e) {
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
						Double[][] matrix=new Double[n][n];//矩阵
						Double[] column=new Double[n];//列
						Double[][] matrixColumn= new Double[n][n];
						Double[] line=new Double[n];
				        Double[] w=new Double[n];//特征向量
				        Double[] bw=new Double[n];
				        Double[] lamda = new Double[n];
						Double sum=0.0;
						Double  sumR=0.0; 
						for( int i=0; i<n*n; i++)
						{	
							try {
								Object tem = engine.eval(textField[i].getText());//返回包含在这个文本组件中的文本。
								String tem_String = String.valueOf(tem);//object转换成string 
								matrix[i/n][i%n]=Double.valueOf(tem_String);//string转换成double
							} catch (ScriptException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						System.out.print("矩阵");
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
				        BigDecimal b = new BigDecimal(sumR);  //精确输出
						double sumR_2 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();//小数点后三位四舍五入
						BigDecimal b_1 = new BigDecimal(cr);  
						double cr_2 = b_1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				        lblLamda.setText("lamda="+sumR_2+"     ");//添加数值
				        lblNewLabel.setText("CR="+cr_2+"        ");

				        ww = w;
						for(int i=0;i<ww.length;i++)
							System.out.println(ww[i]);//将w[i]的值放到ww[i]中
					}
				});
				
				okButton.setActionCommand("OK");//按钮的操作指令
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);//用来设置默认的按钮，回车后直接触发
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {////添加监听
					public void actionPerformed(ActionEvent e) {//actionPerformed为接口ActionListener的方法
						dispose();//关闭窗体，关释放资源 
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				//getRootPane().setDefaultButton(cancelButton);
			}
		}
		
		
		
	}
	public Double[] jisuan() {
		
		
		return ww;
		
		
	}


}
