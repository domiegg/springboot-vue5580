package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscusstianqiyubaoDao;
import com.entity.DiscusstianqiyubaoEntity;
import com.service.DiscusstianqiyubaoService;
import com.entity.vo.DiscusstianqiyubaoVO;
import com.entity.view.DiscusstianqiyubaoView;

@Service("discusstianqiyubaoService")
public class DiscusstianqiyubaoServiceImpl extends ServiceImpl<DiscusstianqiyubaoDao, DiscusstianqiyubaoEntity> implements DiscusstianqiyubaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusstianqiyubaoEntity> page = this.selectPage(
                new Query<DiscusstianqiyubaoEntity>(params).getPage(),
                new EntityWrapper<DiscusstianqiyubaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusstianqiyubaoEntity> wrapper) {
		  Page<DiscusstianqiyubaoView> page =new Query<DiscusstianqiyubaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscusstianqiyubaoVO> selectListVO(Wrapper<DiscusstianqiyubaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusstianqiyubaoVO selectVO(Wrapper<DiscusstianqiyubaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusstianqiyubaoView> selectListView(Wrapper<DiscusstianqiyubaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusstianqiyubaoView selectView(Wrapper<DiscusstianqiyubaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
