<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="竞赛名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入竞赛名称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="竞赛级别" prop="level">
        <el-select v-model="queryParams.level" placeholder="请选择竞赛级别" clearable size="small">
          <el-option v-for="dict in levelOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="竞赛分类" prop="classify">
          <treeselect class="info__treeselect-query" v-model="queryParams.classify" :options="classifyOptions"
            :normalizer="normalizer" placeholder="请选择竞赛分类" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:info:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['ci:info:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ci:info:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['ci:info:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Id" align="center" prop="id" />
      <el-table-column label="竞赛名称" align="center" prop="name" />
      <el-table-column label="竞赛级别" align="center" prop="level" :formatter="levelFormat" />
      <el-table-column label="浏览数" align="center" prop="viewNumber" />
      <el-table-column label="关注数" align="center" prop="concernNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:info:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:info:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改竞赛信息模板对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="竞赛名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入竞赛名称" />
        </el-form-item>
        <el-form-item label="竞赛级别" prop="level">
          <el-select v-model="form.level" placeholder="请选择竞赛级别">
            <el-option v-for="dict in levelOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="竞赛分类">
          <el-tree
            :data="classifyTreeselect"
            show-checkbox
            ref="classify"
            node-key="id"
            empty-text="加载中，请稍后"
            :props="defaultProps"
          ></el-tree>
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
    listInfo,
    getInfo,
    delInfo,
    addInfo,
    updateInfo,
    exportInfo
  } from "@/api/ci/info";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";
  import {
    listClassify,
    treeselect,
    infoClassifyTreeselect
  } from "@/api/ci/classify";

  export default {
    name: "Info",
    components: {
      Treeselect
    },
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
        // 竞赛信息模板表格数据
        infoList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 竞赛级别字典
        levelOptions: [],
        // 分类选项
        classifyOptions: [],
        // 分类树选项
        classifyTreeselect: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: undefined,
          level: undefined,
          classify: undefined
        },
        // 表单参数
        form: {},
        defaultProps: {
          children: "children",
          label: "label"
        },
        // 表单校验
        rules: {
          name: [{
            required: true,
            message: "竞赛名称不能为空",
            trigger: "blur"
          }],
          level: [{
            required: true,
            message: "竞赛级别不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("ci_competition_level").then(response => {
        this.levelOptions = response.data;
      });
      this.getTreeselect()
    },
    methods: {
      /** 查询竞赛信息模板列表 */
      getList() {
        this.loading = true;
        listInfo(this.queryParams).then(response => {
          this.infoList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 竞赛级别字典翻译
      levelFormat(row, column) {
        return this.selectDictLabel(this.levelOptions, row.level);
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
      // 所有菜单节点数据
      getClassifyAllCheckedKeys() {
        // 目前被选中的菜单节点
        let checkedKeys = this.$refs.classify.getHalfCheckedKeys();
        // 半选中的菜单节点
        let halfCheckedKeys = this.$refs.classify.getCheckedKeys();
        checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
        return checkedKeys;
      },
      /** 根据竞赛查询分类树结构 */
      getInfoClassifyTreeselect(infoId) {
        infoClassifyTreeselect(infoId).then(response => {
          this.classifyTreeselect = response.classifies;
          this.$refs.classify.setCheckedKeys(response.checkedKeys);
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        if (this.$refs.classify != undefined) {
          this.$refs.classify.setCheckedKeys([]);
        }
        this.form = {
          id: undefined,
          name: undefined,
          level: undefined,
          classifyIds: []
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
        treeselect().then(res => {
          this.classifyTreeselect = res.data
        })
        this.open = true;
        this.title = "添加竞赛信息模板";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        this.$nextTick(() => {
          this.getInfoClassifyTreeselect(id);
        });
        getInfo(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改竞赛信息模板";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              this.form.classifyIds = this.getClassifyAllCheckedKeys();
              updateInfo(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              this.form.classifyIds = this.getClassifyAllCheckedKeys();
              addInfo(this.form).then(response => {
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
        this.$confirm('是否确认删除竞赛信息模板编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有竞赛信息模板数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
<style scoped="scoped" lang="scss">
  .info__treeselect-query {
    width: 240px;
  }
</style>
