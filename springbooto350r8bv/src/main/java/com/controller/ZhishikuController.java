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

import com.entity.ZhishikuEntity;
import com.entity.view.ZhishikuView;

import com.service.ZhishikuService;
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
 * 知识库
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
@RestController
@RequestMapping("/zhishiku")
public class ZhishikuController {
    @Autowired
    private ZhishikuService zhishikuService;

    @Autowired
    private StoreupService storeupService;



    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhishikuEntity zhishiku,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zhuanjia")) {
			zhishiku.setZhuanjiazhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZhishikuEntity> ew = new EntityWrapper<ZhishikuEntity>();

		PageUtils page = zhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhishiku), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhishikuEntity zhishiku, 
		HttpServletRequest request){
        EntityWrapper<ZhishikuEntity> ew = new EntityWrapper<ZhishikuEntity>();

		PageUtils page = zhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhishiku), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhishikuEntity zhishiku){
       	EntityWrapper<ZhishikuEntity> ew = new EntityWrapper<ZhishikuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhishiku, "zhishiku")); 
        return R.ok().put("data", zhishikuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhishikuEntity zhishiku){
        EntityWrapper< ZhishikuEntity> ew = new EntityWrapper< ZhishikuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhishiku, "zhishiku")); 
		ZhishikuView zhishikuView =  zhishikuService.selectView(ew);
		return R.ok("查询知识库成功").put("data", zhishikuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhishikuEntity zhishiku = zhishikuService.selectById(id);
        return R.ok().put("data", zhishiku);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhishikuEntity zhishiku = zhishikuService.selectById(id);
        return R.ok().put("data", zhishiku);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ZhishikuEntity zhishiku = zhishikuService.selectById(id);
        if(type.equals("1")) {
        	zhishiku.setThumbsupnum(zhishiku.getThumbsupnum()+1);
        } else {
        	zhishiku.setCrazilynum(zhishiku.getCrazilynum()+1);
        }
        zhishikuService.updateById(zhishiku);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhishikuEntity zhishiku, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhishiku);
        zhishikuService.insert(zhishiku);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhishikuEntity zhishiku, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(zhishiku);
        zhishikuService.insert(zhishiku);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhishikuEntity zhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhishiku);
        zhishikuService.updateById(zhishiku);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZhishikuEntity> list = new ArrayList<ZhishikuEntity>();
        for(Long id : ids) {
            ZhishikuEntity zhishiku = zhishikuService.selectById(id);
            zhishiku.setSfsh(sfsh);
            zhishiku.setShhf(shhf);
            list.add(zhishiku);
        }
        zhishikuService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhishikuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
