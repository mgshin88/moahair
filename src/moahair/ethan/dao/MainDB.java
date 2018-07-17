package moahair.ethan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import moahair.data.dto.BestDTO;
import moahair.data.dto.BookingListDTO;
import moahair.data.dto.FilterDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.ItemListDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.data.dto.My_timeDTO;

public class MainDB {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private static MainDB instance = new MainDB();

	public static MainDB getInstance() {
		return instance;
	}

	private MainDB() {

	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/mysql");
		return ds.getConnection();
	}

	// Weekly Best
	public List getBestArticles(String wTime, String mTime) throws Exception {

		List BestList = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select p.ct, i.i_num, i.i_s_num, i.i_bs_num, i.i_price, i.i_name, i.i_thumbnail, i.i_thumbnail_org, bs.bp, bs.bs_name"
							+ "					from item i"
							+ "					join (select p_i_num,count(p_i_num) ct,p_payment_date ppd from purchased group by p_i_num) p"
							+ "					on p.p_i_num=i.i_num"
							+ "					join (select bs_num bn,bs_profile bp,bs_name from business) bs"
							+ "					on i.i_bs_num=bs.bn" + "					where p.ppd between ? and ?"
							+ "					order by p.ct desc limit 4");

			pstmt.setString(1, wTime);
			pstmt.setString(2, mTime);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				BestList = new ArrayList();
				do {
					BestDTO article = new BestDTO();
					// article.setCount(rs.getInt("count"));
					article.setI_num(rs.getInt("i_num"));
					article.setI_s_num(rs.getInt("i_s_num"));
					article.setI_bs_num(rs.getInt("i_bs_num"));
					article.setI_price(rs.getInt("i_price"));
					article.setI_name(rs.getString("i_name"));
					article.setI_thumbnail(rs.getString("i_thumbnail"));
					article.setI_thumbnail_org(rs.getString("i_thumbnail_org"));
					article.setBs_profile(rs.getString("bp"));
					article.setBs_name(rs.getString("bs_name"));

					BestList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return BestList;
	}

	// ItemList

	public List getItemArticles(int start, int end, String listOpt) throws Exception {

		List ItemList = null;

		try {
			conn = getConnection();

			if (listOpt == null) {
				pstmt = conn.prepareStatement(
						"select i.i_num, i.i_s_num, i.i_bs_num, i.i_price, i.i_name, i.i_regdate, i.i_thumbnail, i.i_thumbnail_org, bs.bp, bs.bs_name"
								+ "					from item i"
								+ "					join (select bs_num bn,bs_profile bp,bs_name from business) bs"
								+ "					on i.i_bs_num=bs.bn"
								+ "					order by i.i_regdate desc limit ?,?");

				pstmt.setInt(1, start - 1);
				pstmt.setInt(2, end);
			} else {
				pstmt = conn.prepareStatement(
						"select i.i_num, i.i_s_num, i.i_bs_num, i.i_price, i.i_name, i.i_regdate, i.i_thumbnail, i.i_thumbnail_org,k.*"
								+ " from item i,(select b.* from"
								+ "				(select concat(i_num,i_price,i_name,i_type,bs.bs_name,ba.ba_state,ba.ba_city,ba.ba_surburb,ba.ba_street,ba.ba_rest) w, j.i_num inum, bs.*"
								+ "				from item j"
								+ "				join (select bs_num bn,bs_profile bp,bs_name "
								+ "					 from business" + "					 ) bs"
								+ "				on j.i_bs_num=bs.bn" + "				join (select * "
								+ "					 from business_address" + "					 )ba"
								+ "			    on bs.bn=ba.ba_bs_num" + "				) b "
								+ "			where b.w like ?" + "			)k " + " where i.i_num=k.inum"
								+ " order by i.i_regdate desc limit ?,?");
				pstmt.setString(1, "%" + listOpt + "%");
				pstmt.setInt(2, start - 1);
				pstmt.setInt(3, end);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				ItemList = new ArrayList(end);
				do {
					ItemListDTO article = new ItemListDTO();
					article.setI_num(rs.getInt("i_num"));
					article.setI_s_num(rs.getInt("i_s_num"));
					article.setI_bs_num(rs.getInt("i_bs_num"));
					article.setI_price(rs.getInt("i_price"));
					article.setI_name(rs.getString("i_name"));
					article.setI_thumbnail(rs.getString("i_thumbnail"));
					article.setBs_profile(rs.getString("bp"));
					article.setBs_name(rs.getString("bs_name"));

					ItemList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return ItemList;
	}

	// Filter
	public List getFilterArticles(int start, int end, String query, String listOpt) throws Exception {

		List FilterList = null;

		try {
			conn = getConnection();
			if (listOpt == null) {
				pstmt = conn.prepareStatement("select * from" + " (select i.*,k.*" + " from item i,(select b.* from"
						+ "				(select concat(i_num,i_price,i_name,i_type,bs.bs_name,ba.ba_state,ba.ba_city,ba.ba_surburb,ba.ba_street,ba.ba_rest) w, j.i_num inum, bs.*, ba.*"
						+ "				from item j" + "				join (select bs_num bn,bs_profile bp,bs_name "
						+ "					 from business" + "					 ) bs"
						+ "				on j.i_bs_num=bs.bn" + "				join (select * "
						+ "					 from business_address" + "					 )ba"
						+ "			    on bs.bn=ba.ba_bs_num" + "				) b " + "			)k "
						+ " where i.i_num=k.inum) li" + " where " + query + " order by li.i_regdate desc limit ?,?");

				pstmt.setInt(1, start - 1);
				pstmt.setInt(2, end);
			} else {
				pstmt = conn.prepareStatement("select * from" + " (select i.*,k.*" + " from item i,(select b.* from"
						+ "				(select concat(i_num,i_price,i_name,i_type,bs.bs_name,ba.ba_state,ba.ba_city,ba.ba_surburb,ba.ba_street,ba.ba_rest) w, j.i_num inum, bs.*, ba.*"
						+ "				from item j" + "				join (select bs_num bn,bs_profile bp,bs_name "
						+ "					 from business" + "					 ) bs"
						+ "				on j.i_bs_num=bs.bn" + "				join (select * "
						+ "					 from business_address" + "					 )ba"
						+ "			    on bs.bn=ba.ba_bs_num" + "				) b " + "			where b.w like ?"
						+ "			)k " + " where i.i_num=k.inum) li" + " where " + query
						+ " order by li.i_regdate desc limit ?,?");

				pstmt.setString(1, "%" + listOpt + "%");
				pstmt.setInt(2, start - 1);
				pstmt.setInt(3, end);
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				FilterList = new ArrayList();
				do {
					FilterDTO article = new FilterDTO();
					article.setI_num(rs.getInt("i_num"));
					article.setI_s_num(rs.getInt("i_s_num"));
					article.setI_bs_num(rs.getInt("i_bs_num"));
					article.setI_price(rs.getInt("i_price"));
					article.setI_name(rs.getString("i_name"));
					article.setI_price(rs.getInt("i_gender"));
					article.setI_thumbnail(rs.getString("i_type"));
					article.setI_thumbnail(rs.getString("i_thumbnail"));
					article.setBs_profile(rs.getString("bp"));
					article.setBs_name(rs.getString("bs_name"));
					article.setBs_name(rs.getString("ba_state"));
					article.setBs_name(rs.getString("ba_city"));
					article.setBs_name(rs.getString("ba_surburb"));
					article.setBs_name(rs.getString("ba_street"));
					article.setBs_name(rs.getString("ba_rest"));

					FilterList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return FilterList;
	}

	public int getArticleCount(String query, String listOpt) throws Exception {
		int x = 0;

		try {
			conn = getConnection();
			if (listOpt == null && query == null) {
				pstmt = conn.prepareStatement("select count(i_num) from item");

			} else if (listOpt != null && query != null) {
				pstmt = conn.prepareStatement("select count(i_num) count,li.* from"
						+ " (select i.i_num,i.i_type,i.i_regdate,i.i_gender,k.*" + " from item i,(select b.* from"
						+ "				(select concat(i_num,i_price,i_name,i_type,bs.bs_name,ba.ba_state,ba.ba_city,ba.ba_surburb,ba.ba_street,ba.ba_rest) w, j.i_num inum, bs.*, ba.*"
						+ "				from item j" + "				join (select bs_num bn,bs_name "
						+ "					 from business" + "					 ) bs"
						+ "				on j.i_bs_num=bs.bn" + "				join (select *"
						+ "					 from business_address" + "					 )ba"
						+ "			    on bs.bn=ba.ba_bs_num" + "				) b " + "			where b.w like ?"
						+ "			)k " + "where i.i_num=k.inum) li" + " where " + query
						+ " order by li.i_regdate desc" + "");

				pstmt.setString(1, "%" + listOpt + "%");

			} else if (listOpt != null && query == null) {
				pstmt = conn.prepareStatement("select count(i_num) count,li.* from"
						+ " (select i.i_num,i.i_type,i.i_regdate,i.i_gender,k.*" + " from item i,(select b.* from"
						+ "				(select concat(i_num,i_price,i_name,i_type,bs.bs_name,ba.ba_state,ba.ba_city,ba.ba_surburb,ba.ba_street,ba.ba_rest) w, j.i_num inum, bs.*, ba.*"
						+ "				from item j" + "				join (select bs_num bn,bs_name "
						+ "					 from business" + "					 ) bs"
						+ "				on j.i_bs_num=bs.bn" + "				join (select *"
						+ "					 from business_address" + "					 )ba"
						+ "			    on bs.bn=ba.ba_bs_num" + "				) b " + "			where b.w like ?"
						+ "			)k " + "where i.i_num=k.inum) li" + " order by li.i_regdate desc" + "");

				pstmt.setString(1, "%" + listOpt + "%");

			} else if (listOpt == null && query != null) {
				pstmt = conn.prepareStatement("select count(i_num) count,li.* from"
						+ " (select i.i_num,i.i_type,i.i_regdate,i.i_gender,k.*" + " from item i,(select b.* from"
						+ "				(select concat(i_num,i_price,i_name,i_type,bs.bs_name,ba.ba_state,ba.ba_city,ba.ba_surburb,ba.ba_street,ba.ba_rest) w, j.i_num inum, bs.*, ba.*"
						+ "				from item j" + "				join (select bs_num bn,bs_name "
						+ "					 from business" + "					 ) bs"
						+ "				on j.i_bs_num=bs.bn" + "				join (select *"
						+ "					 from business_address" + "					 )ba"
						+ "			    on bs.bn=ba.ba_bs_num" + "				) b " + "			)k "
						+ "where i.i_num=k.inum) li" + " where " + query + " order by li.i_regdate desc" + "");
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return x;
	}

	public ArrayList<BookingListDTO> getBookingList(int bs_num) {

		ArrayList<BookingListDTO> BookingList = new ArrayList<BookingListDTO>();
		BookingListDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from " + " (select bs_num, bkn.* " + " from business "
					+ " join (select bk.*,m.m_name from booked bk,(select m_name,m_num from member) m where bk.bk_m_num=m.m_num) bkn "
					+ " on bs_num=bkn.bk_bs_num) bkk " + " where bs_num=? ");

			pstmt.setInt(1, bs_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new BookingListDTO();
				dto.setBk_num(rs.getInt("bk_num"));
				dto.setBk_s_num(rs.getInt("bk_s_num"));
				dto.setBk_s_name(rs.getString("bk_s_name"));
				dto.setBk_i_name(rs.getString("bk_i_name"));
				dto.setBk_date(rs.getString("bk_date"));
				dto.setBk_time(rs.getString("bk_time"));
				dto.setBk_price(rs.getInt("bk_price"));
				dto.setBk_i_option(rs.getString("bk_i_option"));
				dto.setBk_i_option_sel1(rs.getString("bk_i_option_sel1"));
				dto.setBk_i_option_sel2(rs.getString("bk_i_option_sel2"));
				dto.setBk_pay_date(rs.getString("bk_pay_date"));
				dto.setBk_condition(rs.getInt("bk_condition"));
				dto.setM_name(rs.getString("m_name"));
				BookingList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return BookingList;

	}

	public void insertBookingList(BookingListDTO dto) {

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"insert into booked (`bk_num`,`bk_s_num`,`bk_s_name`,`bk_i_num`,`bk_i_name`,`bk_bs_num`,`bk_m_id`,"
					+ " `bk_m_num`,`bk_date`,`bk_time`,`bk_price`,`bk_i_option`,`bk_i_option_sel1`,`bk_i_option_sel2`,`bk_condition`)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, dto.getBk_num());
			pstmt.setInt(2, dto.getBk_s_num());
			pstmt.setString(3, dto.getBk_s_name());
			pstmt.setInt(4, dto.getBk_i_num());
			pstmt.setString(5, dto.getBk_i_name());
			pstmt.setInt(6, dto.getBk_bs_num());
			pstmt.setString(7, dto.getBk_m_id());
			pstmt.setInt(8, dto.getBk_m_num());
			pstmt.setString(9, dto.getBk_date());
			pstmt.setString(10, dto.getBk_time());
			pstmt.setInt(11, dto.getBk_price());
			pstmt.setString(12, dto.getBk_i_option());
			pstmt.setString(13, dto.getBk_i_option_sel1());
			pstmt.setString(14, dto.getBk_i_option_sel2());
			pstmt.setInt(15, dto.getBk_condition());

			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}

	public ArrayList<BookingListDTO> updateBookingList(int bs_num) {

		ArrayList<BookingListDTO> BookingList = new ArrayList<BookingListDTO>();
		BookingListDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from " + " (select bs_num, bkn.* " + " from business "
					+ " join (select bk.*,m.m_name from booked bk,(select m_name,m_num from member) m where bk.bk_m_num=m.m_num) bkn "
					+ " on bs_num=bkn.bk_bs_num) bkk " + " where bs_num=? ");

			pstmt.setInt(1, bs_num);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new BookingListDTO();
				dto.setBk_num(rs.getInt("bk_num"));
				dto.setBk_s_num(rs.getInt("bk_s_num"));
				dto.setBk_s_name(rs.getString("bk_s_name"));
				dto.setBk_i_name(rs.getString("bk_i_name"));
				dto.setBk_date(rs.getString("bk_date"));
				dto.setBk_time(rs.getString("bk_time"));
				dto.setBk_price(rs.getInt("bk_price"));
				dto.setBk_i_option(rs.getString("bk_i_option"));
				dto.setBk_i_option_sel1(rs.getString("bk_i_option_sel1"));
				dto.setBk_i_option_sel2(rs.getString("bk_i_option_sel2"));
				dto.setBk_pay_date(rs.getString("bk_pay_date"));
				dto.setBk_condition(rs.getInt("bk_condition"));
				dto.setM_name(rs.getString("m_name"));
				BookingList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return BookingList;

	}

	public ArrayList<String> getMyTIme() {

		ArrayList<String> TimeList = new ArrayList<String>();
		My_timeDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from my_time m");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				TimeList.add(rs.getString("athirty"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return TimeList;

	}

	public ArrayList getBookingItemList(int bs_num, int count, String s_name, String i_name, int io_option) throws Exception {

		ArrayList ItemList = new ArrayList();
		ItemOptionsDTO io_dto = null;

		try {
			conn = getConnection();
			if (count==0) {
				pstmt = conn.prepareStatement("select s_name from staff where s_bs_num=?");

				pstmt.setInt(1, bs_num);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					s_name = rs.getString("s_name");
					
					ItemList.add(s_name);
				}
			} 
			
			if(count==1) {

				pstmt = conn.prepareStatement("select i_name from item join staff on i_s_num=s_num where s_name=?");

				pstmt.setString(1, s_name);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					i_name = rs.getString("i_name");
					
					ItemList.add(i_name);
				}
			}
			
			if(count==2) {
				pstmt = conn.prepareStatement("select io_name, io_price, io_duration, io_option from item_options "
						+ " join (select i_num,i_option,i_option_sel1,i_option_sel2 from item where i_name='물결머리') i "
						+ " on i.i_num=io_i_num where io_option=?");

				pstmt.setString(1, i_name);
				pstmt.setInt(2, io_option);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					io_dto = new ItemOptionsDTO();
					io_dto.setIo_name(rs.getString("io_name"));
					io_dto.setIo_price(rs.getString("io_price"));
					io_dto.setIo_duration(rs.getString("io_duration"));
			
					ItemList.add(io_dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return ItemList;
	}
	
}
