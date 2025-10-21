package com.entity.view;

import com.entity.DiscussqingxurizhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 情绪日志评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
@TableName("discussqingxurizhi")
public class DiscussqingxurizhiView  extends DiscussqingxurizhiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussqingxurizhiView(){
	}
 
 	public DiscussqingxurizhiView(DiscussqingxurizhiEntity discussqingxurizhiEntity){
 	try {
			BeanUtils.copyProperties(this, discussqingxurizhiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
