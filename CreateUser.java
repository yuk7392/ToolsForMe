package ToolsForMe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateUser {
	
	File mainDir = new File("c:/ToolsForMe");
	File userDir;
	File targetFile;
		
	public CreateUser(User p) {
		
		userDir = new File("c:/ToolsForMe/"+p.getnickName());
		targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+".txt");
		
		if(!mainDir.exists()) mainDir.mkdir();
		if(!userDir.exists()) userDir.mkdir();
		if(!targetFile.exists())
		{
			try {
				targetFile.createNewFile();
				
			} catch (IOException e) {
				System.out.println("유저 프로필 파일 생성에 실패했습니다.");
				e.printStackTrace();
			}
		}
		
		
		
		try {
			
		BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
		writer.append(p.getnickName()+"\n");
		writer.append(p.getId()+"\n");
		writer.append(p.getPassword()+"\n");
		writer.close();
			
		}catch(IOException e) {
			
			System.out.println("유저 프로필 파일 작성에 실패했습니다.");
			e.printStackTrace();
			
		}		
	}
	
	
	
	
        public void CreateAdvancedUser(User p) {
			
        	userDir = new File("c:/ToolsForMe/"+p.getnickName());
    		targetFile = new File("c:/ToolsForMe/"+p.getnickName()+"/"+p.getnickName()+".txt");
    		

    		if(targetFile.exists())
    		{
    			targetFile.delete();
    			
    			try {
    				targetFile.createNewFile();
    				
    			} catch (IOException e) {
    				System.out.println("유저 고급 프로필 파일 생성에 실패했습니다.");
    				e.printStackTrace();
    			}
    		}
    		
    		
    		
    		try {
    			
    		BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
    		writer.append(p.getnickName()+"\n");
    		writer.append(p.getId()+"\n");
    		writer.append(p.getPassword()+"\n");
    		writer.append(p.getQuick_password()+"\n");
    		writer.close();
    			
    		}catch(IOException e) {
    			
    			System.out.println("유저 고급 프로필 파일 작성에 실패했습니다.");
    			e.printStackTrace();
    			
    		}		
    	}
		
		
	}
	