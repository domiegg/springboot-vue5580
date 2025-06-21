package com.dao;

import com.entity.DiscusstianqiyubaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusstianqiyubaoVO;
import com.entity.view.DiscusstianqiyubaoView;


/**
 * 天气预报评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
public interface DiscusstianqiyubaoDao extends BaseMapper<DiscusstianqiyubaoEntity> {
	
	List<DiscusstianqiyubaoVO> selectListVO(@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);
	
	DiscusstianqiyubaoVO selectVO(@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);
	
	List<DiscusstianqiyubaoView> selectListView(@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);

	List<DiscusstianqiyubaoView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);

	
	DiscusstianqiyubaoView selectView(@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);
	

}
