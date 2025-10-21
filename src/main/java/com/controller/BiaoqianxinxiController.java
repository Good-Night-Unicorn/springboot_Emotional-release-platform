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

import com.entity.BiaoqianxinxiEntity;
import com.entity.view.BiaoqianxinxiView;

import com.service.BiaoqianxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 标签信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/biaoqianxinxi")
public class BiaoqianxinxiController {
    @Autowired
    private BiaoqianxinxiService biaoqianxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BiaoqianxinxiEntity biaoqianxinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BiaoqianxinxiEntity> ew = new EntityWrapper<BiaoqianxinxiEntity>();


        //查询结果
		PageUtils page = biaoqianxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, biaoqianxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BiaoqianxinxiEntity biaoqianxinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BiaoqianxinxiEntity> ew = new EntityWrapper<BiaoqianxinxiEntity>();

        //查询结果
		PageUtils page = biaoqianxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, biaoqianxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BiaoqianxinxiEntity biaoqianxinxi){
       	EntityWrapper<BiaoqianxinxiEntity> ew = new EntityWrapper<BiaoqianxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( biaoqianxinxi, "biaoqianxinxi")); 
        return R.ok().put("data", biaoqianxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BiaoqianxinxiEntity biaoqianxinxi){
        EntityWrapper< BiaoqianxinxiEntity> ew = new EntityWrapper< BiaoqianxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( biaoqianxinxi, "biaoqianxinxi")); 
		BiaoqianxinxiView biaoqianxinxiView =  biaoqianxinxiService.selectView(ew);
		return R.ok("查询标签信息成功").put("data", biaoqianxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BiaoqianxinxiEntity biaoqianxinxi = biaoqianxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(biaoqianxinxi,deSens);
        return R.ok().put("data", biaoqianxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BiaoqianxinxiEntity biaoqianxinxi = biaoqianxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(biaoqianxinxi,deSens);
        return R.ok().put("data", biaoqianxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增标签信息") 
    public R save(@RequestBody BiaoqianxinxiEntity biaoqianxinxi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(biaoqianxinxiService.selectCount(new EntityWrapper<BiaoqianxinxiEntity>().eq("biaoqian", biaoqianxinxi.getBiaoqian()))>0) {
            return R.error("标签已存在");
        }
        //ValidatorUtils.validateEntity(biaoqianxinxi);
        biaoqianxinxiService.insert(biaoqianxinxi);
        return R.ok().put("data",biaoqianxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增标签信息")
    @RequestMapping("/add")
    public R add(@RequestBody BiaoqianxinxiEntity biaoqianxinxi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(biaoqianxinxiService.selectCount(new EntityWrapper<BiaoqianxinxiEntity>().eq("biaoqian", biaoqianxinxi.getBiaoqian()))>0) {
            return R.error("标签已存在");
        }
        //ValidatorUtils.validateEntity(biaoqianxinxi);
        biaoqianxinxiService.insert(biaoqianxinxi);
        return R.ok().put("data",biaoqianxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改标签信息")
    public R update(@RequestBody BiaoqianxinxiEntity biaoqianxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(biaoqianxinxi);
        //验证字段唯一性，否则返回错误信息
        if(biaoqianxinxiService.selectCount(new EntityWrapper<BiaoqianxinxiEntity>().ne("id", biaoqianxinxi.getId()).eq("biaoqian", biaoqianxinxi.getBiaoqian()))>0) {
            return R.error("标签已存在");
        }
        //全部更新
        biaoqianxinxiService.updateById(biaoqianxinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除标签信息")
    public R delete(@RequestBody Long[] ids){
        biaoqianxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
