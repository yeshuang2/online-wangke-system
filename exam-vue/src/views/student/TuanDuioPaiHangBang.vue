<template>
  <div>
    <!--  有汇总 -->
    <br>
        <span class="spatitle">支行汇总排行榜</span>
    &emsp;
            <span></span>
    <el-table
    title="支行汇总排行榜"
      ref="questionTable"
      highlight-current-row
      v-loading="loadinghuizong"
      :border="true"
      :data="TuanDuioPaiHangBangHuiZongDateInfo"
      tooltip-effect="dark"
      style="width: 100%"
    >

      <el-table-column align="center" prop="zhihangvalue" label="所属支行">
      </el-table-column>

      <el-table-column
        align="center"
        prop="xunlianjiluFalsegeshu"
        label="错误的总数"
      >
              <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluFalsegeshu ? 'red' : 'red' }">
            {{ scope.row.xunlianjiluFalsegeshu.slice(0, -2) }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluTruegeshu"
        label="正确的总数"
      >
       <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluTruegeshu ? 'black' : 'black' }">
            {{ scope.row.xunlianjiluTruegeshu.slice(0, -2) }}
          </div>
        </template>

      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluZonggeshu"
        label="总题库训练的数量"
      >
        <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluZonggeshu ? '#8c90b9' : '#8c90b9' }">
            {{ scope.row.xunlianjiluZonggeshu.slice(0, -2) }}
          </div>
        </template>


      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluGeshuzhengquelv"
        label="总正确率"
      >
        <template slot-scope="scope">
          <span :style="classObje(scope.row.xunlianjiluGeshuzhengquelv)">
            <i
              :class="
                scope.row.xunlianjiluGeshuzhengquelv< 60 ? 'el-icon-bottom' : 'el-icon-top'
              "
            ></i>
            {{ scope.row.xunlianjiluGeshuzhengquelv }}%
          </span>
        </template>
      </el-table-column>
    </el-table>
    <br>    <br>  

    <!-- 没有进行汇总 -->
    <span class="spatitle">各支行排行榜</span>
&emsp;
    <el-table
      ref="questionTable"
        title="各支行排行榜"
      highlight-current-row
      v-loading="loading"
      :border="true"
      :data="TuanDuioPaiHangBangDateInfo"
      tooltip-effect="dark"
      style="width: 100%"
    >

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
        label="错误的总数"
      >
              <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluFalsegeshu ? 'red' : 'red' }">
            {{ scope.row.xunlianjiluFalsegeshu.slice(0, -2) }}
          </div>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluTruegeshu"
        label="正确的总数"
      >
       <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluTruegeshu ? 'black' : 'black' }">
            {{ scope.row.xunlianjiluTruegeshu.slice(0, -2) }}
          </div>
        </template>

      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluZonggeshu"
        label="总题库训练的数量"
      >
        <template slot-scope="scope">
          <div :style="{ color: scope.row.xunlianjiluZonggeshu ? '#8c90b9' : '#8c90b9' }">
            {{ scope.row.xunlianjiluZonggeshu.slice(0, -2) }}
          </div>
        </template>


      </el-table-column>
      <el-table-column
        align="center"
        prop="xunlianjiluGeshuzhengquelv"
        label="总正确率"
      >
        <template slot-scope="scope">
          <span :style="classObje(scope.row.xunlianjiluGeshuzhengquelv)">
            <i
              :class="
                scope.row.xunlianjiluGeshuzhengquelv< 60 ? 'el-icon-bottom' : 'el-icon-top'
              "
            ></i>
            {{ scope.row.xunlianjiluGeshuzhengquelv }}%
          </span>
        </template>
      </el-table-column>
    </el-table>
    
    <br>    <br>  
  </div>
</template>

<script>
import tikuxunlianjilu from "@/api/tikuxunlianjilu"; //训练题库的js charts
export default {
  data() {
    return {
      TuanDuioPaiHangBangDateInfo: [],  // 没有进行计算
      TuanDuioPaiHangBangHuiZongDateInfo:[],  //汇总的计算 3个进行分组
      loading: true,
          loadinghuizong: true,      //汇总
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
    this.getTuanDuioPaiHangBangInFo(); // 没有汇总单
    this.getTuanDuioPaiHangBangHuiZongInFo() //汇总3个支行
  },
  methods: {
    getTuanDuioPaiHangBangInFo() {
      tikuxunlianjilu.getTuanDuioPaiHangBangInfo().then((resp) => {
        this.TuanDuioPaiHangBangDateInfo = resp.data;
        this.loading = false;
        // console.log(resp.data);
      });
    },

        getTuanDuioPaiHangBangHuiZongInFo() {
      tikuxunlianjilu.getTuanDuioPaiHangBangHuiZongInfo().then((resp) => {
        this.TuanDuioPaiHangBangHuiZongDateInfo = resp.data;
        this.loadinghuizong = false;
        // console.log(resp.data);
      });
    },
  },
};
</script>

<style scoped lang="scss">
.spatitle{
  color:rgb(50, 23, 204);
  font-weight: bold;
  font-size: 19px;
  margin-left: 780px;
  margin-bottom: 40px;
}
</style>