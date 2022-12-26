package com.example.springbatchtutorial.job.dbdatareadwrite;

import com.example.springbatchtutorial.SpringBatchTestConfig;
import com.example.springbatchtutorial.core.domain.accounts.AccountsRepository;
import com.example.springbatchtutorial.core.domain.orders.Orders;
import com.example.springbatchtutorial.core.domain.orders.OrdersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest(classes = {SpringBatchTestConfig.class, TrMigrationConfig.class})
class TrMigrationConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AccountsRepository accountsRepository;

    @AfterEach
    public void afterEach() {
        ordersRepository.deleteAll();
        accountsRepository.deleteAll();
    }

    @Test
    public void success_noData() throws Exception {
        //when
        JobExecution execution = jobLauncherTestUtils.launchJob();

        //then
        assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
        assertEquals(0, accountsRepository.count());
    }

    @Test
    public void success_existData() throws Exception {
        //given
        Orders orders1 = new Orders(1L, "kakao gift", 15000, LocalDateTime.now());
        Orders orders2 = new Orders(2L, "naver gift", 15000, LocalDateTime.now());

        ordersRepository.save(orders1);
        ordersRepository.save(orders2);

        //when
        JobExecution execution = jobLauncherTestUtils.launchJob();

        //then
        assertEquals(execution.getExitStatus(), ExitStatus.COMPLETED);
        assertEquals(2, accountsRepository.count());
    }

}