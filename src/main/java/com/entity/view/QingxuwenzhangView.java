package com.entity.view;

import com.entity.QingxuwenzhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 情绪文章
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@TableName("qingxuwenzhang")
public class QingxuwenzhangView  extends QingxuwenzhangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public QingxuwenzhangView(){
	}
 
 	public QingxuwenzhangView(QingxuwenzhangEntity qingxuwenzhangEntity){
 	try {
			BeanUtils.copyProperties(this, qingxuwenzhangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
