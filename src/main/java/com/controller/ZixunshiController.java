package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.ZixunshiEntity;
import com.entity.view.ZixunshiView;

import com.service.ZixunshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 咨询师
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-09 11:05:18
 */
@RestController
@RequestMapping("/zixunshi")
public class ZixunshiController {
    @Autowired
    private ZixunshiService zixunshiService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        ZixunshiEntity u = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", username));
        // 判断用户锁定状态
        if(u!=null && u.getStatus().intValue()==1) {
            //返回已锁定提示
            return R.error("账号已锁定，请联系管理员。");
        }
        // 当用户不存在或md5方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.md5(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"zixunshi",  "管理员" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ZixunshiEntity zixunshi){
    	//ValidatorUtils.validateEntity(zixunshi);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	ZixunshiEntity u = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同咨询师账号，否则返回错误信息
        if(zixunshiService.selectCount(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()))>0) {
            return R.error("咨询师账号已存在");
        }
		Long uId = new Date().getTime();
		zixunshi.setId(uId);
        //设置登录密码md5方式加密
        zixunshi.setMima(EncryptUtil.md5(zixunshi.getMima()));
        //保存用户
        zixunshiService.insert(zixunshi);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ZixunshiEntity u = zixunshiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        ZixunshiEntity u = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用md5方式加密
        u.setMima(EncryptUtil.md5("123456"));
        zixunshiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZixunshiEntity zixunshi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZixunshiEntity> ew = new EntityWrapper<ZixunshiEntity>();


        //查询结果
		PageUtils page = zixunshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunshi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZixunshiEntity zixunshi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZixunshiEntity> ew = new EntityWrapper<ZixunshiEntity>();

        //查询结果
		PageUtils page = zixunshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zixunshi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZixunshiEntity zixunshi){
       	EntityWrapper<ZixunshiEntity> ew = new EntityWrapper<ZixunshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zixunshi, "zixunshi")); 
        return R.ok().put("data", zixunshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZixunshiEntity zixunshi){
        EntityWrapper< ZixunshiEntity> ew = new EntityWrapper< ZixunshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zixunshi, "zixunshi")); 
		ZixunshiView zixunshiView =  zixunshiService.selectView(ew);
		return R.ok("查询咨询师成功").put("data", zixunshiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZixunshiEntity zixunshi = zixunshiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zixunshi,deSens);
        return R.ok().put("data", zixunshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZixunshiEntity zixunshi = zixunshiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zixunshi,deSens);
        return R.ok().put("data", zixunshi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增咨询师") 
    public R save(@RequestBody ZixunshiEntity zixunshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zixunshiService.selectCount(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()))>0) {
            return R.error("咨询师账号已存在");
        }
        //ValidatorUtils.validateEntity(zixunshi);
        //验证账号唯一性，否则返回错误信息
        ZixunshiEntity u = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	zixunshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		zixunshi.setId(new Date().getTime());
        //密码使用md5方式加密
        zixunshi.setMima(EncryptUtil.md5(zixunshi.getMima()));
        zixunshiService.insert(zixunshi);
        return R.ok().put("data",zixunshi.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增咨询师")
    @RequestMapping("/add")
    public R add(@RequestBody ZixunshiEntity zixunshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(zixunshiService.selectCount(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()))>0) {
            return R.error("咨询师账号已存在");
        }
        //ValidatorUtils.validateEntity(zixunshi);
        //验证账号唯一性，否则返回错误信息
        ZixunshiEntity u = zixunshiService.selectOne(new EntityWrapper<ZixunshiEntity>().eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	zixunshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		zixunshi.setId(new Date().getTime());
        //密码使用md5方式加密
        zixunshi.setMima(EncryptUtil.md5(zixunshi.getMima()));
        zixunshiService.insert(zixunshi);
        return R.ok().put("data",zixunshi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改咨询师")
    public R update(@RequestBody ZixunshiEntity zixunshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zixunshi);
        //验证字段唯一性，否则返回错误信息
        if(zixunshiService.selectCount(new EntityWrapper<ZixunshiEntity>().ne("id", zixunshi.getId()).eq("zixunshizhanghao", zixunshi.getZixunshizhanghao()))>0) {
            return R.error("咨询师账号已存在");
        }
	    ZixunshiEntity zixunshiEntity = zixunshiService.selectById(zixunshi.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(zixunshi.getMima()) && !zixunshi.getMima().equals(zixunshiEntity.getMima())) {
            //密码使用md5方式加密
            zixunshi.setMima(EncryptUtil.md5(zixunshi.getMima()));
        }
        //全部更新
        zixunshiService.updateById(zixunshi);
        if(null!=zixunshi.getZixunshizhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(zixunshi.getZixunshizhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", zixunshi.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除咨询师")
    public R delete(@RequestBody Long[] ids){
        zixunshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
