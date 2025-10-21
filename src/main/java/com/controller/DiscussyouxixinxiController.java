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

import com.entity.DiscussyouxixinxiEntity;
import com.entity.view.DiscussyouxixinxiView;

import com.service.DiscussyouxixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 游戏信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
@RestController
@RequestMapping("/discussyouxixinxi")
public class DiscussyouxixinxiController {
    @Autowired
    private DiscussyouxixinxiService discussyouxixinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyouxixinxiEntity discussyouxixinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyouxixinxiEntity> ew = new EntityWrapper<DiscussyouxixinxiEntity>();


        //查询结果
		PageUtils page = discussyouxixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyouxixinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussyouxixinxiEntity discussyouxixinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyouxixinxiEntity> ew = new EntityWrapper<DiscussyouxixinxiEntity>();

        //查询结果
		PageUtils page = discussyouxixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyouxixinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyouxixinxiEntity discussyouxixinxi){
       	EntityWrapper<DiscussyouxixinxiEntity> ew = new EntityWrapper<DiscussyouxixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyouxixinxi, "discussyouxixinxi")); 
        return R.ok().put("data", discussyouxixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyouxixinxiEntity discussyouxixinxi){
        EntityWrapper< DiscussyouxixinxiEntity> ew = new EntityWrapper< DiscussyouxixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyouxixinxi, "discussyouxixinxi")); 
		DiscussyouxixinxiView discussyouxixinxiView =  discussyouxixinxiService.selectView(ew);
		return R.ok("查询游戏信息评论表成功").put("data", discussyouxixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyouxixinxiEntity discussyouxixinxi = discussyouxixinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyouxixinxi,deSens);
        return R.ok().put("data", discussyouxixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyouxixinxiEntity discussyouxixinxi = discussyouxixinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyouxixinxi,deSens);
        return R.ok().put("data", discussyouxixinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增游戏信息评论表") 
    public R save(@RequestBody DiscussyouxixinxiEntity discussyouxixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyouxixinxi);
        discussyouxixinxiService.insert(discussyouxixinxi);
        return R.ok().put("data",discussyouxixinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增游戏信息评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyouxixinxiEntity discussyouxixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyouxixinxi);
        discussyouxixinxiService.insert(discussyouxixinxi);
        return R.ok().put("data",discussyouxixinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyouxixinxiEntity discussyouxixinxi = discussyouxixinxiService.selectOne(new EntityWrapper<DiscussyouxixinxiEntity>().eq("", username));
        return R.ok().put("data", discussyouxixinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyouxixinxiEntity discussyouxixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyouxixinxi);
        //全部更新
        discussyouxixinxiService.updateById(discussyouxixinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除游戏信息评论表")
    public R delete(@RequestBody Long[] ids){
        discussyouxixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyouxixinxiEntity discussyouxixinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyouxixinxiEntity> ew = new EntityWrapper<DiscussyouxixinxiEntity>();
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

		PageUtils page = discussyouxixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyouxixinxi), params), params));
        return R.ok().put("data", page);
    }








}
