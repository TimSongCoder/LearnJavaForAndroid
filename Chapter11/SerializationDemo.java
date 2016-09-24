import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class SerializationDemo{
	private static final String FILE_PATH = "employee.dat";
	
	public static void main(String[] args){
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try{
			/*
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			oos = new ObjectOutputStream(fos);
			Employee emp = new Employee("John Doe", 36);
			oos.writeObject(emp);
			oos.writeInt(89757);
			oos.close();
			oos = null;
			*/
			
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ois = new ObjectInputStream(fis);
			Employee emp;
			emp = (Employee)ois.readObject();
			System.out.println("Deserialization: " + emp.getName());
			System.out.println("Deserialization: " + emp.getAge());
			System.out.println("Deserialization: " + emp.getSalary());
			System.out.println("BONUS: " + ois.readInt());
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}finally{
			if(oos != null){
				try{
					oos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(ois !=null){
				try{
					ois.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}