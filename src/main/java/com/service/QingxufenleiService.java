package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QingxufenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QingxufenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QingxufenleiView;


/**
 * 情绪分类
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxufenleiService extends IService<QingxufenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QingxufenleiVO> selectListVO(Wrapper<QingxufenleiEntity> wrapper);
   	
   	QingxufenleiVO selectVO(@Param("ew") Wrapper<QingxufenleiEntity> wrapper);
   	
   	List<QingxufenleiView> selectListView(Wrapper<QingxufenleiEntity> wrapper);
   	
   	QingxufenleiView selectView(@Param("ew") Wrapper<QingxufenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QingxufenleiEntity> wrapper);

   	

}

