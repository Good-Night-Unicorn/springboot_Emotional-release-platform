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

import com.entity.DiscussqingxuxuanxieEntity;
import com.entity.view.DiscussqingxuxuanxieView;

import com.service.DiscussqingxuxuanxieService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 情绪宣泄评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
@RestController
@RequestMapping("/discussqingxuxuanxie")
public class DiscussqingxuxuanxieController {
    @Autowired
    private DiscussqingxuxuanxieService discussqingxuxuanxieService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussqingxuxuanxieEntity discussqingxuxuanxie,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussqingxuxuanxieEntity> ew = new EntityWrapper<DiscussqingxuxuanxieEntity>();


        //查询结果
		PageUtils page = discussqingxuxuanxieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqingxuxuanxie), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussqingxuxuanxieEntity discussqingxuxuanxie, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussqingxuxuanxieEntity> ew = new EntityWrapper<DiscussqingxuxuanxieEntity>();

        //查询结果
		PageUtils page = discussqingxuxuanxieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqingxuxuanxie), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussqingxuxuanxieEntity discussqingxuxuanxie){
       	EntityWrapper<DiscussqingxuxuanxieEntity> ew = new EntityWrapper<DiscussqingxuxuanxieEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussqingxuxuanxie, "discussqingxuxuanxie")); 
        return R.ok().put("data", discussqingxuxuanxieService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussqingxuxuanxieEntity discussqingxuxuanxie){
        EntityWrapper< DiscussqingxuxuanxieEntity> ew = new EntityWrapper< DiscussqingxuxuanxieEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussqingxuxuanxie, "discussqingxuxuanxie")); 
		DiscussqingxuxuanxieView discussqingxuxuanxieView =  discussqingxuxuanxieService.selectView(ew);
		return R.ok("查询情绪宣泄评论表成功").put("data", discussqingxuxuanxieView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussqingxuxuanxieEntity discussqingxuxuanxie = discussqingxuxuanxieService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqingxuxuanxie,deSens);
        return R.ok().put("data", discussqingxuxuanxie);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussqingxuxuanxieEntity discussqingxuxuanxie = discussqingxuxuanxieService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqingxuxuanxie,deSens);
        return R.ok().put("data", discussqingxuxuanxie);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增情绪宣泄评论表") 
    public R save(@RequestBody DiscussqingxuxuanxieEntity discussqingxuxuanxie, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqingxuxuanxie);
        discussqingxuxuanxieService.insert(discussqingxuxuanxie);
        return R.ok().put("data",discussqingxuxuanxie.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增情绪宣泄评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussqingxuxuanxieEntity discussqingxuxuanxie, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqingxuxuanxie);
        discussqingxuxuanxieService.insert(discussqingxuxuanxie);
        return R.ok().put("data",discussqingxuxuanxie.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussqingxuxuanxieEntity discussqingxuxuanxie = discussqingxuxuanxieService.selectOne(new EntityWrapper<DiscussqingxuxuanxieEntity>().eq("", username));
        return R.ok().put("data", discussqingxuxuanxie);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussqingxuxuanxieEntity discussqingxuxuanxie, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqingxuxuanxie);
        //全部更新
        discussqingxuxuanxieService.updateById(discussqingxuxuanxie);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除情绪宣泄评论表")
    public R delete(@RequestBody Long[] ids){
        discussqingxuxuanxieService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussqingxuxuanxieEntity discussqingxuxuanxie, HttpServletRequest request,String pre){
        EntityWrapper<DiscussqingxuxuanxieEntity> ew = new EntityWrapper<DiscussqingxuxuanxieEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussqingxuxuanxieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqingxuxuanxie), params), params));
        return R.ok().put("data", page);
    }








}
