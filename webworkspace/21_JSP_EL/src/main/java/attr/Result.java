package attr;


//java bean, 
// Vo, DTO, POJO
public class Result {
// 필드, 속성, 멤버변수 
	//결과 화면에서 보여주고 싶은게 뭔지 생각해야함
	
	int price;
	int money;
	int ex;
	String say;


	public Result(int price, int money, int ex, String say) {
		super();
		this.price = price;
		this.money = money;
		this.ex = ex;
		this.say = say;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getEx() {
		return ex;
	}

	public void setEx(int ex) {
		this.ex = ex;
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}
	
	
	}

