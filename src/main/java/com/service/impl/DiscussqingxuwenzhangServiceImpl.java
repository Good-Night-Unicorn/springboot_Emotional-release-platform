package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussqingxuwenzhangDao;
import com.entity.DiscussqingxuwenzhangEntity;
import com.service.DiscussqingxuwenzhangService;
import com.entity.vo.DiscussqingxuwenzhangVO;
import com.entity.view.DiscussqingxuwenzhangView;

@Service("discussqingxuwenzhangService")
public class DiscussqingxuwenzhangServiceImpl extends ServiceImpl<DiscussqingxuwenzhangDao, DiscussqingxuwenzhangEntity> implements DiscussqingxuwenzhangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussqingxuwenzhangEntity> page = this.selectPage(
                new Query<DiscussqingxuwenzhangEntity>(params).getPage(),
                new EntityWrapper<DiscussqingxuwenzhangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussqingxuwenzhangEntity> wrapper) {
		  Page<DiscussqingxuwenzhangView> page =new Query<DiscussqingxuwenzhangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussqingxuwenzhangVO> selectListVO(Wrapper<DiscussqingxuwenzhangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussqingxuwenzhangVO selectVO(Wrapper<DiscussqingxuwenzhangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussqingxuwenzhangView> selectListView(Wrapper<DiscussqingxuwenzhangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussqingxuwenzhangView selectView(Wrapper<DiscussqingxuwenzhangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
