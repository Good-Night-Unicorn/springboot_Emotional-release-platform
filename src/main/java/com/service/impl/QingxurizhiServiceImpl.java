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


import com.dao.QingxurizhiDao;
import com.entity.QingxurizhiEntity;
import com.service.QingxurizhiService;
import com.entity.vo.QingxurizhiVO;
import com.entity.view.QingxurizhiView;

@Service("qingxurizhiService")
public class QingxurizhiServiceImpl extends ServiceImpl<QingxurizhiDao, QingxurizhiEntity> implements QingxurizhiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QingxurizhiEntity> page = this.selectPage(
                new Query<QingxurizhiEntity>(params).getPage(),
                new EntityWrapper<QingxurizhiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QingxurizhiEntity> wrapper) {
		  Page<QingxurizhiView> page =new Query<QingxurizhiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QingxurizhiVO> selectListVO(Wrapper<QingxurizhiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QingxurizhiVO selectVO(Wrapper<QingxurizhiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QingxurizhiView> selectListView(Wrapper<QingxurizhiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QingxurizhiView selectView(Wrapper<QingxurizhiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
