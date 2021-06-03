package ToolsForMe;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 579);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tools For Me");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(12, 30, 871, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("빠른 로그인");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel_1.setBounds(12, 378, 230, 42);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(57, 430, 137, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(57, 461, 137, 21);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("닉네임");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 430, 57, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("코드");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(12, 464, 43, 15);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton = new JButton("빠른 로그인");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
                String nickname = textField.getText();
                String quickpw = textField_1.getText();
                
                if(nickname.equals("") || quickpw.equals(""))
                JOptionPane.showMessageDialog(null, "빈칸이 없도록 작성하세요.");
                else {
                	
				GetUserInfo userinfo = new GetUserInfo(nickname);
				String[] arr = userinfo.getInfoArr();
				
				if(arr[0]==null)
				JOptionPane.showMessageDialog(null, "등록된 정보가 없습니다. \n회원가입을 먼저 해주세요.");
				else {
					
				
				if(!userinfo.isAdvancedUser())
				JOptionPane.showMessageDialog(null, "빠른 로그인을 사용 가능한 유저가 아닙니다.");
				else {
				
				if(nickname.equals(arr[0]) && quickpw.equals(arr[3])) {
				JOptionPane.showMessageDialog(null, "로그인에 성공 하였습니다.");
				
				
				User a = arr[3]==null ? new User(arr[0],arr[1],arr[2]) : new User(arr[0],arr[1],arr[2],arr[3]);
				
				dispose();
				Select slt = new Select(a);
				slt.setVisible(true);
				}
				else
				JOptionPane.showMessageDialog(null, "로그인에 실패 하였습니다.\n입력한 정보를 다시 확인하세요.");
				}
                }
                }
			}
		});
		btnNewButton.setBounds(39, 492, 172, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("로그인");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel_3.setBounds(12, 131, 871, 42);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(343, 203, 207, 36);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(343, 248, 207, 36);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(343, 295, 207, 36);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel_4 = new JLabel("닉네임");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(278, 203, 57, 36);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("아이디");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(278, 248, 57, 36);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("비밀번호");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4_1_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4_1_1.setBounds(278, 295, 66, 36);
		contentPane.add(lblNewLabel_4_1_1);
		
		JButton btnNewButton_1 = new JButton("로그인");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nickname = textField_2.getText();
				String id = textField_3.getText();
				String pw = textField_4.getText();
				
				GetUserInfo userinfo = new GetUserInfo(nickname);
				
				String[] arr = userinfo.getInfoArr();
				
				if(nickname.equals("") || id.equals("") || pw.equals(""))
					JOptionPane.showMessageDialog(null, "빈칸이 없도록 작성하세요.");
				else {
				if(arr[0]==null)
				JOptionPane.showMessageDialog(null, "등록된 정보가 없습니다. \n회원가입을 먼저 해주세요.");
				else {
					
				if(arr[0].equals(nickname) && arr[1].equals(id) && arr[2].equals(pw)) {
					JOptionPane.showMessageDialog(null, "로그인에 성공 하였습니다.");
					
					
				User a = arr[3]==null ? new User(arr[0],arr[1],arr[2]) : new User(arr[0],arr[1],arr[2],arr[3]);
				dispose();
				Select slt = new Select(a);
				slt.setVisible(true);
				
				}
				
				else
					JOptionPane.showMessageDialog(null, "로그인에 실패 하였습니다.\n입력한 정보를 다시 확인하세요.");
				
				}
				}
			}
		});
		btnNewButton_1.setBounds(398, 375, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("회원가입");
		btnNewButton_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Register reg = new Register();
				reg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				reg.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(381, 408, 131, 23);
		contentPane.add(btnNewButton_1_1);
	}
}
