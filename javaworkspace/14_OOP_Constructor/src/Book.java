
public class Book {
	String title;
	int price;
	String publisher;
	boolean stock;

	public Book() { 
		
	} // resource > generate construct using field
	
	
	
	public Book(String title, int price, String publisher, boolean stock) {
		super();
		this.title = title;
		this.price = price;
		this.publisher = publisher;
		this.stock = stock;
	}


	void printInfo() {
		System.out.println(title);
		System.out.println(price);
		System.out.println(publisher);
		System.out.println(stock);
		
	}
	
}
