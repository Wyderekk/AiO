package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class InviteCmd extends Cmd {

    public InviteCmd() {
        super("invite", "Zaproś bota na swój server!", Category.INFO);
    }

    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb) {
        eb.setTitle("Zaproś bota na swoj serwer używajac tego linku: ");
        eb.setDescription("https://discord.com/api/oauth2/authorize?client_id=853581591997448222&permissions=8&scope=bot%20applications.commands");
        e.reply("").addEmbeds(eb.build()).queue();
    }
}
