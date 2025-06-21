package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusstianqiyubaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusstianqiyubaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusstianqiyubaoView;


/**
 * 天气预报评论表
 *
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
public interface DiscusstianqiyubaoService extends IService<DiscusstianqiyubaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusstianqiyubaoVO> selectListVO(Wrapper<DiscusstianqiyubaoEntity> wrapper);
   	
   	DiscusstianqiyubaoVO selectVO(@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);
   	
   	List<DiscusstianqiyubaoView> selectListView(Wrapper<DiscusstianqiyubaoEntity> wrapper);
   	
   	DiscusstianqiyubaoView selectView(@Param("ew") Wrapper<DiscusstianqiyubaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusstianqiyubaoEntity> wrapper);

   	

}

