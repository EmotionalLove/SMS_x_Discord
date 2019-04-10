package com.sasha.smsxdiscord;

import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public class DiscordListener {

    @SubscribeEvent
    public void onMsgRx(PrivateMessageReceivedEvent e) {
        System.out.println("Received message...");
        if (e.getChannel().getUser().getId().equals(Main.cfg.channelToListen) && (e.getAuthor().getIdLong() != Main.discord.getSelfUser().getIdLong())) {
            System.out.println("Matches criteria...");
            Main.send(e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator(), e.getMessage().getContentDisplay());
        }
    }

}
