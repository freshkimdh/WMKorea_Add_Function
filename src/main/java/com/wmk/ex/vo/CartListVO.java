package com.wmk.ex.vo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Getter
@Setter
public class CartListVO {

//    cartNum     number          not null,
//    userId      varchar2(50)    not null,
//    gdsNum      number          not null,
//    gdsColor    varchar2(50)    null,
//    gdsSize     varchar2(50)    null,
//    gdsText     varchar2(50)    null,
//    cartStock   number          not null,
//    addDate     date            default sysdate,
//    primary key(cartNum, userId) 
	
	private int cartNum;
	private String userId;
	private int gdsNum;
	private int cartStock;
	private Date addDate;
	private int num;
	private String gdsName;
	private int gdsPrice;
	private String gdsImg;
	

	private String gdsColor;
	private String gdsSize;
	private String gdsText;

	


	
	

	
	
	
	
}