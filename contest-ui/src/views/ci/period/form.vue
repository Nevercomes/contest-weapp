<template>
  <div class="app-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="竞赛模板" prop="cpInfoId">
            <el-select v-model="form.cpInfoId" filterable placeholder="请选择竞赛模板" clearable size="small">
              <el-option v-for="item in infoOptions" :label="item.label" :value="item.value" :key="item.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="名称" prop="name">
            <el-input v-model="form.name" clearable size="small" placeholder="请输入名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报名开始" prop="signBeginTime">
            <el-date-picker clearable size="small" style="width: 200px" v-model="form.signBeginTime" type="datetime"
              value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择报名开始">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="报名结束" prop="signEndTime">
            <el-date-picker clearable size="small" style="width: 200px" v-model="form.signEndTime" type="datetime"
              value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择报名结束">
            </el-date-picker>
          </el-form-item>
        </el-col>
        
        <el-col :span="12">
          <el-form-item label="封面图片" prop="picUrl">
            <el-upload class="image-uploader" :action="upload.url" :headers="upload.headers" :limit="1" :disabled="upload.isUploading"
              :on-progress="handleFileUploadProgress" :show-file-list="false" :accept="'image/*'" :on-success="handleImageSuccess"
              :before-upload="beforeImageUpload">
              <el-image fit="contain" v-if="form.picUrl" :src="form.picUrl" class="image-show" />
              <i v-else class="el-icon-plus image-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="面向学校" prop="schoolId">
            <el-select v-model="form.schoolId" filterable placeholder="请选择面向学校" clearable size="small">
              <el-option v-for="item in schoolOptions" :label="item.label" :value="item.value" :key="item.value" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="官网链接" prop="officialLink">
            <el-input v-model="form.details.officialLink" clearable size="small" placeholder="请输入官网链接"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="主办方" prop="holder">
            <el-input v-model="form.holder" type="textarea" maxlength="200" show-word-limit placeholder="请输入主办方,逗号分隔" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="简介" prop="intro">
            <el-input v-model="form.details.intro" :autosize="{minRows: 2, maxRows: 4}" type="textarea" show-word-limit
              maxlength="220" placeholder="请输入简介"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="内容" prop="content">
            <el-input v-model="form.details.content" :autosize="{minRows: 4, maxRows: 10}" type="textarea" placeholder="请输入内容"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="div-form-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </div>
</template>

<script>
  import {
    getPeriod,
    addPeriod,
    updatePeriod,
  } from "@/api/ci/period";
  import {
    getInfoOptions
  } from "@/api/ci/info";
  import {
    getSchoolOptions
  } from '@/api/system/school.js'
  import {
    getToken
  } from "@/utils/auth";

  export default {
    name: 'PeriodForm',
    data() {
      return {
        // 竞赛模板选项
        infoOptions: [],
        // 学校选项
        schoolOptions: [],
        // 表单参数
        form: {},
        // 上传参数
        upload: {
          url: process.env.VUE_APP_BASE_API + '/system/ossRecord/upload/cover',
          isUploading: false,
          headers: {
            Authorization: "Bearer " + getToken()
          },
        },
        // 表单校验
        rules: {
          cpInfoId: [{
            required: true,
            message: "竞赛模板不能为空",
            trigger: ["blur", "change"]
          }],
          name: [{
            required: true,
            message: "名称不能为空",
            trigger: "blur"
          }],
          schoolId: [{
            required: true,
            message: "面向学校不能为空",
            trigger: "blur"
          }],
          holder: [{
            required: true,
            message: "主办方不能为空",
            trigger: "blur"
          }],
          signBeginTime: [{
            required: true,
            message: '报名开始时间不能为空',
            trigger: "blur"
          }],
          signEndTime: [{
            required: true,
            message: '报名结束时间不能为空',
            trigger: "blur"
          }]
        }
      }
    },
    created() {
      const periodId = this.$route.params && this.$route.params.periodId
      this.getData(periodId)
      getInfoOptions().then(res => {
        this.infoOptions = res.data
      })
      getSchoolOptions().then(res => {
        this.schoolOptions = res.data
      })
    },
    methods: {
      reset() {
        this.form = {
          id: undefined,
          name: undefined,
          cpInfoId: undefined,
          signBeginTime: undefined,
          signEndTime: undefined,
          schoolId: undefined,
          holder: undefined,
          details: {
            cpId: undefined,
            intro: undefined,
            content: undefined,
            officialLink: undefined
          }
        }
      },
      getData(id) {
        this.reset()
        if (id) {
          getPeriod(id).then(res => {
            this.form = res.data
            if (!this.form.details)
              this.form.details = {}
          })
        }
      },
      submitForm: function() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != undefined) {
              updatePeriod(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.close();
                } else {
                  this.msgError(response.msg);
                }
              });
            } else {
              addPeriod(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("新增成功");
                  this.close();
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
      /** 关闭按钮 */
      close() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.back()
      }
    }
  }
</script>

<style scoped="scoped" lang="scss">
  .app-container {
    width: 50%;
  }

  .image-uploader {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    width: 178px;
    height: 178px;
  }

  .image-uploader:hover {
    border-color: #409EFF;
  }

  .image-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .image-show {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
