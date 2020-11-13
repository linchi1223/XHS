<template>
  <div>
    <div class="bg">
      <!-- <img src="../assets/bg.png" alt=""> -->
    </div>

    <div class="from">
      <div class="form-group" :model="loginForm">
        <label for="exampleInputPhone1" prop="username">Phone Number</label>
        <input
          type="phone"
          class="form-control phoneN"
          id="exampleInputphone1"
          aria-describedby="phoneHelp"
          v-model="loginForm.username"
        />
        <small id="PhoneHelp" class="form-text text-muted"
          >We'll never share your Phone with anyone else.</small
        >
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input
          type="password"
          class="form-control passW"
          id="exampleInputPassword1"
          v-model="loginForm.userpd"
        />
      </div>
      <button class="btn btn-outline-info login" :plain="true" @click="login">
        登入
      </button>
      <button class="btn btn-outline-secondary" :plain="true" @click="register">
        注册
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: [
        {
          username: "",
          userpd: "",
          login_api: "/api/system/commen_control/login/verify",
        },
      ],
    };
  },
  methods: {
    login() {
      let th = this;
      if ($(".phoneN").val() == "" && $(".passW").val() == "") {
        th.$message("请输入用户名和密码");
      } else if ($(".phoneN").val() == "") {
        th.$message("请输入用户名");
      } else if ($(".passW").val() == "") {
        th.$message("请输入密码");
      } else if ($(".phoneN").val() != "" && $(".passW").val() != "") {
        var that = this;
        const a = axios
          .get("/api/system/commen_control/login/verify", {
            // 还可以直接把参数拼接在url后边
            params: {
              phone: this.loginForm.username,
              password: this.loginForm.userpd,
            },
          })
          .then(function (res) {
            console.log(res);
            if (res.data.data == "error") return th.$message("用户名或密码错误");
            if (res.data.data == "success") {
              console.log(1)
              that.$message("登入成功");
              // window.sessionStorage.setItem("username", that.loginForm.username);
              window.sessionStorage.setItem("userid", res.data.userid);
              setTimeout(function () {
                that.$router.push("/index");
              }, 1500);
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },

    //注册
    register() {
      // this.$router.push("/lod");
      this.$router.push({ name: "Register", params: {} });
    },
  },
};
(function () {});
</script>

<style scoped>
.bg {
  position: fixed;
  width: 100%;
  height: 100%;
  /* background-color: antiquewhite; */
}
.from {
  padding: 3%;
  position: fixed;
  box-shadow: 2px 2px 10px #333;
  width: 40%;
  height: 70%;
  border-radius: 5px;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  font-size: 16px;
}
input {
  height: calc(1.5em + 0.75rem + 10px) !important;
  font-size: 1.5rem !important;
}
.btn {
  width: 100%;
  margin: 10px 0;
}
.login {
  background-color: tomato;
  border: 1px solid tomato;
  color: white;
}
</style>
