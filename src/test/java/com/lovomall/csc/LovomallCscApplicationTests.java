package com.lovomall.csc;

import com.lovomall.csc.entity.Balance;
import com.lovomall.csc.entity.ConsumeRecord;
import com.lovomall.csc.entity.DiscountLevel;
import com.lovomall.csc.repository.ConsumeRecordRepository;
import com.lovomall.csc.repository.DiscountLevelRepository;
import com.lovomall.csc.service.BalanceService;
import com.lovomall.csc.service.ConsumeRecordService;
import com.lovomall.csc.service.DiscountLevelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;

@SpringBootTest
class LovomallCscApplicationTests {

    @Autowired(required = false)
    private DiscountLevelService discountLevelService;


    @Test
    void discountLevel(){
        discountLevelService.findAll().stream()
                .map(DiscountLevel::getId)
                .forEach(System.out::println);
    }

}
