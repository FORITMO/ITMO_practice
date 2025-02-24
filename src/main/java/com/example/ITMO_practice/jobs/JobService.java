package com.example.ITMO_practice.jobs;

import com.example.ITMO_practice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {
    private final UserService userService;
    @Scheduled(cron = "0 0 3 * * */2")
    public void invalidateSession() {
        userService.invalidateSession();
    }
    @Scheduled(initialDelay = 5000, fixedDelay = 5000)
    private void sendMsg(){
        String msg = "Hello World!";
    }
}
