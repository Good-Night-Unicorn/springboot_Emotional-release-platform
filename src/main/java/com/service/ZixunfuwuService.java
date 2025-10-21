package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZixunfuwuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZixunfuwuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZixunfuwuView;


/**
 * 咨询服务
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface ZixunfuwuService extends IService<ZixunfuwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZixunfuwuVO> selectListVO(Wrapper<ZixunfuwuEntity> wrapper);
   	
   	ZixunfuwuVO selectVO(@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);
   	
   	List<ZixunfuwuView> selectListView(Wrapper<ZixunfuwuEntity> wrapper);
   	
   	ZixunfuwuView selectView(@Param("ew") Wrapper<ZixunfuwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZixunfuwuEntity> wrapper);

   	

}

