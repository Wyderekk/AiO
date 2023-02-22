package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import me.wyderekk.utils.RandomUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class EssaCmd extends Cmd {


    public EssaCmd() {
        super("essa", "Wysyla poziom essy uzytkownika", Category.FUN);
    }

    public static void execute(SlashCommandInteractionEvent e, String command, EmbedBuilder eb, JDA jda) {
        eb.setTitle("Twoj poziom essy: " + RandomUtil.getRandom(1, 100) + "%");
        e.reply("").addEmbeds(eb.build()).queue();
    }
}
