package com.wzz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzz.entity.Notice;
import com.wzz.vo.WangkeyewuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/10/20 8:59
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {

    // 将所有公告设置为历史公告
    boolean setAllNoticeIsHistoryNotice();

    /**
     * @return 用户属于哪一个业务和银行
     */
    List<WangkeyewuVo> getAllYeWu(@Param("username") String username);

}
