package moahair.ethan.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.ethan.dao.MainDB;
import moahair.mvc.controller.SuperAction;

public class FilterAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		List ItemList = null;
		List FilterList = null;
		HttpSession session = request.getSession();

		// 검색어 전달
		String listOpt = (String) session.getAttribute("listOpt");

		// 페이징작업
		int pageSize = 12;

		String pageNum = request.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;

		MainDB main = MainDB.getInstance();

		// 카테고리 별 파라미터 전달 후 쿼리 작업

		String[] ch1 = request.getParameterValues("ch1[]");
		String[] ch2 = request.getParameterValues("ch2[]");
		String[] ch3 = request.getParameterValues("ch3[]");
		String[] ch4 = request.getParameterValues("ch4[]");

		ArrayList CaList = new ArrayList();

		if (ch1 != null) {
			String query = "";
			if (ch1.length == 1) {
				String[] q = ch1[0].split("-");
				query = ("(" + q[0] + "=" + q[1] + ")");
			} else {
				for (int i = 0; i < ch1.length; i++) {

					String[] q = ch1[i].split("-");

					if (i == 0) {
						query += ("(" + q[0] + "=" + q[1] + " or ");
					} else if (i == (ch1.length - 1)) {
						query += (q[0] + "=" + q[1] + ")");
					} else {
						query += (q[0] + "=" + q[1] + " or ");
					}
				}
			}
			CaList.add(query);

		}

		if (ch2 != null) {
			String query = "";
			if (ch2.length == 1) {
				String[] q = ch2[0].split("-");
				query = ("(" + q[0] + "=" + q[1] + ")");
			} else {
				for (int i = 0; i < ch2.length; i++) {

					String[] q = ch2[i].split("-");

					if (i == 0) {
						query += ("(" + q[0] + "=" + q[1] + " or ");
					} else if (i == (ch2.length - 1)) {
						query += (q[0] + "=" + q[1] + ")");
					}

					else {
						query += (q[0] + "=" + q[1] + " or ");
					}
				}
			}
			CaList.add(query);
		}

		if (ch3 != null) {
			String query = "";
			if (ch3.length == 1) {
				String[] q = ch3[0].split("-");
				query = ("(" + q[0] + "=" + q[1] + ")");
			} else {
				for (int i = 0; i < ch3.length; i++) {

					String[] q = ch3[i].split("-");

					if (i == 0) {
						query += ("(" + q[0] + "=" + q[1] + " or ");
					} else if (i == (ch3.length - 1)) {
						query += (q[0] + "=" + q[1] + ")");
					} else {
						query += (q[0] + "=" + q[1] + " or ");
					}
				}
			}
			CaList.add(query);
		}

		if (ch4 != null) {
			String query = "";

			String[] b = ch4[0].split("-");

			int pr = Integer.parseInt(b[1]);

			if (pr > 200000) {
				if (ch4.length == 1) {
					String[] q = ch4[0].split("-");
					query = ("(" + q[0] + ">=" + q[1] + ")");
				}

			} else {
				if (ch4.length == 1) {
					String[] q = ch4[0].split("-");
					query = ("(" + q[0] + " between 0 and " + q[1] + ")");
				}
			}

			CaList.add(query);
		}

		if (CaList.size() != 0) {
			String BaList = "";
			for (int i = 0; i < CaList.size(); i++) {
				if (i == 0) {
					BaList += CaList.get(i);
				} else {
					BaList += (" and " + CaList.get(i) + " ");
				}
			}

			// 쿼리 전달

			session.setAttribute("BaList", BaList);
			FilterList = main.getFilterArticles(startRow, pageSize, BaList, listOpt);
			count = main.getArticleCount(BaList, listOpt);
			System.out.println("Filter Action!" + "\n" + "query : " + BaList + "\n" + "search : " + listOpt + "\n"
					+ "count : " + count + "\n");
			request.setAttribute("ItemList", FilterList);
		} else {
			session.removeAttribute("BaList");
			String BaList = null;
			count = main.getArticleCount(BaList, listOpt);
			System.out.println("Filter Action!" + "\n" + "query : " + BaList + "\n" + "search : " + listOpt + "\n"
					+ "count : " + count + "\n");

			if (count > 0) {
				ItemList = main.getItemArticles(startRow, pageSize, listOpt);
			}
			request.setAttribute("ItemList", ItemList);
		}

		return "/mainPage/ItemList.jsp";

	}

}
