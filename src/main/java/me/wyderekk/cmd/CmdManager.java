package me.wyderekk.cmd;

import me.wyderekk.cmd.commands.*;
import me.wyderekk.main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CmdManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        JDA jda = e.getJDA();
        String command = e.getName();
        EmbedBuilder eb = new EmbedBuilder();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        eb.setColor(Main.color);
        eb.setTimestamp(Instant.now());
        eb.setFooter("AiO", "https://cdn.discordapp.com/attachments/767767622531940364/864206783203901450/ezgif-2-7dfbb1ca4f15.gif");
        System.out.println("[!] [" + now.format(formatter) + "] " + e.getUser().getName() + " used /" + command + " command.");
        switch (command) {
            case "essa" -> {
                EssaCmd.execute(e, command, eb, jda);
            }
            case "avatar" -> {
                AvatarCmd.execute(e, eb);
            }
            case "stats" -> {
                StatsCmd.execute(e, eb);
            }
            case "profil" -> {
                ProfileCmd.execute(e, eb, jda);
            }
            case "ship" -> {
                ShipCmd.execute(e, eb);
            }
            case "ping" -> {
                PingCmd.execute(e, eb, jda);
            }
            case "market" -> {
                MarketCmd.execute(e, eb);
            }
            case "invite" -> {
                InviteCmd.execute(e, eb);
            }
        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();
        OptionData option = new OptionData(OptionType.USER, "username", "Username", true);
        OptionData option2 = new OptionData(OptionType.USER, "username", "Username", false);
        OptionData option3 = new OptionData(OptionType.STRING, "username", "Username", true);
        OptionData option4 = new OptionData(OptionType.STRING, "username2", "Username", false);
        OptionData option5 = new OptionData(OptionType.STRING, "skin-name", "Skin Name", true);
        OptionData option6 = new OptionData(OptionType.STRING, "condition", "Condition", false)
                .addChoice("Factory New", "factory_new").addChoice("Minimal Wear", "minimal_wear").addChoice("Field Tested", "field_tested")
                .addChoice("Well Worn", "well_worn").addChoice("Battle Scarred", "battle_scarred");
        OptionData option7  = new OptionData(OptionType.BOOLEAN, "stattrak", "StatTrak", false);
        commandData.add(Commands.slash("avatar", "Pobiera avatar uzytkownika").addOptions(option));
        commandData.add(Commands.slash("essa", "Twój poziom essy B)"));
        commandData.add(Commands.slash("stats", "Pokazuje zużycie zasobóww przez bota."));
        commandData.add(Commands.slash("ship", "Shipuje cie z osoba").addOptions(option3, option4));
        commandData.add(Commands.slash("profil","Wyświetla informacje na temat profilu Discord").addOptions(option2));
        commandData.add(Commands.slash("market","Pobiera cenę skina CSGO ze Steama").addOptions(option5, option6, option7));
        commandData.add(Commands.slash("ping", "Wyświetla ping bota."));
        commandData.add(Commands.slash("invite", "Zaproś bota na swoj serwer."));
        System.out.println("[!] Loaded " + commandData.size() + " commands!");
        e.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
