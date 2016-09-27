import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.ObjectInput;

public class SerializationDemo3{
	private static final String FILE_PATH = "emps.dat";
	
	public static void main(String[] args){
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try{
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			oos = new ObjectOutputStream(fos);
			Employee emp = new Employee("John Doe", 36);
			oos.writeObject(emp);
			oos.close();
			oos = null;
			
			FileInputStream fis = new FileInputStream(FILE_PATH);
			ois = new ObjectInputStream(fis);
			emp = (Employee)ois.readObject();
			System.out.println("Deserialization: " + emp.getName());
			System.out.println("Deserialization: " + emp.getAge());
			
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

class Employee implements Externalizable{
	private String name;
	private int age;
	
	// No argument constructor is necessary for deserialization through Externalizable-oriented serialization mechanism.
	public Employee(){
		
	}
	
	public Employee(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAge(){
		return age;
	}
	
	public void writeExternal(ObjectOutput out) throws IOException{
		System.out.println("writeExternal is called.");
		out.writeUTF(name);
		out.writeInt(age);
	}
	
	public void readExternal(ObjectInput in) throws IOException{
		System.out.println("readExternal is called.");
		this.name = in.readUTF();
		this.age = in.readInt();
	}
}