
public class Product {

		String name;
		int price;
		Company company;
		

public Product() {
	// TODO Auto-generated constructor stub
}


public Product(String name, int price, Company company) {
	super();
	this.name = name;
	this.price = price;
	this.company = company;
}


@Override
public String toString() {
	return "Product [name=" + name + ", price=" + price + ", company=" + company + "]";
}



		}	
