package com.yuanian.dmp.base;

import com.yuanian.dmp.base.enums.ApiNameEnum;
import com.yuanian.infrastructure.core.object.service.IMetadataObjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhuzhq on 2022-07-06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class RepairDataTest {

    @Autowired
    private IMetadataObjectService metadataObjectService;

    @Test
    public void test() {
    }
}
