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

import com.entity.YinlexinxiEntity;
import com.entity.view.YinlexinxiView;

import com.service.YinlexinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 音乐信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
@RestController
@RequestMapping("/yinlexinxi")
public class YinlexinxiController {
    @Autowired
    private YinlexinxiService yinlexinxiService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YinlexinxiEntity yinlexinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YinlexinxiEntity> ew = new EntityWrapper<YinlexinxiEntity>();


        //查询结果
		PageUtils page = yinlexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinlexinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YinlexinxiEntity yinlexinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YinlexinxiEntity> ew = new EntityWrapper<YinlexinxiEntity>();

        //查询结果
		PageUtils page = yinlexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinlexinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YinlexinxiEntity yinlexinxi){
       	EntityWrapper<YinlexinxiEntity> ew = new EntityWrapper<YinlexinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yinlexinxi, "yinlexinxi")); 
        return R.ok().put("data", yinlexinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YinlexinxiEntity yinlexinxi){
        EntityWrapper< YinlexinxiEntity> ew = new EntityWrapper< YinlexinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yinlexinxi, "yinlexinxi")); 
		YinlexinxiView yinlexinxiView =  yinlexinxiService.selectView(ew);
		return R.ok("查询音乐信息成功").put("data", yinlexinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YinlexinxiEntity yinlexinxi = yinlexinxiService.selectById(id);
        if(null==yinlexinxi.getClicknum()){
            yinlexinxi.setClicknum(0);
        }
		yinlexinxi.setClicknum(yinlexinxi.getClicknum()+1);
		yinlexinxi.setClicktime(new Date());
		yinlexinxiService.updateById(yinlexinxi);
        yinlexinxi = yinlexinxiService.selectView(new EntityWrapper<YinlexinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yinlexinxi,deSens);
        return R.ok().put("data", yinlexinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YinlexinxiEntity yinlexinxi = yinlexinxiService.selectById(id);
        if(null==yinlexinxi.getClicknum()){
            yinlexinxi.setClicknum(0);
        }
		yinlexinxi.setClicknum(yinlexinxi.getClicknum()+1);
		yinlexinxi.setClicktime(new Date());
		yinlexinxiService.updateById(yinlexinxi);
        yinlexinxi = yinlexinxiService.selectView(new EntityWrapper<YinlexinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yinlexinxi,deSens);
        return R.ok().put("data", yinlexinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        YinlexinxiEntity yinlexinxi = yinlexinxiService.selectById(id);
        if(type.equals("1")) {
        	yinlexinxi.setThumbsupnum(yinlexinxi.getThumbsupnum()+1);
        } else {
        	yinlexinxi.setCrazilynum(yinlexinxi.getCrazilynum()+1);
        }
        yinlexinxiService.updateById(yinlexinxi);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增音乐信息") 
    public R save(@RequestBody YinlexinxiEntity yinlexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinlexinxi);
        yinlexinxiService.insert(yinlexinxi);
        return R.ok().put("data",yinlexinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增音乐信息")
    @RequestMapping("/add")
    public R add(@RequestBody YinlexinxiEntity yinlexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinlexinxi);
        yinlexinxiService.insert(yinlexinxi);
        return R.ok().put("data",yinlexinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改音乐信息")
    public R update(@RequestBody YinlexinxiEntity yinlexinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yinlexinxi);
        //全部更新
        yinlexinxiService.updateById(yinlexinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除音乐信息")
    public R delete(@RequestBody Long[] ids){
        yinlexinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YinlexinxiEntity yinlexinxi, HttpServletRequest request,String pre){
        EntityWrapper<YinlexinxiEntity> ew = new EntityWrapper<YinlexinxiEntity>();
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
		params.put("sort", "clicknum");
        params.put("order", "desc");

		PageUtils page = yinlexinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yinlexinxi), params), params));
        return R.ok().put("data", page);
    }








}
