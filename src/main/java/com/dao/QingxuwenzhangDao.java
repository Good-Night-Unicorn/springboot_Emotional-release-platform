package com.dao;

import com.entity.QingxuwenzhangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QingxuwenzhangVO;
import com.entity.view.QingxuwenzhangView;


/**
 * 情绪文章
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxuwenzhangDao extends BaseMapper<QingxuwenzhangEntity> {
	
	List<QingxuwenzhangVO> selectListVO(@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);
	
	QingxuwenzhangVO selectVO(@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);
	
	List<QingxuwenzhangView> selectListView(@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);

	List<QingxuwenzhangView> selectListView(Pagination page,@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);

	
	QingxuwenzhangView selectView(@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);
	

}
