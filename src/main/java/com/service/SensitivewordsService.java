package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.SensitivewordsEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.SensitivewordsVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.SensitivewordsView;


/**
 * 敏感词
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
public interface SensitivewordsService extends IService<SensitivewordsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<SensitivewordsVO> selectListVO(Wrapper<SensitivewordsEntity> wrapper);
   	
   	SensitivewordsVO selectVO(@Param("ew") Wrapper<SensitivewordsEntity> wrapper);
   	
   	List<SensitivewordsView> selectListView(Wrapper<SensitivewordsEntity> wrapper);
   	
   	SensitivewordsView selectView(@Param("ew") Wrapper<SensitivewordsEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<SensitivewordsEntity> wrapper);

   	

}

