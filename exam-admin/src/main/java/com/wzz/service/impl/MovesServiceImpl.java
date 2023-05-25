package com.wzz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.entity.Moves;
import com.wzz.mapper.MovesMapper;
import com.wzz.service.MovesService;
import com.wzz.vo.MovesVo;
import com.wzz.vo.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/17 12:34
 **/
@Service
@RequiredArgsConstructor
public class MovesServiceImpl extends ServiceImpl<MovesMapper, Moves>implements MovesService {
    /**
     * @param pageNo
     * @param pageSize
     * @return 查询视频记录
     */

    private final MovesMapper movesMapper;

    @Override
    public PageResponse<MovesVo> getMovesList(Integer pageNo, Integer pageSize) {
        IPage<Moves> movesPage = new Page<>(pageNo, pageSize);

        QueryWrapper<Moves> wrapper = new QueryWrapper<>();
//        setLikeWrapper(wrapper, null);   //null 参数查询

        movesPage = movesMapper.selectPage(movesPage, wrapper);
        List<MovesVo> records = movesPage.getRecords().stream().map(MovesVo::fromMoves).collect(Collectors.toList());

        return PageResponse.<MovesVo>builder().data(records).total(movesPage.getTotal()).build();
    }

    /**
     * @param moveCategorize 业务分类
     * @return
     */
    @Override
    public List<Moves> getMovescategorizeInfo(String[] moveCategorize) {
        return movesMapper.getMovescategorizeInfo(moveCategorize);
    }
}
