<template>
  <div class="content">
    <el-tag type="info">头像</el-tag>
    <el-upload
      class="avatar-uploader"
      :action="urladdress + '/common/upload'"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      cut
    >
      <img v-if="userimg" :src="userimg" class="avatar" />
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
    <el-button @click="baocun()">保存</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      password: "",
      phone: "",
      userid: "",
      username: "",
      userimg: "",
      userimgadd: "",
      urladdress: "http://192.168.46.125:8080",
      // urladdress: "http://192.168.31.121:8080",
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      console.log(file.response.url);
      this.userimg = URL.createObjectURL(file.raw);
      this.userimgadd = file.response.url;
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
    baocun() {
      var that = this;
      var userid = window.sessionStorage.getItem("userid");
      axios
        .get("/api/system/commen_control/login/editUserInfo", {
          // 还可以直接把参数拼接在url后边
          params: {
            userid: userid,
            username: this.username,
            picture: this.userimgadd,
            phone: this.phone,
            password: this.password,
          },
        })
        .then(function (res) {
          if (res.data.result == "success") {
            that.$message("保存成功");
            window.sessionStorage.setItem("userimg", that.userimg);

          } else {
            that.$message("保存失败");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  mounted() {
    this.userid = window.sessionStorage.getItem("userid");
    var that = this;
    axios
      .get("/api/system/commen_control/login/edituser", {
        // 还可以直接把参数拼接在url后边
        params: {
          userid: this.userid,
        },
      })
      .then(function (res) {
        console.log(res.data.username);
        that.username = res.data.username;
        if (res.data.picture == "") {
          that.userimg =
            "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png";
        } else {
          console.log(res.data.picture);
          that.userimg = that.urladdress + res.data.picture;
        }

        that.phone = res.data.phone;
        that.password = res.data.password;
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
