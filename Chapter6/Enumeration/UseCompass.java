public class UseCompass{
	public static void main(String[] args){
		int index = (int)(Math.random() * Compass.values().length);
		switch(Compass.values()[index]){
			case NORTH:
			System.out.println("Heading north.");
			break;
			case EAST:
			System.out.println("Heading east.");
			break;
			case SOUTH:
			System.out.println("Heading south.");
			break;
			case WEST:
			System.out.println("Heading west.");
			break;
			default:
			assert false;
		}
	}
}

enum Compass{
	NORTH, EAST, SOUTH, WEST;
}