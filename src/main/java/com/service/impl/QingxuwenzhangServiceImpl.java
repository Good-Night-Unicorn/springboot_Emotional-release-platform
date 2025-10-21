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


import com.dao.QingxuwenzhangDao;
import com.entity.QingxuwenzhangEntity;
import com.service.QingxuwenzhangService;
import com.entity.vo.QingxuwenzhangVO;
import com.entity.view.QingxuwenzhangView;

@Service("qingxuwenzhangService")
public class QingxuwenzhangServiceImpl extends ServiceImpl<QingxuwenzhangDao, QingxuwenzhangEntity> implements QingxuwenzhangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QingxuwenzhangEntity> page = this.selectPage(
                new Query<QingxuwenzhangEntity>(params).getPage(),
                new EntityWrapper<QingxuwenzhangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QingxuwenzhangEntity> wrapper) {
		  Page<QingxuwenzhangView> page =new Query<QingxuwenzhangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<QingxuwenzhangVO> selectListVO(Wrapper<QingxuwenzhangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QingxuwenzhangVO selectVO(Wrapper<QingxuwenzhangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QingxuwenzhangView> selectListView(Wrapper<QingxuwenzhangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QingxuwenzhangView selectView(Wrapper<QingxuwenzhangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
