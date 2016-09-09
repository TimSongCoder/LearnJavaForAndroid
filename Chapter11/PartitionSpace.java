import java.io.File;

public class PartitionSpace{
	public static void main(String[] args){
		File[] roots = File.listRoots();
		if(roots!=null){
			for(File root: roots){
				System.out.println("Partition: " + root);
				System.out.println("Free space on this partition: " + root.getFreeSpace()/1024/1024/1024 + " GB");
				System.out.println("Usable space on this partition: " + root.getUsableSpace()/1024/1024/1024 + " GB");
				System.out.println("Total space on this partition: " + root.getTotalSpace()/1024/1024/1024 + " GB" + "\n");
			}
		}
	}
}