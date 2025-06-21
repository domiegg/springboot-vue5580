package com.entity.view;

import com.entity.NongzuowuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 农作物
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
@TableName("nongzuowu")
public class NongzuowuView  extends NongzuowuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public NongzuowuView(){
	}
 
 	public NongzuowuView(NongzuowuEntity nongzuowuEntity){
 	try {
			BeanUtils.copyProperties(this, nongzuowuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
