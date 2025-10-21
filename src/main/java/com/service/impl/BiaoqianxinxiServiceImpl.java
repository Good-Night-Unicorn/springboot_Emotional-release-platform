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


import com.dao.BiaoqianxinxiDao;
import com.entity.BiaoqianxinxiEntity;
import com.service.BiaoqianxinxiService;
import com.entity.vo.BiaoqianxinxiVO;
import com.entity.view.BiaoqianxinxiView;

@Service("biaoqianxinxiService")
public class BiaoqianxinxiServiceImpl extends ServiceImpl<BiaoqianxinxiDao, BiaoqianxinxiEntity> implements BiaoqianxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BiaoqianxinxiEntity> page = this.selectPage(
                new Query<BiaoqianxinxiEntity>(params).getPage(),
                new EntityWrapper<BiaoqianxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BiaoqianxinxiEntity> wrapper) {
		  Page<BiaoqianxinxiView> page =new Query<BiaoqianxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BiaoqianxinxiVO> selectListVO(Wrapper<BiaoqianxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BiaoqianxinxiVO selectVO(Wrapper<BiaoqianxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BiaoqianxinxiView> selectListView(Wrapper<BiaoqianxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BiaoqianxinxiView selectView(Wrapper<BiaoqianxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
