public class Classify{
	public static void main(String[] args){
		if(args.length != 1){
			System.err.println("usage: java Classify package-qualified type.");
			return;
		}
		try{
			Class<?> clazz = Class.forName(args[0]);
			if(clazz.isAnnotation()){
				System.out.println("Annotaion");
			}else if(clazz.isEnum()){
				System.out.println("Enum");
			}else if(clazz.isInterface()){
				System.out.println("Interface");
			}else{
				System.out.println("Class");
			}
		}catch(ClassNotFoundException cnfe){
			System.err.println("Can not find the specified type.");
			return;
		}
	}
}