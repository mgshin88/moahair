package moahair.kyu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import moahair.data.dto.BoardDTO;
import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.data.dto.CartDTO;
import moahair.data.dto.ItemBusinessDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.data.dto.MemWishListDTO;
import moahair.data.dto.MemberDTO;
import moahair.data.dto.My_timeDTO;
import moahair.data.dto.StaffDTO;
import moahair.data.dto.WishlistDTO;
import moahair.kyu.crypt.BCrypt;
import moahair.kyu.crypt.SHA256;

public class KyuDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private static KyuDAO dao = new KyuDAO();
	private KyuDAO() {}

	public static KyuDAO getInstance() {
		return dao;
	}

	public static Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		Context env = (Context)ctx.lookup("java:comp/env");
		DataSource ds = (DataSource)env.lookup("jdbc/mysql");
		return ds.getConnection();
	}

	public void insertMember(MemberDTO dto) {
		SHA256 sha = SHA256.getInstance();

		try {
			conn = getConnection();

			String orgPass = dto.getM_pw();
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			String sql = "insert into member values(null, ?, ?, ?, ?, ?, ?, 3, 1, curdate())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, bcPass);
			pstmt.setString(3, dto.getM_name());
			pstmt.setString(4, dto.getM_address());
			pstmt.setString(5, dto.getM_phone());
			pstmt.setString(6, dto.getM_email());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
	}

	public boolean confirmId(String id) 
			throws Exception {
		boolean result = false;
		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select m_id from member where m_id = ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();

			if(rs.next())
				result = true; //해당 아이디 있음

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return result;
	}

	public boolean loginCheck(String id, String pw) {
		boolean result = false;
		SHA256 sha = SHA256.getInstance();
		try {
			conn = getConnection();

			String orgPass = pw;
			String shaPass = sha.getSha256(orgPass.getBytes());

			String sql = "select * from member where m_id=? and m_condition=1";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dbpasswd = rs.getString("m_pw");
				if(BCrypt.checkpw(shaPass, dbpasswd)) {
					result = true;
				} else {
					result = false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}

		}

		return result;
	}

	public ItemBusinessDTO productSelect(int id) {
		ItemBusinessDTO dto = null;
		try {
			conn = getConnection();

			String sql = "select * from item i join business bs "
					+ "on i.i_bs_num = bs.bs_num "
					+ "where i.i_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				
				dto = new ItemBusinessDTO();
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_bs_num(rs.getInt("i_bs_num"));
				dto.setI_s_num(rs.getInt("i_s_num"));
				dto.setI_price(rs.getInt("i_price"));
				dto.setI_name(rs.getString("i_name"));
				dto.setI_thumbnail(rs.getString("i_thumbnail"));
				dto.setI_contents(rs.getString("i_contents"));
				dto.setI_option(rs.getString("i_option"));
				dto.setI_option_sel1(rs.getString("i_option_sel1"));
				dto.setI_option_sel2(rs.getString("i_option_sel2"));
				dto.setI_regdate(rs.getTimestamp("i_regdate"));
				dto.setI_duration(rs.getInt("i_duration"));
				dto.setI_condition(rs.getInt("i_condition"));
				dto.setI_thumbnail_org(rs.getString("i_thumbnail_org"));

				dto.setBs_num(rs.getInt("bs_num"));
				dto.setBs_m_num(rs.getInt("bs_m_num"));
				dto.setBs_name(rs.getString("bs_name"));
				dto.setBs_number(rs.getString("bs_number"));
				dto.setBs_profile(rs.getString("bs_profile"));
				dto.setBs_background(rs.getString("bs_background"));
				dto.setBs_open(rs.getInt("bs_open"));
				dto.setBs_close(rs.getInt("bs_close"));
				dto.setBs_condition(rs.getInt("bs_condition"));
				dto.setBs_profile_org(rs.getString("bs_profile_org"));
				dto.setBs_background_org(rs.getString("bs_background_org"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}

		return dto;
	}

	public ItemOptionsDTO productOptionSelect(int io_i_num, String io_option) {
		ItemOptionsDTO dto = null;
		try {
			conn = getConnection();
			
			String sql = "select * from item_options where io_i_num=? "
					+ "and io_option=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, io_i_num);
			pstmt.setString(2, io_option);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new ItemOptionsDTO();
				
				dto.setIo_i_num(rs.getInt("io_num"));
				dto.setIo_name(rs.getString("io_name"));
				dto.setIo_price(rs.getString("io_price"));
				dto.setIo_duration(rs.getString("io_duration"));
			}
		} catch (Exception e) {
			
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return dto;
	}
	
	public My_timeDTO[] bs_OpenClose(int open, int close, int bs_num) {
		My_timeDTO[] tdto = new My_timeDTO[2];
		tdto[0] = new My_timeDTO();
		tdto[1] = new My_timeDTO();
		try {
			conn = getConnection();

			String sql = "select (select mt.athirty from my_time mt join business bs on "
					+ "mt.num = bs.bs_open where bs.bs_open = ? and bs_num = ? ) as open, "
					+ "(select athirty from my_time mt join business bs on "
					+ "mt.num = bs.bs_close where bs.bs_close = ? and bs_num = ?) as close from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, open);
			pstmt.setInt(2, bs_num);
			pstmt.setInt(3, close);
			pstmt.setInt(4, bs_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				tdto[0].setAthirty(rs.getString("open"));
				tdto[1].setAthirty(rs.getString("close"));
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		return tdto;
	}
	
	public My_timeDTO[] staff_OpenClose(int open, int close, int s_num) {
		My_timeDTO[] tdto = new My_timeDTO[2];
		tdto[0] = new My_timeDTO();
		tdto[1] = new My_timeDTO();
		try {
			conn = getConnection();

			String sql = "select (select mt.athirty from my_time mt join staff s on "
					+ "mt.num = s.s_open where s.s_open = ? and s_num = ?) as open, "
					+ "(select mt.athirty from my_time mt join staff s on "
					+ "mt.num = s.s_close where s.s_close = ? and s_num = ?) as close from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, open);
			pstmt.setInt(2, s_num);
			pstmt.setInt(3, close);
			pstmt.setInt(4, s_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				tdto[0].setAthirty(rs.getString("open"));
				System.out.println(tdto[0].getAthirty());
				tdto[1].setAthirty(rs.getString("close"));
				System.out.println(tdto[1].getAthirty());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		return tdto;
	}


	public int getStaffNum(int i_bs_num, int i_num) {
		int x = 0;
		try {
			conn = getConnection();

			String sql = "select i_s_num from item where i_bs_num = ? and i_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_bs_num);
			pstmt.setInt(2, i_num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				x = rs.getInt("i_s_num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
	}

	public ArrayList<StaffDTO> BusinessDesingerSelect (int bs_num) {
		ArrayList<StaffDTO> list = new ArrayList<StaffDTO>();
		StaffDTO dto = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from staff where s_bs_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bs_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new StaffDTO();
				
				dto.setS_num(rs.getInt("s_num"));
				dto.setS_bs_num(rs.getInt("s_bs_num"));
				dto.setS_name(rs.getString("s_name"));
				dto.setS_title(rs.getString("s_title"));
				dto.setS_profile(rs.getString("s_profile"));
				dto.setS_background(rs.getString("s_background"));
				dto.setS_holiday(rs.getString("s_holiday"));
				dto.setS_annualleave(rs.getString("s_annualleave"));
				dto.setS_open(rs.getInt("s_open"));
				dto.setS_close(rs.getInt("s_close"));
				dto.setS_condition(rs.getInt("s_condition"));

				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		
		}
		
		return list;
		
	}
	
	public ArrayList<StaffDTO> productDesingerSelect (int bs_num, int i_num) {
		ArrayList<StaffDTO> list = new ArrayList<StaffDTO>();
		StaffDTO dto = null;
		try {
			conn = getConnection();

			int x = getStaffNum(bs_num, i_num);

			if(x == -1) {
				String sql = "select * from staff where s_bs_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bs_num);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					dto = new StaffDTO();

					dto.setS_num(rs.getInt("s_num"));
					dto.setS_bs_num(rs.getInt("s_bs_num"));
					dto.setS_name(rs.getString("s_name"));
					dto.setS_title(rs.getString("s_title"));
					dto.setS_profile(rs.getString("s_profile"));
					dto.setS_background(rs.getString("s_background"));
					dto.setS_holiday(rs.getString("s_holiday"));
					dto.setS_annualleave(rs.getString("s_annualleave"));
					dto.setS_open(rs.getInt("s_open"));
					dto.setS_close(rs.getInt("s_close"));
					dto.setS_condition(rs.getInt("s_condition"));

					list.add(dto);
				}
			}
			else {
				String sql = "select * from staff where s_bs_num = ? and s_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bs_num);
				pstmt.setInt(2, x);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto = new StaffDTO();
					
					dto.setS_num(rs.getInt("s_num"));
					dto.setS_bs_num(rs.getInt("s_bs_num"));
					dto.setS_name(rs.getString("s_name"));
					dto.setS_title(rs.getString("s_title"));
					dto.setS_profile(rs.getString("s_profile"));
					dto.setS_background(rs.getString("s_background"));
					dto.setS_holiday(rs.getString("s_holiday"));
					dto.setS_annualleave(rs.getString("s_annualleave"));
					dto.setS_open(rs.getInt("s_open"));
					dto.setS_close(rs.getInt("s_close"));
					dto.setS_condition(rs.getInt("s_condition"));

					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}

		return list;
	}
	
	/* Header Coding (규섭 코딩 추가) */
	public int memberLevel (String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int level = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_level from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				level = rs.getInt("m_level");
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
		
		return level;
	}
	
	/* 장바구니 담기 Coding */
	public int InsertItemCart(String m_id, CartDTO c_dto) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select c_m_num, c_i_num from cart "
					+ "where c_m_num = ? and c_i_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, c_dto.getC_i_num());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
				return result;
			}
			
			String[] c_option = c_dto.getC_option().split(",");
			String[] c_option_sel1 = null;
			String[] c_option_sel2 = null;
			
			
			sql = "insert into cart(c_m_num, c_i_num, "
					+ "c_name, c_price, c_thumbnail, c_option, "
					+ "c_option_sel1, c_option_sel2) values"
					+ "(?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, c_dto.getC_i_num());
			pstmt.setString(3, c_dto.getC_name());
			pstmt.setInt(4, c_dto.getC_price());
			pstmt.setString(5, c_dto.getC_thumnail());
			
			if(!c_option[2].equals("0")) {
				pstmt.setString(6, c_option[0] + "(+" + c_option[1] + "원) " + Integer.parseInt(c_option[2]) + "분 추가" );
			} else {
				pstmt.setString(6, c_option[0] + "(+" + c_option[1] + "원) ");	
			}
			
			if(c_dto.getC_option_sel1() != null) {
				c_option_sel1 = c_dto.getC_option_sel1().split(",");
			
				if(!c_option_sel1[2].equals("0")) {
					pstmt.setString(7, c_option_sel1[0] + "(+" + c_option_sel1[1] + "원) " + Integer.parseInt(c_option_sel1[2]) + "분 추가" );
				} else {
					pstmt.setString(7, c_option_sel1[0] + "(+" + c_option_sel1[1] + "원) ");	
				}
			} else {
				pstmt.setString(7, "");
			}
			
			if(c_dto.getC_option_sel2() != null) {
				c_option_sel2 = c_dto.getC_option_sel2().split(",");			
				if(!c_option_sel2[2].equals("0")) {
					pstmt.setString(8, c_option_sel2[0] + "(+" + c_option_sel2[1] + "원) " + Integer.parseInt(c_option_sel2[2]) + "분 추가" );
				} else {
					pstmt.setString(8, c_option_sel2[0] + "(+" + c_option_sel2[1] + "원) ");	
				}
			} else {
				pstmt.setString(8, "");
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		
		}
		return result;
	}
	
	public BusinessPlusAddressDTO SelectBsInfo (int bs_num) {
		BusinessPlusAddressDTO bpa = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from business bs join business_address ba "
					+ "on bs.bs_num = ba.ba_bs_num where bs.bs_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bs_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bpa = new BusinessPlusAddressDTO();
				bpa.setBs_num(rs.getInt("bs_num"));
				bpa.setBs_name(rs.getString("bs_name"));
				bpa.setBs_profile(rs.getString("bs_profile"));
				bpa.setBs_background(rs.getString("bs_background"));
				bpa.setBs_open(rs.getInt("bs_open"));
				bpa.setBs_close(rs.getInt("bs_close"));
				bpa.setBa_city(rs.getString("ba_city"));
				bpa.setBa_surburb(rs.getString("ba_surburb"));
				bpa.setBa_street(rs.getString("ba_street"));
				bpa.setBa_rest(rs.getString("ba_rest"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		return bpa;
	}
	
	public ArrayList<ItemDTO> SelectBsItem (int bs_num) {
		ItemDTO i_dto = null;
		ArrayList<ItemDTO> list = new ArrayList<ItemDTO>();
		
		try {
			conn = getConnection();
			
			String sql = "select * from item i join business bs on "
					+ "bs.bs_num = i.i_bs_num where bs.bs_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bs_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				i_dto = new ItemDTO();
				
				i_dto.setI_num(rs.getInt("i_num"));
				i_dto.setI_thumbnail(rs.getString("i_thumbnail"));
				i_dto.setI_name(rs.getString("i_name"));
				i_dto.setI_price(rs.getInt("i_price"));
				list.add(i_dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return list;
	}
	
	public StaffDTO SelectStaffInfo(int s_num) {
		StaffDTO s_dto = null;
		try {
			conn = getConnection();
			
			String sql = "select * from staff where s_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				s_dto = new StaffDTO();
				
				s_dto.setS_num(rs.getInt("s_num"));
				s_dto.setS_bs_num(rs.getInt("s_bs_num"));
				s_dto.setS_name(rs.getString("s_name"));
				s_dto.setS_title(rs.getString("s_title"));
				s_dto.setS_profile(rs.getString("s_profile"));
				s_dto.setS_background(rs.getString("s_background"));
				s_dto.setS_holiday(rs.getString("s_holiday"));
				s_dto.setS_annualleave(rs.getString("s_annualleave"));
				s_dto.setS_open(rs.getInt("s_open"));
				s_dto.setS_close(rs.getInt("s_close"));
				s_dto.setS_condition(rs.getInt("s_condition"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return s_dto;
	}
	
	public ArrayList<ItemDTO> SelectStaffItem (int s_num) {
		ItemDTO i_dto = null;
		ArrayList<ItemDTO> list = new ArrayList<ItemDTO>();
		
		try {
			conn = getConnection();
			
			String sql = "select * from item i join staff s on "
					+ "s.s_num = i.i_s_num where s.s_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				i_dto = new ItemDTO();
				
				i_dto.setI_num(rs.getInt("i_num"));
				i_dto.setI_thumbnail(rs.getString("i_thumbnail"));
				i_dto.setI_name(rs.getString("i_name"));
				i_dto.setI_price(rs.getInt("i_price"));
				list.add(i_dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return list;
	}
	
	public int SelectItemWishList(String m_id, int w_i_num) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select w_m_num, w_i_num from wishlist "
					+ "where w_m_num = ? and w_i_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_i_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public int SelectBsWishList(String m_id, int w_bs_num) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select w_m_num, w_bs_num from wishlist "
					+ "where w_m_num = ? and w_bs_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_bs_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public int SelectStaffWishList(String m_id, int w_s_num) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select w_m_num, w_s_num from wishlist "
					+ "where w_m_num = ? and w_s_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_s_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public int InsertItemWishList(String m_id, int w_i_num) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select w_m_num, w_i_num from wishlist "
					+ "where w_m_num = ? and w_i_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_i_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				return result;
			}
			
			sql = "insert into wishlist(w_m_num, w_i_num) "
					+ "values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_i_num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	
	
	public int InsertBsWishList(String m_id, int w_bs_num) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select w_m_num, w_bs_num from wishlist "
					+ "where w_m_num = ? and w_bs_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_bs_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				return result;
			}
			
			sql = "insert into wishlist(w_m_num, w_bs_num) "
					+ "values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_bs_num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public int InsertStaffWishList(String m_id, int w_s_num) {
		int m_num = 0;
		int result = 0;
		try {
			conn = getConnection();
			
			String sql = "select m_num from member where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			sql = "select w_m_num, w_s_num from wishlist "
					+ "where w_m_num = ? and w_s_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_s_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				return result;
			}
			
			sql = "insert into wishlist(w_m_num, w_s_num) "
					+ "values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			pstmt.setInt(2, w_s_num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return result;
	}
	
	public String RandomNum() {
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<=6;i++) {
			int n = (int) (Math.random() * 10);
			buffer.append(n);
		}
		return buffer.toString();
	}
	
	public boolean getIdCheckValue(String m_name, String m_email) {
		try {
			conn = getConnection();
			
			String sql = "select * from member where m_name = ? and m_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_name);
			pstmt.setString(2, m_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return false;
	}
	
	public String getIdCheckAllValue(String m_name, String m_email, String si_cerfity, String ranNum) {
		String id = null;
		try {
			conn = getConnection();
			
			String sql = "select m_id from member where m_name = ? and m_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_name);
			pstmt.setString(2, m_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(si_cerfity.equals(ranNum)) {
					id = rs.getString("m_id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return id;
	}
	
	public boolean getPwCheckValue(String m_id, String m_name, String m_email) {
		try {
			conn = getConnection();
			
			String sql = "select * from member where m_id = ? and m_name = ? and m_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_name);
			pstmt.setString(3, m_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return false;
	}
	
	public boolean getPwCheckAllValue(String m_id, String m_name, String m_email, String si_cerfity, String ranNum) {
		try {
			conn = getConnection();
			
			String sql = "select * from member where m_id = ? and m_name = ? and m_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_name);
			pstmt.setString(3, m_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if (si_cerfity.equals(ranNum)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
		
		return false;
	}
	
	public void memPwUpdate(String m_id, String m_pw) {
		SHA256 sha = SHA256.getInstance();
		
		try {
			conn = getConnection();
			
			String orgPass = m_pw;
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			String sql = "update member set m_pw = ? where m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bcPass);
			pstmt.setString(2, m_id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		} finally {
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		}
	}
	
	public void wishDelete(int w_num) {
		try {
			conn = getConnection();
			
			String sql = "delete from wishlist where w_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, w_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
	}
	
	public ArrayList<MemWishListDTO> wishSelecter(String m_id, int num) {
		ArrayList<MemWishListDTO> mdto=null;
		MemWishListDTO mem_wish_dto = null;
		WishlistDTO wish_dto = null;
		
		ResultSet rs1 = null;
		int m_num = 0;
		
		try {
			mdto = new ArrayList<MemWishListDTO>();
			conn = getConnection();
			
			String id_sql = "select m_num from member where m_id=?";
			pstmt = conn.prepareStatement(id_sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m_num = rs.getInt("m_num");
			}
			
			if (num == 1) {
				String sql = "select * from wishlist where w_m_num = ? order by w_bs_num, w_s_num, w_i_num";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {

					wish_dto = new WishlistDTO();
					mem_wish_dto = new MemWishListDTO();
					
					int w_num = rs.getInt("w_num");
					
					mem_wish_dto.setW_num(w_num);
					wish_dto.setW_num(w_num);
					wish_dto.setW_m_num(rs.getInt("w_m_num"));

					int bs_num = rs.getInt("w_bs_num");
					
					//占쏙옙
					if(bs_num != 0) {
						sql = "select bs_num, bs_name, bs_profile from business where bs_num = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, bs_num);

						rs1 = pstmt.executeQuery();
						if(rs1.next()) {
							mem_wish_dto.setShop_num(rs1.getInt("bs_num"));
							mem_wish_dto.setShop_name(rs1.getString("bs_name"));
							mem_wish_dto.setShop_profile(rs1.getString("bs_profile"));
						}
					}

					//占쏙옙占쏙옙占쏙옙
					int i_num = rs.getInt("w_i_num"); 

					if(i_num != 0) {

						sql = "select i_num, i_name, i_thumbnail from item where i_num = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, i_num);

						rs1 = pstmt.executeQuery();
						if(rs1.next()) {
							mem_wish_dto.setDesign_num(rs1.getInt("i_num"));
							mem_wish_dto.setDesign_name(rs1.getString("i_name"));
							mem_wish_dto.setDesign_thumnail(rs1.getString("i_thumbnail"));

						}

					}

					//占쏙옙占쏙옙占싱놂옙(staff)
					int s_num = rs.getInt("w_s_num");

					if(s_num != 0) {

						sql = "select s_num, s_name, s_profile from staff where s_num = ?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, s_num);

						rs1 = pstmt.executeQuery();
						if(rs1.next()) {
							mem_wish_dto.setDesigner_num(rs1.getInt("s_num"));
							mem_wish_dto.setDesigner_name(rs1.getString("s_name"));
							mem_wish_dto.setDesigner_profile(rs1.getString("s_profile"));

						}

					}
					
					mdto.add(mem_wish_dto);
				}
			}
			
			else if(num == 2) {
				String sql = "select w_num, w_s_num from wishlist "
						+ "where w_m_num = ? and w_s_num is not null";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					wish_dto = new WishlistDTO();
					mem_wish_dto = new MemWishListDTO();

					mem_wish_dto.setW_num(rs.getInt("w_num"));
					int w_s_num = rs.getInt("w_s_num");
					
					sql = "select s_num, s_name, s_profile from staff where s_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, w_s_num);
					rs1 = pstmt.executeQuery();
					if(rs1.next()) {
						mem_wish_dto.setDesigner_num(rs1.getInt("s_num"));
						mem_wish_dto.setDesigner_name(rs1.getString("s_name"));
						mem_wish_dto.setDesigner_profile(rs1.getString("s_profile"));
					}

					mdto.add(mem_wish_dto);
				}
			}
			
			else if(num == 3) {
				String sql = "select w_num, w_bs_num from wishlist "
						+ "where w_m_num = ? and w_bs_num is not null";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					wish_dto = new WishlistDTO();
					mem_wish_dto = new MemWishListDTO();

					mem_wish_dto.setW_num(rs.getInt("w_num"));
					int w_bs_num = rs.getInt("w_bs_num");
					
					sql = "select bs_num, bs_name, bs_profile from business where bs_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, w_bs_num);
					rs1 = pstmt.executeQuery();
					if(rs1.next()) {
						mem_wish_dto.setShop_num(rs1.getInt("bs_num"));
						mem_wish_dto.setShop_name(rs1.getString("bs_name"));
						mem_wish_dto.setShop_profile(rs1.getString("bs_profile"));
					}

					mdto.add(mem_wish_dto);
				}
			}
			
			else if (num == 4) {
				String sql = "select w_num, w_i_num from wishlist "
						+ "where w_m_num = ? and w_i_num is not null";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, m_num);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					wish_dto = new WishlistDTO();
					mem_wish_dto = new MemWishListDTO();
					
					mem_wish_dto.setW_num(rs.getInt("w_num"));
					int w_i_num = rs.getInt("w_i_num");
					
					sql = "select i_num, i_name, i_thumbnail from item where i_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, w_i_num);
					rs1 = pstmt.executeQuery();
					if(rs1.next()) {
						mem_wish_dto.setDesign_num(rs1.getInt("i_num"));
						mem_wish_dto.setDesign_name(rs1.getString("i_name"));
						mem_wish_dto.setDesign_thumnail(rs1.getString("i_thumbnail"));
					}
					
					mdto.add(mem_wish_dto);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs1 != null) try {rs1.close();}catch(SQLException e) {}
			if(rs != null) try {rs.close();}catch(SQLException e) {}
			if(pstmt != null) try {pstmt.close();}catch(SQLException e) {}
			if(conn != null) try {conn.close();}catch(SQLException e) {}
		
		}

		return mdto;
	}
	
	public void cartDelete(int c_num) {
		try {
			conn = getConnection();
			
			String sql = "delete from cart where c_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
	}
	
	
	public int getQnA_MineCount(String id) {
		int count = 0;
		String sql = "select bd_ref from board where bd_writer=? order by bd_ref desc ";
		ResultSet rs1 = null;
		PreparedStatement pstmt1=null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				pstmt1 = conn.prepareStatement("select count(*) from board where bd_ref=? and bd_condition=1 order by bd_ref desc, bd_re_level asc ");
				pstmt1.setInt(1, rs.getInt("bd_ref"));
				rs1 = pstmt1.executeQuery();

				while(rs1.next()) {
					count += rs.getInt(1);
				}
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs1 != null) try { rs1.close(); } catch(SQLException ex) {}
			if (pstmt1 != null) try { pstmt1.close(); } catch(SQLException ex) {}
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return count;

	}
	
	public ArrayList<ItemBusinessDTO> getQnA_ItemName (String m_id) {
		ArrayList<ItemBusinessDTO> bdi_list = new ArrayList<ItemBusinessDTO>();
		ItemBusinessDTO dto = null;
		try {
			conn = getConnection();
			
			String sql = "select distinct i.i_num, i.i_name, bs.bs_name "
					+ "from board bd join item i on bd.bd_i_num = i.i_num "
					+ "join business bs on i.i_bs_num = bs.bs_num "
					+ "where bd.bd_condition =1 and bd.bd_writer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new ItemBusinessDTO();
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_name(rs.getString("i_name"));
				dto.setBs_name(rs.getString("bs_name"));
				
				bdi_list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return bdi_list;
		
	}
	
	public ArrayList<BoardDTO> getQnA_MineSelecter(String id, int i_num) {

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select bd_ref from board where bd_writer=? and bd_i_num=? order by bd_ref desc ";
		ResultSet rs1 = null;
		PreparedStatement pstmt1=null;
		BoardDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setInt(2, i_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				pstmt1 = conn.prepareStatement("select * from board where bd_ref=? and bd_condition=1 order by bd_ref desc, bd_re_level asc ");
				pstmt1.setInt(1, rs.getInt("bd_ref"));
				rs1 = pstmt1.executeQuery();

				while(rs1.next()) {
					dto = new BoardDTO();

					dto.setBd_num(rs1.getInt("bd_num"));
					dto.setBd_bs_num(rs1.getInt("bd_bs_num"));
					dto.setBd_writer(rs1.getString("bd_writer"));
					dto.setBd_subject(rs1.getString("bd_subject"));
					dto.setBd_contents(rs1.getString("bd_contents"));
					dto.setBd_date(rs1.getTimestamp("bd_date"));
					dto.setBd_ref(rs1.getInt("bd_ref"));
					dto.setBd_re_level(rs1.getInt("bd_re_level"));
					dto.setBd_i_num(rs1.getInt("bd_i_num"));

					list.add(dto);
				}
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs1 != null) try { rs1.close(); } catch(SQLException ex) {}
			if (pstmt1 != null) try { pstmt1.close(); } catch(SQLException ex) {}
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return list;

	}
	
	public String getBookingTime(String time) {
		String bk_time = null;
		int timeInt = Integer.parseInt(time);
		try {
			conn = getConnection();
			
			String sql = "select * from my_time where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, timeInt);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bk_time = rs.getString("athirty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return bk_time;
	}
	
	
	
	
	public int bkDelete(int bk_num) {
		int result = 0; // 0이면 정상 취소, 1이면 3시간이내 취소, 2이면 취소 불가
		
		String bk_athirtyTime = null;
		int[] bk_athirtyTimeLast = new int[2];
		int[] nowTime = new int[2];
		
		try {
			conn = getConnection();
			
			String sql = "select bk_date, bk_time, curdate() as nowDate from booked where bk_date <= date(now()) and bk_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bk_num);
			rs = pstmt.executeQuery();
			
			// 현재 예약한 bk_num이 오늘 날짜이거나 오늘 날짜보다 전 날일 경우
			if(rs.next()) {
				String bk_date = rs.getString("bk_date");
				String timerList = rs.getString("bk_time");
				String curdate = rs.getString("nowDate");
				
				
				// 오늘 날짜이면
				if(bk_date.equals(curdate)) {
					String[] time = timerList.split(" ");
					sql = "select * from my_time where num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, time[0]);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						bk_athirtyTime = rs.getString("athirty");
						String[] time2 = bk_athirtyTime.split(":");
						bk_athirtyTimeLast[0] = Integer.parseInt(time2[0]);
						bk_athirtyTimeLast[1] = Integer.parseInt(time2[1]);
						sql = "select hour(now()) as hourTime, minute(now()) as minuteTime";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							String[] time3 = new String[2]; 
							time3[0] = rs.getString("hourTime");
							time3[1] = rs.getString("minuteTime");
							nowTime[0] = Integer.parseInt(time3[0]);
							nowTime[1] = Integer.parseInt(time3[1]);
							
							int resultTime1 = bk_athirtyTimeLast[0] - nowTime[0];
							int resultTime2 = bk_athirtyTimeLast[1] - nowTime[1];
							
							resultTime1 *= 60;
							
							// 3시간 이내 취소
							if(resultTime1 + resultTime2 >= 0 && resultTime1 + resultTime2 <= 180) {
								sql = "update booked set bk_condition=3 where bk_num = ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setInt(1, bk_num);
								pstmt.executeUpdate();
								result = 1;
							} 
							
							// 예약시간 지남 (취소 불가)
							else if(resultTime1 + resultTime2 < 0) {
								result = 2;
							} 
							
							// 오늘 날짜이지만 정상 취소 가능
							else {
								sql = "update booked set bk_condition=2 where bk_num = ?";
								pstmt = conn.prepareStatement(sql);
								pstmt.setInt(1, bk_num);
								pstmt.executeUpdate();
							}
						} 
					}
				} 
				// 지난 날짜이면
				else {
					result = 2;
				}
			}
			// 오늘 날짜가 아닌 경우 취소 가능
			else {
				sql = "update booked set bk_condition=2 where bk_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bk_num);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return result;
	
	}
	
	/* Admin Page DAO */
	public int getMemberCount() throws Exception {
		int x=0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x= rs.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
	
	public void adminMemUpdate(MemberDTO dto) throws Exception {
		SHA256 sha = SHA256.getInstance();
		try {
			conn = getConnection();

			String orgPass = dto.getM_pw();
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			String sql = "update MEMBER set m_pw=?,m_name=?,m_address=?,m_phone=?, m_condition=? "+
					"where m_id=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bcPass);
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_address());
			pstmt.setString(4, dto.getM_phone());
			pstmt.setInt(5, dto.getM_condition());
			pstmt.setString(6, dto.getM_id());
			
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

	}
	
	public void insertManyAdd(String m_id, String m_name) {
		try {
			conn = getConnection();
			
			String sql = "insert into member (m_id, m_name) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}

	public int getItemCount() throws Exception {
		int x=0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from item");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x= rs.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x;
	}
}
