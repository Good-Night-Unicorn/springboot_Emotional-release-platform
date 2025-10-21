package com.dao;

import com.entity.ZixunshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZixunshiVO;
import com.entity.view.ZixunshiView;


/**
 * 咨询师
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface ZixunshiDao extends BaseMapper<ZixunshiEntity> {
	
	List<ZixunshiVO> selectListVO(@Param("ew") Wrapper<ZixunshiEntity> wrapper);
	
	ZixunshiVO selectVO(@Param("ew") Wrapper<ZixunshiEntity> wrapper);
	
	List<ZixunshiView> selectListView(@Param("ew") Wrapper<ZixunshiEntity> wrapper);

	List<ZixunshiView> selectListView(Pagination page,@Param("ew") Wrapper<ZixunshiEntity> wrapper);

	
	ZixunshiView selectView(@Param("ew") Wrapper<ZixunshiEntity> wrapper);
	

}
