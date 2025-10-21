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

import com.entity.QingxujianyiEntity;
import com.entity.view.QingxujianyiView;

import com.service.QingxujianyiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 情绪建议
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/qingxujianyi")
public class QingxujianyiController {
    @Autowired
    private QingxujianyiService qingxujianyiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QingxujianyiEntity qingxujianyi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			qingxujianyi.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("zixunshi")) {
			qingxujianyi.setZixunshizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<QingxujianyiEntity> ew = new EntityWrapper<QingxujianyiEntity>();


        //查询结果
		PageUtils page = qingxujianyiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxujianyi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QingxujianyiEntity qingxujianyi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QingxujianyiEntity> ew = new EntityWrapper<QingxujianyiEntity>();

        //查询结果
		PageUtils page = qingxujianyiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxujianyi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QingxujianyiEntity qingxujianyi){
       	EntityWrapper<QingxujianyiEntity> ew = new EntityWrapper<QingxujianyiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qingxujianyi, "qingxujianyi")); 
        return R.ok().put("data", qingxujianyiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QingxujianyiEntity qingxujianyi){
        EntityWrapper< QingxujianyiEntity> ew = new EntityWrapper< QingxujianyiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qingxujianyi, "qingxujianyi")); 
		QingxujianyiView qingxujianyiView =  qingxujianyiService.selectView(ew);
		return R.ok("查询情绪建议成功").put("data", qingxujianyiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QingxujianyiEntity qingxujianyi = qingxujianyiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxujianyi,deSens);
        return R.ok().put("data", qingxujianyi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QingxujianyiEntity qingxujianyi = qingxujianyiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxujianyi,deSens);
        return R.ok().put("data", qingxujianyi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增情绪建议") 
    public R save(@RequestBody QingxujianyiEntity qingxujianyi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxujianyi);
        qingxujianyiService.insert(qingxujianyi);
        return R.ok().put("data",qingxujianyi.getId());
    }
    
    /**
     * 前台保存
     */
    @IgnoreAuth
    @RequestMapping("/add")
    public R add(@RequestBody QingxujianyiEntity qingxujianyi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxujianyi);
        qingxujianyiService.insert(qingxujianyi);
        return R.ok().put("data",qingxujianyi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改情绪建议")
    public R update(@RequestBody QingxujianyiEntity qingxujianyi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxujianyi);
        //全部更新
        qingxujianyiService.updateById(qingxujianyi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除情绪建议")
    public R delete(@RequestBody Long[] ids){
        qingxujianyiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
