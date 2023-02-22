package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.ImageProxy;

public class AvatarCmd extends Cmd {

    public AvatarCmd() {
        super("avatar", "Wysyła avatar uzytkownika", Category.INFO);
    }
    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb) {
        OptionMapping option = e.getOption("username");
        String message = option.getAsString();
        User user = e.getJDA().getUserById(message);
        ImageProxy img = option.getAsUser().getAvatar();
        String url = img.getUrl();
        eb.setTitle("Avatar uzytkownika " + user.getName() + ":");
        eb.setImage(url);
        e.reply("").addEmbeds(eb.build()).queue();
    }
}
