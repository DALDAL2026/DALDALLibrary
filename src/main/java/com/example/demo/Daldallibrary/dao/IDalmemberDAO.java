package com.example.demo.Daldallibrary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Daldallibrary.dto.DalmemberDTO;

@Mapper
public interface IDalmemberDAO {
	//로그인용 조회
	public DalmemberDTO findById(String dalMe);
		
	// 회원 조회
	public List<DalmemberDTO> memberList();
	
	// 회원 등록
	public int memberWrite(DalmemberDTO DALdto);
	
	// 회원 정보 수정
	public int memberUpdate(DalmemberDTO DALdto);
	
	// 회원 삭제
	public int memberDelete(String dalMe);
}
