package ToolsForMe;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Health extends JFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<Integer> color = new ArrayList<Integer>();
	boolean access = true;
	private JPanel contentPane;
    File healthConst;
    String[] infoarr = new String[8];
    JLabel totalWalk = new JLabel("0");
    JLabel walkresult = new JLabel("정보 없음");
    JLabel shrink= new JLabel("0");
    JLabel release= new JLabel("0");
    JLabel bpresult= new JLabel("정보 없음");
    JLabel weight= new JLabel("0");
    JLabel height= new JLabel("0");
    JLabel weightresult= new JLabel("정보 없음");
    private JTextField addwalk;
    
	public Health(User a) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 655);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		healthConst = new File("c:/ToolsForMe/"+a.getnickName()+"/"+a.getnickName()+"_info.txt");
		
		if(!healthConst.exists()){
		
				
				healthProfile hpf = new healthProfile();
				writeConfig(hpf);
				readConfig(a);
				refreshAll();
		}
		else {
			readConfig(a);
			refreshAll();
			}
		
		
		
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
			
			getContentPane().setBackground(new Color(color.get(0),color.get(1),color.get(2)));
			
		}	
		}
		
		
		
		JButton btnNewButton = new JButton("이전화면");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Select s = new Select(a);
				dispose();
				s.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(599, 583, 119, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("건강 관리");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 706, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("걸음 수");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel_1.setBounds(12, 88, 245, 58);
		contentPane.add(lblNewLabel_1);
		
		JLabel walkTotal_alert_1 = new JLabel("누적 걸음 수");
		walkTotal_alert_1.setHorizontalAlignment(SwingConstants.CENTER);
		walkTotal_alert_1.setFont(new Font("굴림", Font.BOLD, 20));
		walkTotal_alert_1.setBounds(12, 157, 245, 33);
		contentPane.add(walkTotal_alert_1);
		
		
		totalWalk.setHorizontalAlignment(SwingConstants.CENTER);
		totalWalk.setFont(new Font("굴림", Font.BOLD, 50));
		totalWalk.setBounds(12, 231, 245, 69);
		contentPane.add(totalWalk);
		
		
		walkresult.setHorizontalAlignment(SwingConstants.CENTER);
		walkresult.setFont(new Font("굴림", Font.BOLD, 35));
		walkresult.setBounds(12, 346, 250, 58);
		contentPane.add(walkresult);
		
		JLabel lblNewLabel_1_1 = new JLabel("혈압\r\n");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(473, 88, 245, 58);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel walkToday_alert_1_1 = new JLabel("수축기");
		walkToday_alert_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		walkToday_alert_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		walkToday_alert_1_1.setBounds(473, 157, 245, 33);
		contentPane.add(walkToday_alert_1_1);
		
		
		shrink.setHorizontalAlignment(SwingConstants.CENTER);
		shrink.setFont(new Font("굴림", Font.BOLD, 30));
		shrink.setBounds(473, 200, 245, 42);
		contentPane.add(shrink);
		
		JLabel walkToday_alert_1_1_1 = new JLabel("수축기");
		walkToday_alert_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		walkToday_alert_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		walkToday_alert_1_1_1.setBounds(473, 252, 245, 33);
		contentPane.add(walkToday_alert_1_1_1);
		

		release.setHorizontalAlignment(SwingConstants.CENTER);
		release.setFont(new Font("굴림", Font.BOLD, 30));
		release.setBounds(473, 295, 245, 42);
		contentPane.add(release);
		

		bpresult.setHorizontalAlignment(SwingConstants.CENTER);
		bpresult.setFont(new Font("굴림", Font.BOLD, 35));
		bpresult.setBounds(473, 346, 245, 58);
		contentPane.add(bpresult);
		
		JButton btnNewButton_1 = new JButton("정보 추가하기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				addHealthProfile ahp = new addHealthProfile(a);
				dispose();
				ahp.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(273, 435, 184, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("정보 삭제하기");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String pw = JOptionPane.showInputDialog(null, "기록이 모두 삭제됩니다.\n 계속 하시려면 비밀번호를 입력하세요.");
				
			 if(pw != null) {
				 
				 if(pw.equals(a.getPassword())) {
					 removeFile();
					 dispose();
					 Select slt = new Select(a);
					 slt.setVisible(true);
				  
					 JOptionPane.showMessageDialog(null, "제거되었습니다.");
					 }
					 else
						 JOptionPane.showMessageDialog(null, "파일이 존재하지 않습니다.");
					 
					 
				 } else JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					 

			}
		});
		btnNewButton_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1_1.setBounds(273, 521, 184, 33);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("정보 수정하기");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				editHealthProfile ehp = new editHealthProfile(a,infoarr);
				ehp.setVisible(true);
				
			}
		});
		btnNewButton_1_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1_2.setBounds(273, 478, 184, 33);
		contentPane.add(btnNewButton_1_2);
		
		JLabel userName = new JLabel(a.getnickName());
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setFont(new Font("굴림", Font.BOLD, 30));
		userName.setBounds(242, 88, 245, 58);
		contentPane.add(userName);
		
		JLabel walkToday_alert_1_1_2 = new JLabel("키");
		walkToday_alert_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		walkToday_alert_1_1_2.setFont(new Font("굴림", Font.BOLD, 20));
		walkToday_alert_1_1_2.setBounds(242, 157, 245, 33);
		contentPane.add(walkToday_alert_1_1_2);
		
		
		height.setHorizontalAlignment(SwingConstants.CENTER);
		height.setFont(new Font("굴림", Font.BOLD, 30));
		height.setBounds(242, 200, 245, 42);
		contentPane.add(height);
		
		JLabel walkToday_alert_1_1_1_1 = new JLabel("몸무게");
		walkToday_alert_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		walkToday_alert_1_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		walkToday_alert_1_1_1_1.setBounds(242, 252, 245, 33);
		contentPane.add(walkToday_alert_1_1_1_1);
		
	
		weight.setHorizontalAlignment(SwingConstants.CENTER);
		weight.setFont(new Font("굴림", Font.BOLD, 30));
		weight.setBounds(242, 295, 245, 42);
		contentPane.add(weight);
		
	
		weightresult.setHorizontalAlignment(SwingConstants.CENTER);
		weightresult.setFont(new Font("굴림", Font.BOLD, 35));
		weightresult.setBounds(242, 346, 245, 58);
		contentPane.add(weightresult);
		
		addwalk = new JTextField();
		addwalk.setToolTipText("걸음 수 추가는 +,-를 지원합니다.");
		addwalk.setBounds(101, 439, 66, 30);
		contentPane.add(addwalk);
		addwalk.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("걸음수 추가");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(addwalk.getText().isEmpty()) JOptionPane.showMessageDialog(null, "수를 먼저 입력하세요.");
				
			healthProfile hp;
			infoarr[2] = Integer.toString(Integer.parseInt(infoarr[2]) + Integer.parseInt(addwalk.getText()));
			int result = Integer.parseInt(infoarr[2]) + Integer.parseInt(addwalk.getText());
			
			if (result > 750)
				infoarr[5] = "매우 좋음";

			if (result <= 750 && result > 500)
				infoarr[5] = "좋음";

			if (result <= 500 && result > 250)
				infoarr[5] = "보통";

			if (result <= 250 && result > 0)
				infoarr[5] = "나쁨";

			if (result <= 0)
				infoarr[5] = "매우 나쁨";
			
			
			hp = new healthProfile(Double.parseDouble(infoarr[0]),Double.parseDouble(infoarr[1]),Integer.parseInt(infoarr[2]),Integer.parseInt(infoarr[3]),Integer.parseInt(infoarr[4]),infoarr[5],infoarr[6],infoarr[7]);
			writeConfig(hp);
			refreshAll();
			}
		});
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.setBounds(56, 480, 156, 28);
		contentPane.add(btnNewButton_2);
		
		 
		
		
	}
	
	public void removeFile() {
		
		if(healthConst.exists())
			healthConst.delete();
		
	}
	
	public void writeConfig(healthProfile p) {

		if (healthConst.exists())
			healthConst.delete();

		try {
			healthConst.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(healthConst));
			writer.append(p.getHeight() + "\n");
			writer.append(p.getWeight() + "\n");
			writer.append(p.getTotalWalk() + "\n");
			writer.append(p.getBp_shrink() + "\n");
			writer.append(p.getBp_release() + "\n");
			writer.append(p.getWalkState() + "\n");
			writer.append(p.getBpState() + "\n");
			writer.append(p.getWeightState() + "\n");

			writer.close();

		} catch (IOException e) {

			System.out.println("건강관리 설정 파일 작성에 실패했습니다.");
			e.printStackTrace();

		}

	}
	
	
	
	
	public void readConfig(User p) {
		
		
		
		if(!healthConst.exists())
			try {
				healthConst.createNewFile();
			} catch (IOException e1) {
			e1.printStackTrace();
			}

			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(healthConst));
				
				String line;
				int count = -1;	
				
				while((line = reader.readLine()) != null) {
				count ++;
				infoarr[count] = line;
				}
				reader.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			
		}
		
		
	public void refreshAll() {
	
		
		height.setText(infoarr[0]);
		weight.setText(infoarr[1]);
		totalWalk.setText(infoarr[2]);
		shrink.setText(infoarr[3]);
		release.setText(infoarr[4]);
		
		switch(infoarr[5]) {
		
		case "매우 나쁨": walkresult.setForeground(Color.RED);
		break;
		case "나쁨": walkresult.setForeground(Color.ORANGE);
		break;
		case "보통": walkresult.setForeground(Color.YELLOW);
		break;
		case "좋음": walkresult.setForeground(Color.GREEN);
		break;
		case "매우 좋음": walkresult.setForeground(Color.BLUE);
		break;
		
		}
		
		walkresult.setText(infoarr[5]);
		
		switch(infoarr[6]) {
		
		case "정상": bpresult.setForeground(Color.BLUE);
		break;
		case "고혈압 전(1기)": bpresult.setForeground(Color.GREEN);
		break;
		case "고혈압 전(2기)": bpresult.setForeground(Color.YELLOW);
		break;
		case "고혈압 1기": bpresult.setForeground(Color.ORANGE);
		break;
		case "고혈압 2기": bpresult.setForeground(Color.RED);
		break;
		}
		
		bpresult.setText(infoarr[6]);
		
		switch(infoarr[7]) {
		
		case "정상": weightresult.setForeground(Color.BLUE);
		break;
		case "저체중": weightresult.setForeground(Color.ORANGE);
		break;
		case "과체중": weightresult.setForeground(Color.ORANGE);
		break;
		case "비만": weightresult.setForeground(Color.RED);
		break;
		
		}
		
		weightresult.setText(infoarr[7]);
		
		
	}
}



class healthProfile {
	
	private double height;
	private double weight;
    private int totalWalk;
    private int bp_shrink;
    private int bp_release;
    private String walkState;
    private String bpState;
    private String weightState;
	
	public healthProfile() {
		
		this.height = 0;
		this.weight = 0;
		this.totalWalk = 0;
		this.bp_shrink = 0;
		this.bp_release = 0;
		this.walkState = "정보 없음";
		this.bpState = "정보 없음";
		this.weightState = "정보 없음";
		
	}
	
	public healthProfile(double _a,double _b, int b,int c,int d,String e, String f,String g) {
		
		this.height = _a;
		this.weight = _b;
		this.totalWalk = b;
		this.bp_shrink = c;
		this.bp_release = d;
		this.walkState = e;
		this.bpState = f;
		this.weightState = g;
		
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getWeightState() {
		return weightState;
	}

	public void setWeightState(String weightState) {
		this.weightState = weightState;
	}

	public int getTotalWalk() {
		return totalWalk;
	}

	public void setTotalWalk(int totalWalk) {
		this.totalWalk = totalWalk;
	}

	public int getBp_shrink() {
		return bp_shrink;
	}

	public void setBp_shrink(int bp_shrink) {
		this.bp_shrink = bp_shrink;
	}

	public int getBp_release() {
		return bp_release;
	}

	public void setBp_release(int bp_release) {
		this.bp_release = bp_release;
	}

	public String getWalkState() {
		return walkState;
	}

	public void setWalkState(String walkState) {
		this.walkState = walkState;
	}

	public String getBpState() {
		return bpState;
	}

	public void setBpState(String bpState) {
		this.bpState = bpState;
	}
	
	
}


class addHealthProfile extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ArrayList<Integer> color = new ArrayList<Integer>();
	boolean access = true;
	private JTextField heightText;
	private JTextField weightText;
	private JTextField walkText;
	private JTextField shrinkText;
	private JTextField releaseText;
	File healthConst;
	
	public addHealthProfile(User a) {
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 491);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("정보 추가하기");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 410, 36);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("이전화면");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Health h = new Health(a);
				dispose();
				h.setVisible(true);

			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(309, 419, 120, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("키");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(104, 130, 79, 25);
		contentPane.add(lblNewLabel_1);

		heightText = new JTextField();
		heightText.setBounds(195, 130, 116, 23);
		contentPane.add(heightText);
		heightText.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("몸무게");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(104, 165, 79, 25);
		contentPane.add(lblNewLabel_1_1);

		weightText = new JTextField();
		weightText.setColumns(10);
		weightText.setBounds(195, 165, 116, 23);
		contentPane.add(weightText);

		JLabel lblNewLabel_1_1_1 = new JLabel("현재 걸음 수");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(52, 200, 131, 25);
		contentPane.add(lblNewLabel_1_1_1);

		walkText = new JTextField();
		walkText.setColumns(10);
		walkText.setBounds(195, 200, 116, 23);
		contentPane.add(walkText);

		JLabel lblNewLabel_1_1_2 = new JLabel("수축기 혈압");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(62, 235, 121, 25);
		contentPane.add(lblNewLabel_1_1_2);

		shrinkText = new JTextField();
		shrinkText.setColumns(10);
		shrinkText.setBounds(195, 235, 116, 23);
		contentPane.add(shrinkText);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("이완기 혈압");
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_2_1.setBounds(62, 270, 121, 25);
		contentPane.add(lblNewLabel_1_1_2_1);

		releaseText = new JTextField();
		releaseText.setColumns(10);
		releaseText.setBounds(195, 270, 116, 23);
		contentPane.add(releaseText);

		JButton btnNewButton_1 = new JButton("등록하기");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				healthConst = new File("c:/ToolsForMe/" + a.getnickName() + "/" + a.getnickName() + "_info.txt");
				healthProfile hp;
				String weightstat = "정보 없음";
				String bpstat = "정보 없음";
				String walkstat = "정보 없음";

				double bmi = (Double.parseDouble(weightText.getText())
						/ Math.pow(Double.parseDouble(heightText.getText()) / 100, 2));

				if (healthConst.exists()) {
					String pw = JOptionPane.showInputDialog("이미 정보가 존재합니다. \n계속 진행(덮어 씌우기)하려면 비밀번호를 입력하세요.\n(해당 기능을 처음 사용하셔도 덮어씌우기 해주세요.)");
				 
					if(pw != null) {
					if (pw.equals(a.getPassword())) {
						healthConst.delete();
						
						if (bmi < 20)
							weightstat = "저체중";
						if (20 <= bmi && bmi <= 24)
							weightstat = "정상";
						if (25 <= bmi && bmi <= 29)
							weightstat = "과체중";
						if (bmi >= 30)
							weightstat = "비만";

						if (Integer.parseInt(shrinkText.getText()) <= 120 && Integer.parseInt(releaseText.getText()) <= 80)
							bpstat = "정상";
						if (120 < Integer.parseInt(shrinkText.getText()) && Integer.parseInt(shrinkText.getText()) <= 129
								|| 80 < Integer.parseInt(releaseText.getText())
										&& Integer.parseInt(releaseText.getText()) <= 84)
							bpstat = "고혈압 전(1기)";
						if (130 <= Integer.parseInt(shrinkText.getText()) && Integer.parseInt(shrinkText.getText()) <= 139
								|| 85 <= Integer.parseInt(releaseText.getText())
										&& Integer.parseInt(releaseText.getText()) <= 89)
							bpstat = "고혈압 전(2기)";
						if (140 <= Integer.parseInt(shrinkText.getText()) && Integer.parseInt(shrinkText.getText()) <= 159
								|| 90 <= Integer.parseInt(releaseText.getText())
										&& Integer.parseInt(releaseText.getText()) <= 99)
							bpstat = "고혈압 1기";
						if (Integer.parseInt(shrinkText.getText()) >= 160 || Integer.parseInt(releaseText.getText()) >= 100)
							bpstat = "고혈압 2기";

						if (Integer.parseInt(walkText.getText()) > 750)
							walkstat = "매우 좋음";

						if (Integer.parseInt(walkText.getText()) <= 750 && Integer.parseInt(walkText.getText()) > 500)
							walkstat = "좋음";

						if (Integer.parseInt(walkText.getText()) <= 500 && Integer.parseInt(walkText.getText()) > 250)
							walkstat = "보통";

						if (Integer.parseInt(walkText.getText()) <= 250 && Integer.parseInt(walkText.getText()) > 0)
							walkstat = "나쁨";

						if (Integer.parseInt(walkText.getText()) <= 0)
							walkstat = "매우 나쁨";

						hp = new healthProfile(Double.parseDouble(heightText.getText()),
								Double.parseDouble(weightText.getText()), Integer.parseInt(walkText.getText()),
								Integer.parseInt(shrinkText.getText()), Integer.parseInt(releaseText.getText()), walkstat,
								bpstat, weightstat);

						writeConfig(hp);
						
						JOptionPane.showMessageDialog(null, "프로필 추가가 완료되었습니다.");
						
						
						
					}
					else
						JOptionPane.showMessageDialog(null, "비밀번호가 올바르지 않습니다.");
				}
				}
				else {
				
                
				if (bmi < 20)
					weightstat = "저체중";
				if (20 <= bmi && bmi <= 24)
					weightstat = "정상";
				if (25 <= bmi && bmi <= 29)
					weightstat = "과체중";
				if (bmi >= 30)
					weightstat = "비만";

				if (Integer.parseInt(shrinkText.getText()) <= 120 && Integer.parseInt(releaseText.getText()) <= 80)
					bpstat = "정상";
				if (120 < Integer.parseInt(shrinkText.getText()) && Integer.parseInt(shrinkText.getText()) <= 129
						|| 80 < Integer.parseInt(releaseText.getText())
								&& Integer.parseInt(releaseText.getText()) <= 84)
					bpstat = "고혈압 전(1기)";
				if (130 <= Integer.parseInt(shrinkText.getText()) && Integer.parseInt(shrinkText.getText()) <= 139
						|| 85 <= Integer.parseInt(releaseText.getText())
								&& Integer.parseInt(releaseText.getText()) <= 89)
					bpstat = "고혈압 전(2기)";
				if (140 <= Integer.parseInt(shrinkText.getText()) && Integer.parseInt(shrinkText.getText()) <= 159
						|| 90 <= Integer.parseInt(releaseText.getText())
								&& Integer.parseInt(releaseText.getText()) <= 99)
					bpstat = "고혈압 1기";
				if (Integer.parseInt(shrinkText.getText()) >= 160 || Integer.parseInt(releaseText.getText()) >= 100)
					bpstat = "고혈압 2기";

				if (Integer.parseInt(walkText.getText()) > 750)
					walkstat = "매우 좋음";

				if (Integer.parseInt(walkText.getText()) <= 750 && Integer.parseInt(walkText.getText()) > 500)
					walkstat = "좋음";

				if (Integer.parseInt(walkText.getText()) <= 500 && Integer.parseInt(walkText.getText()) > 250)
					walkstat = "보통";

				if (Integer.parseInt(walkText.getText()) <= 250 && Integer.parseInt(walkText.getText()) > 0)
					walkstat = "나쁨";

				if (Integer.parseInt(walkText.getText()) <= 0)
					walkstat = "매우 나쁨";

				hp = new healthProfile(Double.parseDouble(heightText.getText()),
						Double.parseDouble(weightText.getText()), Integer.parseInt(walkText.getText()),
						Integer.parseInt(shrinkText.getText()), Integer.parseInt(releaseText.getText()), walkstat,
						bpstat, weightstat);

				writeConfig(hp);
				
				JOptionPane.showMessageDialog(null, "프로필 추가가 완료되었습니다.");
				
				}
			}
				
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(149, 349, 136, 25);
		contentPane.add(btnNewButton_1);

		File configFile = new File("c:/ToolsForMe/" + a.getnickName() + "/" + a.getnickName() + "_config.txt");
		if (!configFile.exists())
			access = false;
		else {

			if (access) {
				try {

					BufferedReader reader = new BufferedReader(new FileReader(configFile));
					String line;
					while ((line = reader.readLine()) != null) {
						color.add(Integer.parseInt(line));

					}
					reader.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

				getContentPane().setBackground(new Color(color.get(0), color.get(1), color.get(2)));

			}
		}

	}

	public void writeConfig(healthProfile p) {

		if (healthConst.exists())
			healthConst.delete();

		try {
			healthConst.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(healthConst));
			writer.append(p.getHeight() + "\n");
			writer.append(p.getWeight() + "\n");
			writer.append(p.getTotalWalk() + "\n");
			writer.append(p.getBp_shrink() + "\n");
			writer.append(p.getBp_release() + "\n");
			writer.append(p.getWalkState() + "\n");
			writer.append(p.getBpState() + "\n");
			writer.append(p.getWeightState() + "\n");

			writer.close();

		} catch (IOException e) {

			System.out.println("건강관리 설정 파일 작성에 실패했습니다.");
			e.printStackTrace();

		}

	}
}	

class editHealthProfile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ArrayList<Integer> color = new ArrayList<Integer>();
	boolean access = true;
	private JTextField height;
	private JTextField weight;
	private JTextField totalwalk;
	private JTextField shrink;
	private JTextField release;
	File healthConst;

	public editHealthProfile(User a, String[] arr) {
		healthConst = new File("c:/ToolsForMe/"+a.getnickName()+"/"+a.getnickName()+"_info.txt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 496);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.activeCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("수정하기");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(12, 10, 410, 54);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("이전화면");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Health h = new Health(a);
				dispose();
				h.setVisible(true);

			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(307, 424, 115, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("키");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(123, 128, 70, 22);
		contentPane.add(lblNewLabel_1);

		height = new JTextField();
		height.setBounds(181, 128, 152, 23);
		contentPane.add(height);
		height.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("몸무게");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(90, 160, 103, 22);
		contentPane.add(lblNewLabel_1_1);

		weight = new JTextField();
		weight.setColumns(10);
		weight.setBounds(181, 160, 152, 23);
		contentPane.add(weight);

		JLabel lblNewLabel_1_1_1 = new JLabel("누적 걸음 수");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(41, 192, 152, 22);
		contentPane.add(lblNewLabel_1_1_1);

		totalwalk = new JTextField();
		totalwalk.setColumns(10);
		totalwalk.setBounds(181, 192, 152, 23);
		contentPane.add(totalwalk);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("수축기 혈압");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(41, 224, 152, 22);
		contentPane.add(lblNewLabel_1_1_1_1);

		shrink = new JTextField();
		shrink.setColumns(10);
		shrink.setBounds(181, 224, 152, 23);
		contentPane.add(shrink);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("이완기 혈압");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1_1_1_1_1.setBounds(41, 256, 152, 22);
		contentPane.add(lblNewLabel_1_1_1_1_1);

		release = new JTextField();
		release.setColumns(10);
		release.setBounds(181, 256, 152, 23);
		contentPane.add(release);

		JButton btnNewButton_1 = new JButton("수정 완료");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				arr[0] = height.getText();
				arr[1] = weight.getText();
				arr[2] = totalwalk.getText();
				arr[3] = shrink.getText();
				arr[4] = release.getText();

				String weightstat = "정보 없음";
				String bpstat = "정보 없음";
				String walkstat = "정보 없음";

				double bmi = (Double.parseDouble(weight.getText()) / Math.pow(Double.parseDouble(height.getText()) / 100, 2));

				if (bmi < 20)
					weightstat = "저체중";
				if (20 <= bmi && bmi <= 24)
					weightstat = "정상";
				if (25 <= bmi && bmi <= 29)
					weightstat = "과체중";
				if (bmi >= 30)
					weightstat = "비만";

				if (Integer.parseInt(shrink.getText()) <= 120 && Integer.parseInt(release.getText()) <= 80)
					bpstat = "정상";
				if (120 < Integer.parseInt(shrink.getText()) && Integer.parseInt(shrink.getText()) <= 129
						|| 80 < Integer.parseInt(release.getText())
								&& Integer.parseInt(release.getText()) <= 84)
					bpstat = "고혈압 전(1기)";
				if (130 <= Integer.parseInt(shrink.getText()) && Integer.parseInt(shrink.getText()) <= 139
						|| 85 <= Integer.parseInt(release.getText())
								&& Integer.parseInt(release.getText()) <= 89)
					bpstat = "고혈압 전(2기)";
				if (140 <= Integer.parseInt(shrink.getText()) && Integer.parseInt(shrink.getText()) <= 159
						|| 90 <= Integer.parseInt(release.getText())
								&& Integer.parseInt(release.getText()) <= 99)
					bpstat = "고혈압 1기";
				if (Integer.parseInt(shrink.getText()) >= 160 || Integer.parseInt(release.getText()) >= 100)
					bpstat = "고혈압 2기";

				if (Integer.parseInt(totalwalk.getText()) > 750)
					walkstat = "매우 좋음";

				if (Integer.parseInt(totalwalk.getText()) <= 750 && Integer.parseInt(totalwalk.getText()) > 500)
					walkstat = "좋음";

				if (Integer.parseInt(totalwalk.getText()) <= 500 && Integer.parseInt(totalwalk.getText()) > 250)
					walkstat = "보통";

				if (Integer.parseInt(totalwalk.getText()) <= 250 && Integer.parseInt(totalwalk.getText()) > 0)
					walkstat = "나쁨";

				if (Integer.parseInt(totalwalk.getText()) <= 0)
					walkstat = "매우 나쁨";
				
				arr[5] = walkstat;
				arr[6] = bpstat;
				arr[7] = weightstat;
				
				healthProfile hp = new healthProfile(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),arr[5],arr[6],arr[7]);
				writeConfig(hp);
				
				JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
				

			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(123, 341, 187, 38);
		contentPane.add(btnNewButton_1);

		height.setText(arr[0]);
		weight.setText(arr[1]);
		totalwalk.setText(arr[2]);
		shrink.setText(arr[3]);
		release.setText(arr[4]);

		File configFile = new File("c:/ToolsForMe/" + a.getnickName() + "/" + a.getnickName() + "_config.txt");
		if (!configFile.exists())
			access = false;
		else {

			if (access) {
				try {

					BufferedReader reader = new BufferedReader(new FileReader(configFile));
					String line;
					while ((line = reader.readLine()) != null) {
						color.add(Integer.parseInt(line));

					}
					reader.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

				getContentPane().setBackground(new Color(color.get(0), color.get(1), color.get(2)));

			}
		}

	}
	
	
	public void writeConfig(healthProfile p) {

		
		if (healthConst.exists())
			healthConst.delete();

		try {
			healthConst.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(healthConst));
			writer.append(p.getHeight() + "\n");
			writer.append(p.getWeight() + "\n");
			writer.append(p.getTotalWalk() + "\n");
			writer.append(p.getBp_shrink() + "\n");
			writer.append(p.getBp_release() + "\n");
			writer.append(p.getWalkState() + "\n");
			writer.append(p.getBpState() + "\n");
			writer.append(p.getWeightState() + "\n");

			writer.close();

		} catch (IOException e) {

			System.out.println("건강관리 설정 파일 작성에 실패했습니다.");
			e.printStackTrace();

		}

	}
	
}

	
