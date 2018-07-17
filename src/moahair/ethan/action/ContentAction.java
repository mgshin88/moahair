package moahair.ethan.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moahair.ethan.dao.MainDB;
import moahair.mvc.controller.SuperAction;

public class ContentAction implements SuperAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List ItemList = null;
		List FilterList = null;
		HttpSession session = request.getSession();		

		int pageSize = 12;
		
		
//		검색엔진
		
		String listOpt = (String) session.getAttribute("listOpt");
		
		String BaList = (String) session.getAttribute("BaList");
		
		String pageNum = request.getParameter("pageNum");
		
		System.out.println(pageNum);

		if (pageNum == null) {
			pageNum = "1";
		}

		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		MainDB main = MainDB.getInstance();

		
		count = main.getArticleCount(BaList,listOpt);
		System.out.println("Content Action!" + "\n" + "query : " + BaList + "\n" + "search : " +listOpt + "\n" + "count : " +count + "\n");
		
		if (BaList ==null && listOpt ==null) {
			
			session.removeAttribute("BaList");
			session.removeAttribute("listOpt");
			count = main.getArticleCount(BaList, listOpt);
			ItemList = main.getItemArticles(startRow, pageSize, listOpt);
			request.setAttribute("ItemList", ItemList);
			
		} else if (BaList !=null && listOpt ==null){
			
			session.removeAttribute("listOpt");
			FilterList = main.getFilterArticles(startRow,pageSize,BaList, listOpt);
			count = main.getArticleCount(BaList,listOpt);
			request.setAttribute("ItemList", FilterList);
			
		} else if(BaList ==null && listOpt !=null) {
			
			session.removeAttribute("BaList");
			count = main.getArticleCount(BaList, listOpt);
			ItemList = main.getItemArticles(startRow, pageSize, listOpt);
			request.setAttribute("ItemList", ItemList);
			
		} else if(BaList !=null && listOpt !=null) {
			
			FilterList = main.getFilterArticles(startRow,pageSize,BaList, listOpt);
			count = main.getArticleCount(BaList,listOpt);
			request.setAttribute("ItemList", FilterList);

		}


		

		
		return "/mainPage/contentList.jsp";
	}

}
