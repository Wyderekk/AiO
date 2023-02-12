package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCmd extends Cmd {

    public PingCmd(String n, String d, Category c) {
        super(n, d, c);
    }

    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb, JDA jda) {
        jda.getRestPing().queue(ping -> {
            eb.setTitle("Pong!");
            eb.setDescription("Ping: **" + ping + "**ms");
            e.reply("").addEmbeds(eb.build()).queue();;
        });
    }
}
