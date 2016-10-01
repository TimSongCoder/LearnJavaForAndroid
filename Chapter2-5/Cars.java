public class Cars {
	public static void main(String[] args){
		int carCounter = Car.counter; // assign the value to carCounter, not refference, fixed 0
		System.out.println("carCounter : " + Car.counter);
		Car myCar = new Car();
		System.out.println("carCounter : " + Car.counter);
		Car yourCar = new Car();
		System.out.println("carCounter : " + Car.counter);
	}
}