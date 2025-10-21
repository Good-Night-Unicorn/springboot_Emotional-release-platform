package com.entity.view;

import com.entity.QingxurizhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 情绪日志
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@TableName("qingxurizhi")
public class QingxurizhiView  extends QingxurizhiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public QingxurizhiView(){
	}
 
 	public QingxurizhiView(QingxurizhiEntity qingxurizhiEntity){
 	try {
			BeanUtils.copyProperties(this, qingxurizhiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
