package moahair.jjae.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.data.dto.StaffDTO;
import moahair.jjae.dao.JaeDAO;
import moahair.mvc.controller.SuperAction;

public class MoneyBusinessAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("memId");

		int bs_num = Integer.parseInt(request.getParameter("bs_num"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		JaeDAO dao = JaeDAO.getInstance();
		// int result = dao.getBusinessMoney(bs_num, startDate, endDate,1); //선택 날짜
		// 기간동안의 매장 총액
		
		List<StaffDTO> staffs = dao.getStaffName(bs_num); // 한 매장의 디자이너 DTO리스트
		// {디자이너고유번호, 디자이너이름}
		HashMap<Integer, String> staffMap = new HashMap<>();

		for (int i = 0; i < staffs.size(); i++) {
			staffMap.put(staffs.get(i).getS_num(), staffs.get(i).getS_name());
		}
		///////////////////////////////////////////////money///////////////////////////////////////////
		int completeIcBu = 0; /// business' complete money
		int cancelIcBu = 0; // business' cancel money
		int noshowIcBu = 0; //  business' noshow money

		// {s_num, completeIncome}
		HashMap<Integer, Integer> completeIc = new HashMap<>();
		// {s_num, cancelIncome}
		HashMap<Integer, Integer> cancelIc = new HashMap<>();
		// {s_num, noshowIncome}
		HashMap<Integer, Integer> noshowIc = new HashMap<>();
		// {s_num, totalIncome}
		HashMap<Integer, Integer> totalIc = new HashMap<>();

		// s_num's keys
		Set key = staffMap.keySet();

		//// {s_num, completeIncome} 데이터입력
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer bk_price = dao.getDesignerMoney(s_num, startDate, endDate, 1);
			completeIc.put(s_num, bk_price);
			completeIcBu += bk_price;

		}
		// {s_num, cancelIncome} 데이터입력
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer bk_price = dao.getDesignerMoney(s_num, startDate, endDate, 3);
			cancelIc.put(s_num, bk_price);
			cancelIcBu += bk_price;

		}
		// {s_num, noshowIncome} 데이터입력
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer bk_price = dao.getDesignerMoney(s_num, startDate, endDate, 4);
			noshowIc.put(s_num, bk_price);
			noshowIcBu += bk_price;

		}
		// {s_num, totalIncome} 데이터입력
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer bk_price = completeIc.get(s_num);
			bk_price += cancelIc.get(s_num);
			bk_price += noshowIc.get(s_num);
			totalIc.put(s_num, bk_price);
		}
		
		//////////////////////////////////////////////////////count////////////////////////////////////////////
		int completeCntBu = 0;
		int cancelCntBu = 0;
		int noshowCntBu = 0;
		
		//designer's complete count {s_num, completeCount}
		HashMap<Integer, Integer> completeCnt = new HashMap<>();
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer count = dao.getDesignerCount(s_num, startDate, endDate, 1);
			completeCnt.put(s_num, count);
			completeCntBu += count;
			System.out.println("시술완료"+s_num+"---------"+count);
			System.out.println("시술완료 총건"+completeCntBu);
		}
		HashMap<Integer, Integer> cancelCnt = new HashMap<>();
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer count = dao.getDesignerCount(s_num, startDate, endDate, 3);
			cancelCnt.put(s_num, count); 
			cancelCntBu += count;
			System.out.println("취소"+s_num+"---------"+count);
			System.out.println("노쇼 총건"+cancelCntBu);
		}
		HashMap<Integer, Integer> noshowCnt = new HashMap<>();
		for (Iterator<Integer> iter = key.iterator(); iter.hasNext();) {
			Integer s_num = iter.next();
			Integer count = dao.getDesignerCount(s_num, startDate, endDate, 4);
			noshowCnt.put(s_num, count); 
			noshowCntBu += count;
			System.out.println("노쇼"+s_num+"---------"+count);
			System.out.println("노쇼 총건"+noshowCntBu);
		}
		int totalCntBu = completeCntBu + cancelCntBu + noshowCntBu;
		System.out.println("매장 총건"+ totalCntBu);
		
		HashMap<Integer, String> businessName = dao.getBusinesses(id);
		
		
		int totalIcBu = completeIcBu + cancelIcBu + noshowIcBu;		///business's total income

		request.setAttribute("bs_num", bs_num);
		request.setAttribute("businessName", businessName);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("staffMap", staffMap); // s_num과 s_name의 맵
		request.setAttribute("staffs", staffs);
		
		request.setAttribute("completeIc", completeIc); // s_num과 기간 총액 bk_price의 맵
		request.setAttribute("cancelIc", cancelIc);			// {s_num, income}
		request.setAttribute("noshowIc", noshowIc);			// {s_num, income}
		request.setAttribute("totalIc", totalIc);			// {s_num, income}
		request.setAttribute("totalIcBu", totalIcBu);		// {s_num, income}
		request.setAttribute("completeIcBu", completeIcBu);	// 매장의 시술완료 수익 int
		request.setAttribute("cancelIcBu", cancelIcBu);		// 매장의 취소 수익 int
		request.setAttribute("noshowIcBu", noshowIcBu);		// 매장의 노쇼 수익 int
		
		request.setAttribute("completeCnt", completeCnt);	// 디자이너별 시술완료 건수	{s_num, count}
		request.setAttribute("cancelCnt", cancelCnt);		// 디자이너별 취소 건수		{s_num, count}
		request.setAttribute("noshowCnt", noshowCnt);		// 디자이너별 노쇼 건수		{s_num, count}
		request.setAttribute("completeCntBu", completeCntBu);	//매장의 시술완료 건수 int
		request.setAttribute("cancelCntBu", cancelCntBu);		//매장의 취소 건수 int
		request.setAttribute("noshowCntBu", noshowCntBu);		//매장의 노쇼 건수 int
		request.setAttribute("totalCntBu", totalCntBu);			//매장 전체 건수 int
		
		return "/sellerPage/seller/moneyBusiness.jsp";
	}

}
