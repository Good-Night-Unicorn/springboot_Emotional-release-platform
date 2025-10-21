package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussqingxuwenzhangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussqingxuwenzhangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussqingxuwenzhangView;


/**
 * 情绪文章评论表
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
public interface DiscussqingxuwenzhangService extends IService<DiscussqingxuwenzhangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussqingxuwenzhangVO> selectListVO(Wrapper<DiscussqingxuwenzhangEntity> wrapper);
   	
   	DiscussqingxuwenzhangVO selectVO(@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);
   	
   	List<DiscussqingxuwenzhangView> selectListView(Wrapper<DiscussqingxuwenzhangEntity> wrapper);
   	
   	DiscussqingxuwenzhangView selectView(@Param("ew") Wrapper<DiscussqingxuwenzhangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussqingxuwenzhangEntity> wrapper);

   	

}

