package me.wyderekk.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public class CmdUtil {

    public static String getShip(int rand) {
        int mod = rand % 10;
        int x = (rand - mod) / 10;
        int left = 10 - x;
        return Emote.FULL.getDesc().repeat(Math.max(0, x)) + Emote.BLANK.getDesc().repeat(Math.max(0, left));
    }
    public static String getBadges(Member member) {
        StringBuilder sb = new StringBuilder();
        for(User.UserFlag flag : member.getUser().getFlags()){
            if(sb.length() <= 0) {
                sb.append(" ");
            }
            switch (flag) {
                case STAFF -> sb.append(Emote.STAFF.getDesc());
                case PARTNER -> sb.append(Emote.PARTNER.getDesc());
                case HYPESQUAD -> sb.append(Emote.HYPESQUAD.getDesc());
                case BUG_HUNTER_LEVEL_1 -> sb.append(Emote.BUG_HUNTER_LEVEL_1.getDesc());
                case HYPESQUAD_BRAVERY -> sb.append(Emote.BRAVERY.getDesc());
                case HYPESQUAD_BRILLIANCE -> sb.append(Emote.BRILLIANCE.getDesc());
                case HYPESQUAD_BALANCE -> sb.append(Emote.BALANCE.getDesc());
                case EARLY_SUPPORTER -> sb.append(Emote.EARLY_SUPPORTER.getDesc());
                case BUG_HUNTER_LEVEL_2 -> sb.append(Emote.BUG_HUNTER_LEVEL_2.getDesc());
                case VERIFIED_DEVELOPER -> sb.append(Emote.DEVELOPER.getDesc());
                case CERTIFIED_MODERATOR -> sb.append(Emote.MODERATOR.getDesc());
            }
        }
        return sb.toString();
    }
}
