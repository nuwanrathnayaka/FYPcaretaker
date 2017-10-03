package com.kerbio.virtualcaretaker.fragments.bots;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niroshan on 5/31/17.
 */
public class BotFactory {

    private static BotFactory instance;

    private BotFactory(){

    }

    public static BotFactory Instance(){
        if(instance==null)
            instance= new BotFactory();

        return instance;
    }

    public List<Bot> getBots(){
        List<Bot> botList = new ArrayList<Bot>();

        botList.add(new Bot("ScheduleAppointment",
                "$LATEST",
                "Bot to book a dentist appointment",
                "us-east-1",
                new String[]{
                }));


        return botList;
    }



}
