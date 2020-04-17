<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="类别名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入类别名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button class="filter-item" type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:classify:add']">新增</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="classifyList" row-key="id" default-expand-all :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column label="类别名称" align="center" prop="name" />
      <!-- <el-table-column label="父级" align="center" prop="parentId" /> -->
      <el-table-column label="显示顺序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-plus" @click="handleAdd(scope.row)" v-hasPermi="['ci:classify:add']">新增</el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:classify:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:classify:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改竞赛分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父级" prop="parentId">
          <treeselect v-model="form.parentId" :options="classifyOptions" :normalizer="normalizer" placeholder="请选择父级" />
        </el-form-item>
        <el-form-item label="类别名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入类别名称" />
        </el-form-item>
        <el-form-item label="显示顺序" prop="sort">
          <el-input-number v-model="form.sort" controls-position="right" :min="0" />
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
    listClassify,
    getClassify,
    delClassify,
    addClassify,
    updateClassify,
    exportClassify
  } from "@/api/ci/classify";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "Classify",
    components: {
      Treeselect
    },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 竞赛分类表格数据
        classifyList: [],
        // 竞赛分类树选项
        classifyOptions: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          name: undefined,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          parentId: [{
            required: true,
            message: '父级不能为空',
            trigger: 'blur'
          }],
          name: [{
            required: true,
            message: '类别名称不能为空',
            trigger: 'blur'
          }],
          sort: [{
            required: true,
            message: '排序不能为空',
            trigger: 'blur'
          }]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询竞赛分类列表 */
      getList() {
        this.loading = true;
        listClassify(this.queryParams).then(response => {
          this.classifyList = this.handleTree(response.data, "id", "parentId");
          this.loading = false;
        });
      },
      /** 转换竞赛分类数据结构 */
      normalizer(node) {
        if (node.children && !node.children.length) {
          delete node.children;
        }
        return {
          id: node.id,
          label: node.name,
          children: node.children
        };
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        listClassify().then(response => {
          this.classifyOptions = [];
          const data = {
            id: 0,
            name: '顶级节点',
            children: []
          };
          data.children = this.handleTree(response.data, "id", "parentId");
          this.classifyOptions.push(data);
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
          parentId: undefined,
          name: undefined,
          sort: undefined,
          createBy: undefined,
          createTime: undefined,
          updateBy: undefined,
          updateTime: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 新增按钮操作 */
      handleAdd(row) {
        this.reset();
        this.getTreeselect();
        if (row != null) {
          this.form.parentId = row.id;
        }
        this.open = true;
        this.title = "添加竞赛分类";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        this.getTreeselect();
        if (row != undefined) {
          this.form.parentId = row.id;
        }
        getClassify(row.id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改竞赛分类";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateClassify(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addClassify(this.form).then(response => {
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
        this.$confirm('是否确认删除竞赛分类编号为"' + row.id + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delClassify(row.id);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      }
    }
  };
</script>
