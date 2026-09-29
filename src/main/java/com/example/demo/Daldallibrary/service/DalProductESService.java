package com.example.demo.Daldallibrary.service;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Daldallibrary.dto.DalproductDTO;

@Service
public class DalProductESService {
	@Autowired
	private RestHighLevelClient client;
	
	public void save(DalproductDTO dto) throws Exception{
		if(dto.getDalBno() == null) {
			throw new IllegalStateException("상품 인덱스가 null입니다.");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("dal_bno", dto.getDalBno());
		map.put("dal_bname", dto.getDalBname());
		map.put("dal_bwriter", dto.getDalBwriter());
		map.put("dal_bpub", dto.getDalBpub());
		map.put("dal_bcontent", dto.getDalBcontent());
		map.put("dal_bqul",dto.getDalBqul());
		map.put("dal_bprice", dto.getDalBprice());
		
		IndexRequest requqest = new IndexRequest("Dalproduct").id(dto.getDalBno().toString()).source(map);
	
		client.index(requqest, RequestOptions.DEFAULT);
	}
}
