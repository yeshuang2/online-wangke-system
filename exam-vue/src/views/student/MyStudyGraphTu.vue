<template>
  <div>
    <div ref="Dom" class="BOX" style="width: 1500px; height: 500px"></div>
  </div>
</template>

<script>
import tikuxunlianjilu from "@/api/tikuxunlianjilu"; //训练题库的js charts
export default {
  data() {
    return {
      getTiKuXunLianEchartsInfo: {
        xunlianjiluUsername: null,
      },
    };
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
        text: "我的学习题库训练趋势图",
        subtextStyle: {
          left: "center", //主副标题的水平位置
          top: "center", //主副标题的垂直位置
        },
        x: "left",
        top: 2,
        left: 410,
        textStyle: {
          fontSize: 16,
          color: "Black",
        },
      },
      legend: {
        data: ["错误个数", "正确个数", "总训练题数量"],
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
        },
      ],
      yAxis: [
        {
          type: "value",
          name: "总个数",
          interval: 1,
          axisLabel: {
            formatter: "{value}",
          },
        },
        {
          type: "value",
          name: "正确率",
          min: 0,
          interval: 10,
          axisLabel: {
            formatter: "{value}%",
          },
        },
      ],
      series: [
        {
          name: "错误个数",
          type: "bar",
          tooltip: {
            valueFormatter: function (value) {
              return value + "个";
            },
          },
          data: [],
        },
        {
          name: "正确个数",
          type: "bar", //
          tooltip: {
            valueFormatter: function (value) {
              return value + "个";
            },
          },
          data: [],
        },
        {
          name: "总训练题数量", //
          type: "bar",
          tooltip: {
            valueFormatter: function (value) {
              return value + "个";
            },
          },
          data: [],
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

    this.getTiKuXunLianEchartsInfo.xunlianjiluUsername =
      localStorage.getItem("username");
    tikuxunlianjilu
      .getQuestionXunLianJiLuInfo(this.getTiKuXunLianEchartsInfo)
      .then((response) => {
        response.data.forEach((item) => {
          option.series[0].data.push(item.xunlianjilu_falsegeshu); //错误
          option.series[1].data.push(item.xunlianjilu_truegeshu); //正确数量
          option.series[2].data.push(item.xunlianjilu_zonggeshu); //总数量
          option.series[3].data.push(
            item.xunlianjilu_geshuzhengquelv.slice(0, -1)
          ); //正确率
          console.log(item.xunlianjilu_geshuzhengquelv.slice(0, -1));
          //   // option.series[1].data.push({ value: item.JF }); //这个对象的写法
          option.xAxis[0].data.push(item.xunlianjilu_tikuname); //时间
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