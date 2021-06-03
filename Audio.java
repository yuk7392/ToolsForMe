package ToolsForMe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Audio {
	
	final JFXPanel fxPanel = new JFXPanel(); // javafx 오류 방지를 위한 선언.
	static MediaPlayer mp; // 가비지컬랙터가 가져가므로 static으로 지정.
	File audioDir;
	File audioConst;
	Media m;
	File[] fileList;
	ArrayList<File> audioList = new ArrayList<File>();
	int audioCount = 0;
	int currentIndex = 0;
	double volume = 1;
	
	
	public Audio(User u) {
		
	audioDir = new File("c:/ToolsForMe/"+u.getnickName()+"/audio");
	audioConst = new File("c:/ToolsForMe/"+u.getnickName()+"/audio/info.txt");
	
	readConfig();
	
	if(!audioDir.exists()) audioDir.mkdir();
	if(!audioConst.exists())
		try {
			audioConst.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	fileList = audioDir.listFiles();
	
	
	for(File i : fileList) 
	if(i.isFile() && i.getName().substring(i.getName().lastIndexOf(".")+1).equals("mp3")) {
	audioCount ++;
	audioList.add(i);
	}
	
	if(audioCount != 0) 
	setPlayMp3(audioList.get(0).toURI().toString());
	
	}
	
	
	
	public void stop() {
		
		mp.stop();
		
	}
	
	public void pause() {
		
		mp.pause();
	}
	
	public String getTitle() {
		
		return audioList.get(currentIndex).getName();
		
	}
	
	public boolean continueable() {
		
		if(audioCount == 0)
		return false;
		else
		return true;
		
	}
	

	
	public void playNext() {
		
		if(currentIndex != audioCount-1) {
		currentIndex ++;
		mp.stop();
		setPlayMp3(audioList.get(currentIndex).toURI().toString());
		play();
		}
		
		else 
		{
			
		currentIndex = 0;
		mp.stop();
		setPlayMp3(audioList.get(currentIndex).toURI().toString());
		play();
		}
		
		
	}
	

	public void playPrev() {
		
		if(currentIndex != 0) {
		currentIndex --;
		mp.stop();
		setPlayMp3(audioList.get(currentIndex).toURI().toString());
		play();
		}
		
		else {
	    mp.stop();	
	    currentIndex = audioCount - 1;
	    setPlayMp3(audioList.get(currentIndex).toURI().toString());
	    play();
	    	
	    }
		
		
	}
	
	
	
	public int findIndexByName(String n) {
		for(int i=0;i<audioList.size();i++)
		if(audioList.get(i).getName().equals(n))
		return i;
		return -1;
		
	}
	
	public void setPlayMp3(String path) {
		m = new Media(path);
		mp = new MediaPlayer(m);
	}
	
	
	
	public void play() {
	mp.setAutoPlay(true);
	mp.setVolume(volume);
	mp.play();

	}
	
	public int getAudioCount() {
		return audioCount;
	}
	
	public String getAudioPath() {
		
		return audioDir.getPath();
		
	}
	
	public void setVolume(double v) {
		volume = v;
		mp.setVolume(v);
		writeConfig();
		
	}
	
	public double getVolume() {
		
		return volume;
		
	}
	
	
	public String getStatus() {
		
	Status sts = mp.getStatus();
	
	if(sts == Status.UNKNOWN)
	return "알 수 없음";
	
	if(sts == Status.READY)
	return "준비 완료";
	
	if(sts == Status.PAUSED)
	return "일시 정지";
	
	if(sts == Status.PLAYING)
	return "재생 중";
	
	if(sts == Status.STOPPED)
	return "재생 중지";
	
	return "알 수 없는 상태";
	
		
		
	}
	
	public Duration getCurrentTime() {
		
		return mp.getCurrentTime();
		
	}
	
	public Duration getFullTime() {
		
		return mp.getTotalDuration();
		
	}
	
	public void writeConfig() {
		
		if(audioConst.exists()) {
			
			audioConst.delete();
			
		}
		else { try {
			audioConst.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}}
		
		
		
		try {
			
    		BufferedWriter writer = new BufferedWriter(new FileWriter(audioConst));
    		writer.append(volume+"\n");

    		writer.close();
    			
    		}catch(IOException e) {
    			
    			System.out.println("오디오 설정 파일 작성에 실패했습니다.");
    			e.printStackTrace();
    			
    		}		
		
	}	
	
	
public void readConfig() {
		
if(audioConst.exists()) {
	
	BufferedReader reader;
		
			try {
				reader = new BufferedReader(new FileReader(audioConst));
				
		
				String line;
				
				while((line = reader.readLine()) != null) {
				
				volume = Double.parseDouble(line);
			  
			
			}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
		}
}
	
	

}




