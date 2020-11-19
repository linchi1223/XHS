<template>
  <div class="content">
    <el-tag type="info">头像</el-tag>
    <el-upload
      class="avatar-uploader"
      action="localhost:8080"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <el-tag type="info">用户名</el-tag>
    <el-input placeholder="用户名" v-model="username" clearable
      >{{ username }}
    </el-input>
    <el-tag type="info">密码</el-tag>
    <el-input show-password placeholder="密码" v-model="password" clearable
      >{{ password }}
    </el-input>
    <el-tag type="info">手机号</el-tag>
    <el-input placeholder="手机号" v-model="phone" clearable
      >{{ phone }}
    </el-input>
    <el-button>保存</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "123",
      password: "123",
      phone: "123",
      userid: "",
      username: "",
      userimg: "",
      imageUrl:
        "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
  mounted() {
    this.userid = window.sessionStorage.getItem("userid");
    this.username = window.sessionStorage.getItem("username");
    this.userimg = window.sessionStorage.getItem("userimg");
    console.log(window.sessionStorage.getItem("userid"));
    axios
      .get("/api/system/commen_control/login/edituser", {
        // 还可以直接把参数拼接在url后边
        params: {
          userid: this.userid,
        },
      })
      .then(function (res) {
        console.log(res);
      })
      .catch(function (error) {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.content {
  width: 1000px;
  margin: auto;
}
.el-input,
.el-tag {
  margin-top: 10px;
  margin-bottom: 5px;
}
.el-button {
  margin-top: 10px;
  float: right;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
