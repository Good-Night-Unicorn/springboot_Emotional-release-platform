package com.entity.view;

import com.entity.DiscussqingxuwenzhangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 情绪文章评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
@TableName("discussqingxuwenzhang")
public class DiscussqingxuwenzhangView  extends DiscussqingxuwenzhangEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussqingxuwenzhangView(){
	}
 
 	public DiscussqingxuwenzhangView(DiscussqingxuwenzhangEntity discussqingxuwenzhangEntity){
 	try {
			BeanUtils.copyProperties(this, discussqingxuwenzhangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
