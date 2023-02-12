package me.wyderekk.cmd.commands;

import com.sun.management.OperatingSystemMXBean;
import me.wyderekk.cmd.Cmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.lang.management.ManagementFactory;

public class StatsCmd extends Cmd {


    public StatsCmd(String n, String d, Category c) {
        super("stats", "Wysyła statystyki na temat zużycia zasobów przez bota.", Category.INFO);
    }

    public static void execute(SlashCommandInteractionEvent e, EmbedBuilder eb) {
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
}
