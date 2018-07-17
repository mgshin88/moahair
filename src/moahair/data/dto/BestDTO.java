package moahair.data.dto;

public class BestDTO {
private int count; //판매횟수
private int i_num; //아이템 넘버 pk
private int i_s_num; //staff 넘버 fk
private int i_bs_num; //비즈니스 넘버 fk
private int i_price; //가격
private String i_name; //아이템 이름
private String i_thumbnail; //아이템 썸네일
private String i_thumbnail_org; //아이템 썸네일 원본이름
private String bs_profile; //비즈니스 프로필
private String bs_name;


public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
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
public String getI_thumbnail_org() {
	return i_thumbnail_org;
}
public void setI_thumbnail_org(String i_thumbnail_org) {
	this.i_thumbnail_org = i_thumbnail_org;
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
