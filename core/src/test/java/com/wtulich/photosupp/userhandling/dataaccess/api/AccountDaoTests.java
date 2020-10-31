package com.wtulich.photosupp.userhandling.dataaccess.api;

import com.wtulich.photosupp.config.H2JpaConfig;
import com.wtulich.photosupp.userhandling.dataaccess.api.dao.AccountDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {H2JpaConfig.class})
public class AccountDaoTests {

    @Autowired
    private AccountDao accountDao;

    @Test
    void existsByEmail_TRUE() {
        //Act Assert
        Assertions.assertEquals(true, accountDao.existsByEmail("user1@test.com"));
    }

    @Test
    void existsByEmail_FALSE() {
        //Act Assert
        Assertions.assertEquals(false, accountDao.existsByEmail("user3@test.com"));
    }
}
