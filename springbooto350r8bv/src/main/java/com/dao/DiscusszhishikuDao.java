package com.dao;

import com.entity.DiscusszhishikuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscusszhishikuVO;
import com.entity.view.DiscusszhishikuView;


/**
 * 知识库评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
public interface DiscusszhishikuDao extends BaseMapper<DiscusszhishikuEntity> {
	
	List<DiscusszhishikuVO> selectListVO(@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);
	
	DiscusszhishikuVO selectVO(@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);
	
	List<DiscusszhishikuView> selectListView(@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);

	List<DiscusszhishikuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);

	
	DiscusszhishikuView selectView(@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);
	

}
