package moahair.data.dto;

import java.sql.Timestamp;

public class PerformanceDTO {

	private int bs_num;
	private String bs_name;
	private int total;
	private int i_num;
	private String i_name;
	
	public int getI_num() {
		return i_num;
	}
	public void setI_num(int i_num) {
		this.i_num = i_num;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public int getBs_num() {
		return bs_num;
	}
	public void setBs_num(int bs_num) {
		this.bs_num = bs_num;
	}
	public String getBs_name() {
		return bs_name;
	}
	public void setBs_name(String bs_name) {
		this.bs_name = bs_name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	

	
}
