import java.util.HashMap;
import java.lang.ref.SoftReference;

class SoftCache<K, V>{
	private HashMap<K, SoftReference<V>> map;
	
	public SoftCache(){
		map = new HashMap<K, SoftReference<V>>();
	}
	
	public V get(K key){
		SoftReference<V> softRef = map.get(key);
		if(softRef == null){
			return null;
		}else{
			return softRef.get();
		}
	}
	
	public V put(K key, V value){
		SoftReference<V> previousSoftRef = map.put(key, new SoftReference<V>(value));
		if(previousSoftRef == null){
			return null;
		}else{
			V oldValue = previousSoftRef.get();
			previousSoftRef.clear();  // Make the old referent null.
			return oldValue;
		}
	}
	
	public V remove(K key){
		SoftReference<V> softRef = map.remove(key);
		if(softRef == null){
			return null;
		}else{
			V oldValue = softRef.get();
			softRef.clear();
			return oldValue;
		}
	}
}

class Image{
	private byte[] image;
	
	private Image(String name){
		image = new byte[1024*1024*100];
	}
	
	static Image getImage(String name){
		return new Image(name);
	}
}

public class SoftCacheDemo{
	public static void main(String[] args){
		SoftCache<Integer, Image> sc = new SoftCache<Integer, Image>();
		int i = 0;
		while(true){
			System.out.printf("Putting large image %d into soft cache%n", i);
			sc.put(i, Image.getImage("large.png" + i));
			i++;
			int x = (int)(Math.random()*i);  // less than i
			System.out.printf("Acquiring image %d from cache.%n", x);
			Image img = sc.get(x);
			if(img == null){
				System.out.printf("Image %d no longer in cache. Re-caching.%n", x);
				sc.put(x, img = Image.getImage("large.png" + x));
			}
			System.out.printf("Drawing image %d%n", x);
			img = null;  // Remove strong reference to image.
		}
	}
}