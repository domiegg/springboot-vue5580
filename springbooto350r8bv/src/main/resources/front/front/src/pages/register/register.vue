<template>
	<div>

	<div class="container" :style='{"minHeight":"100vh","width":"100%","background":"url(\"http://codegen.caihongy.cn/20231023/595d22725fdb4421ab3806ffa9cde944.png\") 6vw calc(50vh - 150px) / 57vw 55vh fixed no-repeat, url(\"http://codegen.caihongy.cn/20231023/4169fe3a937a4c71a2f8462a8bc2c54c.png\") left top / 100% fixed no-repeat","display":"flex"}'>
		<el-form class='rgs-form' v-if="pageFlag=='register'" :style='{"minHeight":"55vh","border":"1px solid #edeef0","padding":"50px 30px 0","margin":"calc(50vh - 150px) 0 10vh 65vw","borderRadius":"0px","background":"#fff","width":"30vw","minWidth":"400px"}' ref="registerForm" :model="registerForm" :rules="rules">
			<div v-if="false" :style='{"margin":"0 0 10px 0","color":"rgba(64, 158, 255, 1)","textAlign":"center","width":"100%","lineHeight":"44px","fontSize":"20px","textShadow":"4px 4px 2px rgba(64, 158, 255, .5)"}'>USER / REGISTER</div>
			<div v-if="true" :style='{"margin":"0 0 30px 0","color":"#000000","textAlign":"center","width":"100%","lineHeight":"40px","fontSize":"30px","fontWeight":"700"}'>智慧农业专家远程指导系统注册</p></div>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="nonghuzhanghao">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('nonghuzhanghao')?'required':''">农户账号：</div>
				<el-input v-model="registerForm.nonghuzhanghao"  placeholder="请输入农户账号" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="mima">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('mima')?'required':''">密码：</div>
				<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="mima2">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('mima')?'required':''">确认密码：</div>
				<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="nonghuxingming">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('nonghuxingming')?'required':''">农户姓名：</div>
				<el-input v-model="registerForm.nonghuxingming"  placeholder="请输入农户姓名" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="xingbie">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('xingbie')?'required':''">性别：</div>
                <el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
                  <el-option
                      v-for="(item,index) in nonghuxingbieOptions"
                      :key="index"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="nianling">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('nianling')?'required':''">年龄：</div>
				<el-input v-model.number="registerForm.nianling"  placeholder="请输入年龄" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="shoujihaoma">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('shoujihaoma')?'required':''">手机号码：</div>
				<el-input v-model="registerForm.shoujihaoma"  placeholder="请输入手机号码" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='nonghu'" prop="touxiang">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('touxiang')?'required':''">头像：</div>
                <file-upload
					tip="点击上传头像"
					action="file/upload"
					:limit="1"
					:multiple="true"
					:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
					@change="nonghutouxiangUploadChange"
				></file-upload>
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="zhuanjiazhanghao">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('zhuanjiazhanghao')?'required':''">专家账号：</div>
				<el-input v-model="registerForm.zhuanjiazhanghao"  placeholder="请输入专家账号" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="mima">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('mima')?'required':''">密码：</div>
				<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="mima2">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('mima')?'required':''">确认密码：</div>
				<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="zhuanjiaxingming">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('zhuanjiaxingming')?'required':''">专家姓名：</div>
				<el-input v-model="registerForm.zhuanjiaxingming"  placeholder="请输入专家姓名" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="xingbie">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('xingbie')?'required':''">性别：</div>
                <el-select v-model="registerForm.xingbie" placeholder="请选择性别" >
                  <el-option
                      v-for="(item,index) in zhuanjiaxingbieOptions"
                      :key="index"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="nianling">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('nianling')?'required':''">年龄：</div>
				<el-input v-model.number="registerForm.nianling"  placeholder="请输入年龄" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="shoujihaoma">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('shoujihaoma')?'required':''">手机号码：</div>
				<el-input v-model="registerForm.shoujihaoma"  placeholder="请输入手机号码" />
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="touxiang">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('touxiang')?'required':''">头像：</div>
                <file-upload
					tip="点击上传头像"
					action="file/upload"
					:limit="1"
					:multiple="true"
					:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
					@change="zhuanjiatouxiangUploadChange"
				></file-upload>
			</el-form-item>
			<el-form-item :style='{"width":"100%","margin":"0 0 20px"}' v-if="tableName=='zhuanjia'" prop="xueli">
				<div v-if="true" :style='{"flex-shrink":"0","padding":"0 6px 0 0","color":"#000000","textAlign":"right","width":"100px","lineHeight":"44px","fontSize":"14px"}' :class="changeRules('xueli')?'required':''">学历：</div>
                <el-select v-model="registerForm.xueli" placeholder="请选择学历" >
                  <el-option
                      v-for="(item,index) in zhuanjiaxueliOptions"
                      :key="index"
                      :label="item"
                      :value="item">
                  </el-option>
                </el-select>
			</el-form-item>
			<el-button :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"20px auto 5px","outline":"none","color":"#fff","borderRadius":"8px","background":"#00AA3A","display":"block","width":"80%","fontSize":"16px","height":"44px"}' type="primary" @click="submitForm('registerForm')">注册</el-button>
			<el-button :style='{"border":"1px solid","cursor":"pointer","padding":"0 10px","margin":"20px auto 5px","outline":"none","color":"#000","borderRadius":"8px","background":"#fff","display":"block","width":"80%","fontSize":"16px","height":"44px"}' @click="resetForm('registerForm')">重置</el-button>
			<router-link :style='{"cursor":"pointer","padding":"20px 10%","color":"rgba(159, 159, 159, 1)","display":"flex","lineHeight":"1","fontSize":"14px","textDecoration":"none","justifyContent":"center"}' to="/login">已有账户登录</router-link>
			<div class="idea1" :style='{"width":"100%","background":"red","display":"none","height":"40px"}'></div>
			<div class="idea2" :style='{"width":"100%","background":"blue","display":"none","height":"40px"}'></div>
		</el-form>
    </div>
  </div>
</div>
</template>

<script>

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			requiredRules: {},
            nonghuxingbieOptions: [],
            zhuanjiaxingbieOptions: [],
            zhuanjiaxueliOptions: [],
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='nonghu'){
				this.registerForm = {
					nonghuzhanghao: '',
					mima: '',
					mima2: '',
					nonghuxingming: '',
					xingbie: '',
					nianling: '',
					shoujihaoma: '',
					touxiang: '',
				}
			}
			if(this.tableName=='zhuanjia'){
				this.registerForm = {
					zhuanjiazhanghao: '',
					mima: '',
					mima2: '',
					zhuanjiaxingming: '',
					xingbie: '',
					nianling: '',
					shoujihaoma: '',
					touxiang: '',
					xueli: '',
					zhuanyelingyu: '',
					yanjiuchengguo: '',
					sfsh: '',
					shhf: '',
					clicktime: '',
					clicknum: '',
					storeupnum: '',
					status: '',
				}
			}
			if ('nonghu' == this.tableName) {
				this.requiredRules.nonghuzhanghao = [{ required: true, message: '请输入农户账号', trigger: 'blur' }]
			}
			if ('nonghu' == this.tableName) {
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('nonghu' == this.tableName) {
				this.requiredRules.nonghuxingming = [{ required: true, message: '请输入农户姓名', trigger: 'blur' }]
			}
			if ('zhuanjia' == this.tableName) {
				this.requiredRules.zhuanjiazhanghao = [{ required: true, message: '请输入专家账号', trigger: 'blur' }]
			}
			if ('zhuanjia' == this.tableName) {
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('zhuanjia' == this.tableName) {
				this.requiredRules.zhuanjiaxingming = [{ required: true, message: '请输入专家姓名', trigger: 'blur' }]
			}
			if ('zhuanjia' == this.tableName) {
				this.requiredRules.xueli = [{ required: true, message: '请输入学历', trigger: 'blur' }]
			}
		}
	},
    created() {
		this.pageFlag = this.$route.query.pageFlag;
		if(this.$route.query.pageFlag=='register'){
		  if ('nonghu' == this.tableName) {
			this.rules.nonghuzhanghao = [{ required: true, message: '请输入农户账号', trigger: 'blur' }];
		  }
		  if ('nonghu' == this.tableName) {
			this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
		  }
		  if ('nonghu' == this.tableName) {
			this.rules.nonghuxingming = [{ required: true, message: '请输入农户姓名', trigger: 'blur' }];
		  }
			this.nonghuxingbieOptions = "男,女".split(',');
		  if ('nonghu' == this.tableName) {
			this.rules.nianling = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
		  }
		  if ('nonghu' == this.tableName) {
			this.rules.shoujihaoma = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.zhuanjiazhanghao = [{ required: true, message: '请输入专家账号', trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.zhuanjiaxingming = [{ required: true, message: '请输入专家姓名', trigger: 'blur' }];
		  }
			this.zhuanjiaxingbieOptions = "男,女".split(',');
		  if ('zhuanjia' == this.tableName) {
			this.rules.nianling = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.shoujihaoma = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
		  }
			this.zhuanjiaxueliOptions = "大专,本科,硕士研究生,博士研究生".split(',');
		  if ('zhuanjia' == this.tableName) {
			this.rules.xueli = [{ required: true, message: '请输入学历', trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.clicknum = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.storeupnum = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
		  }
		  if ('zhuanjia' == this.tableName) {
			this.rules.status = [{ required: true, validator: this.$validate.isIntNumer, trigger: 'blur' }];
		  }
		}
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
      // 获取uuid
      getUUID () {
        return new Date().getTime();
      },
        // 下二随
      nonghutouxiangUploadChange(fileUrls) {
          this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
      },
      zhuanjiatouxiangUploadChange(fileUrls) {
          this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
      },

        // 多级联动参数


      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var url=this.tableName+"/register";
				if((!this.registerForm.nonghuzhanghao) && `nonghu` == this.tableName){
					this.$message.error(`农户账号不能为空`);
					return
				}
               if(`nonghu` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
                this.$message.error(`两次密码输入不一致`);
                return
               }
				if((!this.registerForm.mima) && `nonghu` == this.tableName){
					this.$message.error(`密码不能为空`);
					return
				}
				if((!this.registerForm.nonghuxingming) && `nonghu` == this.tableName){
					this.$message.error(`农户姓名不能为空`);
					return
				}
				if(`nonghu` == this.tableName && this.registerForm.nianling &&(!this.$validate.isIntNumer2(this.registerForm.nianling))){
					this.$message.error(`年龄应输入整数`);
					return
				}
					if(`nonghu` == this.tableName && this.registerForm.shoujihaoma &&(!this.$validate.isMobile2(this.registerForm.shoujihaoma))){
						this.$message.error(`手机号码应输入手机格式`);
						return
					}
			  if(this.tableName=='zhuanjia'){
				  this.registerForm.sfsh = '待审核'
			  }
				if((!this.registerForm.zhuanjiazhanghao) && `zhuanjia` == this.tableName){
					this.$message.error(`专家账号不能为空`);
					return
				}
               if(`zhuanjia` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
                this.$message.error(`两次密码输入不一致`);
                return
               }
				if((!this.registerForm.mima) && `zhuanjia` == this.tableName){
					this.$message.error(`密码不能为空`);
					return
				}
				if((!this.registerForm.zhuanjiaxingming) && `zhuanjia` == this.tableName){
					this.$message.error(`专家姓名不能为空`);
					return
				}
				if(`zhuanjia` == this.tableName && this.registerForm.nianling &&(!this.$validate.isIntNumer2(this.registerForm.nianling))){
					this.$message.error(`年龄应输入整数`);
					return
				}
					if(`zhuanjia` == this.tableName && this.registerForm.shoujihaoma &&(!this.$validate.isMobile2(this.registerForm.shoujihaoma))){
						this.$message.error(`手机号码应输入手机格式`);
						return
					}
				if((!this.registerForm.xueli) && `zhuanjia` == this.tableName){
					this.$message.error(`学历不能为空`);
					return
				}
            this.$http.post(url, this.registerForm).then(res => {
              if (res.data.code === 0) {
                this.$message({
                  message: '注册成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.$router.push('/login');
                  }
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		position: relative;
		background: url("http://codegen.caihongy.cn/20231023/595d22725fdb4421ab3806ffa9cde944.png") 6vw calc(50vh - 150px) / 57vw 55vh fixed no-repeat, url("http://codegen.caihongy.cn/20231023/4169fe3a937a4c71a2f8462a8bc2c54c.png") left top / 100% fixed no-repeat;
		
		.el-input {
		  width: auto;
		}
		
		.el-date-editor.el-input {
			width: auto;
		}
		
		.el-form-item /deep/ .el-form-item__content {
						display: flex;
					}
		
		.rgs-form .el-input /deep/ .el-input__inner {
						border: 1px solid #C7C7C7;
						border-radius: 4px;
						padding: 0 10px;
						color: #000000;
						width: 288px;
						font-size: 14px;
						height: 36px;
					}
		
		.rgs-form .el-select /deep/ .el-input__inner {
						border: 1px solid #C7C7C7;
						border-radius: 4px;
						padding: 0 10px;
						color: #000000;
						width: 288px;
						font-size: 14px;
						height: 36px;
					}
		
		.rgs-form .el-date-editor /deep/ .el-input__inner {
						border: 1px solid #C7C7C7;
						border-radius: 4px;
						padding: 0 10px 0 30px;
						color: #000000;
						width: 288px;
						font-size: 14px;
						height: 36px;
					}
		
		.rgs-form .el-date-editor /deep/ .el-input__inner {
						border: 1px solid #C7C7C7;
						border-radius: 4px;
						padding: 0 10px 0 30px;
						color: #000000;
						width: 288px;
						font-size: 14px;
						height: 36px;
					}
		
		.rgs-form /deep/ .el-upload--picture-card {
			background: transparent;
			border: 0;
			border-radius: 0;
			width: auto;
			height: auto;
			line-height: initial;
			vertical-align: middle;
		}
		
		.rgs-form /deep/ .upload .upload-img {
		  		  border: 1px solid #C7C7C7;
		  		  cursor: pointer;
		  		  border-radius: 4px;
		  		  color: #C7C7C7;
		  		  width: 60px;
		  		  font-size: 32px;
		  		  line-height: 60px;
		  		  text-align: center;
		  		  height: 60px;
		  		}
		
		.rgs-form /deep/ .el-upload-list .el-upload-list__item {
		  		  border: 1px solid #C7C7C7;
		  		  cursor: pointer;
		  		  border-radius: 4px;
		  		  color: #C7C7C7;
		  		  width: 60px;
		  		  font-size: 32px;
		  		  line-height: 60px;
		  		  text-align: center;
		  		  height: 60px;
		  		}
		
		.rgs-form /deep/ .el-upload .el-icon-plus {
		  		  border: 1px solid #C7C7C7;
		  		  cursor: pointer;
		  		  border-radius: 4px;
		  		  color: #C7C7C7;
		  		  width: 60px;
		  		  font-size: 32px;
		  		  line-height: 60px;
		  		  text-align: center;
		  		  height: 60px;
		  		}
	}
	.required {
		position: relative;
	}
	.required::after{
				color: red;
				left: 90px;
				position: absolute;
				content: "*";
			}
</style>
