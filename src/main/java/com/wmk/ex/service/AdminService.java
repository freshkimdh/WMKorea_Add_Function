package com.wmk.ex.service;

import java.util.List;

import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;



public interface AdminService {
	
	//ī�װ���
	public List<CategoryVO> category() throws Exception;
	
	//��ǰ���
	public void register(GoodsVO vo) throws Exception;
	
	//��ǰ���
	public List<GoodsVO> goodslist() throws Exception;
	
	//��ǰ��ȸ
	public GoodsViewVO goodsView(int gdsNum) throws Exception;
	
	//��ǰ ����
	public void goodsModify(GoodsVO vo) throws Exception;
	
	
	//��ǰ ����
	public void goodsDelete(int gdsNum) throws Exception;
	
}