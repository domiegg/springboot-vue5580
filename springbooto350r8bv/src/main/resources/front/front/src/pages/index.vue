<template>
	<div class="main-containers">
		<div class="body-containers" :style='{"minHeight":"100vh","position":"relative","background":"#fff","display":"block"}'>
		<div class="top-container" :style='{"border-top":"50px solid #00AA3A","padding":"0 10%","alignItems":"center","display":"flex","justifyContent":"center","overflow":"hidden","top":"0","left":"0","flexWrap":"wrap","background":"#ECF2EA","width":"100%","height":"150px","zIndex":"1002"}'>
			<!-- info -->
			<div :style='{"padding":"0 30px","alignItems":"center","left":"10%","background":"#fff","display":"flex","width":"320px","position":"absolute","justifyContent":"center","zIndex":"1002","height":"90px"}'>
			  <span :style='{"padding":"0px","lineHeight":"1.2","fontSize":"26px","color":"#000","fontWeight":"600"}'>智慧农业专家远程指导系统</span>
			</div>
			<!-- notice -->
			<el-button class="notice" @click="dialogVisible = true" :style='{"border":"0","cursor":"pointer","padding":"0 12px","color":"#999","borderRadius":"0px","background":"none","width":"auto","fontSize":"16px","height":"40px","order":"2"}' type="success">
				<span class="icon iconfont icon-xiaoxi-zhihui" :style='{"margin":"0 2px","fontSize":"16px","color":"#666","height":"40px"}'></span>
				系统公告
			</el-button>
			<el-dialog title="系统公告" :append-to-body="true" :visible.sync="dialogVisible" width="50%">
				<div v-html="noticeDetail.content"></div>
			</el-dialog>
			
			<div v-if="false" :style='{"color":"#666","margin":"0 10px","fontSize":"14px"}'>0753-1234567</div>
			
			<img v-if="headportrait&&Token" :style='{"width":"40px","margin":"0 6px","borderRadius":"50%","display":"none","height":"40px"}' :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
			<div v-if="Token" :style='{"padding":"0 12px","fontSize":"16px","lineHeight":"32px","color":"#9E9E9E","height":"32px"}'>{{username}}</div>
			<div v-if="Token && notAdmin" :style='{"cursor":"pointer","padding":"0 12px","fontSize":"16px","lineHeight":"32px","color":"#9E9E9E","height":"32px"}' @click="goMenu('/index/center')">个人中心</div>
			<el-button v-if="!Token" @click="toLogin()" :style='{"border":"none","padding":"0 12px 0 40px","margin":"0 10px","color":"#fff","display":"inline-block","right":"18vw","borderRadius":"2px","top":"0","background":"url(http://codegen.caihongy.cn/20231107/82c85784c16b4bc199d9ce063ad5304e.png) no-repeat left center","fontSize":"16px","lineHeight":"32px","position":"absolute","height":"48px"}'>登录/注册</el-button>
			<el-button v-if="Token" @click="logout" :style='{"border":"none","padding":"0 12px 0 40px","margin":"0 10px","color":"#fff","display":"inline-block","right":"10vw","borderRadius":"2px","top":"0","background":"url(http://codegen.caihongy.cn/20231107/40af8d0ec5fe49e29e49f03138bb744e.png) no-repeat left center","fontSize":"16px","lineHeight":"32px","position":"absolute","height":"48px"}'>退出</el-button>
		</div>

		<div class="menu-preview" :style='{"padding":"90px 0 0","overflow":"auto","top":"50px","left":"10%","background":"#fff","bottom":"0","width":"320px","fontSize":"0px","position":"absolute","height":"700px","zIndex":"1001"}'>
			<el-scrollbar wrap-class="scrollbar-wrapper">
				<div class="userinfo" :style='{"width":"100%","padding":"0 30px","alignItems":"center","justifyContent":"center","display":"none","height":"98px"}'>
				  <el-image :style='{"width":"auto","flex-shrink":"0","margin":"0 20px 0 0","height":"48px"}' :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')" fit="cover"></el-image>
				  <div :style='{"fontSize":"40px","lineHeight":"58px","color":"#000000","textAlign":"center","fontWeight":"700"}'>{{username}}</div>
				</div>
				<el-menu class="el-menu-vertical-demo" :default-active="activeMenu" :style='{"border":0,"padding":"0 60px 0 80px","listStyle":"none","margin":"0","position":"relative","background":"#fff"}' :unique-opened="true" mode="vertical" :router="true" @select="handleSelect">
					<el-menu-item class="home" index="/index/home" @click.native="goMenu('/index/home')">
						<span :style='{"padding":"0 10px","margin":"0 5px 0 0","color":"inherit","width":"16px","lineHeight":"56px","fontSize":"16px","height":"56px"}' class="icon iconfont icon-shouye-zhihui"></span>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"16px","color":"inherit","height":"56px"}'>网站首页</span>
					</el-menu-item>
					<el-menu-item class="item" v-for="(menu, index) in menuList" :index="menu.url" :key="index" @click.native="goMenu(menu.url)">
						<i :style='{"padding":"0 10px","margin":"0 5px 0 0","color":"inherit","width":"16px","lineHeight":"56px","fontSize":"16px","height":"56px"}' :class="iconArr[index]"></i>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"16px","color":"inherit","height":"56px"}'>{{menu.name}}</span>
					</el-menu-item>
					<el-menu-item class="user" index="/index/center" v-if="Token && notAdmin" @click.native="goMenu('/index/center')">
						<span :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"56px","fontSize":"14px","height":"56px"}' class="icon iconfont icon-shouye-zhihui"></span>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"14px","color":"inherit","height":"56px"}'>个人中心</span>
					</el-menu-item>
				</el-menu>
			</el-scrollbar>
		</div>




			<div class="swiper3" :style='{"width":"100%","margin":"0 auto","height":"600px"}'>
			  <div class="swiper-container mySwiper3">
			    <div class="swiper-wrapper">
			      <div class="swiper-slide" v-for="item in carouselList" :key="item.id">
			        <div :style='{"width":"100%","height":"100%"}'>
			          <el-image @click="carouselClick(item.url)" :style='{"objectFit":"cover","width":"100%","height":"600px"}' :src="baseUrl + item.value" fit="cover"></el-image>
			        </div>
			      </div>
			    </div>
			    <!-- Add Pagination -->
			    <div class="swiper-pagination" :style='{"width":"100%","left":"0","bottom":"10px"}'></div>
			    <!-- Add Arrows -->
			    <div class="swiper-button-next" :style='{"width":"24px","margin":"-12px 0 0","top":"50%","height":"24px"}'>
			      <span class="icon iconfont icon-jiantou18" :style='{"width":"24px","fontSize":"24px","color":"#fff","height":"24px"}'></span>
			    </div>
			    <div class="swiper-button-prev" :style='{"width":"24px","margin":"-12px 0 0","top":"50%","height":"24px"}'>
			      <span class="icon iconfont icon-jiantou39" :style='{"width":"24px","fontSize":"24px","color":"#fff","height":"24px"}'></span>
			    </div>
			  </div>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview" :style='{"width":"100%","height":"auto"}'>
				<div :style='{"padding":"20px 10%","overflow":"hidden","color":"#ccc","textAlign":"center","background":"#000","width":"100%","height":"auto"}'><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
	</div>
</template>

<script>
import Vue from 'vue'
import Swiper from "swiper";
import axios from 'axios'

export default {
    data() {
		return {
			// 系统公告
			noticeDetail: {},
			dialogVisible: false,
            activeIndex: '0',
			roleMenus: [{"backMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","私聊"],"appFrontIcon":"cuIcon-similar","buttons":["新增","查看","修改","删除"],"menu":"农户","menuJump":"列表","tableName":"nonghu"}],"menu":"农户管理"},{"child":[{"allButtons":["新增","查看","修改","删除","审核","私聊"],"appFrontIcon":"cuIcon-paint","buttons":["新增","查看","修改","删除","审核"],"menu":"专家","menuJump":"列表","tableName":"zhuanjia"}],"menu":"专家管理"},{"child":[{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-album","buttons":["新增","查看","修改","删除"],"menu":"知识分类","menuJump":"列表","tableName":"zhishifenlei"}],"menu":"知识分类管理"},{"child":[{"allButtons":["新增","查看","修改","删除","审核","查看评论"],"appFrontIcon":"cuIcon-link","buttons":["查看","修改","删除","审核","查看评论"],"menu":"知识库","menuJump":"列表","tableName":"zhishiku"}],"menu":"知识库管理"},{"child":[{"allButtons":["新增","查看","修改","删除","种植统计","首页总数","首页统计"],"appFrontIcon":"cuIcon-phone","buttons":["新增","查看","修改","删除","种植统计","首页总数","首页统计"],"menu":"农作物","menuJump":"列表","tableName":"nongzuowu"}],"menu":"农作物管理"},{"child":[{"allButtons":["新增","查看","修改","删除","天气统计","查看评论","首页总数","首页统计"],"appFrontIcon":"cuIcon-pay","buttons":["新增","查看","修改","删除","天气统计","查看评论","首页总数","首页统计"],"menu":"天气预报","menuJump":"列表","tableName":"tianqiyubao"}],"menu":"天气预报管理"},{"child":[{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-group","buttons":["查看","删除"],"menu":"论坛交流","tableName":"forum"}],"menu":"论坛交流"},{"child":[{"allButtons":["查看","修改"],"appFrontIcon":"cuIcon-qrcode","buttons":["查看","修改"],"menu":"系统公告","tableName":"systemnotice"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-discover","buttons":["查看","修改"],"menu":"轮播图管理","tableName":"config"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"农业新闻","tableName":"news"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"农业新闻分类","tableName":"newstype"},{"allButtons":["查看","修改"],"appFrontIcon":"cuIcon-flashlightopen","buttons":["查看","修改"],"menu":"系统简介","tableName":"systemintro"}],"menu":"系统管理"}],"frontMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","审核","私聊"],"appFrontIcon":"cuIcon-time","buttons":["查看","私聊"],"menu":"专家列表","menuJump":"列表","tableName":"zhuanjia"}],"menu":"专家模块"},{"child":[{"allButtons":["新增","查看","修改","删除","审核","查看评论"],"appFrontIcon":"cuIcon-cardboard","buttons":["查看"],"menu":"知识库列表","menuJump":"列表","tableName":"zhishiku"}],"menu":"知识库模块"},{"child":[{"allButtons":["新增","查看","修改","删除","天气统计","查看评论","首页总数","首页统计"],"appFrontIcon":"cuIcon-discover","buttons":["查看"],"menu":"天气预报列表","menuJump":"列表","tableName":"tianqiyubao"}],"menu":"天气预报模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[],"frontMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","审核","私聊"],"appFrontIcon":"cuIcon-time","buttons":["查看","私聊"],"menu":"专家列表","menuJump":"列表","tableName":"zhuanjia"}],"menu":"专家模块"},{"child":[{"allButtons":["新增","查看","修改","删除","审核","查看评论"],"appFrontIcon":"cuIcon-cardboard","buttons":["查看"],"menu":"知识库列表","menuJump":"列表","tableName":"zhishiku"}],"menu":"知识库模块"},{"child":[{"allButtons":["新增","查看","修改","删除","天气统计","查看评论","首页总数","首页统计"],"appFrontIcon":"cuIcon-discover","buttons":["查看"],"menu":"天气预报列表","menuJump":"列表","tableName":"tianqiyubao"}],"menu":"天气预报模块"}],"hasBackLogin":"否","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"农户","tableName":"nonghu"},{"backMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","审核","查看评论"],"appFrontIcon":"cuIcon-link","buttons":["新增","查看","修改","删除"],"menu":"知识库","menuJump":"列表","tableName":"zhishiku"}],"menu":"知识库管理"}],"frontMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","审核","私聊"],"appFrontIcon":"cuIcon-time","buttons":["查看","私聊"],"menu":"专家列表","menuJump":"列表","tableName":"zhuanjia"}],"menu":"专家模块"},{"child":[{"allButtons":["新增","查看","修改","删除","审核","查看评论"],"appFrontIcon":"cuIcon-cardboard","buttons":["查看"],"menu":"知识库列表","menuJump":"列表","tableName":"zhishiku"}],"menu":"知识库模块"},{"child":[{"allButtons":["新增","查看","修改","删除","天气统计","查看评论","首页总数","首页统计"],"appFrontIcon":"cuIcon-discover","buttons":["查看"],"menu":"天气预报列表","menuJump":"列表","tableName":"tianqiyubao"}],"menu":"天气预报模块"}],"hasBackLogin":"否","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"专家","tableName":"zhuanjia"}],
			baseUrl: '',
			carouselList: [],
			menuList: [],
			form: {
				ask: '',
				userid: localStorage.getItem('frontUserid')
			},
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
            username: localStorage.getItem('username'),
            notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			timer: '',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
		}
    },
    created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
        if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
        }
    },
    mounted() {
        this.activeIndex = localStorage.getItem('keyPath') || '0';


		// banner
		setTimeout(()=>{
			new Swiper(".mySwiper3", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"autoplay":true,"speed":300,"effect":"fade"})
		}, 500)

		// 系统公告
		this.getNotice()
    },
    computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
    },
    watch: {
        $route(newValue) {
            let that = this
            let url = window.location.href
            let arr = url.split('#')
            for (let x in this.menuList) {
                if (newValue.path == this.menuList[x].url) {
                    this.activeIndex = x
                }
            }
            this.Token = localStorage.getItem('frontToken')
            if(arr[1]!='/index/home'){
            	var element = document.getElementById('scrollView');
            	var distance = element.offsetTop;
            	window.scrollTo( 0, distance )
            }else{
            	window.scrollTo( 0, 0 )
            }
        },
		headportrait(){
			this.$forceUpdate()
		},
    },
    methods: {

		// 获取系统公告
		getNotice() {
			this.$http.get('systemnotice/detail/1').then(res=>{
				if (res.data&&res.data.code==0) {
					this.noticeDetail = res.data.data
				}
			})
		},
		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
        handleSelect(keyPath) {
            if (keyPath) {
                localStorage.setItem('keyPath', keyPath)
            }
        },
		toLogin() {
		  this.$router.push('/login');
		},
        logout() {
            localStorage.clear();
            Vue.http.headers.common['Token'] = "";
            this.$router.push('/index/home');
            this.activeIndex = '0'
            localStorage.setItem('keyPath', this.activeIndex)
            this.Token = ''
            this.$forceUpdate()
            this.$message({
                message: '登出成功',
                type: 'success',
                duration: 1000,
            });
        },
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', localStorage.getItem('frontUserid'));
			window.open(`${this.$config.baseUrl}admin/dist/index.html`, "_blank");
		},
		goMenu(path) {
            this.$router.push(path);
		},
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.menu-preview {
	  .el-scrollbar {
	    height: 100%;
	  
	    & /deep/ .scrollbar-wrapper-vertical {
	      overflow-x: hidden;
	    }
	  
	    & /deep/ .scrollbar-wrapper-horizontal {
	      overflow-y: hidden;
	  
	      .el-scrollbar__view {
	        white-space: nowrap;
	      }
	    }
	  }
	}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.home {
				cursor: pointer;
				padding: 0px;
				color: #000;
				white-space: nowrap;
				background: #fff;
				display: flex;
				font-size: 16px;
				line-height: 56px;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.home:hover {
				color: #00AA3A;
				background: none;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.home.is-active {
				color: #00AA3A;
				background: none;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.user {
				cursor: pointer;
				padding: 0 20px;
				color: #000000;
				white-space: nowrap;
				background: #fff;
				display: none;
				font-size: 18px;
				line-height: 56px;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.user:hover {
				color: #fff;
				background: #00AA3A;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.user.is-active {
				color: #00AA3A;
				background: #fff;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.service {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #000;
				white-space: nowrap;
				display: none;
				font-size: 18px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.service:hover {
				color: #fff;
				background: #00AA3A;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.service.is-active {
				color: #00AA3A;
				background: #fff;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.shop {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #000;
				white-space: nowrap;
				display: none;
				font-size: 18px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.shop:hover {
				color: #fff;
				background: #00AA3A;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.shop.is-active {
				color: #00AA3A;
				background: #fff;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.back {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #000;
				white-space: nowrap;
				display: none;
				font-size: 18px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.back:hover {
				color: #fff;
				background: #00AA3A;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.back.is-active {
				color: #00AA3A;
				background: #fff;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.item {
				cursor: pointer;
				padding: 0px;
				color: #000;
				white-space: nowrap;
				background: #fff;
				display: flex;
				font-size: 16px;
				line-height: 56px;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.item:hover {
				color: #00AA3A;
				background: none;
			}
	
	.menu-preview .el-menu-vertical-demo .el-menu-item.item.is-active {
				color: #00AA3A;
				background: none;
			}
	
	
	.banner-preview {
	  .el-carousel /deep/ .el-carousel__indicator button {
	    width: 0;
	    height: 0;
	    display: none;
	  }
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left {
		width: 36px;
		font-size: 12px;
		height: 36px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left:hover {
		background: #00AA3A;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right {
		width: 36px;
		font-size: 12px;
		height: 36px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right:hover {
		background: #00AA3A;
	}

	.banner-preview .el-carousel /deep/ .el-carousel__indicators {
		padding: 0;
		margin: 0;
		z-index: 2;
		position: absolute;
		list-style: none;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__indicators li {
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 12px;
		opacity: 0.4;
		transition: 0.3s;
		height: 12px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__indicators li:hover {
		padding: 0;
		margin: 0 4px;
		background: #00AA3A;
		display: inline-block;
		width: 24px;
		opacity: 0.7;
		height: 12px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__indicators li.is-active {
		padding: 0;
		margin: 0 4px;
		background: #00AA3A;
		display: inline-block;
		width: 24px;
		opacity: 1;
		height: 12px;
	}

    .chat-content {
        padding-bottom: 20px;
        width: 100%;
        margin-bottom: 10px;
        max-height: 300px;
        height: 300px;
        overflow-y: scroll;
        border: 1px solid #eeeeee;
        background: #fff;

        .left-content {
            float: left;
            margin-bottom: 10px;
            padding: 10px;
            max-width: 80%;
        }

        .right-content {
            float: right;
            margin-bottom: 10px;
            padding: 10px;
            max-width: 80%;
        }
    }

    .clear-float {
        clear: both;
    }


	.swiper3 .swiper-button-prev:after {
      display:none;
    }
    .swiper3 .swiper-button-next:after {
      display:none;
    }
	.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet {
				border-radius: 100%;
				margin: 0 4px;
				background: #000;
				display: inline-block;
				width: 8px;
				opacity: .2;
				height: 8px;
			}
	
	.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet:hover {
				background: #fff;
				opacity: 1;
			}
	
	.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
				background: #fff;
				opacity: 1;
			}
	
	// -------- search --------
	.main-containers .search .select /deep/ .el-input__inner {
				border: 0;
				border-radius: 4px;
				padding: 0 30px 0 10px;
				box-shadow: 0 0 6px rgba(64, 158, 255, .3);
				outline: none;
				color: rgba(64, 158, 255, 1);
				width: 180px;
				font-size: 14px;
				height: 44px;
			}
	
	.main-containers .search .input /deep/ .el-input__inner {
				border: 0;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: 0 0 6px rgba(64, 158, 255, .3);
				outline: none;
				color: rgba(64, 158, 255, 1);
				border-left: 1px solid #D8D8D8;
				width: 180px;
				font-size: 14px;
				height: 44px;
			}
	// -------- search --------
	
	.main-containers .btn-service {
				border: 0;
				padding: 0 8px 0 30px;
				margin: 0 10px;
				color: #9E9E9E;
				background: url(http://codegen.caihongy.cn/20231108/c010013dae06483fba75c3c49999b346.png) no-repeat left center,#ECF2EA;
				width: auto;
				font-size: 16px;
				line-height: 32px;
				height: 32px;
			}
	
	.main-containers .btn-service:hover {
			}
	
	.main-containers .btn-shop {
				border: 0;
				padding: 0 8px 0 30px;
				margin: 0 0 0 auto;
				color: #9E9E9E;
				background: url(http://codegen.caihongy.cn/20231108/2cbf4eaee2a34e50926a90d2bb54dc85.png) no-repeat left center,#ECF2EA;
				width: auto;
				font-size: 16px;
				line-height: 32px;
				height: 32px;
			}
	
	.main-containers .btn-shop:hover {
			}
</style>
