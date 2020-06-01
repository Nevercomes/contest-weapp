<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="推荐类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择推荐类型" clearable size="small">
          <el-option v-for="dict in typeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:swiper:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['ci:swiper:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ci:swiper:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['ci:swiper:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="swiperList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="图片Url" align="center" prop="picUrl" show-overflow-tooltip />
      <el-table-column label="跳转地址" align="center" prop="link" show-overflow-tooltip />
      <el-table-column label="推荐类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="排序" align="center" prop="orderNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:swiper:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:swiper:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改首页推荐对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图片" prop="picUrl">
          <el-upload class="image-uploader" :action="upload.url" :headers="upload.headers" :limit="1" :disabled="upload.isUploading"
            :on-progress="handleFileUploadProgress" :show-file-list="false" :accept="'image/*'" :on-success="handleImageSuccess"
            :before-upload="beforeImageUpload">
            <el-image fit="contain" v-if="form.picUrl" :src="form.picUrl" class="image-show" />
            <i v-else class="el-icon-plus image-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="推荐类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择推荐类型">
            <el-option v-for="dict in typeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="跳转地址" prop="link">
          <el-input v-model="form.link" placeholder="请输入跳转地址" />
        </el-form-item>
        <el-form-item label="显示排序" prop="orderNum">
          <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
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
    listSwiper,
    getSwiper,
    delSwiper,
    addSwiper,
    updateSwiper,
    exportSwiper
  } from "@/api/ci/swiper";
  import {
    getToken
  } from "@/utils/auth";

  export default {
    name: "Swiper",
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
        // 首页推荐表格数据
        swiperList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 推荐类型字典
        typeOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          type: undefined,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          type: [{
            required: true,
            message: "推荐类型不能为空",
            trigger: "blur"
          }],
        },
        upload: {
          url: process.env.VUE_APP_BASE_API + '/system/ossRecord/upload/recoSwiper',
          isUploading: false,
          headers: {
            Authorization: "Bearer " + getToken()
          },
        },
      };
    },
    created() {
      this.getList();
      this.getDicts("ci_recommend_type").then(response => {
        this.typeOptions = response.data;
      });
    },
    methods: {
      /** 查询首页推荐列表 */
      getList() {
        this.loading = true;
        listSwiper(this.queryParams).then(response => {
          this.swiperList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 推荐类型字典翻译
      typeFormat(row, column) {
        return this.selectDictLabel(this.typeOptions, row.type);
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
          createBy: undefined,
          updateBy: undefined,
          createTime: undefined,
          updateTime: undefined,
          picUrl: undefined,
          link: undefined,
          type: undefined,
          orderNum: undefined
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
        this.title = "添加首页推荐";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getSwiper(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改首页推荐";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateSwiper(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addSwiper(this.form).then(response => {
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
      handleImageSuccess(res, file) {
        this.upload.isUploading = false
        this.form.picUrl = res.data
      },
      handleFileUploadProgress(event, file, fileList) {
        this.upload.isUploading = true;
      },
      beforeImageUpload(file) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        return isLt2M;
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$confirm('是否确认删除首页推荐编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSwiper(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有首页推荐数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSwiper(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>

<style scoped="scoped" lang="scss">
  
</style>
