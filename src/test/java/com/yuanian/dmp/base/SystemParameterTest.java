package com.yuanian.dmp.base;

import com.yuanian.dmp.base.dto.SystemParameterDTO;
import com.yuanian.dmp.base.dto.SystemParameterKeyValueDTO;
import com.yuanian.dmp.base.vo.SystemParameterVO;
import com.yuanian.dmp.base.enums.SystemParameterEnum;
import com.yuanian.dmp.base.enums.SystemParameterParamTypeEnum;
import com.yuanian.dmp.base.service.imp.SystemParameterServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * 系统参数单元测试
 *
 * @author zhuzhq 2021-05-10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class SystemParameterTest {

    private List<SystemParameterVO> allDateParams = new ArrayList<>();

    @Autowired
    private SystemParameterServiceImpl systemParameterService;

    private static final LocalDateTime BASE_DATE = LocalDateTime.of(2021, 5, 11, 10, 40, 30);

    @Before
    public void doBefore() {
        SystemParameterVO today = new SystemParameterVO();
        today.setParamName("dmp_today");
        today.setParamFormat("yyyy-MM-dd HH:mm:ss");
        today.setParamValue("");
        today.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(today);

        SystemParameterVO a = new SystemParameterVO();
        a.setParamName("a");
        a.setParamFormat("yyyy-MM-dd HH:mm:ss");
        a.setParamValue("dmp_today+3d");
        a.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(a);

        SystemParameterVO b = new SystemParameterVO();
        b.setParamName("b");
        b.setParamFormat("yyyy-MM-dd HH:mm:ss");
        b.setParamValue("a+4d");
        b.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(b);

        SystemParameterVO c = new SystemParameterVO();
        c.setParamName("c");
        c.setParamFormat("yyyy-MM-dd HH:mm:ss");
        c.setParamValue("b-4m");
        c.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(c);

        SystemParameterVO d = new SystemParameterVO();
        d.setParamName("d");
        d.setParamFormat("yyyy-MM-dd HH:mm:ss");
        d.setParamValue("a-4y");
        d.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(d);

        SystemParameterVO e = new SystemParameterVO();
        e.setParamName("e");
        e.setParamFormat("yyyy-MM-dd HH:mm:ss");
        e.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(e);

        SystemParameterVO f = new SystemParameterVO();
        f.setParamName("f");
        f.setParamFormat("yyyy-MM-dd HH:mm:ss");
        f.setParamValue("e-4M");
        f.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        allDateParams.add(f);
    }

    @Test
    public void testGetDate() {
        String paramFormat = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(paramFormat);
        assertEquals("2021-05-11 10:40:30", systemParameterService.getDate(SystemParameterEnum.TODAY.getValue(), BASE_DATE)
                .format(formatter));
        assertEquals("2021-05-10 10:40:30", systemParameterService.getDate(SystemParameterEnum.YESTERDAY.getValue(), BASE_DATE)
                .format(formatter));
        assertEquals("2020-05-11 10:40:30", systemParameterService.getDate(SystemParameterEnum.LAST_YEAR.getValue(), BASE_DATE)
                .format(formatter));
        assertEquals("2021-04-11 10:40:30", systemParameterService.getDate(SystemParameterEnum.LAST_MONTH.getValue(), BASE_DATE)
                .format(formatter));
    }

    @Test
    public void testDateParamProcess() {
        List<SystemParameterKeyValueDTO> keyValueDTOS = systemParameterService
                .parseDate(allDateParams,
                        BASE_DATE.minusDays(1)
                                .minusMonths(1)
                );

        Map<String, String> dateParams = keyValueDTOS.stream().collect(Collectors.toMap(SystemParameterKeyValueDTO::getKey, SystemParameterKeyValueDTO::getValue));

        assertEquals("2021-04-10 10:40:30", dateParams.get("dmp_today"));
        assertEquals("2021-04-13 10:40:30", dateParams.get("a"));
        assertEquals("2021-04-17 10:40:30", dateParams.get("b"));
        assertEquals("2021-04-17 10:36:30", dateParams.get("c"));
        assertEquals("2017-04-13 10:40:30", dateParams.get("d"));
        assertEquals("2021-04-10 10:40:30", dateParams.get("e"));
        assertEquals("2020-12-10 10:40:30", dateParams.get("f"));
    }

    @Test
    public void testValidate() {
        SystemParameterDTO a = new SystemParameterDTO();
        a.setParamName("a");
        a.setParamFormat("yyyy-MM-dd HH:mm:ss");
        a.setParamValue("b-4M");
        a.setParamType(SystemParameterParamTypeEnum.DATE.getValue());
        try {
            systemParameterService.validateDateParam(a, allDateParams);
        } catch (Exception e) {
            assertEquals("业务异常:日期型参数被循环引用", e.getMessage());
        }
    }
}
