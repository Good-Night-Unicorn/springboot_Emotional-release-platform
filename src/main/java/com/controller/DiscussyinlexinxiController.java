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

import com.entity.DiscussyinlexinxiEntity;
import com.entity.view.DiscussyinlexinxiView;

import com.service.DiscussyinlexinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 音乐信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
@RestController
@RequestMapping("/discussyinlexinxi")
public class DiscussyinlexinxiController {
    @Autowired
    private DiscussyinlexinxiService discussyinlexinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyinlexinxiEntity discussyinlexinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyinlexinxiEntity> ew = new EntityWrapper<DiscussyinlexinxiEntity>();


        //查询结果
		PageUtils page = discussyinlexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyinlexinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussyinlexinxiEntity discussyinlexinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyinlexinxiEntity> ew = new EntityWrapper<DiscussyinlexinxiEntity>();

        //查询结果
		PageUtils page = discussyinlexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyinlexinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyinlexinxiEntity discussyinlexinxi){
       	EntityWrapper<DiscussyinlexinxiEntity> ew = new EntityWrapper<DiscussyinlexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyinlexinxi, "discussyinlexinxi")); 
        return R.ok().put("data", discussyinlexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyinlexinxiEntity discussyinlexinxi){
        EntityWrapper< DiscussyinlexinxiEntity> ew = new EntityWrapper< DiscussyinlexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyinlexinxi, "discussyinlexinxi")); 
		DiscussyinlexinxiView discussyinlexinxiView =  discussyinlexinxiService.selectView(ew);
		return R.ok("查询音乐信息评论表成功").put("data", discussyinlexinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyinlexinxiEntity discussyinlexinxi = discussyinlexinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyinlexinxi,deSens);
        return R.ok().put("data", discussyinlexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyinlexinxiEntity discussyinlexinxi = discussyinlexinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyinlexinxi,deSens);
        return R.ok().put("data", discussyinlexinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增音乐信息评论表") 
    public R save(@RequestBody DiscussyinlexinxiEntity discussyinlexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyinlexinxi);
        discussyinlexinxiService.insert(discussyinlexinxi);
        return R.ok().put("data",discussyinlexinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增音乐信息评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyinlexinxiEntity discussyinlexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyinlexinxi);
        discussyinlexinxiService.insert(discussyinlexinxi);
        return R.ok().put("data",discussyinlexinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyinlexinxiEntity discussyinlexinxi = discussyinlexinxiService.selectOne(new EntityWrapper<DiscussyinlexinxiEntity>().eq("", username));
        return R.ok().put("data", discussyinlexinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyinlexinxiEntity discussyinlexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyinlexinxi);
        //全部更新
        discussyinlexinxiService.updateById(discussyinlexinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除音乐信息评论表")
    public R delete(@RequestBody Long[] ids){
        discussyinlexinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyinlexinxiEntity discussyinlexinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyinlexinxiEntity> ew = new EntityWrapper<DiscussyinlexinxiEntity>();
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

		PageUtils page = discussyinlexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyinlexinxi), params), params));
        return R.ok().put("data", page);
    }








}
