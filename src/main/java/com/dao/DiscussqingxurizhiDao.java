package com.dao;

import com.entity.DiscussqingxurizhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussqingxurizhiVO;
import com.entity.view.DiscussqingxurizhiView;


/**
 * 情绪日志评论表
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
public interface DiscussqingxurizhiDao extends BaseMapper<DiscussqingxurizhiEntity> {
	
	List<DiscussqingxurizhiVO> selectListVO(@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);
	
	DiscussqingxurizhiVO selectVO(@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);
	
	List<DiscussqingxurizhiView> selectListView(@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);

	List<DiscussqingxurizhiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);

	
	DiscussqingxurizhiView selectView(@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);
	

}
