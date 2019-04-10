package com.sasha.smsxdiscord;

import com.sasha.simplesettings.SettingHandler;
import com.sendgrid.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {


    public static JDA discord;
    public static SettingHandler settingHandler = new SettingHandler("config");
    public static ConfigSettings cfg = new ConfigSettings();

    public static void main(String[] args) throws LoginException, InterruptedException {
        System.out.println("Reading configuration...");
        settingHandler.read(cfg);
        System.out.println("Connecting to Discord...");
        if (cfg.discordToken.equalsIgnoreCase("[no default]")) {
            System.err.println("Discord token isn't set. Please edit config.yml");
            System.exit(1);
        }
        if (cfg.channelToListen.equalsIgnoreCase("[no default]")) {
            System.err.println("Channel to listen isn't set. Please edit config.yml");
            System.exit(1);
        }
        discord = new JDABuilder(AccountType.CLIENT).setToken(cfg.discordToken).buildBlocking();
        discord.setEventManager(new AnnotatedEventManager());
        discord.addEventListener(new DiscordListener());
        discord.getPresence().setIdle(true);
        discord.getPresence().setStatus(OnlineStatus.IDLE);
        send("SMS x Discord", "Connected to discord as " + discord.getSelfUser().getName() + "#" + discord.getSelfUser().getDiscriminator());
    }

    public static void send(String subject, String message) {
        System.out.println("Sending message...");
        Email from = new Email(cfg.originEmail);
        Email to = new Email(cfg.destinationEmail);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(cfg.sendgridToken);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
