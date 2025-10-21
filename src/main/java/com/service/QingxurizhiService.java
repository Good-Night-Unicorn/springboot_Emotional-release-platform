package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QingxurizhiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QingxurizhiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QingxurizhiView;


/**
 * 情绪日志
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxurizhiService extends IService<QingxurizhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QingxurizhiVO> selectListVO(Wrapper<QingxurizhiEntity> wrapper);
   	
   	QingxurizhiVO selectVO(@Param("ew") Wrapper<QingxurizhiEntity> wrapper);
   	
   	List<QingxurizhiView> selectListView(Wrapper<QingxurizhiEntity> wrapper);
   	
   	QingxurizhiView selectView(@Param("ew") Wrapper<QingxurizhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QingxurizhiEntity> wrapper);

   	

}

