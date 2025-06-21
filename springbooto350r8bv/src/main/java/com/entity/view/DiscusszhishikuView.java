package com.entity.view;

import com.entity.DiscusszhishikuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 知识库评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
@TableName("discusszhishiku")
public class DiscusszhishikuView  extends DiscusszhishikuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscusszhishikuView(){
	}
 
 	public DiscusszhishikuView(DiscusszhishikuEntity discusszhishikuEntity){
 	try {
			BeanUtils.copyProperties(this, discusszhishikuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
