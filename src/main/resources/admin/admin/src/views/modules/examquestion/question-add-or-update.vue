<template>
	<div :style='{"color":"#666","padding":"30px","fontSize":"16px"}'>
		<el-form
			:style='{"padding":"30px 25% 30px 15%","borderColor":"#ddd","borderRadius":"12px","borderStyle":"solid","borderWidth":"1px","background":"rgba(255,255,255,0)"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' label="选择情绪测评" prop="paperid">
				<el-select @change="onPaperChange" v-model="ruleForm.paperid" placeholder="选择情绪测评">
					<el-option
						v-for="(item,index) in paperOptions"
						v-bind:key="index"
						:label="item.name"
						:value="item.id"
					></el-option>
				</el-select>
			</el-form-item>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' label="试题" prop="questionname">
				<editor
					myQuillEditor="questionname"
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.questionname" 
					class="editor" 
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' label="类型" prop="type">
				<el-select @change="typeChange" v-model="ruleForm.type" placeholder="类型">
					<el-option label="客观题" value="0"></el-option>
					<el-option label="主观题" value="4"></el-option>
				</el-select>
			</el-form-item>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' v-if="ruleForm.type!=3&&ruleForm.type!=2&&ruleForm.type!=4" label="选项" prop="options">
				<div class="options" v-for="(item,index) in options" v-bind:key="index">
					{{item.text}}  （ 分数：{{item.score}} ） 					<el-button size="mini" @click="editOptions(index)" type="warning" round>修改</el-button><el-button size="mini" @click="deleteOptions(index)" type="warning" round>删除</el-button>
				</div>
				<el-button size="small" @click="addOptionsDialog" type="primary" round>添加选项</el-button>
			</el-form-item>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' label="分数" prop="score" v-if="ruleForm.type&&ruleForm.type!='0'">
				<el-input-number v-model="ruleForm.score" :min="1" :max="100" label="分数"></el-input-number>
			</el-form-item>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' label="分析" prop="analysis">
				<el-input
					type="textarea"
					min="1"
					v-model="ruleForm.analysis"
					placeholder="分析"
					clearable
				></el-input>
			</el-form-item>
			<el-form-item :style='{"border":"0px solid #eee","width":"100%","padding":"2px 20px","margin":"0 0 20px 0","display":"inline-block"}' label="排序" prop="sequence">
				<el-input-number v-model="ruleForm.sequence" :min="1" :max="100" label="排序"></el-input-number>
			</el-form-item>
			<el-form-item :style='{"width":"100%","padding":"0","margin":"20px 0 0"}'>
				<el-button class="btn3" :style='{"border":"0px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 10px 0 0","color":"#fff","borderRadius":"4px","background":"#00ad45","width":"auto","fontSize":"16px","minWidth":"110px","height":"40px"}' type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
					提交
				</el-button>
				<el-button class="btn4" :style='{"border":"0px solid #ccc","cursor":"pointer","padding":"0 10px","margin":"0 10px 0 0","color":"#fff","borderRadius":"4px","background":"#00ad45","width":"auto","fontSize":"16px","minWidth":"110px","height":"40px"}' type="success" @click="back()">
					<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
					取消
				</el-button>
			</el-form-item>
		</el-form>
		<el-dialog title="添加选项" :visible.sync="addOptionsDialogVisiable" width="50%">
			<el-form ref="dialogForm" :model="dialogForm" :rules="dialogRules" label-width="80px">
				<el-form-item label="选项" prop="code">
					<el-select v-model="dialogForm.code" placeholder="选项">
						<el-option label="A" value="A" :disabled="changeCode('A')"></el-option>
						<el-option label="B" value="B" :disabled="changeCode('B')"></el-option>
						<el-option label="C" value="C" :disabled="changeCode('C')"></el-option>
						<el-option label="D" value="D" :disabled="changeCode('D')"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="内容" prop="text">
					<el-input type="textarea" min="1" v-model="dialogForm.text" placeholder="内容" clearable></el-input>
				</el-form-item>
				<el-form-item label="分数" prop="score">
					<el-input-number v-model="dialogForm.score" :min="0" :max="20" label="分数"></el-input-number>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="addOptionsDialogVisiable = false">取 消</el-button>
				<el-button type="primary" @click="addOptions">确 定</el-button>
			</span>
		</el-dialog>
	</div>
</template>
<script>
	import { isNumber } from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			return {
				ruleForm: {},
				options: [],
				addOptionsDialogVisiable: false,
				dialogForm: {},
				paperOptions: [],
				dialogRules: {
					text: [{ required: true, message: "请填写内容", trigger: "blur" }],
					code: [{ required: true, message: "请选择选项", trigger: "blur" }],
					score: [{ required: true, message: "请输入分数", trigger: "blur" }]
				},
				rules: {
					paperid: [{ required: true, message: "请选择情绪测评", trigger: "blur" }],
					questionname: [
						{ required: true, message: "试题内容不能为空", trigger: "blur" }
					],
					type: [{ required: true, message: "请选择试题类型", trigger: "blur" }],
					sequence: [{ required: true, message: "排序不能为空", trigger: "blur" }],
					analysis: [
						{ required: true, message: "分析不能为空", trigger: "blur" }
					],
					score: [{ required: true, message: "分数不能为空", trigger: "blur" }]
				},
				editIndex: -1
			};
		},
		props: ["parent"],
		methods: {
			// 初始化
			init(id) {
				if (id) {
					this.info(id);
				} else {
					this.$http({
						url: this.$api.exampaperpage,
						method: "get",
						params: {
							page: 1,
							limit: 999
						}
					}).then(({ data }) => {
						if (data && data.code === 0) {
							this.paperOptions = data.data.list;
						}
					});
					this.ruleForm.created = new Date();
				}
			},
			info(id) {
				this.$http({
					url: this.$api.exampaperpage,
					method: "get",
					params: {
						page: 1,
						limit: 999
					}
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.paperOptions = data.data.list;
						this.$http({
							url: `${this.$api.examquestioninfo}${id}`,
							method: "get"
						}).then(({ data }) => {
							if (data && data.code === 0) {
								this.ruleForm = data.data;
								this.ruleForm.type = this.ruleForm.type + "";
								this.options = JSON.parse(this.ruleForm.options);
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				});
			},
			// 提交
			onSubmit() {
				if (!this.options && this.type != 3) {
					this.$message.error("请设置选项");
					return;
				}
				this.ruleForm.options = JSON.stringify(this.options);
				this.$refs["ruleForm"].validate(valid => {
					if (valid) {
						this.$http({
							url: `${
								!this.ruleForm.id
								? `${this.$api.examquestionsave}`
								: `${this.$api.examquestionupdate}`
							}`,
							method: "post",
							data: this.ruleForm
						}).then(({ data }) => {
							if (data && data.code === 0) {
								this.$message({
									message: "操作成功",
									type: "success",
									duration: 1500,
									onClose: () => {
										this.parent.showFlag = false;
										this.parent.search();
									}
								});
							} else {
								this.$message.error(data.msg);
							}
						});
					}
				});
			},
			// 返回
			back() {
				this.parent.showFlag = false;
			},
			// 新增选项弹窗
			addOptionsDialog() {
				this.addOptionsDialogVisiable = !this.addOptionsDialogVisiable;
			},
			// 新增选项
			addOptions() {
				this.$refs["dialogForm"].validate(valid => {
					if (valid) {
						if(this.editIndex == -1){
							this.options.push({
								text: this.dialogForm.code + "." + this.dialogForm.text,
								code: this.dialogForm.code,
								score: this.dialogForm.score
							});
			  
						}else{
							this.options[this.editIndex] = {
								text: this.dialogForm.code + "." + this.dialogForm.text,
								code: this.dialogForm.code,
								score: this.dialogForm.score
							}
						}
						this.dialogForm = {};
						this.addOptionsDialogVisiable = !this.addOptionsDialogVisiable;
						this.editIndex = -1
					}
				});
			},
			// 修改选项
			editOptions(index) {
				this.editIndex = index
				let arr = JSON.parse(JSON.stringify(this.options[index]))
				arr.text = arr.text.split(`${arr.code}.`)[1]
				this.dialogForm = arr
				this.addOptionsDialogVisiable = !this.addOptionsDialogVisiable;
			},
			// 删除选项
			deleteOptions(index) {
				this.options.splice(index, 1);
			},
			onPaperChange(e) {
				for (let item of this.paperOptions) {
					if (item.id == e) {
						this.ruleForm.papername = item.name;
					}
				}
			},
			// 是否有相同选项
			changeCode(index) {
				for (let x in this.options) {
					if (this.options[x].code == index) {
						return true
					}
				}
				return false
			},
			typeChange(e) {
			},
		}
	};
</script>
<style lang="scss" scoped>

	.options {
		margin-bottom: 10px;
		display: flex;
		justify-content: space-between;
		width: 400px;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
				padding: 0 10px 0 0;
				color: #666;
				font-weight: 500;
				width: 180px;
				font-size: 16px;
				line-height: 40px;
				text-align: right;
			}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
				border: 0px solid #ccc;
				border-radius: 0px;
				padding: 0 12px;
				color: #666;
				width: 100%;
				font-size: 16px;
				height: 34px;
			}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
				border: 0px solid #ccc;
				border-radius: 0px;
				padding: 0 10px;
				color: #666;
				width: 100%;
				font-size: 16px;
				height: 34px;
			}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
				border: 0px solid #ccc;
				border-radius: 0px;
				padding: 0 10px 0 30px;
				color: #666;
				width: 100%;
				font-size: 16px;
				height: 34px;
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
				border: 0px solid #ccc;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				object-fit: cover;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
				border: 0px solid #ccc;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				object-fit: cover;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
				border: 0px solid #ccc;
				cursor: pointer;
				border-radius: 0px;
				color: #666;
				background: #fff;
				object-fit: cover;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
				border: 0px solid #ccc;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #fff;
				width: 100%;
				font-size: 16px;
				min-width: 400px;
				height: 120px;
			}
	
	.add-update-preview .btn .btn3 {
				border: 0px solid #ccc;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 10px 0 0;
				color: #fff;
				background: #00ad45;
				width: auto;
				font-size: 16px;
				min-width: 110px;
				height: 40px;
			}
	
	.add-update-preview .btn .btn3:hover {
				opacity: 0.8;
			}
	
	.add-update-preview .btn .btn4 {
				border: 0px solid #ccc;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 10px 0 0;
				color: #fff;
				background: #00ad45;
				width: auto;
				font-size: 16px;
				min-width: 110px;
				height: 40px;
			}
	
	.add-update-preview .btn .btn4:hover {
				opacity: 0.8;
			}
</style>
