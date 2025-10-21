package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QingxuxuanxieEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QingxuxuanxieVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QingxuxuanxieView;


/**
 * 情绪宣泄
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxuxuanxieService extends IService<QingxuxuanxieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QingxuxuanxieVO> selectListVO(Wrapper<QingxuxuanxieEntity> wrapper);
   	
   	QingxuxuanxieVO selectVO(@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);
   	
   	List<QingxuxuanxieView> selectListView(Wrapper<QingxuxuanxieEntity> wrapper);
   	
   	QingxuxuanxieView selectView(@Param("ew") Wrapper<QingxuxuanxieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QingxuxuanxieEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<QingxuxuanxieEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<QingxuxuanxieEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<QingxuxuanxieEntity> wrapper);



}

