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


import com.dao.DiscusszhishikuDao;
import com.entity.DiscusszhishikuEntity;
import com.service.DiscusszhishikuService;
import com.entity.vo.DiscusszhishikuVO;
import com.entity.view.DiscusszhishikuView;

@Service("discusszhishikuService")
public class DiscusszhishikuServiceImpl extends ServiceImpl<DiscusszhishikuDao, DiscusszhishikuEntity> implements DiscusszhishikuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusszhishikuEntity> page = this.selectPage(
                new Query<DiscusszhishikuEntity>(params).getPage(),
                new EntityWrapper<DiscusszhishikuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusszhishikuEntity> wrapper) {
		  Page<DiscusszhishikuView> page =new Query<DiscusszhishikuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscusszhishikuVO> selectListVO(Wrapper<DiscusszhishikuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscusszhishikuVO selectVO(Wrapper<DiscusszhishikuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscusszhishikuView> selectListView(Wrapper<DiscusszhishikuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusszhishikuView selectView(Wrapper<DiscusszhishikuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
