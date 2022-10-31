package com.yuanian.dmp.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 访客
 * @since Wed Dec 30 14:46:58 CST 2020
 */
@RestController
public class Healthz {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = "/healthz", produces = MediaType.TEXT_PLAIN_VALUE)
    public String healthz() {
        return "V20221001_03_20221008";
    }
}
