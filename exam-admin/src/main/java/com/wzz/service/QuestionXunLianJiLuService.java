package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.Question;
import com.wzz.entity.QuestionXunLianJiLu;
import com.wzz.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 15:35
 **/
public interface QuestionXunLianJiLuService   extends IService<QuestionXunLianJiLu> {


    /**
     * @param xunlianjiluUsername 登录用户信息数据进行查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageResponse<QuestionXunLianJiLuVo> getQuestionXunLianJiLu(String xunlianjiluUsername, Integer pageNo, Integer pageSize);

    /**
     * @return 统计学生个人学习题库 训练系统成绩的报表
     */
    List<Map> Echartstikunxunlianlist(String xunlianjiluUsername);

    /**
     * @param xunlianjiluUsername 用户名称
     * @return  统计学习个人视频累积时长
     */
    List<Map> EchartsEchartsMovesLeiJilist(String xunlianjiluUsername);


    /**
     * @return 部门经理查询学生题库数据信息
     */
    List<BuMengsshujuVo> EchartsBuMengJingLinInfolist();



    /**
     * @return 部门经理查询学生网课的学习情况数据信息
     */
    List<BuMengWangKeVo> tableBuMengJingLinWangKeInfolist();

    /**
     * @return 行长查看所有题库记录的信息
     */
    List<BuMengsshujuVo> TablesHangZhangJingLinTiKuInfolist();


    /**
     * @return  行长查看所有网课记录的信息
     */
    List<BuMengWangKeVo> TablesHangZhangJingLinWangKeInfolist();


    /**
     * @param zhihangvalue  支行为一个团体
     * @return
     *    学生会以一个部门或支行为一个团体，学生可以在此查看个人学习情况在所属部门的情况
     *
     */
    List<BuMengsshujuVo> TablesSuoShuBuMenQingKunInfolist(String  zhihangvalue);


    /**
     * @return  部门情况-======团队排行榜 没有汇总
     */
    List<BuMengsshujuVo> TablesTuanDuioPaiHangBangInfolist();


    /**
     * @return  部门情况-======团队排行榜 有汇总技术
     */
    List<BuMengsshujuVo> TablesTuanDuioPaiHangBangHuiZongInfolist();


    /**
     * @return 学习发展曲线表
     */
    List<QuestionXunLianJiLu> TablesAllStudyFaZhanQuXianDataInfolist();



    /**
     * @return  查询所有== 曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
     */
    List<StudyXunLianVo> TablesAllStudyFuzhiFiveLvDataInfolist();


    /**
     * @param studyUsername  根据当前用户名称去程序数据信息
     * @return 曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
     */
    List<StudyXunLianVo>  SelectUserNameAllStudyFuzhiFiveLvDataInfolist(String studyUsername);



    /**
     * @return 结束时间 以这个来记录周 数据（所有的数据 学生进行50%的 计算 给管理员进行查看）
     */
    List<StudyXunLianVo> OneWeekShiXunTuEcharts();




    /**
     * @return 查看是否看完视频状态
     */
    List<StudyDurationVo> MovesZT(String studyUsername);
}
