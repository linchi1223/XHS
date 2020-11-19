<template>
  <div class="content">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">图片</li>
      </ol>
    </nav>
    <el-upload
      :action="urladdress+'/common/upload1'"
      list-type="picture-card"
      :on-success="handleUpSuccess"
      :on-error="handleUpError"
      :on-change="handleUpUpload"
      :on-preview="handleUpPreview"
      :on-remove="handleUpRemove"
      :file-list="fileList" >

      <i slot="default" class="el-icon-plus"></i>
      <div slot="file" slot-scope="{ file }">
        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
        <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
          >
            <i class="el-icon-zoom-in"></i>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleDownload(file)"
          >
            <i class="el-icon-download"></i>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
          >
            <i class="el-icon-delete"></i>
          </span>
        </span>
      </div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="" />
    </el-dialog>
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">文章</li>
      </ol>
    </nav>
    <el-input
      type="textarea"
      :autosize="{ minRows: 15, maxRows: 20 }"
      placeholder="请输入内容"
      v-model="textarea1"
    >
    </el-input>
    <el-button class="buttongo" @click="fabuwenzhang()">发布</el-button>
  </div>
</template>
<style scoped>
.content {
  width: 1000px;
  margin: auto;
}
nav {
  margin-top: 10px;
}
.buttongo {
  float: right;
  margin: 10px;
}
</style>

<script>
export default {
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      disabled: false,
      textarea1: "",
      // urladdress: "http://192.168.46.124:8080",
      urladdress: "http://192.168.31.121:8080",
      fileList: [],
      reqUploadImgApi: "http://192.168.31.121:8080" + "/common/upload1",
      form: {
        photo: "",
      },
    };
  },

  methods: {
    headers() {
      return {
        Authorization: this.userid, // 直接从本地获取token就行
      };
    },
 
    handleUpSuccess(response, file, fileList) {
      console.log(response);
      if (response.code == 200) {
        this.ruleForm.cardUpImg = response.data[0];
        this.imagesUp = response.data;
        this.hideUp = fileList.length == 1;
        this.$message.success(response.msg);
      }
    },
    handleUpError(err, file, fileList) {},
    // 删除图片
    handleUpRemove(file, fileList) {
      this.ruleForm.cardUpImg = "";
      this.imagesUp = [];
      this.hideUp = false;
    },
    // 预览图片
    handleUpPreview(file) {
      this.$refs.viewer.handleDialog(true);
    },
    // 上传图片
    handleUpUpload(file, fileList) {
      console.log(file);
      this.hideUp = true;
    },
  },
};
</script>
