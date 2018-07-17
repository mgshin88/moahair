package moahair.kyu.action;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moahair.kyu.dao.KyuDAO;
import moahair.mvc.controller.SuperAction;

public class PwSearchCerfityAction implements SuperAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String m_id = request.getParameter("si_id");
		String m_name = request.getParameter("si_name");
		String m_email = request.getParameter("si_email");
		KyuDAO dao = KyuDAO.getInstance();
		boolean result = dao.getPwCheckValue(m_id, m_name, m_email);
		String ranNum = null;
		
		if(result) {
			ranNum = dao.RandomNum();
			
			Cookie cookie = new Cookie("ranNumber", ranNum);
			cookie.setMaxAge(60*3);
			response.addCookie(cookie);
			
			String from = "jangquf0521@naver.com";
			String to = m_email;
			String subject = "[MoaHair]모아헤어 인증 메일 보내드립니다.";
			String content = "인증번호 입력란에 " + ranNum + "를 입력해주세요.";
			// 占쌉력곤옙 占쏙옙占쏙옙

			Properties p = new Properties(); // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙체

			p.put("mail.smtp.host","smtp.naver.com"); // 占쏙옙占싱뱄옙 SMTP

			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			// SMTP 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹깍옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙

			try{
				Authenticator auth = new SMTPAuthenticatior();
				Session ses = Session.getInstance(p, auth);

				ses.setDebug(true);

				MimeMessage msg = new MimeMessage(ses); // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙체
				msg.setSubject(subject); // 占쏙옙占쏙옙

				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr); // 占쏙옙占쏙옙占쏙옙 占쏙옙占�

				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr); // 占쌨댐옙 占쏙옙占�

				msg.setContent(content, "text/html;charset=UTF-8"); // 占쏙옙占쏙옙占� 占쏙옙占쌘듸옙

				Transport.send(msg); // 占쏙옙占쏙옙
		
			} catch(Exception e){
				e.printStackTrace();
			}
		} else {
		
		}
		request.setAttribute("result", result);
		request.setAttribute("ranNum", ranNum);
		
		return "/memberPage/PwSearchCerfity.jsp";
	}

}
