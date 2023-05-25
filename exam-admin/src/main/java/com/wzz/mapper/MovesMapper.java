package com.wzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzz.entity.Moves;
import com.wzz.vo.MovesVo;
import com.wzz.vo.PageResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/17 12:36
 **/
@Repository
public interface MovesMapper extends BaseMapper<Moves> {

    /**
     * @param pageNo
     * @param pageSize
     * @return  这个是调用没有使用
     */
    PageResponse<MovesVo> getMovesList(Integer pageNo, Integer pageSize);


    /**
     * @param  moveCategorize 业务类型进行 List 查询
     * @return
     */
    List<Moves> getMovescategorizeInfo(String[] moveCategorize);

}
