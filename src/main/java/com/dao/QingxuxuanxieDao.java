package com.dao;

import com.entity.QingxuxuanxieEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QingxuxuanxieVO;
import com.entity.view.QingxuxuanxieView;


/**
 * 情绪宣泄
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxuxuanxieDao extends BaseMapper<QingxuxuanxieEntity> {
	
	List<QingxuxuanxieVO> selectListVO(@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);
	
	QingxuxuanxieVO selectVO(@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);
	
	List<QingxuxuanxieView> selectListView(@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);

	List<QingxuxuanxieView> selectListView(Pagination page,@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);

	
	QingxuxuanxieView selectView(@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);



}
