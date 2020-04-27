<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px" v-model="queryParams.createTime" type="date"
          value-format="yyyy-MM-dd" placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否已读" prop="readFlag">
        <el-select v-model="queryParams.readFlag" placeholder="请选择是否已读" clearable size="small">
          <el-option v-for="dict in readFlagOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:letter:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['ci:letter:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ci:letter:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['ci:letter:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="letterList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="接收用户" align="center" prop="receiveUser" />
      <el-table-column label="内容" align="center" prop="content" />
      <el-table-column label="是否已读" align="center" prop="readFlag" :formatter="readFlagFormat" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:letter:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:letter:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改私信管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="接收用户" prop="receiveUser">
          <el-input v-model="form.receiveUser" placeholder="请输入接收用户" />
        </el-form-item>
        <el-form-item label="是否已读">
          <el-select v-model="form.readFlag" placeholder="请选择是否已读">
            <el-option v-for="dict in readFlagOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
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
    listLetter,
    getLetter,
    delLetter,
    addLetter,
    updateLetter,
    exportLetter
  } from "@/api/ci/letter";

  export default {
    name: "Letter",
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
        // 私信管理表格数据
        letterList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 是否已读字典
        readFlagOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          createTime: undefined,
          readFlag: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_yes_no").then(response => {
        this.readFlagOptions = response.data;
      });
    },
    methods: {
      /** 查询私信管理列表 */
      getList() {
        this.loading = true;
        listLetter(this.queryParams).then(response => {
          this.letterList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 是否已读字典翻译
      readFlagFormat(row, column) {
        return this.selectDictLabel(this.readFlagOptions, row.readFlag);
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
          createTime: undefined,
          content: undefined,
          receiveUser: undefined,
          readFlag: undefined
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
        this.title = "添加私信管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getLetter(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改私信管理";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateLetter(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addLetter(this.form).then(response => {
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
        this.$confirm('是否确认删除私信管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delLetter(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有私信管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportLetter(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
