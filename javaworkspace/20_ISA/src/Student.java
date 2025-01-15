
// student is a human (o) : 상속  

public class Student extends Human {

	int jpScore; //학생만이 갖고 있고, name, age는 물려받았음, 눈에 안보이지만 Human에서

@Override
	void info() {
	super.info();
	System.out.println(jpScore);
}

}
