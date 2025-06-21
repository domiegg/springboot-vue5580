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
 * 农作物
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
@TableName("nongzuowu")
public class NongzuowuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public NongzuowuEntity() {
		
	}
	
	public NongzuowuEntity(T t) {
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
	 * 作物名称
	 */
					
	private String zuowumingcheng;
	
	/**
	 * 种植地区
	 */
					
	private String zhongzhidiqu;
	
	/**
	 * 种植面积²
	 */
					
	private Double zhongzhimianji;
	
	/**
	 * 种植日期
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date zhongzhiriqi;
	
	/**
	 * 种植数量/公顷
	 */
					
	private Integer zhongzhishuliang;
	
	/**
	 * 预计收成/吨
	 */
					
	private Double yujishoucheng;
	
	/**
	 * 详情
	 */
					
	private String xiangqing;
	
	
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
	 * 设置：作物名称
	 */
	public void setZuowumingcheng(String zuowumingcheng) {
		this.zuowumingcheng = zuowumingcheng;
	}
	/**
	 * 获取：作物名称
	 */
	public String getZuowumingcheng() {
		return zuowumingcheng;
	}
	/**
	 * 设置：种植地区
	 */
	public void setZhongzhidiqu(String zhongzhidiqu) {
		this.zhongzhidiqu = zhongzhidiqu;
	}
	/**
	 * 获取：种植地区
	 */
	public String getZhongzhidiqu() {
		return zhongzhidiqu;
	}
	/**
	 * 设置：种植面积²
	 */
	public void setZhongzhimianji(Double zhongzhimianji) {
		this.zhongzhimianji = zhongzhimianji;
	}
	/**
	 * 获取：种植面积²
	 */
	public Double getZhongzhimianji() {
		return zhongzhimianji;
	}
	/**
	 * 设置：种植日期
	 */
	public void setZhongzhiriqi(Date zhongzhiriqi) {
		this.zhongzhiriqi = zhongzhiriqi;
	}
	/**
	 * 获取：种植日期
	 */
	public Date getZhongzhiriqi() {
		return zhongzhiriqi;
	}
	/**
	 * 设置：种植数量/公顷
	 */
	public void setZhongzhishuliang(Integer zhongzhishuliang) {
		this.zhongzhishuliang = zhongzhishuliang;
	}
	/**
	 * 获取：种植数量/公顷
	 */
	public Integer getZhongzhishuliang() {
		return zhongzhishuliang;
	}
	/**
	 * 设置：预计收成/吨
	 */
	public void setYujishoucheng(Double yujishoucheng) {
		this.yujishoucheng = yujishoucheng;
	}
	/**
	 * 获取：预计收成/吨
	 */
	public Double getYujishoucheng() {
		return yujishoucheng;
	}
	/**
	 * 设置：详情
	 */
	public void setXiangqing(String xiangqing) {
		this.xiangqing = xiangqing;
	}
	/**
	 * 获取：详情
	 */
	public String getXiangqing() {
		return xiangqing;
	}

}
