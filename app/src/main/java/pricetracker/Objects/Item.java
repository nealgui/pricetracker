package pricetracker.Objects;

public class Item{
	private String productName;
	private float productPrice;
	private String storeName;
	private String description;
	private String image;
	private int productStock;
	private String review;
	private int ph1;
	private int ph2;
	private int ph3;
	private int ph4;
	private int ph5;
	private int ph6;

	public int getPh1() {
		return this.ph1;
	}

	public int getPh2() {
		return ph2;
	}


	public int getPh3() {
		return ph3;
	}

	public int getPh4() {
		return ph4;
	}

	public int getPh5() {
		return ph5;
	}

	public int getPh6() {
		return ph6;
	}


	//Getters
	public String getProdName(){return this.productName;}
	public float getProdPrice(){return this.productPrice;}
	public String getStoreName(){return this.storeName;}
	public String getDesc(){return this.description;}
	public String getImage(){return this.image;}
	public int getProdStock(){return this.productStock;}
	public String getReview(){return this.review;}

	//Update values after a change
	public void updatePrice(float newPrice){this.productPrice = newPrice;}
	public void updateStore(String newName){this.storeName = newName;}
	public void updateImage(String newImage){this.image = newImage;}
	public void updateStock(int newStock){this.productStock = newStock;}
	public void updateReview(String newReview){this.review = newReview;}


	public String toString(){
		return "Product: "+productName+" ($"+productPrice+") Store: "+storeName+"\n"+description+"\n"+image;
	}

	public static class Builder {
		private String name;
		private float price;
		private String store;
		private String desc = "";
		private String img = "";
		private int stock = 0;
		private String review = "";
		private int ph1=0;
		private int ph2=0;
		private int ph3=0;
		private int ph4=0;
		private int ph5=0;
		private int ph6=0;
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setPrice(float price) {
			this.price = price;
			return this;
		}

		public Builder setStore(String store) {
			this.store = store;
			return this;
		}

		public Builder setDesc(String desc) {
			this.desc = desc;
			return this;
		}

		public Builder setImg(String img) {
			this.img = img;
			return this;
		}

		public Builder setStock(int stock) {
			this.stock = stock;
			return this;
		}

		public Builder setReview(String review) {
			this.review = review;
			return this;
		}
		public Builder setPh1(int ph1) {
			this.ph1 = ph1;
			return this;

		}

		public Builder setPh2(int ph2) {
			this.ph2 = ph2;
			return this;

		}

		public Builder setPh3(int ph3) {
			this.ph3 = ph3;
			return this;

		}


		public Builder setPh4(int ph4) {
			this.ph4 = ph4;
			return this;

		}


		public Builder setPh5(int ph5) {
			this.ph5 = ph5;
			return this;

		}


		public Builder setPh6(int ph6) {
			this.ph6 = ph6;
			return this;

		}
		public Item createItem() {
			return new Item(this);
		}
	}

	//Constructor
	public Item(Builder builder){
		productName = builder.name;
		storeName = builder.store;
		productPrice = builder.price;
		description = builder.desc;
		image = builder.img;
		productStock = builder.stock;
		review = builder.review;
		ph1 = builder.ph1;
		ph2 = builder.ph2;
		ph3 = builder.ph3;
		ph4 = builder.ph4;
		ph5 = builder.ph5;
		ph6 = builder.ph6;

	}

}//class Item
