package com.study.shop.item.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.shop.item.vo.CategoryVO;
import com.study.shop.item.vo.ItemVO;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<CategoryVO> cateList(){
		
		return sqlSession.selectList("itemMapper.cateList");
	}

	@Override
	public List<ItemVO> itemListForUser() {

		return sqlSession.selectList("itemMapper.itemListForUser");
	}

	@Override
	public ItemVO itemDetail(String itemCode) {
		return sqlSession.selectOne("itemMapper.itemDetail", itemCode);
	}





	
}
