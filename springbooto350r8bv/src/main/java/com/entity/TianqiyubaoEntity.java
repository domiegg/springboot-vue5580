package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 天气预报
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2024-03-17 11:02:04
 */
@TableName("tianqiyubao")
public class TianqiyubaoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TianqiyubaoEntity() {
		
	}
	
	public TianqiyubaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
    private Long id;
	/**
	 * 标题
	 */
					
	private String biaoti;
	
	/**
	 * 城市
	 */
					
	private String chengshi;
	
	/**
	 * 天气
	 */
					
	private String tianqi;
	
	/**
	 * 气温
	 */
					
	private String qiwen;
	
	/**
	 * 湿度
	 */
					
	private String shidu;
	
	/**
	 * 封面
	 */
					
	private String fengmian;
	
	/**
	 * 风向
	 */
					
	private String fengxiang;
	
	/**
	 * 风速
	 */
					
	private String fengsu;
	
	/**
	 * 变化提醒
	 */
					
	private String bianhuatixing;
	
	/**
	 * 更新时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date gengxinshijian;
	
	/**
	 * 详细说明
	 */
					
	private String xiangxishuoming;
	
	/**
	 * 评论数
	 */
					
	private Integer discussnum;
	
	/**
	 * 收藏数
	 */
					
	private Integer storeupnum;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：标题
	 */
	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}
	/**
	 * 获取：标题
	 */
	public String getBiaoti() {
		return biaoti;
	}
	/**
	 * 设置：城市
	 */
	public void setChengshi(String chengshi) {
		this.chengshi = chengshi;
	}
	/**
	 * 获取：城市
	 */
	public String getChengshi() {
		return chengshi;
	}
	/**
	 * 设置：天气
	 */
	public void setTianqi(String tianqi) {
		this.tianqi = tianqi;
	}
	/**
	 * 获取：天气
	 */
	public String getTianqi() {
		return tianqi;
	}
	/**
	 * 设置：气温
	 */
	public void setQiwen(String qiwen) {
		this.qiwen = qiwen;
	}
	/**
	 * 获取：气温
	 */
	public String getQiwen() {
		return qiwen;
	}
	/**
	 * 设置：湿度
	 */
	public void setShidu(String shidu) {
		this.shidu = shidu;
	}
	/**
	 * 获取：湿度
	 */
	public String getShidu() {
		return shidu;
	}
	/**
	 * 设置：封面
	 */
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
	/**
	 * 设置：风向
	 */
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	/**
	 * 获取：风向
	 */
	public String getFengxiang() {
		return fengxiang;
	}
	/**
	 * 设置：风速
	 */
	public void setFengsu(String fengsu) {
		this.fengsu = fengsu;
	}
	/**
	 * 获取：风速
	 */
	public String getFengsu() {
		return fengsu;
	}
	/**
	 * 设置：变化提醒
	 */
	public void setBianhuatixing(String bianhuatixing) {
		this.bianhuatixing = bianhuatixing;
	}
	/**
	 * 获取：变化提醒
	 */
	public String getBianhuatixing() {
		return bianhuatixing;
	}
	/**
	 * 设置：更新时间
	 */
	public void setGengxinshijian(Date gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getGengxinshijian() {
		return gengxinshijian;
	}
	/**
	 * 设置：详细说明
	 */
	public void setXiangxishuoming(String xiangxishuoming) {
		this.xiangxishuoming = xiangxishuoming;
	}
	/**
	 * 获取：详细说明
	 */
	public String getXiangxishuoming() {
		return xiangxishuoming;
	}
	/**
	 * 设置：评论数
	 */
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
	/**
	 * 设置：收藏数
	 */
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}

}
