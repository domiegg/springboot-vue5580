package com.dao;

import com.entity.ZhishikuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhishikuVO;
import com.entity.view.ZhishikuView;


/**
 * 知识库
 * 
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
public interface ZhishikuDao extends BaseMapper<ZhishikuEntity> {
	
	List<ZhishikuVO> selectListVO(@Param("ew") Wrapper<ZhishikuEntity> wrapper);
	
	ZhishikuVO selectVO(@Param("ew") Wrapper<ZhishikuEntity> wrapper);
	
	List<ZhishikuView> selectListView(@Param("ew") Wrapper<ZhishikuEntity> wrapper);

	List<ZhishikuView> selectListView(Pagination page,@Param("ew") Wrapper<ZhishikuEntity> wrapper);

	
	ZhishikuView selectView(@Param("ew") Wrapper<ZhishikuEntity> wrapper);
	

}
