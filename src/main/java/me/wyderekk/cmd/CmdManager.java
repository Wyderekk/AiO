package me.wyderekk.cmd;

import me.wyderekk.cmd.commands.*;
import me.wyderekk.main.Main;
import me.wyderekk.utils.Emote;
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
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
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
            case "ys" -> {
                eb.setTitle(Emote.ARROW.getDesc() + " **W strefie VIP znajdziesz:**");
//                eb.addField("**VIP (dostep do wszystkiego)**", "50zł", true);
//                eb.addField("**Cheaty do LabyModa (injector)**", "35zł", true);
//                eb.addField("**Cheaty forge (injector)**", "20zł", true);
//                eb.addField("**Dostep do modow, skryptow i unikalnych cheat clientow**", "10zł / 5 zaproszeń / 2 boosty", true);
//                eb.addField("**Dostęp do wycieku 18 milionow IP 1.8/1.16**", "40zł", true);
                eb.setDescription(
                        "**Ponad 10 prywatnych jak i publicznych cheat clientow na wersje 1.12.2 -> 1.19.3**\n" +
                        "**Prywatne skrypty**\n" +
                        "**Baze danych 18 milionow adresow IP**\n" +
                        "**Cheaty do LabyMod**\n" +
                        "**Injectowalne cheaty do Forge**\n" +
                        "\n" +
                        "**Ponadto odbywają się tam giveawaye tylko dla osób VIP i specjalne okacje dla tej grupy.**");
                e.reply(";)");
                e.getChannel().sendMessageEmbeds(eb.build()).queue();
            }
            case "invite" -> {
                InviteCmd.execute(e, eb);
            }
        }
    }

    @Override
    public void onReady(ReadyEvent e) {
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
        commandData.add(Commands.slash("ys", "dasdas"));
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
