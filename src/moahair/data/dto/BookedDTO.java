package moahair.data.dto;

public class BookedDTO {
	private int bk_num; //예약 고유번호
	private int bk_s_num;//디자이너 고유번호
	private String bk_s_name;//디자이너 이름
	private int bk_i_num;//상품 고유번호
	private String bk_i_name;//상품 이름
	private int bk_bs_num;//샵 고유번호
	private String bk_m_id;//사용자 아이디
	private int bk_m_num;//사용자 고유번호
	private String bk_date;//예약날짜
	private String bk_time;//예약시간
	private int bk_price;//가격
	private String bk_i_option;//옵션
	private String bk_i_option_sel1;//추가옵션1
	private String bk_i_option_sel2;//추가옵션2
	private String bk_pay_date;//결제 날짜 
	private int bk_condition;
	private int modify_limit;
	private int final_price;
	private int bk_i_duration;
	
	
	

	
	public int getBk_i_duration() {
		return bk_i_duration;
	}
	public void setBk_i_duration(int bk_i_duration) {
		this.bk_i_duration = bk_i_duration;
	}
	public int getBk_condition() {
		return bk_condition;
	}
	public void setBk_condition(int bk_condition) {
		this.bk_condition = bk_condition;
	}
	public int getModify_limit() {
		return modify_limit;
	}
	public void setModify_limit(int modify_limit) {
		this.modify_limit = modify_limit;
	}
	public int getFinal_price() {
		return final_price;
	}
	public void setFinal_price(int final_price) {
		this.final_price = final_price;
	}
	public int getBk_num() {
		return bk_num;
	}
	public void setBk_num(int bk_num) {
		this.bk_num = bk_num;
	}
	public int getBk_s_num() {
		return bk_s_num;
	}
	public void setBk_s_num(int bk_s_num) {
		this.bk_s_num = bk_s_num;
	}
	public int getBk_i_num() {
		return bk_i_num;
	}
	public void setBk_i_num(int bk_i_num) {
		this.bk_i_num = bk_i_num;
	}
	public int getBk_bs_num() {
		return bk_bs_num;
	}
	public void setBk_bs_num(int bk_bs_num) {
		this.bk_bs_num = bk_bs_num;
	}
	public String getBk_m_id() {
		return bk_m_id;
	}
	public void setBk_m_id(String bk_m_id) {
		this.bk_m_id = bk_m_id;
	}
	public int getBk_m_num() {
		return bk_m_num;
	}
	public void setBk_m_num(int bk_m_num) {
		this.bk_m_num = bk_m_num;
	}
	public String getBk_date() {
		return bk_date;
	}
	public void setBk_date(String bk_date) {
		this.bk_date = bk_date;
	}
	public String getBk_time() {
		return bk_time;
	}
	public void setBk_time(String bk_time) {
		this.bk_time = bk_time;
	}
	public String getBk_s_name() {
		return bk_s_name;
	}
	public void setBk_s_name(String bk_s_name) {
		this.bk_s_name = bk_s_name;
	}
	public int getBk_price() {
		return bk_price;
	}
	public void setBk_price(int bk_price) {
		this.bk_price = bk_price;
	}
	public String getBk_i_name() {
		return bk_i_name;
	}
	public void setBk_i_name(String bk_i_name) {
		this.bk_i_name = bk_i_name;
	}
	public String getBk_i_option() {
		return bk_i_option;
	}
	public void setBk_i_option(String bk_i_option) {
		this.bk_i_option = bk_i_option;
	}
	public String getBk_i_option_sel1() {
		return bk_i_option_sel1;
	}
	public void setBk_i_option_sel1(String bk_i_option_sel1) {
		this.bk_i_option_sel1 = bk_i_option_sel1;
	}
	public String getBk_i_option_sel2() {
		return bk_i_option_sel2;
	}
	public void setBk_i_option_sel2(String bk_i_option_sel2) {
		this.bk_i_option_sel2 = bk_i_option_sel2;
	}
	public String getBk_pay_date() {
		return bk_pay_date;
	}
	public void setBk_pay_date(String bk_pay_date) {
		this.bk_pay_date = bk_pay_date;
	}
	

}
