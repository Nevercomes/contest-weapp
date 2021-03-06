<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="队伍" prop="teamId">
        <el-select v-model="queryParams.teamId" placeholder="请选择队伍" clearable size="small">
          <el-option v-for="item in teamOptions" :value="item.value" :label="item.label" :key="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="身份" prop="identity">
        <el-select v-model="queryParams.identity" placeholder="请选择身份" clearable size="small">
          <el-option v-for="dict in identityOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="分工" prop="work">
        <el-select v-model="queryParams.work" placeholder="请选择分工" clearable size="small">
          <el-option v-for="dict in workOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['ci:member:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['ci:member:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['ci:member:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['ci:member:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="memberList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="队员" align="center" prop="assUser.nickName" />
      <el-table-column label="队伍" align="center" prop="teamName" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="身份" align="center" prop="identity" :formatter="identityFormat" />
      <el-table-column label="分工" align="center" prop="work" :formatter="workFormat" />
      <el-table-column label="能力" align="center" prop="capacity" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['ci:member:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['ci:member:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改队伍成员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="队员" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入队员" />
        </el-form-item>
        <el-form-item label="队伍" prop="teamId">
          <el-select v-model="form.teamId" placeholder="请选择队伍" clearable size="small">
            <el-option v-for="item in teamOptions" :value="item.value" :label="item.label" :key="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="身份" prop="identity">
          <el-select v-model="form.identity" placeholder="请选择身份">
            <el-option v-for="dict in identityOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分工" prop="work">
          <el-select v-model="form.work" placeholder="请选择分工">
            <el-option v-for="dict in workOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="能力" prop="capacity">
          <el-input v-model="form.capacity" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
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
    listMember,
    getMember,
    delMember,
    addMember,
    updateMember,
    exportMember
  } from "@/api/ci/member";
  import {
    getTeamInfoOptions
  } from '@/api/ci/teamInfo.js'

  export default {
    name: "Member",
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
        // 队伍成员表格数据
        memberList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        // 身份字典
        identityOptions: [],
        // 分工字典
        workOptions: [],
        // 队伍选项
        teamOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          teamId: undefined,
          status: undefined,
          identity: undefined,
          work: undefined,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          userId: [{
            required: true,
            message: "队员不能为空",
            trigger: "blur"
          }],
          teamId: [{
            required: true,
            message: "队伍不能为空",
            trigger: "blur"
          }],
          identity: [{
            required: true,
            message: "身份不能为空",
            trigger: "blur"
          }],
          work: [{
            required: true,
            message: "分工不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("team_member_status").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("team_member_identity").then(response => {
        this.identityOptions = response.data;
      });
      this.getDicts("team_work_type").then(response => {
        this.workOptions = response.data;
      });
      getTeamInfoOptions().then(res => {
        this.teamOptions = res.data
      })
    },
    methods: {
      /** 查询队伍成员列表 */
      getList() {
        this.loading = true;
        listMember(this.queryParams).then(response => {
          this.memberList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status);
      },
      // 身份字典翻译
      identityFormat(row, column) {
        return this.selectDictLabel(this.identityOptions, row.identity);
      },
      // 分工字典翻译
      workFormat(row, column) {
        return this.selectDictLabel(this.workOptions, row.work);
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
          userId: undefined,
          teamId: undefined,
          status: undefined,
          identity: undefined,
          work: undefined,
          capacity: undefined
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
        this.title = "添加队伍成员";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getMember(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改队伍成员";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updateMember(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addMember(this.form).then(response => {
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
        this.$confirm('是否确认删除队伍成员编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMember(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有队伍成员数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMember(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
