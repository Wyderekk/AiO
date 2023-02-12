package me.wyderekk.cmd;

import me.wyderekk.cmd.commands.AvatarCmd;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class Cmd {

    private String name;
    private String description;
    private Category category;

    public Cmd(String n, String d, Category c) {
        this.name = n;
        this.description = d;
        this.category = c;
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
