
public class Pen extends Product {
	
public Pen() {
	
}
	public Pen(String name, int price) {
//		super(name, price);
		// 부모한테 set, get 다 물려받았기 때문에, 여기서도 setName(name); 등을 쓸 수 있다.
		setName(name);
		setPrice(price);

	}



}
