<template>
  <div class="all">
    <div class="contents">
      <div class="head">
        <h1
          class="username"
          style="line-height: 60px; display: line-block; margin-left: 25px"
        >
          文章主题名字文章主题名字文章主题名字文章主题名字文章123
        </h1>
        <!-- <button class="btn btn-defult btnn">发布</button> -->
      </div>
      <hr />
      <div class="item"></div>
    </div>
    <div class="Cuserinfo">
      <div class="W_person_info">
        <div class="cover">
          <div class="headpic">
            <a bpfilter="page_frame" href="" title="薛定的小谔猫"
              ><img
                class="W_face_radius"
                src="https://tvax1.sinaimg.cn/crop.0.0.828.828.1024/b4794d17ly8gds0hpo8qij20n00n00ud.jpg?KID=imgbed,tva&amp;Expires=1603788250&amp;ssig=DnwD1levlx"
                width="60"
                height="60"
                alt="薛定的小谔猫"
            /></a>
          </div>
        </div>
        <div class="WB_innerwrap">
          <div class="nameBox">
            <a
              bpfilter="page_frame"
              href=""
              class="name S_txt1"
              title="薛定的小谔猫"
              >薛定的小谔猫</a
            >
          </div>
          <ul class="user_atten clearfix W_f18">
            <li class="S_line1">
              <a bpfilter="page_frame" href="follow" class="S_txt1"
                ><strong node-type="follow">78</strong
                ><span class="S_txt2">关注</span></a
              >
            </li>
            <li class="S_line1">
              <a bpfilter="page_frame" href="fans" class="S_txt1"
                ><strong node-type="fans">86</strong
                ><span class="S_txt2">粉丝</span></a
              >
            </li>
            <li class="S_line1">
              <a bpfilter="page_frame" href="profile" class="S_txt1"
                ><strong node-type="weibo">47</strong
                ><span class="S_txt2">微博</span></a
              >
            </li>
          </ul>
        </div>
      </div>
      <button class="btn btn-warning userchange">用户修改</button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
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
