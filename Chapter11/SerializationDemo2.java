import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

// Non-serializable Super class
class Employee{
	private String name;
	
	Employee(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name;
	}
}

// A walkaround to solve the non-serializable super class
class SerEmployee implements Serializable{
	private Employee emp;
	private String name;
	
	SerEmployee(String name){
		this.name = name;
		emp = new Employee(name);
	}
	
	private void readObject(ObjectInputStream ois) throws IOException{
		name = ois.readUTF();
		emp = new Employee(name);
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException{
		oos.writeUTF(name);
	}
	
	@Override
	public String toString(){
		return name;
	}
}

public class SerializationDemo2{
	public static void main(String[] args){
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try{
			FileOutputStream fos = new FileOutputStream("employees.dat");
			oos = new ObjectOutputStream(fos);
			SerEmployee se = new SerEmployee("John Doe");
			oos.writeObject(se);
			System.out.println("se object writeten to file.");
			oos.close();
			oos = null;
			
			FileInputStream fis = new FileInputStream("employees.dat");
			ois = new ObjectInputStream(fis);
			se = (SerEmployee)ois.readObject();
			System.out.println("se object read from file.");
			System.out.println(se);
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			if(oos!=null){
				try{
				oos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
			if(ois!=null){
				try{
					ois.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
	}
}