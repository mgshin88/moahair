package moahair.jung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import moahair.data.dto.BoardDTO;
import moahair.data.dto.BookedDTO;
import moahair.data.dto.BusinessAddressDTO;
import moahair.data.dto.BusinessDTO;
import moahair.data.dto.BusinessPlusAddressDTO;
import moahair.data.dto.CartDTO;
import moahair.data.dto.ItemBusinessDTO;
import moahair.data.dto.MemWishListDTO;
import moahair.data.dto.MemberDTO;
import moahair.data.dto.PerformanceDTO;
import moahair.data.dto.TimeDTO;
import moahair.data.dto.WishlistDTO;
import moahair.kyu.crypt.BCrypt;
import moahair.kyu.crypt.SHA256;

public class MoaHairDAO {
	private static MoaHairDAO dao = new MoaHairDAO();

	public static MoaHairDAO getInstance() {
		return dao;
	}

	private MoaHairDAO() {
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/mysql");
		return ds.getConnection();
	}


	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;



	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public int select_m_num(String id) throws Exception {

		int m_num=0;

		String id_sql = "select m_num from member where m_id=?";

		conn = getConnection();
		pstmt = conn.prepareStatement(id_sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			m_num = rs.getInt("m_num");
		}

		return m_num;

	}


	public MemberDTO getMember(String id) throws Exception{

		MemberDTO member = null;
		try {
			String sql = "select * from member where m_id = ?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberDTO();
				member.setM_num(rs.getInt("m_num"));
				member.setM_id(rs.getString("m_id"));
				member.setM_name(rs.getString("m_name"));
				member.setM_pw(rs.getString("m_pw"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_address(rs.getString("m_address"));
				member.setM_condition(rs.getInt("m_condition"));
				member.setM_level(rs.getInt("m_level"));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}


		return member;
	}
	
	public ArrayList<MemberDTO> getMembers(int startRow, int endRow) throws Exception{
	      ArrayList list = new ArrayList<>();
	      MemberDTO member = null;
	      try {
	      String sql = "select * from member order by m_num desc limit ?,?";
	      conn = getConnection();
	      
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, startRow-1);
		  pstmt.setInt(2, endRow);
	      rs= pstmt.executeQuery();
	      while(rs.next()) {
	         member = new MemberDTO();
	         member.setM_num(rs.getInt("m_num"));
	         member.setM_id(rs.getString("m_id"));
	         member.setM_name(rs.getString("m_name"));
	         member.setM_pw(rs.getString("m_pw"));
	         member.setM_phone(rs.getString("m_phone"));
	         member.setM_address(rs.getString("m_address"));
	         member.setM_condition(rs.getInt("m_condition"));
	         member.setM_level(rs.getInt("m_level"));
	         member.setM_email(rs.getString("m_email"));
	         member.setM_regdate(rs.getTimestamp("m_regdate"));
	         
	         list.add(member);
	         
	      }
	      }catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	      
	      
	      return list;
	   }

	public void memDelete(String id) throws Exception{
		try {
			conn = getConnection();
			String sql = "update member set m_condition=? where m_id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, id);
			pstmt.executeUpdate();

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}


	}

	public void memUpdate(MemberDTO dto) throws Exception {
		SHA256 sha = SHA256.getInstance();
		try {
			conn = getConnection();

			String orgPass = dto.getM_pw();
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			String sql = "update MEMBER set m_pw=?,m_name=?,m_address=?,m_phone=? "+
					"where m_id=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bcPass);
			pstmt.setString(2, dto.getM_name());
			pstmt.setString(3, dto.getM_address());
			pstmt.setString(4, dto.getM_phone());
			pstmt.setString(5, dto.getM_id());
			pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

	}



	public ArrayList<CartDTO> getCart(String id) {
	      
	      ArrayList<CartDTO> list = new ArrayList<CartDTO>();
	      CartDTO cart = null;
	      ResultSet rs1 = null;
	      try {
	         
	         String id_sql = "select m_num from member where m_id=?";
	         
	         
	         int m_num=0;
	         conn = getConnection();
	         pstmt = conn.prepareStatement(id_sql);
	         pstmt.setString(1, id);
	         rs1 = pstmt.executeQuery();
	         if(rs1.next()) {
	         m_num = rs1.getInt("m_num");
	         }
	         
	         String sql = "select * from cart where c_m_num=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, m_num);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            cart = new CartDTO();
	            cart.setC_bk_num(rs.getInt("c_bk_num"));
	            cart.setC_i_num(rs.getInt("c_i_num"));
	            cart.setC_m_num(rs.getInt("c_m_num"));
	            cart.setC_name(rs.getString("c_name"));
	            cart.setC_num(rs.getInt("c_num"));
	            cart.setC_option(rs.getString("c_option"));
	            cart.setC_price(rs.getInt("c_price"));
	            cart.setC_thumnail(rs.getString("c_thumbnail"));
	            cart.setC_option_sel1(rs.getString("c_option_sel1"));
	            cart.setC_option_sel2(rs.getString("c_option_sel2"));
	            
	            list.add(cart);
	            
	         }
	      }catch(Exception ex) {
	               ex.printStackTrace();
	           } finally {
	              if (rs1 != null) try { rs1.close(); } catch(SQLException ex) {}
	               if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	           }

	      return list;
	      
	   }

	public void InsertBusinessInfo(String id, BusinessDTO dto ) {
	      //占쏙옙占� 占쏙옙占쏙옙絿占� 占쏙옙占쏙옙 占쌍깍옙 
	      ResultSet rs1 = null;
	         try {
	            
	            String id_sql = "select m_num from member where m_id=?";
	            
	            
	            int m_num=0;
	            conn = getConnection();
	            pstmt = conn.prepareStatement(id_sql);
	            pstmt.setString(1, id);
	            rs1 = pstmt.executeQuery();
	            
	            if(rs1.next()) {
	            m_num = rs1.getInt("m_num");
	            pstmt = conn.prepareStatement("update member set m_level = 6 where m_num =?");
	            pstmt.setInt(1, m_num);
	            pstmt.executeUpdate();
	            
	            }
	            
	            
	            
	         conn = getConnection();
	         String sql = "insert into business"
	               + "(bs_m_num, bs_name, bs_number, bs_profile, bs_background, bs_open, bs_close, bs_condition, bs_profile_org, bs_background_org) "
	               + "values(?,?,?,?,?,?,?,?,?,?);";
	         
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(1, m_num);
	         pstmt.setString(2, dto.getBs_name());
	         pstmt.setString(3, dto.getBs_number());
	         pstmt.setString(4, dto.getBs_profile());
	         pstmt.setString(5, dto.getBs_background());
	         pstmt.setInt(6, dto.getBs_open());
	         pstmt.setInt(7, dto.getBs_close());
	         pstmt.setInt(8, 1);
	         pstmt.setString(9, dto.getBs_profile_org());
	         pstmt.setString(10, dto.getBs_background_org());
	         
	         
	         
	         pstmt.executeUpdate();
	         
	         }catch(Exception ex) {
	               ex.printStackTrace();
	           } finally {
	              if (rs1 != null) try { rs1.close(); } catch(SQLException ex) {}
	               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	           }
	         }

	public void InsertBusinessAddress(String id, BusinessAddressDTO adto ) {
		//�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕永욕뜝占� �뜝�룞�삕�뿥諭꾩삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�뙇源띿삕 

		ResultSet rs1 = null;
		try {

			String id_sql = "select m_num from member where m_id=?";


			int m_num=0;
			conn = getConnection();
			pstmt = conn.prepareStatement(id_sql);
			pstmt.setString(1, id);
			rs1 = pstmt.executeQuery();
			if(rs1.next()) {
				m_num = rs1.getInt("m_num");
			}

			String m_num_sql = "select bs_num from business where bs_m_num = ?";
			int bs_num = 0;
			conn = getConnection();
			pstmt = conn.prepareStatement(m_num_sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bs_num = rs.getInt("bs_num");
			}



			conn = getConnection();
			String sql = "insert into business_address (ba_bs_num, ba_state, ba_city, ba_surburb, ba_street, ba_rest) values "
					+ "(?,?,?,?,?,?);";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, bs_num);
			pstmt.setString(2, adto.getBa_state());
			pstmt.setString(3, adto.getBa_city());
			pstmt.setString(4, adto.getBa_surburb());
			pstmt.setString(5, adto.getBa_street());
			pstmt.setString(6, adto.getBa_rest());

			pstmt.executeUpdate();

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs1 != null) try { rs1.close(); } catch(SQLException ex) {}
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}




	public ArrayList getTime(int start, int end){

		ArrayList timetable = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from my_time limit ?,?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				timetable = new ArrayList();
				do{
					TimeDTO dto = new TimeDTO();
					dto.setNum(rs.getInt("num"));
					dto.setAthirty(rs.getString("athirty"));
					timetable.add(dto);
				}while(rs.next());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return timetable;

	}

	public ArrayList getTime(){
        
  	  ArrayList timetable = null;
        try {
           conn = getConnection();
           pstmt = conn.prepareStatement("select * from my_time");
           
           rs = pstmt.executeQuery();
           
           if(rs.next()) {
              timetable = new ArrayList();
              do{
              	TimeDTO dto = new TimeDTO();
              	dto.setNum(rs.getInt("num"));
              	dto.setAthirty(rs.getString("athirty"));
              	timetable.add(dto);
              }while(rs.next());
           }
        } catch(Exception ex) {
             ex.printStackTrace();
      } finally {
      	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
         if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
         if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }
     
     return timetable;
        
     }

	public ArrayList<MemWishListDTO> getwishs(String id) {

		ArrayList<MemWishListDTO> mdto=null;
		MemWishListDTO mem_wish_dto = null;
		WishlistDTO wish_dto = null;
		int m_num = 0;
		ResultSet rs1 = null;


		try {

			String get_m_num_sql = "select m_num from member where m_id=?";
			conn = getConnection();
			pstmt = conn.prepareStatement(get_m_num_sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				m_num = rs.getInt("m_num");

			}


			String selectallfromwishlsit="select * from wishlist where w_m_num = ? order by w_bs_num, w_s_num, w_i_num";
			pstmt = conn.prepareStatement(selectallfromwishlsit);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();


			mdto = new ArrayList<MemWishListDTO>();

			while(rs.next()) {

				wish_dto = new WishlistDTO();
				mem_wish_dto = new MemWishListDTO();
				
				int w_num = rs.getInt("w_num");
				
				mem_wish_dto.setW_num(w_num);
				wish_dto.setW_num(w_num);
				wish_dto.setW_m_num(rs.getInt("w_m_num"));

				int bs_num = rs.getInt("w_bs_num");
				
				//�뜝�룞�삕
				if(bs_num != 0) {
					String sql = "select bs_num, bs_name, bs_profile from business where bs_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, bs_num);

					rs1 = pstmt.executeQuery();
					if(rs1.next()) {
						mem_wish_dto.setShop_num(rs1.getInt("bs_num"));
						mem_wish_dto.setShop_name(rs1.getString("bs_name"));
						mem_wish_dto.setShop_profile(rs1.getString("bs_profile"));
					}
				}

				//�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
				int i_num = rs.getInt("w_i_num"); 

				if(i_num != 0) {

					String sql = "select i_num, i_name, i_thumbnail from item where i_num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, i_num);

					rs1 = pstmt.executeQuery();
					if(rs1.next()) {
						mem_wish_dto.setDesign_num(rs1.getInt("i_num"));
						mem_wish_dto.setDesign_name(rs1.getString("i_name"));
						mem_wish_dto.setDesign_thumnail(rs1.getString("i_thumbnail"));

					}

				}

				//�뜝�룞�삕�뜝�룞�삕�뜝�떛�냲�삕(staff)
				int s_num = rs.getInt("w_s_num");

				if(s_num != 0) {

					String sql = "select s_num, s_name, s_profile from staff where s_num = ?";
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




		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs1 != null) try { rs1.close(); } catch(SQLException ex) {}
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}




		return mdto;

	}
	
	public String selectShopName(int bd_bs_num) {

		String shopname="";
		try {

			String sql = "select bs_name from business where bs_num = ?";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bd_bs_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				shopname = rs.getString("bs_name");
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return shopname;

	}



	public String getMemName(String id) {

		String memname="";

		try {

			String sql = "select m_name from member where m_id = ?";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				memname = rs.getString("m_name");

			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return memname;

	}

	public int getMemLevel(String id) {

		int m_level=-1;

		try {

			String sql = "select m_level from member where m_id = ?";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				m_level = rs.getInt("m_level");

			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return m_level;

	}

	public boolean checkSeller(int bs_num ,String id) {

		boolean result = false;
		int bs_m_num = 0;
		try {

			int m_num = select_m_num(id);


			String sql = "select bs_m_num from business where bs_num = ?";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bs_num);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				bs_m_num = rs.getInt("bs_m_num");
			}

			if(m_num == bs_m_num) {
				result = true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return result;

	}



	public boolean getCondition (String id) {

		boolean result = false;
		int m_condition = 0;
		try {

			select_m_num(id);


			String id_sql = "select m_condition from member where m_id=?";

			conn = getConnection();
			pstmt = conn.prepareStatement(id_sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				m_condition = rs.getInt("m_condition");
			}

			if(m_condition == 1) {
				result = true;
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return result;

	}

	public int getArticleCount(int i_num) throws Exception {
		int x=0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from board where bd_condition =1 and bd_i_num =?");
			pstmt.setInt(1, i_num);
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


	public ArrayList<BookedDTO> PuschasedList(String id) {

		ArrayList<BookedDTO> list = new ArrayList<BookedDTO>();
		BookedDTO dto = null;
		int m_num = 0;

		try {
			m_num = select_m_num(id);
			String sql = "select * from booked where bk_m_num = ? and bk_condition = 1";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new BookedDTO();
				
				dto.setBk_i_name(rs.getString("bk_i_name"));
				dto.setBk_s_name(rs.getString("bk_s_name"));
				dto.setBk_price(rs.getInt("bk_price"));
				dto.setBk_i_option(rs.getString("bk_i_option"));
				dto.setBk_i_option_sel1(rs.getString("bk_i_option_sel1"));
				dto.setBk_i_option_sel2(rs.getString("bk_i_option_sel2"));
				dto.setBk_date(rs.getString("bk_date"));
				dto.setBk_time(rs.getString("bk_time"));
				
				list.add(dto);

			}


		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return list;

	}

	public ArrayList<BookedDTO> getBooking(String id) {

		ArrayList<BookedDTO> list = new ArrayList<BookedDTO>();
		BookedDTO dto = null;
		try {
			int m_num = select_m_num(id);
			
			String sql = "select * from booked where bk_m_num=? and bk_condition=0";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BookedDTO();
				dto.setBk_num(rs.getInt("bk_num"));
				dto.setBk_s_name(rs.getString("bk_s_name"));
				dto.setBk_i_name(rs.getString("bk_i_name"));
				dto.setBk_date(rs.getString("bk_date"));
				dto.setBk_time(rs.getString("bk_time"));
				dto.setBk_price(rs.getInt("bk_price"));
				dto.setBk_i_option(rs.getString("bk_i_option"));
				dto.setBk_i_option_sel1(rs.getString("bk_i_option_sel1"));
				dto.setBk_i_option_sel2(rs.getString("bk_i_option_sel2"));
				
				list.add(dto);

			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return list;

	}

	public void insertQnA(BoardDTO dto) {

		int num =0;
		String sql = "";
		int bd_ref = dto.getBd_ref();
		int bd_level = dto.getBd_re_level();
		try {

			conn = getConnection();

			pstmt = conn.prepareStatement("select max(bd_num) from board");
			rs = pstmt.executeQuery();



			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num = 1;
			}

			sql = "insert into board(bd_writer,bd_subject,bd_contents,bd_date,bd_ref,bd_re_level,bd_bs_num,bd_i_num) "
					+ "values(?,?,?,?,?,?,?,?)";

			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBd_writer());
			pstmt.setString(2, dto.getBd_subject());
			pstmt.setString(3, dto.getBd_contents());
			pstmt.setTimestamp(4, dto.getBd_date());
			pstmt.setInt(5, num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7,dto.getBd_bs_num());
			pstmt.setInt(8,dto.getBd_i_num());
			//condition�뜝�룞�삕 default�뜝�떎�뼲�삕 �뜝�룞�삕�뜝�룞�삕

			pstmt.executeUpdate();

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}


	}

	public void insertQnARef(BoardDTO dto) {

		int num =0;
		String sql = "";
		int bd_ref = dto.getBd_ref();

		try {

			conn = getConnection();
			
			sql = "update board set bd_ref_count=? where bd_ref=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, bd_ref);
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select max(bd_num) from board");
			rs = pstmt.executeQuery();
			
			sql = "insert into board(bd_writer,bd_subject,bd_contents,bd_date,bd_ref,bd_re_level,bd_i_num) "
					+ "values(?,?,?,?,?,?,?)";

			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBd_writer());
			pstmt.setString(2, dto.getBd_subject());
			pstmt.setString(3, dto.getBd_contents());
			pstmt.setTimestamp(4, dto.getBd_date());
			pstmt.setInt(5, bd_ref);
			pstmt.setInt(6, 1);
			pstmt.setInt(7,dto.getBd_i_num());
			//condition�뜝�룞�삕 default�뜝�떎�뼲�삕 �뜝�룞�삕�뜝�룞�삕

			pstmt.executeUpdate();

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}


	}



	public ArrayList<BoardDTO> getQnA(int i_num) {

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select * from board where bd_i_num = ? and bd_condition = 1 order by bd_ref desc ";

		BoardDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBd_num(rs.getInt("bd_num"));
				dto.setBd_bs_num(rs.getInt("bd_bs_num"));
				dto.setBd_writer(rs.getString("bd_writer"));
				dto.setBd_subject(rs.getString("bd_subject"));
				dto.setBd_contents(rs.getString("bd_contents"));
				dto.setBd_date(rs.getTimestamp("bd_date"));
				dto.setBd_ref(rs.getInt("bd_ref"));
				dto.setBd_re_level(rs.getInt("bd_re_level"));
				dto.setBd_i_num(rs.getInt("bd_i_num"));


				list.add(dto);

			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return list;

	}



	public BoardDTO getQnA_one (int bd_num) {

		BoardDTO dto = null;
		String sql = "select * from board where bd_num = ? and bd_condition = 1 order by bd_ref desc ";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bd_num);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBd_num(rs.getInt("bd_num"));
				dto.setBd_bs_num(rs.getInt("bd_bs_num"));
				dto.setBd_writer(rs.getString("bd_writer"));
				dto.setBd_subject(rs.getString("bd_subject"));
				dto.setBd_contents(rs.getString("bd_contents"));
				dto.setBd_date(rs.getTimestamp("bd_date"));
				dto.setBd_ref(rs.getInt("bd_ref"));
				dto.setBd_re_level(rs.getInt("bd_re_level"));
				dto.setBd_i_num(rs.getInt("bd_i_num"));


			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return dto;

	}



	public int getCountQnA(int i_num) {

		int count=0;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from board where bd_i_num = ?");
			pstmt.setInt(1, i_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}


		return count;

	}

	public void QnADelete(int bd_num) {
		int bd_level = 100;
		int bd_ref = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bd_re_level from board where bd_num = ?");
            pstmt.setInt(1, bd_num);
            rs = pstmt.executeQuery();
            if(rs.next()) {
               bd_level = rs.getInt(1);
            }
            
            if(bd_level == 0) {
            	pstmt = conn.prepareStatement("select bd_ref from board where bd_num = ?");
            	pstmt.setInt(1, bd_num);
            	rs = pstmt.executeQuery();
            	if(rs.next()) {
            		bd_ref = rs.getInt(1);
            	}
            	pstmt = conn.prepareStatement("update board set bd_condition=0 where bd_ref = ? ");
            	pstmt.setInt(1, bd_ref);
            	pstmt.executeUpdate();
            }
            
            else if(bd_level == 1) {
            	pstmt = conn.prepareStatement("select bd_ref from board where bd_num = ?");
            	pstmt.setInt(1, bd_num);
            	rs = pstmt.executeQuery();
            	if(rs.next()) {
            		bd_ref = rs.getInt(1);
            	}
            	
               pstmt = conn.prepareStatement("update board set bd_condition=0 where bd_num = ? ");
               pstmt.setInt(1, bd_num);
               pstmt.executeUpdate();
            }
			
			String sql = "update board set bd_ref_count=0 where bd_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bd_ref);
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}


	}

	public void updateQnA(BoardDTO dto, int bd_num) {

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update board set bd_subject = ?, bd_contents = ? where bd_num = ? ");
			pstmt.setString(1, dto.getBd_subject());
			pstmt.setString(2, dto.getBd_contents());
			pstmt.setInt(3, bd_num);
			pstmt.executeUpdate();

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}

	public ArrayList<BoardDTO> getQnA_Mine(String id) {

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select bd_ref from board where bd_writer=? and bd_re_level=0 order by bd_ref desc ";
		ResultSet rs1 = null;
		PreparedStatement pstmt1=null;
		BoardDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
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

	public ArrayList<BusinessPlusAddressDTO> getShop() {
		//�꺏�젙蹂� �떎 媛��졇���꽌 肉뚮젮二쇰젮怨� 留뚮뱾�뼱�넃��寃�
		ArrayList list = new ArrayList<>();
		
		BusinessPlusAddressDTO bpa = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bs.*, ba.* from business bs join business_address ba on bs.bs_num = ba.ba_bs_num ;");
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				bpa = new BusinessPlusAddressDTO();
				
				
				bpa.setBs_num(rs.getInt("bs_num"));
				bpa.setBs_m_num(rs.getInt("bs_m_num"));
				bpa.setBs_name(rs.getString("bs_name"));
				bpa.setBs_number(rs.getString("bs_number"));
				bpa.setBs_profile(rs.getString("bs_profile"));
				bpa.setBs_background(rs.getString("bs_background"));
				bpa.setBs_open(rs.getInt("bs_open"));//////
				bpa.setBs_close(rs.getInt("bs_close"));////////
				bpa.setBs_condition(rs.getInt("bs_condition"));
				bpa.setBs_profile_org(rs.getString("bs_profile_org"));
				bpa.setBs_background_org(rs.getNString("bs_profile_org"));
				bpa.setBa_state(rs.getString("ba_state"));
				bpa.setBa_city(rs.getString("ba_city"));
				bpa.setBa_surburb(rs.getString("ba_surburb"));
				bpa.setBa_street(rs.getString("ba_street"));
				bpa.setBa_rest(rs.getString("ba_rest"));
				
				list.add(bpa);
				
			}
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	
		
		return list;
		
	}
	
	public ArrayList<BusinessPlusAddressDTO> getBusiness(int startRow, int pageSize) {
		
		ArrayList list = new ArrayList<>();
		
		BusinessPlusAddressDTO bpa = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bs.*, ba.* from business bs join business_address ba on bs.bs_num = ba.ba_bs_num order by bs.bs_num desc limit ?,? ;");
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				bpa = new BusinessPlusAddressDTO();
				
				bpa.setBs_num(rs.getInt("bs_num"));
				bpa.setBs_m_num(rs.getInt("bs_m_num"));
				bpa.setBs_name(rs.getString("bs_name"));
				bpa.setBs_number(rs.getString("bs_number"));
				bpa.setBs_profile(rs.getString("bs_profile"));
				bpa.setBs_background(rs.getString("bs_background"));
				bpa.setBs_open(rs.getInt("bs_open"));//////
				bpa.setBs_close(rs.getInt("bs_close"));////////
				bpa.setBs_condition(rs.getInt("bs_condition"));
				bpa.setBs_profile_org(rs.getString("bs_profile_org"));
				bpa.setBs_background_org(rs.getString("bs_profile_org"));
				bpa.setBa_num(rs.getInt("ba_num"));
				bpa.setBa_state(rs.getString("ba_state"));
				bpa.setBa_city(rs.getString("ba_city"));
				bpa.setBa_surburb(rs.getString("ba_surburb"));
				bpa.setBa_street(rs.getString("ba_street"));
				bpa.setBa_rest(rs.getString("ba_rest"));
				
				list.add(bpa);
				
			}
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	
		
		return list;
		
	}
	public int getBusinessCount() throws Exception {
		int x=0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from business");
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
	
	
	   public int getBsnum() {
		      int result = 0;
		      
		      try {
		         conn = getConnection();
		         
		         pstmt = conn.prepareStatement("select bs_num from business order by bs_num desc limit 1;");
		         
		         rs = pstmt.executeQuery();
		         
		         if (rs.next()) {
		            result = rs.getInt(1);
		         }
		      } catch (Exception ex) {
		         ex.printStackTrace();
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
		      System.out.println("dao寃곌낵 i_num"+result);
		      return result;
		   }
	
	
	
	public void shopDelete(int bs_num) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update business set bs_condition=0 where bs_num=?");
			pstmt.setInt(1, bs_num);
			pstmt.executeUpdate();
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		
	}
	
	public BusinessPlusAddressDTO getshopInfo(int bs_num) {
		// �꺏 �젙蹂� �븯�굹留� 諛쏆븘�삤�뒗嫄� 
		BusinessPlusAddressDTO bpa = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bs.*, ba.* from business bs join business_address ba on bs.bs_num = ba.ba_bs_num where bs.bs_num = ?");
			pstmt.setInt(1, bs_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bpa = new BusinessPlusAddressDTO();
				
				bpa.setBs_num(rs.getInt("bs_num"));
				bpa.setBs_m_num(rs.getInt("bs_m_num"));
				bpa.setBs_name(rs.getString("bs_name"));
				bpa.setBs_number(rs.getString("bs_number"));
				bpa.setBs_open(rs.getInt("bs_open"));//////
				bpa.setBs_close(rs.getInt("bs_close"));////////
				bpa.setBs_condition(rs.getInt("bs_condition"));
				bpa.setBa_num(rs.getInt("ba_num"));
				bpa.setBa_state(rs.getString("ba_state"));
				bpa.setBa_city(rs.getString("ba_city"));
				bpa.setBa_surburb(rs.getString("ba_surburb"));
				bpa.setBa_street(rs.getString("ba_street"));
				bpa.setBa_rest(rs.getString("ba_rest"));
				bpa.setBs_profile(rs.getString("bs_profile"));
				bpa.setBs_background(rs.getString("bs_background"));
				bpa.setBs_background_org(rs.getString("bs_background_org"));
				bpa.setBs_profile_org(rs.getString("bs_profile_org"));
			}
			
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		
		
		return bpa;
		
	}
	
	
	public ArrayList seller_total(String time1, String time2) {
		//留ㅼ텧�쁽�솴媛��졇�삤湲�(�뙋留ㅼ옄蹂�)
		ArrayList total_list = new ArrayList<>();
		PerformanceDTO dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bk.bk_bs_num bs_num, bs.bs_name, sum(bk.final_price) total\r\n" + 
					"from booked bk \r\n" + 
					"join business bs on bk.bk_bs_num = bs.bs_num\r\n" + 
					"where bk_condition = 1 and bk.bk_date between ? and ?\r\n" + 
					"group by bk.bk_bs_num;");
			pstmt.setString(1, time1);
			pstmt.setString(2, time2);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new PerformanceDTO();
				
				dto.setBs_name(rs.getString("bs_name"));
				dto.setBs_num(rs.getInt("bs_num"));
				dto.setTotal(rs.getInt("total"));
				
				total_list.add(dto);
			}
			
			
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		
		return total_list;
	}
	
	public ArrayList item_total(String time1, String time2) {
		//留ㅼ텧�쁽�솴媛��졇�삤湲�(�긽�뭹蹂�)
		ArrayList total_list = new ArrayList<>();
		PerformanceDTO dto = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bk.bk_i_num i_num, i.i_name i_name, sum(bk.final_price) total\r\n" + 
					"from booked bk\r\n" + 
					"join item i on bk.bk_i_num = i.i_num\r\n" + 
					"where bk_condition = 1 and bk.bk_date between ? and ?\r\n" + 
					"group by bk.bk_i_num;");
			pstmt.setString(1, time1);
			pstmt.setString(2, time2);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new PerformanceDTO();
				
				dto.setI_name(rs.getString("i_name"));
				dto.setI_num(rs.getInt("i_num"));
				dto.setTotal(rs.getInt("total"));
				
				total_list.add(dto);
			}
			
			
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		
		return total_list;
	}
		
	

	/* ItemQnA Pagination */
	public ArrayList<BoardDTO> getItemQnA(int i_num, int startRow, int endRow) {

		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		String sql = "select * from board where bd_i_num = ? and bd_condition = 1 order by bd_ref desc limit ?,? ";

		BoardDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i_num);
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				dto = new BoardDTO();
				dto.setBd_num(rs.getInt("bd_num"));
				dto.setBd_bs_num(rs.getInt("bd_bs_num"));
				dto.setBd_writer(rs.getString("bd_writer"));
				dto.setBd_subject(rs.getString("bd_subject"));
				dto.setBd_contents(rs.getString("bd_contents"));
				dto.setBd_date(rs.getTimestamp("bd_date"));
				dto.setBd_ref(rs.getInt("bd_ref"));
				dto.setBd_re_level(rs.getInt("bd_re_level"));
				dto.setBd_i_num(rs.getInt("bd_i_num"));
				dto.setBd_ref_count(rs.getInt("bd_ref_count"));


				list.add(dto);

			}

		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

		return list;

	}
	
	public void updateBusinessInfo(BusinessDTO dto, int bs_num) {
		try {
			conn = getConnection();
			String sql = "update business set bs_name=?, bs_number=?, bs_profile=?, "
					+ "bs_profile_org=?, bs_background=?,  bs_background_org=?, "
					+ "bs_open=?, bs_close = ?, bs_condition = ? "
					+ "where bs_num = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getBs_name());
			pstmt.setString(2, dto.getBs_number());
			pstmt.setString(3, dto.getBs_profile());
			pstmt.setString(4, dto.getBs_profile_org());
			pstmt.setString(5, dto.getBs_background());
			pstmt.setString(6, dto.getBs_background_org());
			pstmt.setInt(7, dto.getBs_open());
			pstmt.setInt(8, dto.getBs_close());
			pstmt.setInt(9, dto.getBs_condition());
			pstmt.setInt(10, bs_num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList getItems(int startRow, int endRow) {
		
		ArrayList list = new ArrayList<>();
		ItemBusinessDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select i.* , b.bs_name, b.bs_num from item i join " + 
					"business b on b.bs_num = i.i_bs_num order by i.i_num desc limit ?,?");
			pstmt.setInt(1, startRow -1);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto = new ItemBusinessDTO();
				dto.setI_bs_num(rs.getInt("i_bs_num"));
				dto.setI_condition(rs.getInt("i_condition"));
				dto.setI_contents(rs.getString("i_contents"));
				dto.setI_duration(rs.getInt("i_duration"));
				dto.setI_gender(rs.getInt("i_gender"));
				dto.setI_name(rs.getString("i_name"));
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_option(rs.getString("i_option"));
				dto.setI_option_sel1(rs.getString("i_option_sel1"));
				dto.setI_option_sel2(rs.getString("i_option_sel2"));
				dto.setI_price(rs.getInt("i_price"));
				dto.setI_regdate(rs.getTimestamp("i_regdate"));
				dto.setI_s_num(rs.getInt("i_s_num"));
				dto.setI_thumbnail(rs.getString("i_thumbnail"));
				dto.setI_thumbnail_org(rs.getString("i_thumbnail_org"));
				dto.setI_type(rs.getString("i_type"));
				
				dto.setBs_name(rs.getString("bs_name"));
				dto.setBs_num(rs.getInt("bs_num"));
				
				list.add(dto);
				
			}
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
		
		return list;
		
	}
	
	public ArrayList item_search(String item_search) {
		ArrayList list = new ArrayList<>();
		ItemBusinessDTO dto = null;
		String sql = "'%"+item_search+"%'";
		try {
		conn = getConnection();
		pstmt = conn.prepareStatement("select i.* , b.bs_name, b.bs_num " + 
				"from item i " + 
				"join business b on b.bs_num = i.i_bs_num where i.i_name like "+sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			dto = new ItemBusinessDTO();
			dto.setI_bs_num(rs.getInt("i_bs_num"));
			dto.setI_condition(rs.getInt("i_condition"));
			dto.setI_contents(rs.getString("i_contents"));
			dto.setI_duration(rs.getInt("i_duration"));
			dto.setI_gender(rs.getInt("i_gender"));
			dto.setI_name(rs.getString("i_name"));
			dto.setI_num(rs.getInt("i_num"));
			dto.setI_option(rs.getString("i_option"));
			dto.setI_option_sel1(rs.getString("i_option_sel1"));
			dto.setI_option_sel2(rs.getString("i_option_sel2"));
			dto.setI_price(rs.getInt("i_price"));
			dto.setI_regdate(rs.getTimestamp("i_regdate"));
			dto.setI_s_num(rs.getInt("i_s_num"));
			dto.setI_thumbnail(rs.getString("i_thumbnail"));
			dto.setI_thumbnail_org(rs.getString("i_thumbnail_org"));
			dto.setI_type(rs.getString("i_type"));
			
			dto.setBs_name(rs.getString("bs_name"));
			dto.setBs_num(rs.getInt("bs_num"));
			
			list.add(dto);
			
			
		}
	}catch(Exception ex) {
        ex.printStackTrace();
    } finally {
        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
    }
	
	return list;
	
	}
	
	public String getTime3(int time) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String x= "";
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select athirty from my_time where num = ?");
			pstmt.setInt(1, time);
			
			rs = pstmt.executeQuery();
			if(rs.next()) 
				x=rs.getString(1);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}

		}
		return x;
			
		
	}
	
public ArrayList<BusinessPlusAddressDTO> getBusiness2(String name) {
		
		ArrayList list = new ArrayList<>();
		
		BusinessPlusAddressDTO bpa = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bs.*, ba.* from business bs join business_address ba on bs.bs_num = ba.ba_bs_num where bs_name=?");
			pstmt.setString(1, name);
		
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				bpa = new BusinessPlusAddressDTO();
				
				
				bpa.setBs_num(rs.getInt("bs_num"));
				bpa.setBs_m_num(rs.getInt("bs_m_num"));
				bpa.setBs_name(name);
				bpa.setBs_number(rs.getString("bs_number"));
				bpa.setBs_profile(rs.getString("bs_profile"));
				bpa.setBs_background(rs.getString("bs_background"));
				bpa.setBs_open(rs.getInt("bs_open"));//////
				bpa.setBs_close(rs.getInt("bs_close"));////////
				bpa.setBs_condition(rs.getInt("bs_condition"));
				bpa.setBs_profile_org(rs.getString("bs_profile_org"));
				bpa.setBs_background_org(rs.getNString("bs_profile_org"));
				bpa.setBa_state(rs.getString("ba_state"));
				bpa.setBa_city(rs.getString("ba_city"));
				bpa.setBa_surburb(rs.getString("ba_surburb"));
				bpa.setBa_street(rs.getString("ba_street"));
				bpa.setBa_rest(rs.getString("ba_rest"));
				
				list.add(bpa);
				
			}
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	
		
		return list;
		
	}
}

