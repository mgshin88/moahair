package moahair.kyu.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.ItemBusinessDTO;
import moahair.data.dto.ItemOptionsDTO;
import moahair.data.dto.My_timeDTO;
import moahair.data.dto.StaffDTO;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class ProductViewAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String m_id = (String)session.getAttribute("memId");
		int i_num = Integer.parseInt(request.getParameter("i_num"));
		
		
		KyuDAO dao = KyuDAO.getInstance();
		
		ItemBusinessDTO dto = dao.productSelect(i_num);
		
		ItemOptionsDTO dtooption = dao.productOptionSelect(dto.getI_num(), dto.getI_option());
		ItemOptionsDTO dtooption_sel1 = dao.productOptionSelect(dto.getI_num(), dto.getI_option_sel1());
		ItemOptionsDTO dtooption_sel2 = dao.productOptionSelect(dto.getI_num(), dto.getI_option_sel2());
		
		String[] tmp = new String[5];
		String[] tmpValue = new String[5];
		for(int i=0;i<tmp.length;i++) {
			tmp[i] = "";
			tmpValue[i] = "";
		}
		
		String[] optionName = dtooption.getIo_name().split(",");
		String[] optionPrice = dtooption.getIo_price().split(",");
		String[] optionDuration = dtooption.getIo_duration().split(",");
		ArrayList<String> option_result = new ArrayList<String>();
		for(int i=0;i<optionName.length;i++) {
			if(optionName[i].equals("*")) {
				if(i==0) {
					option_result = null;
				}
				break;
			}
			
			if(!optionName[i].equals("*")) {
				tmp[i] += optionName[i];
				tmpValue[i] += optionName[i] + ",";
			}
			
			if(!optionPrice[i].equals("*")) {
				tmp[i] += "(+" + optionPrice[i] + "원) ";
				tmpValue[i] += optionPrice[i] + ",";
			}
			
			if(!optionDuration[i].equals("*")) {
				if(!optionDuration[i].equals("0")) {
					tmp[i] += Integer.parseInt(optionDuration[i])*30 + "분 추가";
					tmpValue[i] += Integer.parseInt(optionDuration[i])*30;
				}
				else {
					tmpValue[i] += 0;
				}
			}
			
			option_result.add(tmp[i]);
		}
		
		String[] tmp1 = new String[5];
		String[] tmpValue1 = new String[5];
		for(int i=0;i<tmp1.length;i++) {
			tmp1[i] = "";
			tmpValue1[i] = "";
		}
		
		optionName = dtooption_sel1.getIo_name().split(",");
		optionPrice = dtooption_sel1.getIo_price().split(",");
		optionDuration = dtooption_sel1.getIo_duration().split(",");
		ArrayList<String> option_sel1_result = new ArrayList<String>();
		
		for(int i=0;i<optionName.length;i++) {
			if(optionName[i].equals("*")) {
				if(i==0) {
					option_sel1_result = null;
				}
				break;
			}
			
			if(!optionName[i].equals("*")) {
				tmp1[i] += optionName[i];
				tmpValue1[i] += optionName[i] + ",";
			}
			
			if(!optionPrice[i].equals("*")) {
				tmp1[i] += "(+" + optionPrice[i] + "원) ";
				tmpValue1[i] += optionPrice[i] + ",";
			}
			
			if(!optionDuration[i].equals("*")) {
				if(!optionDuration[i].equals("0")) {
					tmp1[i] += Integer.parseInt(optionDuration[i])*30 + "분 추가";
					tmpValue1[i] += Integer.parseInt(optionDuration[i])*30;
				}
				else {
					tmpValue1[i] += 0;
				}
			}
			option_sel1_result.add(tmp1[i]);
		}
		
		String[] tmp2 = new String[5];
		String[] tmpValue2 = new String[5];
		for(int i=0;i<tmp1.length;i++) {
			tmp2[i] = "";
			tmpValue2[i] = "";
		}
		
		optionName = dtooption_sel2.getIo_name().split(",");
		optionPrice = dtooption_sel2.getIo_price().split(",");
		optionDuration = dtooption_sel2.getIo_duration().split(",");
		ArrayList<String> option_sel2_result = new ArrayList<String>();

		for(int i=0;i<optionName.length;i++) {
			if(optionName[i].equals("*")) {
				if(i==0) {
					option_sel2_result = null;
				}
				break;
			}
			
			if(!optionName[i].equals("*")) {
				tmp2[i] += optionName[i];
				tmpValue2[i] += optionName[i] + ",";
			}
			
			if(!optionPrice[i].equals("*")) {
				tmp2[i] += "(+" + optionPrice[i] + "원) ";
				tmpValue2[i] += optionPrice[i] + ",";
			}
			
			if(!optionDuration[i].equals("*")) {
				if(!optionDuration[i].equals("0")) {
					tmp2[i] += Integer.parseInt(optionDuration[i])*30 + "분 추가";
					tmpValue2[i] += Integer.parseInt(optionDuration[i])*30;
				} else {
					tmpValue2[i] += 0;
				}
			}
			
			option_sel2_result.add(tmp2[i]);
		}
		
		ArrayList<StaffDTO> slist = new ArrayList<StaffDTO>();
		slist = dao.productDesingerSelect(dto.getBs_num(), dto.getI_num());
		
		My_timeDTO[] tdto = dao.bs_OpenClose(dto.getBs_open(), dto.getBs_close(), dto.getBs_num());
		
		int level = dao.memberLevel(m_id);
		int bs_num = dto.getBs_num();
		
		int siwl = dao.SelectItemWishList(m_id, i_num);
		int sbwl = dao.SelectBsWishList(m_id, bs_num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("tdto", tdto);
		request.setAttribute("slist", slist);
		request.setAttribute("m_id", m_id);
		request.setAttribute("i_num", i_num);
		request.setAttribute("option", option_result);
		request.setAttribute("dtooption1", option_sel1_result);
		request.setAttribute("dtooption2", option_sel2_result);
		request.setAttribute("level", level);
		request.setAttribute("bs_num", bs_num);
		request.setAttribute("siwl", siwl);
		request.setAttribute("sbwl", sbwl);
		
		if(request.getParameter("number") != null) { 
			int number = Integer.parseInt(request.getParameter("number"));
			request.setAttribute("number", number);
		}
		
		request.setAttribute("tmpValue", tmpValue);
		request.setAttribute("tmpValue1", tmpValue1);
		request.setAttribute("tmpValue2", tmpValue2);
		
		/*
		request.setAttribute("b_list", b_list);
		request.setAttribute("count", count);
		request.setAttribute("check", m_dao.checkSeller(bs_num, m_id));
		
		request.setAttribute("shopname", shopname);
		request.setAttribute("name", name);
		*/
		return "/itemPage/ProductView.jsp";
	}

}
