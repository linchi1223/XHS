<template>
  <div class="content">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">图片</li>
      </ol>
    </nav>
    <el-upload
      :action="urladdress + '/common/upload'"
      list-type="picture-card"
      :on-success="handleUpSuccess"
      :on-error="handleUpError"
      :on-change="handleUpUpload"
      :on-preview="handleUpPreview"
      :on-remove="handleUpRemove"
      :file-list="fileList"
    >
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
        <li class="breadcrumb-item active" aria-current="page">文章标题</li>
      </ol>
    </nav>
    <el-input placeholder="请输入标题" v-model="input1" clearable> </el-input>
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item active" aria-current="page">文章内容</li>
      </ol>
    </nav>

    <el-input
      type="textarea"
      :autosize="{ minRows: 15, maxRows: 20 }"
      placeholder="请输入内容"
      v-model="textarea1"
    >
    </el-input>

    <el-select v-model="value" placeholder="请选择" style="margin-top: 10px">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      >
      </el-option>
    </el-select>
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
      input1: "",
      urladdress: "http://172.20.10.3:8080",
      // urladdress: "http://192.168.31.121:8080",
      fileList: [],
      imglist: "",
      options: [],
      value: "",
    };
  },

  methods: {
    headers() {
      return {
        Authorization: this.userid, // 直接从本地获取token就行
      };
    },

    handleUpSuccess(response, file, fileList) {
      if (response.code == 0) {
        var str = "";
        for (var i = 0; i < fileList.length; i++) {
          str = str + fileList[i].response.url + ",";
          this.imglist = str;
        }
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
      // console.log(file);
      this.hideUp = true;
    },
    // 发布文章
    fabuwenzhang() {
      var that = this;
      var userid = window.sessionStorage.getItem("userid");
      var arr = [];
      that.textarea1
        .split("\n")
        .forEach((item) => arr.push(`<p>${item.trim()}</p>`));
      var textarea2 = arr.join("<br>");
      console.log(userid, this.imglist, this.input1, textarea2, this.value);
      axios
        .get("/api/common/up_text", {
          // 还可以直接把参数拼接在url后边
          params: {
            userid: userid,
            picture: this.imglist,
            textname: this.input1,
            textcontent: textarea2,
            csid: this.value,
          },
        })
        .then(function (res) {
          console.log(res.data.result);
          if (res.data.result == "success") {
            that.$message("发布成功");
          }
          if (res.data.result == "fail") {
            that.$message("发布失败");
          }
        })
        .catch(function (error) {});
    },
  },
  mounted() {

    var that = this;
    axios
      .get("/api/system/commen_control/login/getclassify", {
        // 还可以直接把参数拼接在url后边
        params: {},
      })
      .then(function (res) {
        var len = Object.keys(res.data).length;
        for (var i = 0; i < len; i++) {
          var a = {};
          a.value = $(res.data[i])[0];
          a.label = $(res.data[i])[1];
          that.options.push(a);
        }
      })
      .catch(function (error) {});
  },
};
</script>
