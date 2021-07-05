package Admin;

public class Market {
	
	private int Number;
	private String Area;
	private String Category;
	private String MarketName;
	
	
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getMarketName() {
		return MarketName;
	}
	public void setMarketName(String marketName) {
		MarketName = marketName;
	}
	
	
	

	public Market() {
	
	}
	public Market(int number, String area, String category, String marketName) {
		super();
		this.Number = number;
		this.Area = area;
		this.Category = category;
		this.MarketName = marketName;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Market [Number =" + Number + ", Area ="+ Area + ", Category =" + Category + ", MarketName =" + MarketName + "]";
	}
	
	
	
	

}
