package com.entity.view;

import com.entity.ChatmessageEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 消息表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
@TableName("chatmessage")
public class ChatmessageView  extends ChatmessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ChatmessageView(){
	}
 
 	public ChatmessageView(ChatmessageEntity chatmessageEntity){
 	try {
			BeanUtils.copyProperties(this, chatmessageEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
