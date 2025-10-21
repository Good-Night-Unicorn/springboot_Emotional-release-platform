package com.dao;

import com.entity.YuyuezixunshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YuyuezixunshiVO;
import com.entity.view.YuyuezixunshiView;


/**
 * 预约咨询师
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface YuyuezixunshiDao extends BaseMapper<YuyuezixunshiEntity> {
	
	List<YuyuezixunshiVO> selectListVO(@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);
	
	YuyuezixunshiVO selectVO(@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);
	
	List<YuyuezixunshiView> selectListView(@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);

	List<YuyuezixunshiView> selectListView(Pagination page,@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);

	
	YuyuezixunshiView selectView(@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);



}
