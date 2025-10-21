package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuyuezixunshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YuyuezixunshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YuyuezixunshiView;


/**
 * 预约咨询师
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface YuyuezixunshiService extends IService<YuyuezixunshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuyuezixunshiVO> selectListVO(Wrapper<YuyuezixunshiEntity> wrapper);
   	
   	YuyuezixunshiVO selectVO(@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);
   	
   	List<YuyuezixunshiView> selectListView(Wrapper<YuyuezixunshiEntity> wrapper);
   	
   	YuyuezixunshiView selectView(@Param("ew") Wrapper<YuyuezixunshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuyuezixunshiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YuyuezixunshiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YuyuezixunshiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YuyuezixunshiEntity> wrapper);



}

