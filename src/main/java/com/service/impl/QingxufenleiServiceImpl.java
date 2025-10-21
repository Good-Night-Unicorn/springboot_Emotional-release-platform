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


import com.dao.QingxufenleiDao;
import com.entity.QingxufenleiEntity;
import com.service.QingxufenleiService;
import com.entity.vo.QingxufenleiVO;
import com.entity.view.QingxufenleiView;

@Service("qingxufenleiService")
public class QingxufenleiServiceImpl extends ServiceImpl<QingxufenleiDao, QingxufenleiEntity> implements QingxufenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QingxufenleiEntity> page = this.selectPage(
                new Query<QingxufenleiEntity>(params).getPage(),
                new EntityWrapper<QingxufenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QingxufenleiEntity> wrapper) {
		  Page<QingxufenleiView> page =new Query<QingxufenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QingxufenleiVO> selectListVO(Wrapper<QingxufenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QingxufenleiVO selectVO(Wrapper<QingxufenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QingxufenleiView> selectListView(Wrapper<QingxufenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QingxufenleiView selectView(Wrapper<QingxufenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
