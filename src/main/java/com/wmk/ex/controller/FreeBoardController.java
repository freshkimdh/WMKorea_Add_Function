package com.wmk.ex.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wmk.ex.page.Criteria;
import com.wmk.ex.page.PageDTO;
import com.wmk.ex.service.FreeBoardService;
import com.wmk.ex.vo.FreeBoardVO;
import com.wmk.ex.vo.FreeReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
public class FreeBoardController {
	
	private FreeBoardService freeService;
	
	//게시판 목록 read
	@GetMapping("/free_boardList")
	public String BoardList(Model model, Criteria cri) throws Exception {
		
		int total = freeService.getTotalCount(cri);
		
		log.info("total" + total);
		log.info(cri);
		log.info("BoardList...");
		
		model.addAttribute("list", freeService.getListWithPaging(cri));	
		model.addAttribute("pageMaker", new PageDTO(cri,total));
		
		return "/free_board/boardList";
	}
	
	
	//게시판 내용 read
	@GetMapping("/free_contentView")
	public String ContentView(FreeBoardVO freeBoardVO, Model model) throws Exception {
		
	   log.info("content_view...");
	   model.addAttribute("contentView", freeService.getNum(freeBoardVO.getfBoard_Num()));
	   model.addAttribute("list", freeService.getList());
	   
	   return "/free_board/contentView";	   	  
	}
	
	
	//게시판 작성 뷰 read
	@GetMapping("/free_writeView")
	public String WriteView() throws Exception {
		
		log.info("free_writeView...");
		
		return "/free_board/writeView";
	}
	
	
	//게시판 작성 create
	@PostMapping("/free_write")
	public String Write(FreeBoardVO freeBoardVO) throws Exception {
		
		log.info("free_write...");
		freeService.writeBoard(freeBoardVO);

		return "redirect:free_boardList";
	}
	
	
	//게시판 수정 뷰 read
	@GetMapping("/free_modifyView") 
	public String ModifyView(FreeBoardVO freeBoardVO, Model model) throws Exception {
	
		log.info("modifyView...");	
		model.addAttribute("modifyView", freeService.getNum(freeBoardVO.getfBoard_Num()));
		
		return "/free_board/modifyView";
	}
	
	
	//게시판 수정 작성 완료 update
	@PutMapping("/free_modify")
	public String Modify(FreeBoardVO freeboardVO, Model model) throws Exception {
		
		log.info("Modify...");
		freeService.updateModify(freeboardVO);
				
		return "redirect:free_boardList";
	}
	
	
	//게시판 삭제 delete
	@ResponseBody
	@DeleteMapping("/free_delete") 
	public String Delete(FreeBoardVO freeBoardVO) throws Exception {
		
		
		int result = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.info("free_delete...");	
		 
		String username = ((UserDetails)principal).getUsername();
		String userId = freeService.boardUserIdCheck(freeBoardVO.getfBoard_Num());
		
		log.info("username:" + username);
		log.info("userid:" + userId);
		
		if(username.equals(userId)) {
			 
			 freeBoardVO.setfId(username); 
			 freeService.deleteBoard(freeBoardVO);
			  
			 result = 1;
			 
			 log.info("삭제 성공");
			 log.info(result);
		}
		
		log.info(result);
	   
		return String.valueOf(result);
	}
	
	
	//댓글 등록 create
	@ResponseBody
	@PostMapping("/free_contentView/registReply")
	public void RegistReply(FreeReplyVO freeReplyVO) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("regist reply...");

		if (principal instanceof UserDetails) { // user id 가져오기 성공
			String username = ((UserDetails)principal).getUsername();
			log.info("유저 id " + username);
			freeReplyVO.setId(username);
		} else { //user id 가져오기 실패
			String username = principal.toString();
			log.info("유저 id 가져오기 실패");
		}
	 
		freeService.registReply(freeReplyVO);	 
	}
	
	
	//댓글 목록 read
	@ResponseBody
	@GetMapping("/free_contentView/replyList")
	public List<FreeReplyVO> GetReplyList(@RequestParam("n") int fBoard_Num) throws Exception {
		log.info("get reply list");
		    
		List<FreeReplyVO> replyList = freeService.replyList(fBoard_Num);
		  
		return replyList;
	}
	
	
	//댓글 삭제 delete
	@ResponseBody
	@DeleteMapping("/free_contentView/deleteReply")
	public String DeleteReply(FreeReplyVO freeReplyVO) throws Exception {
	 
		int result = 0;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		log.info("DeleteReply...");	
		 
		String username = ((UserDetails)principal).getUsername();
		String userId = freeService.replyUserIdCheck(freeReplyVO.getRepNum());
		log.info("username id " + username);
		log.info("userId id " + userId);
		 
		if(username.equals(userId)) {
				 
			freeReplyVO.setId(username); 
			freeService.deleteReply(freeReplyVO);
			  
			result = 1;
		}	 
		return String.valueOf(result);		 
	}
	
	
	
	
}



