import java.io.RandomAccessFile;
import java.io.IOException;

public class PartsDB{
	public final static int PART_NUM_LENGTH = 20; // char-based
	public final static int DESC_LENGTH = 30; // char-based
	public final static int QUANTITY_LENGTH = 1; // int-based
	public final static int COST_LENGTH = 1; // int-based;
	
	private final static int RECORD_LENGTH = PART_NUM_LENGTH*2 + DESC_LENGTH*2 + QUANTITY_LENGTH*4 + COST_LENGTH*4; // UNIT BYTE.
	private RandomAccessFile raf;
	
	public PartsDB(String pathname) throws IOException{
		raf = new RandomAccessFile(pathname, "rw");
	}
	
	public void append(String partNum, String partDesc, int quantity, int unitCost) throws IOException{
		raf.seek(raf.length());
		write(partNum, partDesc, quantity, unitCost);
	}
	
	public void close() throws IOException{
		raf.close();
	}
	
	public int numRecs() throws IOException{
		return (int)raf.length()/RECORD_LENGTH;
	}
	
	public Part select(int recNo) throws IOException{
		if(recNo<0 || recNo >= numRecs()){
			throw new IllegalArgumentException(recNo + " out of range");
		}
		raf.seek(recNo*RECORD_LENGTH);
		return read();
	}
	
	public void update(int recNo, String partNum, String partDesc, int quantity, int unitCost) throws IOException{
		if(recNo<0 || recNo>=numRecs()){
			throw new IllegalArgumentException(recNo + " out of range");
		}
		raf.seek(recNo*RECORD_LENGTH);
		write(partNum, partDesc, quantity, unitCost);
	}
	
	private Part read() throws IOException{
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<PART_NUM_LENGTH;i++){
			sb.append(raf.readChar());
		}
		String partNum = sb.toString().trim(); // remove the possible padding.
		sb.setLength(0);
		for(int i=0;i<DESC_LENGTH;i++){
			sb.append(raf.readChar());
		}
		String partDesc = sb.toString().trim();  // truncate the possible padding.
		int quantity = raf.readInt();
		int unitCost = raf.readInt();
		return new Part(partNum, partDesc, quantity, unitCost);
	}
	
	private void write(String partNum, String partDesc, int quantity, int unitCost) throws IOException{
		StringBuffer sb = new StringBuffer(partNum);
		if(sb.length() > PART_NUM_LENGTH){
			sb.setLength(PART_NUM_LENGTH);
		}else{
			// add some padding
			int paddingLen = PART_NUM_LENGTH-sb.length();
			for(int i=0;i< paddingLen;i++){
				sb.append(" ");
			}
		}
		System.out.print("WritePart: " + sb.toString() + " | ");
		raf.writeChars(sb.toString());
		sb = new StringBuffer(partDesc);
		if(sb.length() > DESC_LENGTH){
			sb.setLength(DESC_LENGTH);
		}else{
			// add some padding
			int descLen = DESC_LENGTH-sb.length();
			for(int i=0;i<descLen;i++){
				sb.append(" ");
			}
		}
		System.out.print(sb.toString() + " | ");
		raf.writeChars(sb.toString());
		System.out.print(quantity + " | ");
		raf.writeInt(quantity);
		System.out.println(unitCost);
		raf.writeInt(unitCost);
	}
	
	public static class Part{
		private String partNum, partDesc;
		private int partQuantity, partUnitCost;
		
		public Part(String partNum, String partDesc, int partQuantity, int partUnitCost){
			this.partNum = partNum;
			this.partDesc = partDesc;
			this.partQuantity = partQuantity;
			this.partUnitCost = partUnitCost;
		}
		
		String getDesc(){
			return partDesc;
		}
		
		String getPartnum(){
			return partNum;
		}
		
		int getQuantity(){
			return partQuantity;
		}
		
		int getUnitCost(){
			return partUnitCost;
		}
	}
}