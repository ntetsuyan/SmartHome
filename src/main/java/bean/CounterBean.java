package bean;

public class CounterBean {
	private int count;
	
	public CounterBean() {
		count = 0;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount() {
		count++;
		return count;
	}

}
