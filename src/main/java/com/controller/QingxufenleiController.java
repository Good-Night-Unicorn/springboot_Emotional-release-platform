package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.QingxufenleiEntity;
import com.entity.view.QingxufenleiView;

import com.service.QingxufenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 情绪分类
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/qingxufenlei")
public class QingxufenleiController {
    @Autowired
    private QingxufenleiService qingxufenleiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QingxufenleiEntity qingxufenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QingxufenleiEntity> ew = new EntityWrapper<QingxufenleiEntity>();


        //查询结果
		PageUtils page = qingxufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxufenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QingxufenleiEntity qingxufenlei, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QingxufenleiEntity> ew = new EntityWrapper<QingxufenleiEntity>();

        //查询结果
		PageUtils page = qingxufenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxufenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QingxufenleiEntity qingxufenlei){
       	EntityWrapper<QingxufenleiEntity> ew = new EntityWrapper<QingxufenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qingxufenlei, "qingxufenlei")); 
        return R.ok().put("data", qingxufenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QingxufenleiEntity qingxufenlei){
        EntityWrapper< QingxufenleiEntity> ew = new EntityWrapper< QingxufenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qingxufenlei, "qingxufenlei")); 
		QingxufenleiView qingxufenleiView =  qingxufenleiService.selectView(ew);
		return R.ok("查询情绪分类成功").put("data", qingxufenleiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QingxufenleiEntity qingxufenlei = qingxufenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxufenlei,deSens);
        return R.ok().put("data", qingxufenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QingxufenleiEntity qingxufenlei = qingxufenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxufenlei,deSens);
        return R.ok().put("data", qingxufenlei);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增情绪分类") 
    public R save(@RequestBody QingxufenleiEntity qingxufenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(qingxufenleiService.selectCount(new EntityWrapper<QingxufenleiEntity>().eq("qingxufenlei", qingxufenlei.getQingxufenlei()))>0) {
            return R.error("情绪分类已存在");
        }
        //ValidatorUtils.validateEntity(qingxufenlei);
        qingxufenleiService.insert(qingxufenlei);
        return R.ok().put("data",qingxufenlei.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增情绪分类")
    @RequestMapping("/add")
    public R add(@RequestBody QingxufenleiEntity qingxufenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(qingxufenleiService.selectCount(new EntityWrapper<QingxufenleiEntity>().eq("qingxufenlei", qingxufenlei.getQingxufenlei()))>0) {
            return R.error("情绪分类已存在");
        }
        //ValidatorUtils.validateEntity(qingxufenlei);
        qingxufenleiService.insert(qingxufenlei);
        return R.ok().put("data",qingxufenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改情绪分类")
    public R update(@RequestBody QingxufenleiEntity qingxufenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxufenlei);
        //验证字段唯一性，否则返回错误信息
        if(qingxufenleiService.selectCount(new EntityWrapper<QingxufenleiEntity>().ne("id", qingxufenlei.getId()).eq("qingxufenlei", qingxufenlei.getQingxufenlei()))>0) {
            return R.error("情绪分类已存在");
        }
        //全部更新
        qingxufenleiService.updateById(qingxufenlei);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除情绪分类")
    public R delete(@RequestBody Long[] ids){
        qingxufenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
