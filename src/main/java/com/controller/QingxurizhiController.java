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

import com.entity.QingxurizhiEntity;
import com.entity.view.QingxurizhiView;

import com.service.QingxurizhiService;
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
 * 情绪日志
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/qingxurizhi")
public class QingxurizhiController {
    @Autowired
    private QingxurizhiService qingxurizhiService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QingxurizhiEntity qingxurizhi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			qingxurizhi.setXuehao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<QingxurizhiEntity> ew = new EntityWrapper<QingxurizhiEntity>();


        //查询结果
		PageUtils page = qingxurizhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxurizhi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QingxurizhiEntity qingxurizhi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QingxurizhiEntity> ew = new EntityWrapper<QingxurizhiEntity>();

        //查询结果
		PageUtils page = qingxurizhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qingxurizhi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QingxurizhiEntity qingxurizhi){
       	EntityWrapper<QingxurizhiEntity> ew = new EntityWrapper<QingxurizhiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qingxurizhi, "qingxurizhi")); 
        return R.ok().put("data", qingxurizhiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QingxurizhiEntity qingxurizhi){
        EntityWrapper< QingxurizhiEntity> ew = new EntityWrapper< QingxurizhiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qingxurizhi, "qingxurizhi")); 
		QingxurizhiView qingxurizhiView =  qingxurizhiService.selectView(ew);
		return R.ok("查询情绪日志成功").put("data", qingxurizhiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QingxurizhiEntity qingxurizhi = qingxurizhiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxurizhi,deSens);
        return R.ok().put("data", qingxurizhi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QingxurizhiEntity qingxurizhi = qingxurizhiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qingxurizhi,deSens);
        return R.ok().put("data", qingxurizhi);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        QingxurizhiEntity qingxurizhi = qingxurizhiService.selectById(id);
        if(type.equals("1")) {
        	qingxurizhi.setThumbsupnum(qingxurizhi.getThumbsupnum()+1);
        } else {
        	qingxurizhi.setCrazilynum(qingxurizhi.getCrazilynum()+1);
        }
        qingxurizhiService.updateById(qingxurizhi);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增情绪日志") 
    public R save(@RequestBody QingxurizhiEntity qingxurizhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxurizhi);
        qingxurizhiService.insert(qingxurizhi);
        return R.ok().put("data",qingxurizhi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增情绪日志")
    @RequestMapping("/add")
    public R add(@RequestBody QingxurizhiEntity qingxurizhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxurizhi);
        qingxurizhiService.insert(qingxurizhi);
        return R.ok().put("data",qingxurizhi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改情绪日志")
    public R update(@RequestBody QingxurizhiEntity qingxurizhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qingxurizhi);
        //全部更新
        qingxurizhiService.updateById(qingxurizhi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除情绪日志")
    public R delete(@RequestBody Long[] ids){
        qingxurizhiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
