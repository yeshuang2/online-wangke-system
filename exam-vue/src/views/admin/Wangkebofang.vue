<template>
  <div class="release_wrap">
    <!-- <div class="release_title">业 务 视 频</div> -->

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
          width="145"
        >
        </el-table-column>


        <el-table-column align="center" prop="moveNameOld" label="上传视频名称">
        </el-table-column>
        <el-table-column align="center" prop="moveName" label="上传后视频名称">
        </el-table-column>
        <!-- <el-table-column align="center" prop="moveUrl" label="视频路径">
        </el-table-column> -->
        <!-- <el-table-column align="center" prop="moveStart" label="视频开始时间">
        </el-table-column>
        <el-table-column align="center" prop="moveEnd" label="视频结束时间">
        </el-table-column> -->
        <!-- prop="moveTimejilu" -->
        <!-- <el-table-column align="center" v-model="zongshichang" label="视频总时长" width="105">
          <template >
       {{ this.zongshichang }}
          </template>
        </el-table-column> -->

        <el-table-column
          align="center"
          prop="moveUrl"
          label="视频路径地址"
          width="155"
        >
        </el-table-column>

        <!-- <el-table-column align="center" label="角色">
          <template slot-scope="scope">
            <span class="role" v-show="scope.row.roleId === 3">超级管理员</span>
            <span class="role" v-show="scope.row.roleId === 2">教师</span>
            <span class="role" v-show="scope.row.roleId === 1">学生</span>
          </template>
        </el-table-column> -->

        <el-table-column align="center" label="创建时间" width="185">
          <template slot-scope="scope">
            {{ scope.row.createTime }}
          </template>
        </el-table-column>

        <!-- <el-table-column align="center" label="状态">
          <template slot-scope="scope">
            {{ scope.row.status === 1 ? "正常" : "待审核" }}
          </template>
        </el-table-column> -->

        <!-- 操作 -->
        <el-table-column label="操作" width="">
          <template slot-scope="scope">
            <el-button
              v-show="scope.row.roleId !== 3"
              size="mini"
              type="primary"
              @click="handleEdit(scope.$index, scope.row)"
              >编辑</el-button
            >
            <el-button
              v-show="scope.row.roleId !== 3"
              size="mini"
              type="info"
              @click="playVideo(scope.$index, scope.row)"
              >查看</el-button
            >

            <el-button
              v-show="scope.row.roleId !== 3"
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

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


       <!-- 修改视频信息 -->
    <el-dialog
      title="修改视频"
      :visible.sync="updateMovesTableVisible"
      width="30%"
      center
    >
   <el-form :model="updatemoveForm" ref="updatemoveForm">
    <!--操作的下拉框-->
    <!-- <el-form-item label="网课类别">
      <el-select
        @change="selectChange"
        clearable
        v-model="movesselected"
        style="margin-bottom: 25px"
      >
        <el-option
          v-for="(item, index) in movesoptionInfo"
          :key="index"
          :value="item.label"
        >
          <span style="float: left">{{ item.label }}</span>
        </el-option>
      </el-select>
    </el-form-item> -->
          <el-form-item label="选择网课类别" label-width="120px">
            <el-select v-model="movesselected"
            placeholder="请选择网课类别">
              <el-option
                v-for="item in allMovesBank"
                :key="item.wangkeid"
                :label="item.wangkeName"
                :value="item.wangkeName">
              </el-option>
            </el-select>
          </el-form-item>
    <!-- 视频更新 -->
        <el-form-item label="更新视频">
      <el-upload
        class="avatar-uploader el-upload--text"
        :drag="Plus"
        action="http://localhost:8888/api/updateuploadVideo"
         multiple
        :show-file-list="false"
        :data="{ 
                 SavePath: this.Path.url,
                 moveCategorize: this.movesselected,
                  movesid: this.moveId
         }"
        :on-success="handleVideoSuccess"
        :before-upload="beforeUploadVideo"
        :on-progress="uploadVideoProcess"
      >
       <i v-if="Plus" class="el-icon-upload"></i>
                <div v-if="Plus" class="el-upload__text">
                  将文件拖到此处，或<em>点击上传</em>
                </div>
              <!-- <el-progress
                  v-if="videoFlag == true"
                  type="circle"
                  :percentage="videoUploadPercent"
                  style="margin-top: 30px"
                ></el-progress> -->
        <!-- <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div> -->
        <div class="el-upload__tip" slot="tip">
          只能上传mp4/flv/avi文件，且不超过300M
        </div>
      </el-upload>
    </el-form-item>
   </el-form>
      <!-- <div slot="footer" class="dialog-footer">
        <el-button @click="updateTableVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div> -->
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
import { mapState } from "vuex";
import wangke from '@/api/wangke'
export default {
  data() {
    return {
      title: "",
      videolist: "",
      //表格数据
      tableData: [],
      //弹框组件隐藏
      dialogVisible: false,
      updateMovesTableVisible:false, //更新视频的控件
      //用于保存视频的id moveId
      moveId: 0,
      //保存视频的名称
      moveNameOld: "",
      zongshichang: 0, //视频总时长
      //查询的参数
      queryInfo: {
        pageNo: 1,
        pageSize: 10,
      },
      total: 0,
      //表格信息加载
      loading: true,

      // 更新 视频
            //下拉框所选择的数据
      movesselected: "",
      //表格行被选中后的所有操作方式的数据
   allMovesBank: [],
       videoFlag: false,
      Plus: true,
      //上传视频时带的参数，这个地址就是后端保存磁盘的地址。可以更改。不建议放F盘，有的电脑可能没有F盘，只有C和D
      Path: {
        url: "F:/video/videoUpload",
      },
      videoUploadPercent: 0,
      customColor: "#409eff",
           updatemoveForm: {
        // status: "",
      }, //更新负责操作
    };
  },
  computed: {
    //引入vuex中state的变量，可以直接this.xxx调用到
    ...mapState(["article"]),
  },
  created() {
    this.getVideoInfo();
    // 获取网课业务类别
        wangke.getMovesBank().then((resp) => {
        if (resp.code === 200) {
          this.allMovesBank = resp.data
        }
      })
  },

  methods: {
    //   这个视频播放的时候进行暂停 获取总时长和当前播放的时间
    movesstop() {
      var videoTime = document.getElementById("myvideo");
      this.zongshichang = videoTime.duration.toFixed(0);
      console.log(videoTime.duration.toFixed(0)); //获取视频总时长
      this.dialogVisible = false;
      // 触发暂停
      this.$refs.vueMiniPlayer.pause(); //暂停
      this.currentTime = videoTime.currentTime.toFixed(0); //获取视频当前播放时间
      console.log(videoTime.currentTime.toFixed(0)); //打印播放时间
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

    // 更新网课记录
    handleEdit(i, val) {
        //显示弹框
      this.updateMovesTableVisible = true;
      // 1、先进行查询 进行更新赋值操作
      this.movesselected=val.moveCategorize
      this.moveId=val.moveid
      // console.log(val);
    },

    //获取video表格数据
    getVideoInfo() {
      moves.getMovesinfo(this.queryInfo).then((resp) => {
        this.tableData = resp.data.data;
        this.total = resp.data.total;
        this.loading = false;
        // console.log(resp);
      });
    },
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
      //   console.log(i, val);
    },

    //    删除单个视频
    handleDelete(i, val) {
      console.log(val.moveid);
      moves.deleteMovesinfo(val.moveid).then((resp) => {
        if (resp.code === 200) {
          this.$notify({
            title: "Tips",
            message: "删除成功",
            type: "success",
            duration: 2000,
          });
          this.getVideoInfo(); //重新刷新页面数据
        } else {
          this.$notify({
            title: "Tips",
            message: "删除失败",
            type: "error",
            duration: 2000,
          });
        }
      });
    },
    //功能下拉框被选择
    selectChange(val) {
      this.movesselected=val
      console.log(val);
    },
    // 视频上传前执行
    beforeUploadVideo(file) {
      //文件大小
      const isLt300M = file.size / 1024 / 1024 < 300;
      //视频后缀检查
      if (
        [ "video/mp4", "video/ogg",   "video/flv",   "video/avi",   "video/wmv",   "video/rmvb", ].indexOf(file.type) === -1
      ) {
        this.$message.error("请上传正确的视频格式");
        return false;
      }
      if (!isLt300M) {
        this.$message.error("上传视频大小不能超过300MB哦!");
        return false;
      }
    },
    // 视频上传过程中执行
    uploadVideoProcess(event, file, fileList) {
      this.Plus = false;
      this.videoFlag = true;
      this.videoUploadPercent = +file.percentage.toFixed(0);
    },
    // 视频上传成功是执行
    handleVideoSuccess(res, file) {
      this.Plus = false;
      // this.videoUploadPercent = 100;
      console.log(res);
      // 如果为200代表视频保存成功
      if (res.resCode === '200') {
        // 接收视频传回来的名称和保存地址
        this.updateMovesTableVisible=true
        // 至于怎么使用看你啦~
        // this.videoForm.videoId = res.newVidoeName;
        // this.videoForm.videoUrl = res.VideoUrl;
        this.$message.success("视频更新成功！");
          this.$router.go(0);   //重新进入页面
          //解决回调延迟到下次DOM更新循环之后执行。在修改数据之后立即使用它，然后等待DOM更新。
      } else {
        this.$message.error("视频上传失败，请重新上传！");
      }
    },

  },
};
</script>

<style  scoped lang='scss'>
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
