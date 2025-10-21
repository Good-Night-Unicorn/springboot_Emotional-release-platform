package com.entity.vo;

import com.entity.ExampaperEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 情绪测评表
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
public class ExampaperVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 考试时长(分钟)
	 */
	
	private Integer time;
		
	/**
	 * 情绪测评状态
	 */
	
	private Integer status;
				
	
	/**
	 * 设置：考试时长(分钟)
	 */
	 
	public void setTime(Integer time) {
		this.time = time;
	}
	
	/**
	 * 获取：考试时长(分钟)
	 */
	public Integer getTime() {
		return time;
	}
				
	
	/**
	 * 设置：情绪测评状态
	 */
	 
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 获取：情绪测评状态
	 */
	public Integer getStatus() {
		return status;
	}
			
}
