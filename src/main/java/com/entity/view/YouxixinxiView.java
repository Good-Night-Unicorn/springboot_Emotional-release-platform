package com.entity.view;

import com.entity.YouxixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 游戏信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
@TableName("youxixinxi")
public class YouxixinxiView  extends YouxixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public YouxixinxiView(){
	}
 
 	public YouxixinxiView(YouxixinxiEntity youxixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, youxixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
