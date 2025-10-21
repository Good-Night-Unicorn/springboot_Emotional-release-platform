package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BiaoqianxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BiaoqianxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BiaoqianxinxiView;


/**
 * 标签信息
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface BiaoqianxinxiService extends IService<BiaoqianxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BiaoqianxinxiVO> selectListVO(Wrapper<BiaoqianxinxiEntity> wrapper);
   	
   	BiaoqianxinxiVO selectVO(@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);
   	
   	List<BiaoqianxinxiView> selectListView(Wrapper<BiaoqianxinxiEntity> wrapper);
   	
   	BiaoqianxinxiView selectView(@Param("ew") Wrapper<BiaoqianxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BiaoqianxinxiEntity> wrapper);

   	

}

