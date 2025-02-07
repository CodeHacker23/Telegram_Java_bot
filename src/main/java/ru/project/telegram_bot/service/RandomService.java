package ru.project.telegram_bot.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomService {
    private final Random RANDOM = new Random();

    public int getNumber(int from,int to){

        return RANDOM.nextInt(from,to);
    }



}
