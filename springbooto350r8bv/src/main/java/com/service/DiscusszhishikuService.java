package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusszhishikuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscusszhishikuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusszhishikuView;


/**
 * 知识库评论表
 *
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
public interface DiscusszhishikuService extends IService<DiscusszhishikuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusszhishikuVO> selectListVO(Wrapper<DiscusszhishikuEntity> wrapper);
   	
   	DiscusszhishikuVO selectVO(@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);
   	
   	List<DiscusszhishikuView> selectListView(Wrapper<DiscusszhishikuEntity> wrapper);
   	
   	DiscusszhishikuView selectView(@Param("ew") Wrapper<DiscusszhishikuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusszhishikuEntity> wrapper);

   	

}

