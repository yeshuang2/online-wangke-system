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
        text: "我的视频时长趋势图",
        subtextStyle: {
          left: "center", //主副标题的水平位置
          top: "center", //主副标题的垂直位置
        },
        x: "left",
        top: 2,
        left: 410,
        textStyle: {
          fontSize: 17,
          color: "#afc8ba",
        },
      },
      legend: {
        data: ["单次播放时长", "视频播放累积时长", "视频总时长"],
        x: "left",
        top: 2,
        left: 710,
        textStyle: {
          fontSize: 16,
          color: "#8b658b",
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
          name: "总时长",
          interval: 50,
          axisLabel: {
            formatter: "{value}",
          },
        },
        {
          type: "value",
          name: "总时长",
          interval: 50,
          axisLabel: {
            formatter: "{value}",
          },
        },
      ],
      series: [
        {
          name: "单次播放时长", //
          type: "bar",
          yAxisIndex: 1,
        itemStyle: {
						        color: '#276893'
						      },
          tooltip: {
            valueFormatter: function (value) {
              return value + " 秒";
            },
          },
          data: [],
        },

        {
          name: "视频播放累积时长",
          type: "bar", //bar
          smooth: true,
          yAxisIndex: 1,
                  itemStyle: {
						        color: '#25446b'
						      },
          tooltip: {
            valueFormatter: function (value) {
              return value + " 秒";
            },
          },
          data: [],
        },

        {
          name: "视频总时长",
          type: "bar", //bar
          smooth: true,
          yAxisIndex: 1,
                         itemStyle: {
						        color: '#7a988b'
						      },
          tooltip: {
            valueFormatter: function (value) {
              return value + " 秒";
            },
          },
          data: [],
        },
      ],
    };

    this.getTiKuXunLianEchartsInfo.xunlianjiluUsername =
      localStorage.getItem("username");
    tikuxunlianjilu
      .getQuestionMovesLieJiInfo(this.getTiKuXunLianEchartsInfo)
      .then((response) => {
        response.data.forEach((item) => {
          option.series[0].data.push(item.study_shichang); //时长
          option.series[1].data.push(item.study_leijishichang); //累积时长
          option.series[2].data.push(item.study_totalshichang); //总时长
          option.xAxis[0].data.push(item.study_yewu); //时间
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