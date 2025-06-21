<template>
  <div>
    <div class="container" :style='{"minHeight":"100vh","alignItems":"center","background":"url(http://codegen.caihongy.cn/20231027/5fa8bb2ec9d64c91b210d7855e42eb9b.jpg)","display":"flex","width":"100%","backgroundSize":"cover","backgroundPosition":"center center","backgroundRepeat":"no-repeat","justifyContent":"center"}'>
      <el-form :style='{"border":"0px solid #f6f6f6","padding":"20px 20px 20px","boxShadow":"0 0px 0px rgba(0, 0, 0, .2)","margin":"0 0 0 45vw","borderRadius":"20px","textAlign":"center","background":"#fff","width":"40vw","fontSize":"14px","position":"relative","height":"auto"}'>
        <div v-if="true" :style='{"padding":"0px","margin":"20px auto 20px","borderColor":"#ddd","color":"#333","background":"none","borderWidth":"0px","display":"inline-block","width":"80%","lineHeight":"auto","fontSize":"22px","borderStyle":"solid","fontWeight":"600"}' class="title-container">智慧农业专家远程指导系统登录</div>
        <div v-if="loginType==1" class="list-item" :style='{"width":"50%","margin":"0 auto 20px","position":"relative","alignItems":"center","flexWrap":"wrap","display":"flex"}'>
          <div v-if="true" class="lable" :style='{"color":"#666","left":"-150px","textAlign":"right","width":"150px","lineHeight":"44px","fontSize":"inherit","position":"absolute"}'>用户名：</div>
          <input :style='{"padding":"0 10px","borderColor":"#ddd","color":"#666","borderRadius":"20px","background":"#f2f2f2","borderWidth":"0px","width":"100%","fontSize":"inherit","borderStyle":"solid","height":"44px"}' placeholder="请输入用户名" name="username" type="text" v-model="rulesForm.username">
        </div>
        <div v-if="loginType==1" class="list-item" :style='{"width":"50%","margin":"0 auto 20px","position":"relative","alignItems":"center","flexWrap":"wrap","display":"flex"}'>
          <div v-if="true" class="lable" :style='{"color":"#666","left":"-150px","textAlign":"right","width":"150px","lineHeight":"44px","fontSize":"inherit","position":"absolute"}'>密码：</div>
          <input :style='{"padding":"0 10px","borderColor":"#ddd","color":"#666","borderRadius":"20px","background":"#f2f2f2","borderWidth":"0px","width":"100%","fontSize":"inherit","borderStyle":"solid","height":"44px"}' placeholder="请输入密码" name="password" type="password" v-model="rulesForm.password">
        </div>

        <div :style='{"boxShadow":"0 0px 0px rgba(60, 60, 60, .1)","padding":"0 0px","margin":"10px auto","borderColor":"#bfbfbf","borderRadius":"0px","textAlign":"left","borderWidth":"0px","width":"50%","fontSize":"inherit","lineHeight":"44px","borderStyle":"solid"}' v-if="roles.length>1" prop="loginInRole" class="list-type">
          <el-radio v-if="loginType==1||(loginType==2&&item.roleName!='管理员')" v-for="item in roles" v-bind:key="item.roleName" v-model="rulesForm.role" :label="item.roleName">{{item.roleName}}</el-radio>
        </div>

		
        <div :style='{"margin":"20px auto 0","alignItems":"center","flexWrap":"wrap","display":"flex","width":"50%","fontSize":"16px","position":"relative","justifyContent":"flex-start"}'>
          <el-button v-if="loginType==1" :style='{"border":"0px solid #fff","cursor":"pointer","padding":"0 0 0 20px","margin":"0 0 20px","color":"#fff","textAlign":"center","letterSpacing":"10px","outline":"none","borderRadius":"50px","background":"#19aa8d","width":"100%","fontSize":"20px","height":"50px"}' type="primary" @click="login()" class="loginInBt">登录</el-button>
        </div>
      </el-form>

    </div>
  </div>
</template>
<script>
import menu from "@/utils/menu";
export default {
  data() {
    return {
		verifyCheck2: false,
		flag: false,
      baseUrl:this.$base.url,
      loginType: 1,
      rulesForm: {
        username: "",
        password: "",
        role: "",
      },
      menus: [],
      roles: [],
      tableName: "",
    };
  },
  mounted() {
    let menus = menu.list();
    this.menus = menus;

    for (let i = 0; i < this.menus.length; i++) {
      if (this.menus[i].hasBackLogin=='是') {
        this.roles.push(this.menus[i])
      }
    }

  },
  created() {

  },
  destroyed() {
	    },
  components: {
  },
  methods: {

    //注册
    register(tableName){
		this.$storage.set("loginTable", tableName);
		this.$router.push({path:'/register',query:{pageFlag:'register'}})
    },
    // 登陆
    login() {

		if (!this.rulesForm.username) {
			this.$message.error("请输入用户名");
			return;
		}
		if (!this.rulesForm.password) {
			this.$message.error("请输入密码");
			return;
		}
		if(this.roles.length>1) {
			if (!this.rulesForm.role) {
				this.$message.error("请选择角色");
				return;
			}

			let menus = this.menus;
			for (let i = 0; i < menus.length; i++) {
				if (menus[i].roleName == this.rulesForm.role) {
					this.tableName = menus[i].tableName;
				}
			}
		} else {
			this.tableName = this.roles[0].tableName;
			this.rulesForm.role = this.roles[0].roleName;
		}
		
		this.loginPost()
    },
	loginPost() {
		this.$http({
			url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
			method: "post"
		}).then(({ data }) => {
			if (data && data.code === 0) {
				this.$storage.set("Token", data.token);
				this.$storage.set("role", this.rulesForm.role);
				this.$storage.set("sessionTable", this.tableName);
				this.$storage.set("adminName", this.rulesForm.username);
				this.$router.replace({ path: "/" });
			} else {
				this.$message.error(data.msg);
			}
		});
	},
  }
}
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  position: relative;
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
      background: url(http://codegen.caihongy.cn/20231027/5fa8bb2ec9d64c91b210d7855e42eb9b.jpg);
        
  .list-item /deep/ .el-input .el-input__inner {
		border-radius: 20px;
		padding: 0 10px;
		color: #666;
		background: #f2f2f2;
		width: 100%;
		font-size: inherit;
		border-color: #ddd;
		border-width: 0px;
		border-style: solid;
		height: 44px;
	  }
  
  .list-item.select /deep/ .el-select .el-input__inner {
		border: 0;
		border-radius: 20px;
		padding: 0 10px;
		margin: 20px 0 0;
		color: #666;
		background: #f2f2f2;
		width: 100%;
		font-size: 14px;
		line-height: 44px;
		height: 44px;
	  }
  
  .list-code /deep/ .el-input .el-input__inner {
  	  	border-radius: 20px;
  	  	padding: 0 10px;
  	  	color: #666;
  	  	background: #f2f2f2;
  	  	width: calc(100% - 90px);
  	  	font-size: inherit;
  	  	border-color: #ddd;
  	  	border-width: 0;
  	  	border-style: solid;
  	  	height: 44px;
  	  }

  .list-type /deep/ .el-radio__input .el-radio__inner {
		background: rgba(53, 53, 53, 0);
		border-color: #666666;
	  }
  .list-type /deep/ .el-radio__input.is-checked .el-radio__inner {
        background: #bfbfbf;
        border-color: #bfbfbf;
      }
  .list-type /deep/ .el-radio__label {
		color: #666666;
		font-size: 14px;
	  }
  .list-type /deep/ .el-radio__input.is-checked+.el-radio__label {
        color: rgba(50,50,50,1);
        font-size: 14px;
      }
}

</style>
