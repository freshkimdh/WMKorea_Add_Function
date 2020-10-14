package com.wmk.ex;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmk.ex.service.ShopService;
import com.wmk.ex.vo.BoardVO;
import com.wmk.ex.vo.CartListVO;
import com.wmk.ex.vo.CartVO;
import com.wmk.ex.vo.CommentListVO;
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
		 
//		 List<CommentListVO> reply = service.commentList(gdsNum);
//		 model.addAttribute("reply", reply);
		 
		 return "/admin_goods/shop/view";
	 }
	 
	 
	 
//	// 상품 조회 - 소감(댓글) 작성
//	 @RequestMapping(value = "/shop/view", method = RequestMethod.POST)
//	 public String registReply(CommentVO comment, HttpSession session) throws Exception {
//		 log.info("regist reply");
//		 
//		 MemberVO member = (MemberVO)session.getAttribute("member");
//		 comment.setUserId(member.getUserId());
//		 
//		 service.registReply(comment);
//	  
//	  
//	  return "redirect:/shop/view?n=" + comment.getGdsNum();
//	 }
	 
	// 상품 소감(댓글) 작성
	 @ResponseBody
	 @RequestMapping(value = "/shop/view/registReply", method = RequestMethod.POST)
	 public void registReply(CommentVO comment, HttpSession session) throws Exception {
	  log.info("regist reply");
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  comment.setUserId(member.getUserId());
	  
	  service.registReply(comment);
	  

	 } 
	 
	 
	 // 상품 소감(댓글) 목록
	 @ResponseBody
	 @RequestMapping(value = "/shop/view/replyList", method = RequestMethod.GET)
	 public List<CommentListVO> getReplyList(@RequestParam("n") int gdsNum) throws Exception {
	  log.info("get reply list");
	    
	  List<CommentListVO> reply = service.commentList(gdsNum);
	  
	  return reply;
	 } 
	 
	 
	 
	// 상품 소감(댓글) 삭제
	 @ResponseBody
	 @RequestMapping(value = "/shop/view/deleteReply", method = RequestMethod.POST)
	 public int getReplyList(CommentVO comment, HttpSession session) throws Exception {
	  log.info("post delete reply");

	  int result = 0;
	  
	  MemberVO member = (MemberVO)session.getAttribute("member");
	  String userId = service.idCheck(comment.getRepNum());
	    
	  if(member.getUserId().equals(userId)) {
	   
	   comment.setUserId(member.getUserId());
	   service.deleteReply(comment);
	   
	   result = 1;
	  }
	  
	  return result; 
	 }
	 
//		@GetMapping("/reply_view") 
//		public String reply_view(BoardVO boardVO, Model model) {
//		
//			log.info("reply_view");
//			model.addAttribute("reply_view", service.get(boardVO.getbId()));
//			
//			return "reply_view";
//		}
	 
	 
	 //장바구니 담기 (스프링 시큐리티 세션값 불러오기): chaddy
	 @ResponseBody
	 @RequestMapping(value = "/addCart", method = RequestMethod.POST)
	 public void addCart(CartVO cart, HttpSession session) throws Exception {
		 
 
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
		 cart.setUserId(username);
		 service.addCart(cart);
		 
		 
	 }
	 
	 //장바구니 보기
	 @RequestMapping(value = "/cartList", method = RequestMethod.GET)
	 public String getCartList(HttpSession session, Model model) throws Exception {
		 log.info("get cart list");
		 
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String username = ((UserDetails)principal).getUsername();

	  
		 List<CartListVO> cartList = service.cartList(username);
	  
		 model.addAttribute("cartList", cartList);
		 
		 return "/wmk_goods/cartList";
	  
	 }
	 
	// 장바구니 삭제
	 @ResponseBody
	 @RequestMapping(value = "/shop/cartList/deleteCart", method = RequestMethod.POST)
	 public int deleteCart(HttpSession session,
	      @RequestParam(value = "chbox[]") List<String> chArr, CartVO cart) throws Exception {
		 log.info("delete cart");
	  	  
	  
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 
		 String username = ((UserDetails)principal).getUsername();
	   
		  int result = 0;
		  int cartNum = 0;
		  	  
		  if(username != null) {
		   cart.setUserId(username);
		   
		   for(String i : chArr) {   
		    cartNum = Integer.parseInt(i);
		    cart.setCartNum(cartNum);
		    service.deleteCart(cart);
		   }   
		   result = 1;	
		  }  
		  return result;  
	 }
	 


	 
	 
	 
}