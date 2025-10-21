package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.QingxuwenzhangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.QingxuwenzhangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.QingxuwenzhangView;


/**
 * 情绪文章
 *
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
public interface QingxuwenzhangService extends IService<QingxuwenzhangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QingxuwenzhangVO> selectListVO(Wrapper<QingxuwenzhangEntity> wrapper);
   	
   	QingxuwenzhangVO selectVO(@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);
   	
   	List<QingxuwenzhangView> selectListView(Wrapper<QingxuwenzhangEntity> wrapper);
   	
   	QingxuwenzhangView selectView(@Param("ew") Wrapper<QingxuwenzhangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QingxuwenzhangEntity> wrapper);

   	

}

