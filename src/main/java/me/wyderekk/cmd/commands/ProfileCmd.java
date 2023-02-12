package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import me.wyderekk.utils.CmdUtil;
import me.wyderekk.utils.Emote;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileCmd extends Cmd {

    public ProfileCmd(String n, String d, Category c) {
        super("profil", "Wysyła informacje na temat profilu użytkownika.", Category.INFO);
    }

    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb, JDA jda) {
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
                if(member.getRoles().get(0) != null) {
                    Role highestRole = member.getRoles().get(0);
                    eb.addField(Emote.ARROWS.getDesc() + " Najwyzsza rola", highestRole.getAsMention(), false);
                }
                eb.setThumbnail(user.getAvatarUrl());
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
}
