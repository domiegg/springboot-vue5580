<template>
	<div class="menu-preview">
		<!-- 竖向-2 -->
		<el-scrollbar :wrap-class="isCollapse ? 'scrollbar-wrapper scrollbar-wrapper-close' : 'scrollbar-wrapper scrollbar-wrapper-open'">
		  <el-button :style="verticalStyle2[isCollapse?'close':'open'].btn.default" type="primary" @click="collapse">
		    <span class="icon iconfont"
		      :style="verticalStyle2[isCollapse?'close':'open'].btn.icon.default"
		      :class="verticalStyle2[isCollapse?'close':'open'].btn.icon.text"></span>{{verticalStyle2[isCollapse?'close':'open'].btn.text}}
		  </el-button>
		  <div class="userinfo"
		    :style="verticalStyle2[isCollapse?'close':'open'].userinfo.box.default">
		    <el-image v-if="avatar" :style="verticalStyle2[isCollapse?'close':'open'].userinfo.img.default" :src="avatar?this.$base.url + avatar:require('@/assets/img/avator.png')" fit="cover"></el-image>
		    <div :style="verticalStyle2[isCollapse?'close':'open'].userinfo.nickname.default">
		      {{this.$storage.get('adminName')}}</div>
		  </div>
		  <el-menu :default-active="activeMenu" :unique-opened="true" :style="verticalStyle2[isCollapse?'close':'open'].menu.box.default"
		    class="el-menu-vertical-2" :collapse-transition="false" :collapse="isCollapse">
		    <el-menu-item class="home" :popper-append-to-body="false" popper-class="home" @click.native="menuHandler('')" :style="verticalStyle2[isCollapse?'close':'open'].home.one.box.default" index="/">
		      <div class="el-tooltip">
		        <i :style="verticalStyle2[isCollapse?'close':'open'].home.one.icon.default"
		          class="icon iconfont icon-shouye-zhihui"></i>
		        <span :style="verticalStyle2[isCollapse?'close':'open'].home.one.title.default"
		          slot="title">{{verticalStyle2.open.home.one.title.text}}</span>
		      </div>
		    </el-menu-item>
		    <el-submenu class="user" popper-class="user" :popper-append-to-body="false"
		      :style="verticalStyle2[isCollapse?'close':'open'].user.one.box.default" index="1">
		      <template slot="title">
		        <i :style="verticalStyle2[isCollapse?'close':'open'].user.one.icon.default"
		          class="icon iconfont icon-kuaijiezhifu"></i>
		        <span :style="verticalStyle2[isCollapse?'close':'open'].user.one.title.default"
		          slot="title">{{verticalStyle2.open.user.one.title.text}}</span>
		      </template>
		      <el-menu-item index="/updatePassword" @click="menuHandler('updatePassword')">修改密码</el-menu-item>
		      <el-menu-item index="/center" @click="menuHandler('center')">个人信息</el-menu-item>
		    </el-submenu>
			<template v-for="(menu,index) in menuList.backMenu">
				<el-submenu v-if="menu.child.length > 1 || !verticalIsMultiple" class="other" popper-class="other" :popper-append-to-body="false" :style="verticalStyle2[isCollapse?'close':'open'].menu.one.box.default" :index="index+2+''">
					<template slot="title">
						<i :style="verticalStyle2[isCollapse?'close':'open'].menu.one.icon.default" class="el-icon-menu" :class="icons[index]"></i>
						<span :style="verticalStyle2[isCollapse?'close':'open'].menu.one.title.default" slot="title">{{menu.menu + (verticalFlag ? '管理' : '')}}</span>
					</template>
					<el-menu-item v-for="(child,sort) in menu.child" :key="sort" :index="'/'+child.tableName" @click="menuHandler(child.tableName)">{{ child.menu }}</el-menu-item>
				</el-submenu>
				<el-menu-item v-if="menu.child.length <= 1 && verticalIsMultiple" class="other" popper-class="other" :style="verticalStyle2[isCollapse?'close':'open'].menu.one.box.default" @click="menuHandler(menu.child[0].tableName)" :index="'/'+menu.child[0].tableName">
				  <div class="el-tooltip">
				    <i :style="verticalStyle2[isCollapse?'close':'open'].menu.one.icon.default" class="el-icon-menu" :class="icons[index]"></i>
				    <span :style="verticalStyle2[isCollapse?'close':'open'].menu.one.title.default" slot="title">{{menu.child[0].menu + (verticalFlag ? '管理' : '')}}</span>
				  </div>
				</el-menu-item>
			</template>
		  </el-menu>
		</el-scrollbar>


	</div>
</template>

<script>
import menu from '@/utils/menu'
export default {
	data() {
		return {
			menuList: [],
			dynamicMenuRoutes: [],
			role: '',
			user: null,
			avatar:'',
			icons: [
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-fold',
				'el-icon-s-unfold',
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
				'el-icon-s-custom',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check',
				'el-icon-s-grid',
				'el-icon-menu',
				'el-icon-chat-dot-square',
				'el-icon-message',
				'el-icon-postcard',
				'el-icon-position',
				'el-icon-microphone',
				'el-icon-close-notification',
				'el-icon-bangzhu',
				'el-icon-time',
				'el-icon-odometer',
				'el-icon-crop',
				'el-icon-aim',
				'el-icon-switch-button',
				'el-icon-full-screen',
				'el-icon-copy-document',
				'el-icon-mic',
				'el-icon-stopwatch',
			],
			menulistBorderBottom: {},
			verticalFlag: false,
			isCollapse: false,
			verticalStyle2: {"isCollapse":false,"close":{"contentBox":{"hover":{},"active":{"margin":"0 0 0 54px"},"default":{"minHeight":"100%","padding":"111px 0 0 36px","margin":"0","position":"relative","background":"#f5f5f5","display":"block"}},"box":{"hover":{},"active":{"width":"54px"},"default":{"boxShadow":"0px 0 0px rgba(255,205,155,1)","padding":"20px 0 0","borderColor":"#d9d9d9","bottom":"0","transition":"width 0.3s","overflow":"hidden","top":"81px","left":"0","background":"#19aa8d","borderWidth":"0 1px 0 0","width":"36px","fontSize":"0px","position":"fixed","borderStyle":"solid","height":"100%","zIndex":"1003"}},"menu":{"two":{"title":{"hover":{"padding":"0 0px","borderColor":"#fbafbf","color":"#fff","textAlign":"center","background":"#222!important","borderWidth":"0px 0","lineHeight":"auto","borderStyle":"solid","height":"auto"},"active":{"padding":"0 0px","borderColor":"#fbafbf","color":"#fff","textAlign":"center","background":"#222!important","borderWidth":"0px 0","lineHeight":"auto","borderStyle":"solid","height":"auto"},"default":{"padding":"0 0px","borderColor":"#ffd0da","color":"#fff","textAlign":"center","background":"#000","borderWidth":"0px 0","lineHeight":"auto","fontSize":"12px","borderStyle":"solid","height":"auto"}},"box":{"hover":{},"default":{"border":"none","padding":"0","fontSize":"inherit","borderRadius":"0px","left":"-5px","background":"none"}}},"box":{"hover":{},"default":{"border":0,"padding":"10px 0px","listStyle":"none","margin":"10px 0 0","flexWrap":"wrap","background":"none","display":"flex","width":"100%","position":"relative"}},"one":{"box1":{"hover":{"color":"#fff","background":"none"},"active":{"color":"#fff","background":"none"},"default":{"cursor":"pointer","padding":"0","borderColor":"rgba(255, 255, 255, 0.23)","whiteSpace":"nowrap","color":"#fff","textAlign":"center","background":"none","borderWidth":"0 0 0px","width":"100%","fontSize":"inherit","position":"relative","borderStyle":"solid","height":"auto"}},"icon":{"hover":{},"default":{"verticalAlign":"middle","margin":"0 auto","color":"#f6f6f6","textAlign":"center","display":"inline-block","width":"24px","fontSize":"24px"},"flag":true},"box":{"hover":{},"default":{"width":"100%","padding":"0px","listStyle":"none","margin":"0 0 5px","fontSize":"inherit"}},"title":{"hover":{},"default":{"width":"0","verticalAlign":"middle","fontSize":"inherit","color":"inherit","height":"0"}},"arrow":{"hover":{},"default":{"verticalAlign":"middle","margin":"-7px 0 0 0","top":"50%","color":"inherit","display":"none","fontSize":"12px","position":"absolute","right":"20px"}}}},"btn":{"icon":{"hover":{},"default":{"color":"#96e3d3","margin":"0 2px 0 0","fontSize":"24px"},"text":"icon-menu02"},"hover":{"opacity":"0.8"},"default":{"border":"0","cursor":"pointer","padding":"0 0px","margin":"81px 0px 0px","color":"#fff","outline":"none","borderRadius":"4px","top":"0","left":"4px","background":"none","width":"30px","fontSize":"14px","position":"fixed","height":"30px","zIndex":"1003"},"text":""},"user":{"two":{"title":{"hover":{"padding":"0 0px","backgroundColor":"#222!important","lineHeight":"auto","color":"#fff","height":"auto"},"active":{"padding":"0 0px","backgroundColor":"#222!important","lineHeight":"auto","color":"#fff","height":"auto"},"default":{"padding":"0 0px","backgroundColor":"#000","lineHeight":"auto","color":"#fff","textAlign":"center","height":"auto"}},"box":{"hover":{},"default":{"border":"none","left":"-5px"}}},"one":{"box1":{"hover":{"color":"#fff","background":"none"},"active":{"color":"#fff","background":"none"},"default":{"cursor":"pointer","padding":"0 0px","borderColor":"#ddd","whiteSpace":"nowrap","color":"#fff","textAlign":"center","background":"none","borderWidth":"0 0 0px","position":"relative","borderStyle":"solid"}},"icon":{"hover":{},"default":{"verticalAlign":"middle","margin":"0","color":"#f6f6f6","textAlign":"center","display":"inline-block","width":"24px","fontSize":"24px"},"flag":true,"text":"icon-kuaijiezhifu"},"box":{"hover":{},"default":{"padding":"0","listStyle":"none","margin":"0 0 0px","background":"none","display":"block","width":"100%","order":"2"}},"title":{"hover":{},"default":{"width":"0","verticalAlign":"middle","fontSize":"14px","color":"inherit","height":"0"}},"arrow":{"hover":{},"default":{"verticalAlign":"middle","margin":"-7px 0 0 0","top":"50%","color":"inherit","display":"none","fontSize":"12px","position":"absolute","right":"20px"}}}},"userinfo":{"nickname":{"hover":{},"default":{"fontSize":"24px","lineHeight":"1.5","color":"#fff","textAlign":"center"}},"img":{"hover":{},"default":{"width":"100%","objectFit":"cover","borderRadius":"20px","display":"block","height":"170px"}},"box":{"hover":{},"default":{"width":"100%","padding":"20px","display":"none","height":"auto"}}},"home":{"two":{"title":{"hover":{"padding":"0 20px","backgroundColor":"#222 !important","lineHeight":"56px","color":"#fff","height":"56px"},"active":{"padding":"0 20px","backgroundColor":"#222 !important","lineHeight":"56px","color":"#fff","height":"56px"},"default":{"padding":"0 20px","backgroundColor":"#fff","lineHeight":"56px","color":"#646","height":"56px"}},"box":{"hover":{},"default":{"border":"none"}}},"one":{"box1":{"hover":{"color":"#fff","background":"none"},"active":{"color":"#fff","background":"none"},"default":{"cursor":"pointer","padding":"0","borderColor":"#ddd","whiteSpace":"nowrap","color":"#fff","textAlign":"center","background":"none","borderWidth":"0 0 0px","position":"relative","borderStyle":"solid"}},"icon":{"hover":{},"default":{"verticalAlign":"middle","margin":"0","color":"#f6f6f6","textAlign":"center","display":"inline-block","width":"24px","fontSize":"24px"},"flag":true,"text":"icon-home8"},"box":{"hover":{},"default":{"width":"100%","padding":"0","listStyle":"none","margin":"0 0 5px","background":"none","display":"block"}},"title":{"hover":{},"default":{"width":"0","verticalAlign":"middle","fontSize":"14px","color":"inherit","height":"0"}},"arrow":{"hover":{},"default":{"verticalAlign":"middle","margin":"-7px 0 0 0","top":"50%","color":"inherit","display":"none","fontSize":"12px","position":"absolute","right":"20px"}}}}},"open":{"contentBox":{"hover":{},"default":{"minHeight":"100%","padding":"111px 0 0 193px","margin":"0","position":"relative","background":"#f5f5f5","display":"block"}},"box":{"hover":{},"default":{"boxShadow":"0px 0 0px rgba(255,205,155,1)","padding":"30px 0px 0","borderColor":"#acb0b9","bottom":"0px","transition":"width 0.3s","overflow":"hidden","top":"81px","left":"0px","background":"url(http://codegen.caihongy.cn/20231024/be133e1659f34eb09400c8921af694b9.png) no-repeat center top / 100% auto,#fff","borderWidth":"0 1px 0 0","width":"193px","fontSize":"14px","position":"fixed","borderStyle":"solid","height":"100%","zIndex":"1001"}},"menu":{"two":{"title":{"hover":{"padding":"0 10px 0 40px","lineHeight":"18px","color":"#272636","background":"url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d","height":"18px"},"active":{"padding":"0 10px 0 40px","lineHeight":"18px","color":"#272636","background":"url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d","height":"18px"},"default":{"padding":"0 10px 0 40px","margin":"0px 0 0","borderColor":"rgba(126, 96, 16, .2)","color":"#272636","textAlign":"left","borderRadius":"0px","background":"url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center","borderWidth":"0 0 0px","width":"auto","lineHeight":"18px","fontSize":"inherit","borderStyle":"dashed","height":"18px"}},"box":{"hover":{},"default":{"border":"0px solid rgba(252, 234, 238, .9)","minHeight":"50vh","padding":"10px 0","margin":"0px auto","borderColor":"#ddd","borderRadius":"0px","background":"#eee","borderWidth":"0 0 0px","width":"auto","fontSize":"inherit","borderStyle":"solid"}}},"box":{"hover":{},"default":{"border":0,"padding":"0 0 80px","listStyle":"none","margin":"0","alignItems":"flex-start","flexWrap":"wrap","background":"none","display":"flex","position":"relative"}},"one":{"box1":{"hover":{"borderColor":"#a5b2c2","color":"#f5f5f5","borderStyle":"solid","background":"#2f4050","borderWidth":"1px 0 0","height":"auto"},"active":{"borderColor":"#a5b2c2","color":"#fff","borderStyle":"solid","background":"#19aa8d","borderWidth":"1px 0 0","height":"auto"},"default":{"cursor":"pointer","border":"0px solid rgba(3, 152, 15, .2)","padding":"0px 15px","borderColor":"#a5b2c2","whiteSpace":"nowrap","color":"#f5f5f5","borderRadius":"0px","background":"#2f4050","borderWidth":"1px 0 0","fontSize":"inherit","lineHeight":"30px","position":"relative","borderStyle":"solid","height":"auto"}},"icon":{"hover":{},"default":{"width":"auto","verticalAlign":"middle","margin":"0 3px","fontSize":"inherit","color":"inherit","textAlign":"center"},"flag":true},"box":{"hover":{},"default":{"width":"100%","padding":"0 0 0px","listStyle":"none","margin":"0","lineHeight":"auto","height":"auto"}},"title":{"hover":{},"default":{"verticalAlign":"middle","color":"inherit","textAlign":"center","display":"inline-block","width":"auto","fontSize":"inherit","lineHeight":"30px"}},"arrow":{"hover":{},"default":{"verticalAlign":"middle","margin":"-7px 0 0 0","top":"50%","color":"#aab4c0","fontSize":"inherit","position":"absolute","right":"10px"}}}},"btn":{"icon":{"hover":{},"default":{"color":"#9aa8b3","margin":"0px 2px","fontSize":"20px"},"text":"icon-menu02"},"hover":{"opacity":"0.8"},"default":{"border":"0px solid #ddd","cursor":"pointer","padding":"0 0px","margin":"81px 0px 0px","color":"#fff","display":"block","outline":"none","borderRadius":"4px","top":"0","left":"153px","background":"none","width":"30px","fontSize":"inherit","position":"fixed","height":"30px","zIndex":"1005"},"text":""},"user":{"two":{"title":{"hover":{"padding":"0 10px 0 40px","lineHeight":"18px","color":"#272636","background":"url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d","height":"18px"},"active":{"padding":"0 10px 0 40px","lineHeight":"18px","color":"#272636","background":"url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d","height":"18px"},"default":{"padding":"0 10px 0 40px","borderColor":"#eee","color":"#272636","textAlign":"left","background":"url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center","borderWidth":"0 0 0px","width":"auto","lineHeight":"18px","fontSize":"inherit","borderStyle":"solid","height":"18px"}},"box":{"hover":{},"default":{"border":"0px solid rgba(3, 152, 15, .2)","minHeight":"50vh","padding":"10px 0","margin":"0px auto","fontSize":"inherit","background":"#eee"}}},"one":{"box1":{"hover":{"borderColor":"#a5b2c2","color":"#f5f5f5","borderStyle":"solid","background":"#2f4050","borderWidth":"1px 0 0","height":"auto"},"active":{"borderColor":"#a5b2c2","color":"#fff","borderStyle":"solid","background":"#19aa8d","borderWidth":"1px 0 0","height":"auto"},"default":{"cursor":"pointer","padding":"0px 15px","borderColor":"#a5b2c2","whiteSpace":"nowrap","color":"#f5f5f5","borderRadius":"0px","background":"#2f4050","borderWidth":"1px 0 0","fontSize":"inherit","lineHeight":"30px","position":"relative","borderStyle":"solid","height":"auto"}},"icon":{"hover":{},"default":{"width":"auto","verticalAlign":"middle","margin":"0 3px","fontSize":"inherit","color":"inherit","textAlign":"center"},"flag":true,"text":"icon-touxiang09"},"box":{"hover":{},"default":{"padding":"0 0 0px","listStyle":"none","margin":"0","display":"block","width":"100%","lineHeight":"auto","order":"2","height":"auto"}},"title":{"hover":{},"default":{"verticalAlign":"middle","color":"inherit","textAlign":"center","display":"inline-block","width":"auto","fontSize":"inherit","lineHeight":"32px"},"text":"我的信息"},"arrow":{"hover":{},"default":{"verticalAlign":"middle","margin":"-7px 0 0 0","top":"50%","color":"#aab4c0","fontSize":"inherit","position":"absolute","right":"10px"}}}},"userinfo":{"nickname":{"hover":{},"default":{"fontSize":"18px","lineHeight":"1.5","color":"#fff","textAlign":"center"}},"img":{"hover":{},"default":{"border":"3px solid #fff","margin":"0 auto 6px","objectFit":"cover","borderRadius":"100%","display":"block","width":"80px","height":"80px"}},"box":{"hover":{},"default":{"width":"100%","padding":"0","margin":"0 0 20px","display":"none","height":"auto","order":"2"}}},"home":{"two":{"title":{"hover":{"padding":"0px 25px","lineHeight":"44px","color":"#fff","background":"rgba(0, 0, 0, 0.2)","height":"44px"},"active":{"padding":"0px 25px","lineHeight":"44px","color":"#fff","background":"rgba(0, 0, 0, 0.2)","height":"44px"},"default":{"padding":"0px 25px","lineHeight":"44px","color":"#664","background":"none","height":"44px"}},"box":{"hover":{},"default":{"border":"none","display":"none"}}},"one":{"box1":{"hover":{"borderColor":"#327e33","color":"#494848","borderStyle":"solid","background":"#fff","borderWidth":"0 0 0 4px"},"active":{"borderColor":"#327e33","color":"#494848","borderStyle":"solid","background":"#fff","borderWidth":"0 0 0 4px"},"default":{"cursor":"pointer","padding":"0px 15px","borderColor":"#327e33 #327e33 #c7c5c5 #efeeee","whiteSpace":"nowrap","color":"#494848","borderRadius":"0","background":"#efeeee","borderWidth":"0 0 1px 4px","fontSize":"inherit","lineHeight":"58px","position":"relative","borderStyle":"solid","height":"auto"}},"icon":{"hover":{},"default":{"verticalAlign":"middle","margin":"0 3px","color":"#327e33","textAlign":"center","display":"inline-block","width":"auto","fontSize":"24px"},"flag":true,"text":"icon-home8"},"box":{"hover":{},"default":{"padding":"0 0 10px","listStyle":"none","margin":"0","display":"none","width":"100%","fontSize":"inherit","lineHeight":"auto","height":"auto"}},"title":{"hover":{},"default":{"color":"inherit","verticalAlign":"middle","fontSize":"inherit"},"text":"Home"},"arrow":{"hover":{},"default":{"verticalAlign":"middle","margin":"-7px 0 0 0","top":"50%","color":"inherit","fontSize":"12px","position":"absolute","right":"20px"}}}}}},
			verticalIsMultiple: false,
		}
	},
	computed: {
		activeMenu() {
			const route = this.$route
			console.log(route)
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		}
	},
	watch:{
		avatar(){
			this.$forceUpdate()
		},
	},
	mounted() {
		const menus = menu.list()
		if(menus) {
			this.menuList = menus
		} else {
			let params = {
				page: 1,
				limit: 1,
				sort: 'id',
			}
			
			this.$http({
				url: "menu/list",
				method: "get",
				params: params
			}).then(({
				data
			}) => {
				if (data && data.code === 0) {
					this.menuList = JSON.parse(data.data.list[0].menujson);
					this.$storage.set("menus", this.menuList);
				}
			})
		}
		this.role = this.$storage.get('role')
		
		for(let i=0;i<this.menuList.length;i++) {
			if(this.menuList[i].roleName == this.role) {
				this.menuList = this.menuList[i];
				break;
			}
		}
		this.styleChange()
		
		let sessionTable = this.$storage.get("sessionTable")
		this.$http({
			url: sessionTable + '/session',
			method: "get"
		}).then(({
			data
		}) => {
			if (data && data.code === 0) {
				if(sessionTable == 'nonghu') {
					this.avatar = data.data.touxiang
				}
				if(sessionTable == 'zhuanjia') {
					this.avatar = data.data.touxiang
				}
				if(sessionTable=='users') {
					this.avatar = data.data.image
				}
				this.user = data.data;
			} else {
				let message = this.$message
				message.error(data.msg);
			}
		});
	},
	created(){
		this.icons.sort(()=>{
			return (0.5-Math.random())
		})
	},
	methods: {
		collapse() {
		  this.isCollapse = !this.isCollapse
		  this.$emit('oncollapsechange', this.isCollapse)
		},
		styleChange() {
			this.$nextTick(() => {
								document.querySelectorAll('.el-menu-vertical-demo .el-submenu .el-menu').forEach(el => {
				  el.removeAttribute('style')
				  const icon = {"border":"none","display":"none"}
				  Object.keys(icon).forEach((key) => {
					el.style[key] = icon[key]
				  })
				})
											})
		},
		menuHandler(name) {
			let router = this.$router
			name = '/'+name
			router.push(name)
		},
	}
}
</script>
<style lang="scss" scoped>
	.menu-preview {
	  .el-scrollbar {
	    height: 100%;
	
	    & /deep/ .scrollbar-wrapper {
	      overflow-x: hidden;
	    }
		
				// 竖向
		.el-menu-vertical-demo {
		  .el-submenu:first-of-type /deep/ .el-submenu__title .el-submenu__icon-arrow {
		    display: none;
		  }
		}
		
		.el-menu-vertical-demo>.el-menu-item {
				  				  cursor: pointer;
				  				  padding: 0 20px;
				  				  color: #333;
				  				  white-space: nowrap;
				  				  background: #fff;
				  				  position: relative;
				  		}
		
		.el-menu-vertical-demo>.el-menu-item:hover {
						color: #fff;
						background: blue;
					}
		
		.el-menu-vertical-demo .el-submenu /deep/ .el-submenu__title {
						cursor: pointer;
						padding: 0 20px;
						color: #333;
						white-space: nowrap;
						background: #fff;
						position: relative;
					}
		
		.el-menu-vertical-demo .el-submenu /deep/ .el-submenu__title:hover {
						color: #fff;
						background: blue;
					}
		
		.el-menu-vertical-demo .el-submenu /deep/ .el-submenu__title .el-submenu__icon-arrow {
						margin: -7px 0 0 0;
						top: 50%;
						color: inherit;
						vertical-align: middle;
						font-size: 12px;
						position: absolute;
						right: 20px;
					}
		
		.el-menu-vertical-demo .el-submenu {
						padding: 0;
						margin: 0;
						list-style: none;
					}
		
		// .el-menu-vertical-demo .el-submenu /deep/ .el-menu {
		// 				// 		border: none;
		// 				// 		display: none;
		// 				// }
		
		.el-menu-vertical-demo .el-submenu /deep/ .el-menu .el-menu-item {
						padding: 0 40px;
						color: #666;
						background: #fff;
						line-height: 50px;
						height: 50px;
					}
		
		.el-menu-vertical-demo .el-submenu /deep/ .el-menu .el-menu-item:hover {
						padding: 0 40px;
						color: #fff;
						background: red;
						line-height: 50px;
						height: 50px;
					}
		
		.el-menu-vertical-demo .el-submenu /deep/ .el-menu .el-menu-item.is-active {
						padding: 0 40px;
						color: #fff;
						background: blue;
						line-height: 50px;
						height: 50px;
					}
		// 竖向
			  }
	  	}
	// 竖向 样式二-open
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.other {
		font-size: inherit;
		background: none;
	}
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.home {
		font-size: inherit;
		background: none;
	}
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.other>.el-tooltip {
				cursor: pointer;
				border: 0px solid rgba(3, 152, 15, .2);
				padding: 0px 15px;
				color: #f5f5f5;
				white-space: nowrap;
				font-size: inherit;
				border-color: #a5b2c2;
				line-height: 30px;
				border-radius: 0px;
				background: #2f4050;
				border-width: 1px 0 0;
				position: relative;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.other>.el-tooltip:hover {
				color: #f5f5f5 !important;
				background: #2f4050 !important;
				border-color: #a5b2c2 !important;
				border-width: 1px 0 0 !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title {
				cursor: pointer !important;
				border: 0px solid rgba(3, 152, 15, .2) !important;
				padding: 0px 15px !important;
				color: #f5f5f5 !important;
				white-space: nowrap !important;
				font-size: inherit !important;
				border-color: #a5b2c2 !important;
				line-height: 30px !important;
				border-radius: 0px !important;
				background: #2f4050 !important;
				border-width: 1px 0 0 !important;
				position: relative !important;
				border-style: solid !important;
				height: auto !important;
			}
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.other.is-active>.el-tooltip {
				color: #fff !important;
				background: #19aa8d !important;
				border-color: #a5b2c2 !important;
				border-width: 1px 0 0 !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title:hover {
				color: #f5f5f5 !important;
				background: #2f4050 !important;
				border-color: #a5b2c2 !important;
				border-width: 1px 0 0 !important;
				border-style: solid !important;
				height: auto !important;
			}
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other.is-active /deep/ .el-submenu__title {
				color: #fff !important;
				background: #19aa8d !important;
				border-color: #a5b2c2 !important;
				border-width: 1px 0 0 !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title .iconfont {
				margin: 0 3px;
				color: inherit;
				width: auto;
				vertical-align: middle;
				font-size: inherit;
				text-align: center;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title .el-submenu__icon-arrow {
				margin: -7px 0 0 0;
				top: 50%;
				color: #aab4c0;
				vertical-align: middle;
				font-size: inherit;
				position: absolute;
				right: 10px;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 /deep/ .el-submenu.other .el-menu {
				border: 0px solid rgba(252, 234, 238, .9);
				border-radius: 0px;
				padding: 10px 0;
				margin: 0px auto;
				background: #eee;
				width: auto;
				font-size: inherit;
				min-height: 50vh;
				border-color: #ddd;
				border-width: 0 0 0px;
				border-style: solid;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other .el-menu .el-menu-item {
				padding: 0 10px 0 40px !important;
				margin: 0px 0 0 !important;
				color: #272636 !important;
				font-size: inherit !important;
				border-color: rgba(126, 96, 16, .2) !important;
				line-height: 18px !important;
				border-radius: 0px !important;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center !important;
				width: auto !important;
				border-width: 0 0 0px !important;
				border-style: dashed !important;
				text-align: left !important;
				height: 18px !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other .el-menu .el-menu-item:hover {
				padding: 0 10px 0 40px !important;
				color: #272636 !important;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d !important;
				line-height: 18px !important;
				height: 18px !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.other .el-menu .el-menu-item.is-active {
				padding: 0 10px 0 40px !important;
				color: #272636 !important;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d !important;
				line-height: 18px !important;
				height: 18px !important;
			}

	// 竖向 样式二-close
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.other>.el-tooltip {
				cursor: pointer;
				padding: 0;
				color: #fff;
				white-space: nowrap;
				font-size: inherit;
				border-color: rgba(255, 255, 255, 0.23);
				background: none;
				width: 100%;
				border-width: 0 0 0px;
				position: relative;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.other>.el-tooltip:hover {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.other.is-active>.el-tooltip {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title {
				cursor: pointer !important;
				padding: 0 !important;
				color: #fff !important;
				white-space: nowrap !important;
				font-size: inherit !important;
				border-color: rgba(255, 255, 255, 0.23) !important;
				background: none !important;
				width: 100% !important;
				border-width: 0 0 0px !important;
				position: relative !important;
				border-style: solid !important;
				text-align: center !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title:hover {
				color: #fff !important;
				background: none !important;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title .iconfont {
				margin: 0 auto;
				color: #f6f6f6;
				display: inline-block;
				vertical-align: middle;
				width: 24px;
				font-size: 24px;
				text-align: center;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other /deep/ .el-submenu__title .el-submenu__icon-arrow {
				margin: -7px 0 0 0;
				top: 50%;
				color: inherit;
				display: none;
				vertical-align: middle;
				font-size: 12px;
				position: absolute;
				right: 20px;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other .el-menu {
				border: none;
				border-radius: 0px;
				padding: 0;
				left: -5px;
				background: none;
				font-size: inherit;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other .el-menu--vertical.other .el-menu-item {
				padding: 0 0px;
				color: #fff;
				background: #000;
				font-size: 12px;
				border-color: #ffd0da;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other .el-menu--vertical.other .el-menu-item:hover {
				padding: 0 0px;
				color: #fff;
				background: #222!important;
				border-color: #fbafbf;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.other .el-menu--vertical.other .el-menu-item.is-active {
				padding: 0 0px;
				color: #fff;
				background: #222!important;
				border-color: #fbafbf;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	// 竖向 样式二-open-首页
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.home>.el-tooltip {
				cursor: pointer;
				border: 0px solid rgba(3, 152, 15, .2);
				padding: 0px 15px;
				color: #f5f5f5;
				white-space: nowrap;
				font-size: inherit;
				border-color: #a5b2c2;
				line-height: 30px;
				border-radius: 0px;
				background: #2f4050;
				border-width: 1px 0 0;
				position: relative;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.home>.el-tooltip:hover {
				color: #f5f5f5;
				background: #2f4050;
				border-color: #a5b2c2;
				border-width: 1px 0 0;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.home.is-active>.el-tooltip {
				color: #fff;
				background: #19aa8d;
				border-color: #a5b2c2;
				border-width: 1px 0 0;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title {
				cursor: pointer !important;
				border: 0px solid rgba(3, 152, 15, .2) !important;
				padding: 0px 15px !important;
				color: #f5f5f5 !important;
				white-space: nowrap !important;
				font-size: inherit !important;
				border-color: #a5b2c2 !important;
				line-height: 30px !important;
				border-radius: 0px !important;
				background: #2f4050 !important;
				border-width: 1px 0 0 !important;
				position: relative !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title:hover {
				color: #f5f5f5 !important;
				background: #2f4050 !important;
				border-color: #a5b2c2 !important;
				border-width: 1px 0 0 !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title .iconfont {
				margin: 0 3px;
				color: inherit;
				width: auto;
				vertical-align: middle;
				font-size: inherit;
				text-align: center;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title .el-submenu__icon-arrow {
				margin: -7px 0 0 0;
				top: 50%;
				color: #aab4c0;
				vertical-align: middle;
				font-size: inherit;
				position: absolute;
				right: 10px;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home .el-menu {
				border: 0px solid rgba(252, 234, 238, .9);
				border-radius: 0px;
				padding: 10px 0;
				margin: 0px auto;
				background: #eee;
				width: auto;
				font-size: inherit;
				min-height: 50vh;
				border-color: #ddd;
				border-width: 0 0 0px;
				border-style: solid;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home .el-menu .el-menu-item {
				padding: 0 10px 0 40px;
				margin: 0px 0 0;
				color: #272636;
				font-size: inherit;
				border-color: rgba(126, 96, 16, .2);
				line-height: 18px;
				border-radius: 0px;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center;
				width: auto;
				border-width: 0 0 0px;
				border-style: dashed;
				text-align: left;
				height: 18px;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home .el-menu .el-menu-item:hover {
				padding: 0 10px 0 40px;
				color: #272636;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d;
				line-height: 18px;
				height: 18px;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.home .el-menu .el-menu-item.is-active {
				padding: 0 10px 0 40px;
				color: #272636;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d;
				line-height: 18px;
				height: 18px;
			}
	
	// 竖向 样式二-close-首页
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.home>.el-tooltip {
				cursor: pointer;
				padding: 0;
				color: #fff;
				white-space: nowrap;
				font-size: inherit;
				border-color: rgba(255, 255, 255, 0.23);
				background: none;
				width: 100%;
				border-width: 0 0 0px;
				position: relative;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.home>.el-tooltip:hover {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.home.is-active>.el-tooltip {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title {
				cursor: pointer;
				padding: 0;
				color: #fff;
				white-space: nowrap;
				font-size: inherit;
				border-color: rgba(255, 255, 255, 0.23);
				background: none;
				width: 100%;
				border-width: 0 0 0px;
				position: relative;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title:hover {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title .iconfont {
				margin: 0 auto;
				color: #f6f6f6;
				display: inline-block;
				vertical-align: middle;
				width: 24px;
				font-size: 24px;
				text-align: center;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home /deep/ .el-submenu__title .el-submenu__icon-arrow {
				margin: -7px 0 0 0;
				top: 50%;
				color: inherit;
				display: none;
				vertical-align: middle;
				font-size: 12px;
				position: absolute;
				right: 20px;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home .el-menu {
				border: none;
				border-radius: 0px;
				padding: 0;
				left: -5px;
				background: none;
				font-size: inherit;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home .el-menu--vertical.home .el-menu-item {
				padding: 0 0px;
				color: #fff;
				background: #000;
				font-size: 12px;
				border-color: #ffd0da;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home .el-menu--vertical.home .el-menu-item:hover {
				padding: 0 0px;
				color: #fff;
				background: #222!important;
				border-color: #fbafbf;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.home .el-menu--vertical.home .el-menu-item.is-active {
				padding: 0 0px;
				color: #fff;
				background: #222!important;
				border-color: #fbafbf;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	// 竖向 样式二-open-个人中心
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.user>.el-tooltip {
				cursor: pointer;
				border: 0px solid rgba(3, 152, 15, .2);
				padding: 0px 15px;
				color: #f5f5f5;
				white-space: nowrap;
				font-size: inherit;
				border-color: #a5b2c2;
				line-height: 30px;
				border-radius: 0px;
				background: #2f4050;
				border-width: 1px 0 0;
				position: relative;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.user>.el-tooltip:hover {
				color: #f5f5f5;
				background: #2f4050;
				border-color: #a5b2c2;
				border-width: 1px 0 0;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2>.el-menu-item.user.is-active>.el-tooltip {
				color: #fff;
				background: #19aa8d;
				border-color: #a5b2c2;
				border-width: 1px 0 0;
				border-style: solid;
				height: auto;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title {
				cursor: pointer !important;
				border: 0px solid rgba(3, 152, 15, .2) !important;
				padding: 0px 15px !important;
				color: #f5f5f5 !important;
				white-space: nowrap !important;
				font-size: inherit !important;
				border-color: #a5b2c2 !important;
				line-height: 30px !important;
				border-radius: 0px !important;
				background: #2f4050 !important;
				border-width: 1px 0 0 !important;
				position: relative !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title:hover {
				color: #f5f5f5 !important;
				background: #2f4050 !important;
				border-color: #a5b2c2 !important;
				border-width: 1px 0 0 !important;
				border-style: solid !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title .iconfont {
				margin: 0 3px;
				color: inherit;
				width: auto;
				vertical-align: middle;
				font-size: inherit;
				text-align: center;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title .el-submenu__icon-arrow {
				margin: -7px 0 0 0;
				top: 50%;
				color: #aab4c0;
				vertical-align: middle;
				font-size: inherit;
				position: absolute;
				right: 10px;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 /deep/ .el-submenu.user .el-menu {
				border: 0px solid rgba(252, 234, 238, .9);
				border-radius: 0px;
				padding: 10px 0;
				margin: 0px auto;
				background: #eee;
				width: auto;
				font-size: inherit;
				min-height: 50vh;
				border-color: #ddd;
				border-width: 0 0 0px;
				border-style: solid;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user .el-menu .el-menu-item {
				padding: 0 10px 0 40px !important;
				margin: 0px 0 0 !important;
				color: #272636 !important;
				font-size: inherit !important;
				border-color: rgba(126, 96, 16, .2) !important;
				line-height: 18px !important;
				border-radius: 0px !important;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center !important;
				width: auto !important;
				border-width: 0 0 0px !important;
				border-style: dashed !important;
				text-align: left !important;
				height: 18px !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user .el-menu .el-menu-item:hover {
				padding: 0 10px 0 40px !important;
				color: #272636 !important;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d !important;
				line-height: 18px !important;
				height: 18px !important;
			}
	
	.scrollbar-wrapper-open .el-menu-vertical-2 .el-submenu.user .el-menu .el-menu-item.is-active {
				padding: 0 10px 0 40px !important;
				color: #272636 !important;
				background: url(http://codegen.caihongy.cn/20231024/753c2bb5cd6e4056a47828eb31e65a65.png) no-repeat 20px center,#ffe48d !important;
				line-height: 18px !important;
				height: 18px !important;
			}
	
	// 竖向 样式二-close-个人中心
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.user>.el-tooltip {
				cursor: pointer;
				padding: 0;
				color: #fff;
				white-space: nowrap;
				font-size: inherit;
				border-color: rgba(255, 255, 255, 0.23);
				background: none;
				width: 100%;
				border-width: 0 0 0px;
				position: relative;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.user>.el-tooltip:hover {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2>.el-menu-item.user.is-active>.el-tooltip {
				color: #fff;
				background: none;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title {
				cursor: pointer !important;
				padding: 0 !important;
				color: #fff !important;
				white-space: nowrap !important;
				font-size: inherit !important;
				border-color: rgba(255, 255, 255, 0.23) !important;
				background: none !important;
				width: 100% !important;
				border-width: 0 0 0px !important;
				position: relative !important;
				border-style: solid !important;
				text-align: center !important;
				height: auto !important;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title:hover {
				color: #fff !important;
				background: none !important;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title .iconfont {
				margin: 0 auto;
				color: #f6f6f6;
				display: inline-block;
				vertical-align: middle;
				width: 24px;
				font-size: 24px;
				text-align: center;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user /deep/ .el-submenu__title .el-submenu__icon-arrow {
				margin: -7px 0 0 0;
				top: 50%;
				color: inherit;
				display: none;
				vertical-align: middle;
				font-size: 12px;
				position: absolute;
				right: 20px;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user .el-menu {
				border: none;
				border-radius: 0px;
				padding: 0;
				left: -5px;
				background: none;
				font-size: inherit;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user .el-menu--vertical.user .el-menu-item {
				padding: 0 0px;
				color: #fff;
				background: #000;
				font-size: 12px;
				border-color: #ffd0da;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user .el-menu--vertical.user .el-menu-item:hover {
				padding: 0 0px;
				color: #fff;
				background: #222!important;
				border-color: #fbafbf;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
	
	.scrollbar-wrapper-close .el-menu-vertical-2 .el-submenu.user .el-menu--vertical.user .el-menu-item.is-active {
				padding: 0 0px;
				color: #fff;
				background: #222!important;
				border-color: #fbafbf;
				border-width: 0px 0;
				line-height: auto;
				border-style: solid;
				text-align: center;
				height: auto;
			}
</style>
