package com.yuanian.dmp.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuanian.dmp.base.dao.AzkabanDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author zhuzhq on 2022-08-02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class AzkabanDaoTest {

    @Autowired
    private AzkabanDAO azkabanDAO;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testQueryFlowsByExecIds() {

    }
}
