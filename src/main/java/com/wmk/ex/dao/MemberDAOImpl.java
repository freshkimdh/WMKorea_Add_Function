package com.wmk.ex.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wmk.ex.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sql;
	
	//매퍼
	private static String namespace = "com.wmk.ex.mapper.MemberMapper";
	
	//회원가입
	@Override
	public void signup(MemberVO vo) throws Exception {
		sql.insert(namespace + ".signup", vo);
		
	}

	//로그인
	@Override
	public MemberVO signin(MemberVO vo) throws Exception {
		
		return sql.selectOne(namespace + ".signin", vo);
	}
	
	

}
