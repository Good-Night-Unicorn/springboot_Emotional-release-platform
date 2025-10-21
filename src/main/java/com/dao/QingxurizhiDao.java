package com.dao;

import com.entity.QingxurizhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QingxurizhiVO;
import com.entity.view.QingxurizhiView;


/**
 * 情绪日志
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxurizhiDao extends BaseMapper<QingxurizhiEntity> {
	
	List<QingxurizhiVO> selectListVO(@Param("ew") Wrapper<QingxurizhiEntity> wrapper);
	
	QingxurizhiVO selectVO(@Param("ew") Wrapper<QingxurizhiEntity> wrapper);
	
	List<QingxurizhiView> selectListView(@Param("ew") Wrapper<QingxurizhiEntity> wrapper);

	List<QingxurizhiView> selectListView(Pagination page,@Param("ew") Wrapper<QingxurizhiEntity> wrapper);

	
	QingxurizhiView selectView(@Param("ew") Wrapper<QingxurizhiEntity> wrapper);
	

}
