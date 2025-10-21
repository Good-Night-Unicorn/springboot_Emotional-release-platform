<template>
	<div>
		<div class="login-container">
			<el-form class="login_form animate__animated animate__fadeInDown">
				<div class="login_form2">
					<div class="title-container">基于JAVA的情绪宣泄平台的设计与实现</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							账号：
						</div>
						<input placeholder="请输入账号：" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							密码：
						</div>
						<div class="password-box">
							<input placeholder="请输入密码：" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item select" v-if="roles.length>1">
						<div class="lable">
							角色：
						</div>
						<el-select v-model="rulesForm.role" placeholder="请选择角色：">
							<el-option v-if="loginType==1||(loginType==2&&item.roleName!='管理员')" v-for="item in roles" :key="item.roleName" :label="item.roleName" :value="item.roleName" />
						</el-select>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
							<el-button type="primary" @click="register('zixunshi')" class="register">
								注册咨询师							</el-button>
						</div>
						<div class="login-btn3">
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
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
				showPassword: false,
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
						if(this.$storage.get('nowLocation')){
							let location = this.$storage.get('nowLocation')
							this.$storage.set('beforeLocation',location)
						}
						if(this.$storage.get('nowTime')){
							let times = this.$storage.get('nowTime')
							this.$storage.set('beforeTime',times)
						}
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'xuesheng') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'zixunshi') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.image)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					} else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20240808/4690bab5e91648408e9ba44aedb3ccb2.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20240808/4690bab5e91648408e9ba44aedb3ccb2.jpg);
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: flex-end;
	align-items: center;
	background-position: center center;
	position: relative;

	.login_form {
		padding: 0;
		margin: 0;
		background: none;
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: flex-end;
		align-items: center;
		position: relative;
		.login_form2 {
			border-radius: 10px;
			padding: 30px 20px 20px;
			box-shadow: -10px 0px 13px -7px #000000, 10px 0px 13px -7px #000000, 5px 5px 15px 5px rgba(0,0,0,0);
			margin: 0 20% 0 0;
			background: #4dc57b;
			width: 550px;
			min-height: 320px;
		}
		.title-container {
			padding: 0 20px;
			margin: 0  auto;
			color: #000;
			font-weight: 600;
			font-size: 26px;
			line-height: 80px;
			border-radius: 8px;
			top: 0;
			left: 0;
			background: #fff;
			width: calc(100% - 0px);
			position: absolute;
			text-align: center;
		}
		.list-item {
			padding: 0;
			margin: 0 0 20px 120px;
			display: flex;
			width: calc(90% - 120px);
			align-items: center;
			position: relative;
			flex-wrap: wrap;
			.lable {
				color: #fff;
				left: -120px;
				width: 120px;
				font-size: 16px;
				line-height: 40px;
				position: absolute !important;
				text-align: right;
			}
			input {
				border: 1px solid rgba(255, 255, 255, .5);
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 36px;
			}
			input:focus {
				border: 1px solid rgba(0, 0, 0, .5);
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 34px;
			}
			.password-box {
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					border: 1px solid rgba(255, 255, 255, .5);
					padding: 0 10px;
					color: rgba(64, 158, 255, 1);
					background: none;
					width: 100%;
					font-size: 16px;
					height: 36px;
				}
				input:focus {
					border: 1px solid rgba(0, 0, 0, .5);
					padding: 0 10px;
					color: #fff;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 36px;
				}
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #fff;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: rgba(255, 255, 255, .6);
				font-size: 16px;
			}
			/deep/ .el-select {
				width: 100%;
			}
			/deep/ .el-select .el-input__inner {
				border: 1px solid rgba(255, 255, 255, .5);
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 36px;
			}
			/deep/ .el-select .is-focus .el-input__inner {
				border: 1px solid rgba(0, 0, 0, .5);
				border-radius: 0;
				padding: 0 10px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 16px;
				height: 36px;
			}
			/deep/ .el-select .el-input__inner::placeholder{
				color: rgba(255, 255, 255, .6);
				font-size: 16px;
			}
		}
		.login-btn {
			margin: 20px auto 0 120px;
			display: flex;
			width: calc(90% - 120px);
			align-items: center;
			flex-wrap: wrap;
			.login-btn1 {
				margin: 0 5px 10px 0;
				width: auto;
			}
			.login-btn2 {
				width: auto;
			}
			.login-btn3 {
				width: auto;
			}
			.loginInBt {
				border: 0px solid rgba(255, 255, 255, .5);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0;
				color: rgba(0, 0, 0, 1);
				background: #fff;
				width: 100%;
				font-size: 16px;
				min-width: 68px;
				height: 40px;
			}
			.loginInBt:hover {
				border: 0px solid rgba(85, 170, 0, 1.0);
				color: #00ad45;
				background: #fff;
				opacity: 0.8;
			}
			.register {
				border: 1px solid rgba(255, 255, 255, 1);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 5px 10px 0;
				color: rgba(255, 255, 255, 1);
				background: none;
				width: auto;
				font-size: 16px;
				height: 34px;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0;
				cursor: pointer;
				border-radius: 0;
				padding: 0;
				margin: 0 5px 10px 0;
				color: #fff;
				background: none;
				width: auto;
				font-size: 16px;
				height: 34px;
			}
			.forget:hover {
				opacity: 1;
			}
		}
	}
}

</style>
