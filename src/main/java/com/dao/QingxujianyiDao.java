package com.dao;

import com.entity.QingxujianyiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QingxujianyiVO;
import com.entity.view.QingxujianyiView;


/**
 * 情绪建议
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxujianyiDao extends BaseMapper<QingxujianyiEntity> {
	
	List<QingxujianyiVO> selectListVO(@Param("ew") Wrapper<QingxujianyiEntity> wrapper);
	
	QingxujianyiVO selectVO(@Param("ew") Wrapper<QingxujianyiEntity> wrapper);
	
	List<QingxujianyiView> selectListView(@Param("ew") Wrapper<QingxujianyiEntity> wrapper);

	List<QingxujianyiView> selectListView(Pagination page,@Param("ew") Wrapper<QingxujianyiEntity> wrapper);

	
	QingxujianyiView selectView(@Param("ew") Wrapper<QingxujianyiEntity> wrapper);
	

}
