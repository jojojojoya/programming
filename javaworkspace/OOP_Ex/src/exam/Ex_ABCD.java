package exam;

class A {
}

class B extends  A{
}
class C extends A {
}
class D extends B{
}
class E extends B{
}
class F extends C{
}

public class Ex_ABCD {
	public static void main(String[] args) {
//		B bb = (B) new A();
//		System.out.println(bb);
		
		A a = new B(); // 상위타입에 하위를 담는 것 가능 
		System.out.println(a);
		
		B b = (B) a; // 하위타입에 상위를 담는 것
		System.out.println(b);
		
		C c = (C) a;
		System.out.println(c);
		
	}
}
