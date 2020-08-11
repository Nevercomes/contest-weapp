<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="竞赛" prop="cpId">
        <el-select v-model="queryParams.cpId" filterable placeholder="请选择竞赛" clearable size="small">
          <el-option v-for="item in cpOptions" :label="item.label" :value="item.value" :key="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="时间" prop="time">
        <el-date-picker clearable size="small" style="width: 200px" v-model="queryParams.time" type="date" value-format="yyyy-MM-dd"
          placeholder="选择时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="事件" prop="content">
        <el-input v-model="queryParams.content" placeholder="请输入事件" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="外部链接" prop="outerLink">
        <el-input v-model="queryParams.outerLink" placeholder="请输入外部链接" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:timeline:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['ci:timeline:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ci:timeline:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['ci:timeline:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="timelineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="竞赛" align="center" prop="cpName" show-overflow-tooltip />
      <el-table-column label="时间" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="事件" align="center" prop="content"  />
      <el-table-column label="外部链接" align="center" prop="outerLink" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:timeline:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:timeline:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改竞赛资讯对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="竞赛" prop="cpId">
          <el-select v-model="form.cpId" filterable placeholder="请选择竞赛" clearable size="small">
            <el-option v-for="item in cpOptions" :label="item.label" :value="item.value" :key="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="时间" prop="time">
          <el-date-picker clearable size="small" style="width: 200px" v-model="form.time" type="date" value-format="yyyy-MM-dd"
            placeholder="选择时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="事件" prop="content">
          <el-input v-model="form.content" placeholder="请输入事件" />
        </el-form-item>
        <el-form-item label="外部链接" prop="outerLink">
          <el-input v-model="form.outerLink" placeholder="请输入外部链接" />
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
    listTimeline,
    getTimeline,
    delTimeline,
    addTimeline,
    updateTimeline,
    exportTimeline
  } from "@/api/ci/timeline";
  import {
    getPeriodOptions
  } from '@/api/ci/period.js'

  export default {
    name: "Timeline",
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
        // 竞赛资讯表格数据
        timelineList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 比赛选项
        cpOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          cpId: undefined,
          time: undefined,
          content: undefined,
          outerLink: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          cpId: [{
            required: true,
            message: "竞赛不能为空",
            trigger: "blur"
          }],
          time: [{
            required: true,
            message: "时间不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    created() {
      this.getList();
      getPeriodOptions().then(res => {
        this.cpOptions = res.data
      })
    },
    methods: {
      /** 查询竞赛资讯列表 */
      getList() {
        this.loading = true;
        listTimeline(this.queryParams).then(response => {
          this.timelineList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
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
          cpId: undefined,
          time: undefined,
          content: undefined,
          outerLink: undefined
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
        this.title = "添加竞赛资讯";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getTimeline(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改竞赛资讯";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateTimeline(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addTimeline(this.form).then(response => {
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
        this.$confirm('是否确认删除竞赛资讯编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTimeline(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有竞赛资讯数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTimeline(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
