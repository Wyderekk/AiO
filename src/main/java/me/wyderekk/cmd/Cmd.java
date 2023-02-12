package me.wyderekk.cmd;

import me.wyderekk.cmd.commands.AvatarCmd;

public class Cmd {

    private String name;
    private String description;
    private Category category;
    private static int maxSize = 10;
    private Cmd[] cmds = new Cmd[maxSize];

    public Cmd(String n, String d, Category c) {
        this.name = n;
        this.description = d;
        this.category = c;
        if(cmds.length >= maxSize) {
            maxSize++;
        }
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
