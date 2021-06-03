package ToolsForMe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GetUserInfo {
	
	File mainDir = new File("c:/ToolsForMe");
	File userDir;
	File targetFile;
	String[] info = new String[4];
	boolean access = true;
	
	public GetUserInfo(String nickname) {
		
		userDir = new File("c:/ToolsForMe/"+nickname);
		targetFile = new File("c:/ToolsForMe/"+nickname+"/"+nickname+".txt");
		
		if (!userDir.exists()) {
			/* JOptionPane.showMessageDialog(null, nickname+"에 해당하는 폴더가 없습니다."); */access = false;}
		if (!targetFile.exists()) {
			/* JOptionPane.showMessageDialog(null, nickname+"에 해당하는 파일이 없습니다."); */access = false;}
		
		if(access) {
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(targetFile));
			String line;
			int count = -1;
			while((line = reader.readLine()) != null) {
			count ++;	
			info[count] = line;	
			
			}
			reader.close();
		
			
		}catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
		
	}
		
		}
	
	
	public String[] getInfoArr() {
		
		return info;
		
	}	
	
	public boolean isAdvancedUser() {
		
		String[] cpr = getInfoArr();
		
		if(cpr[3] == null)
		return false;
		else
		return true;
		
	}
	
	
	
	
	}
	

