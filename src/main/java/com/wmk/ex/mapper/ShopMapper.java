package com.wmk.ex.mapper;

import java.util.List;

import com.wmk.ex.vo.CategoryVO;
import com.wmk.ex.vo.GoodsVO;
import com.wmk.ex.vo.GoodsViewVO;
import com.wmk.ex.vo.UserVO;

public interface ShopMapper {
	
//	//ī�װ����� ��ǰ ����Ʈ
//	public List<GoodsViewVO> list(int cateCode) throws Exception;
//	
//	//��ǰ��ȸ
//	public GoodsViewVO goodslist(int gdsNum) throws Exception;
	

	public UserVO userIdread(String id) throws Exception;

}