package moahair.data.dto;

import java.sql.Timestamp;

public class BoardDTO {
	private int bd_num;
	private int bd_bs_num;
	private String bd_writer;
	private String bd_subject;
	private String bd_contents;
	private Timestamp bd_date;
	private int bd_ref;
	private int bd_re_level;
	private int bd_condition;
	private int bd_i_num;
	private String bd_img;
	private String bd_img_org;
	private int bd_ref_count;
	
	public int getBd_i_num() {
		return bd_i_num;
	}
	
	public void setBd_i_num(int bd_i_num) {
		this.bd_i_num = bd_i_num;
	}
	public String getBd_img() {
		return bd_img;
	}

	public void setBd_img(String bd_img) {
		this.bd_img = bd_img;
	}

	public String getBd_img_org() {
		return bd_img_org;
	}

	public void setBd_img_org(String bd_img_org) {
		this.bd_img_org = bd_img_org;
	}

	public int getBd_num() {
		return bd_num;
	}

	public void setBd_num(int bd_num) {
		this.bd_num = bd_num;
	}

	public int getBd_bs_num() {
		return bd_bs_num;
	}

	public void setBd_bs_num(int bd_bs_num) {
		this.bd_bs_num = bd_bs_num;
	}

	public String getBd_writer() {
		return bd_writer;
	}

	public void setBd_writer(String bd_writer) {
		this.bd_writer = bd_writer;
	}

	public String getBd_subject() {
		return bd_subject;
	}

	public void setBd_subject(String bd_subject) {
		this.bd_subject = bd_subject;
	}

	public String getBd_contents() {
		return bd_contents;
	}

	public void setBd_contents(String bd_contents) {
		this.bd_contents = bd_contents;
	}

	public Timestamp getBd_date() {
		return bd_date;
	}

	public void setBd_date(Timestamp bd_date) {
		this.bd_date = bd_date;
	}

	public int getBd_ref() {
		return bd_ref;
	}

	public void setBd_ref(int bd_ref) {
		this.bd_ref = bd_ref;
	}

	public int getBd_re_level() {
		return bd_re_level;
	}

	public void setBd_re_level(int bd_re_level) {
		this.bd_re_level = bd_re_level;
	}

	public int getBd_condition() {
		return bd_condition;
	}

	public void setBd_condition(int bd_condition) {
		this.bd_condition = bd_condition;
	}

	public int getBd_ref_count() {
		return bd_ref_count;
	}

	public void setBd_ref_count(int bd_ref_count) {
		this.bd_ref_count = bd_ref_count;
	}
	
}
