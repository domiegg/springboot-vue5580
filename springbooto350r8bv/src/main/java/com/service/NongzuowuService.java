package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.NongzuowuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.NongzuowuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.NongzuowuView;


/**
 * 农作物
 *
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
public interface NongzuowuService extends IService<NongzuowuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NongzuowuVO> selectListVO(Wrapper<NongzuowuEntity> wrapper);
   	
   	NongzuowuVO selectVO(@Param("ew") Wrapper<NongzuowuEntity> wrapper);
   	
   	List<NongzuowuView> selectListView(Wrapper<NongzuowuEntity> wrapper);
   	
   	NongzuowuView selectView(@Param("ew") Wrapper<NongzuowuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NongzuowuEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<NongzuowuEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<NongzuowuEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<NongzuowuEntity> wrapper);



}

