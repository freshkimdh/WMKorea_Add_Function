package com.wmk.ex.dao;

import java.util.List;

import com.wmk.ex.vo.CommentVO;
import com.wmk.ex.vo.CommentListVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.UserVO;


public interface ShopDAO {

	// ī�װ����� ��ǰ ����Ʈ: 1��
	public List<GoodsViewVO> list(int cateCode, int cateCodeRef) throws Exception;

	// ī�װ����� ��ǰ ����Ʈ: 2��
	public List<GoodsViewVO> list(int cateCode) throws Exception;
	
	//��ǰ��ȸ
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//��ǰ ��� �ۼ�
	public void registReply(CommentVO comment) throws Exception;
	
	//��ǰ �Ұ�(���) ����Ʈ
	public List<CommentListVO> commentList(int gdsNum) throws Exception;
	
	//��ǰ �Ұ�(���) ����
	public void deleteReply(CommentVO comment) throws Exception;
	
	//���̵� üũ
	public String idCheck(int repNum) throws Exception;
	
		
}  