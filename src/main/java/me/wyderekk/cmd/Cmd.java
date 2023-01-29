package me.wyderekk.cmd;

import com.sun.management.OperatingSystemMXBean;
import me.wyderekk.main.Main;
import me.wyderekk.utils.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.ImageProxy;
import org.jetbrains.annotations.NotNull;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cmd extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e) {
        JDA jda = e.getJDA();
        String command = e.getName();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Main.color);
        eb.setTimestamp(Instant.now());
        eb.setFooter("AiO", "https://cdn.discordapp.com/attachments/767767622531940364/864206783203901450/ezgif-2-7dfbb1ca4f15.gif");
        switch (command) {
            case "essa" -> {
                eb.setTitle("Twoj poziom essy: " + RandomUtil.getRandom(1, 100) + "%");
                e.reply("").addEmbeds(eb.build()).queue();
            }
            case "avatar" -> {
                OptionMapping option = e.getOption("username");
                String message = option.getAsString();
                User user = e.getJDA().getUserById(message);
                ImageProxy img = option.getAsUser().getAvatar();
                String url = img.getUrl();
                eb.setTitle("Avatar uzytkownika " + user.getName() + ":");
                eb.setImage(url);
                e.reply("").addEmbeds(eb.build()).queue();
            }
            case "stats" -> {
                OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
                double cpuUsage = osBean.getProcessCpuLoad() * 100;
                Runtime runtime = Runtime.getRuntime();
                long usedMemory = runtime.totalMemory() - runtime.freeMemory();
                long maxMemory = runtime.totalMemory();
                double memoryUsagePercentage = (double) usedMemory / maxMemory * 100;
                String cpuUsageRounded = String.format("%.2f", cpuUsage);
                String memoryUsageRounded = String.format("%.2f", memoryUsagePercentage);
                eb.setTitle("Stats:");
                eb.setDescription("OS: " + osBean.getName() + "\nCores: " + osBean.getAvailableProcessors() + "\nMemory Usage: " + memoryUsageRounded + "%\n CPU Usage: " + cpuUsageRounded + "%");
                e.reply("").addEmbeds(eb.build()).queue();
            }
            case "profil" -> {
                if(e.getOption("username") != null) {
                    String message = e.getOption("username").getAsString();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date date;
                    Date date2;
                    try {
                        User user = jda.getUserById(message);
                        Guild guild = jda.getGuildById(e.getGuild().getId());
                        Member member = guild.getMemberById(user.getId());
                        Role highestRole = member.getRoles().get(0);
                        date = inputFormat.parse(String.valueOf(user.getTimeCreated()));
                        date2 = inputFormat.parse(String.valueOf(member.getTimeJoined()));
                        String outputString2 = outputFormat.format(date2);
                        String outputString = outputFormat.format(date);
                        eb.setTitle("Informacje o profilu");
                        eb.setThumbnail(user.getAvatarUrl());
                        eb.addField(Emote.ARROWS.getDesc() + " Nazwa", user.getName(), true);
                        eb.addField(Emote.TAG.getDesc() + " Tag", "#" + user.getDiscriminator(), true);
                        eb.addField(Emote.HYPENEON.getDesc() + " Data dolaczenia na serwer", outputString2, false);
                        eb.addField(Emote.HYPENEON.getDesc() + " Data zalozenia konta", outputString, false);
                        eb.addField(Emote.ARROWS.getDesc() + " Najwyzsza rola", highestRole.getAsMention(), false);
                        eb.addField(Emote.ARROWS.getDesc() + " Odznaki", CmdUtil.getBadges(member), false);
                        e.reply("").addEmbeds(eb.build()).queue();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date date;
                    Date date2;
                    try {
                        User user = e.getUser();
                        Guild guild = jda.getGuildById(e.getGuild().getId());
                        Member member = guild.getMemberById(e.getUser().getId());
                        Role highestRole = member.getRoles().get(0);
                        date = inputFormat.parse(String.valueOf(e.getUser().getTimeCreated()));
                        date2 = inputFormat.parse(String.valueOf(member.getTimeJoined()));
                        String outputString2 = outputFormat.format(date2);
                        String outputString = outputFormat.format(date);
                        eb.setTitle("Informacje o profilu");
                        eb.setThumbnail(user.getAvatarUrl());
                        eb.addField(Emote.ARROWS.getDesc() + " Nazwa", user.getName(), true);
                        eb.addField(Emote.TAG.getDesc() + " Tag", "#" + user.getDiscriminator(), true);
                        eb.addField(Emote.HYPENEON.getDesc() + " Data dolaczenia na serwer", outputString2, false);
                        eb.addField(Emote.HYPENEON.getDesc() + " Data zalozenia konta", outputString, false);
                        eb.addField(Emote.ARROWS.getDesc() + " Najwyzsza rola", highestRole.getAsMention(), false);
                        eb.addField(Emote.ARROWS.getDesc() + " Odznaki", CmdUtil.getBadges(member), false);
                        e.reply("").addEmbeds(eb.build()).queue();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            case "ship" -> {
                String message = e.getOption("username").getAsString();
                int rand = RandomUtil.getRandom(1, 100);
                if(message.matches("<@[0-9]+>"))  {
                    if(e.getOption("username2") != null) {
                        String message2 = e.getOption("username2").getAsString();
                        User user = e.getJDA().getUserById(message.replace("<@", "").replace(">", ""));
                        User user2 = e.getJDA().getUserById(message2.replace("<@", "").replace(">", ""));
                        eb.setTitle(Emote.NAME.getDesc() + " " + user.getName() + " x " + user2.getName());
                    } else {
                        User user = e.getJDA().getUserById(message.replace("<@", "").replace(">", ""));
                        eb.setTitle(Emote.NAME.getDesc() + " " + user.getName() + " x " + e.getUser().getName());
                    }
                } else {
                    if(e.getOption("username2") != null) {
                        String message2 = e.getOption("username2").getAsString();
                        eb.setTitle(Emote.NAME.getDesc() + " " + message + " x " + message2);
                    } else {
                        eb.setTitle(Emote.NAME.getDesc() + " " + message + " x " + e.getUser().getName());
                    }
                }
                eb.setDescription("**" + rand + "%** " + CmdUtil.getShip(rand));
                e.reply("").addEmbeds(eb.build()).queue();
            }
            case "ping" -> {
                jda.getRestPing().queue(ping -> {
                    eb.setTitle("Pong!");
                    eb.setDescription("Ping: **" + ping + "**ms");
                    e.reply("").addEmbeds(eb.build()).queue();;
                });
            }
            case "market" -> {
                String marketHash = e.getOption("skin-name").getAsString();
                String condition;
                boolean statTrak;
                if(e.getOption("condition") != null && e.getOption("stattrak") != null) {
                    condition = e.getOption("condition").getAsString();
                    statTrak = e.getOption("stattrak").getAsBoolean();
                    String[] data = SteamAPI.makeRequest(marketHash, condition, statTrak);
                    if(data == null) {
                        eb.setTitle("Error: Podany skin nie istnieje!");
                    } else if(data.length < 3){
                        eb.setTitle("Dane dla skina: " + marketHash + SteamAPI.getCondition(condition));
                        eb.addField("Najniższa cena", data[0], false);
                    } else {
                        eb.setTitle("Dane dla skina: " + marketHash + SteamAPI.getCondition(condition));
                        eb.addField("Najniższa cena", data[0], false);
                        eb.addField("Średnia cena", data[1], false);
                        eb.addField("Ilość", data[2], false);
                    }
                } else if(e.getOption("condition") != null && e.getOption("stattrak") == null) {
                    condition = e.getOption("condition").getAsString();
                    String[] data = SteamAPI.makeRequest(marketHash, condition);
                    if(data == null) {
                        eb.setTitle("Error: Podany skin nie istnieje!");
                    } else {
                        eb.setTitle("Dane dla skina: " + marketHash + SteamAPI.getCondition(condition));
                        eb.addField("Najniższa cena", data[0], false);
                    }
                } else if(e.getOption("condition") == null && e.getOption("stattrak") != null) {
                    statTrak = e.getOption("stattrak").getAsBoolean();
                    String[] data = SteamAPI.makeRequest(marketHash, statTrak);
                    if(data == null) {
                        eb.setTitle("Error: Podany skin nie istnieje!");
                    } else {
                        eb.setTitle("Data for skin: " + marketHash);
                        eb.addField("Najniższa cena", data[0], false);
                    }
                } else {
                    String[] data = SteamAPI.makeRequest(marketHash);
                    if(data == null) {
                        eb.setTitle("Error: Podany skin nie istnieje!");
                    } else {
                        eb.setTitle("Data for skin: " + marketHash);
                        eb.addField("Najniższa cena", data[0], false);
                        eb.addField("Średnia cena", data[1], false);
                        eb.addField("Ilość", data[2], false);
                    }
                }
                e.reply("").addEmbeds(eb.build()).queue();
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
        commandData.add(Commands.slash("market","Pobiera cenę skina ze Steama (nie działa na wszystkie kosy)").addOptions(option5, option6, option7));
        commandData.add(Commands.slash("ping", "Wyswietla ping bota."));
        System.out.println("Loaded " + commandData.size() + " commands!");
        e.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
