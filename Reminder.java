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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

class Reminder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    boolean access = true;
    ArrayList<Integer> color = new ArrayList<Integer>();
    JLabel totalText; 
    AddReminder addRemind;
    ArrayList<Remind> storedReminder = new ArrayList<Remind>(); 
    ArrayList<Remind> rmdSave = new ArrayList<Remind>(); 
    DefaultListModel model = new DefaultListModel();
    JLabel completedText;
    JLabel rateText;
    JList list;
    
	public Reminder(User a) {
		addRemind = new AddReminder(a);
		readConfig(a,model);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 645);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_prev = new JButton("이전화면");
		btn_prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Select m = new Select(a);
				m.setVisible(true);
			}
		});
		btn_prev.setFont(new Font("굴림", Font.BOLD, 20));
		btn_prev.setBounds(313, 583, 119, 23);
		contentPane.add(btn_prev);
		
		JLabel statusText = new JLabel("RDY");
		statusText.setForeground(Color.ORANGE);
		statusText.setHorizontalAlignment(SwingConstants.CENTER);
		statusText.setFont(new Font("굴림", Font.BOLD, 50));
		statusText.setBounds(313, 52, 119, 88);
		contentPane.add(statusText);
		
		list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				
                  if(list.getSelectedIndex() != -1) {

					switch(storedReminder.get(list.getSelectedIndex()).getStatus()){
						
						case "진행":
							statusText.setForeground(Color.GREEN);
							statusText.setText("진행");
							break;
							
						case "종료":
							statusText.setForeground(Color.RED);
							statusText.setText("종료");
							break;
							
						case "임박":
							statusText.setForeground(Color.YELLOW);
							statusText.setText("임박");
							break;
						
						case "완료":
							statusText.setForeground(Color.GREEN);
							statusText.setText("완료");
					
					
				
					}
				
			} else {statusText.setForeground(Color.ORANGE); statusText.setText("RDY");}
			
				
			}
		});
		list.setFont(new Font("굴림", Font.BOLD, 20));
		list.setBounds(12, 10, 292, 596);
		
		
		JLabel lblNewLabel = new JLabel("할 일 상태");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(313, 18, 119, 24);
		contentPane.add(lblNewLabel);
		
		JButton btn_add = new JButton("추가하기");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				addRemind.setVisible(true);
				
			}
		});
		btn_add.setFont(new Font("굴림", Font.BOLD, 20));
		btn_add.setBounds(313, 444, 119, 23);
		contentPane.add(btn_add);
		
		JButton btn_remove = new JButton("삭제하기");
		btn_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(list.getSelectedIndex() != -1) {
				storedReminder.remove(list.getSelectedIndex());
				rewriteConfig(a);
				dispose();
				Reminder r = new Reminder(a);
				r.setVisible(true);
				r.refreshStats();
				}
				else
				JOptionPane.showMessageDialog(null,"선택된 일정이 없습니다.");
			}
		});
		btn_remove.setFont(new Font("굴림", Font.BOLD, 20));
		btn_remove.setBounds(313, 510, 119, 23);
		contentPane.add(btn_remove);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 10, 289, 596);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("TOTAL");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(313, 150, 119, 23);
		contentPane.add(lblNewLabel_1);
		
		totalText = new JLabel("0");
		totalText.setForeground(SystemColor.info);
		totalText.setHorizontalAlignment(SwingConstants.CENTER);
		totalText.setFont(new Font("굴림", Font.BOLD, 30));
		totalText.setBounds(313, 175, 119, 41);
		contentPane.add(totalText);
		
		completedText = new JLabel("0");
		completedText.setForeground(SystemColor.menu);
		completedText.setHorizontalAlignment(SwingConstants.CENTER);
		completedText.setFont(new Font("굴림", Font.BOLD, 30));
		completedText.setBounds(313, 251, 119, 41);
		contentPane.add(completedText);
		
		JLabel lblNewLabel_1_1 = new JLabel("COMPLETE");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(313, 226, 119, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("RATE");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(313, 302, 119, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		rateText = new JLabel("0%");
		rateText.setHorizontalAlignment(SwingConstants.CENTER);
		rateText.setForeground(SystemColor.desktop);
		rateText.setFont(new Font("굴림", Font.BOLD, 30));
		rateText.setBounds(313, 327, 119, 41);
		contentPane.add(rateText);
		
		JButton btn_modify = new JButton("수정하기");
		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedIndex() == -1)
				JOptionPane.showMessageDialog(null, "수정할 일정을 먼저 선택하세요.");
				else {
				dispose();
				Modify mdf = new Modify(a,storedReminder,list.getSelectedIndex());
				mdf.setVisible(true);
				}
			}
		});
		btn_modify.setFont(new Font("굴림", Font.BOLD, 20));
		btn_modify.setBounds(313, 477, 119, 23);
		contentPane.add(btn_modify);
		
		JButton btn_detail = new JButton("상세정보");
		btn_detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if(list.getSelectedIndex() != -1)
				
			JOptionPane.showMessageDialog(null, "메모 : "+storedReminder.get(list.getSelectedIndex()).getWork()+
					"\n세부 정보 : "+storedReminder.get(list.getSelectedIndex()).getDetail()+"\n목표 날짜 : "+storedReminder.get(list.getSelectedIndex()).getDate()+
					"\n상태 : "+storedReminder.get(list.getSelectedIndex()).getStatus());
			
			else
				
		    JOptionPane.showMessageDialog(null, "선택된 일정이 없습니다.");
				
			}
		});
		btn_detail.setFont(new Font("굴림", Font.BOLD, 20));
		btn_detail.setBounds(313, 411, 119, 23);
		contentPane.add(btn_detail);
		
		JButton btn_workDone = new JButton("할일완료");
		btn_workDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(list.getSelectedIndex() != -1) {
				
			if(storedReminder.get(list.getSelectedIndex()).getStatus().equals("완료"))
			JOptionPane.showMessageDialog(null,"이미 완료된 일정입니다.");
			
			if(!storedReminder.get(list.getSelectedIndex()).getStatus().equals("종료")) {
			storedReminder.get(list.getSelectedIndex()).setStatus("완료");
			statusText.setForeground(Color.GREEN);
			statusText.setText(storedReminder.get(list.getSelectedIndex()).getStatus());
			rewriteConfig(a);
			dispose();
			Reminder r = new Reminder(a);
			r.setVisible(true);
			r.refreshStats();
			}
			
			else
				JOptionPane.showMessageDialog(null,"이미 종료된 일정입니다.");
			} else JOptionPane.showMessageDialog(null,"선택된 일정이 없습니다.");
			}
		});
		btn_workDone.setFont(new Font("굴림", Font.BOLD, 20));
		btn_workDone.setBounds(313, 378, 119, 23);
		contentPane.add(btn_workDone);
		
		JButton btn_remove_1 = new JButton("다시로드");
		btn_remove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Reminder r = new Reminder(a);
				r.setVisible(true);
				r.refreshStats();
			}
		});
		btn_remove_1.setFont(new Font("굴림", Font.BOLD, 20));
		btn_remove_1.setBounds(313, 543, 119, 23);
		contentPane.add(btn_remove_1);
		
		File configFile = new File("c:/ToolsForMe/"+a.getnickName()+"/"+a.getnickName()+"_config.txt");
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
		
		refreshStats();

} 
	  
		public void readConfig(User p,DefaultListModel modl) {
			
		File targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder.txt");
		
		if(!targetFile.exists())
			try {
				targetFile.createNewFile();
			} catch (IOException e1) {
			e1.printStackTrace();
			}

			BufferedReader reader;
			int idx = -1;
			
			try {
				reader = new BufferedReader(new FileReader(targetFile));

				String line;
				int count = -1;	
				String[] arr = new String[3];
				
				while((line = reader.readLine()) != null) {
				if(!line.equals("END OF WORK")) {
				count ++;
				arr[count] = line;
				} else {
				idx ++;
				count = -1;
				readDetailConfig(p,modl,arr[0],arr[1],arr[2],idx);
				}
			}
				reader.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		
		
		public void readDetailConfig(User p,DefaultListModel modl, String a,String b,String c,int index) {
			
			File targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder_detail.txt");
			
			if(!targetFile.exists())
				try {
					targetFile.createNewFile();
				} catch (IOException e1) {
				e1.printStackTrace();
				}
			
				BufferedReader reader;
				try {
					reader = new BufferedReader(new FileReader(targetFile));
			
					ArrayList<String> arr = new ArrayList<String>();
					String line;
					String str = "";
					
					while((line = reader.readLine()) != null) {
					if(!line.equals("END OF DETAIL")) 
					str += line + "\n";
					else {
					    arr.add(str);
						str = "";
						
					}
					
					
					
				}
					writeAll(modl,a,arr.get(index),b,c,index);
					reader.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
			}
		
		public void writeAll(DefaultListModel modl,String a,String b,String c,String d,int index) {
			
			Remind rmd = null;
			rmd = new Remind(a,b,c,d);
			rmdSave.add(rmd);
			
		     modl.clear();
		     storedReminder.clear();
		     for(int i=0;i<rmdSave.size();i++) {
		    	 
		    	 modl.addElement(rmdSave.get(i).getWork());
		    	 storedReminder.add(rmdSave.get(i));
		    	 
		     }
			
		}
		
		
		
		public void refreshStats() {
			
		    totalText.setText(Integer.toString(storedReminder.size()));
		    int completedcount = 0;
		    
		    
			for(int i=0;i<storedReminder.size();i++) 
			if(storedReminder.get(i).getStatus().equals("완료"))
			completedcount ++;
	
				
			
			
		 completedText.setText(Integer.toString(completedcount));

		 float avg = (((float)completedcount / (float)storedReminder.size()) * 100);
	
		
		     if(avg <= 30 && avg >= 0) {
			 
			 rateText.setForeground(Color.RED);
			 rateText.setText(Float.toString(avg));
			 
		 }
		 
             if(avg <= 50 && avg > 30) {
			 
			 rateText.setForeground(Color.YELLOW);
			 rateText.setText(Float.toString(avg));
			 
		 }
             
             if(avg <= 70 && avg > 50) {
    			 
    			 rateText.setForeground(Color.GREEN);
    			 rateText.setText(Float.toString(avg));
    			 
    		 }
             
             if(avg > 70) {
    			 
    			 rateText.setForeground(Color.BLUE);
    			 rateText.setText(Float.toString(avg));
    			 
    		 }
		 
			
		}
		
		
		public void rewriteConfig(User p) {
			
			File targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder.txt");
			File targetFile2 = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+"_reminder_detail.txt");
			
			if(targetFile.exists()) 
			targetFile.delete(); 
			
			try {
				targetFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			BufferedWriter writer,writer2;
					try {
						
						writer = new BufferedWriter(new FileWriter(targetFile)); 
						
						
						for(int i=0;i<storedReminder.size();i++) {
						writer.append(storedReminder.get(i).getWork()+"\n");
						writer.append(storedReminder.get(i).getDate()+"\n");
						writer.append(storedReminder.get(i).getStatus()+"\n");
						writer.append("END OF WORK\n");
						
						}
						writer.close();
					    	
					} catch (IOException e) {
				
						e.printStackTrace();
					}    	
					
					try {
						writer2 = new BufferedWriter(new FileWriter(targetFile2));
						
						
						for(int i=0;i<storedReminder.size();i++) {
						writer2.append(storedReminder.get(i).getDetail()+"\n");
						writer2.append("END OF DETAIL\n");
						}
						writer2.close();
						
					} catch (IOException e) {
						
						e.printStackTrace();
					} 
			
					
					
					
					readConfig(p,model);
					refreshStats();
		}
}


class Remind {
	
	private String work,date,status,detail;
	
	public Remind(String work,String detail,String date,String status) {
		
		this.work = work;
		this.date = date;
		this.detail = detail;
		this.status = status;
		
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}



