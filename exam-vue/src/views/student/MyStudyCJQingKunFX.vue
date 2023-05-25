<template>
  <div>
    <el-table
      ref="questionTable"
      highlight-current-row
      v-loading="loading"
      :border="true"
      :data="gengreStudyFuzhiFiveLvnData"
      tooltip-effect="dark"
      style="width: 100%"
    >
      <el-table-column align="center" prop="studyUsername" label="用户名称">
      </el-table-column>

      <el-table-column align="center" prop="studyYewu" label="网课所属业务">
      </el-table-column>

      <el-table-column align="center" prop="studyWangkename" label="网课名称">
      </el-table-column>

      <el-table-column align="center" prop="cjmovebili" label="之前分数">
        <template slot-scope="scope">
          <span :style="classObje(scope.row.cjmovebili)">
            <i
              :class="
                scope.row.cjmovebili < 60 ? 'el-icon-bottom' : 'el-icon-top'
              "
            ></i>
            {{ scope.row.cjmovebili }}
          </span>
        </template>
      </el-table-column>

      <el-table-column align="center" prop="chulimove" label="处理后分数">
        <template slot-scope="scope">
          <span :style="classObje1(scope.row.chulimove)">
            <i
              :class="
                scope.row.chulimove < 25 ? 'el-icon-bottom' : 'el-icon-top'
              "
            ></i>
            {{ scope.row.chulimove }}
          </span>
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        prop="xunlianjiluTikuname"
        label="题库业务"
      >
      </el-table-column>

      <el-table-column
        align="center"
        prop="xunlianjiluGeshuzhengquelv"
        label="之前正确率"
      >
        <template slot-scope="scope">
          <span
            :style="
              classObje(scope.row.xunlianjiluGeshuzhengquelv.slice(0, -1))
            "
          >
            <i
              :class="
                scope.row.xunlianjiluGeshuzhengquelv.slice(0, -1) < 60
                  ? 'el-icon-bottom'
                  : 'el-icon-top'
              "
            ></i>
            {{ scope.row.xunlianjiluGeshuzhengquelv }}
          </span>
        </template>
      </el-table-column>

      <el-table-column align="center" prop="chulitiku" label="处理后的分数值">
        <template slot-scope="scope">
          <span :style="classObje1(scope.row.chulitiku)">
            <i
              :class="
                scope.row.chulitiku < 25 ? 'el-icon-bottom' : 'el-icon-top'
              "
            ></i>
            {{ scope.row.chulitiku }}
          </span>
        </template>

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
              getTiKuXunLianEchartsInfo: {
        studyUsername: null,
      },
      gengreStudyFuzhiFiveLvnData: [],
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

    classObje1(totalGrade) {
      return (totalGrade) => {
        if (totalGrade >= 25) {
          return { color: "red" };
        } else if (totalGrade === 0) {
          return { color: "#000000" };
        } else if (totalGrade < 25) {
          return { color: "#13ce66" };
        }
      };
    },
    jshu(values) {
      console.log(values);
    },
  },

  created() {
          this.getTiKuXunLianEchartsInfo.studyUsername =
      localStorage.getItem("username");
    this.getbumenInFo(); // 获取部门的经理下的数据 也包含经理的数据
  },
  methods: {
    getbumenInFo() {
      tikuxunlianjilu.SelectUserNameAllStudyFuzhiFiveLvnInfo(this.getTiKuXunLianEchartsInfo).then((resp) => {
        this.gengreStudyFuzhiFiveLvnData = resp.data;
        this.loading = false;
        // console.log(resp.data);
      });
    },
  },
};
</script>

<style scoped lang="scss">
</style>