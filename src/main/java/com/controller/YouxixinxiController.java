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

import com.entity.YouxixinxiEntity;
import com.entity.view.YouxixinxiView;

import com.service.YouxixinxiService;
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
 * 游戏信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:19
 */
@RestController
@RequestMapping("/youxixinxi")
public class YouxixinxiController {
    @Autowired
    private YouxixinxiService youxixinxiService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YouxixinxiEntity youxixinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YouxixinxiEntity> ew = new EntityWrapper<YouxixinxiEntity>();


        //查询结果
		PageUtils page = youxixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, youxixinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YouxixinxiEntity youxixinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YouxixinxiEntity> ew = new EntityWrapper<YouxixinxiEntity>();

        //查询结果
		PageUtils page = youxixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, youxixinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YouxixinxiEntity youxixinxi){
       	EntityWrapper<YouxixinxiEntity> ew = new EntityWrapper<YouxixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( youxixinxi, "youxixinxi")); 
        return R.ok().put("data", youxixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YouxixinxiEntity youxixinxi){
        EntityWrapper< YouxixinxiEntity> ew = new EntityWrapper< YouxixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( youxixinxi, "youxixinxi")); 
		YouxixinxiView youxixinxiView =  youxixinxiService.selectView(ew);
		return R.ok("查询游戏信息成功").put("data", youxixinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YouxixinxiEntity youxixinxi = youxixinxiService.selectById(id);
        if(null==youxixinxi.getClicknum()){
            youxixinxi.setClicknum(0);
        }
		youxixinxi.setClicknum(youxixinxi.getClicknum()+1);
		youxixinxi.setClicktime(new Date());
		youxixinxiService.updateById(youxixinxi);
        youxixinxi = youxixinxiService.selectView(new EntityWrapper<YouxixinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(youxixinxi,deSens);
        return R.ok().put("data", youxixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YouxixinxiEntity youxixinxi = youxixinxiService.selectById(id);
        if(null==youxixinxi.getClicknum()){
            youxixinxi.setClicknum(0);
        }
		youxixinxi.setClicknum(youxixinxi.getClicknum()+1);
		youxixinxi.setClicktime(new Date());
		youxixinxiService.updateById(youxixinxi);
        youxixinxi = youxixinxiService.selectView(new EntityWrapper<YouxixinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(youxixinxi,deSens);
        return R.ok().put("data", youxixinxi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        YouxixinxiEntity youxixinxi = youxixinxiService.selectById(id);
        if(type.equals("1")) {
        	youxixinxi.setThumbsupnum(youxixinxi.getThumbsupnum()+1);
        } else {
        	youxixinxi.setCrazilynum(youxixinxi.getCrazilynum()+1);
        }
        youxixinxiService.updateById(youxixinxi);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增游戏信息") 
    public R save(@RequestBody YouxixinxiEntity youxixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(youxixinxi);
        youxixinxiService.insert(youxixinxi);
        return R.ok().put("data",youxixinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增游戏信息")
    @RequestMapping("/add")
    public R add(@RequestBody YouxixinxiEntity youxixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(youxixinxi);
        youxixinxiService.insert(youxixinxi);
        return R.ok().put("data",youxixinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改游戏信息")
    public R update(@RequestBody YouxixinxiEntity youxixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(youxixinxi);
        //全部更新
        youxixinxiService.updateById(youxixinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除游戏信息")
    public R delete(@RequestBody Long[] ids){
        youxixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YouxixinxiEntity youxixinxi, HttpServletRequest request,String pre){
        EntityWrapper<YouxixinxiEntity> ew = new EntityWrapper<YouxixinxiEntity>();
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

		PageUtils page = youxixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, youxixinxi), params), params));
        return R.ok().put("data", page);
    }








}
