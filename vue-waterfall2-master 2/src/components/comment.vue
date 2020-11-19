<template>
  <div class="all">
    <div class="contents">
      <div class="head">
        <h1
          class="username"
          style="line-height: 60px; display: line-block; margin-left: 25px"
        >
          {{ textname }}
        </h1>
        <!-- <button class="btn btn-defult btnn">发布</button> -->
      </div>
      <hr />
      <div class="item">
        <div class="block" style="margin-bottom: 10px">
          <el-carousel height="750px">
            <el-carousel-item
              v-for="(itempic,index) in pictures"
              :key="index"
              style="
                display: flex;
                align-items: center;
                justify-content: center;
              "
            >
              <img
                v-bind:src="itempic"
                class="d-block w-100"
                style="max-width: 95%; height-width: 100%"
                alt="..."
              />
            </el-carousel-item>
          </el-carousel>
        </div>

        <div class="wenzhang" v-html="textcontent"></div>
        <hr />
        <div class="commentinput">
          <div class="media">
            <!-- <a href="#"><img v-bind:src="userimg" alt="comment" /></a> -->
            <el-avatar shape="square" :size="50" :src="userimg"> </el-avatar>
            <div class="media-body">
              <div class="card-body">
                <div class="textup">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 4, maxRows: 4 }"
                    placeholder="请输入内容"
                    v-model="textarea"
                  >
                  </el-input>
                </div>
              </div>
            </div>
            <button class="btn btn-defualt" @click="fasong()">发送</button>
          </div>
        </div>
        <div class="comments">
          <div class="media" v-for="(item1,index) in comment_list" :key="index">
            <a href="#"><img v-bind:src="item1.cUser_p" alt="comment" /></a>
            <div class="media-body">
              <h5><a href="#">{{item1.cUser_n}}</a></h5>
              <span class="date">{{item1.time}}</span>
              <p>
                {{item1.comment}}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="Cuserinfo">
      <div class="W_person_info">
        <div class="cover">
          <div class="headpic">
            <a bpfilter="page_frame" href="" title="textuser"
              ><img
                class="W_face_radius"
                :src="textuserimg"
                width="60"
                height="60"
                alt="textuser"
            /></a>
          </div>
        </div>
        <div class="WB_innerwrap">
          <div class="nameBox">
            <a
              bpfilter="page_frame"
              href=""
              class="name S_txt1"
              title="textuser"
              >{{ textuser }}</a
            >
          </div>
          <ul class="user_atten clearfix W_f18">
            <li class="S_line1">
              <a bpfilter="page_frame" href="follow" class="S_txt1"
                ><strong node-type="follow">{{ user_info.favor }}</strong
                ><span class="S_txt2">关注</span></a
              >
            </li>
            <li class="S_line1">
              <a bpfilter="page_frame" href="fans" class="S_txt1"
                ><strong node-type="fans">{{ user_info.fans }}</strong
                ><span class="S_txt2">粉丝</span></a
              >
            </li>
            <li class="S_line1">
              <a bpfilter="page_frame" href="profile" class="S_txt1"
                ><strong node-type="weibo">{{ user_info.textCount }}</strong
                ><span class="S_txt2">文章</span></a
              >
            </li>
          </ul>
        </div>
      </div>
      <button class="btn btn-warning userchange" v-if="followed == true">
        <h5>已关注</h5>
      </button>
      <button class="btn btn-danger userchange" v-if="followed == false">
        <h5>关注</h5>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      urladdress: "http://192.168.46.125:8080",
      // urladdress: "http://192.168.31.121:8080",
      //  urladdress: "http://192.168.46.124:8080",
      followed: false,
      textarea: "",
      textId: "",
      comment_list: [],
      picture: "",
      username: "",
      textname: "",
      uptime: "",
      textcontent: "",
      pictures: [],
      user_info: [],
      textuser: "",
      textuserimg: "",
      userimg: "",
      userid: "",
    };
  },
  methods: {
    // 发送评论
    fasong() {
      var that = this;
      axios
        .get("/api/common/insert_comment", {
          // 还可以直接把参数拼接在url后边
          params: {
            userid: that.userid,
            comment: that.textarea,
            textid: window.sessionStorage.getItem("textId"),
          },
        })
        .then(function (res) {
          console.log(res);
          that.comment_list = res.data.comment_list.reverse();
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  mounted() {
    var that = this;
    // 刷新的时候进行
    that.userid = window.sessionStorage.getItem("userid");
    that.userimg = window.sessionStorage.getItem("userimg");
    var textId = this.$route.params.textId;
    if (textId == null) {
      textId = window.sessionStorage.getItem("textId");
    }
    window.sessionStorage.setItem("textId", textId);
    // 获取信息
    axios
      .get("/api/system/commen_control/login/getTextInfo", {
        // 还可以直接把参数拼接在url后边
        params: {
          textid: textId,
        },
      })
      .then(function (res) {
        console.log(res);
        var datas = res.data;
        var textinfo = res.data.textinfo;
        var picturescount = [];
        that.textname = textinfo.textname;
        that.textcontent = textinfo.textcontent;
        var pictures = textinfo.picture.split(",");
        console.log(pictures);
        for (var j = 0; j < pictures.length; j++) {
          pictures[j] = that.urladdress + pictures[j];
        }
        that.pictures = pictures;
        setTimeout(function () {
          $(".imgs").first().addClass("active");
        }, 300);
        that.user_info = res.data.user_info;
        that.textuser = res.data.username;
        that.textuserimg = that.urladdress + res.data.picture;
        that.comment_list = res.data.comment_list.reverse();
        console.log(that.comment_list)
      })
      .catch(function (error) {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.wenzhang {
  font-size: 15px;
}
.carousel-indicators {
  position: absolute;
  bottom: 10px;
  left: 50%;
  z-index: 15;
  padding-left: 0;
  margin-left: -30%;
  text-align: center;
  list-style: none;
}
.carousel-indicators li {
  box-sizing: content-box;
  -ms-flex: 0 1 auto;
  flex: 0 1 auto;
  width: 30px;
  height: 5px;
  margin-right: 3px;
  margin-left: 3px;
  text-indent: -999px;
  cursor: pointer;
  background-color: #000;
  border: 0px;
  /* border-bottom: 3px solid transparent; */
  opacity: 0.5;
  transition: opacity 0.6s ease;
}
.carousel-indicators .active {
  width: 30px;
  height: 5px;
  /* margin: 0; */
  background-color: #fff;
}
.items {
  height: 100%;
}
.userchange {
  width: 200px;
  margin: auto;
  display: block;
  color: #fff;
}

.nameBox a {
  font-size: 20px;
  line-height: 20px;
  color: #000;
}
.cover {
  position: relative;
  height: 75px;
}
ul {
  list-style: none;
}
li .S_txt2 {
  display: block;
  font-size: 12px;
  margin-top: -6px;
  color: #38547b;
  text-decoration: none;
}
li {
  display: list-item;
  text-align: -webkit-match-parent;
}
.user_atten li:last-child {
  border: 0;
}
.W_person_info {
  position: relative;
}
.W_face_radius {
  border-radius: 50%;
}
.W_person_info .WB_innerwrap {
  padding: 30px 9px;
}
.S_txt1 {
  font-size: 18px;
}
.W_person_info .nameBox {
  height: 35px;
  line-height: 16px;
  padding-top: 5px;
  text-align: center;
  *height: 18px;
  *padding-top: 3px;
  *line-height: 18px;
}
.user_atten li {
  float: left;
  width: 65px;
  text-align: center;
  border-right-width: 1px;
  border-right-style: solid;
}
.headpic {
  width: 60px;
  height: 60px;
  padding: 3px;
  background: rgba(255, 255, 255, 0.3);
  border-radius: 50%;
  position: absolute;
  bottom: -20px;
  left: 50%;
  margin-left: -30px;
}
.close {
  visibility: hidden;
  font-size: 2.5rem;
  transition: 0.3s;
}
.media:hover .close {
  visibility: visible;
}
.media {
  align-items: center;
  margin-bottom: 35px;
  padding: 15px;
  transition: 0.4s;
  visibility: block;
}
.media:hover {
  background: floralwhite;
}
.media a img {
  border-radius: 50%;
  max-width: 100%;
  vertical-align: middle;
  border-style: none;
  width: 50px;
  height: 50px;
  margin-right: 20px;
}

/* .media .media-body {
  margin-left: 30px;
} */
h5 {
  display: inline-block;
}
h5 a {
  font-size: 18px;
  font-weight: 700;
  color: #14212b;
  transition: 0.4s;
}
a:hover,
a:focus,
a:active {
  text-decoration: none;
  outline: 0;
  color: yellowgreen;
}
.media-body .date {
  font-size: 13px;
  color: #616161;
  margin-left: 14px;
}
.media-body p {
  font-size: 15px;
  color: #696969;
  line-height: 25px;
}
.item {
  /* background-color: yellowgreen; */
  padding: 20px;
  position: relative;
}
.ctext {
  line-height: 16px;
  font-size: 16px;
  margin-bottom: 15px;
}

.deleteorchange {
  position: absolute;
  right: 0;
}
.head {
  clear: both;
  min-height: 50px;
  height: auto;
}

.btnn {
  border: 1px solid #ddd;
  border-radius: 5px;
  float: right;
  position: relative;
  width: 100px;
  top: 10px;
  margin-right: 10px;
}
.all {
  position: fixed;
  margin: auto;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  width: 100%;
  height: 3rem;
  background: #fff;
  z-index: 999;
  height: 100%;
  max-width: 1000px;
  /* background-color: rgba(0, 0, 0, 0.2); */
}
.contents {
  width: 700px;
  height: 100%;
  border: 1px solid #ddd;
  overflow: scroll;
  scroll-behavior: auto;
  scroll-behavior: smooth;
  float: left;
}
.Cuserinfo {
  margin: 20px 0;
  width: 280px;
  height: 280px;

  float: right;
  border: 1px solid #ddd;
}
</style>
