package com.yuanian.dmp.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuanian.dmp.base.constant.RepairDataConstants;
import com.yuanian.dmp.common.azkaban.response.ExecuteFlowResponse;
import com.yuanian.dmp.common.azkaban.response.FetchExecFlowResponse;
import com.yuanian.dmp.common.azkaban.response.Node;
import com.yuanian.dmp.common.azkaban.service.AzkabanApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

/**
 * @author zhuzhq on 2022-07-13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class AzkabanApiTest {

    @Autowired
    @Qualifier("offlineAzkabanApi")
    private AzkabanApi azkabanApi;
    @Autowired
    private ObjectMapper objectMapper;

    // http://192.168.58.41:30100/dmp-dataIntegration/offlineTask/11ecfc45dd4544d0bef535c0a4616fd5/127689/fetchExecFlow?offset=0&limit=1000
    @Test
    public void test() throws JsonProcessingException {
        String execId = "127689";
        FetchExecFlowResponse fetchExecFlowResponse = azkabanApi.fetchExecFlow(execId);
        System.out.println(objectMapper.writeValueAsString(fetchExecFlowResponse));
        Iterator<Node> iterator = fetchExecFlowResponse.getNodes().iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (RepairDataConstants.PRE_JOB.equals(next.getId())
                    || RepairDataConstants.SUCCESS_JOB.equals(next.getId())
                    || RepairDataConstants.FAILED_JOB.equals(next.getId())
                    || RepairDataConstants.END_JOB.equals(next.getId())) {
                iterator.remove();
            }
        }

        System.out.println(objectMapper.writeValueAsString(fetchExecFlowResponse));
    }

    @Test
    public void test2() throws JsonProcessingException {
        ExecuteFlowResponse executeFlowResponse = azkabanApi.executeFlowWithFailureActionAndConcurrent("dataflows_11ece0d05a4037acaf1187b7baf77e9d",
                "base", "failureAction=finishCurrent&disabled=[%220e8b4ebc-9891-42a4-ace8-257b51213f3b%22]&concurrentOption=ignore&flowOverride%5BDMP_SYSTEM_PARAMETER_BASE_DATE%5D=2022-07-17+00%3A00%3A00");
        System.out.println(objectMapper.writeValueAsString(executeFlowResponse));
    }
}
