package com.wtulich.photosupp.serviceordering.dataaccess.api;

import com.wtulich.photosupp.config.H2JpaConfig;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.BookingDao;
import com.wtulich.photosupp.serviceordering.dataaccess.api.dao.IndicatorDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = {H2JpaConfig.class})
public class BookingDaoTest {

    @Autowired
    private BookingDao bookingDao;

    @Test
    void existsByName_TRUE() {
        //Act Assert
        Assertions.assertEquals(true, bookingDao.existsByName("Film dla TestCompany"));
    }

    @Test
    void existsByName_FALSE() {
        //Act Assert
        Assertions.assertEquals(false, bookingDao.existsByName("Film dla TestCompany!"));
    }
}
