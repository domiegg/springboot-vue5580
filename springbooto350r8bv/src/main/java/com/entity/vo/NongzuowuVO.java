package com.entity.vo;

import com.entity.NongzuowuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 农作物
 * @author 
 * @email 
 * @date 2024-03-17 11:02:03
 */
public class NongzuowuVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
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
