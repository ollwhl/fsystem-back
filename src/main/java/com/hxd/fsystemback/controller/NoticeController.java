package com.hxd.fsystemback.controller;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.common.Result;
import com.hxd.fsystemback.entity.Notice;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.service.NoticeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    NoticeService noticeService;
    @GetMapping("")
    Result getNotice(Params params){
        PageInfo<Notice> list = noticeService.getNotice(params);
        return Result.success(list);
    }
    @PostMapping("addNotice")
    Result addNotice(@RequestBody Notice notice){
        System.out.println(notice);
        noticeService.addNotice(notice);
        return Result.success();
    }
    @PostMapping("delNotice")
    Result delNotice(@RequestBody Notice notice){
        noticeService.delNotice(notice);
        return Result.success();
    }

}
