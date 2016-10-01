class SuperClass {
	int mFieldA;
	String mFieldB;
	
	SuperClass(int a, String b){
		mFieldA = a;
		mFieldB = b;
	}
}

public class SubClass extends SuperClass{
	// 如果子类不在构造方法中通过super()调用父类的有参数构造方法，编译直接报错，父类成员得不到初始化。
}