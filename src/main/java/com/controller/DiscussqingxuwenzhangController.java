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

import com.entity.DiscussqingxuwenzhangEntity;
import com.entity.view.DiscussqingxuwenzhangView;

import com.service.DiscussqingxuwenzhangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 情绪文章评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:20
 */
@RestController
@RequestMapping("/discussqingxuwenzhang")
public class DiscussqingxuwenzhangController {
    @Autowired
    private DiscussqingxuwenzhangService discussqingxuwenzhangService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussqingxuwenzhangEntity discussqingxuwenzhang,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussqingxuwenzhangEntity> ew = new EntityWrapper<DiscussqingxuwenzhangEntity>();


        //查询结果
		PageUtils page = discussqingxuwenzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqingxuwenzhang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussqingxuwenzhangEntity discussqingxuwenzhang, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussqingxuwenzhangEntity> ew = new EntityWrapper<DiscussqingxuwenzhangEntity>();

        //查询结果
		PageUtils page = discussqingxuwenzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqingxuwenzhang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussqingxuwenzhangEntity discussqingxuwenzhang){
       	EntityWrapper<DiscussqingxuwenzhangEntity> ew = new EntityWrapper<DiscussqingxuwenzhangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussqingxuwenzhang, "discussqingxuwenzhang")); 
        return R.ok().put("data", discussqingxuwenzhangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussqingxuwenzhangEntity discussqingxuwenzhang){
        EntityWrapper< DiscussqingxuwenzhangEntity> ew = new EntityWrapper< DiscussqingxuwenzhangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussqingxuwenzhang, "discussqingxuwenzhang")); 
		DiscussqingxuwenzhangView discussqingxuwenzhangView =  discussqingxuwenzhangService.selectView(ew);
		return R.ok("查询情绪文章评论表成功").put("data", discussqingxuwenzhangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussqingxuwenzhangEntity discussqingxuwenzhang = discussqingxuwenzhangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqingxuwenzhang,deSens);
        return R.ok().put("data", discussqingxuwenzhang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussqingxuwenzhangEntity discussqingxuwenzhang = discussqingxuwenzhangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqingxuwenzhang,deSens);
        return R.ok().put("data", discussqingxuwenzhang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增情绪文章评论表") 
    public R save(@RequestBody DiscussqingxuwenzhangEntity discussqingxuwenzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqingxuwenzhang);
        discussqingxuwenzhangService.insert(discussqingxuwenzhang);
        return R.ok().put("data",discussqingxuwenzhang.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增情绪文章评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussqingxuwenzhangEntity discussqingxuwenzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqingxuwenzhang);
        discussqingxuwenzhangService.insert(discussqingxuwenzhang);
        return R.ok().put("data",discussqingxuwenzhang.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussqingxuwenzhangEntity discussqingxuwenzhang = discussqingxuwenzhangService.selectOne(new EntityWrapper<DiscussqingxuwenzhangEntity>().eq("", username));
        return R.ok().put("data", discussqingxuwenzhang);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussqingxuwenzhangEntity discussqingxuwenzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqingxuwenzhang);
        //全部更新
        discussqingxuwenzhangService.updateById(discussqingxuwenzhang);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除情绪文章评论表")
    public R delete(@RequestBody Long[] ids){
        discussqingxuwenzhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussqingxuwenzhangEntity discussqingxuwenzhang, HttpServletRequest request,String pre){
        EntityWrapper<DiscussqingxuwenzhangEntity> ew = new EntityWrapper<DiscussqingxuwenzhangEntity>();
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

		PageUtils page = discussqingxuwenzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqingxuwenzhang), params), params));
        return R.ok().put("data", page);
    }








}
