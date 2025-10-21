package com.entity.vo;

import com.entity.YouxixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 游戏信息
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
public class YouxixinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 游戏分类
	 */
	
	private String youxifenlei;
		
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 制作
	 */
	
	private String zhizuo;
		
	/**
	 * 链接
	 */
	
	private String lianjie;
		
	/**
	 * 游戏视频
	 */
	
	private String youxishipin;
		
	/**
	 * 发售日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fashouriqi;
		
	/**
	 * 游戏详情
	 */
	
	private String youxixiangqing;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：游戏分类
	 */
	 
	public void setYouxifenlei(String youxifenlei) {
		this.youxifenlei = youxifenlei;
	}
	
	/**
	 * 获取：游戏分类
	 */
	public String getYouxifenlei() {
		return youxifenlei;
	}
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：制作
	 */
	 
	public void setZhizuo(String zhizuo) {
		this.zhizuo = zhizuo;
	}
	
	/**
	 * 获取：制作
	 */
	public String getZhizuo() {
		return zhizuo;
	}
				
	
	/**
	 * 设置：链接
	 */
	 
	public void setLianjie(String lianjie) {
		this.lianjie = lianjie;
	}
	
	/**
	 * 获取：链接
	 */
	public String getLianjie() {
		return lianjie;
	}
				
	
	/**
	 * 设置：游戏视频
	 */
	 
	public void setYouxishipin(String youxishipin) {
		this.youxishipin = youxishipin;
	}
	
	/**
	 * 获取：游戏视频
	 */
	public String getYouxishipin() {
		return youxishipin;
	}
				
	
	/**
	 * 设置：发售日期
	 */
	 
	public void setFashouriqi(Date fashouriqi) {
		this.fashouriqi = fashouriqi;
	}
	
	/**
	 * 获取：发售日期
	 */
	public Date getFashouriqi() {
		return fashouriqi;
	}
				
	
	/**
	 * 设置：游戏详情
	 */
	 
	public void setYouxixiangqing(String youxixiangqing) {
		this.youxixiangqing = youxixiangqing;
	}
	
	/**
	 * 获取：游戏详情
	 */
	public String getYouxixiangqing() {
		return youxixiangqing;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
