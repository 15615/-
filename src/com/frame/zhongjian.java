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
	 * Launch the application.����Ӧ�ó���
	 */
	public static void main(String[] args) {
		try {
			//int n=Integer.parseInt(textField.getText());
			zhongjian dialog = new zhongjian(2,2);//3����
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
	public zhongjian(int n,int ii) throws Exception{
		Double[][] matrix=new Double[n][n];
		Double[] column=new Double[n];
		Double[][] matrixColumn= new Double[n][n];
		Double[] line=new Double[n];
        Double[] w=new Double[n];//��������
        Double[] bw=new Double[n];
        Double[] lamda = new Double[3]; //��ֹ����  �����ڲ���;ֲ��ڲ���ֻ�������ⲿ��fianl����
        //�ֱ�洢    �������ֵ��һ����ָ�ꡢһ���Ա���
        double RI[]={0,0,0.52,0.89,1.12,1.26,1.36,1.41,1.46,1.49,1.52,1.54,1.56,1.58,1.59};
        
        
        for(int i=0;i<n;i++){
            matrix[i][i]=1.0;
        }
		
		setTitle("��"+(ii+1)+"׼����жϾ���");
		setBounds(100, 100, 450, 300);
		contentPanel.setLayout(new GridLayout(n,n, 0, 0));//����Ϊ���񲼾֣�n��n�У����ˮƽ����ֱ����Ϊ0
		getContentPane().add(contentPanel, BorderLayout.CENTER );//�м�
		
		JLabel[] labels = new JLabel[n*n];
		JPanel[] panels = new JPanel[n*n];
		JTextField[] textField = new JTextField[n*n];
		

		for( int i=0; i<panels.length; i++)
			{panels[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());}//���ò���Ϊ�߿򲼾֣��߿򲼾ֶַ���������5����λ�����ӿؼ���
			
		for( int i=0; i<labels.length; i++)
			labels[i] = new JLabel( "   a"+(i/n+1)+(i%n+1) );
		for( int i=0; i<panels.length; i++)
			panels[i].add( labels[i],BorderLayout.NORTH);//a�����ڱ���
		for( int i=0; i<panels.length; i++)
			{textField[i] = new JTextField();
			panels[i].add( textField[i],BorderLayout.CENTER);}//�ı����м�
		
		for( int i=0; i<panels.length; i++)
			contentPanel.add( panels[i] );//���ܵ�jpanel�ж�nxn��jpanel��������
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
				        Double[] w=new Double[n];//��������
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
						System.out.print(n+"�׾���");
						System.out.print("\n");
						for(int i=0;i<n;i++) {
							int sum1=0;
							sum1=sum1+i-1;
						
							for(int j=0;j<n;j++) {
							
								System.out.print(matrix[i][j]+"    ");//�����������ֵ
							}
						if(sum1 != i) {
							System.out.print("\n");
							}
							}
						
						//�����
				        for(int j=0;j<n;j++){
				            for(int i=0;i<n;i++){
				                if(column[j]!=null){
				                    column[j]=column[j]+matrix[i][j];
				                }else{
				                    column[j]=matrix[i][j];
				                }
				            }
				            
				        }
						//�����й�һ��
				        for(int j=0;j<n;j++){
				            for(int i=0;i<n;i++){
				                matrixColumn[i][j]=matrix[i][j]/column[j];
				            }
				        }
				        
				      //��������飨����ͣ�
				        for(int i=0;i<n;i++){
				            for(int j=0;j<n;j++){
				                if(line[i]!=null){
				                    line[i]=line[i]+matrixColumn[i][j];
				                }else{
				                    line[i]=matrixColumn[i][j];
				                }
				            }
				        }
				        
				        //�й�һ�������������

				        
				        for(int i=0;i<n;i++){
				            sum=sum+line[i];
				        }
				        for(int i=0;i<n;i++){
				            w[i]=line[i]/sum;                    //��������
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
				        
				                                //���������R
				        for(int i=0;i<n;i++){
				            sumR = sumR+bw[i]/(n*w[i]);
				        }
				        lamda[0] = sumR; //��Ϊ�������ֵ
				        Double ci=(sumR-n)/(n-1);                //����һ����ָ��
				        System.out.println("������ľ���һ����ָ��CI="+ci+"\n");
				        Double cr=ci/RI[n-1];                        //���һ���Ա��� 1.24Ϊ6�׾����ƽ��һ����ָ��
				      
				        lamda[1]=ci;
				        lamda[2]=cr;
				        if(cr>=0.1){
				            System.out.println("Ȩ�����ò�����");
				        }else{
				            //�����������
				            for(int i=0;i<n;i++){
				                System.out.println("����"+(i+1)+"��Ȩ�أ�"+w[i]);
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