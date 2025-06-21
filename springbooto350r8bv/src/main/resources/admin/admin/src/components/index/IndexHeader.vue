<template>
	<div class="navbar">
		<div class="title" :style='{"position":"absolute","left":"20px","display":"inline-block","order":"0"}'>
			<el-image v-if="false" class="title-img" :style='{"width":"44px","objectFit":"cover","borderRadius":"100%","float":"left","height":"44px"}' src="http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg" fit="cover" />
			<span class="title-name" :style='{"padding":"0 0 0 12px","lineHeight":"auto","fontSize":"18px","color":"inherit","float":"left","fontWeight":"500"}'>{{this.$project.projectName}}</span>
		</div>
		<!-- 系统通知-1 -->
		<el-button class="notice" @click="dialogVisible = true" :style='{"border":"0px solid rgba(0, 0, 0, 0.2)","cursor":"pointer","padding":"0 10px","color":"inherit","borderRadius":"0px","background":"none","display":"inline-block","width":"auto","fontSize":"inherit","minWidth":"120px","height":"auto","order":"4"}' type="success">
		  <span class="icon iconfont icon-gonggao16" :style='{"margin":"0 2px","fontSize":"inherit","color":"inherit","display":"inline-block","height":"auto"}'></span>
		  系统公告
		</el-button>
		<el-dialog title="系统公告" :append-to-body="true" :visible.sync="dialogVisible" width="30%">
		  <div v-html="noticeDetail.content"></div>
		</el-dialog>
		<!--
		<div class="right" :style='{"position":"absolute","right":"20px","top":"8px","display":"flex"}'>
			<div :style='{"cursor":"pointer","margin":"0 5px","lineHeight":"44px","color":"#333"}' class="nickname">{{this.$storage.get('role')}} {{this.$storage.get('adminName')}}</div>
			<div :style='{"cursor":"pointer","margin":"0 5px","lineHeight":"44px","color":"#666"}' v-if="this.$storage.get('role')!='管理员'" class="logout" @click="onIndexTap">退出到前台</div>
			<div :style='{"cursor":"pointer","margin":"0 5px","lineHeight":"44px","color":"#666"}' class="logout" @click="onLogout">退出登录</div>
		</div>
		-->
		
		<el-dropdown @command="handleCommand" trigger="click" :style='{"fontSize":"inherit","color":"inherit","display":"inline-block","order":"11"}'>
		  <div class="el-dropdown-link" :style='{"alignItems":"center","display":"none"}'>
		    <el-image v-if="user" :style='{"boxShadow":"0px 0px 10px rgba(0,0,0,.45)","margin":"0 15px","objectFit":"cover","borderRadius":"100%","display":"inline-block","width":"64px","height":"64px"}' :src="avatar?this.$base.url + avatar : require('@/assets/img/avator.png')" fit="cover"></el-image>
		    <span :style='{"color":"inherit","lineHeight":"32px","fontSize":"inherit"}'>{{this.$storage.get('adminName')}}</span>
		    <span class="icon iconfont icon-a-xiala2" :style='{"color":"inherit","margin":"0 0 0 5px","fontSize":"14px"}'></span>
		  </div>
		  <div class="top-el-dropdown-menu-2" :style='{"alignItems":"center","justifyContent":"center","display":"flex"}'>
		    <div class="item1" @click="handleCommand('')">首页</div>
		    <div class="item2" @click="handleCommand('center')">个人中心</div>
		    <div class="item3" v-if="this.$storage.get('role')!='管理员'" @click="handleCommand('front')">退出到前台</div>
		    <div class="item4" @click="handleCommand('logout')">退出登录</div>
		  </div>
		</el-dropdown>
		
	</div>
</template>

<script>
	import axios from 'axios'
	export default {
		data() {
			return {
				dialogVisible: false,
				ruleForm: {},
				user: null,
				// 系统公告
				noticeDetail: {},
			};
		},
		created() {
			// 系统公告
			this.getNotice()
		},
		computed: {
			avatar(){
				return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
			}
		},
		mounted() {
			let sessionTable = this.$storage.get("sessionTable")
			this.$http({
				url: sessionTable + '/session',
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code === 0) {
					if(sessionTable == 'nonghu') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'zhuanjia') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'users') {
						this.$storage.set('headportrait',data.data.image)
					}
					this.$storage.set('userForm',JSON.stringify(data.data))
					this.user = data.data;
					this.$storage.set('userid',data.data.id);
				} else {
					let message = this.$message
					message.error(data.msg);
				}
			});
		},
		methods: {
			// 获取系统公告
			getNotice() {
				this.$http({
				    url: '/systemnotice/detail/1',
				    method: "get"
				}).then(({data})=>{
                    if ( data && data.code==0 ) {
						this.noticeDetail = data.data
					}
				})
			},
			handleCommand(name) {
				if (name == 'front') {
					this.onIndexTap()
				} else if (name == 'logout') {
					this.onLogout()
				} else if (name == 'board'){
					this.toBoard()
				} else if (name == 'backUp'){
					this.backUp()
				} else {
					let router = this.$router
					name = '/'+name
					router.push(name)
				}
			},
			
			onLogout() {
				let storage = this.$storage
				let router = this.$router
				storage.clear()
				this.$store.dispatch('tagsView/delAllViews')
				router.replace({
					name: "login"
				});
			},
			onIndexTap(){
				window.location.href = `${this.$base.indexUrl}`
			},
		}
	};
</script>


<style lang="scss" scoped>
	.top-el-dropdown-menu-2 div.item1 {
				cursor: pointer;
				padding: 0 10px;
				color: #fff;
				font-size: 14px;
				border-color: #eee;
				border-width: 0 0 0 1px;
				line-height: 73px;
				border-style: dashed;
				text-align: center;
				min-width: 100px;
			}

	.top-el-dropdown-menu-2 div.item1:hover {
				color: #fff;
				background: #000;
			}
	
	.top-el-dropdown-menu-2 div.item2 {
				cursor: pointer;
				padding: 0 10px;
				color: #fff;
				font-size: 14px;
				border-color: #eee;
				border-width: 0 0 0 1px;
				line-height: 73px;
				border-style: dashed;
				text-align: center;
				min-width: 100px;
			}
	
	.top-el-dropdown-menu-2 div.item2:hover {
				color: #fff;
				background: #000;
			}
	
	.top-el-dropdown-menu-2 div.item3 {
				cursor: pointer;
				padding: 0 10px;
				color: #fff;
				font-size: 14px;
				border-color: #eee;
				border-width: 0 0 0 1px;
				line-height: 73px;
				border-style: dashed;
				text-align: center;
				min-width: 100px;
			}
	
	.top-el-dropdown-menu-2 div.item3:hover {
				color: #fff;
				background: #000;
			}
	
	.top-el-dropdown-menu-2 div.item4 {
				cursor: pointer;
				padding: 0 10px;
				color: #fff;
				font-size: 14px;
				border-color: #eee;
				border-width: 0 0 0 1px;
				line-height: 73px;
				border-style: dashed;
				text-align: center;
				min-width: 100px;
			}
	
	.top-el-dropdown-menu-2 div.item4:hover {
				color: #fff;
				background: #000;
			}
</style>
