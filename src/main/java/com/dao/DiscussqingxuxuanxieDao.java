package com.dao;

import com.entity.DiscussqingxuxuanxieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussqingxuxuanxieVO;
import com.entity.view.DiscussqingxuxuanxieView;


/**
 * 情绪宣泄评论表
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
public interface DiscussqingxuxuanxieDao extends BaseMapper<DiscussqingxuxuanxieEntity> {
	
	List<DiscussqingxuxuanxieVO> selectListVO(@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);
	
	DiscussqingxuxuanxieVO selectVO(@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);
	
	List<DiscussqingxuxuanxieView> selectListView(@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);

	List<DiscussqingxuxuanxieView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);

	
	DiscussqingxuxuanxieView selectView(@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);
	

}
