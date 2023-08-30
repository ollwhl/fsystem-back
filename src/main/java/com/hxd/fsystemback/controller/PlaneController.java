package com.hxd.fsystemback.controller;

import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.service.PartsService;
import com.hxd.fsystemback.service.PlaneService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/plane")

public class PlaneController {
    @Resource
    PlaneService planeService;

}
