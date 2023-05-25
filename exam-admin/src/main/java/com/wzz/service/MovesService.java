package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.Moves;
import com.wzz.vo.MovesVo;
import com.wzz.vo.PageResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 叶双贵
 * @version: 1.0
 * @编辑时间：2023/4/17 12:33
 **/
public interface MovesService  extends IService<Moves> {

    /**
     * @param pageNo
     * @param pageSize
     * @return 查询视频记录
     */
    PageResponse<MovesVo> getMovesList(Integer pageNo, Integer pageSize);

    /**
     * @param  namecategorize 业务类型
     * @return
     */
    List<Moves> getMovescategorizeInfo(String[] namecategorize);

}
