package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussqingxurizhiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussqingxurizhiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussqingxurizhiView;


/**
 * 情绪日志评论表
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
public interface DiscussqingxurizhiService extends IService<DiscussqingxurizhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussqingxurizhiVO> selectListVO(Wrapper<DiscussqingxurizhiEntity> wrapper);
   	
   	DiscussqingxurizhiVO selectVO(@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);
   	
   	List<DiscussqingxurizhiView> selectListView(Wrapper<DiscussqingxurizhiEntity> wrapper);
   	
   	DiscussqingxurizhiView selectView(@Param("ew") Wrapper<DiscussqingxurizhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussqingxurizhiEntity> wrapper);

   	

}

