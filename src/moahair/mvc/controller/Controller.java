package moahair.mvc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {
	private HashMap map = new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getInitParameter("propertiesPath"); 
		Properties f = new Properties();  
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path); 
			f.load(fis); 
			
			Iterator iter = f.keySet().iterator();
			while(iter.hasNext()) {
				String key = (String)iter.next();
				String value = f.getProperty(key);
				Class c = Class.forName(value);
				Object obj = c.newInstance();
				map.put(key, obj);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(fis != null) try {fis.close();} catch (IOException e) {}
		}
	}

	public void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException,IOException {

		

		String uri = request.getRequestURI();
		System.out.println(uri);
		SuperAction action = null;
		String view = "";
		
		try {

			action = (SuperAction)map.get(uri);
			view = action.requestPro(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(view).forward(request, response);
	}
}

