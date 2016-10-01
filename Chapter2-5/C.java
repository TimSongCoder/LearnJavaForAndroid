interface A{
	int X = 1;
	
	void foo();
}

interface B{
	double X = 2.0;
	
	int foo();
}

public interface C extends A, B {
	// Compile error, interface A and B have the same siganature method foo, but different return type.
	// if the method declarations are completely identical, then compile successfully.
}