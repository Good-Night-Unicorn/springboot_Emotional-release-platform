package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QingxujianyiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QingxujianyiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QingxujianyiView;


/**
 * 情绪建议
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxujianyiService extends IService<QingxujianyiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QingxujianyiVO> selectListVO(Wrapper<QingxujianyiEntity> wrapper);
   	
   	QingxujianyiVO selectVO(@Param("ew") Wrapper<QingxujianyiEntity> wrapper);
   	
   	List<QingxujianyiView> selectListView(Wrapper<QingxujianyiEntity> wrapper);
   	
   	QingxujianyiView selectView(@Param("ew") Wrapper<QingxujianyiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QingxujianyiEntity> wrapper);

   	

}

