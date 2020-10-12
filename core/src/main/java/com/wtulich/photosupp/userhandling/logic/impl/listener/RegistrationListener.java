package com.wtulich.photosupp.userhandling.logic.impl.listener;

import com.wtulich.photosupp.userhandling.logic.impl.events.OnRegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;

public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {

    }
}
