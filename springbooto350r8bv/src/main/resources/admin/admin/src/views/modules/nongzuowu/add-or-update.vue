<template>
	<div class="addEdit-block" :style='{"minHeight":"calc(100vh - 200px)","padding":"10px 0 30px","margin":"0 auto","color":"#666","background":"none","width":"calc(100% - 20px)","fontSize":"12px","height":"100%"}'>
		<el-form
			:style='{"border":"1px solid #ddd","padding":"30px 0","boxShadow":"inset 0px 0px 0px 0px #E8EFF7,0 0px 0px 0px rgba(100,100,100,.1)","borderColor":"#ccc","alignItems":"flex-start","borderRadius":"0px","flexWrap":"wrap","background":"#eee","borderWidth":"1px","display":"flex","fontSize":"inherit","borderStyle":"solid"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="200px"
		>
			<template >
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="作物名称" prop="zuowumingcheng">
					<el-input v-model="ruleForm.zuowumingcheng" placeholder="作物名称" clearable  :readonly="ro.zuowumingcheng"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="作物名称" prop="zuowumingcheng">
					<el-input v-model="ruleForm.zuowumingcheng" placeholder="作物名称" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="种植地区" prop="zhongzhidiqu">
					<el-input v-model="ruleForm.zhongzhidiqu" placeholder="种植地区" clearable  :readonly="ro.zhongzhidiqu"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="种植地区" prop="zhongzhidiqu">
					<el-input v-model="ruleForm.zhongzhidiqu" placeholder="种植地区" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="种植面积²" prop="zhongzhimianji">
					<el-input-number v-model="ruleForm.zhongzhimianji" placeholder="种植面积²" :readonly="ro.zhongzhimianji"></el-input-number>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="种植面积²" prop="zhongzhimianji">
					<el-input v-model="ruleForm.zhongzhimianji" placeholder="种植面积²" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="date" v-if="type!='info'" label="种植日期" prop="zhongzhiriqi">
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.zhongzhiriqi" 
						type="date"
						:readonly="ro.zhongzhiriqi"
						placeholder="种植日期"
					></el-date-picker> 
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-else-if="ruleForm.zhongzhiriqi" label="种植日期" prop="zhongzhiriqi">
					<el-input v-model="ruleForm.zhongzhiriqi" placeholder="种植日期" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="种植数量/公顷" prop="zhongzhishuliang">
					<el-input v-model.number="ruleForm.zhongzhishuliang" placeholder="种植数量/公顷" clearable  :readonly="ro.zhongzhishuliang"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="种植数量/公顷" prop="zhongzhishuliang">
					<el-input v-model="ruleForm.zhongzhishuliang" placeholder="种植数量/公顷" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="预计收成/吨" prop="yujishoucheng">
					<el-input-number v-model="ruleForm.yujishoucheng" placeholder="预计收成/吨" :readonly="ro.yujishoucheng"></el-input-number>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="预计收成/吨" prop="yujishoucheng">
					<el-input v-model="ruleForm.yujishoucheng" placeholder="预计收成/吨" readonly></el-input>
				</el-form-item>
			</template>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="textarea" v-if="type!='info'" label="详情" prop="xiangqing">
					<el-input
					  style="min-width: 200px; max-width: 600px;"
					  type="textarea"
					  :rows="8"
					  placeholder="详情"
					  v-model="ruleForm.xiangqing" >
					</el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else-if="ruleForm.xiangqing" label="详情" prop="xiangqing">
					<span :style='{"fontSize":"12px","lineHeight":"24px","color":"inherit","fontWeight":"500","display":"inline-block"}'>{{ruleForm.xiangqing}}</span>
				</el-form-item>
			<el-form-item :style='{"padding":"0","margin":"30px auto","alignItems":"center","textAlign":"center","background":"none","display":"flex","width":"100%","fontSize":"18px"}' class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-tijiao10" :style='{"color":"inherit","margin":"0 2px","fontSize":"inherit"}'></span>
					确定
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-quxiao08" :style='{"color":"inherit","margin":"0 2px","fontSize":"inherit"}'></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui01" :style='{"color":"inherit","margin":"0 2px","fontSize":"inherit"}'></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import { isNumber,isIntNumer,isEmail,isPhone, isMobile,isURL,checkIdCard } from "@/utils/validate";
export default {
	data() {
		let self = this
		var validateIdCard = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!checkIdCard(value)) {
				callback(new Error("请输入正确的身份证号码"));
			} else {
				callback();
			}
		};
		var validateUrl = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isURL(value)) {
				callback(new Error("请输入正确的URL地址"));
			} else {
				callback();
			}
		};
		var validateMobile = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isMobile(value)) {
				callback(new Error("请输入正确的手机号码"));
			} else {
				callback();
			}
		};
		var validatePhone = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isPhone(value)) {
				callback(new Error("请输入正确的电话号码"));
			} else {
				callback();
			}
		};
		var validateEmail = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isEmail(value)) {
				callback(new Error("请输入正确的邮箱地址"));
			} else {
				callback();
			}
		};
		var validateNumber = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isNumber(value)) {
				callback(new Error("请输入数字"));
			} else {
				callback();
			}
		};
		var validateIntNumber = (rule, value, callback) => {
			if(!value){
				callback();
			} else if (!isIntNumer(value)) {
				callback(new Error("请输入整数"));
			} else {
				callback();
			}
		};
		return {
			id: '',
			type: '',
			
			
			ro:{
				zuowumingcheng : false,
				zhongzhidiqu : false,
				zhongzhimianji : false,
				zhongzhiriqi : false,
				zhongzhishuliang : false,
				yujishoucheng : false,
				xiangqing : false,
			},
			
			
			ruleForm: {
				zuowumingcheng: '',
				zhongzhidiqu: '',
				zhongzhimianji: '',
				zhongzhiriqi: '',
				zhongzhishuliang: '',
				yujishoucheng: '',
				xiangqing: '',
			},
		

			
			rules: {
				zuowumingcheng: [
					{ required: true, message: '作物名称不能为空', trigger: 'blur' },
				],
				zhongzhidiqu: [
					{ required: true, message: '种植地区不能为空', trigger: 'blur' },
				],
				zhongzhimianji: [
					{ required: true, message: '种植面积²不能为空', trigger: 'blur' },
					{ validator: validateNumber, trigger: 'blur' },
				],
				zhongzhiriqi: [
				],
				zhongzhishuliang: [
					{ required: true, message: '种植数量/公顷不能为空', trigger: 'blur' },
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				yujishoucheng: [
					{ validator: validateNumber, trigger: 'blur' },
				],
				xiangqing: [
				],
			}
		};
	},
	props: ["parent"],
	computed: {



	},
    components: {
    },
	created() {
	},
	methods: {
		
		// 下载
		download(file){
			window.open(`${file}`)
		},
		// 初始化
		init(id,type) {
			if (id) {
				this.id = id;
				this.type = type;
			}
			if(this.type=='info'||this.type=='else'){
				this.info(id);
			}else if(this.type=='logistics'){
				this.logistics=false;
				this.info(id);
			}else if(this.type=='cross'){
				var obj = this.$storage.getObj('crossObj');
				for (var o in obj){
						if(o=='zuowumingcheng'){
							this.ruleForm.zuowumingcheng = obj[o];
							this.ro.zuowumingcheng = true;
							continue;
						}
						if(o=='zhongzhidiqu'){
							this.ruleForm.zhongzhidiqu = obj[o];
							this.ro.zhongzhidiqu = true;
							continue;
						}
						if(o=='zhongzhimianji'){
							this.ruleForm.zhongzhimianji = obj[o];
							this.ro.zhongzhimianji = true;
							continue;
						}
						if(o=='zhongzhiriqi'){
							this.ruleForm.zhongzhiriqi = obj[o];
							this.ro.zhongzhiriqi = true;
							continue;
						}
						if(o=='zhongzhishuliang'){
							this.ruleForm.zhongzhishuliang = obj[o];
							this.ro.zhongzhishuliang = true;
							continue;
						}
						if(o=='yujishoucheng'){
							this.ruleForm.yujishoucheng = obj[o];
							this.ro.yujishoucheng = true;
							continue;
						}
						if(o=='xiangqing'){
							this.ruleForm.xiangqing = obj[o];
							this.ro.xiangqing = true;
							continue;
						}
				}
				







			}
			
			// 获取用户信息
			this.$http({
				url: `${this.$storage.get('sessionTable')}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					
					var json = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			
			
		},
    // 多级联动参数

    info(id) {
      this.$http({
        url: `nongzuowu/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
        this.ruleForm = data.data;
        //解决前台上传图片后台不显示的问题
        let reg=new RegExp('../../../upload','g')//g代表全部
        } else {
          this.$message.error(data.msg);
        }
      });
    },


    // 提交
    onSubmit() {








var objcross = this.$storage.getObj('crossObj');
      //更新跨表属性
       var crossuserid;
       var crossrefid;
       var crossoptnum;
       if(this.type=='cross'){
                var statusColumnName = this.$storage.get('statusColumnName');
                var statusColumnValue = this.$storage.get('statusColumnValue');
                if(statusColumnName!='') {
                        var obj = this.$storage.getObj('crossObj');
                       if(statusColumnName && !statusColumnName.startsWith("[")) {
                               for (var o in obj){
                                 if(o==statusColumnName){
                                   obj[o] = statusColumnValue;
                                 }
                               }
                               var table = this.$storage.get('crossTable');
                             this.$http({
                                 url: `${table}/update`,
                                 method: "post",
                                 data: obj
                               }).then(({ data }) => {});
                       } else {
                               crossuserid=this.$storage.get('userid');
                               crossrefid=obj['id'];
                               crossoptnum=this.$storage.get('statusColumnName');
                               crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
                        }
                }
        }
		this.$refs["ruleForm"].validate(valid => {
			if (valid) {
				if(crossrefid && crossuserid) {
					this.ruleForm.crossuserid = crossuserid;
					this.ruleForm.crossrefid = crossrefid;
					let params = { 
						page: 1, 
						limit: 10, 
						crossuserid:this.ruleForm.crossuserid,
						crossrefid:this.ruleForm.crossrefid,
					} 
				this.$http({ 
					url: "nongzuowu/page", 
					method: "get", 
					params: params 
				}).then(({ 
					data 
				}) => { 
					if (data && data.code === 0) { 
						if(data.data.total>=crossoptnum) {
							this.$message.error(this.$storage.get('tips'));
							return false;
						} else {
							this.$http({
								url: `nongzuowu/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.nongzuowuCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});

						}
					} else { 
				} 
			});
		} else {
			this.$http({
				url: `nongzuowu/${!this.ruleForm.id ? "save" : "update"}`,
				method: "post",
			   data: this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "操作成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.parent.showFlag = true;
							this.parent.addOrUpdateFlag = false;
							this.parent.nongzuowuCrossAddOrUpdateFlag = false;
							this.parent.search();
							this.parent.contentStyleChange();
						}
					});
				} else {
					this.$message.error(data.msg);
			   }
			});
		 }
         }
       });
    },
    // 获取uuid
    getUUID () {
      return new Date().getTime();
    },
    // 返回
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.nongzuowuCrossAddOrUpdateFlag = false;
      this.parent.contentStyleChange();
    },
  }
};
</script>
<style lang="scss" scoped>
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
	  	  padding: 0 10px 0 0;
	  	  color: inherit;
	  	  background: none;
	  	  font-weight: 600;
	  	  display: inline-block;
	  	  width: 200px;
	  	  font-size: inherit;
	  	  line-height: 40px;
	  	  text-align: right;
	  	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
	  margin-left: 200px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
	  	  border-radius: 4px;
	  	  padding: 0 10px;
	  	  color: inherit;
	  	  background: #f6f6f6;
	  	  width: auto;
	  	  font-size: 12px;
	  	  border-color: #ccc;
	  	  border-width: 1px;
	  	  border-style: solid;
	  	  min-width: 200px;
	  	  height: 30px;
	  	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
	  	  border-radius: 4px;
	  	  padding: 0 10px;
	  	  color: inherit;
	  	  background: #f6f6f6;
	  	  width: auto;
	  	  font-size: 12px;
	  	  border-color: #ccc;
	  	  border-width: 1px;
	  	  border-style: solid;
	  	  min-width: 200px;
	  	  height: 30px;
	  	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
	  	  border-radius: 4px;
	  	  padding: 0 10px;
	  	  color: inherit;
	  	  background: #f6f6f6;
	  	  width: auto;
	  	  font-size: 12px;
	  	  border-color: #ccc;
	  	  border-width: 1px;
	  	  border-style: solid;
	  	  min-width: 200px;
	  	  height: 30px;
	  	}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
	  	  border-radius: 4px;
	  	  padding: 0 10px 0 30px;
	  	  color: inherit;
	  	  background: #f6f6f6;
	  	  width: auto;
	  	  font-size: 12px;
	  	  border-color: #ccc;
	  	  border-width: 1px;
	  	  border-style: solid;
	  	  min-width: 200px;
	  	  height: 30px;
	  	}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
	  	  border: 1px solid #ccc;
	  	  cursor: pointer;
	  	  border-radius: 4px;
	  	  margin: 0 0 10px;
	  	  color: #aaa;
	  	  background: #f6f6f6;
	  	  object-fit: cover;
	  	  width: 150px;
	  	  font-size: 24px;
	  	  line-height: 60px;
	  	  text-align: center;
	  	  height: 60px;
	  	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
	  	  border: 1px solid #ccc;
	  	  cursor: pointer;
	  	  border-radius: 4px;
	  	  margin: 0 0 10px;
	  	  color: #aaa;
	  	  background: #f6f6f6;
	  	  object-fit: cover;
	  	  width: 150px;
	  	  font-size: 24px;
	  	  line-height: 60px;
	  	  text-align: center;
	  	  height: 60px;
	  	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
	  	  border: 1px solid #ccc;
	  	  cursor: pointer;
	  	  border-radius: 4px;
	  	  margin: 0 0 10px;
	  	  color: #aaa;
	  	  background: #f6f6f6;
	  	  object-fit: cover;
	  	  width: 150px;
	  	  font-size: 24px;
	  	  line-height: 60px;
	  	  text-align: center;
	  	  height: 60px;
	  	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
	  	  border: 1px solid #ccc;
	  	  border-radius: 4px;
	  	  padding: 10px 10px;
	  	  color: inherit;
	  	  background: #f6f6f6;
	  	  width: auto;
	  	  font-size: 12px;
	  	  min-height: 80px;
	  	  min-width: 300px;
	  	  height: auto;
	  	}
	
	.add-update-preview .btn .btn1 {
				border: 0px solid #3fcbca;
				cursor: pointer;
				padding: 0 10px;
				margin: 0px 4px;
				color: #fff;
				display: inline-block;
				font-size: 12px;
				line-height: 24px;
				border-radius: 4px;
				outline: none;
				background: #5680ac;
				width: auto;
				height: 24px;
			}
	
	.add-update-preview .btn .btn1:hover {
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn2 {
				border: 0px solid #23b7e5;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0px 4px;
				outline: none;
				color: #fff;
				background: #56ac92;
				width: auto;
				font-size: 12px;
				line-height: 24px;
				height: 24px;
			}
	
	.add-update-preview .btn .btn2:hover {
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn3 {
				border: 0px solid #3fcbca;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0px 4px;
				outline: none;
				color: #fff;
				background: #64ac56;
				width: auto;
				font-size: 12px;
				line-height: 24px;
				height: 24px;
			}
	
	.add-update-preview .btn .btn3:hover {
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn4 {
				border: 0px solid #FF6B6B;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0px 4px;
				outline: none;
				color: #fff;
				background: #be696d;
				width: auto;
				font-size: 12px;
				line-height: 24px;
				height: 24px;
			}
	
	.add-update-preview .btn .btn4:hover {
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn5 {
				border: 0px solid #65C3DF;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 4px;
				outline: none;
				color: #fff;
				background: #aaa;
				width: auto;
				font-size: 12px;
				line-height: 24px;
				height: 24px;
			}
	
	.add-update-preview .btn .btn5:hover {
				opacity: 0.8;
			}
</style>
