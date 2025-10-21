package com.dao;

import com.entity.BiaoqianxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BiaoqianxinxiVO;
import com.entity.view.BiaoqianxinxiView;


/**
 * 标签信息
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface BiaoqianxinxiDao extends BaseMapper<BiaoqianxinxiEntity> {
	
	List<BiaoqianxinxiVO> selectListVO(@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);
	
	BiaoqianxinxiVO selectVO(@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);
	
	List<BiaoqianxinxiView> selectListView(@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);

	List<BiaoqianxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);

	
	BiaoqianxinxiView selectView(@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);
	

}
