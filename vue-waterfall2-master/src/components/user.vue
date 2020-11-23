<template>
  <div class="all">
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
    <div class="contents">
      <div class="head">
         <a href="http://localhost:8080/#/index">
        <h1
          class="username"
          style="
            float: left;
            overflow: hidden;
            text-overflow: ellipsis;
            line-height: 90px;
            margin-left: 25px;
            width: 500px;
            height: 80px;
          "
        >
          主页
        </h1>
       </a>
        <button class="btn btn-defult btnn" @click="fabu()">发布</button>
      </div>
      <hr />
      <div class="items">
        <div
          class="media"
          v-for="(item, index) in text_list"
          :key="index"
          :data-textid="item.textid"
        >
          <a href="#"><img :src="item.pictures[0]" alt="comment" /></a>
          <div class="media-body">
            <h5>
              <a href="#">{{ item.textname }}</a>
            </h5>
            <span class="date">{{ item.uptime }}</span>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
              @click="handleClose(item.textid)"
            >
              <span aria-hidden="true">&times;</span>
            </button>
            <div v-html="item.textcontent">
              <!-- {{ item.textcontent }} -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="Cuserinfo">
      <div class="W_person_info">
        <div class="cover">
          <div class="headpic">
            <a bpfilter="page_frame" href="" :title="username"
              ><img
                class="W_face_radius"
                :src="userimg"
                width="60"
                height="60"
                :alt="username"
            /></a>
          </div>
        </div>
        <div class="WB_innerwrap">
          <div class="nameBox">
            <a
              bpfilter="page_frame"
              href=""
              class="name S_txt1"
              :title="username"
              >{{ username }}</a
            >
          </div>
          <ul class="user_atten clearfix W_f18">
            <li class="S_line1">
              <a bpfilter="page_frame" href="follow" class="S_txt1"
                ><strong node-type="follow">{{ userinfoTable.favor }}</strong
                ><span class="S_txt2">关注</span></a
              >
            </li>
            <li class="S_line1">
              <a bpfilter="page_frame" href="fans" class="S_txt1"
                ><strong node-type="fans">{{ userinfoTable.fans }}</strong
                ><span class="S_txt2">粉丝</span></a
              >
            </li>
            <li class="S_line1">
              <a bpfilter="page_frame" href="profile" class="S_txt1"
                ><strong node-type="weibo">{{ userinfoTable.textCount }}</strong
                ><span class="S_txt2">文章</span></a
              >
            </li>
          </ul>
        </div>
      </div>
      <button class="btn btn-warning userchange" @click="change()">
        用户修改
      </button><button class="btn btn-danger userchange" style="margin-top:10px" @click="out()">
        登出
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {

      urladdress: "http://192.168.46.125:8080",
      followed: false,
      textarea: "",
      userid: "",
      username: "",
      userimg: "",
      text_list: [],
      pictures: [],
      userinfoTable: [],
      dialogVisible: false,
    };
  },
  methods: {
    fabu() {
      this.$router.push({ name: "Upcontent", params: {} });
    },
    //注册
    change() {
      // this.$router.push("/lod");
      this.$router.push({ name: "Userchange", params: {} });
    },
    out(){
      sessionStorage.clear();
      this.$router.push({ name: "Index", params: {} });
    },
    handleClose(textid) {
      console.log(textid);
      this.$confirm("确认删除？")
        .then(() => {
          console.log(textid);
          this.delect(textid);
          done();
        })
        .catch(() => {});
    },
    delect(textid) {
      var that= this;
      axios
        .get("/api/common/delete_text", {
          // 还可以直接把参数拼接在url后边
          params: {
            textid: textid,
          },
        })
        .then(function (res) {
          axios
            .get("/api/system/commen_control/login/getUserText", {
              // 还可以直接把参数拼接在url后边
              params: {
                userid: that.userid,
              },
            })
            .then(function (res) {
             
              that.userinfoTable = res.data.userinfoTable;
              var textlist = res.data.text_list;
              var a = [];
              for (var i = 0; i < textlist.length; i++) {
                var pictures = (textlist[i].picture || "").split(",");
                var b = [];
                for (var j = 0; j < pictures.length; j++) {
                  pictures[j] = that.urladdress + pictures[j];
                  b.push(pictures[j]);
                }
                textlist[i].pictures = b;
              }
              that.text_list = textlist;
            })
            .catch(function (error) {
              console.log(error);
            });
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
  mounted() {
    var that = this;
    this.userid = window.sessionStorage.getItem("userid");
    this.username = window.sessionStorage.getItem("username");
    this.userimg = window.sessionStorage.getItem("userimg");
    axios
      .get("/api/system/commen_control/login/getUserText", {
        // 还可以直接把参数拼接在url后边
        params: {
          userid: this.userid,
        },
      })
      .then(function (res) {
        console.log(res);
        that.userinfoTable = res.data.userinfoTable;
        var textlist = res.data.text_list;
        var a = [];
        for (var i = 0; i < textlist.length; i++) {
          var pictures = (textlist[i].picture || "").split(",");
          var b = [];
          for (var j = 0; j < pictures.length; j++) {
            pictures[j] = that.urladdress + pictures[j];
            b.push(pictures[j]);
          }
          textlist[i].pictures = b;
        }
        that.text_list = textlist;
      })
      .catch(function (error) {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.items {
  height: 100%;
  overflow: scroll;
  scroll-behavior: auto;
  scroll-behavior: smooth;
  padding-bottom: 70px;
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
  padding: 30px 9px 10px;
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
  width: 100px;
  height: 100px;
}

.media .media-body {
  margin-left: 30px;
}
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
  background-color: yellowgreen;
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
  height: 80px;
}

.btnn {
  border: 1px solid #ddd;
  border-radius: 5px;
  float: right;
  position: relative;
  width: 100px;
  top: 30px;
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

  float: left;
}
.Cuserinfo {
  margin: 20px 0;
  width: 280px;
  height: 300px;

  float: right;
  border: 1px solid #ddd;
}
</style>
