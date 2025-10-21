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


import com.dao.YuyuezixunshiDao;
import com.entity.YuyuezixunshiEntity;
import com.service.YuyuezixunshiService;
import com.entity.vo.YuyuezixunshiVO;
import com.entity.view.YuyuezixunshiView;

@Service("yuyuezixunshiService")
public class YuyuezixunshiServiceImpl extends ServiceImpl<YuyuezixunshiDao, YuyuezixunshiEntity> implements YuyuezixunshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuyuezixunshiEntity> page = this.selectPage(
                new Query<YuyuezixunshiEntity>(params).getPage(),
                new EntityWrapper<YuyuezixunshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuyuezixunshiEntity> wrapper) {
		  Page<YuyuezixunshiView> page =new Query<YuyuezixunshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YuyuezixunshiVO> selectListVO(Wrapper<YuyuezixunshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YuyuezixunshiVO selectVO(Wrapper<YuyuezixunshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YuyuezixunshiView> selectListView(Wrapper<YuyuezixunshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuyuezixunshiView selectView(Wrapper<YuyuezixunshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YuyuezixunshiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YuyuezixunshiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YuyuezixunshiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
