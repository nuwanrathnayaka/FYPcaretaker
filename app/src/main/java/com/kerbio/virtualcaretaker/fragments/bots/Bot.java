package com.kerbio.virtualcaretaker.fragments.bots;

import java.io.Serializable;

/**
 * Created by niroshan on 5/31/17.
 */
public class Bot implements Serializable {

    private String botName;
    private String botAlias;
    private String description;
    private String region;
    private String[] helpCommands;

    public String getBotName(){
        return this.botName;
    }

    public String getBotAlias(){
        return this.botAlias;
    }

    public String getDescription(){
        return this.description;
    }

    public String getRegion(){
        return this.region;
    }

    public String[] getHelpCommands() {
        return helpCommands;
    }

    public Bot(final String botName,
               final String botAlias,
               final String description,
               final String region,
               final String[] helpCommands){
        this.botName = botName;
        this.botAlias = botAlias;
        this.description  =description;
        this.region = region;
        this.helpCommands = helpCommands;
    }
}
