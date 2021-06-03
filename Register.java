package ToolsForMe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtPassword;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtNickname;
	private JLabel lblNewLabel_3;

	
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Register frame = new Register();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */
	 

	    public Register() {
	    setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(162, 10, 120, 29);
		contentPane.add(lblNewLabel);
		
		txtNickname = new JTextField();
		txtNickname.setToolTipText("Nickname\uC744 \uC774\uACF3\uC5D0 \uC785\uB825\uD558\uC138\uC694.");
		txtNickname.setColumns(10);
		txtNickname.setBounds(159, 84, 161, 21);
		contentPane.add(txtNickname);
		
		txtId = new JTextField();
		txtId.setToolTipText("ID\uB97C \uC774\uACF3\uC5D0 \uC785\uB825\uD558\uC138\uC694.");
		txtId.setBounds(159, 115, 161, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setToolTipText("PW\uB97C \uC774\uACF3\uC5D0 \uC785\uB825\uD558\uC138\uC694.");
		txtPassword.setColumns(10);
		txtPassword.setBounds(159, 146, 161, 21);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtNickname.getText().equals("") || txtId.getText().equals("") || txtPassword.getText().equals(""))
				JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.");
				else {
				
				String nickname = txtNickname.getText();
				String id = txtId.getText();
				String pw = txtPassword.getText();
				
				User u = new User(nickname,id,pw);
				new CreateUser(u);
				
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				dispose();
				}
			}
		});
		btnNewButton.setBounds(168, 216, 97, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("\uC544 \uC774 \uB514");
		lblNewLabel_1.setBounds(92, 118, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_2.setBounds(92, 149, 57, 15);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("\uB2C9 \uB124 \uC784");
		lblNewLabel_3.setBounds(92, 87, 57, 15);
		contentPane.add(lblNewLabel_3);
	}
	}
