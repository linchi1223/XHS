<template>
  <div class="content">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">图片</li>
      </ol>
    </nav>
    <el-upload action="#" list-type="picture-card" :auto-upload="false">
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
      <el-button class="buttongo">发布</el-button>
    
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
.buttongo{
  float:right;
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
      textarea2: "",
    };
  },
  methods: {
    handleRemove(file) {
      console.log(file);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleDownload(file) {
      console.log(file);
    },
  },
};
</script>
