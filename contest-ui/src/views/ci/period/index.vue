<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="竞赛模板" prop="cpInfoId">
        <el-select v-model="queryParams.cpInfoId" filterable remote :remote-method="queryInfoOptions" placeholder="请选择竞赛模板"
          clearable size="small">
          <el-option v-for="item in qInfoOptions" :label="item.label" :value="item.value" :key="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="面向学校" prop="schooId">
        <el-select v-model="queryParams.schooId" filterable remote :remote-method="querySchoolOptions" placeholder="请选择面向学校"
          clearable size="small">
          <el-option v-for="item in qSchoolOptions" :label="item.label" :value="item.value" :key="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:period:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['ci:period:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ci:period:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['ci:period:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="periodList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="报名开始" align="center" prop="signupTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signupTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报名结束" align="center" prop="competitionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图片链接" align="center" prop="picUrl" show-overflow-tooltip />
      <el-table-column label="面向学校" align="center" prop="schooId" />
      <el-table-column label="主办方" align="center" prop="holder" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:period:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:period:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改竞赛列表信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="竞赛模板" prop="cpInfoId">
          <el-select v-model="form.cpInfoId" filterable remote :remote-method="formInfoOptions" placeholder="请选择竞赛模板"
            clearable size="small">
            <el-option v-for="item in fInfoOptions" :label="item.label" :value="item.value" :key="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="报名开始" prop="signupTime">
          <el-date-picker clearable size="small" style="width: 200px" v-model="form.signBeginTime" type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择报名开始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名结束" prop="competitionTime">
          <el-date-picker clearable size="small" style="width: 200px" v-model="form.signEndTime" type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择报名结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="面向学校" prop="schooId">
          <el-select v-model="form.schooId" filterable remote :remote-method="formSchoolOptions" placeholder="请选择面向学校"
            clearable size="small">
            <el-option v-for="item in fSchoolOptions" :label="item.label" :value="item.value" :key="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="主办方" prop="holder">
          <el-input v-model="form.holder" type="textarea" maxlength="200" show-word-limit placeholder="请输入主办方,逗号分隔" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listPeriod,
    getPeriod,
    delPeriod,
    addPeriod,
    updatePeriod,
    exportPeriod
  } from "@/api/ci/period";
  import {
    getInfoOptions
  } from "@/api/ci/info";
  import {
    getSchoolOptions
  } from '@/api/system/school.js'

  export default {
    name: "Period",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 总条数
        total: 0,
        // 竞赛列表信息表格数据
        periodList: [],
        // 竞赛模板选项
        qInfoOptions: [],
        fInfoOptions: [],
        // 学校选项
        qSchoolOptions: [],
        fSchoolOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          cpInfoId: undefined,
          name: undefined,
          schooId: undefined,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          cpInfoId: [{
            required: true,
            message: "竞赛模板不能为空",
            trigger: "blur"
          }],
          name: [{
            required: true,
            message: "名称不能为空",
            trigger: "blur"
          }],
          schooId: [{
            required: true,
            message: "面向学校不能为空",
            trigger: "blur"
          }],
          holder: [{
            required: true,
            message: "主办方不能为空",
            trigger: "blur"
          }]
        }
      };
    },
    created() {
      this.getList();
      this.queryInfoOptions()
      this.querySchoolOptions()
    },
    methods: {
      /** 查询竞赛列表信息列表 */
      getList() {
        this.loading = true;
        listPeriod(this.queryParams).then(response => {
          this.periodList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 查询竞赛模板选项
      queryInfoOptions(query) {
        getInfoOptions(query).then(res => {
          this.qInfoOptions = res.data
        })
      },
      // 查询学校选项
      querySchoolOptions(query) {
        getSchoolOptions(query).then(res => {
          this.qSchoolOptions = res.data
        })
      },
      formInfoOptions(query) {
        getInfoOptions(query).then(res => {
          this.fInfoOptions = res.data
        })
      },
      // 查询学校选项
      formSchoolOptions(query) {
        getSchoolOptions(query).then(res => {
          this.fSchoolOptions = res.data
        })
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          signEndTime: undefined,
          schooId: undefined,
          holder: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加竞赛列表信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getPeriod(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改竞赛列表信息";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updatePeriod(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addPeriod(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除竞赛列表信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delPeriod(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有竞赛列表信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportPeriod(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      },
      handlePictureSuccess(res, file) {
        this.form.picUrl = URL.createObjectURL(file.raw);
      },
      beforePictureUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        if (!isJPG)
          isJPG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传图片只能是 JPG/PNG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  };
</script>
<style scoped="scoped" lang="scss">
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
