public class SystemDemo{
	public static void main(String[] args){
		String[] propertyNames = {"file.separator", "java.class.path", "java.home", "java.io.tempdir", "java.library.path", "line.separator", "os.arch", "os.name", "path.separator", "user.dir"};
		for(int i=0; i< propertyNames.length; i++){
			System.out.println(propertyNames[i] + " : " + System.getProperty(propertyNames[i]));
		}
		
		String lineSeparator = System.getProperty("line.separator");
		for(int i=0;i<lineSeparator.length();i++){
			char separatorChar = lineSeparator.charAt(i);
			System.out.println("line.separator: " + (int)separatorChar);
		}
	}
}