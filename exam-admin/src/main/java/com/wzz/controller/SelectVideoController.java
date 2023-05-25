package com.wzz.controller;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/17 10:19
 **/

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzz.entity.Moves;
import com.wzz.entity.User;
import com.wzz.mapper.VideoUploadMapper;
import com.wzz.service.MovesService;
import com.wzz.service.UserRoleService;
import com.wzz.utils.NonStaticResourceHttpRequestHandler;

import com.wzz.vo.CommonResult;
import com.wzz.vo.MovesVo;
import com.wzz.vo.PageResponse;
import com.wzz.vo.UserInfoVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


//前端获取后端视频流
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SelectVideoController {
    //引入返回视频流的组件
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;
    //把后端链接数据库接口引入进来
    @Resource
    VideoUploadMapper videoUploadMapper;

      @Autowired
     MovesService movesService;


    //解决跨域的注解
    @CrossOrigin(origins = "*", maxAge = 3600)
    //查询视频流的接口
    @GetMapping("/policemen/{moveId}")
    //前两个参数不管，第三个参数moveId代表前端传过来的视频的id号
    public void videoPreview(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("moveId") Integer moveId) throws Exception {
        //调用查询方法，把前端传来的id传过去，查询对应的视频信息。
        Moves videoPathList = videoUploadMapper.selectById(moveId);
        //从视频信息中单独把视频路径信息拿出来保存
        String videoPathUrl=videoPathList.getMoveUrl();
        System.out.println("查询视频的路径"+videoPathUrl);
        //保存视频磁盘路径
        Path filePath = Paths.get(videoPathUrl);
        //Files.exists：用来测试路径文件是否存在
        if (Files.exists(filePath)) {
            //获取视频的类型，比如是MP4这样
            String mimeType = Files.probeContentType(filePath);
            if (StringUtils.hasText(mimeType)) {
                //判断类型，根据不同的类型文件来处理对应的数据
                response.setContentType(mimeType);
            }
            //转换视频流部分
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

    //查询所以视频表格列表的接口
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/alltablemoves")
    public CommonResult<PageResponse<MovesVo>> videoTable(Integer pageNo, Integer pageSize)  {
            return CommonResult.<PageResponse<MovesVo>>builder()
                    .data(movesService.getMovesList(pageNo, pageSize))
                    .build();
    }


    /**
     * @param moveId
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/deletemove/{moveId}")
    @ApiOperation("删除视频")
    public CommonResult<Void> deleteMovesbyID(@PathVariable Integer moveId) {
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("moveid",moveId);
        movesService.remove(queryWrapper);
        return CommonResult.<Void>builder().build();
    }

}
