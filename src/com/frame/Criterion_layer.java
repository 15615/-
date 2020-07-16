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
//׼���
public class Criterion_layer extends JDialog {

	private final JPanel contentPanel = new JPanel();//���´�bai��һ��JPanel����
	public Double[] ww;//����
	/**
	 * Launch the application.����Ӧ�ó���
	 */
	public static void main(String[] args) {
		try {
			Criterion_layer dialog = new Criterion_layer(5);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//�����û��ڴ˶Ի������������رա�ʱĬ������½������Ĳ��������ô���Ĭ�ϴ��ڹرղ�����
			//setDefaultCloseOperation�����������趨���ڱ��ر�ʱ�򣨱����������Ͻǵ�"x"������Ϊ�ġ� 
			//DISPOSE_ON_CLOSE�ڴ��ڱ��رյ�ʱ���dispose������ڡ� 
			dialog.setVisible(true);//Shows or hides this Dialog depending on the value of parameter b. tureΪ��ʾ
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.//�����Ի���
	 */
	public Criterion_layer(int n) throws Exception{
		Double[][] matrix=new Double[n][n];
		Double[] column=new Double[n];
		Double[][] matrixColumn= new Double[n][n];
		Double[] line=new Double[n];
        Double[] w=new Double[n];//��������
        Double[] bw=new Double[n];
        Double[] lamda = new Double[n]; //��ֹ����  �����ڲ���;ֲ��ڲ���ֻ�������ⲿ��fianl����
        //�ֱ�洢    �������ֵ��һ����ָ�ꡢһ���Ա���
        double RI[]={0,0,0.52,0.89,1.12,1.26,1.36,1.41,1.46,1.49,1.52,1.54,1.56,1.58,1.59};
        
        
        for(int i=0;i<n;i++){
            matrix[i][i]=1.0;//�����б�Խ�Ϊ1    1  x  x
                                               //x  1  x
                                              // x  x  1
        }
		
		setTitle("\u51C6\u5219\u5C42\u5224\u65AD\u77E9\u9635");//���öԻ���ı��⡣      ׼����жϾ��� 
		setBounds(100, 100, 450, 300);//����һ���µľ��Σ������Ͻ�ָ��Ϊ(x,y)�����Ⱥ͸߶���ͬ���Ĳ���ָ��
		contentPanel.setLayout(new GridLayout(n,n, 0, 0));//����Ϊ���񲼾֣�n��n�У����ˮƽ����ֱ����Ϊ0
		getContentPane().add(contentPanel, BorderLayout.CENTER);//�м�
		
		JLabel[] labels = new JLabel[n*n];//�½�һ��nxn��JLabel���� 
		JPanel[] panels = new JPanel[n*n];//��������
		JTextField[] textField = new JTextField[n*n];//�ı��򳤶�nxn
		

		for( int i=0; i<panels.length; i++)
			{panels[i] = new JPanel();
			panels[i].setLayout(new BorderLayout());}//���ò���Ϊ�߿򲼾֣��߿򲼾ֶַ���������5����λ����ӿؼ���
			
		for( int i=0; i<labels.length; i++)
			labels[i] = new JLabel( "   a"+(i/n+1)+(i%n+1) );//axy��axy
		for( int i=0; i<panels.length; i++)
			panels[i].add( labels[i],BorderLayout.NORTH);//labels��i������������λ��
		for( int i=0; i<panels.length; i++)
			{textField[i] = new JTextField();
			panels[i].add( textField[i],BorderLayout.CENTER  );}//text�ı�����nxn��С�������м�λ�á�
		
		for( int i=0; i<panels.length; i++)
			contentPanel.add( panels[i] );//���ܵ�jpanel�ж�nxn��jpanel�������
		{
			
			JPanel buttonPane = new JPanel();//��������
			
				JLabel lblLamda = new JLabel("lamda=           ");//����һ��label������飬����һ��Jlabel��lamda����
				buttonPane.add(lblLamda);
			
			
				JLabel lblNewLabel = new JLabel("CR=              ");//ͬ��
				buttonPane.add(lblNewLabel);
				
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));//����������õĶ��뷽ʽ��������  �Ҷ���
			getContentPane().add(buttonPane, BorderLayout.SOUTH);//��������Ϊ���������ϡ��������������ÿ������ֻ�ܷ���һ������� ��
			{
				JButton okButton = new JButton("OK");//ok��ť
				okButton.addActionListener(new ActionListener() {//��Ӽ���
					public void actionPerformed(ActionEvent e) {
						ScriptEngineManager manager = new ScriptEngineManager();
						ScriptEngine engine = manager.getEngineByName("js");
						Double[][] matrix=new Double[n][n];//����
						Double[] column=new Double[n];//��
						Double[][] matrixColumn= new Double[n][n];
						Double[] line=new Double[n];
				        Double[] w=new Double[n];//��������
				        Double[] bw=new Double[n];
				        Double[] lamda = new Double[n];
						Double sum=0.0;
						Double  sumR=0.0; 
						for( int i=0; i<n*n; i++)
						{	
							try {
								Object tem = engine.eval(textField[i].getText());//���ذ���������ı�����е��ı���
								String tem_String = String.valueOf(tem);//objectת����string 
								matrix[i/n][i%n]=Double.valueOf(tem_String);//stringת����double
							} catch (ScriptException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						System.out.print("����");
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
				        BigDecimal b = new BigDecimal(sumR);  //��ȷ���
						double sumR_2 = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();//С�������λ��������
						BigDecimal b_1 = new BigDecimal(cr);  
						double cr_2 = b_1.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				        lblLamda.setText("lamda="+sumR_2+"     ");//�����ֵ
				        lblNewLabel.setText("CR="+cr_2+"        ");

				        ww = w;
						for(int i=0;i<ww.length;i++)
							System.out.println(ww[i]);//��w[i]��ֵ�ŵ�ww[i]��
					}
				});
				
				okButton.setActionCommand("OK");//��ť�Ĳ���ָ��
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);//��������Ĭ�ϵİ�ť���س���ֱ�Ӵ���
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {////��Ӽ���
					public void actionPerformed(ActionEvent e) {//actionPerformedΪ�ӿ�ActionListener�ķ���
						dispose();//�رմ��壬���ͷ���Դ 
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
