package me.wyderekk.main;

import me.wyderekk.cmd.Cmd;
import me.wyderekk.cmd.CmdManager;
import me.wyderekk.utils.JsonUtil;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.json.simple.parser.JSONParser;
import java.awt.*;

public class Main {

    public static final String path = "src/main/java/me/wyderekk/config/config.json";
    public static final Color color = new Color(53, 47, 110);
    public static JSONParser parser = new JSONParser();

    public static void main(String[] args) {
        initializeBot();
        Cmd.initializeCmd();
    }

    private static void initializeBot() {
        try {
            DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(JsonUtil.getToken());
            builder.setStatus(OnlineStatus.ONLINE);
            builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES);
            builder.setMemberCachePolicy(MemberCachePolicy.ALL);
            builder.setChunkingFilter(ChunkingFilter.ALL);
            builder.enableCache(CacheFlag.ONLINE_STATUS);
            builder.setActivity(Activity.watching("wyderekk"));
            ShardManager shardManager = builder.build();
            shardManager.addEventListener(new CmdManager());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
