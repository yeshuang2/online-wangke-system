package com.wzz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.entity.Notice;
import com.wzz.vo.PageResponse;
import com.wzz.vo.WangkeyewuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by ysg
 * @implNote 2022/10/20 9:04
 */
public interface NoticeService extends IService<Notice> {

    /**
     * @return 用户属于哪一个业务和银行
     */
    List<WangkeyewuVo> getAllYeWu(@Param("username") String username);

    // 将所有公告设置为历史公告
    boolean setAllNoticeIsHistoryNotice();

    String getCurrentNotice();

    PageResponse<Notice> getAllNotices(String content, Integer pageNo, Integer pageSize);

    void publishNotice(Notice notice);

    void deleteNoticeByIds(String noticeIds);

    void updateNotice(Notice notice);
}
