<template>
  <div class="release_wrap">
    <el-main>
      <el-table
        ref="multipleTable"
        highlight-current-row
        v-loading="loading"
        :border="true"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
      >
        <el-table-column align="center" prop="moveid" label="视频ID" width="65">
        </el-table-column>

        <el-table-column
          align="center"
          prop="moveCategorize"
          label="视频分类"
         width="439"
        >
        </el-table-column>

        <el-table-column align="center" prop="moveNameOld" label="视频名称" width="505">
        </el-table-column>

        <el-table-column align="center" label="创建时间" width="285">
          <template slot-scope="scope">
            {{ scope.row.createTime }}
          </template>
        </el-table-column>

        <!-- 操作 -->
        <el-table-column label="操作" width="">
          <template slot-scope="scope">
            <el-button
              v-show="scope.row.roleId !== 3"
              size="mini"
              type="primary"
              @click="playVideo(scope.$index, scope.row)"
              >查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>

<br>

            <el-table
        ref="multipleTable"
        highlight-current-row
        v-loading="loadingmoves"
        :border="true"
        :data="movestableData"
        tooltip-effect="dark"
        style="width: 100%"
      >
        <!-- <el-table-column align="center" prop="studyId" label="视频id" width="505">
        </el-table-column> -->
          <el-table-column align="center" prop="studyYewu" label="所属业务" width="505">
        </el-table-column>

        <el-table-column align="center" prop="studyWangkename" label="视频名称" width="505">
        </el-table-column>

                <el-table-column align="center" prop="movesZt" label="视频状态" width="265">
                          <template slot-scope="scope">
          <div :style="{ color: scope.row.movesZt ? '#cf292f' : '#cf292f' }">
            {{ scope.row.movesZt }}
          </div>
        </template>
        </el-table-column>
            </el-table>

      <!-- <div style="boder=1px" class="passbox" v-for="(item, index) in this.moveinfo" :key="index">
        {{ item }}
      </div> -->

      <!--分页-->
      <el-pagination
        style="margin-top: 25px"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pageNo"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="queryInfo.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-main>

    <!-- 播放视频的控件 -->
    <el-dialog
      :modal="false"
      :title="moveNameOld"
      :show-close="false"
      :visible.sync="dialogVisible"
      width="40%"
    >
      <video
        :src="`http://localhost:8888/api/policemen/${moveId}`"
        controls="controls"
        width="100%"
        height="400px"
        @canplay="getVidDur()"
        @timeupdate="getMovesupdateTime"
        id="myvideo"
        ref="vueMiniPlayer"
      ></video>
      <div slot="footer" class="dialog-footer">
        <el-button @click="movesstop">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
var video = () => {
  var videoTime = document.getElementById("myvideo");
  console.log(videoTime.duration); //获取视频总时长
  console.log(videoTime.currentTime); //获取视频当前播放时间
};
import moves from "@/api/moves";
import studyduration from "@/api/studyduration";
import { mapState } from "vuex";
import wangke from "@/api/wangke";
export default {
  data() {
    return {
      title: "",
      videolist: "",
      //表格数据
      tableData: [],
      //弹框组件隐藏
      dialogVisible: false,
      //用于保存视频的id moveId
      moveId: 0,
      //保存视频的名称
      moveNameOld: "",
      zongshichang: 0, //视频总时长

      //add参数
      addStudryqueryInfo: {
        studyUsername: "",
        studyYewu: "",
        studyWangkename: "",
        studyWangkeshipingId: "",
        createBy: "",
      },
      // update更新
      updateStudryqueryInfoForm: {
        studyWangkeshipingId: "", // 网课id
        studyShichang: "", // 当前学习时长
        studyLeijishichang: "", // 累积总学习时长
        studyTotalshichang: "", //总时长

        studyUsername: "", //用户名称
        studyWangkename: "", //网可业务
      },

      queryInfo: {
        pageNo: 1,
        pageSize: 10,
      },
      moveinfo: [],

      total: 0,
      //表格信息加载
      loading: true,
      // 更新 视频
      //下拉框所选择的数据
      movesselected: "",
      //表格行被选中后的所有操作方式的数据
      videoUploadPercent: 0,
      customColor: "#409eff",
      movestableData:[], //视频状态
         loadingmoves:true,

      updatemoveForm: {
        // status: "",
      }, //更新负责操作

      studeyqueryInfo: {
        studyUsername: localStorage.getItem("username"),
      },
    };
  },
  computed: {
    //引入vuex中state的变量，可以直接this.xxx调用到
    ...mapState(["article"]),
  },
  created() {
    // this.getVideoInfo();
    console.log(localStorage.getItem("username"));
    this.getyonghuwangkeyewuBank(); //查询用户所属的业务和银行

    this.getstudydurration(); //个人学生学习成绩
  },

  methods: {
    getstudydurration() {
      studyduration.getStudyMovesZtInfo(this.studeyqueryInfo).then((resp) => {
        // console.log(resp.data);
        this.movestableData=resp.data
 this.loadingmoves = false;
      });
    },

    getyonghuwangkeyewuBank() {
      wangke
        .getYonghuWangkeYeWuBankInfo({
          username: localStorage.getItem("username"),
        })
        .then((resp) => {
          this.tableData = resp.data;
          this.loading = false;
        });
    },

    //   这个视频播放的时候进行暂停 获取总时长和当前播放的时间
    movesstop() {
      var videoTime = document.getElementById("myvideo");
      this.zongshichang = videoTime.duration.toFixed(0);
      console.log(videoTime.duration.toFixed(0)); //获取视频总时长
      this.dialogVisible = false;
      // 触发暂停
      this.$refs.vueMiniPlayer.pause(); //暂停
      // 暂停就进行更新 之前查看就进行添加数据信息  这个就根据ｉｄ去更新
      this.currentTime = videoTime.currentTime.toFixed(0); //获取视频当前播放时间
      console.log(videoTime.currentTime.toFixed(0)); //打印播放时间 null
      this.updateStudryqueryInfoForm.studyWangkeshipingId =
        this.addStudryqueryInfo.studyWangkeshipingId; //拿到学生记录里面的网课id 进行更新操作
      this.updateStudryqueryInfoForm.studyShichang =
        videoTime.currentTime.toFixed(0); //获取视频当前播放时间
      this.updateStudryqueryInfoForm.studyLeijishichang =
        videoTime.currentTime.toFixed(0); //当前累积获取视频当前播放时间
      this.updateStudryqueryInfoForm.studyTotalshichang =
        videoTime.duration.toFixed(0); //获取视频总时长
      // 更新操作 累积时长
      (this.updateStudryqueryInfoForm.studyUsername =
        localStorage.getItem("username")),
        // this.updateStudryqueryInfoForm.createBy =localStorage.getItem("username"), //创建人
        (this.updateStudryqueryInfoForm.studyWangkename =
          this.addStudryqueryInfo.studyWangkename);
      setTimeout(() => {
        studyduration
          .updateStudyDurationInfo(this.updateStudryqueryInfoForm)
          .then((resp) => {
            if (resp.code === 200) {
              this.$notify({
                title: "Tips",
                message: "记录时长成功",
                type: "success",
                duration: 2000,
              });
              this.getyonghuwangkeyewuBank(); //重新刷新页面数据
              this.$router.go(0); //重新进入页面
            }
          });
      }, 500);
    },

    jump_home() {
      this.$router.go(-1);
    },

    getVidDur() {
      var videoTime = document.getElementById("myvideo");
      //   this.$nextTick(() => {
      //解决回调延迟到下次DOM更新循环之后执行。在修改数据之后立即使用它，然后等待DOM更新。
      // console.log(videoTime.duration.toFixed(0)); //获取视频总时长
      //   });
      this.zongshichang = videoTime.duration.toFixed(0);
    },
    getMovesupdateTime(e) {
      this.currentTime = e.target.currentTime; //获取视频当前播放时间
      //   console.log(e.target.currentTime.toFixed(0)); //打印播放时间
    },

    //获取video表格数据
    // getVideoInfo() {
    //   moves.getMovesinfo(this.queryInfo).then((resp) => {
    //     console.log(resp);
    //   });
    // },
    //分页插件的大小改变
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.getVideoInfo();
    },
    //分页插件的页数
    handleCurrentChange(val) {
      this.queryInfo.pageNo = val;
      this.getVideoInfo();
    },

    //点击播放按钮
    playVideo(i, val) {
      //显示弹框
      this.dialogVisible = true;
      //保存视频名字
      this.moveNameOld = val.moveNameOld;
      //保存视频id
      this.moveId = val.moveid;
      (this.addStudryqueryInfo.studyUsername =
        localStorage.getItem("username")),
        (this.addStudryqueryInfo.createBy = localStorage.getItem("username"));
      this.addStudryqueryInfo.studyWangkeshipingId = val.moveid;
      this.addStudryqueryInfo.studyWangkename = val.moveNameOld.slice(0, -4);
      this.addStudryqueryInfo.studyYewu = val.moveCategorize; //学生业务分类
      console.log(i, val);
      // 只有点击查看就开始记录 add操作
      studyduration.addStudyDurationInfoInfo(this.addStudryqueryInfo);
    },
  },
};
</script>

<style  scoped lang='scss'>
.passbox{
  // display:inline;
//  position: relative;
  //  float: right;
  //  display:block ;
   padding: 20px 10px;
    box-sizing : border-box;

}
.release_wrap {
  //   background-image: url("../assets/home1.jpg");
  width: 100%;
  height: 100%;
  position: relative;
}
//卡片效果
.release_card {
  width: 70%;
  position: absolute;
  top: 200px;
  left: 50%;
  transform: translateX(-50%);
  box-shadow: 0 0.3px 0.7px rgba(0, 0, 0, 0.126),
    0 0.9px 1.7px rgba(0, 0, 0, 0.179), 0 1.8px 3.5px rgba(0, 0, 0, 0.224),
    0 3.7px 7.3px rgba(0, 0, 0, 0.277), 0 10px 20px rgba(0, 0, 0, 0.4);
  transition: 0.5s ease; //改变大小
  &:hover {
    box-shadow: 0 0.7px 1px rgba(0, 0, 0, 0.157),
      0 1.7px 2.6px rgba(0, 0, 0, 0.224), 0 3.5px 5.3px rgba(0, 0, 0, 0.28),
      0 7.3px 11px rgba(0, 0, 0, 0.346), 0 20px 30px rgba(0, 0, 0, 0.5);
  }
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
// title效果
.release_title {
  text-align: center;
  font-size: 38px;
  font-weight: bold;
  position: absolute;
  top: 75px;
  left: 50%;
  transform: translateX(-50%);
  font-family: Lato, sans-serif; //字体
  letter-spacing: 4px; //字符间距空白
  text-transform: uppercase; //转换文本，控制大小写
  background: linear-gradient(
    90deg,
    rgba(0, 0, 0, 1) 0%,
    rgba(255, 255, 255, 1) 50%,
    rgba(0, 0, 0, 1) 100%
  );
  background-size: 80%; //背景大小
  background-repeat: no-repeat; //背景平铺不重复
  // below two lines create text gradient effect
  color: rgba(237, 227, 231, 0.7); //颜色透明
  background-clip: text; //规定背景的绘制区域在文字上
  animation: shining 3s linear infinite;
}
@keyframes shining {
  from {
    background-position: -500%; //背景图像的起始位置
  }
  to {
    background-position: 500%; //背景图像的结束位置
  }
}
.video_title {
  text-align: center;
  font-size: 22px;
  font-weight: bold;
  letter-spacing: 3px;
}
</style>
