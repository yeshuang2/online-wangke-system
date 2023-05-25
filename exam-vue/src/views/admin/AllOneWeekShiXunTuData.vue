<template>
  <div>
    <div ref="Dom" class="BOX" style="width: 1700px; height: 600px"></div>
  </div>
</template>

<script>
import tikuxunlianjilu from "@/api/tikuxunlianjilu"; //训练题库的js charts
export default {
  data() {
    return {};
  },
  //初始化进入数据渲染
  mounted() {
    //获取Dom节点
    let chartDom = this.$refs.Dom;
    //初始化echarts实例
    let myChart = this.$echarts.init(chartDom);
    //绘制图标
    let option;
    //数据
    option = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "cross",
          crossStyle: {
            color: "pink",
          },
        },
      },
      toolbox: {
        feature: {
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ["line", "bar"] },
          restore: { show: true },
          saveAsImage: { show: true },
        },
      },
      title: {
        text: "学生周数据成绩趋势图",
        subtextStyle: {
          left: "center", //主副标题的水平位置
          top: "center", //主副标题的垂直位置
        },
        x: "left",
        top: 2,
        left: 410,
        textStyle: {
          fontSize: 17,
          color: "#4e1892",
        },
      },
      legend: {
        data: ["网课成绩", "题库成绩", "合计总成绩"],
        x: "left",
        top: 2,
        left: 710,
        textStyle: {
          fontSize: 16,
          color: "#0069f9",
        },
      },
      xAxis: [
        {
          type: "category",
          data: [],
          axisPointer: {
            type: "shadow",
          },
          axisLabel: {
            fontSize: 9,
            color: "#3c5e91",
          },
        },
      ],
      yAxis: [
        {
          type: "value",
          name: "总分数",
          interval: 10,
          axisLabel: {
            formatter: "{value}",
          },
        },
        {
          type: "value",
          name: "正确率",
          interval: 1,
          axisLabel: {
            formatter: "{value}%",
          },
        },
      ],
      series: [
        {
          name: "网课成绩",
          type: "bar",
          tooltip: {
            valueFormatter: function (value) {
              return value + "分";
            },
          },
          data: [],
          itemStyle: {
            normal: {
              color: "#c1a299",
            }, 
          },
        },
        {
          name: "题库成绩",
          type: "bar", //
          tooltip: {
            valueFormatter: function (value) {
              return value + "分";
            },
          },
          data: [],
          axisLabel: {
            fontSize: 9,
            color: "#7991d1",
          },
        },
        {
          name: "合计总成绩", //
          type: "bar",
          tooltip: {
            valueFormatter: function (value) {
              return value + "分";
            },
          },
          data: [],
          itemStyle: {
            normal: {
              color: "#b80233",
            }, 
          },
        },

        {
          name: "正确率",
          type: "line", //bar
          smooth: true,
          yAxisIndex: 1,
          tooltip: {
            valueFormatter: function (value) {
              return value;
            },
          },
          data: [],
        },
      ],
    };

    tikuxunlianjilu.getAllOneWeekShiXunTuDataInfo().then((response) => {
      response.data.forEach((item) => {
        option.series[0].data.push(item.chulimove); //网课学习成绩
        option.series[1].data.push(item.chulitiku); //题库训练成绩
        option.series[2].data.push(item.chulimove + item.chulitiku); //总成绩
        //   option.series[3].data.push(
        //     item.xunlianjilu_geshuzhengquelv.slice(0, -1)
        //   ); //正确率
        //   console.log(item.xunlianjilu_geshuzhengquelv.slice(0, -1));
        //   // option.series[1].data.push({ value: item.JF }); //这个对象的写法
        option.xAxis[0].data.push(
          item.studyUsername +
            " " +
            item.studyEndtime.slice(0, -8) +
            item.xunlianjiluTikuname
        ); //时间
      });
      console.log(response.data);
      option && myChart.setOption(option);
    });
  },
};
</script>

<style scoped lang="scss">
.BOX {
  margin-top: 80px;
}
</style>