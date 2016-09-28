import java.io.FileWriter;
import java.io.IOException;

public class FileLog{
	private final static String LINE_SEPARATOR = System.getProperty("line.separator"); // for portability.
	
	private String destName;
	private FileWriter fw;
	
	public FileLog(String destName){
		this.destName = destName;
	}
	
	public boolean connect(){
		if(destName==null){
			return false;
		}
		try{
			fw = new FileWriter(destName);
		}catch(IOException ioe){
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean disconnect(){
		if(fw==null){
			return false;
		}
		try{
			fw.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean log(String msg){
		if(fw==null){
			return false;
		}
		try{
			fw.write(msg + LINE_SEPARATOR);
		}catch(IOException ioe){
			ioe.printStackTrace();
			return false;
		}
		return true;
	}
}