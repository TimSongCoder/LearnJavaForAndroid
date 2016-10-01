public class Image{
	Image(){
		this(null, null);
		System.out.println("Image() called.");
	}
	
	Image(String filename){
		this(filename, null);
		System.out.println("Image(String filename) called.");
	}
	
	Image(String filename, String imageType){
		System.out.println("Image(String filename, String imageType) called.");
		if(filename != null){
			System.out.println("reading " + filename);
			if(imageType != null){
				System.out.println("interpreting " + filename + " as storing a " + imageType + " image.");
			}
		}
	}
}