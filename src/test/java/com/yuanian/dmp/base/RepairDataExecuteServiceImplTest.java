package com.yuanian.dmp.base;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author zhuzhq on 2022-07-20
 */
public class RepairDataExecuteServiceImplTest {


    @Test
    public void testNextToRun() {
        List<Repair> queuedList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        queuedList.add(new Repair().setBusinessDate(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())));
        queuedList.add(new Repair().setBusinessDate(Date.from(localDateTime.minusDays(1).atZone(ZoneId.systemDefault()).toInstant())));
        queuedList.add(new Repair().setBusinessDate(Date.from(localDateTime.minusDays(2).atZone(ZoneId.systemDefault()).toInstant())));
        Optional<Repair> maxOption = queuedList.stream().min(Comparator.comparing(Repair::getBusinessDate));
        if (maxOption.isPresent()) {
            System.out.println(maxOption.get());
        }
    }

    class Repair {
        private Date businessDate;

        public Date getBusinessDate() {
            return businessDate;
        }

        @Override
        public String toString() {
            return "Repair{" +
                    "businessDate=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(businessDate) +
                    '}';
        }

        public Repair setBusinessDate(Date businessDate) {
            this.businessDate = businessDate;
            return this;
        }
    }
}
