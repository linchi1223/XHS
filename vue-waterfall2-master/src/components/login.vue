<template>
  <div>
    <div class="bg">
      <!-- <img src="../assets/bg.png" alt=""> -->
    </div>
    <div class="from">
      <div class="form-group" :model="loginForm" :rules="loginFormRules">
        <label for="exampleInputPhone1" prop="username">Phone Number</label>
        <input
          type="phone"
          class="form-control"
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
          class="form-control"
          id="exampleInputPassword1"
          v-model="loginForm.userpd"
        />
      </div>
      <button class="btn btn-outline-info login" @click="login">登入</button>
      <button class="btn btn-outline-secondary" @click="register">注册</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: [
        {
          username: "123",
          userpd: "123",
        },
      ],
      loginFormRules: [
        {
          required: true,
          message: "用户名",
          trigger: "blur",
        },
        {
          min: 3,
          max: 5,
          message: "3-5字符",
          trigger: "blur",
        },
      ],
    };
  },
  methods: {
    login() {
      // 测试部分
      // console.log(1);
      // var status = 200;
      // var toten ="123"
      // window.sessionStorage.setItem("token", toten);
      // 真实数据
      const {data:res} =this.$http.post('login',this.loginForm)
      console.log(res)
      // if(res.meta.status !== 200)return console.log('登入失败')
      // if (status !== 200) return console.log("登入失败");
      // console.log("login");
      // window.sessionStorage.setItem("token", res.data.toten);
      // this.$router.push("/index");
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
