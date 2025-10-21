package com.dao;

import com.entity.SensitivewordsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.SensitivewordsVO;
import com.entity.view.SensitivewordsView;


/**
 * 敏感词
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
public interface SensitivewordsDao extends BaseMapper<SensitivewordsEntity> {
	
	List<SensitivewordsVO> selectListVO(@Param("ew") Wrapper<SensitivewordsEntity> wrapper);
	
	SensitivewordsVO selectVO(@Param("ew") Wrapper<SensitivewordsEntity> wrapper);
	
	List<SensitivewordsView> selectListView(@Param("ew") Wrapper<SensitivewordsEntity> wrapper);

	List<SensitivewordsView> selectListView(Pagination page,@Param("ew") Wrapper<SensitivewordsEntity> wrapper);

	
	SensitivewordsView selectView(@Param("ew") Wrapper<SensitivewordsEntity> wrapper);
	

}
