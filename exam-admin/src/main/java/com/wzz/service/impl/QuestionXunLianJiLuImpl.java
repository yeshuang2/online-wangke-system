package com.wzz.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.entity.QuestionXunLianJiLu;
import com.wzz.entity.StudyDuration;
import com.wzz.mapper.QuestionXunLianJiLuMapper;
import com.wzz.mapper.QuestionYeWuMapper;
import com.wzz.service.QuestionXunLianJiLuService;
import com.wzz.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wzz.utils.CommonUtils.setLikeWrapper;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/20 15:36
 *
 *  记录训练的题库数据信息
 **/
@Service
@RequiredArgsConstructor
public class QuestionXunLianJiLuImpl extends ServiceImpl<QuestionXunLianJiLuMapper, QuestionXunLianJiLu> implements QuestionXunLianJiLuService {


    private  final QuestionXunLianJiLuMapper questionXunLianJiLuMapper;

    /**
     * @param xunlianjiluUsername 登录用户信息数据进行查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageResponse<QuestionXunLianJiLuVo> getQuestionXunLianJiLu(String xunlianjiluUsername, Integer pageNo, Integer pageSize) {
        IPage<QuestionXunLianJiLu> StudyDurationPage = new Page<>(pageNo, pageSize);
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("xunlianjilu_username", xunlianjiluUsername);;

        QueryWrapper<QuestionXunLianJiLu> QuestionXunLianJiLuWwrapper = new QueryWrapper<>();
        setLikeWrapper(QuestionXunLianJiLuWwrapper, queryParams);
        QuestionXunLianJiLuWwrapper.orderByDesc("create_time");
        StudyDurationPage = questionXunLianJiLuMapper.selectPage(StudyDurationPage, QuestionXunLianJiLuWwrapper);
        List<QuestionXunLianJiLuVo> records = StudyDurationPage.getRecords().stream().map(QuestionXunLianJiLuVo::fromQuestionXunLianJiLu).collect(Collectors.toList());

        return PageResponse.<QuestionXunLianJiLuVo>builder().data(records).total(StudyDurationPage.getTotal()).build();
    }


    /**
     * @param xunlianjiluUsername  用户名称
     * @return
     * 统计学生个人学习题库 训练系统成绩的报表 正确和错误率
     *
     */
    @Override
    public List<Map> Echartstikunxunlianlist(String xunlianjiluUsername) {
        return questionXunLianJiLuMapper.Echartstikunxunlianlist(xunlianjiluUsername);
    }

    /**
     * @param xunlianjiluUsername 用户名称
     * @return
     */
    @Override
    public List<Map> EchartsEchartsMovesLeiJilist(String xunlianjiluUsername) {
        return questionXunLianJiLuMapper.EchartsEchartsMovesLeiJilist(xunlianjiluUsername);
    }

    /**
     * @return 部门经理查询学生题库数据信息
     */
    @Override
    public List<BuMengsshujuVo> EchartsBuMengJingLinInfolist() {
        return questionXunLianJiLuMapper.EchartsBuMengJingLinInfolist();
    }

    /**
     * @return 部门经理查询学生网课的学习情况数据信息
     */
    @Override
    public List<BuMengWangKeVo> tableBuMengJingLinWangKeInfolist() {
        return questionXunLianJiLuMapper.tableBuMengJingLinWangKeInfolist();
    }

    /**
     * @return 行长查看所有题库记录的信息
     */
    @Override
    public List<BuMengsshujuVo> TablesHangZhangJingLinTiKuInfolist() {
        return questionXunLianJiLuMapper.TablesHangZhangJingLinTiKuInfolist();
    }

    /**
     * @return 行长查看所有网课记录的信息
     */
    @Override
    public List<BuMengWangKeVo> TablesHangZhangJingLinWangKeInfolist() {
        return questionXunLianJiLuMapper.TablesHangZhangJingLinWangKeInfolist();
    }

    /**
     * @return 学生会以一个部门或支行为一个团体，学生可以在此查看个人学习情况在所属部门的情况
     */
    @Override
    public List<BuMengsshujuVo> TablesSuoShuBuMenQingKunInfolist(String zhihangvalue) {
        return questionXunLianJiLuMapper.TablesSuoShuBuMenQingKunInfolist(zhihangvalue);
    }

    /**
     * @return 部门情况-======团队排行榜 没有进行汇总计算
     */
    @Override
    public List<BuMengsshujuVo> TablesTuanDuioPaiHangBangInfolist() {
        return questionXunLianJiLuMapper.TablesTuanDuioPaiHangBangInfolist();
    }

    /**
     * @return 部门情况-======团队排行榜 有汇总技术
     */
    @Override
    public List<BuMengsshujuVo> TablesTuanDuioPaiHangBangHuiZongInfolist() {
        return questionXunLianJiLuMapper.TablesTuanDuioPaiHangBangHuiZongInfolist();
    }

    /**
     * @return 学习发展曲线表
     */
    @Override
    public List<QuestionXunLianJiLu> TablesAllStudyFaZhanQuXianDataInfolist() {
        return questionXunLianJiLuMapper.TablesAllStudyFaZhanQuXianDataInfolist();
    }

    /**
     * @return 查询所有== 曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
     */
    @Override
    public List<StudyXunLianVo> TablesAllStudyFuzhiFiveLvDataInfolist() {
        return questionXunLianJiLuMapper.TablesAllStudyFuzhiFiveLvDataInfolist();
    }

    /**
     * @param studyUsername 根据当前用户名称去程序数据信息
     * @return 曲线图原理为通过对网课和题库学习情况赋值算分，网课刷题各占50%，每星期记录一次
     */
    @Override
    public List<StudyXunLianVo> SelectUserNameAllStudyFuzhiFiveLvDataInfolist(String studyUsername) {
        return questionXunLianJiLuMapper.SelectUserNameAllStudyFuzhiFiveLvDataInfolist(studyUsername);
    }

    /**
     * @return 结束时间 以这个来记录周 数据（所有的数据 学生进行50%的 计算 给管理员进行查看）
     */
    @Override
    public List<StudyXunLianVo> OneWeekShiXunTuEcharts() {
        return questionXunLianJiLuMapper.OneWeekShiXunTuEcharts();
    }

    /**
     * @return 查看是否看完视频状态
     */
    @Override
    public List<StudyDurationVo> MovesZT(String studyUsername) {
        return questionXunLianJiLuMapper.MovesZT(studyUsername);
    }
}
