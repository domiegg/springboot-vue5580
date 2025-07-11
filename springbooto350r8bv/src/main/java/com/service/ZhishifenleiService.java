package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhishifenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhishifenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhishifenleiView;


/**
 * 知识分类
 *
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
public interface ZhishifenleiService extends IService<ZhishifenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhishifenleiVO> selectListVO(Wrapper<ZhishifenleiEntity> wrapper);
   	
   	ZhishifenleiVO selectVO(@Param("ew") Wrapper<ZhishifenleiEntity> wrapper);
   	
   	List<ZhishifenleiView> selectListView(Wrapper<ZhishifenleiEntity> wrapper);
   	
   	ZhishifenleiView selectView(@Param("ew") Wrapper<ZhishifenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhishifenleiEntity> wrapper);

   	

}

