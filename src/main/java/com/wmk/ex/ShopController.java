package com.wmk.ex;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wmk.ex.service.ShopService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.MemberVO;
import com.wmk.ex.vo.UserVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
//@RequestMapping("/shop/*")
public class ShopController {
 
	 @Inject
	 ShopService service;
    
	 // 카테고리별 상품 리스트
	 @RequestMapping(value = "/shop/list", method = RequestMethod.GET)
	 public String getList(@RequestParam("c") int cateCode, @RequestParam("l") int level, Model model) throws Exception {
		  log.info("get llist");
		  
		  List<GoodsViewVO> list = null;
		  list = service.list(cateCode, level);
		 
		  model.addAttribute("list", list);
		  
		  return "/admin_goods/shop/list";
	  
	 }
	 
	 @RequestMapping(value = "/shop/view", method = RequestMethod.GET)
	 public String getView(@RequestParam("n") int gdsNum, Model model) throws Exception{
		 log.info("get view");
		 
		 GoodsViewVO view = service.goodsView(gdsNum);
		 model.addAttribute("view", view);
		 
		 return "/admin_goods/shop/view";
	 }
	 
	 
	 
	// 상품 조회 - 소감(댓글) 작성
	 @RequestMapping(value = "/shop/view", method = RequestMethod.POST)
	 public String registReply(CommentVO comment, HttpSession session) throws Exception {
		 log.info("regist reply");
		 
		 MemberVO member = (MemberVO)session.getAttribute("member");
		 comment.setUserId(member.getUserId());
		 
		 service.registReply(comment);
	  
	  
	  return "redirect:/shop/view?n=" + comment.getGdsNum();
	 }
	 
	 
//		@GetMapping("/reply_view") 
//		public String reply_view(BoardVO boardVO, Model model) {
//		
//			log.info("reply_view");
//			model.addAttribute("reply_view", service.get(boardVO.getbId()));
//			
//			return "reply_view";
//		}
	 


	 
	 
	 
}