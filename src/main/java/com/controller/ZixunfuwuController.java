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

import com.entity.ZixunfuwuEntity;
import com.entity.view.ZixunfuwuView;

import com.service.ZixunfuwuService;
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
 * 咨询服务
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/zixunfuwu")
public class ZixunfuwuController {
    @Autowired
    private ZixunfuwuService zixunfuwuService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZixunfuwuEntity zixunfuwu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zixunshi")) {
			zixunfuwu.setZixunshizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZixunfuwuEntity> ew = new EntityWrapper<ZixunfuwuEntity>();


        //查询结果
		PageUtils page = zixunfuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunfuwu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZixunfuwuEntity zixunfuwu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZixunfuwuEntity> ew = new EntityWrapper<ZixunfuwuEntity>();

        //查询结果
		PageUtils page = zixunfuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunfuwu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZixunfuwuEntity zixunfuwu){
       	EntityWrapper<ZixunfuwuEntity> ew = new EntityWrapper<ZixunfuwuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zixunfuwu, "zixunfuwu")); 
        return R.ok().put("data", zixunfuwuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZixunfuwuEntity zixunfuwu){
        EntityWrapper< ZixunfuwuEntity> ew = new EntityWrapper< ZixunfuwuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zixunfuwu, "zixunfuwu")); 
		ZixunfuwuView zixunfuwuView =  zixunfuwuService.selectView(ew);
		return R.ok("查询咨询服务成功").put("data", zixunfuwuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZixunfuwuEntity zixunfuwu = zixunfuwuService.selectById(id);
        if(null==zixunfuwu.getClicknum()){
            zixunfuwu.setClicknum(0);
        }
		zixunfuwu.setClicknum(zixunfuwu.getClicknum()+1);
		zixunfuwu.setClicktime(new Date());
		zixunfuwuService.updateById(zixunfuwu);
        zixunfuwu = zixunfuwuService.selectView(new EntityWrapper<ZixunfuwuEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zixunfuwu,deSens);
        return R.ok().put("data", zixunfuwu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZixunfuwuEntity zixunfuwu = zixunfuwuService.selectById(id);
        if(null==zixunfuwu.getClicknum()){
            zixunfuwu.setClicknum(0);
        }
		zixunfuwu.setClicknum(zixunfuwu.getClicknum()+1);
		zixunfuwu.setClicktime(new Date());
		zixunfuwuService.updateById(zixunfuwu);
        zixunfuwu = zixunfuwuService.selectView(new EntityWrapper<ZixunfuwuEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zixunfuwu,deSens);
        return R.ok().put("data", zixunfuwu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增咨询服务") 
    public R save(@RequestBody ZixunfuwuEntity zixunfuwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zixunfuwu);
        zixunfuwuService.insert(zixunfuwu);
        return R.ok().put("data",zixunfuwu.getId());
    }
    
    /**
     * 前台保存
     */
    @IgnoreAuth
    @RequestMapping("/add")
    public R add(@RequestBody ZixunfuwuEntity zixunfuwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zixunfuwu);
        zixunfuwuService.insert(zixunfuwu);
        return R.ok().put("data",zixunfuwu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改咨询服务")
    public R update(@RequestBody ZixunfuwuEntity zixunfuwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zixunfuwu);
        //全部更新
        zixunfuwuService.updateById(zixunfuwu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除咨询服务")
    public R delete(@RequestBody Long[] ids){
        zixunfuwuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZixunfuwuEntity zixunfuwu, HttpServletRequest request,String pre){
        EntityWrapper<ZixunfuwuEntity> ew = new EntityWrapper<ZixunfuwuEntity>();
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

		PageUtils page = zixunfuwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunfuwu), params), params));
        return R.ok().put("data", page);
    }








}
