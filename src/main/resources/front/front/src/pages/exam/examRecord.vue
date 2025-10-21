<template>
	<div :style='{"padding":"0 0 20px","margin":"0px auto","color":"#666","background":"#fff","width":"1200px","fontSize":"16px","position":"relative"}'>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="section-title" :style='{"padding":"0","margin":"20px 0","color":"#333","textAlign":"center","background":"url(http://codegen.caihongy.cn/20240921/d1174ef38e674130b0bd28e07897ab45.png) no-repeat center bottom","width":"110%","lineHeight":"80px","fontSize":"26px","order":"0"}'>{{title}}</div>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="情绪测评名称"
				prop="papername">
			</el-table-column>
			<el-table-column
				label="试题"
				prop="questionname">
				<template slot-scope="scope">
					<div class="ql-snow ql-editor" v-html="scope.row.questionname"></div>
				</template>
			</el-table-column>
			<el-table-column
				prop="type"
				label="试题类型"
			>
				<template slot-scope="scope">
					<el-tag type="success" v-if="scope.row.type==0">客观题</el-tag>
					<el-tag type="danger" v-if="scope.row.type==4">主观题</el-tag>
				</template>
			</el-table-column>
			<el-table-column
				label="考生答案"
				prop="myanswer">
			</el-table-column>
			<el-table-column
				label="考试得分">
				<template slot-scope="scope">
					{{ scope.row.myscore }}分
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			background
			id="pagination" class="pagination"
			:pager-count="7"
			:page-size="pageSize"
			:page-sizes="pageSizes"
			prev-text="上一页"
			next-text="下一页"
			:hide-on-single-page="false"
			:layout='["total","prev","pager","next","sizes","jumper"].join()'
			:total="total"
			:style='{"padding":"0 calc((100% - 1200px)/2)","margin":"20px auto","whiteSpace":"nowrap","overflow":"hidden","color":"#333","textAlign":"center","width":"100%","clear":"both","fontSize":"16px","fontWeight":"500","order":"50"}'
			@current-change="curChange"
			@prev-click="prevClick"
			@size-change="sizeChange"
			@next-click="nextClick"
		></el-pagination>
	
	</div>
</template>

<script>
	export default {
		data() {
			return {
				layouts: '',
				title: '考试记录',
				tableData: [],
				total: 1,
				pageSize: 10,
				pageSizes: [],
				totalPage: 1
			}
		},
		created() {
			this.getExamRecord(1);
		},
		methods: {
			backClick() {
				this.$router.push('/index/center')
			},
			getExamRecord(page) {
				let otherParams = {};
				if (this.$route.params.type == 0) {
					this.title = '错题本';
					otherParams.myscore = 0;
					otherParams.ismark = 1
				}
				this.$http.get('examrecord/list', {params: Object.assign({page, limit: this.pageSize, paperid: this.$route.query.paperid, userid: Number(localStorage.getItem('frontUserid'))}, otherParams)}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list){
							res.data.data.list[x].questionname = res.data.data.list[x].questionname.replace(/img src/gi,"img style=\"width:100%;\" src");
						}
						this.tableData = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			curChange(page) {
				this.getExamRecord(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getExamRecord(1);
			},
			prevClick(page) {
				this.getExamRecord(page);
			},
			nextClick(page) {
				this.getExamRecord(page);
			},
			handleView(index, row) {
				this.$router.push({path: '/index/examRecord', query: {id: row.id}})
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.section {
		width: 900px;
		margin: 0 auto;
	}
</style>
