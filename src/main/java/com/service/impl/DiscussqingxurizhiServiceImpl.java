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


import com.dao.DiscussqingxurizhiDao;
import com.entity.DiscussqingxurizhiEntity;
import com.service.DiscussqingxurizhiService;
import com.entity.vo.DiscussqingxurizhiVO;
import com.entity.view.DiscussqingxurizhiView;

@Service("discussqingxurizhiService")
public class DiscussqingxurizhiServiceImpl extends ServiceImpl<DiscussqingxurizhiDao, DiscussqingxurizhiEntity> implements DiscussqingxurizhiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussqingxurizhiEntity> page = this.selectPage(
                new Query<DiscussqingxurizhiEntity>(params).getPage(),
                new EntityWrapper<DiscussqingxurizhiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussqingxurizhiEntity> wrapper) {
		  Page<DiscussqingxurizhiView> page =new Query<DiscussqingxurizhiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussqingxurizhiVO> selectListVO(Wrapper<DiscussqingxurizhiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussqingxurizhiVO selectVO(Wrapper<DiscussqingxurizhiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussqingxurizhiView> selectListView(Wrapper<DiscussqingxurizhiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussqingxurizhiView selectView(Wrapper<DiscussqingxurizhiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
