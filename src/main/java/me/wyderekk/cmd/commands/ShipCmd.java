package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import me.wyderekk.utils.CmdUtil;
import me.wyderekk.utils.Emote;
import me.wyderekk.utils.RandomUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ShipCmd extends Cmd {

    public ShipCmd(String n, String d, Category c) {
        super("ship", "Shipuje Cie z osobą.", Category.FUN);
    }

    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb) {
        String message = e.getOption("username").getAsString();
        int rand = RandomUtil.getRandom(1, 100);
        if (message.matches("<@[0-9]+>")) {
            if (e.getOption("username2") != null) {
                String message2 = e.getOption("username2").getAsString();
                User user = e.getJDA().getUserById(message.replace("<@", "").replace(">", ""));
                User user2 = e.getJDA().getUserById(message2.replace("<@", "").replace(">", ""));
                eb.setTitle(Emote.NAME.getDesc() + " " + user.getName() + " x " + user2.getName());
            } else {
                User user = e.getJDA().getUserById(message.replace("<@", "").replace(">", ""));
                eb.setTitle(Emote.NAME.getDesc() + " " + user.getName() + " x " + e.getUser().getName());
            }
        } else {
            if (e.getOption("username2") != null) {
                String message2 = e.getOption("username2").getAsString();
                eb.setTitle(Emote.NAME.getDesc() + " " + message + " x " + message2);
            } else {
                eb.setTitle(Emote.NAME.getDesc() + " " + message + " x " + e.getUser().getName());
            }
        }
        eb.setDescription("**" + rand + "%** " + CmdUtil.getShip(rand));
        e.reply("").addEmbeds(eb.build()).queue();
    }
}
