package com.example.bookstore.services;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class SessionService {
    static final int maxSessionDuration = 2; //session should be no longer than 2 minutes

    public boolean isSessionLessThanTwoMinutes(Date orderStartTime) {
        Instant startInstant = orderStartTime.toInstant();
        Instant nowInstant = Instant.now();


        Duration duration = Duration.between(startInstant, nowInstant);
        return duration.toMinutes() < maxSessionDuration;
    }

}
