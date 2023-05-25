<template>
  <div>
    <el-table
      ref="questionTable"
      highlight-current-row
      v-loading="loading"
      :border="true"
      :cell-style="tableRowClassName"
      :data="SuoShuBuMenQingKunInfo"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column align="center" prop="zhihangvalue" label="所属支行">
      </el-table-column>

      <el-table-column align="center" prop="username" label="姓名">
      </el-table-column>

      <el-table-column align="center" prop="threevalue" label="职位">
      </el-table-column>

      <el-table-column align="center" prop="studyYewu" label="所属业务">
      </el-table-column>

      <el-table-column align="center" label="总分数值">
        <template slot-scope="scope">
          <span :style="classObje(scope.row.chulimove + scope.row.chulitiku)">
            <i
              :class="
                scope.row.chulimove + scope.row.chulitiku < 60
                  ? 'el-icon-bottom'
                  : 'el-icon-top'
              "
            ></i>
            {{ scope.row.chulimove + scope.row.chulitiku }}
          </span>
        </template>
      </el-table-column>

      <el-table-column align="center" prop="rank" label="排名">
      </el-table-column>
    </el-table>

    <br />
    <br />
  </div>
</template>

<script>
import tikuxunlianjilu from "@/api/tikuxunlianjilu"; //训练题库的js charts
export default {
  data() {
    return {
      SuoShuBuMenQingKunInfo: [],
      loading: true,
      getALLSuoShuBuMenQingKunInfo: {
        xunlianjiluUsername: localStorage.getItem("username"),
      },
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
    //已签订的合同，这一行蓝色字体显示，已作废的合同这一行红色字体显示
    tableRowClassName({ row }) {
      return row.username == localStorage.getItem("username")
        ? "color: red;"
        : "color: black;";
    },

    getbumenInFo() {
      this.getALLSuoShuBuMenQingKunInfo.xunlianjiluUsername =
        localStorage.getItem("username");
      console.log(this.getALLSuoShuBuMenQingKunInfo);
      tikuxunlianjilu
        .getSuoShuBuMenQingKunInfo(this.getALLSuoShuBuMenQingKunInfo)
        .then((resp) => {
          this.SuoShuBuMenQingKunInfo = resp.data;
          this.loading = false;
          console.log(resp.data);
        });
    },
  },
};
</script>

<style>
</style>