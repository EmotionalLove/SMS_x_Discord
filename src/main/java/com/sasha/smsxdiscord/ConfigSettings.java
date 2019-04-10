package com.sasha.smsxdiscord;

import com.sasha.simplesettings.annotation.Setting;

public class ConfigSettings {

    @Setting
    public String originEmail = "somewhere@someonelove.me";
    @Setting
    public String destinationEmail = "atashiwakiminosuizowotabetai@gmail.com";
    @Setting
    public String sendgridToken = "[no default]";
    @Setting
    public String discordToken = "[no default]";
    @Setting
    public String channelToListen = "[no default]";

}
