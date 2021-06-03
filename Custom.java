package ToolsForMe;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;

public class Custom extends JFrame {

	private static final long serialVersionUID = 1L;
	CreateUser createu;
	Select slt;
	Audio ado;
	File configFile;
	JSlider color_r;
	JSlider color_g;
	JSlider color_b;
	private JPanel contentPane;
	private JTextField confirmPW;
	private JTextField newPW;
    boolean access = true;
    ArrayList<Integer> color = new ArrayList<Integer>();
    
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Custom frame = new Custom();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */
    
public static void removeProfile(String path) {
		
	    File folder = new File(path);
	    try {
		if(folder.exists()){
                File[] folder_list = folder.listFiles(); 
				
		for (int i = 0; i < folder_list.length; i++) {
		    if(folder_list[i].isFile()) {
			folder_list[i].delete();
		    }else {
			removeProfile(folder_list[i].getPath()); 
		    }
		    folder_list[i].delete();
		 }
		 folder.delete();
	       }
	   } catch (Exception e) {
		e.getStackTrace();
	   }
    }

	public Custom(User a) {
		ado = new Audio(a);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 651);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		
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
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("확인");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(confirmPW.getText().equals(a.getPassword())) {
				User b = new User(a.getnickName(),a.getId(),a.getPassword(),newPW.getText());
				createu = new CreateUser(b);
				createu.CreateAdvancedUser(b);
				JOptionPane.showMessageDialog(null, "프로필 갱신이 완료되었습니다.\n빠른 비밀번호로 로그인 가능합니다.");
				}
				else
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
			}
		});
		btnNewButton.setBounds(191, 536, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("이전화면");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slt = new Select(a);
				dispose();
				slt.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(357, 569, 110, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("음악 파일 관리");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 243, 455, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("배경 화면 관리");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel_1.setBounds(12, 25, 455, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("빠른 로그인 관리");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(12, 423, 455, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("현재 발견된 음악 파일 수 : "+ado.getAudioCount());
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)
					try {
						Runtime.getRuntime().exec("c:/windows/explorer.exe "+ado.getAudioPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(17, 283, 445, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("!파일 확인을 원하신다면, 위 텍스트를 더블클릭 하세요!");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(12, 323, 455, 30);
		contentPane.add(lblNewLabel_2_1);
		
		confirmPW = new JTextField();
		confirmPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		confirmPW.setBounds(236, 464, 101, 21);
		contentPane.add(confirmPW);
		confirmPW.setColumns(10);
		
		newPW = new JTextField();
		newPW.setBounds(236, 495, 101, 21);
		contentPane.add(newPW);
		newPW.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("현재 비밀번호");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_3.setBounds(112, 464, 124, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("빠른 비밀번호");
		lblNewLabel_3_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(112, 495, 124, 18);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel testBackgroundColor = new JLabel("이 텍스트의 색이 배경화면의 색이 됩니다.");
		testBackgroundColor.setHorizontalAlignment(SwingConstants.CENTER);
		testBackgroundColor.setForeground(Color.BLACK);
		testBackgroundColor.setFont(new Font("굴림", Font.BOLD, 20));
		testBackgroundColor.setBounds(17, 66, 445, 23);
		contentPane.add(testBackgroundColor);
		
		color_r = new JSlider();
		color_r.setMaximum(255);
		color_r.setValue(128);
		color_r.setBounds(139, 99, 200, 26);
		contentPane.add(color_r);
		
		color_g = new JSlider();
		color_g.setMaximum(255);
		color_g.setValue(128);
		color_g.setBounds(139, 135, 200, 26);
		contentPane.add(color_g);
		
		color_b = new JSlider();
		color_b.setMaximum(255);
		color_b.setValue(128);
		color_b.setBounds(139, 171, 200, 26);
		contentPane.add(color_b);
		
		
		color_r.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent arg0) {
				
				
				
				 testBackgroundColor.setForeground(new Color(color_r.getValue(),color_g.getValue(),color_b.getValue()));
		
				 
				
			}
		});
		
		color_g.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
					
					
					
					 testBackgroundColor.setForeground(new Color(color_r.getValue(),color_g.getValue(),color_b.getValue()));
			
					 
					
				}
			});
		
		color_b.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
					
					
					
					 testBackgroundColor.setForeground(new Color(color_r.getValue(),color_g.getValue(),color_b.getValue()));
			
					 
					
				}
			});
		
		
		JLabel lblNewLabel_5 = new JLabel("R");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_5.setBounds(112, 99, 22, 26);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("G");
		lblNewLabel_5_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_5_1.setBounds(112, 135, 22, 26);
		contentPane.add(lblNewLabel_5_1);
		
		
		JLabel lblNewLabel_5_2 = new JLabel("B");
		lblNewLabel_5_2.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_5_2.setBounds(112, 171, 22, 26);
		contentPane.add(lblNewLabel_5_2);
		
		JButton btnNewButton_2 = new JButton("다시 불러오기");
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Custom c = new Custom(a);
				c.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(152, 363, 174, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("배경화면 설정");
		btnNewButton_2_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				writeConfig(a);
				JOptionPane.showMessageDialog(null, "설정이 완료되었습니다.\n이전화면으로 돌아간 시점부터 적용됩니다.");
				
			}
		});
		btnNewButton_2_1.setBounds(155, 210, 169, 23);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_3 = new JButton("회원 탈퇴");
		btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pw = JOptionPane.showInputDialog("계정을 삭제하면, 모든정보가 소실됩니다. \n계속 진행하려면 비밀번호를 입력하세요."); 
				
				if(pw != null) {
				if(pw.equals(a.getPassword()))
				{
					removeProfile("c:/ToolsForMe/"+a.getnickName());
					JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다.");
					dispose();
					Main m = new Main();
					m.setVisible(true);
					
				}
				else 
				JOptionPane.showMessageDialog(null, "비밀번호가 올바르지 않습니다.");
				}
			}
		});
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setBounds(12, 569, 97, 23);
		contentPane.add(btnNewButton_3);
	}
	
	
	public void writeConfig(User a) {
		
		configFile = new File("c:/ToolsForMe/"+a.getnickName()+"/"+a.getnickName()+"_config.txt");
		if(!configFile.exists())
			try {
				configFile.createNewFile();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		
		if(configFile.exists()) configFile.delete();
		
   
		try {
			
    		BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
    		writer.append(color_r.getValue()+"\n");
    		writer.append(color_g.getValue()+"\n");
    		writer.append(color_b.getValue()+"\n");
    		writer.close();
    			
    		}catch(IOException e) {
    			
    			System.out.println("설정 파일 작성에 실패했습니다.");
    			e.printStackTrace();
    			
    		}		
		
		
		

	}
}
