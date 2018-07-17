package moahair.ethan.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.ethan.dao.MainDB;
import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class IndexAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List BestList = null;
		List ItemList = null;
		List FilterList = null;
		String BaList = null;

		MainDB main = MainDB.getInstance();
		KyuDAO admin = KyuDAO.getInstance();

		HttpSession session = request.getSession();

		// 검색기능 초기화
		session.removeAttribute("listOpt");
		session.removeAttribute("BaList");

		// 페이징작업
System.out.println(request.getRealPath(""));
		
		int pageSize = 12;
		
		String pageNum = request.getParameter("pageNum");
		String listOpt = request.getParameter("listOpt");

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;

		// 로그인 아이디 세션저장
		String id = (String) session.getAttribute("memId");

		// 오늘 날짜
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);

		// 일주일전 날짜
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date wdate = cal.getTime();
		String wTime = mSimpleDateFormat.format(wdate);

		// MainDB에서 불러올 자료들

		BestList = main.getBestArticles(wTime, mTime);
		
		

		
//		null 카운트
		

		count = main.getArticleCount(BaList, listOpt);
//		System.out.println("Index Action!" + "\n" + "query : " + BaList + "\n" + "search : " +listOpt + "\n" + "count : " +count + "\n");

		if (listOpt!=null) {
			session.setAttribute("listOpt", listOpt);
			if (count > 0) {
				ItemList = main.getItemArticles(startRow, pageSize, listOpt);
			}
		}else {
			count = main.getArticleCount(BaList, listOpt);
			ItemList = main.getItemArticles(startRow, pageSize, listOpt);
		}

		int memberLevel = admin.memberLevel(id);

		request.setAttribute("BestList", BestList);
		request.setAttribute("ItemList", ItemList);
		request.setAttribute("memberLevel", memberLevel);

		return "/mainPage/index.jsp";

	}

}
