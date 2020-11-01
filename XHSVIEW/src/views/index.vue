<style  lang="scss" scoped>
* {
  margin: 0;
}

.container-water-fall {
  // padding: 0 28px;
  width: 100vw;
  box-sizing: border-box;
  background-color: #ddd;
  // background-image: url(assets/bg.jpg);
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
            i {
            }

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
    background-color: rgba($color: #000, $alpha: 0.7) !important;
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
    background-color: rgba($color: #000, $alpha: 0.7) !important;
  }
}
.info {
  z-index: 1200;
  clear: both;
  height: 80px;
  position: fixed;
  width: 100%;
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
  line-height: 80px;
  margin-top: 15px;
  margin-right: 15px;
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
          {{ username }}测试名字山东分局啊收快递放假啊熵
        </div>
        <div class="userimg Fright">
          <el-avatar> User </el-avatar>
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
import loading from "../components/loading";
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
      col: 4,
      is_login: true,
      username: "",
      loading: false,
      gitHubData: {},
      // 第一次加载数据
      originData: [
        {
          img:
            "https://ci.xiaohongshu.com/3bf640b3-6f2e-5f71-a05e-61a6e6402faf?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title: "贵妇级好用的水乳有哪些呢？",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/551c1ed4-11b9-5ed8-be36-777e415c20e7?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "952",
        },
        {
          img:
            "https://ci.xiaohongshu.com/cbd291a0-2141-5937-8457-7e5e9e932338?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/68f6a729-ecd4-5419-bd97-98c157c0f30f?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
          user: "迷人的小妖精迷人的小妖精123",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
          user: "迷人的小妖精迷人的小妖精123",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
          user: "迷人的小妖精迷人的小妖精",
          like: "953",
        },
        {
          img:
            "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
          avatar:
            "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
          title:
            "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
          user: "迷人的小妖精迷人的小妖精123",
          like: "953",
        },
      ],
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
    user(){
      this.$router.push({ name: "User", params: {} });
    },
    // 设置列数
    switchCol(col) {
      this.col = col;
    },
    // // 获取gethub的瀑布流
    // getGitHub() {
    //   fetch("https://api.github.com/repos/AwesomeDevin/vue-waterfall2").then(
    //     (data) => {
    //       data.json().then((res) => {
    //         this.gitHubData = res;
    //       });
    //     }
    //   );
    // },
    // 加载更多
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
    this.data = [
      {
        img:
          "https://ci.xiaohongshu.com/3bf640b3-6f2e-5f71-a05e-61a6e6402faf?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title: "贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/551c1ed4-11b9-5ed8-be36-777e415c20e7?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "952",
      },
      {
        img:
          "https://ci.xiaohongshu.com/cbd291a0-2141-5937-8457-7e5e9e932338?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/68f6a729-ecd4-5419-bd97-98c157c0f30f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/eb971d00-05ab-5b2a-ba03-52d8f544c42b?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/9d5d58d0-7f91-5792-b8e3-25b13b5c1807?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/97247d43-f7ab-5755-a6a6-c5e6c293442f?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/7e4df2f1-a364-59e7-b7a8-b165abbecd69?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试",
        user: "迷人的小妖精迷人的小妖精",
        like: "953",
      },
      {
        img:
          "https://ci.xiaohongshu.com/19216d62-09cd-5d00-8116-0d60c9fb9102?imageView2/2/w/400/q/50/format/jpg",
        avatar:
          "https://img.xiaohongshu.com/avatar/5b7d198a7e6e15000155f7c9.jpg@80w_80h_90q_1e_1c_1x.jpg",
        title:
          "贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试贵妇级好用的水乳有哪些呢？千万不要去乱尝试1",
        user: "迷人的小妖精迷人的小妖精123",
        like: "953",
      },
    ];
    // this.getGitHub();
    // this.data = []
  },
};
</script>
