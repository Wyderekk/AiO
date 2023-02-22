package me.wyderekk.cmd;

import me.wyderekk.cmd.commands.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cmd {

    private String name;
    private String description;
    private Category category;
    private static final List<Cmd> cmdList = new ArrayList<>();

    public Cmd(String n, String d, Category c) {
        this.name = n;
        this.description = d;
        this.category = c;
    }

    public static void initializeCmd() {
        cmdList.add(new AvatarCmd());
        cmdList.add(new EssaCmd());
        cmdList.add(new InviteCmd());
        cmdList.add(new MarketCmd());
        cmdList.add(new ProfileCmd());
        cmdList.add(new PingCmd());
        cmdList.add(new ShipCmd());
        cmdList.add(new StatsCmd());
        System.out.println("[!] Created " + cmdList.size() + " commands!");
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category {
        FUN, INFO, MODERATION;
    }
}
