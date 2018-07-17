package moahair.jjae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import moahair.data.dto.BoardDTO;
import moahair.data.dto.BusinessAddressDTO;
import moahair.data.dto.BusinessDTO;
import moahair.data.dto.ItemDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.data.dto.StaffDTO;
import moahair.data.dto.TimeDTO;
import moahair.kyu.crypt.BCrypt;
import moahair.kyu.crypt.SHA256;

public class JaeDAO {
	private Connection con = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	private static JaeDAO dao = new JaeDAO();

	public JaeDAO() {
	}

	public static JaeDAO getInstance() {
		return dao;
	}

	// 1.2단계 DB에 접속하는 메소드 getConnection
	public static Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		Context env = (Context) ctx.lookup("java:comp/env");
		DataSource ds = (DataSource) env.lookup("jdbc/mysql");
		return ds.getConnection();
	}

	// 상품 등록 insert 메소드
	public void insertItem(ItemDTO dto) {
		try {
			con = getConnection();
			String sql = "insert into item(i_name, i_thumbnail, i_contents, "
					+ "i_option, i_option_sel1, i_option_sel2, i_duration, i_price, i_regdate, "
					+ "i_thumbnail_org, i_s_num, i_bs_num, i_gender, i_type) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, sysdate(), ?, ?, ?, ?, ?);";
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getI_name());
			ps.setString(2, dto.getI_thumbnail());
			ps.setString(3, dto.getI_contents());
			ps.setString(4, dto.getI_option());
			ps.setString(5, dto.getI_option_sel1());
			ps.setString(6, dto.getI_option_sel2());
			ps.setInt(7, dto.getI_duration());
			ps.setInt(8, dto.getI_price());
			ps.setString(9, dto.getI_thumbnail_org());
			ps.setInt(10, dto.getI_s_num());
			ps.setInt(11, dto.getI_bs_num());
			ps.setInt(12, dto.getI_gender());
			ps.setString(13, dto.getI_type());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 판매자가 올린 상품 게시글 수
	public int getItemCount(String id, int bs_num) {
		int count = 0;

		try {
			con = getConnection();

			ps = con.prepareStatement("select count(*) from item where i_bs_num = ? and i_condition = ?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return count;
	}

	// 판매자 상품 리스트를 가져오는 메소드
	public List getItems(int startRow, int pageSize, int bs_num) {
		int m_num = 0;
		List<ItemDTO> list = new ArrayList();
		ItemDTO dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"select * from item where i_bs_num = ? and i_condition = ? order by i_regdate desc limit ?,?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1);
			ps.setInt(3, startRow - 1);
			ps.setInt(4, pageSize);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new ItemDTO();
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_bs_num(rs.getInt("i_bs_num"));
				dto.setI_s_num(rs.getInt("i_s_num"));
				dto.setI_price(rs.getInt("i_price"));
				dto.setI_name(rs.getString("i_name"));
				dto.setI_thumbnail(rs.getString("i_thumbnail"));
				dto.setI_thumbnail_org(rs.getString("i_thumbnail_org"));
				dto.setI_contents(rs.getString("i_contents"));
				dto.setI_option(rs.getString("i_option"));
				dto.setI_option_sel1(rs.getString("i_option_sel1"));
				dto.setI_option_sel2(rs.getString("i_option_sel2"));
				dto.setI_duration(rs.getInt("i_duration"));
				dto.setI_regdate(rs.getTimestamp("i_regdate"));
				list.add(dto);
			}

		} catch (

		Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return list;
	}

	// 한 샵의 디자이너 이름목록, List<StaffDTO> s_num, s_bs_num, s_name만 저장
	public List getStaffName(int bs_num) {

		List<StaffDTO> list = new ArrayList<>();
		StaffDTO dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from staff where s_bs_num = ? and s_condition = ?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new StaffDTO();
				dto.setS_num(rs.getInt("s_num"));
				dto.setS_bs_num(rs.getInt("s_bs_num"));
				dto.setS_name(rs.getString("s_name"));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return list;
	}

	// 게시글 수정시 pageNum인 i_num을 매개변수로받아 해당 게시글을 반환
	public ItemDTO getItem(int num) {
		ItemDTO dto = new ItemDTO();

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from item where i_num = ?");
			ps.setInt(1, num);
			rs = ps.executeQuery();

			if (rs.next()) {
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
				dto.setI_thumbnail_org(rs.getString("i_thumbnail_org"));
				dto.setI_type(rs.getString("i_type"));
				dto.setI_gender(rs.getInt("i_gender"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	// 상품 게시글 수정시 i_num와 io_option을 매개변수로 받아 item_options를 가져옴
	public ItemOptionsDTO getItemOptions(int io_i_num, String io_option) {
		ItemOptionsDTO dto = new ItemOptionsDTO();

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from item_options where io_i_num = ? and io_option = ?");
			ps.setInt(1, io_i_num);
			ps.setString(2, io_option);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setIo_num(rs.getInt("io_num"));
				dto.setIo_i_num(rs.getInt("io_i_num"));
				dto.setIo_name(rs.getString("io_name"));
				dto.setIo_price(rs.getString("io_price"));
				dto.setIo_duration(rs.getString("io_duration"));
				dto.setIo_option(rs.getString("io_option"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	// 상품등록 수정하는 update
	public void updateItem(ItemDTO dto, int i_num) {
		try {
			con = getConnection();
			String sql = "update item set i_s_num = ?, i_price = ?, i_name = ?, i_thumbnail = ?, i_contents = ?, i_option = ?, "
					+ "i_option_sel1 = ?, i_option_sel2 = ?, i_regdate = sysdate(), i_duration = ?, i_thumbnail_org = ?, i_type = ?, i_gender = ? "
					+ "where i_num = ?";
			ps = con.prepareStatement(sql);

			ps.setInt(1, dto.getI_s_num());
			ps.setInt(2, dto.getI_price());
			ps.setString(3, dto.getI_name());
			ps.setString(4, dto.getI_thumbnail());
			ps.setString(5, dto.getI_contents());
			ps.setString(6, dto.getI_option());
			ps.setString(7, dto.getI_option_sel1());
			ps.setString(8, dto.getI_option_sel2());
			ps.setInt(9, dto.getI_duration());
			ps.setString(10, dto.getI_thumbnail_org());
			ps.setString(11, dto.getI_type());
			ps.setInt(12, dto.getI_gender());
			ps.setInt(13, i_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 상품 수정시 item option update
	public void updateItemOptions(ItemOptionsDTO dto, int io_num) {
		try {
			con = getConnection();
			String sql = "update item_options set io_name = ?, io_price = ?, io_duration = ? where io_num = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getIo_name());
			ps.setString(2, dto.getIo_price());
			ps.setString(3, dto.getIo_duration());
			ps.setInt(4, io_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 상품등록글 삭제 (update condition)
	public void deleteItem(int i_num) {
		try {
			con = getConnection();
			String sql = "update item set i_condition = ? where i_num = ?";
			ps = con.prepareStatement(sql);

			ps.setInt(1, 0);
			ps.setInt(2, i_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// time테이블 가져오기
	public ArrayList getTime(int start, int end) {

		ArrayList timetable = null;
		try {
			con = getConnection();
			ps = con.prepareStatement("select * from my_time limit ?,?");
			ps.setInt(1, start - 1);
			ps.setInt(2, end);
			rs = ps.executeQuery();

			timetable = new ArrayList();

			while (rs.next()) {
				TimeDTO dto = new TimeDTO();
				dto.setNum(rs.getInt("num"));
				dto.setAthirty(rs.getString("athirty"));
				timetable.add(dto);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return timetable;
	}

	// business 정보 db에 넣기
	public void insertBusinessInfo(int bs_num, BusinessDTO dto) {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"update business set bs_number = ?, bs_profile = ?,bs_background = ?,bs_open = ?,bs_close = ? ,"
							+ "bs_condition = 1, bs_profile_org = ?, bs_background_org = ? where bs_num = ?");

			ps.setString(1, dto.getBs_number());
			ps.setString(2, dto.getBs_profile());
			ps.setString(3, dto.getBs_background());
			ps.setInt(4, dto.getBs_open());
			ps.setInt(5, dto.getBs_close());
			ps.setString(6, dto.getBs_profile_org());
			ps.setString(7, dto.getBs_background_org());
			ps.setInt(8, bs_num);
			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}

	// business address 정보 db에 넣기
	public void insertBusinessAddress(int bs_num, BusinessAddressDTO dto) {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"insert into business_address (ba_bs_num, ba_state, ba_city, ba_surburb, ba_street, ba_rest) values "
							+ "(?,?,?,?,?,?);");
			ps.setInt(1, bs_num);
			ps.setString(2, dto.getBa_state());
			ps.setString(3, dto.getBa_city());
			ps.setString(4, dto.getBa_surburb());
			ps.setString(5, dto.getBa_street());
			ps.setString(6, dto.getBa_rest());
			ps.executeUpdate();

		} catch (

		Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 사용자 id를 받아 샵고유번호와 상호명을 찾아 map에 담기
	public HashMap<Integer, String> getBusinesses(String id) {
		HashMap<Integer, String> map = new HashMap<>();
		int m_num = 0;

		try {
			con = getConnection();
			ps = con.prepareStatement("select m_num from member where m_id = ? and m_level = ?");
			ps.setString(1, id);
			ps.setInt(2, 6);
			rs = ps.executeQuery();

			if (rs.next()) {
				m_num = rs.getInt(1); // member table에서 m_num 사용자고유번호 추출

				ps = con.prepareStatement("select * from business where bs_m_num = ? and bs_condition >= ?");
				ps.setInt(1, m_num);
				ps.setInt(2, 1);
				rs = ps.executeQuery();

				while (rs.next()) {
					map.put(rs.getInt("bs_num"), rs.getString("bs_name"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return map;
	}

	// 판매자가 내정보를 클릭했을때 처음에 비번 확인
	public int userCheck(String id, String pw) {
		int result = 0;
		SHA256 sha = SHA256.getInstance();
		try {
			con = getConnection();

			String orgPass = pw;
			String shaPass = sha.getSha256(orgPass.getBytes());

			String sql = "select * from member where m_id=? and m_condition=1";
			ps = con.prepareStatement(sql);

			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				String dbpasswd = rs.getString("m_pw");
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					result = 1;
				} else {
					result = -1;
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
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}

		}

		return result;
	}

	// bs_num샵고유번호를 받아 정보를 가져옴
	public BusinessDTO getBusinessInfo(int bs_num) {
		BusinessDTO dto = null;
		try {
			con = getConnection();
			ps = con.prepareStatement("select * from business where bs_num = ?");
			ps.setInt(1, bs_num);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new BusinessDTO();
				dto.setBs_m_num(rs.getInt("bs_m_num"));
				dto.setBs_name(rs.getString("bs_name"));
				dto.setBs_number(rs.getString("bs_number"));
				dto.setBs_profile(rs.getString("bs_profile"));
				dto.setBs_background(rs.getString("bs_background"));
				dto.setBs_holiday(rs.getString("bs_holiday"));
				dto.setBs_open(rs.getInt("bs_open"));
				dto.setBs_close(rs.getInt("bs_close"));
				dto.setBs_profile_org(rs.getString("bs_profile_org"));
				dto.setBs_background_org(rs.getString("bs_background_org"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	// bs_num 샵고유번호를 받아 샵의 주소 정보를 가져옴
	public BusinessAddressDTO getBusinessAdderss(int bs_num) {
		BusinessAddressDTO dto = null;
		try {
			con = getConnection();
			ps = con.prepareStatement("select * from business_address where ba_bs_num = ?");
			ps.setInt(1, bs_num);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new BusinessAddressDTO();
				dto.setBa_num(rs.getInt("ba_num"));
				dto.setBa_state(rs.getString("ba_state"));
				dto.setBa_city(rs.getString("ba_city"));
				dto.setBa_surburb(rs.getString("ba_surburb"));
				dto.setBa_street(rs.getString("ba_street"));
				dto.setBa_rest(rs.getString("ba_rest"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	// business info update
	public void updateBusinessInfo(BusinessDTO dto, int bs_num) {
		try {
			con = getConnection();
			String sql = "update business set bs_profile = ?, "
					+ "bs_profile_org = ?, bs_background = ?,  bs_background_org = ?, " + "bs_open = ?, bs_close = ? "
					+ "where bs_num = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getBs_profile());
			ps.setString(2, dto.getBs_profile_org());
			ps.setString(3, dto.getBs_background());
			ps.setString(4, dto.getBs_background_org());
			ps.setInt(5, dto.getBs_open());
			ps.setInt(6, dto.getBs_close());
			ps.setInt(7, bs_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// business address info update
	public void updateBusinessAddress(BusinessAddressDTO dto, int ba_num) {
		try {
			con = getConnection();
			String sql = "update business_address set ba_state = ?, ba_city = ?,"
					+ "ba_surburb = ?, ba_street = ?, ba_rest = ? " + "where ba_num = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getBa_state());
			ps.setString(2, dto.getBa_city());
			ps.setString(3, dto.getBa_surburb());
			ps.setString(4, dto.getBa_street());
			ps.setString(5, dto.getBa_rest());
			ps.setInt(6, ba_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// business info delete condition = 0으로
	public void deleteBusiness(int bs_num) {
		try {
			con = getConnection();
			String sql = "update business set bs_condition = 0 where bs_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bs_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// business address delete 칼럼삭제
	public void deleteBusinessAddress(int bs_num) {
		try {
			con = getConnection();
			String sql = "delete from business_address where ba_bs_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bs_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 옵션을 다른 테이블에 저장할때 옵션 제외 나머지 부분을 item에 넣고 생성된 i_num을 찾아 item_options에 넣는다
	public int getInum(ItemDTO dto) {
		int result = 0;

		try {
			con = getConnection();

			ps = con.prepareStatement("select i_num from item order by i_num desc limit 1;");

			rs = ps.executeQuery();

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

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		System.out.println("dao결과 i_num" + result);
		return result;
	}

	// insert into item_options
	public void insertItemOptions(ItemOptionsDTO idto) {
		try {
			con = getConnection();
			ps = con.prepareStatement(
					"insert into item_options (io_i_num, io_name, io_price, io_duration, io_option) values (?,?,?,?,?)");

			ps.setInt(1, idto.getIo_i_num());
			ps.setString(2, idto.getIo_name());
			ps.setString(3, idto.getIo_price());
			ps.setString(4, idto.getIo_duration());
			ps.setString(5, idto.getIo_option());
			ps.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}

	// get the number of staff (one business)
	public int getStaffCount(int bs_num) {
		int result = 0;

		try {
			con = getConnection();

			ps = con.prepareStatement("select count(*) from staff where s_bs_num = ? and s_condition = ?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1);
			rs = ps.executeQuery();

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
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return result;
	}

	// get info of staff (list)
	public List getStaffs(int startRow, int pageSize, int bs_num) {
		List<StaffDTO> list = new ArrayList<>();
		StaffDTO dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from staff where s_bs_num = ? and s_condition = ?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1);
			rs = ps.executeQuery();

			while (rs.next()) {
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
				dto.setS_profile_org(rs.getString("s_profile_org"));
				dto.setS_background_org(rs.getString("s_background_org"));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return list;
	}

	// delete staff's info (update s_condition)
	public void deleteStaff(int s_num) {
		try {
			con = getConnection();
			String sql = "update staff set s_condition = 0 where s_num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, s_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// get staff's info using s_num
	public StaffDTO getStaffInfo(int s_num) {
		StaffDTO dto = null;
		try {
			con = getConnection();
			ps = con.prepareStatement("select * from staff where s_num = ?");
			ps.setInt(1, s_num);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new StaffDTO();
				dto.setS_name(rs.getString("s_name"));
				dto.setS_title(rs.getString("s_title"));
				dto.setS_profile(rs.getString("s_profile"));
				dto.setS_background(rs.getString("s_background"));
				dto.setS_holiday(rs.getString("s_holiday"));
				dto.setS_annualleave(rs.getString("s_annualleave"));
				dto.setS_open(rs.getInt("s_open"));
				dto.setS_close(rs.getInt("s_close"));
				dto.setS_profile_org(rs.getString("s_profile_org"));
				dto.setS_background_org(rs.getString("s_background_org"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	// update staff's info using s_num
	public void updateStaff(int s_num, StaffDTO dto) {
		try {
			con = getConnection();
			String sql = "update staff set s_name = ?, s_title = ?, s_profile = ?, s_background = ?, s_holiday = ?, s_annualleave = ?, s_open = ?, s_close = ?, s_profile_org = ?, s_background_org = ? "
					+ "where s_num = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, dto.getS_name());
			ps.setString(2, dto.getS_title());
			ps.setString(3, dto.getS_profile());
			ps.setString(4, dto.getS_background());
			ps.setString(5, dto.getS_holiday());
			ps.setString(6, dto.getS_annualleave());
			ps.setInt(7, dto.getS_open());
			ps.setInt(8, dto.getS_close());
			ps.setString(9, dto.getS_profile_org());
			ps.setString(10, dto.getS_background_org());
			ps.setInt(11, s_num);

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// count article in board table using bs_num
	public int getBoardCount(int bs_num) {
		int count = 0;

		try {
			con = getConnection();

			ps = con.prepareStatement(
					"select count(*) from board where bd_bs_num = ? and bd_condition = ? and bd_ref_count = ? and bd_re_level = ?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1); // 댓글이 삭제되지 않음
			ps.setInt(3, 0); // 답글이 달리지않음
			ps.setInt(4, 0); // 댓글

			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return count;
	}

	// count last article in board
	public int getLastBoardCount(int bs_num) {
		int count = 0;

		try {
			con = getConnection();

			ps = con.prepareStatement(
					"select count(*) from board where (bd_re_level = ? or bd_ref_count = ?) and (bd_bs_num = ? and bd_condition = ?) ");
			ps.setInt(1, 1);
			ps.setInt(2, 1);
			ps.setInt(3, bs_num);
			ps.setInt(4, 1); // 댓글이나 답변이 삭제되지 않음

			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return count;
	}

	// getComment 10개씩
	public List getArticles(int startRow, int pageSize, int bs_num) {
		List<BoardDTO> list = new ArrayList<>();
		BoardDTO dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"select * from board where bd_bs_num = ? and bd_condition = ? and bd_ref_count = ? and bd_re_level = ? limit ?, ? ");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1); // 댓글이 삭제되지 않음
			ps.setInt(3, 0); // 답글이 달리지않음
			ps.setInt(4, 0); // 댓글
			ps.setInt(5, startRow - 1);
			ps.setInt(6, pageSize);

			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new BoardDTO();
				dto.setBd_num(rs.getInt("bd_num"));
				dto.setBd_bs_num(rs.getInt("bd_bs_num"));
				dto.setBd_writer(rs.getString("bd_writer"));
				dto.setBd_contents(rs.getString("bd_contents"));
				dto.setBd_date(rs.getTimestamp("bd_date"));
				dto.setBd_i_num(rs.getInt("bd_i_num"));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return list;
	}

	// getLastComment 10개씩
	public List getLastArticles(int startRow, int pageSize, int bs_num) {
		List<BoardDTO> list = new ArrayList<>();
		BoardDTO dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"select * from board where (bd_re_level = ? or bd_ref_count = ?) and (bd_bs_num = ? and bd_condition = ?) order by bd_ref desc, bd_re_level asc limit ?, ? ");
			ps.setInt(1, 1);
			ps.setInt(2, 1);
			ps.setInt(3, bs_num);
			ps.setInt(4, 1); // 댓글이나 답변이 삭제되지 않음
			ps.setInt(5, startRow - 1);
			ps.setInt(6, pageSize);

			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new BoardDTO();
				dto.setBd_num(rs.getInt("bd_num"));
				dto.setBd_bs_num(rs.getInt("bd_bs_num"));
				dto.setBd_writer(rs.getString("bd_writer"));
				dto.setBd_contents(rs.getString("bd_contents"));
				dto.setBd_date(rs.getTimestamp("bd_date"));
				dto.setBd_i_num(rs.getInt("bd_i_num"));
				list.add(dto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return list;
	}

	// qna리스트에서 쓸 상품명과 상품번호 map
	public HashMap<Integer, String> getItemName(int bs_num) {
		HashMap<Integer, String> map = new HashMap<>();

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from item where i_bs_num = ? and i_condition = ?");
			ps.setInt(1, bs_num);
			ps.setInt(2, 1);
			rs = ps.executeQuery();

			while (rs.next()) {
				map.put(rs.getInt("i_num"), rs.getString("i_name"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return map;
	}

	//판매자 출퇴근시간  가져오기
	public ArrayList<Integer> getTimeNum(int bs_num) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		try {
			con = getConnection();
			ps = con.prepareStatement("select bs_open, bs_close from business where bs_num = ?");
			ps.setInt(1, bs_num);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result.add(rs.getInt(1));
				result.add(rs.getInt(2));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	//디자이너 일정입력(update s_annualleave)
	public void insertSchedule(String s_annualleave, int s_num) {
		
		try {
			con = getConnection();
			
			String sql = "update staff set s_annualleave = ? where s_num = ? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, s_annualleave);
			ps.setInt(2, s_num);
			ps.executeUpdate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}
	
	
	/* about settlement methods */
	// 전체매장 총 수익(예약 100%환불은 검색x)
	public int getMemberMoney(String id, String startDate, String endDate) {
		int result = 0;
		int m_num = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement("select m_num from member where m_id = ? ");
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				m_num = rs.getInt(1);
				ps = con.prepareStatement("select final_price from business right outer join booked on bs_num = bk_bs_num where bs_m_num = ? and bk_pay_date >= ? and bk_pay_date <= ? and (bk_condition=1 or bk_condition=3 or bk_condition=4) ");
				ps.setInt(1, m_num);
				ps.setString(2, startDate);
				ps.setString(3, endDate);
				rs =  ps.executeQuery();
				while(rs.next()) {
					result += rs.getInt(1); 
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}

	// 선택날짜의 매장별 총 수익
	public int getBusinessMoney(int bs_num, String startDate, String endDate, int bk_condition) {
		int result = 0;

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from booked where bk_pay_date >= ? and bk_pay_date <= ? and bk_bs_num = ? and bk_condition = ?");
			
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ps.setInt(3, bs_num);
			ps.setInt(4, bk_condition);

			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("final_price");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}

	// 선택날짜의 디자이별 총 수익
	public int getDesignerMoney(int s_num, String startDate, String endDate, int bk_condition) {
		int result = 0;

		try {
			con = getConnection();
			ps = con.prepareStatement("select * from booked where bk_pay_date >= ? and bk_pay_date <= ? and bk_s_num = ? and bk_condition = ?");
			
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ps.setInt(3, s_num);
			ps.setInt(4, bk_condition);

			rs = ps.executeQuery();

			while (rs.next()) {
				result += rs.getInt("final_price");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
	
	//디자이너 건수 count 
	public int getDesignerCount(int s_num, String startDate, String endDate, int bk_condition) {
		int count = 0;

		try {
			con = getConnection();
			ps = con.prepareStatement("select count(*) from booked where bk_pay_date >= ? and bk_pay_date <= ? and bk_s_num = ? and bk_condition = ?");
			
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ps.setInt(3, s_num);
			ps.setInt(4, bk_condition);

			rs = ps.executeQuery();

			if(rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}

			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return count;
	}
	
	//검색가 포함되는 아이템 개수
	public int getItemSearchCount(String id, int bs_num, String itemSearch) {
		int count = 0;

		try {
			con = getConnection();

			ps = con.prepareStatement("select count(*) from item where i_name like ? and i_bs_num = ? and i_condition = ? ");
			ps.setString(1, "%"+itemSearch+"%");
			ps.setInt(2, bs_num);
			ps.setInt(3, 1);
			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return count;
	}
	
	//검색가 포함되는 아이템 리스트
	public List getSearchItems(int startRow, int pageSize, int bs_num, String itemSearch) {
		int m_num = 0;
		List<ItemDTO> list = new ArrayList();
		ItemDTO dto = null;

		try {
			con = getConnection();
			ps = con.prepareStatement(
					"select * from item where i_name like ? and i_bs_num = ? and i_condition = ? order by i_regdate desc limit ?,?");
			ps.setString(1, "%"+itemSearch+"%");
			ps.setInt(2, bs_num);
			ps.setInt(3, 1);
			ps.setInt(4, startRow - 1);
			ps.setInt(5, pageSize);
			rs = ps.executeQuery();

			while (rs.next()) {
				dto = new ItemDTO();
				dto.setI_num(rs.getInt("i_num"));
				dto.setI_bs_num(rs.getInt("i_bs_num"));
				dto.setI_s_num(rs.getInt("i_s_num"));
				dto.setI_price(rs.getInt("i_price"));
				dto.setI_name(rs.getString("i_name"));
				dto.setI_thumbnail(rs.getString("i_thumbnail"));
				dto.setI_thumbnail_org(rs.getString("i_thumbnail_org"));
				dto.setI_contents(rs.getString("i_contents"));
				dto.setI_option(rs.getString("i_option"));
				dto.setI_option_sel1(rs.getString("i_option_sel1"));
				dto.setI_option_sel2(rs.getString("i_option_sel2"));
				dto.setI_duration(rs.getInt("i_duration"));
				dto.setI_regdate(rs.getTimestamp("i_regdate"));
				list.add(dto);
			}

		} catch (

		Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}

		return list;
	}
}
