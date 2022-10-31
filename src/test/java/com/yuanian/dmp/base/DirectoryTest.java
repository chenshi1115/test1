package com.yuanian.dmp.base;

import com.yuanian.dmp.common.enums.ApiNameEnum;
import com.yuanian.infrastructure.core.object.service.IMetadataObjectService;
import com.yuanian.infrastructure.metadata.utils.SecurityUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuzhq 2022-02-11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class DirectoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryTest.class);
    @Autowired
    private IMetadataObjectService metadataObjectService;

    @Test
    public void testSelectAllData() {
        SecurityUtil.disableDataSecurity(ApiNameEnum.dmpCommonDirectory.name());
        List list = metadataObjectService.selectAllData(ApiNameEnum.dmpCommonDirectory.name(), null, null);
        LOGGER.info("total: {}", list.size());
    }

    @Test
    public void testUpdateById() {
        SecurityUtil.disableDataSecurity(ApiNameEnum.dmpCommonDirectory.name());
        Map<String, Object> map = new HashMap<>();
        map.put("objectId", "11ec7ab6da45da1aaaeb03aafe0cc928");
        map.put("directoryName", "哈哈");
        int affectNum = metadataObjectService.updateDataById(ApiNameEnum.dmpCommonDirectory.name(), null, map);
        LOGGER.info("updated: {}", affectNum);
    }

    @Test
    public void testInsertCommonDirectory() {
        Map<String, Object> map = new HashMap<>();
        map.put("directoryName", "哈哈");
        map.put("createBy", "11ec69970a2e0928b6d0e5abdb5e6c4c");
        map.put("updateBy", "11ec69970a2e0928b6d0e5abdb5e6c4c");
        String objectId = metadataObjectService.insertData(ApiNameEnum.dmpCommonDirectory.name(), null, map);
        LOGGER.info("insert: {}", objectId);
    }
}
