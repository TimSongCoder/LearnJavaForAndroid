public class SystemDemo{
	public static void main(String[] args){
		String[] propertyNames = {"file.separator", "java.class.path", "java.home", "java.io.tempdir", "java.library.path", "line.separator", "os.arch", "os.name", "path.separator", "user.dir"};
		for(int i=0; i< propertyNames.length; i++){
			System.out.println(propertyNames[i] + " : " + System.getProperty(propertyNames[i]));
		}
	}
}