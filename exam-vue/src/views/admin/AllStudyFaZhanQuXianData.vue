<template>
  <div>
    <div>
      <div ref="Dom" class="BOX" style="width: 1500px; height: 500px"></div>
    </div>
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
    var app = {};
    //获取Dom节点
    let chartDom = this.$refs.Dom;
    //初始化echarts实例
    let myChart = this.$echarts.init(chartDom);
    //绘制图标
    let option;
    //数据
    const posList = [
      "left",
      "right",
      "top",
      "bottom",
      "inside",
      "insideTop",
      "insideLeft",
      "insideRight",
      "insideBottom",
      "insideTopLeft",
      "insideTopRight",
      "insideBottomLeft",
      "insideBottomRight",
    ];
    app.configParameters = {
      rotate: {
        min: -90,
        max: 90,
      },
      align: {
        options: {
          left: "left",
          center: "center",
          right: "right",
        },
      },
      verticalAlign: {
        options: {
          top: "top",
          middle: "middle",
          bottom: "bottom",
        },
      },
      position: {
        options: posList.reduce(function (map, pos) {
          map[pos] = pos;
          return map;
        }, {}),
      },
      distance: {
        min: 0,
        max: 100,
      },
    };
    app.config = {
      rotate: 90,
      align: "left",
      verticalAlign: "middle",
      position: "insideBottom",
      distance: 15,
      onChange: function () {
        const labelOption = {
          rotate: app.config.rotate,
          align: app.config.align,
          verticalAlign: app.config.verticalAlign,
          position: app.config.position,
          distance: app.config.distance,
        };
        myChart.setOption({
          series: [
            {
              label: labelOption,
            },
            {
              label: labelOption,
            },
            // {
            //   label: labelOption,
            // },
            // {
            //   label: labelOption,
            // },
          ],
        });
      },
    };
    const labelOption = {
      show: true,
      position: app.config.position,
      distance: app.config.distance,
      align: app.config.align,
      verticalAlign: app.config.verticalAlign,
      rotate: app.config.rotate,
      formatter: "{c}  {name|{a}}",
      fontSize: 17,
      rich: {
        name: {},
      },
    };

    option = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "shadow",
        },
      },
        title: {
        text: "学习发展曲线表",
        subtextStyle: {
          left: "center", //主副标题的水平位置
          top: "center", //主副标题的垂直位置
        },
        x: "left",
        top: 2,
        left: 710,
        textStyle: {
          fontSize: 17,
          color: "#276893",
        },
      },
      legend: {
        data: [],
      },
      toolbox: {
        show: true,
        orient: "vertical",
        left: "right",
        top: "center",
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ["line", "bar", "stack"] },
          restore: { show: true },
          saveAsImage: { show: true },
        },
      },
      xAxis: [
        {
          type: "category",
          axisTick: { show: false },
          data: [],
           axisLabel: {
          fontSize:9,
          color: "#005b5a",
        },
        },
      ],
      yAxis: [
        {
          type: "value",
              axisLabel: {
            formatter: "{value}%",
          },
        },
      ],
      series: [
        {
          name: "正确率",
          type: "bar",
          barGap: 0,
          label: labelOption,
          emphasis: {
            focus: "series",
          },
          data: [],
        },
        {
          name: "当前学生",
          type: "bar",
                  barGap: 0,
          label: labelOption,
          emphasis: {
            focus: "series",
          },
          data: [],
        },
      ],
    };

        tikuxunlianjilu
      .getStudyFaZhanQuXianDataInfo()
      .then((response) => {
        response.data.forEach((item) => {
         option.legend.data.push(item.xunlianjiluUsername)
          option.series[0].data.push(item.xunlianjiluGeshuzhengquelv.slice(0, -1)); //时长
          option.series[1].data.push(item.xunlianjiluUsername); //时长
         option.xAxis[0].data.push(item.xunlianjiluTikuname); //
        });
        // console.log(response.data);
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