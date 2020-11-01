<template>
  <div>
    <div class="bg">
      <!-- <img src="../assets/bg.png" alt=""> -->
    </div>
    <div class="from">
      <div class="form-group" :model="registerForm">
        <label for="username" prop="username">username</label>
        <input
          type="username"
          class="form-control"
          id="username"
          aria-describedby="phoneHelp"
          v-model="registerForm.username"
        />
        <small id="PhoneHelp" class="form-text text-muted"
          >We'll never share your Phone with anyone else.</small
        >
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input
          type="password"
          class="form-control"
          id="password"
          v-model="registerForm.userpd"
        />
      </div>
       <div class="form-group">
        <label for="PhoneNumber">Phone Number</label>
        <input
          type="text"
          class="form-control"
          id="PhoneNumber"
          v-model="registerForm.mobilephone"
        />
      </div>
       <div class="form-group">
        <label for="Email">e-mail</label>
        <input
          type="text"
          class="form-control"
          id="Email"
          v-model="registerForm.email"
        />
      </div>
      <!-- <button class="btn btn-outline-info login" @click="login">登入</button> -->
      <button class="btn btn-outline-secondary" @click="register">注册</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      registerForm: [
        {
          username: "",
          userpd: "",
          email:"",
          mobilephone:"",
        },
      ],
    };
  },
  methods: {
    //注册
    register() {
      console.log(this.registerForm);
      
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
