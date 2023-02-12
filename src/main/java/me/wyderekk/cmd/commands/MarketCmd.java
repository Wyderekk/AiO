package me.wyderekk.cmd.commands;

import me.wyderekk.cmd.Cmd;
import me.wyderekk.utils.SteamAPI;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class MarketCmd extends Cmd {

    public MarketCmd(String n, String d, Category c) {
        super("market", "Pobiera cenę skina CSGO ze Steama", Category.INFO);
    }

    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb) {
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
