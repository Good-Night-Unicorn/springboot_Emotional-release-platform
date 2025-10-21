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

import com.entity.QingxuwenzhangEntity;
import com.entity.view.QingxuwenzhangView;

import com.service.QingxuwenzhangService;
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
 * 情绪文章
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/qingxuwenzhang")
public class QingxuwenzhangController {
    @Autowired
    private QingxuwenzhangService qingxuwenzhangService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QingxuwenzhangEntity qingxuwenzhang,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zixunshi")) {
			qingxuwenzhang.setZixunshizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<QingxuwenzhangEntity> ew = new EntityWrapper<QingxuwenzhangEntity>();


        //查询结果
		PageUtils page = qingxuwenzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxuwenzhang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QingxuwenzhangEntity qingxuwenzhang, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QingxuwenzhangEntity> ew = new EntityWrapper<QingxuwenzhangEntity>();

        //查询结果
		PageUtils page = qingxuwenzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxuwenzhang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QingxuwenzhangEntity qingxuwenzhang){
       	EntityWrapper<QingxuwenzhangEntity> ew = new EntityWrapper<QingxuwenzhangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qingxuwenzhang, "qingxuwenzhang")); 
        return R.ok().put("data", qingxuwenzhangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QingxuwenzhangEntity qingxuwenzhang){
        EntityWrapper< QingxuwenzhangEntity> ew = new EntityWrapper< QingxuwenzhangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qingxuwenzhang, "qingxuwenzhang")); 
		QingxuwenzhangView qingxuwenzhangView =  qingxuwenzhangService.selectView(ew);
		return R.ok("查询情绪文章成功").put("data", qingxuwenzhangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QingxuwenzhangEntity qingxuwenzhang = qingxuwenzhangService.selectById(id);
        if(null==qingxuwenzhang.getClicknum()){
            qingxuwenzhang.setClicknum(0);
        }
		qingxuwenzhang.setClicknum(qingxuwenzhang.getClicknum()+1);
		qingxuwenzhang.setClicktime(new Date());
		qingxuwenzhangService.updateById(qingxuwenzhang);
        qingxuwenzhang = qingxuwenzhangService.selectView(new EntityWrapper<QingxuwenzhangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxuwenzhang,deSens);
        return R.ok().put("data", qingxuwenzhang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QingxuwenzhangEntity qingxuwenzhang = qingxuwenzhangService.selectById(id);
        if(null==qingxuwenzhang.getClicknum()){
            qingxuwenzhang.setClicknum(0);
        }
		qingxuwenzhang.setClicknum(qingxuwenzhang.getClicknum()+1);
		qingxuwenzhang.setClicktime(new Date());
		qingxuwenzhangService.updateById(qingxuwenzhang);
        qingxuwenzhang = qingxuwenzhangService.selectView(new EntityWrapper<QingxuwenzhangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxuwenzhang,deSens);
        return R.ok().put("data", qingxuwenzhang);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        QingxuwenzhangEntity qingxuwenzhang = qingxuwenzhangService.selectById(id);
        if(type.equals("1")) {
        	qingxuwenzhang.setThumbsupnum(qingxuwenzhang.getThumbsupnum()+1);
        } else {
        	qingxuwenzhang.setCrazilynum(qingxuwenzhang.getCrazilynum()+1);
        }
        qingxuwenzhangService.updateById(qingxuwenzhang);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增情绪文章") 
    public R save(@RequestBody QingxuwenzhangEntity qingxuwenzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxuwenzhang);
        qingxuwenzhangService.insert(qingxuwenzhang);
        return R.ok().put("data",qingxuwenzhang.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增情绪文章")
    @RequestMapping("/add")
    public R add(@RequestBody QingxuwenzhangEntity qingxuwenzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxuwenzhang);
        qingxuwenzhangService.insert(qingxuwenzhang);
        return R.ok().put("data",qingxuwenzhang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改情绪文章")
    public R update(@RequestBody QingxuwenzhangEntity qingxuwenzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxuwenzhang);
        //全部更新
        qingxuwenzhangService.updateById(qingxuwenzhang);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除情绪文章")
    public R delete(@RequestBody Long[] ids){
        qingxuwenzhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,QingxuwenzhangEntity qingxuwenzhang, HttpServletRequest request,String pre){
        EntityWrapper<QingxuwenzhangEntity> ew = new EntityWrapper<QingxuwenzhangEntity>();
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

		PageUtils page = qingxuwenzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxuwenzhang), params), params));
        return R.ok().put("data", page);
    }








}
