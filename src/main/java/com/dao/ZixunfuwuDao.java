package com.dao;

import com.entity.ZixunfuwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZixunfuwuVO;
import com.entity.view.ZixunfuwuView;


/**
 * 咨询服务
 * 
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface ZixunfuwuDao extends BaseMapper<ZixunfuwuEntity> {
	
	List<ZixunfuwuVO> selectListVO(@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);
	
	ZixunfuwuVO selectVO(@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);
	
	List<ZixunfuwuView> selectListView(@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);

	List<ZixunfuwuView> selectListView(Pagination page,@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);

	
	ZixunfuwuView selectView(@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);
	

}
