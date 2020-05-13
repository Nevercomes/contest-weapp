<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="显示名字" prop="showName">
        <el-select v-model="queryParams.showName" placeholder="请选择显示名字" clearable size="small">
          <el-option
            v-for="dict in showNameOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="显示简介" prop="showIntro">
        <el-select v-model="queryParams.showIntro" placeholder="请选择显示简介" clearable size="small">
          <el-option
            v-for="dict in showIntroOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="显示经历" prop="showExperience">
        <el-select v-model="queryParams.showExperience" placeholder="请选择显示经历" clearable size="small">
          <el-option
            v-for="dict in showExperienceOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="显示积分" prop="showPoints">
        <el-select v-model="queryParams.showPoints" placeholder="请选择显示积分" clearable size="small">
          <el-option
            v-for="dict in showPointsOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="显示动态" prop="showNews">
        <el-select v-model="queryParams.showNews" placeholder="请选择显示动态" clearable size="small">
          <el-option
            v-for="dict in showNewsOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ci:showSetting:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ci:showSetting:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ci:showSetting:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ci:showSetting:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="showSettingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属用户" align="center" prop="ownUserId" />
      <el-table-column label="显示名字" align="center" prop="showName" :formatter="showNameFormat" />
      <el-table-column label="显示简介" align="center" prop="showIntro" :formatter="showIntroFormat" />
      <el-table-column label="显示经历" align="center" prop="showExperience" :formatter="showExperienceFormat" />
      <el-table-column label="显示积分" align="center" prop="showPoints" :formatter="showPointsFormat" />
      <el-table-column label="显示动态" align="center" prop="showNews" :formatter="showNewsFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ci:showSetting:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ci:showSetting:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户显示设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="显示名字">
          <el-select v-model="form.showName" placeholder="请选择显示名字">
            <el-option
              v-for="dict in showNameOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="显示简介">
          <el-select v-model="form.showIntro" placeholder="请选择显示简介">
            <el-option
              v-for="dict in showIntroOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="显示经历">
          <el-select v-model="form.showExperience" placeholder="请选择显示经历">
            <el-option
              v-for="dict in showExperienceOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="显示积分">
          <el-select v-model="form.showPoints" placeholder="请选择显示积分">
            <el-option
              v-for="dict in showPointsOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="显示动态">
          <el-select v-model="form.showNews" placeholder="请选择显示动态">
            <el-option
              v-for="dict in showNewsOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
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
import { listShowSetting, getShowSetting, delShowSetting, addShowSetting, updateShowSetting, exportShowSetting } from "@/api/ci/showSetting";

export default {
  name: "ShowSetting",
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
      // 用户显示设置表格数据
      showSettingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 显示名字字典
      showNameOptions: [],
      // 显示简介字典
      showIntroOptions: [],
      // 显示经历字典
      showExperienceOptions: [],
      // 显示积分字典
      showPointsOptions: [],
      // 显示动态字典
      showNewsOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        showName: undefined,
        showIntro: undefined,
        showExperience: undefined,
        showPoints: undefined,
        showNews: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        showName: [
          { required: true, message: "显示名字不能为空", trigger: "blur" }
        ],
        showIntro: [
          { required: true, message: "显示简介不能为空", trigger: "blur" }
        ],
        showExperience: [
          { required: true, message: "显示经历不能为空", trigger: "blur" }
        ],
        showPoints: [
          { required: true, message: "显示积分不能为空", trigger: "blur" }
        ],
        showNews: [
          { required: true, message: "显示动态不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.showNameOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.showIntroOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.showExperienceOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.showPointsOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.showNewsOptions = response.data;
    });
  },
  methods: {
    /** 查询用户显示设置列表 */
    getList() {
      this.loading = true;
      listShowSetting(this.queryParams).then(response => {
        this.showSettingList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 显示名字字典翻译
    showNameFormat(row, column) {
      return this.selectDictLabel(this.showNameOptions, row.showName);
    },
    // 显示简介字典翻译
    showIntroFormat(row, column) {
      return this.selectDictLabel(this.showIntroOptions, row.showIntro);
    },
    // 显示经历字典翻译
    showExperienceFormat(row, column) {
      return this.selectDictLabel(this.showExperienceOptions, row.showExperience);
    },
    // 显示积分字典翻译
    showPointsFormat(row, column) {
      return this.selectDictLabel(this.showPointsOptions, row.showPoints);
    },
    // 显示动态字典翻译
    showNewsFormat(row, column) {
      return this.selectDictLabel(this.showNewsOptions, row.showNews);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        ownUserId: undefined,
        showName: undefined,
        showIntro: undefined,
        showExperience: undefined,
        showPoints: undefined,
        showNews: undefined
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
      this.ids = selection.map(item => item.ownUserId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户显示设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ownUserId = row.ownUserId || this.ids
      getShowSetting(ownUserId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户显示设置";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ownUserId != undefined) {
            updateShowSetting(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addShowSetting(this.form).then(response => {
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
      const ownUserIds = row.ownUserId || this.ids;
      this.$confirm('是否确认删除用户显示设置编号为"' + ownUserIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delShowSetting(ownUserIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户显示设置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportShowSetting(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>