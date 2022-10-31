package com.yuanian.dmp.base;

import com.yuanian.dmp.common.enums.ApiNameEnum;
import com.yuanian.infrastructure.core.object.service.IMetadataObjectService;
import com.yuanian.infrastructure.metadata.utils.SecurityUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Map;

/**
 * 数据集成任务offlineTask测试
 *
 * @author zhuzhq 2021-12-24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class OfflineTaskTest {

    @Autowired
    private IMetadataObjectService metadataObjectService;

    @Test
    public void testSelectSetting() {
        String taskId = "11ebf4f3a9aefe19a1b73101faf5ae94";
        SecurityUtil.disableDataSecurity(ApiNameEnum.offlineTask.name());
        Object offlineTaskObj = metadataObjectService.selectDataById(ApiNameEnum.offlineTask.name(), null, taskId);
        Assert.assertNotNull("offlineTask为空", offlineTaskObj);
        Assert.assertNull("selectDataById的setting不为空", ((Map) offlineTaskObj).get("setting"));
        Object settingObj = metadataObjectService.selectBigDataById(ApiNameEnum.offlineTask.name(), null, taskId, "setting");
        Assert.assertNotNull("setting为空", settingObj);
    }
}
