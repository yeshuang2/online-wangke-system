<template>
  <div>
    <el-table
      ref="questionTable"
      highlight-current-row
      v-loading="loading"
      :border="true"
      :data="HangZhangTiKuDateInfo"
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
        prop="xunlianjiluTikuname"
        label="所属题库"
      >
      </el-table-column>

      <el-table-column
        align="center"
        prop="xunlianjiluFalsegeshu"
        label="错误数量"
      >
              <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluFalsegeshu ? 'red' : 'red' }">
            {{ scope.row.xunlianjiluFalsegeshu }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluTruegeshu"
        label="正确数量"
      >
      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluZonggeshu"
        label="总数量"
      >
        <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluZonggeshu ? '#8c90b9' : '#8c90b9' }">
            {{ scope.row.xunlianjiluZonggeshu }}
          </div>
        </template>


      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluGeshuzhengquelv"
        label="正确率"
      >
        <template slot-scope="scope">
          <span :style="classObje(scope.row.xunlianjiluGeshuzhengquelv.slice(0, -1))">
            <i
              :class="
                scope.row.xunlianjiluGeshuzhengquelv.slice(0, -1) < 60 ? 'el-icon-bottom' : 'el-icon-top'
              "
            ></i>
            {{ scope.row.xunlianjiluGeshuzhengquelv }}
          </span>
        </template>
      </el-table-column>
    </el-table>
      <br>  
      <br>  
  </div>
</template>

<script>
import tikuxunlianjilu from "@/api/tikuxunlianjilu"; //训练题库的js charts
export default {
  data() {
    return {
      HangZhangTiKuDateInfo: [],
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
      tikuxunlianjilu.gethangzhangtikuInfo().then((resp) => {
        this.HangZhangTiKuDateInfo = resp.data;
        this.loading = false;
        // console.log(resp.data);
      });
    },
  },
};
</script>

<style>
</style>