
public class Product {

		private String name;
		private int price;
		void info() {

		System.out.println(name);
		System.out.println(price);
}
		
		public Product() {
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public Product(String name, int price) {
			super();
			this.name = name;
			this.price = price;
		}
}



