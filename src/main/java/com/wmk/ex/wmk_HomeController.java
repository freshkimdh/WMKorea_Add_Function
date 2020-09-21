package com.wmk.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
public class wmk_HomeController {
	
//	@RequestMapping("/index")
//	public String index() { 
//		log.info("index...");
//		return "index";
//	}
	
	@RequestMapping("/wmk_login/loginForm")
	public String login() { 
		log.info("loginForm...");
		return "wmk_login/loginForm";
	}
	
	@RequestMapping("/wmk_login/join")
	public String join() { 
		log.info("join...");
		return "wmk_login/join";
	}
	
	@GetMapping("/index") //�� ������Ʈ ������ �ƴ� �ٸ����ΰ�?: ������ ���� �� ���ذ� �ȵȴ�.
	public String index(Model model) {
		log.info("index...");
		return "index";

	}
	
	
	@GetMapping("/loginForm") //�� ������Ʈ ������ �ƴ� �ٸ����ΰ�?: ������ ���� �� ���ذ� �ȵȴ�.
	public String login(Model model) {
		
		log.info("loginForm...");
		return "loginForm";

	}
		
	@GetMapping("/joinForm") //�� ������Ʈ ������ �ƴ� �ٸ����ΰ�?: ������ ���� �� ���ذ� �ȵȴ�.
	public String join(Model model) {
		
		log.info("joinForm...");
		return "joinForm";

	}
	
	@GetMapping("/mypage") //�� ������Ʈ ������ �ƴ� �ٸ����ΰ�?: ������ ���� �� ���ذ� �ȵȴ�.
	public String myPage(Model model) {
		
		log.info("mypage...");
		return "myPage";

	}
	
}