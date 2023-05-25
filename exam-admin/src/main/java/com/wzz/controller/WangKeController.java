package com.wzz.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzz.entity.Moves;
import com.wzz.entity.MovesBank;
import com.wzz.mapper.MovesMapper;
import com.wzz.service.MovesService;
import com.wzz.service.NoticeService;
import com.wzz.service.WangKeService;
import com.wzz.vo.CommonResult;
import com.wzz.vo.WangkeyewuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author: 叶
 * @version: 1.0
 * @编辑时间：2023/4/18 14:14
 * 网课信息
 **/
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "网课接口")
@RequestMapping(value = "/wangke")
public class WangKeController {

    @Autowired
    private MovesService movesService;  //网课信息
    @Autowired
    private MovesMapper movesMapper;  //网课信息

    @Autowired
    private WangKeService wangKeService;  // 业务列表

    @Autowired
    private NoticeService noticeService; //里面有查询用户业务所属银行

    @GetMapping("/getMovesBankInfo")
    @ApiOperation("获取所有MovesBank信息")
    public CommonResult<List<MovesBank>> getQuestionBank() {
        return CommonResult.<List<MovesBank>>builder()
                .data(wangKeService.getAllMovesBankInfo())
                .build();
    }


    @PostMapping("/addBankInfo")
    @ApiOperation("添加业务")
    public CommonResult<Void> addQuestion(@RequestBody @Valid MovesBank movesBank) {
        wangKeService.save(movesBank);
        return CommonResult.<Void>builder().build();
    }

    @PostMapping("/updateBankInfoById")
    @ApiOperation("更新业务名称")
    public CommonResult<Void> updateQuestion(@RequestBody @Valid MovesBank movesBank) {
        wangKeService.updateById(movesBank);
        return CommonResult.<Void>builder()
                .build();
    }


    /**
     * @param wangkeid 业务id
     * @return
     */
    @GetMapping("/deleteBankInfoById")
    @ApiOperation("删除业务")
    public CommonResult<Void> deleteQuestionBank(@RequestParam Integer wangkeid) {
        wangKeService.removeById(wangkeid);
        return CommonResult.<Void>builder()
                .build();
    }


    /**
     * @param username 当前登录的用户名称信息 进行筛选
     * @return 查询当前登录的用户 所属的支行和业务 进行匹配 业务学习网课的内容信息
     */
    @GetMapping("/getYonghuWangkeYeWuBankInfo")
    @ApiOperation("查询用户所属的业务和银行")
    public CommonResult<List<Moves>> getYonghuWangkeYeWuBank(String username) {
        List<WangkeyewuVo> allYeWu = noticeService.getAllYeWu(username);
        // 显示一个字段数据
        List<String> collectyewuQuBinkname = allYeWu.stream().map(WangkeyewuVo::getYewuQuBinkname).collect(Collectors.toList());
        //String[]转String
        String id= String.join(",", collectyewuQuBinkname);
        //String转String[]
        String[] ids = id.split(",");
        System.out.println(ids);
        List<Moves> movescategorizeInfo = movesService.getMovescategorizeInfo(ids);
        return CommonResult.<List<Moves>>builder()
                .data(movescategorizeInfo)
                .message("获取用户学习网课视频成功")
                .build();
    }


}
