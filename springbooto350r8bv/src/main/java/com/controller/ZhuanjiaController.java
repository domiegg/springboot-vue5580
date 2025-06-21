package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
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
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.ZhuanjiaEntity;
import com.entity.view.ZhuanjiaView;

import com.service.ZhuanjiaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 专家
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
@RestController
@RequestMapping("/zhuanjia")
public class ZhuanjiaController {
    @Autowired
    private ZhuanjiaService zhuanjiaService;

    @Autowired
    private StoreupService storeupService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		ZhuanjiaEntity u = zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", username));
        if(u!=null && u.getStatus().intValue()==1) {
            return R.error("账号已锁定，请联系管理员。");
        }
		if(u==null || !u.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
		String token = tokenService.generateToken(u.getId(), username,"zhuanjia",  "专家" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ZhuanjiaEntity zhuanjia){
    	//ValidatorUtils.validateEntity(zhuanjia);
    	ZhuanjiaEntity u = zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", zhuanjia.getZhuanjiazhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		zhuanjia.setId(uId);
        zhuanjiaService.insert(zhuanjia);
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
        ZhuanjiaEntity u = zhuanjiaService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	ZhuanjiaEntity u = zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setMima("123456");
        zhuanjiaService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuanjiaEntity zhuanjia,
		HttpServletRequest request){
        EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();

		PageUtils page = zhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjia), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhuanjiaEntity zhuanjia, 
		HttpServletRequest request){
        EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();

		PageUtils page = zhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjia), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuanjiaEntity zhuanjia){
       	EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuanjia, "zhuanjia")); 
        return R.ok().put("data", zhuanjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuanjiaEntity zhuanjia){
        EntityWrapper< ZhuanjiaEntity> ew = new EntityWrapper< ZhuanjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuanjia, "zhuanjia")); 
		ZhuanjiaView zhuanjiaView =  zhuanjiaService.selectView(ew);
		return R.ok("查询专家成功").put("data", zhuanjiaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
		zhuanjia.setClicknum(zhuanjia.getClicknum()+1);
		zhuanjiaService.updateById(zhuanjia);
        zhuanjia = zhuanjiaService.selectView(new EntityWrapper<ZhuanjiaEntity>().eq("id", id));
        return R.ok().put("data", zhuanjia);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
		zhuanjia.setClicknum(zhuanjia.getClicknum()+1);
		zhuanjiaService.updateById(zhuanjia);
        zhuanjia = zhuanjiaService.selectView(new EntityWrapper<ZhuanjiaEntity>().eq("id", id));
        return R.ok().put("data", zhuanjia);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        if(zhuanjiaService.selectCount(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", zhuanjia.getZhuanjiazhanghao()))>0) {
            return R.error("专家账号已存在");
        }
    	zhuanjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhuanjia);
    	ZhuanjiaEntity u = zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", zhuanjia.getZhuanjiazhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		zhuanjia.setId(new Date().getTime());
        zhuanjiaService.insert(zhuanjia);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        if(zhuanjiaService.selectCount(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", zhuanjia.getZhuanjiazhanghao()))>0) {
            return R.error("专家账号已存在");
        }
    	zhuanjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhuanjia);
    	ZhuanjiaEntity u = zhuanjiaService.selectOne(new EntityWrapper<ZhuanjiaEntity>().eq("zhuanjiazhanghao", zhuanjia.getZhuanjiazhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		zhuanjia.setId(new Date().getTime());
        zhuanjiaService.insert(zhuanjia);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjia);
        if(zhuanjiaService.selectCount(new EntityWrapper<ZhuanjiaEntity>().ne("id", zhuanjia.getId()).eq("zhuanjiazhanghao", zhuanjia.getZhuanjiazhanghao()))>0) {
            return R.error("专家账号已存在");
        }
        zhuanjiaService.updateById(zhuanjia);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZhuanjiaEntity> list = new ArrayList<ZhuanjiaEntity>();
        for(Long id : ids) {
            ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
            zhuanjia.setSfsh(sfsh);
            zhuanjia.setShhf(shhf);
            list.add(zhuanjia);
        }
        zhuanjiaService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuanjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ZhuanjiaEntity zhuanjia, HttpServletRequest request,String pre){
        EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
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
		PageUtils page = zhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjia), params), params));
        return R.ok().put("data", page);
    }


    /**
     * 协同算法（基于用户收藏的协同算法）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "zhuanjia"));
        Map<String, Map<String, Double>> ratings = new HashMap<>();
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity storeup : storeups) {
                Map<String, Double> userRatings = null;
                if(ratings.containsKey(storeup.getUserid().toString())) {
                    userRatings = ratings.get(storeup.getUserid().toString());
                } else {
                    userRatings = new HashMap<>();
                    ratings.put(storeup.getUserid().toString(), userRatings);
                }

                if(userRatings.containsKey(storeup.getRefid().toString())) {
                    userRatings.put(storeup.getRefid().toString(), userRatings.get(storeup.getRefid().toString())+1.0);
                } else {
                    userRatings.put(storeup.getRefid().toString(), 1.0);
                }
            }
        }
        // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering(ratings);

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(targetUser, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        PageUtils page = zhuanjiaService.queryPage(params, ew);
        List<ZhuanjiaEntity> pageList = (List<ZhuanjiaEntity>)page.getList();
        if(pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<ZhuanjiaEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(zhuanjiaService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }








}
