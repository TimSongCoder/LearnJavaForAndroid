import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Decompiler{
	public static void main(String[] args){
		if(args.length != 1){
			System.err.println("usage: java Decompiler package-qualified-classname");
		}else{
			try{
				decompileClass(Class.forName(args[0]), 0);
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
		}
	}
	
	static void decompileClass(Class<?> clazz, int indentLevel){
		indent(indentLevel * 3);
		String modifierString = Modifier.toString(clazz.getModifiers());
		System.out.print( modifierString.replace(" interface", "") + " ");  
		// Class.toString() handle the type string, otherwise the annotation type declaration will be weird.
		
		if(clazz.isEnum()){
			System.out.println("enum " + clazz.getName());
		}else if(clazz.isInterface()){
			if(clazz.isAnnotation()){
				System.out.print("@"); // The annotation type source pattern can reference the Override.
			}
			System.out.println(clazz);
		}else{
			System.out.println(clazz);
		}
		
		indent(indentLevel * 3);
		
		System.out.println("{");
		
		Field[] fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			indent(indentLevel * 3);
			System.out.println("   " + fields[i]);
		}
		
		Constructor[] constructors = clazz.getDeclaredConstructors();
		if(constructors.length!=0 && fields.length!=0){
			System.out.println();
		}
		for(int i=0;i<constructors.length;i++){
			indent(indentLevel * 3);
			System.out.println("   " + constructors[i]);
		}
		
		Method[] methods = clazz.getDeclaredMethods();
		if(methods.length !=0 && (fields.length!=0 || constructors.length!=0)){
			System.out.println();
		}
		for(int i=0;i<methods.length;i++){
			indent(indentLevel*3);
			System.out.println("   " + methods[i]);
		}
		
		Method[] allPublicMethods = clazz.getMethods();
		if(allPublicMethods.length!=0 && (fields.length!=0 || constructors.length!=0 || methods.length!=0)){
			System.out.println();
		}
		if(allPublicMethods.length!=0){
			indent(indentLevel*3);
			System.out.println("   ALL PUBLIC METHODS");
			System.out.println();
		}
		for(int i=0;i<allPublicMethods.length;i++){
			indent(indentLevel*3);
			System.out.println("   " + allPublicMethods[i]);
		}
		
		Class<?>[] members = clazz.getDeclaredClasses();
		if(members.length!=0 && (fields.length!=0|| constructors.length!=0 || methods.length!=0 || allPublicMethods.length!=0)){
			System.out.println();
		}
		for(int i=0; i < members.length;i++){
			if(clazz != members[i]){
				decompileClass(members[i], indentLevel + 1);
				if(i != members.length -1){
					System.out.println();
				}
			}
			
		}
		
		indent(indentLevel*3);
		System.out.println("}");
	}
	
	static void indent(int numSpaces){
		for(int i=0;i<numSpaces;i++){
			System.out.print(' ');
		}
	}
}