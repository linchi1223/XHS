<style  lang="less" scoped>
* {
  margin: 0;
}
.container-water-fall {
  // padding: 0 28px;
  width: 100vw;
  box-sizing: border-box;
  background-color: #ddd;
  h4 {
    padding-top: 56px;
    padding-bottom: 28px;
    font-family: PingFangSC-Medium;
    font-size: 36px;
    color: #000000;
    letter-spacing: 1px;
    text-align: justify;
  }
  .cell-item:hover {
    transform: translateY(-3px);
    box-shadow: 2px 2px 10px #fff;
    background: rgba(250, 250, 250, 0.8);
  }
  // button {
  //   background-color: #ff0;
  //   color: #24292e;
  //   border: 1px solid rgba(27, 31, 35, 0.2);
  //   border-radius: 0.25em;
  //   width: 100px;
  //   line-height: 26px;
  //   font-size: 13px;
  //   margin: 4px 0;
  //   margin-right: 4px;
  //   cursor: pointer;
  //   outline: none;
  //   &.blue-light {
  //     background: #27fbc2;
  //   }
  // }
  // button:hover {
  //   background-image: linear-gradient(-180deg, #fafbfc, #ccc 90%);
  // }

  .cell-item {
    // transition: background 0.3s linear;
    transition: all 0.25s ease-in;
    width: 100%;
    // margin-bottom: 18px;
    // padding: 5px;
    background: #fff;
    border: 5px solid #ddd;
    border-radius: 5px 5px 5px 5px;
    overflow: hidden;
    box-sizing: border-box;
    margin: 5px;
    img {
      // border-radius: 12px 12px 0 0;
      width: 100%;
      height: auto;
      display: block;
    }
    .item-body {
      // border: 1px solid #F0F0F0;
      padding: 12px;
      .item-desc {
        font-size: 13px;
        color: #444;
        line-height: 15px;
      }
      .item-footer {
        border-top: 1px solid #ddd;
        margin-top: 15px;
        padding-top: 5px;
        position: relative;
        display: flex;
        align-items: center;
        .avatar {
          width: 44px;
          height: 44px;
          border-radius: 50%;
          background-repeat: no-repeat;
          background-size: contain;
        }
        .name {
          max-width: 150px;
          margin-left: 10px;
          font-size: 14px;
          color: #999999;
        }
        .like {
          position: absolute;
          right: 0;
          display: flex;
          align-items: center;
          &.active {
            .like-total {
              color: #ff4479;
            }
          }
          i {
            width: 28px;
            display: block;
          }
          .like-total {
            margin-left: 10px;
            font-size: 12px;
            color: #999999;
          }
        }
      }
    }
  }
}
.clear {
  clear: both;
}
.LoginRegiste {
  float: right;
  margin-right: 90px;
}

.header {
  width: 100%;
  height: 80px;
  position: fixed;
  z-index: 1000;
  clear: both;
  button {
    background-color: rgba(0, 0, 0, 0.7) !important;
    transition: all 0.25s ease-in;
    color: #f0f0f0;
    border: 0px;
    width: 100px;
    font-weight: 400;
    margin: 15px 10px;
    height: 50px;
    opacity: 1;
    z-index: 900;
  }
  button:hover {
    transform: translateY(-1px);
    box-shadow: 2px 2px 10px #333;
    background-color: rgba(0, 0, 0, 0.7) !important;
  }
}

.info {
  z-index: 1200;
  clear: both;
  height: 80px;
  position: relative;

  float: right;
  cursor: pointer;
}
.headerbg {
  width: 100%;
  height: 80px;
  position: fixed;
  z-index: 800;
  overflow: hidden;
  background-color: rgba(255, 255, 255, 0.5);
  // .img {
  //   width: 100%;
  //   opacity: 0.8;
  // }
}
.Fleft {
  float: left;
}
.Fright {
  float: right;
}
.logo {
  width: 80px;
  height: 80px;
  position: fixed;
  z-index: 1020;
  img {
    width: 100%;
    height: 100%;
  }
}
.username {
  line-height: 80px;
  size: 20px;
  margin-right: 15px;
}
.userimg {
  // background-color: salmon;
  text-align: center;
  line-height: 80px;
  width: 60px;
  height: 60px;
  margin-top: 15px;
  border-radius: 50%;
  margin-right: 15px;
}
.githubdata {
  float: right;
  margin-right: 90px;
  img {
    width: 14px;
    // height: 16px;
  }
}
</style>
<template>
  <div class="container-water-fall">
    <div class="header">
      <div class="logo Fleft"><img src="../assets/logo.png" alt="" /></div>

      <div class="info" v-if="!is_login">
        <button class="btn Fright register" type="submit" @click="register()">
          注册
        </button>
        <button class="btn Fright login" type="submit" @click="login()">
          登入
        </button>
      </div>

      <div class="info" v-if="is_login" @click="user()">
        <div class="username Fright">
          {{ username }}
        </div>
        <div class="userimg Fright">
          <el-avatar shape="square" :size="50" :src="squareUrl"></el-avatar>
        </div>
      </div>
      <div class="headerbg">
        <!-- <img class="img" src="../assets/bg.jpg" alt="" /> -->
      </div>
    </div>
    <waterfall
      :col="col"
      :data="data"
      @loadmore="loadmore"
      style="width: 90%; margin: auto; padding-top: 80px"
    >
      <template>
        <div
          class="cell-item"
          :key="index"
          v-for="(item, index) in data"
          @click="comments(index)"
        >
          <img v-if="item.img" :src="item.img" alt="加载错误" />
          <div class="item-body">
            <div class="item-desc">{{ item.title }}</div>
            <div class="item-footer">
              <div
                v-if="item.avatar"
                class="avatar"
                :style="{ backgroundImage: `url(${item.avatar})` }"
              ></div>
              <div class="name">{{ item.user }}</div>
              <div class="like" :class="item.liked ? 'active' : ''">
                <i></i>
                <div class="like-total">{{ item.like }}</div>
              </div>
            </div>
          </div>
        </div>
      </template>
    </waterfall>
    <loading :show="loading" />
  </div>
</template>

<script>
/*
  注意:
  1.itemWidth需要与gutterWidth一起使用才会生效，否则会进行自适应宽度
  2.使用了waterfall的组件不允许使用scoped,否则样式会有问题
*/
// import Vue from 'vue'
import loading from "./loading";

import json from "./data.json";
// import  routerLink  from 'vue-router'
export default {
  props: {
    title: String,
  },
  components: {
    loading,
  },
  data() {
    return {
      data: [],
      col: 5,
      squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
      is_login: false,
      username: "",
      loading: false,
      gitHubData: {},
      originData: json,
    };
  },
  computed: {
    itemWidth() {
      return 133 * 0.5 * (document.documentElement.clientWidth / 375);
    },
    gutterWidth() {
      return 10 * 0.5 * (document.documentElement.clientWidth / 375);
    },
  },
  methods: {
    reset() {
      this.data = [];
    },
    //登入
    login() {
      // this.$router.push("/lod");
      this.$router.push({ name: "Login", params: {} });
    },
    //注册
    register() {
      // this.$router.push("/lod");
      this.$router.push({ name: "Register", params: {} });
    },
    // 页面跳转
    comments(id) {
      // console.log(1)
      this.$router.push({ name: "Comment", params: {} });
    },
    user() {
      this.$router.push({ name: "User", params: {} });
    },

    // switchCol(col) {
    //   this.col = col;
    //   // console.log(this.col)
    // },
    getGitHub() {
      fetch("https://api.github.com/repos/AwesomeDevin/vue-waterfall2").then(
        (data) => {
          data.json().then((res) => {
            // console.log(res)
            this.gitHubData = res;
          });
        }
      );
    },
    scroll(data) {
      // console.log(data)
    },

    loadmore(num) {
      this.loading = true;
      setTimeout(() => {
        this.data = this.data.concat(this.originData, this.originData);
        this.loading = false;
      }, 1000);
    },
  },
  mounted() {
    var self = this;
    this.data = this.originData;
    this.getGitHub();



    
    var tokenname = window.sessionStorage.getItem("username");
    if (tokenname == null) {
      this.is_login = false;
    } else {
      this.is_login = true;
      this.username = tokenname;
    }
  },
};
</script>