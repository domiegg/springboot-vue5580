package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TianqiyubaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TianqiyubaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TianqiyubaoView;


/**
 * 天气预报
 *
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
public interface TianqiyubaoService extends IService<TianqiyubaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TianqiyubaoVO> selectListVO(Wrapper<TianqiyubaoEntity> wrapper);
   	
   	TianqiyubaoVO selectVO(@Param("ew") Wrapper<TianqiyubaoEntity> wrapper);
   	
   	List<TianqiyubaoView> selectListView(Wrapper<TianqiyubaoEntity> wrapper);
   	
   	TianqiyubaoView selectView(@Param("ew") Wrapper<TianqiyubaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TianqiyubaoEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<TianqiyubaoEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<TianqiyubaoEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<TianqiyubaoEntity> wrapper);



}

