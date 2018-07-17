package moahair.yeon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import moahair.data.dto.BookedDTO;
import moahair.data.dto.BookingTimeDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.MemberDTO;
import moahair.data.dto.StaffDTO;
import moahair.data.dto.TimeDTO;



public class StaffDAO {

	private static StaffDAO instance = new StaffDAO();
	public static StaffDAO getInstance() {
		return instance;
	}
	private StaffDAO() {}

	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mysql");
		return ds.getConnection();
	}

	public ArrayList<TimeDTO> getTime(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ArrayList<TimeDTO> timetable = new ArrayList<TimeDTO>();
		TimeDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from my_time limit ?,?");
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new TimeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setAthirty(rs.getString("athirty"));
				timetable.add(dto);
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
	public int getTime1(String time) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int x=0;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select num from my_time where athirty = ?");
			pstmt.setString(1, time);
			
			rs = pstmt.executeQuery();
			if(rs.next()) 
				x=rs.getInt(1);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}

		}
		return x;
			
		
	}
	public List<String> getTime2(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<String> timetable2 = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select athirty from my_time limit ?,?");
			pstmt.setInt(1, start-1);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				timetable2 = new ArrayList<String>();
				do{
					timetable2.add(rs.getString("athirty"));
				}while(rs.next());
			}
		} catch(Exception ex) {
		     ex.printStackTrace();
	 } finally {
		 if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		 if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	 }
	
	return timetable2;
		
	}

	public void insertSchedule(StaffDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="";
		try {
			conn = getConnection();
			
			sql="insert into staff(s_name,s_title,s_profile,s_background,"
					+ " s_holiday, s_annualleave, s_open, s_close) values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, dto.getS_name());
			pstmt.setString(2, dto.getS_title());
			pstmt.setString(3, dto.getS_profile());
			pstmt.setString(4, dto.getS_background());
			pstmt.setString(5, dto.getS_holiday());
			pstmt.setString(6, dto.getS_annualleave());
			pstmt.setInt(7, dto.getS_open());
			pstmt.setInt(8, dto.getS_close());

			pstmt.executeUpdate();

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

	}

	public StaffDTO getSchedule(String name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		StaffDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select s_num, s_bs_num, s_title, s_profile, s_background, s_holiday, "
					+ "s_annualleave, s_open, s_close, s_condition from staff where s_name=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new StaffDTO();
				dto.setS_num(rs.getInt("s_num"));
				dto.setS_bs_num(rs.getInt("s_bs_num"));
				dto.setS_title(rs.getString("s_title"));
				dto.setS_profile(rs.getString("s_profile"));
				dto.setS_background(rs.getString("s_background"));
				dto.setS_holiday(rs.getString("s_holiday"));
				dto.setS_annualleave(rs.getString("s_annualleave"));
				dto.setS_open(rs.getInt("s_open"));
				dto.setS_close(rs.getInt("s_close"));
				dto.setS_condition(rs.getInt("s_condition"));


			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return dto;
	}

	public void insertBooked(BookedDTO dto) {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql="";
	try {
		conn = getConnection();

		
		sql="insert into booked(bk_s_num, bk_s_name, bk_i_num, bk_i_name, bk_bs_num, bk_m_id, bk_m_num,"
				+ "bk_date, bk_time, bk_price, bk_i_option, bk_i_option_sel1, bk_i_option_sel2, bk_pay_date, bk_i_duration) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, dto.getBk_s_num());
		pstmt.setString(2, dto.getBk_s_name());
		pstmt.setInt(3, dto.getBk_i_num());
		pstmt.setString(4, dto.getBk_i_name());
		pstmt.setInt(5, dto.getBk_bs_num());
		pstmt.setString(6, dto.getBk_m_id());
		pstmt.setInt(7, dto.getBk_m_num());
		pstmt.setString(8, dto.getBk_date());
		pstmt.setString(9, dto.getBk_time());
		pstmt.setInt(10, dto.getBk_price());
		pstmt.setString(11, dto.getBk_i_option());
		pstmt.setString(12, dto.getBk_i_option_sel1());
		pstmt.setString(13, dto.getBk_i_option_sel2());
		pstmt.setString(14, dto.getBk_pay_date());
		pstmt.setInt(15, dto.getBk_i_duration());


		pstmt.executeUpdate();

	} catch(Exception ex) {
		ex.printStackTrace();
	} finally {
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}

}
	
	public String getBookedTime(String name, String today) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String booked = "";
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bk_time from booked where bk_s_name=? and bk_date=?");
			pstmt.setString(1, name);
			pstmt.setString(2, today);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				do{
					booked += rs.getString(1);
				
				}while(rs.next());
			}
		} catch(Exception ex) {
		     ex.printStackTrace();
	 } finally {
		 if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		 if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	 }
	
	return booked;
}
	
	public ItemDTO getitem(int inum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		ItemDTO itemList = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select i_bs_num,i_s_num, i_price,i_name, i_option, i_option_sel1, i_option_sel2, i_duration from item where i_num=?");
			pstmt.setInt(1, inum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			 
				itemList = new ItemDTO();
				itemList.setI_bs_num(rs.getInt("i_bs_num"));
				itemList.setI_s_num(rs.getInt("i_s_num"));
				itemList.setI_price(rs.getInt("i_price"));
				itemList.setI_name(rs.getString("i_name"));
				itemList.setI_option(rs.getString("i_option"));
				itemList.setI_option_sel1(rs.getString("i_option_sel1"));
				itemList.setI_option_sel2(rs.getString("i_option_sel2"));
				itemList.setI_duration(rs.getInt("i_duration"));
				
		}
		}catch (Exception e) {
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
		
		return itemList;
		
	}
	public MemberDTO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		MemberDTO member = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select m_num, m_name,m_address,m_phone,m_email from member where m_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			 
				member = new MemberDTO();
				member.setM_num(rs.getInt("m_num"));
				member.setM_name(rs.getString("m_name"));
				member.setM_address(rs.getString("m_address"));
				member.setM_phone(rs.getString("m_phone"));
				member.setM_email(rs.getString("m_email"));
				
		}
		}catch (Exception e) {
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
		
		return member;
		
		
	}
	
	public BookedDTO getBookedInfo(int bk_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		BookedDTO dto = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select bk_s_num,bk_s_name,bk_i_num,bk_i_name, bk_bs_num, "
					+ "bk_m_id, bk_m_num, bk_date,bk_time, bk_price, bk_pay_date, bk_condition, modify_limit, bk_i_duration from booked where bk_num=?");
			pstmt.setInt(1, bk_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			 
				dto = new BookedDTO();
				dto.setBk_s_num(rs.getInt("bk_s_num"));
				dto.setBk_s_name(rs.getString("bk_s_name"));
				dto.setBk_i_num(rs.getInt("bk_i_num"));
				dto.setBk_i_name(rs.getString("bk_i_name"));
				dto.setBk_bs_num(rs.getInt("bk_bs_num"));
				dto.setBk_m_id(rs.getString("bk_m_id"));
				dto.setBk_m_num(rs.getInt("bk_m_num"));
				dto.setBk_date(rs.getString("bk_date"));
				dto.setBk_time(rs.getString("bk_time"));
				dto.setBk_price(rs.getInt("bk_price"));
				dto.setBk_pay_date(rs.getString("bk_pay_date"));
				dto.setBk_condition(rs.getInt("bk_condition"));
				dto.setModify_limit(rs.getInt("modify_limit"));
				dto.setBk_i_duration(rs.getInt("bk_i_duration"));

			}
		}catch (Exception e) {
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
		
		return dto;
		
		
	}
	
	public void modifyBooking(int a, String b, String c) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update booked set bk_date=?, bk_time=?, modify_limit=modify_limit-1  where bk_num = ?");
		
			pstmt.setString(1, b);
			pstmt.setString(2, c);
			pstmt.setInt(3, a);

			
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}

		}
	}
	public int bkUpdate(int bk_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;

	    int result = 0;
	      
	      String bk_athirtyTime = null;
	      int[] bk_athirtyTimeLast = new int[2];
	      int[] nowTime = new int[2];
	      
	      try {
	         conn = getConnection();
	         
	         String sql = "select bk_date, bk_time, curdate() as nowDate from booked where bk_date <= date(now()) and bk_num = ?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, bk_num);
	         rs = pstmt.executeQuery();
	         
	         // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 bk_num占쏙옙 占쏙옙占쏙옙 占쏙옙짜占싱거놂옙 占쏙옙占쏙옙 占쏙옙짜占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占�
	         if(rs.next()) {
	            String bk_date = rs.getString("bk_date");
	            String timerList = rs.getString("bk_time");
	            String curdate = rs.getString("nowDate");
	            
	            
	            // 占쏙옙占쏙옙 占쏙옙짜占싱몌옙
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
	                     
	                     // 3占시곤옙 占싱놂옙 占쏙옙占쏙옙占쌀곤옙
	                     if(resultTime1 + resultTime2 >= 0 && resultTime1 + resultTime2 <= 180) {
	                    
	                        result = 1;
	                     } 
	                     
	                     // 占쏙옙占쏙옙챨占� 占쏙옙占쏙옙 (占쏙옙占쏙옙 占쌀곤옙)
	                     else if(resultTime1 + resultTime2 < 0) {
	                        result = 2;
	                     } 
	                     
	                     // 占쏙옙占쏙옙 占쏙옙짜占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙
	                     else {
	                    	 result = 0;
	                     }
	                  } 
	               }
	            } 
	            // 占쏙옙占쏙옙 占쏙옙짜占싱몌옙
	            else {
	               result = 2;
	            }
	         }
	         // 占쏙옙占쏙옙 占쏙옙짜占쏙옙 占싣댐옙 占쏙옙占� 占쏙옙占� 占쏙옙占쏙옙
	         else {
	        	 result = 0;
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	         if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	      }
	      return result;
	   
	   }
	public void insertDesigner(StaffDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = getConnection();

			sql = "insert into staff(s_name,s_title,s_profile,s_background,"
					+ " s_holiday, s_open, s_close, s_bs_num,s_profile_org,s_background_org) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getS_name());
			pstmt.setString(2, dto.getS_title());
			pstmt.setString(3, dto.getS_profile());
			pstmt.setString(4, dto.getS_background());
			pstmt.setString(5, dto.getS_holiday());
			
			pstmt.setInt(6, dto.getS_open());
			pstmt.setInt(7, dto.getS_close());
			pstmt.setInt(8, dto.getS_bs_num());
			pstmt.setString(9, dto.getS_profile_org());
			pstmt.setString(10, dto.getS_background_org());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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

	}
}