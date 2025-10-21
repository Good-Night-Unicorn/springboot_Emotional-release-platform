package com.dao;

import com.entity.QingxufenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QingxufenleiVO;
import com.entity.view.QingxufenleiView;


/**
 * 情绪分类
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxufenleiDao extends BaseMapper<QingxufenleiEntity> {
	
	List<QingxufenleiVO> selectListVO(@Param("ew") Wrapper<QingxufenleiEntity> wrapper);
	
	QingxufenleiVO selectVO(@Param("ew") Wrapper<QingxufenleiEntity> wrapper);
	
	List<QingxufenleiView> selectListView(@Param("ew") Wrapper<QingxufenleiEntity> wrapper);

	List<QingxufenleiView> selectListView(Pagination page,@Param("ew") Wrapper<QingxufenleiEntity> wrapper);

	
	QingxufenleiView selectView(@Param("ew") Wrapper<QingxufenleiEntity> wrapper);
	

}
