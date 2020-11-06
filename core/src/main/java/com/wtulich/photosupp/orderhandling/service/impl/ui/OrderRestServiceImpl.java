package com.wtulich.photosupp.orderhandling.service.impl.ui;

import com.wtulich.photosupp.orderhandling.logic.impl.OrderHandlingImpl;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class OrderRestServiceImpl {
    private static String ORDERS_NOT_EXIST = "Orders do not exist.";
    private static String MEDIA_CONTENT_NOT_EXIST = "Media content does not exist.";
    private static String COMMENT_NOT_EXIST = "Comments do not exist.";
    private static final String BASE_URL = "service/v1/";

    @Inject
    private OrderHandlingImpl orderHandling;
}
