package ToolsForMe;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class Select extends JFrame {

	private static final long serialVersionUID = 1L;
	public Custom custom;
	private JPanel contentPane;
    File configFile;
    boolean access = true;
    ArrayList<Integer> color = new ArrayList<Integer>();
    Audio ado;
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Select frame = new Select();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */


	public Select(User a) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 659);
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
		
		JButton btnNewButton = new JButton("건강관리");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				Health hl = new Health(a);
				hl.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(325, 276, 235, 78);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("리마인더");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Reminder rmd = new Reminder(a);
				rmd.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(33, 276, 235, 78);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("커스터마이징");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			   custom = new Custom(a);
			   dispose();
			   custom.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.setBounds(613, 276, 235, 78);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("환영합니다 !");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setBounds(12, 56, 862, 47);
		contentPane.add(lblNewLabel);
		
		JLabel userLabel = new JLabel(a.getnickName());
		userLabel.setHorizontalAlignment(SwingConstants.LEFT);
		userLabel.setFont(new Font("굴림", Font.BOLD, 20));
		userLabel.setBounds(12, 597, 726, 23);
		contentPane.add(userLabel);
		
		JButton btnNewButton_3 = new JButton("로그아웃");
		btnNewButton_3.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main m = new Main();
				m.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(750, 597, 124, 23);
		contentPane.add(btnNewButton_3);
		
		JButton audioButton = new JButton("음악 컨트롤러");
		audioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AudioInterface adi = new AudioInterface(a);
				
				adi.setVisible(true);
				
			}
		});
		audioButton.setFont(new Font("굴림", Font.BOLD, 20));
		audioButton.setBounds(678, 10, 196, 23);
		contentPane.add(audioButton);
	}
}
