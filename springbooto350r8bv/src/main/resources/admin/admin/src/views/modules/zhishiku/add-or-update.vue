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
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="知识名称" prop="zhishimingcheng">
					<el-input v-model="ruleForm.zhishimingcheng" placeholder="知识名称" clearable  :readonly="ro.zhishimingcheng"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="知识名称" prop="zhishimingcheng">
					<el-input v-model="ruleForm.zhishimingcheng" placeholder="知识名称" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="upload" v-if="type!='info' && !ro.fengmian" label="封面" prop="fengmian">
					<file-upload
						tip="点击上传封面"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.fengmian?ruleForm.fengmian:''"
						@change="fengmianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="upload" v-else-if="ruleForm.fengmian" label="封面" prop="fengmian">
					<img v-if="ruleForm.fengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.fengmian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.fengmian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="select" v-if="type!='info'"  label="知识分类" prop="zhishifenlei">
					<el-select :disabled="ro.zhishifenlei" v-model="ruleForm.zhishifenlei" placeholder="请选择知识分类" >
						<el-option
							v-for="(item,index) in zhishifenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="知识分类" prop="zhishifenlei">
					<el-input v-model="ruleForm.zhishifenlei"
						placeholder="知识分类" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="适用对象" prop="shiyongduixiang">
					<el-input v-model="ruleForm.shiyongduixiang" placeholder="适用对象" clearable  :readonly="ro.shiyongduixiang"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="适用对象" prop="shiyongduixiang">
					<el-input v-model="ruleForm.shiyongduixiang" placeholder="适用对象" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="地区" prop="diqu">
					<el-input v-model="ruleForm.diqu" placeholder="地区" clearable  :readonly="ro.diqu"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="地区" prop="diqu">
					<el-input v-model="ruleForm.diqu" placeholder="地区" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="upload" v-if="type!='info'&& !ro.zhidaowendang" label="指导文档" prop="zhidaowendang">
					<file-upload
						tip="点击上传指导文档"
						action="file/upload"
						:limit="1"
						:type="3"
						:multiple="true"
						:fileUrls="ruleForm.zhidaowendang?ruleForm.zhidaowendang:''"
						@change="zhidaowendangUploadChange"
					></file-upload>
				</el-form-item>  
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else-if="ruleForm.zhidaowendang" label="指导文档" prop="zhidaowendang">
					<el-button :style='{"border":"1px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 20px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"#f6f6f6","width":"auto","lineHeight":"30px","fontSize":"12px","height":"30px"}' type="text" size="small" @click="download($base.url+ruleForm.zhidaowendang)">下载</el-button>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else-if="!ruleForm.zhidaowendang" label="指导文档" prop="zhidaowendang">
					<el-button :style='{"border":"1px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 20px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"#f6f6f6","width":"auto","lineHeight":"30px","fontSize":"12px","height":"30px"}' type="text" size="small">无</el-button>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="upload" v-if="type!='info'&& !ro.zhidaoshipin" label="指导视频" prop="zhidaoshipin">
					<file-upload
						tip="点击上传指导视频"
						action="file/upload"
						:limit="1"
						:type="2"
						:multiple="true"
						:fileUrls="ruleForm.zhidaoshipin?ruleForm.zhidaoshipin:''"
						@change="zhidaoshipinUploadChange"
					></file-upload>
				</el-form-item> 
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else-if="ruleForm.zhidaoshipin" label="指导视频" prop="zhidaoshipin">
					<el-button :style='{"border":"1px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 20px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"#f6f6f6","width":"auto","lineHeight":"30px","fontSize":"12px","height":"30px"}' type="text" size="small" @click="download($base.url+ruleForm.zhidaoshipin)">预览</el-button>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else-if="!ruleForm.zhidaoshipin" label="指导视频" prop="zhidaoshipin">
					<el-button :style='{"border":"1px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 20px 0 0","outline":"none","color":"#333","borderRadius":"4px","background":"#f6f6f6","width":"auto","lineHeight":"30px","fontSize":"12px","height":"30px"}' type="text" size="small">无</el-button>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="专家账号" prop="zhuanjiazhanghao">
					<el-input v-model="ruleForm.zhuanjiazhanghao" placeholder="专家账号" clearable  :readonly="ro.zhuanjiazhanghao"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="专家账号" prop="zhuanjiazhanghao">
					<el-input v-model="ruleForm.zhuanjiazhanghao" placeholder="专家账号" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-if="type!='info'"  label="专家姓名" prop="zhuanjiaxingming">
					<el-input v-model="ruleForm.zhuanjiaxingming" placeholder="专家姓名" clearable  :readonly="ro.zhuanjiaxingming"></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else class="input" label="专家姓名" prop="zhuanjiaxingming">
					<el-input v-model="ruleForm.zhuanjiaxingming" placeholder="专家姓名" readonly></el-input>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="date" v-if="type!='info'" label="发布日期" prop="faburiqi">
					<el-date-picker
						format="yyyy 年 MM 月 dd 日"
						value-format="yyyy-MM-dd"
						v-model="ruleForm.faburiqi" 
						type="date"
						:readonly="ro.faburiqi"
						placeholder="发布日期"
					></el-date-picker> 
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' class="input" v-else-if="ruleForm.faburiqi" label="发布日期" prop="faburiqi">
					<el-input v-model="ruleForm.faburiqi" placeholder="发布日期" readonly></el-input>
				</el-form-item>
			</template>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-if="type!='info'"  label="知识内容" prop="zhishineirong">
					<editor 
						style="min-width: 200px; max-width: 600px;"
						v-model="ruleForm.zhishineirong" 
						class="editor" 
						action="file/upload">
					</editor>
				</el-form-item>
				<el-form-item :style='{"padding":"0px 0","margin":"0 auto 20px","color":"inherit","borderRadius":"4px","background":"none","width":"100%","fontSize":"inherit"}' v-else-if="ruleForm.zhishineirong" label="知识内容" prop="zhishineirong">
                    <span :style='{"fontSize":"12px","lineHeight":"24px","color":"inherit","fontWeight":"500","display":"inline-block"}' v-html="ruleForm.zhishineirong"></span>
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
				zhishimingcheng : false,
				fengmian : false,
				zhishifenlei : false,
				shiyongduixiang : false,
				diqu : false,
				zhidaowendang : false,
				zhidaoshipin : false,
				zhishineirong : false,
				zhuanjiazhanghao : false,
				zhuanjiaxingming : false,
				faburiqi : false,
				sfsh : false,
				shhf : false,
				thumbsupnum : false,
				crazilynum : false,
				discussnum : false,
				storeupnum : false,
			},
			
			
			ruleForm: {
				zhishimingcheng: '',
				fengmian: '',
				zhishifenlei: '',
				shiyongduixiang: '',
				diqu: '',
				zhidaowendang: '',
				zhidaoshipin: '',
				zhishineirong: '',
				zhuanjiazhanghao: '',
				zhuanjiaxingming: '',
				faburiqi: '',
				shhf: '',
			},
		
			zhishifenleiOptions: [],

			
			rules: {
				zhishimingcheng: [
					{ required: true, message: '知识名称不能为空', trigger: 'blur' },
				],
				fengmian: [
				],
				zhishifenlei: [
					{ required: true, message: '知识分类不能为空', trigger: 'blur' },
				],
				shiyongduixiang: [
					{ required: true, message: '适用对象不能为空', trigger: 'blur' },
				],
				diqu: [
					{ required: true, message: '地区不能为空', trigger: 'blur' },
				],
				zhidaowendang: [
				],
				zhidaoshipin: [
				],
				zhishineirong: [
				],
				zhuanjiazhanghao: [
				],
				zhuanjiaxingming: [
				],
				faburiqi: [
				],
				sfsh: [
				],
				shhf: [
				],
				thumbsupnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				crazilynum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				discussnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
				],
				storeupnum: [
					{ validator: validateIntNumber, trigger: 'blur' },
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
		this.ruleForm.faburiqi = this.getCurDate()
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
						if(o=='zhishimingcheng'){
							this.ruleForm.zhishimingcheng = obj[o];
							this.ro.zhishimingcheng = true;
							continue;
						}
						if(o=='fengmian'){
							this.ruleForm.fengmian = obj[o];
							this.ro.fengmian = true;
							continue;
						}
						if(o=='zhishifenlei'){
							this.ruleForm.zhishifenlei = obj[o];
							this.ro.zhishifenlei = true;
							continue;
						}
						if(o=='shiyongduixiang'){
							this.ruleForm.shiyongduixiang = obj[o];
							this.ro.shiyongduixiang = true;
							continue;
						}
						if(o=='diqu'){
							this.ruleForm.diqu = obj[o];
							this.ro.diqu = true;
							continue;
						}
						if(o=='zhidaowendang'){
							this.ruleForm.zhidaowendang = obj[o];
							this.ro.zhidaowendang = true;
							continue;
						}
						if(o=='zhidaoshipin'){
							this.ruleForm.zhidaoshipin = obj[o];
							this.ro.zhidaoshipin = true;
							continue;
						}
						if(o=='zhishineirong'){
							this.ruleForm.zhishineirong = obj[o];
							this.ro.zhishineirong = true;
							continue;
						}
						if(o=='zhuanjiazhanghao'){
							this.ruleForm.zhuanjiazhanghao = obj[o];
							this.ro.zhuanjiazhanghao = true;
							continue;
						}
						if(o=='zhuanjiaxingming'){
							this.ruleForm.zhuanjiaxingming = obj[o];
							this.ro.zhuanjiaxingming = true;
							continue;
						}
						if(o=='faburiqi'){
							this.ruleForm.faburiqi = obj[o];
							this.ro.faburiqi = true;
							continue;
						}
						if(o=='thumbsupnum'){
							this.ruleForm.thumbsupnum = obj[o];
							this.ro.thumbsupnum = true;
							continue;
						}
						if(o=='crazilynum'){
							this.ruleForm.crazilynum = obj[o];
							this.ro.crazilynum = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
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
					if(((json.zhuanjiazhanghao!=''&&json.zhuanjiazhanghao) || json.zhuanjiazhanghao==0) && this.$storage.get("role")!="管理员"){
						this.ruleForm.zhuanjiazhanghao = json.zhuanjiazhanghao
						this.ro.zhuanjiazhanghao = true;
					}
					if(((json.zhuanjiaxingming!=''&&json.zhuanjiaxingming) || json.zhuanjiaxingming==0) && this.$storage.get("role")!="管理员"){
						this.ruleForm.zhuanjiaxingming = json.zhuanjiaxingming
						this.ro.zhuanjiaxingming = true;
					}
				} else {
					this.$message.error(data.msg);
				}
			});
			
            this.$http({
				url: `option/zhishifenlei/zhishifenlei`,
				method: "get"
            }).then(({ data }) => {
				if (data && data.code === 0) {
					this.zhishifenleiOptions = data.data;
				} else {
					this.$message.error(data.msg);
				}
            });
			
		},
    // 多级联动参数

    info(id) {
      this.$http({
        url: `zhishiku/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
        this.ruleForm = data.data;
        //解决前台上传图片后台不显示的问题
        let reg=new RegExp('../../../upload','g')//g代表全部
        this.ruleForm.zhishineirong = this.ruleForm.zhishineirong.replace(reg,'../../../springbooto350r8bv/upload');
        } else {
          this.$message.error(data.msg);
        }
      });
    },


    // 提交
    onSubmit() {


	if(this.ruleForm.fengmian!=null) {
		this.ruleForm.fengmian = this.ruleForm.fengmian.replace(new RegExp(this.$base.url,"g"),"");
	}




	if(this.ruleForm.zhidaowendang!=null) {
		this.ruleForm.zhidaowendang = this.ruleForm.zhidaowendang.replace(new RegExp(this.$base.url,"g"),"");
	}

	if(this.ruleForm.zhidaoshipin!=null) {
		this.ruleForm.zhidaoshipin = this.ruleForm.zhidaoshipin.replace(new RegExp(this.$base.url,"g"),"");
	}











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
					url: "zhishiku/page", 
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
								url: `zhishiku/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.zhishikuCrossAddOrUpdateFlag = false;
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
				url: `zhishiku/${!this.ruleForm.id ? "save" : "update"}`,
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
							this.parent.zhishikuCrossAddOrUpdateFlag = false;
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
      this.parent.zhishikuCrossAddOrUpdateFlag = false;
      this.parent.contentStyleChange();
    },
    fengmianUploadChange(fileUrls) {
	    this.ruleForm.fengmian = fileUrls;
    },
    zhidaowendangUploadChange(fileUrls) {
	    this.ruleForm.zhidaowendang = fileUrls;
    },
    zhidaoshipinUploadChange(fileUrls) {
	    this.ruleForm.zhidaoshipin = fileUrls;
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
