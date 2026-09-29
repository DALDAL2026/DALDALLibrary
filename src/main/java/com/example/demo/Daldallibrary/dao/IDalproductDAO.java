package com.example.demo.Daldallibrary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.Daldallibrary.dto.DalproductDTO;

@Mapper
public interface IDalproductDAO {
	public void insert(DalproductDTO pDTO);
	public List<DalproductDTO> list();
}
