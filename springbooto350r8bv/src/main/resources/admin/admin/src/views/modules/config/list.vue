<template>
	<div class="main-content" :style='{"minHeight":"calc(100vh - 200px)","padding":"10px 0 30px","margin":"0 auto","color":"#333","background":"none","width":"calc(100% - 20px)","fontSize":"12px","height":"100%"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"padding":"10px 0px","margin":"0 0 10px","borderColor":"#ccc","overflow":"hidden","borderRadius":"0px","flexWrap":"wrap","background":"#eee","borderWidth":"1px","display":"flex","fontSize":"inherit","borderStyle":"dotted"}' :inline="true" :model="searchForm">

				<el-row class="actions" :style='{"padding":"0 20px 0 0","margin":"0px 0 0px","color":"#333","alignItems":"center","textAlign":"left","display":"flex","justifyContent":"flex-start","borderRadius":"0px","flexWrap":"wrap","background":"none","width":"100%","fontSize":"inherit","order":"1"}'>



				</el-row>
			</el-form>
			<div :style='{"padding":"0px","borderColor":"#ccc","margin":"0 0 0px","borderRadius":"0px","background":"#eee","borderWidth":"1px 0px 1px 1px","width":"100%","borderStyle":"solid dotted dotted"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","borderColor":"#ccc","color":"inherit","borderRadius":"0px","borderWidth":"0px 0px 0 0px","background":"none","width":"100%","fontSize":"inherit","borderStyle":"dotted"}' 
					:border='false'
					v-if="isAuth('config','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="name"
						label="名称">
						<template slot-scope="scope">
							{{scope.row.name}}
						</template>
					</el-table-column>
					<!-- 无 -->
					<el-table-column :resizable='true' :sortable='true' prop="value" width="200" label="值">
						<template slot-scope="scope">
							<div v-if="scope.row.value">
								<img v-if="scope.row.value.substring(0,4)=='http'" :src="scope.row.value.split(',')[0]" width="100" height="100" style="object-fit: cover">
								<img v-else :src="$base.url+scope.row.value.split(',')[0]" width="100" height="100" style="object-fit: cover">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('config','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-chakan14" :style='{"margin":"0 0 0 4px","fontSize":"14px","color":"#333","height":"auto"}'></span>
								浏览
							</el-button>
							<el-button class="edit" v-if=" isAuth('config','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xiugai19" :style='{"margin":"0 0 0 4px","fontSize":"14px","color":"#333","height":"auto"}'></span>
								编辑
							</el-button>

							<el-button class="view" v-if="isAuth('config','查看评论')" type="success" @click="disscussListHandler(scope.row.id)">
								<span class="icon iconfont icon-chakan14" :style='{"margin":"0 0 0 4px","fontSize":"14px","color":"#333","height":"auto"}'></span>
								查看评论
							</el-button>



						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="上一页 "
				next-text="下一页 "
				:hide-on-single-page="false"
				:style='{"padding":"4px 20px","margin":"0 0 0","borderColor":"#ddd","whiteSpace":"nowrap","color":"inherit","textAlign":"left","background":"#f4f4f4","borderWidth":"0 1px 1px","width":"100%","fontSize":"inherit","borderStyle":"solid","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>





	</div>
</template>

<script>
import axios from 'axios'
import AddOrUpdate from "./add-or-update";
	export default {
		data() {
			return {
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				sfshVisiable: false,
				shForm: {},
				chartVisiable: false,
				chartVisiable1: false,
				chartVisiable2: false,
				chartVisiable3: false,
				chartVisiable4: false,
				chartVisiable5: false,
				addOrUpdateFlag:false,
				layouts: ["total","prev","pager","next"],
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange()
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
		},
		methods: {
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},






    init () {
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
        order: 'desc',
      }
           params['name'] = '%picture%'
			let user = JSON.parse(this.$storage.getObj('userForm'))
			console.log(user)
			this.$http({
				url: "config/page",
				method: "get",
				params: params
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.dataList = data.data.list;
					this.totalPage = data.data.total;
				} else {
					this.dataList = [];
					this.totalPage = 0;
				}
				this.dataListLoading = false;
			});
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id,type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if(type!='info'){
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id,type);
      });
    },
    // 查看评论
    disscussListHandler(id,type) {
	this.$router.push({path:'/discussconfig',query:{refid:id}});
    },
    // 下载
    download(file){
      let arr = file.replace(new RegExp('upload/', "g"), "")
      axios.get(this.$base.url + 'file/download?fileName=' + arr, {
      	headers: {
      		token: this.$storage.get('Token')
      	},
      	responseType: "blob"
      }).then(({
      	data
      }) => {
      	const binaryData = [];
      	binaryData.push(data);
      	const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
      		type: 'application/pdf;chartset=UTF-8'
      	}))
      	const a = document.createElement('a')
      	a.href = objectUrl
      	a.download = arr
      	// a.click()
      	// 下面这个写法兼容火狐
      	a.dispatchEvent(new MouseEvent('click', {
      		bubbles: true,
      		cancelable: true,
      		view: window
      	}))
      	window.URL.revokeObjectURL(data)
      },err=>{
		  axios.get((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] :'') + this.$base.name + '/file/download?fileName=' + arr, {
		  	headers: {
		  		token: this.$storage.get('Token')
		  	},
		  	responseType: "blob"
		  }).then(({
		  	data
		  }) => {
		  	const binaryData = [];
		  	binaryData.push(data);
		  	const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
		  		type: 'application/pdf;chartset=UTF-8'
		  	}))
		  	const a = document.createElement('a')
		  	a.href = objectUrl
		  	a.download = arr
		  	// a.click()
		  	// 下面这个写法兼容火狐
		  	a.dispatchEvent(new MouseEvent('click', {
		  		bubbles: true,
		  		cancelable: true,
		  		view: window
		  	}))
		  	window.URL.revokeObjectURL(data)
		  })
	  })
    },
	// 预览
	preClick(file){
		if(!file){
			return false
		}
		window.open((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] + this.$base.name + '/' + file :this.$base.url + file))
	},
	configstatusChange(e,row){
		if(row.status==0){
			row.passwordwrongnum = 0
		}
		this.$http({
			url:'config/update',
			method:'post',
			data:row
		}).then(res=>{
			if(row.status==1){
				this.$message.error('该用户已锁定')
			}else{
				this.$message.success('该用户已解除锁定')
			}
		})
	},
    // 删除
    async deleteHandler(id ) {
		var ids = id? [Number(id)]: this.dataListSelections.map(item => {
			return Number(item.id);
		});
		await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			type: "warning"
		}).then(async () => {
			await this.$http({
				url: "config/delete",
				method: "post",
				data: ids
			}).then(async ({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "操作成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.search();
						}
					});
            
				} else {
					this.$message.error(data.msg);
				}
			});
		});
    },


  }

};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
	  .el-date-editor.el-input {
	    width: auto;
	  }
	}
	
	.el-input {
	  width: auto;
	}
	
	// form
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 1px solid #b4b4b4;
				border-radius: 2px;
				padding: 0 10px;
				outline: none;
				color: inherit;
				background: #f6f6f6;
				width: 150px;
				font-size: 12px;
				line-height: 36px;
				height: 36px;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border: 1px solid #b4b4b4;
				border-radius: 2px;
				padding: 0 10px;
				color: inherit;
				background: #f6f6f6;
				width: 150px;
				font-size: 12px;
				line-height: 36px;
				height: 36px;
			}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
				border: 1px solid #b4b4b4;
				border-radius: 2px;
				padding: 0 10px 0 30px;
				color: inherit;
				background: #f6f6f6;
				width: 150px;
				font-size: 12px;
				line-height: 36px;
				height: 36px;
			}
	
	.center-form-pv .search {
				border: 1px solid #b4b4b4;
				cursor: pointer;
				border-radius: 2px;
				padding: 0 24px ;
				color: #333;
				background: #f3f3f3;
				width: auto;
				font-size: inherit;
				height: 36px;
			}
	
	.center-form-pv .search:hover {
				opacity: 0.8;
			}
	
	.center-form-pv .actions .add {
				border: 1px solid #eee;
				cursor: pointer;
				padding: 0px 4px;
				margin: 0px 2px 4px;
				color: #000;
				font-weight: 500;
				display: flex;
				font-size: inherit;
				line-height: 24px;
				border-radius: 4px;
				outline: none;
				background: none;
				width: auto;
				align-items: center;
				position: relative;
				height: auto;
			}
	
	.center-form-pv .actions .add:hover {
				border: 1px solid #c0d1ed;
				background: #eaf2ff;
			}
	
	.center-form-pv .actions .del {
				border: 1px solid #eee;
				cursor: pointer;
				padding: 0px 4px;
				margin: 0px 2px 4px;
				color: #000;
				font-size: inherit;
				line-height: 24px;
				border-radius: 4px;
				outline: none;
				background: none;
				width: auto;
				position: relative;
				height: auto;
			}
	
	.center-form-pv .actions .del:hover {
				border: 1px solid #c0d1ed;
				background: #eaf2ff;
			}
	
	.center-form-pv .actions .statis {
				border: 1px solid #eee;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 4px;
				margin: 0px 2px 4px;
				outline: none;
				color: #000;
				background: none;
				width: auto;
				font-size: inherit;
				line-height: 24px;
				height: auto;
			}
	
	.center-form-pv .actions .statis:hover {
				border: 1px solid #c0d1ed;
				background: #eaf2ff;
			}
	
	.center-form-pv .actions .btn18 {
				border: 1px solid #eee;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 4px;
				margin: 0px 2px 4px;
				color: #333;
				background: none;
				width: auto;
				font-size: inherit;
				line-height: 24px;
				height: auto;
			}
	
	.center-form-pv .actions .btn18:hover {
				border: 1px solid #c0d1ed;
				background: #eaf2ff;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: inherit;
				background: #F4F4F4;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 0px 0;
				background: none;
				border-color: #ccc;
				border-width: 0 1px 1px 0;
				border-style: dotted;
				text-align: left;
			}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 10px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: 600;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				font-size: 12px;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}

	
	.el-table /deep/ .el-table__body-wrapper tbody {
				padding: 0;
				width: 100%;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: none;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0 0;
				color: inherit;
				background: none;
				font-size: inherit;
				border-color: #ccc;
				border-width: 0 1px 1px 0px;
				border-style: dotted;
				text-align: left;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 4px 0 0;
				color: #666;
				background: #eaf2ff;
				border-color: #ccc;
				border-width: 0 1px 1px 0px;
				border-style: dotted;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0 0;
				color: inherit;
				background: none;
				font-size: inherit;
				border-color: #ccc;
				border-width: 0 1px 1px 0px;
				border-style: dotted;
				text-align: left;
			}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 10px;
				overflow: hidden;
				color: inherit;
				word-break: break-all;
				white-space: normal;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 2px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				box-shadow: inset 0px 0px 0px 0px rgba(244,100,130,.3);
				outline: none;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: solid;
				min-width: 40px;
				height: 24px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
				border-color: #23b7e5;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 2px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: solid;
				min-width: 40px;
				height: 24px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
				border-color: #45B6B0;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 2px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				box-shadow: inset 0px 0px 0px 0px rgba(244,100,130,.3);
				outline: none;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: solid;
				min-width: 40px;
				height: 24px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
				border-color: #d9534f;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 2px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: solid;
				min-width: 40px;
				height: 24px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
				border-color: #666;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: inherit;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 18px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 18px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: #999;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 18px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0 5px;
				margin: 0 5px;
				color: #999;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: 18px;
				line-height: 28px;
				height: 28px;
			}

	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
			}

	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: inherit;
				background: none;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: auto;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				border: 1px solid #ddd;
				padding: 0 4px;
				margin: 0 5px;
				color: #333;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: auto;
				border-radius: 4px;
				background: #eaf2ff;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				border: 1px solid #ddd;
				padding: 0 4px;
				margin: 0 5px;
				color: #333;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: auto;
				border-radius: 4px;
				background: #eaf2ff;
				text-align: center;
				height: 24px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				color: inherit;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				color: inherit;
				width: 100px;
				font-size: inherit;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 0px solid #ddd;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: inherit;
				display: inline-block;
				font-size: inherit;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: none;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: inherit;
				display: inline-block;
				vertical-align: top;
				font-size: inherit;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				color: inherit;
				display: inline-block;
				width: 50px;
				font-size: inherit;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #eee;
				cursor: pointer;
				padding: 0 0px;
				color: inherit;
				display: inline-block;
				font-size: inherit;
				line-height: 24px;
				border-radius: 3px;
				outline: 0;
				background: #fff;
				width: auto;
				text-align: center;
				height: 24px;
			}
	
	// list one
	.one .list1-view {
				cursor: pointer;
				padding: 0 8px 0 30px;
				margin: 0px 5px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				box-shadow: inset 0px 0px 0px 0px rgba(244,100,130,.3);
				outline: none;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: double;
				min-width: 40px;
				height: 24px;
			}
	
	.one .list1-view:hover {
				border-color: #23b7e5;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.one .list1-edit {
				cursor: pointer;
				padding: 0 8px 0 30px;
				margin: 0px 5px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				box-shadow: inset 0px 0px 0px 0px rgba(244,100,130,.3);
				outline: none;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: double;
				min-width: 40px;
				height: 24px;
			}
	
	.one .list1-edit:hover {
				border-color: #45B6B0;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.one .list1-del {
				cursor: pointer;
				padding: 0 8px 0 30px;
				margin: 0px 5px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				box-shadow: inset 0px 0px 0px 0px rgba(244,100,130,.3);
				outline: none;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: solid;
				min-width: 40px;
				height: 24px;
			}
	
	.one .list1-del:hover {
				border-color: #d9534f;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.one .list1-btn8 {
				cursor: pointer;
				padding: 0 8px 0 30px;
				margin: 0px 5px 5px 0;
				color: #333;
				font-size: inherit;
				border-color: #fff;
				line-height: 24px;
				border-radius: 20px;
				box-shadow: inset 0px 0px 0px 0px rgba(244,100,130,.3);
				outline: none;
				background: none;
				width: auto;
				border-width: 0px;
				position: relative;
				border-style: solid;
				min-width: 40px;
				height: 24px;
			}
	
	.one .list1-btn8:hover {
				border-color: #333;
				border-width: 0px;
				opacity: 0.8;
				border-style: solid;
			}
	
	.main-content .el-table .el-switch {
				display: inline-flex;
				vertical-align: middle;
				line-height: 30px;
				position: relative;
				align-items: center;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
				cursor: pointer;
				margin: 0 10px 0 0;
				color: #333;
				font-weight: 500;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				transition: .2s;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
				cursor: pointer;
				margin: 0 0 0 10px;
				color: #333;
				font-weight: 500;
				display: inline-block;
				vertical-align: middle;
				font-size: 16px;
				transition: .2s;
				height: 30px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
				border: 1px solid #dff0d8;
				cursor: pointer;
				border-radius: 15px;
				margin: 0;
				outline: 0;
				background: #dff0d8;
				display: inline-block;
				width: 40px;
				box-sizing: border-box;
				transition: border-color .3s,background-color .3s;
				height: 20px;
			}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
				border-radius: 100%;
				top: 1px;
				left: 1px;
				background: #fff;
				width: 16px;
				position: absolute;
				transition: all .3s;
				height: 16px;
			}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
				margin: 0 0 0 -17px;
				left: 100%;
			}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
				cursor: pointer;
				display: inline-block;
				vertical-align: middle;
				font-size: 0;
				position: relative;
			}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
				margin: 0 3px;
				color: #666;
				display: inline-block;
				font-size: 18px;
				position: relative;
				transition: .3s;
			}
</style>
