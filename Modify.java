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

class Modify extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean access = true;
    ArrayList<Integer> color = new ArrayList<Integer>();
    File configFile; 
    private JTextField workTextField;
    SpinnerDateModel mdl;
	Date value;
	Date next;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JTextArea detailTextField;

	public Modify(User a,ArrayList<Remind> b,int i) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("일정 수정하기");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 10, 410, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("일정");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1.setBounds(12, 61, 410, 31);
		contentPane.add(lblNewLabel_1);
		
		workTextField = new JTextField();
		workTextField.setText(b.get(i).getWork());
		workTextField.setBounds(66, 102, 302, 22);
		contentPane.add(workTextField);
		workTextField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("날짜");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(12, 194, 410, 31);
		contentPane.add(lblNewLabel_1_1);
		
		Date now = new Date();
		mdl = new SpinnerDateModel(now,null,null,Calendar.DAY_OF_WEEK);
		
		JSpinner spinner = new JSpinner(mdl);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				value = (Date)mdl.getValue();
				next = (Date)mdl.getNextValue();
				
			}
		});
		spinner.setBounds(66, 224, 302, 22);
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner,"yyyy-MM-dd");
		JFormattedTextField ftf = editor.getTextField();
		ftf.setHorizontalAlignment(JTextField.CENTER);
		spinner.setEditor(editor);
		contentPane.add(spinner);
		
		JButton btnNewButton = new JButton("수정하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				value = (Date)mdl.getValue();
				int num = JOptionPane.showConfirmDialog(null, "이대로 수정하시겠습니까?\n할 일 목록 : "+workTextField.getText()+"\n세부 정보 : "+(detailTextField.getText().isEmpty() ? "세부 정보 없음" : detailTextField.getText())+"\n기간 : "+df.format(value));
				if(num == 0) {
				if(detailTextField.getText().isEmpty())
				b.get(i).setDetail("세부 정보 없음");
				else
				b.get(i).setDetail(detailTextField.getText());
				
				if(workTextField.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "일정 입력은 필수 사항입니다.");
				else {
				b.get(i).setWork(workTextField.getText());
				b.get(i).setDate(df.format(value));
				
				Date d1 = null;
                Date d2 = null;
        
				try {
					d1 = df.parse(df.format(new Date()));
					d2 = df.parse(df.format(value));
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
				
				int cpr = d1.compareTo(d2); // d1이 d2 보다 크면 
				
				if(cpr > 0)
				b.get(i).setStatus("종료");
				else if(cpr < 0)
				b.get(i).setStatus("진행");
				else
				b.get(i).setStatus("임박");
				
				rewriteConfig(a,b);
				JOptionPane.showMessageDialog(null,"수정되었습니다.");
				}
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(131, 256, 172, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("이전화면");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Reminder rd = new Reminder(a);
				rd.setVisible(true);
				rd.refreshStats();
				
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(300, 288, 122, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("세부 정보");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(12, 134, 410, 31);
		contentPane.add(lblNewLabel_1_2);
		
		detailTextField = new JTextArea();
		detailTextField.setText(b.get(i).getDetail());
		detailTextField.setColumns(10);
		detailTextField.setBounds(66, 171, 302, 22);
		detailTextField.setText(b.get(i).getDetail());
	
		
		JScrollPane scrollPane = new JScrollPane(detailTextField);
		scrollPane.setBounds(66, 171, 302, 22);
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
	
	
	
	public void rewriteConfig(User p,ArrayList<Remind> arr) {
		
		File targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder.txt");
		File targetFile2 = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder_detail.txt"); 
		
		if(targetFile.exists()) 
		targetFile.delete(); 
		
		try {
			targetFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if(targetFile2.exists()) 
			targetFile2.delete(); 
			
			try {
				targetFile2.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		            	BufferedWriter writer,writer2;
				try {
					
					writer = new BufferedWriter(new FileWriter(targetFile)); 
					
					for(int i=0;i<arr.size();i++) {
					writer.append(arr.get(i).getWork()+"\n");
					writer.append(arr.get(i).getDate()+"\n");
					writer.append(arr.get(i).getStatus()+"\n");
					writer.append("END OF WORK\n");
					
					}
					writer.close();
				    	
				} catch (IOException e) {
			
					e.printStackTrace();
				}    	
				
				
				try {
					writer2 = new BufferedWriter(new FileWriter(targetFile2));
					
					for(int i=0;i<arr.size();i++) {
					writer2.append(arr.get(i).getDetail()+"\n");
					writer2.append("END OF DETAIL\n");
					}
					writer2.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				} 
			
	}
}


	








