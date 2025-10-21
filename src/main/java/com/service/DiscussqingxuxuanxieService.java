package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussqingxuxuanxieEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussqingxuxuanxieVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussqingxuxuanxieView;


/**
 * 情绪宣泄评论表
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
public interface DiscussqingxuxuanxieService extends IService<DiscussqingxuxuanxieEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussqingxuxuanxieVO> selectListVO(Wrapper<DiscussqingxuxuanxieEntity> wrapper);
   	
   	DiscussqingxuxuanxieVO selectVO(@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);
   	
   	List<DiscussqingxuxuanxieView> selectListView(Wrapper<DiscussqingxuxuanxieEntity> wrapper);
   	
   	DiscussqingxuxuanxieView selectView(@Param("ew") Wrapper<DiscussqingxuxuanxieEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussqingxuxuanxieEntity> wrapper);

   	

}

