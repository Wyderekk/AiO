package me.wyderekk.utils;

public enum Emote {

    ARROWS("<:Strzalki:811910053052416041>"),
    HYPENEON("<a:HypeNeon:645663863861411859>"),
    TAG("<:Tag:811910053292408842>"),
    NAME("<:Name:1064999822320472185>"),
    FULL("<:Full:1064998654408142968>"),
    BLANK("<:Blank:1064998831365828649>"),
    STAFF("<:Staff:1064695956932669523>"),
    BUG_HUNTER_LEVEL_1("<:BugHunter:1064696858787721226>"),
    BUG_HUNTER_LEVEL_2("<:BugHunterGold:1064696856598302760>"),
    DEVELOPER("<:Developer:1064697354172780605>"),
    MODERATOR("<:Moderator:1064697352918667384>"),
    PARTNER("<:Partner:1064696863309185094>"),
    BRAVERY("<:Bravery:1064696855264493599>"),
    BRILLIANCE("<:Brilliance:1064696853486121041>"),
    BALANCE("<:Balance:1064696851367989328>"),
    EARLY_SUPPORTER("<:EarlySupporter:1064697355212967947>"),
    HYPESQUAD("<a:Event:1064696861186854993>");

    Emote(String desc) {
        this.desc = desc;
    }
    private final String desc;
    public String getDesc() {
        return desc;
    }
}
