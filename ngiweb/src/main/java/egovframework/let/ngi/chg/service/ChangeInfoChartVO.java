package egovframework.let.ngi.chg.service;

public class ChangeInfoChartVO {

	private String name;
	private String count;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ChangeInfoChartVO [name=" + name + ", count=" + count + "]";
	}

}
