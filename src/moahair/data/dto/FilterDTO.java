package moahair.data.dto;

import java.sql.Timestamp;

public class FilterDTO {
	private int i_num; //아이템 넘버 pk
	private int i_s_num; //staff 넘버 fk
	private int i_bs_num; //비즈니스 넘버 fk
	private int i_price; //가격
	private int i_gender;
	private String i_type;
	private String i_regdate;
	private String i_name; //아이템 이름
	private String i_thumbnail; //아이템 썸네일
	private String bs_profile; //비즈니스 프로필
	private String bs_name;
	private String ba_state;
	private String ba_city;
	private String ba_surburb;
	private String ba_street;
	private String ba_rest;
	
	
	public int getI_gender() {
		return i_gender;
	}
	public void setI_gender(int i_gender) {
		this.i_gender = i_gender;
	}
	public String getI_type() {
		return i_type;
	}
	public void setI_type(String i_type) {
		this.i_type = i_type;
	}
	public String getBa_state() {
		return ba_state;
	}
	public void setBa_state(String ba_state) {
		this.ba_state = ba_state;
	}
	public String getBa_city() {
		return ba_city;
	}
	public void setBa_city(String ba_city) {
		this.ba_city = ba_city;
	}
	public String getBa_surburb() {
		return ba_surburb;
	}
	public void setBa_surburb(String ba_surburb) {
		this.ba_surburb = ba_surburb;
	}
	public String getBa_street() {
		return ba_street;
	}
	public void setBa_street(String ba_street) {
		this.ba_street = ba_street;
	}
	public String getBa_rest() {
		return ba_rest;
	}
	public void setBa_rest(String ba_rest) {
		this.ba_rest = ba_rest;
	}
	public int getI_num() {
		return i_num;
	}
	public void setI_num(int i_num) {
		this.i_num = i_num;
	}
	public int getI_s_num() {
		return i_s_num;
	}
	public void setI_s_num(int i_s_num) {
		this.i_s_num = i_s_num;
	}
	public int getI_bs_num() {
		return i_bs_num;
	}
	public void setI_bs_num(int i_bs_num) {
		this.i_bs_num = i_bs_num;
	}
	public int getI_price() {
		return i_price;
	}
	public void setI_price(int i_price) {
		this.i_price = i_price;
	}
	public String getI_regdate() {
		return i_regdate;
	}
	public void setI_regdate(String i_regdate) {
		this.i_regdate = i_regdate;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getI_thumbnail() {
		return i_thumbnail;
	}
	public void setI_thumbnail(String i_thumbnail) {
		this.i_thumbnail = i_thumbnail;
	}
	public String getBs_profile() {
		return bs_profile;
	}
	public void setBs_profile(String bs_profile) {
		this.bs_profile = bs_profile;
	}
	public String getBs_name() {
		return bs_name;
	}
	public void setBs_name(String bs_name) {
		this.bs_name = bs_name;
	}
}

