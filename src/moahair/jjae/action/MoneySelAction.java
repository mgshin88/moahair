package moahair.jjae.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class MoneySelAction implements SuperAction{
//��ü����
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memId");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		System.out.println(startDate+"/////"+endDate);
		
		JaeDAO dao = JaeDAO.getInstance();
		
		//{��������ȣ, ��ȣ��} �Ѿ��̵�� ��ϵ� ����������
		HashMap<Integer, String> businesses = dao.getBusinesses(id);
		
		//{��������ȣ, ����}
		HashMap<Integer, Integer> price = new HashMap<>();
		
		Set key = businesses.keySet();
		
		int allmoney = dao.getMemberMoney(id, startDate, endDate);
	
		//{��������ȣ, ����}
		for(Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer bs_num = iter.next();
			Integer final_price = dao.getBusinessMoney(bs_num, startDate, endDate, 1);		//�ü��Ϸ� ����
			final_price += dao.getBusinessMoney(bs_num, startDate, endDate, 3);				//��� ������ ����
			final_price += dao.getBusinessMoney(bs_num, startDate, endDate, 4);				//��� ������ ����
			price.put(bs_num, final_price);
			System.out.println(bs_num+"-------"+ final_price);
		}
		
		
		request.setAttribute("businesses", businesses);
		request.setAttribute("todayMoney", price);
		request.setAttribute("allmoney", allmoney);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		return "/sellerPage/seller/moneySel.jsp";
	}
	
}
