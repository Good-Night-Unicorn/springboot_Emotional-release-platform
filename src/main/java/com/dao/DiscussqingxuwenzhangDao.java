package com.dao;

import com.entity.DiscussqingxuwenzhangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussqingxuwenzhangVO;
import com.entity.view.DiscussqingxuwenzhangView;


/**
 * 情绪文章评论表
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
public interface DiscussqingxuwenzhangDao extends BaseMapper<DiscussqingxuwenzhangEntity> {
	
	List<DiscussqingxuwenzhangVO> selectListVO(@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);
	
	DiscussqingxuwenzhangVO selectVO(@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);
	
	List<DiscussqingxuwenzhangView> selectListView(@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);

	List<DiscussqingxuwenzhangView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);

	
	DiscussqingxuwenzhangView selectView(@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);
	

}
