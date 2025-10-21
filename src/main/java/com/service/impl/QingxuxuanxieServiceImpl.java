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


import com.dao.QingxuxuanxieDao;
import com.entity.QingxuxuanxieEntity;
import com.service.QingxuxuanxieService;
import com.entity.vo.QingxuxuanxieVO;
import com.entity.view.QingxuxuanxieView;

@Service("qingxuxuanxieService")
public class QingxuxuanxieServiceImpl extends ServiceImpl<QingxuxuanxieDao, QingxuxuanxieEntity> implements QingxuxuanxieService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QingxuxuanxieEntity> page = this.selectPage(
                new Query<QingxuxuanxieEntity>(params).getPage(),
                new EntityWrapper<QingxuxuanxieEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QingxuxuanxieEntity> wrapper) {
		  Page<QingxuxuanxieView> page =new Query<QingxuxuanxieView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QingxuxuanxieVO> selectListVO(Wrapper<QingxuxuanxieEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QingxuxuanxieVO selectVO(Wrapper<QingxuxuanxieEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QingxuxuanxieView> selectListView(Wrapper<QingxuxuanxieEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QingxuxuanxieView selectView(Wrapper<QingxuxuanxieEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<QingxuxuanxieEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<QingxuxuanxieEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<QingxuxuanxieEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
