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


import com.dao.QingxujianyiDao;
import com.entity.QingxujianyiEntity;
import com.service.QingxujianyiService;
import com.entity.vo.QingxujianyiVO;
import com.entity.view.QingxujianyiView;

@Service("qingxujianyiService")
public class QingxujianyiServiceImpl extends ServiceImpl<QingxujianyiDao, QingxujianyiEntity> implements QingxujianyiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QingxujianyiEntity> page = this.selectPage(
                new Query<QingxujianyiEntity>(params).getPage(),
                new EntityWrapper<QingxujianyiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QingxujianyiEntity> wrapper) {
		  Page<QingxujianyiView> page =new Query<QingxujianyiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QingxujianyiVO> selectListVO(Wrapper<QingxujianyiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QingxujianyiVO selectVO(Wrapper<QingxujianyiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QingxujianyiView> selectListView(Wrapper<QingxujianyiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QingxujianyiView selectView(Wrapper<QingxujianyiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
