package ToolsForMe;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JScrollPane;

class AddReminder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	File configFile;
	boolean access = true;
	ArrayList<Integer> color = new ArrayList<Integer>();
	private JTextField memoTextField;
	Date now = new Date();
	SpinnerDateModel mdl = new SpinnerDateModel(now,null,now,Calendar.DAY_OF_WEEK);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date value = (Date)mdl.getValue();
	Date next = (Date)mdl.getNextValue();
	private JTextArea detailTextField;


	public AddReminder(User a) {
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 354);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("할 일 추가하기");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 10, 500, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("메모");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 71, 500, 28);
		contentPane.add(lblNewLabel_1);
		
		memoTextField = new JTextField();
		memoTextField.setBounds(82, 109, 359, 22);
		contentPane.add(memoTextField);
		memoTextField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("목표 날짜");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(12, 203, 500, 22);
		contentPane.add(lblNewLabel_1_1);
		
		Date now = new Date();
		SpinnerDateModel mdl = new SpinnerDateModel(now,null,null,Calendar.DAY_OF_WEEK);

		
		JSpinner spinner = new JSpinner(mdl);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				value = (Date)mdl.getValue();
				next = (Date)mdl.getNextValue();
				
			}
		});
		spinner.setBounds(168, 235, 188, 22);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner,"yyyy-MM-dd");
		JFormattedTextField ftf = editor.getTextField();
		ftf.setHorizontalAlignment(JTextField.CENTER);
		spinner.setEditor(editor);
		contentPane.add(spinner);
		
		JButton btnNewButton = new JButton("이전화면");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e)
			{
				
				dispose();
				Reminder r = new Reminder(a);
				r.setVisible(true);
				
			}
			
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(391, 292, 121, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("추가");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 int num = JOptionPane.showConfirmDialog(null, "이대로 등록하시겠습니까?\n할 일 목록 : "+memoTextField.getText()+"\n세부 정보 : "+(detailTextField.getText().isEmpty() ? "세부 정보 없음" : detailTextField.getText())+"\n기간 : "+df.format(value));
				 if(num == 0) {
				
				Date d1 = null;
                Date d2 = null;
        
				try {
					d1 = df.parse(df.format(new Date()));
					d2 = df.parse(df.format(value));
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				int cpr = d1.compareTo(d2); // d1이 d2 보다 크면 
				
				if(cpr > 0) {
				writeConfig(a, memoTextField.getText(),detailTextField.getText(), df.format(value), "종료");}
				
				
				else if(cpr < 0) {
				writeConfig(a, memoTextField.getText(),detailTextField.getText(), df.format(value), "진행");}
				
				
				else {
				writeConfig(a,memoTextField.getText(),detailTextField.getText(), df.format(value), "임박");
				
				}
			     JOptionPane.showMessageDialog(null,"등록되었습니다.");
				 }
					
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(213, 294, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("세부 정보");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(12, 141, 500, 28);
		contentPane.add(lblNewLabel_1_2);
		
		detailTextField = new JTextArea();
		detailTextField.setColumns(10);
		detailTextField.setBounds(82, 179, 359, 22);
		
		
		JScrollPane scrollPane = new JScrollPane(detailTextField);
		scrollPane.setBounds(82, 179, 359, 22);
		contentPane.add(scrollPane);
		
		configFile = new File("c:/ToolsForMe/"+a.getnickName()+"/"+a.getnickName()+"_config.txt");
		if(!configFile.exists()) 
		access = false;
		else {
		
		
		
		if(access) {
			try {
				
				BufferedReader reader = new BufferedReader(new FileReader(configFile));
				String line;
				while((line = reader.readLine()) != null) {
                color.add(Integer.parseInt(line));
				
				}
				reader.close();
			
				
			}catch(IOException e) {
				
				e.printStackTrace();
				
			}
			
			contentPane.setBackground(new Color(color.get(0),color.get(1),color.get(2)));
			
		}
		
		
		
			
		}
	}
	
	
	  public void writeConfig(User p, String work,String detail, String date, String status) {
	    	
	    	
		    File targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder.txt");
		    File targetFile2 = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder_detail.txt");
		    
		    if(!targetFile.exists()) 
		    try {
				targetFile.createNewFile();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		    
		    if(!targetFile2.exists()) 
			    try {
					targetFile2.createNewFile();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			    
		    
		    
		    BufferedWriter writer,writer2;
			try {
				writer = new BufferedWriter(new FileWriter(targetFile,true));  
				
				writer.append(work+"\n");
				writer.append(date+"\n");
				writer.append(status+"\n");
				writer.append("END OF WORK\n");
				writer.close();
			    	
			} catch (IOException e) {
		
				e.printStackTrace();
			}    	
			
			
			   try {
				   
				writer2 = new BufferedWriter(new FileWriter(targetFile2,true));
				writer2.append(detail+"\n");
				writer2.append("END OF DETAIL\n");
				writer2.close();
				
			} 
			   catch (IOException e) {
				e.printStackTrace();
			}  
			 
			
			
			
		    }

	      
	  
}

