package com.dao;

import com.entity.NewstypeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.NewstypeVO;
import com.entity.view.NewstypeView;


/**
 * 农业新闻分类
 * 
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
public interface NewstypeDao extends BaseMapper<NewstypeEntity> {
	
	List<NewstypeVO> selectListVO(@Param("ew") Wrapper<NewstypeEntity> wrapper);
	
	NewstypeVO selectVO(@Param("ew") Wrapper<NewstypeEntity> wrapper);
	
	List<NewstypeView> selectListView(@Param("ew") Wrapper<NewstypeEntity> wrapper);

	List<NewstypeView> selectListView(Pagination page,@Param("ew") Wrapper<NewstypeEntity> wrapper);

	
	NewstypeView selectView(@Param("ew") Wrapper<NewstypeEntity> wrapper);
	

}
