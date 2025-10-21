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


import com.dao.DiscussqingxuxuanxieDao;
import com.entity.DiscussqingxuxuanxieEntity;
import com.service.DiscussqingxuxuanxieService;
import com.entity.vo.DiscussqingxuxuanxieVO;
import com.entity.view.DiscussqingxuxuanxieView;

@Service("discussqingxuxuanxieService")
public class DiscussqingxuxuanxieServiceImpl extends ServiceImpl<DiscussqingxuxuanxieDao, DiscussqingxuxuanxieEntity> implements DiscussqingxuxuanxieService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussqingxuxuanxieEntity> page = this.selectPage(
                new Query<DiscussqingxuxuanxieEntity>(params).getPage(),
                new EntityWrapper<DiscussqingxuxuanxieEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussqingxuxuanxieEntity> wrapper) {
		  Page<DiscussqingxuxuanxieView> page =new Query<DiscussqingxuxuanxieView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussqingxuxuanxieVO> selectListVO(Wrapper<DiscussqingxuxuanxieEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussqingxuxuanxieVO selectVO(Wrapper<DiscussqingxuxuanxieEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussqingxuxuanxieView> selectListView(Wrapper<DiscussqingxuxuanxieEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussqingxuxuanxieView selectView(Wrapper<DiscussqingxuxuanxieEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
