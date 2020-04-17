<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="全称" prop="schoolName">
        <el-input v-model="queryParams.schoolName" placeholder="请输入学校全称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="简称" prop="shortName">
        <el-input v-model="queryParams.shortName" placeholder="请输入学校简称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="英文全称" prop="englishName">
        <el-input v-model="queryParams.englishName" placeholder="请输入英文全称" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="英文缩写" prop="englishShortName">
        <el-input v-model="queryParams.englishShortName" placeholder="请输入缩写英文名称" clearable size="small"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="首字母" prop="schoolFirst">
        <el-input v-model="queryParams.schoolFirst" placeholder="请输入学校名称首字母" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="拼音字母" prop="schoolFirstPy">
        <el-input v-model="queryParams.schoolFirstPy" placeholder="请输入学校全称拼音首字母" clearable size="small"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="全称拼音" prop="schoolPinyin">
        <el-input v-model="queryParams.schoolPinyin" placeholder="请输入学校全称拼音" clearable size="small" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="学历" prop="schoolDegreeType">
        <el-select v-model="queryParams.schoolDegreeType" placeholder="请选择学历" clearable size="small">
          <el-option v-for="dict in schoolDegreeTypeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:school:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:school:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:school:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['system:school:export']">导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="schoolList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="schoolId" />
      <el-table-column label="编号" align="center" prop="schoolNo" />
      <el-table-column label="名字全称" align="center" prop="schoolName" />
      <el-table-column label="名字简称" align="center" prop="shortName" />
      <el-table-column label="英文全称" align="center" prop="englishName" />
      <el-table-column label="英文缩写" align="center" prop="englishShortName" />
      <el-table-column label="名称首字母" align="center" prop="schoolFirst" />
      <el-table-column label="全称拼音首字母" align="center" prop="schoolFirstPy" />
      <el-table-column label="全称拼音" align="center" prop="schoolPinyin" />
      <el-table-column label="学历" align="center" prop="schoolDegreeType" :formatter="schoolDegreeTypeFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:school:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:school:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改学校信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="编号" prop="schoolNo">
          <el-input v-model="form.schoolNo" placeholder="请输入学校编号" />
        </el-form-item>
        <el-form-item label="名字全称" prop="schoolName">
          <el-input v-model="form.schoolName" placeholder="请输入学校名字全称" />
        </el-form-item>
        <el-form-item label="名字简称" prop="shortName">
          <el-input v-model="form.shortName" placeholder="请输入学校名字简称" />
        </el-form-item>
        <el-form-item label="英文全称" prop="englishName">
          <el-input v-model="form.englishName" placeholder="请输入英文全称" />
        </el-form-item>
        <el-form-item label="英文缩写" prop="englishShortName">
          <el-input v-model="form.englishShortName" placeholder="请输入缩写英文名称" />
        </el-form-item>
        <el-form-item label="名称首字母" prop="schoolFirst">
          <el-input v-model="form.schoolFirst" placeholder="请输入学校名称首字母" />
        </el-form-item>
        <el-form-item label="全称拼音首字母" prop="schoolFirstPy">
          <el-input v-model="form.schoolFirstPy" placeholder="请输入全称拼音首字母" />
        </el-form-item>
        <el-form-item label="全称拼音" prop="schoolPinyin">
          <el-input v-model="form.schoolPinyin" placeholder="请输入学校全称拼音" />
        </el-form-item>
        <el-form-item label="学历">
          <el-select v-model="form.schoolDegreeType" placeholder="请选择学历">
            <el-option v-for="dict in schoolDegreeTypeOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
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
    listSchool,
    getSchool,
    delSchool,
    addSchool,
    updateSchool,
    exportSchool
  } from "@/api/system/school";

  export default {
    name: "School",
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
        // 学校信息表格数据
        schoolList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 学历字典
        schoolDegreeTypeOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          schoolName: undefined,
          shortName: undefined,
          englishName: undefined,
          englishShortName: undefined,
          schoolFirst: undefined,
          schoolFirstPy: undefined,
          schoolPinyin: undefined,
          schoolDegreeType: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          schoolName: [{
            required: true,
            message: "学校名字全称不能为空",
            trigger: "blur"
          }],
          schoolDegreeType: [{
            required: true,
            message: "学历类别不能为空",
            trigger: "blur"
          }]
        }
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_school_type").then(response => {
        this.schoolDegreeTypeOptions = response.data;
      });
    },
    methods: {
      /** 查询学校信息列表 */
      getList() {
        this.loading = true;
        listSchool(this.queryParams).then(response => {
          this.schoolList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 学历字典翻译
      schoolDegreeTypeFormat(row, column) {
        return this.selectDictLabel(this.schoolDegreeTypeOptions, row.schoolDegreeType);
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          schoolId: undefined,
          schoolNo: undefined,
          schoolName: undefined,
          shortName: undefined,
          englishName: undefined,
          englishShortName: undefined,
          schoolFirst: undefined,
          schoolFirstPy: undefined,
          schoolPinyin: undefined,
          schoolDegreeType: undefined
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
        this.ids = selection.map(item => item.schoolId)
        this.single = selection.length != 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加学校信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const schoolId = row.schoolId || this.ids
        getSchool(schoolId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改学校信息";
        });
      },
      /** 提交按钮 */
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.schoolId != undefined) {
              updateSchool(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addSchool(this.form).then(response => {
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
        const schoolIds = row.schoolId || this.ids;
        this.$confirm('是否确认删除学校信息编号为"' + schoolIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSchool(schoolIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有学校信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSchool(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
