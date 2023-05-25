<template>
  <el-form ref="form" :model="form" label-width="80px">
    <!--操作的下拉框-->
    <!-- <el-form-item label="网课类别">
      <el-select
        @change="selectChange"
        clearable
        v-model="movesselected"
        style="margin-bottom: 25px"
      >
        <el-option
          v-for="(item, index) in allMovesBank"
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


    <el-form-item label="上传视频">
      <el-upload
        class="avatar-uploader el-upload--text"
        :drag="Plus"
        action="http://localhost:8888/api/uploadVideo"
         multiple
        :show-file-list="false"
        :data="{ 
                 SavePath: this.Path.url,
                 moveCategorize: this.movesselected
         }"
        :on-success="handleVideoSuccess"
        :before-upload="beforeUploadVideo"
        :on-progress="uploadVideoProcess"
      >
       <i v-if="Plus" class="el-icon-upload"></i>
                <div v-if="Plus" class="el-upload__text">
                  将文件拖到此处，或<em>点击上传</em>
                </div>
                <el-progress
                  v-if="videoFlag == true"
                  type="circle"
                  :percentage="videoUploadPercent"
                  style="margin-top: 30px"
                ></el-progress>

        <!-- <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div> -->
        <div class="el-upload__tip" slot="tip">
          只能上传mp4/flv/avi文件，且不超过300M
        </div>
      </el-upload>
    </el-form-item>
    <!-- <el-form-item>
      <el-button type="primary" @click="onSubmit">立即创建</el-button>
    </el-form-item> -->
  </el-form>
</template>

<script>
import wangke from '@/api/wangke'
export default {
  name: "WangkeManage",
  data() {
    return {
      // 表单数据信息
      form: {
        SavePath: null,
      },
      //视频部分
      videoForm: {
        videoId: "",
        videoUrl: "",
      },
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
    };
  },

  created(){
    wangke.getMovesBank().then((resp) => {
        if (resp.code === 200) {
          this.allMovesBank = resp.data
        }
      })
  },
  methods: {

    //数据提交
    onSubmit() {


    },

    //功能下拉框被选择
    selectChange(val) {
      this.movesselected=val
      console.log(val);
    },

    //视频部分
    // 视频上传前执行
    beforeUploadVideo(file) {
      //文件大小
      const isLt300M = file.size / 1024 / 1024 < 300;
      //视频后缀检查
      if (
        [
          "video/mp4",
          "video/ogg",
          "video/flv",
          "video/avi",
          "video/wmv",
          "video/rmvb",
        ].indexOf(file.type) === -1
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
      this.videoUploadPercent = 100;
      console.log(res);
      // 如果为200代表视频保存成功
      if (res.resCode === "200") {
        // 接收视频传回来的名称和保存地址
        // 至于怎么使用看你啦~
        this.videoForm.videoId = res.newVidoeName;
        this.videoForm.videoUrl = res.VideoUrl;
        this.$message.success("视频上传成功！");
        this.$nextTick(() => {
          //解决回调延迟到下次DOM更新循环之后执行。在修改数据之后立即使用它，然后等待DOM更新。
          this.movesselected == null;
          this.$router.go(0);   //重新进入页面
        });

      } else {
        this.$message.error("视频上传失败，请重新上传！");
      }
    },
  },
};
</script>

<style scoped lang="scss">
</style>