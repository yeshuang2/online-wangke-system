<template>
  <div>
    <el-table
      ref="questionTable"
      highlight-current-row
      v-loading="loading"
      :border="true"
      :data="BuMenWangKeDateInfo"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column align="center" prop="username" label="用户名称">
      </el-table-column>

      <el-table-column align="center" prop="threevalue" label="所属部门">
      </el-table-column>

      <el-table-column align="center" prop="zhihangvalue" label="所属支行">
      </el-table-column>

      <el-table-column
        align="center"
        prop="studyYewu"
        label="所属业务"
      >
      </el-table-column>

      <el-table-column
        align="center"
        prop="studyWangkename"
        label="网课视频名称"
      >
              <template slot-scope="scope">
          <div :style="{ color: scope.row.studyWangkename ? 'blue' : 'blue' }">
            {{ scope.row.studyWangkename }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="studyStarttime"
        label="开始时间"
      >
      </el-table-column>
      <el-table-column
        align="center"
        prop="studyShichang"
        label="单次时长"
      >
        <template slot-scope="scope">
          <div :style="{ color: scope.row.studyShichang ? '#8c90b9' : '#8c90b9' }">
            {{ scope.row.studyShichang }}
          </div>
        </template>


      </el-table-column>
      <el-table-column
        align="center"
        prop="studyLeijishichang"
        label="累积时长"
      >

      </el-table-column>

      <el-table-column
        align="center"
        prop="studyTotalshichang"
        label="总时长"
      >
      </el-table-column>
    </el-table>
    
    <br>  <br>  
  </div>
</template>

<script>
import tikuxunlianjilu from "@/api/tikuxunlianjilu"; //训练题库的js charts
export default {
  data() {
    return {
      BuMenWangKeDateInfo: [],
      loading: true,
    };
  },
  computed: {
    classObje(totalGrade) {
      return (totalGrade) => {
        if (totalGrade >= 60) {
          return { color: "red" };
        } else if (totalGrade === 0) {
          return { color: "#000000" };
        } else if (totalGrade < 60) {
          return { color: "#13ce66" };
        }
      };
    },
  },

  created() {
    this.getbumenInFo(); // 获取部门的经理下的数据 也包含经理的数据
  },
  methods: {
    getbumenInFo() {
      tikuxunlianjilu.getbumenwabgkeshujuiInfo().then((resp) => {
        this.BuMenWangKeDateInfo = resp.data;
        this.loading = false;
        // console.log(resp.data);
      });
    },
  },
};
</script>

<style scoped lang="scss">
</style>