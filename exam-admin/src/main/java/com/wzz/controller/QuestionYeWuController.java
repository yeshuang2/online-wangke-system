package com.wzz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzz.entity.QuestionYewu;
import com.wzz.mapper.QuestionYeWuMapper;
import com.wzz.mapper.WangKeMapper;
import com.wzz.service.MovesService;
import com.wzz.service.QuestionYeWuService;
import com.wzz.utils.RedisUtil;
import com.wzz.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/18 17:23
 **/

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(tags = "业务分配管理系统")
@RequestMapping(value = "/fepeiyewu")
public class QuestionYeWuController {

    private final RedisUtil redisUtil;
    @Autowired
    private QuestionYeWuService questionYeWuService;

    @Autowired
    private MovesService movesService;

    @Autowired
    private WangKeMapper wangKeMapper;

    @GetMapping("/getQuestTionYeWuInfo")
    @ApiOperation("获取所有MovesBank信息")
    public CommonResult<List<QuestionYewu>> getQuestionBank() {
        return CommonResult.<List<QuestionYewu>>builder()
                .data(questionYeWuService.getAllQuestTionYeWuInfo())
                .build();
    }


    @PostMapping("/addQuestTionYeWuInfo")
    @ApiOperation("添加业务")
    public CommonResult<String> addQuestionYewu(String yewuName, String yewuQuBinkid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("yewu_name", yewuName);
        QuestionYewu CunZaione = questionYeWuService.getOne(queryWrapper);
        if (CunZaione != null) {
         //  不等于null 进行更新
            // 需要 角色业务名称 这个是复选框不要 yewuQuBinkid
            String[] bankIds = yewuQuBinkid.split(",");
            // 当前已经有的题库id
            Set<Integer> allId = new HashSet<>();
            // 将新增的仓库id放入
            for (String bankId : bankIds) {
                // remove cache
                redisUtil.del("questionYeWuQuestion:" + bankId);
                allId.add(Integer.parseInt(bankId)); //放入缓存
            }
            // 处理后的id字符串 例如(1,2,3)
            String handleHaveBankIds = allId.toString().replaceAll(" ", "");
            System.out.println(handleHaveBankIds);
            // 更新当前用户的题库id值
            // 将存放处理后的set集合遍历,然后替换数据库的题库名
            StringBuilder bankNames = new StringBuilder();
            for (Integer id : allId) {
                System.out.println("业务" + id);
                bankNames.append(wangKeMapper.selectById(id).getWangkeName()).append(",");
            }
            QuestionYewu questionYewu = new QuestionYewu();
            questionYewu.setYewuName(yewuName);
            questionYewu.setYewuQuBinkid(handleHaveBankIds);
            questionYewu.setYewuQuBinkname(bankNames.substring(0, bankNames.toString().length() - 1));
//           更新操作
            QueryWrapper updateWrapper = new QueryWrapper<>();
            updateWrapper.eq("yewu_name", yewuName);
            questionYeWuService.update(questionYewu, updateWrapper);
            return CommonResult.<String>builder().message("更新成功").build();
        } else {
            // 需要 角色业务名称 这个是复选框不要 yewuQuBinkid
            String[] bankIds = yewuQuBinkid.split(",");
            // 当前已经有的题库id
            Set<Integer> allId = new HashSet<>();
            // 将新增的仓库id放入
            for (String bankId : bankIds) {
                // remove cache
                redisUtil.del("questionYeWuQuestion:" + bankId);
                allId.add(Integer.parseInt(bankId)); //放入缓存
            }
            // 处理后的id字符串 例如(1,2,3)
            String handleHaveBankIds = allId.toString().replaceAll(" ", "");
            System.out.println(handleHaveBankIds);
            // 更新当前用户的题库id值
            // 将存放处理后的set集合遍历,然后替换数据库的题库名
            StringBuilder bankNames = new StringBuilder();
            for (Integer id : allId) {
//                System.out.println("网课课程id" + id); Moves
                bankNames.append(wangKeMapper.selectById(id).getWangkeName()).append(",");
            }
            QuestionYewu questionYewu = new QuestionYewu();
            questionYewu.setYewuName(yewuName);
            questionYewu.setYewuQuBinkid(handleHaveBankIds);
            questionYewu.setYewuQuBinkname(bankNames.substring(0, bankNames.toString().length() - 1));
            System.out.println(questionYewu);
            questionYeWuService.save(questionYewu);
            return CommonResult.<String>builder().message("添加成功").build();
        }

    }

    @PostMapping("/updateQuestTionYeWuInfoById")
    @ApiOperation("更新业务名称")
    public CommonResult<Void> updateQuestion(@RequestBody @Valid QuestionYewu questionYewu) {
        questionYeWuService.updateById(questionYewu);
        return CommonResult.<Void>builder()
                .build();
    }


    /**
     * @param yewuid 业务id
     * @return
     */
    @GetMapping("/deleteQuestTionYeWuInfoById")
    @ApiOperation("删除业务")
    public CommonResult<Void> deleteQuestionBank(@RequestParam Integer yewuid) {
        questionYeWuService.removeById(yewuid);
        return CommonResult.<Void>builder()
                .build();
    }


}
