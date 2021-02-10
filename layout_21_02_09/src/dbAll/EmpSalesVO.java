package dbAll;

public class EmpSalesVO {
	
	private String brdDate;
	private String flightNo;
	private String dep;
	private String des;
	private String fare;
	private String sales;
	private String	avail;


	public EmpSalesVO() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpSalesVO(String brdDate, String flightNo, String dep,String des,
							String fare, String sales,String avail) {
	
		
		this.brdDate = brdDate;
		this.flightNo=flightNo;
		this.dep=dep;
		this.des=des;
		this.fare=fare;
		this.sales=sales;
		this.avail=avail;
			
		
	}

	public String getBrdDate() {
		return brdDate;
	}

	public void setBrdDate(String brdDate) {
		this.brdDate = brdDate;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getAvail() {
		return avail;
	}

	public void setAvail(String avail) {
		this.avail = avail;
	}

	

	
	
	
	
	
	
	
}
