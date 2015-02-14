package com.spring4.servlet3.sample.web.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.spring4.servlet3.sample.common.abs.AbstractBaseController;
import com.spring4.servlet3.sample.common.util.GlobalCacheUtil;
import com.spring4.servlet3.sample.web.member.service.MemberService;

@Controller
@RequestMapping(value="/member")
public class MemberController extends AbstractBaseController<MemberController>{

	@Autowired MemberService memberService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@CookieValue(required=false, defaultValue="") String SESSION_KEY, Model model) throws Exception {	
		try{
			if(!SESSION_KEY.equals("")){
//				Thread.sleep(1000);
				Object m = GlobalCacheUtil.getGlobalCache("default", SESSION_KEY);
				if(m != null) model.addAttribute("SESSION_INFO", m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "member/login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam String id, @RequestParam String pass, HttpServletRequest req, HttpServletResponse res) throws Exception {

		try{
			DBObject m = memberService.selectMember(id, pass);
			System.out.println("########"+m);
			if(m != null){
				String sessionKey = getSessionId(req);
				GlobalCacheUtil.setGlobalCache("default", sessionKey, m);
				
				Cookie c = new Cookie("SESSION_KEY", sessionKey);
				c.setPath("/");
				res.addCookie(c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "redirect:/member/login";
	}
	
	@RequestMapping(value="/getData", method = RequestMethod.GET)
	public @ResponseBody String getData(@CookieValue(required=false, defaultValue="") String SESSION_KEY, Model model) throws Exception {	
		DBObject o = new BasicDBObject();
		try{
//			Thread.sleep(500);
			if(!SESSION_KEY.equals("")){
				Object m = GlobalCacheUtil.getGlobalCache("default", SESSION_KEY);
				if(m != null) o.put("member", m);
				else o.put("member", "NULL");
			}
		}catch(Exception e){
			o.put("member", "ERROR");
			e.printStackTrace();
		}
		return o.toString();
	}
}
