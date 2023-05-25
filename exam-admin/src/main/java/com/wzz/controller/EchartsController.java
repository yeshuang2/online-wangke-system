package com.wzz.controller;

import com.wzz.entity.QuestionXunLianJiLu;
import com.wzz.entity.User;
import com.wzz.service.QuestionXunLianJiLuService;
import com.wzz.service.UserService;
import com.wzz.utils.Result;
import com.wzz.vo.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/21 9:24
 *  //    ECHarts可视化效果图
 **/

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "ECHarts可视化效果图")
@RequestMapping(value = "/echarts")
public class EchartsController {


    @Autowired
    private QuestionXunLianJiLuService questionXunLianJiLuService;

    @Autowired
    private UserService userService;

    /**
     * @return
     * 可视化后端 统计学生个人学习题库的报表
     */
    @PostMapping("/getechartsshujutikunxunlian")
    public Result getEchartstikuInfo(@RequestParam(required = false) String xunlianjiluUsername) {
        List<Map> tikunxunlianResult = questionXunLianJiLuService.Echartstikunxunlianlist(xunlianjiluUsername);
       return Result.user_success1(200,"题库返回成功",tikunxunlianResult,null);

    }


    /**
     * @return
     * 可视化后端 统计学生个人学习视频累积时长的报表
     */
    @PostMapping("/getechartsshujumovesleijiinfo")
    public Result getEchartsMovesLeiJiInfo(@RequestParam(required = false) String xunlianjiluUsername) {
        List<Map> tikunxunlianResult = questionXunLianJiLuService.EchartsEchartsMovesLeiJilist(xunlianjiluUsername);
        return Result.user_success1(200,"视频累积返回成功",tikunxunlianResult,null);

    }

    /**
     * @return
     *    部门经理 统计部门经理下的所有的   学生个人和部门    的训练题库的数据
     */
    @GetMapping("/getbumengxiadtikuinfo")
    public Result getBumengxiadtikuinfoInfo() {
        List<BuMengsshujuVo> buMengsshujuVos = questionXunLianJiLuService.EchartsBuMengJingLinInfolist();
        return Result.user_success1(200,"部门经理题库--返回成功",buMengsshujuVos,null);

    }


    /**
     * @return
     *    部门经理 统计部门经理下的所有的   学生个人和部门    的训练网课的数据
     */
    @GetMapping("/getbumengxiadwangkeinfo")
    public Result getBumengxiadwangkeInfo() {
        List<BuMengWangKeVo> buMengsshujuVos = questionXunLianJiLuService.tableBuMengJingLinWangKeInfolist();
        return Result.user_success1(200,"部门经理网课--返回成功",buMengsshujuVos,null);

    }


    /**
     * @return
     *   行长统计所有的   学生个人和部门    的训练网课的数据
     */
    @GetMapping("/gethangzhangtikuinfo")
    public Result gethangzhangAllTiKuInfo() {
        List<BuMengsshujuVo> buMengsshujuVos = questionXunLianJiLuService.TablesHangZhangJingLinTiKuInfolist();
        return Result.user_success1(200,"行长网课--返回成功",buMengsshujuVos,null);

    }


    /**
     * @return
     *    行长 统计所有的   学生个人和部门    的训练网课的数据
     */
    @GetMapping("/gethangzhangwangkeinfo")
    public Result getHangZhangJingLinWangKeAllInfo() {
        List<BuMengWangKeVo> buMengsshujuVos = questionXunLianJiLuService.TablesHangZhangJingLinWangKeInfolist();
        return Result.user_success1(200,"行长网课--返回成功",buMengsshujuVos,null);

    }
        // 部门情况
        // 学生会以一个部门或支行为一个团体，学生可以在此查看个人学习情况在所属部门的情况，包括团体排行榜。
        // 同时也可以申请选择目标进行详细成绩上的二人PK比赛
    /**
     * @param xunlianjiluUsername  当前登录的用户名称 进行查询 个人所属的支行部门
     * @return
     *      学生可以在此查看个人学习情况在所属部门的情况
     *
     */
    @PostMapping("/getsuoshubumenqingkundatainfo")
    public Result getSuoShuBuMenQingKunInfo(@RequestParam(required = false) String xunlianjiluUsername) {
        User userByUsername = userService.getUserByUsername(xunlianjiluUsername);
        System.out.println("当前用户登录的所属的支行 "+userByUsername.getZhihangvalue());
        List<BuMengsshujuVo> buMengsshujuVos = questionXunLianJiLuService.TablesSuoShuBuMenQingKunInfolist(userByUsername.getZhihangvalue());
        return Result.user_success1(200,"所属部门的情况--返回成功",buMengsshujuVos,null);
    }

    /**
     * @return
     *   通过行长支行--包括-------------没有团体排行榜。
     */
    @GetMapping("/gettuanduipaihangbangdatainfo")
    public Result getTuanDuioPaiHangBangInfo() {
        List<BuMengsshujuVo> buMengsshujuVos = questionXunLianJiLuService.TablesTuanDuioPaiHangBangInfolist();
        return Result.user_success1(200,"所属部门的情况--团体排行榜返回成功",buMengsshujuVos,null);
    }


    /**
     * @return
     *   通过行长支行--包括        有 3种类型进行 团体排行榜。
     */
    @GetMapping("/gettuanduipaihangbanghuizhongdatainfo")
    public Result getTuanDuioPaiHangBangHuiZongInfo() {
        List<BuMengsshujuVo> PaiHangBangHuiZongVos = questionXunLianJiLuService.TablesTuanDuioPaiHangBangHuiZongInfolist();
        return Result.user_success1(200,"所属部门的情况--团体排行榜返回成功",PaiHangBangHuiZongVos,null);
    }


    /**
     * @return
     *   管理员能够直接看到所有的学生信息和学习情况，此处学习情况反应为三张表，
     *   学习发展曲线表。
     */
    @GetMapping("/getallstudyfazhanquxiantudatainfo")
    public Result getAllStudyFaZhanQuXianDataInfo() {
        List<QuestionXunLianJiLu> FaZhanQuXianVos = questionXunLianJiLuService.TablesAllStudyFaZhanQuXianDataInfolist();
        return Result.user_success1(200,"学习发展曲线表返回成功",FaZhanQuXianVos,null);
    }



    /**
     * @return
     *   曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
     *   返回所有的数据
     */
    @GetMapping("/getallstudyfuzhifivelvndatainfo")
    public Result getAllStudyFuzhiFiveLvnDataInfo() {
        List<StudyXunLianVo> FaZhanQuXianVos = questionXunLianJiLuService.TablesAllStudyFuzhiFiveLvDataInfolist();
        return Result.user_success1(200,"返回all网课刷题各占50%--返回成功",FaZhanQuXianVos,null);
    }


    /**
     * @param studyUsername  通过当前学生登录的名称
     * @return   曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
     */
    @PostMapping("/getselectusernameallstudyfuzhifivelvndatainfo")
    public Result getSelectUserNameAllStudyFuzhiFiveLvnInfo(@RequestParam(required = false) String studyUsername) {
        List<StudyXunLianVo> buMengsshujuVos = questionXunLianJiLuService.SelectUserNameAllStudyFuzhiFiveLvDataInfolist(studyUsername);
        return Result.user_success1(200,"通过当前学生登录的名称网课刷题各占50%--返回成功",buMengsshujuVos,null);
    }



    /**
     * @return
     *   结束时间 以这个来记录1周数据（所有的数据 学生进行50%的 计算 给管理员进行查看）
     *   返回所有的数据
     */
    @GetMapping("/getoneweekshixuntudatainfo")
    public Result getAllOneWeekShiXunTuEchartsDataInfo() {
        List<StudyXunLianVo> oneWeekShiXunTuEchartsVos = questionXunLianJiLuService.OneWeekShiXunTuEcharts();
        return Result.user_success1(200,"记录1周数据--返回成功",oneWeekShiXunTuEchartsVos,null);
    }



    /**
     * @return
     *   结束时间 以这个来记录1周数据（所有的数据 学生进行50%的 计算 给管理员进行查看）
     *   返回所有的数据
     */
    @GetMapping("/getoneMovesZTinfo")
    public Result getAllOneMovesZTDataInfo(@RequestParam(required = false) String studyUsername) {
        List<StudyDurationVo> oneMovesZtVos = questionXunLianJiLuService.MovesZT(studyUsername);
        return Result.user_success1(200,"记录1周数据--返回成功",oneMovesZtVos,null);
    }





}
