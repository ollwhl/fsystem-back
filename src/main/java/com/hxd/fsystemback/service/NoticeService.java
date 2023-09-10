package com.hxd.fsystemback.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.dao.NoticeMapper;
import com.hxd.fsystemback.entity.Notice;
import com.hxd.fsystemback.entity.Params;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class NoticeService {
    @Resource
    NoticeMapper noticeMapper;

    @Resource
    LogService logService;

    public PageInfo<Notice> getNotice(Params params){
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Notice> list = noticeMapper.getNotice();
        return PageInfo.of(list);
    }

    //传入msg title
    @Transactional
    public void addNotice (Notice notice){
        Date time=new Date();
        noticeMapper.addNotice(notice.getMsg(),time);
        logService.setLog("发布了系统公告 标题为"+notice.getTitle()+"内容为"+notice.getMsg());
    }

    @Transactional
    public void delNotice (Notice notice){
        noticeMapper.delNotice(notice.getTitle());
        logService.setLog("删除了系统公告 标题为"+notice.getTitle());
    }
}
