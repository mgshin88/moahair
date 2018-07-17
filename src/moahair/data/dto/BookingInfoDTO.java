package moahair.data.dto;

public class BookingInfoDTO {

	private String staffName;
	private String itemPrice;
	private String itemName;
	private String itemThumbnail;
	
	
	private int bk_num;
	private int bk_s_num;
	private int bk_i_num;
	private String bk_id;
	private String bk_date;
	private String bk_time;

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

	public String getBk_id() {
		return bk_id;
	}

	public void setBk_id(String bk_id) {
		this.bk_id = bk_id;
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

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemThumbnail() {
		return itemThumbnail;
	}

	public void setItemThumbnail(String itemThumbnail) {
		this.itemThumbnail = itemThumbnail;
	}

}
