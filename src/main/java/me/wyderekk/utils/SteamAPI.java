package me.wyderekk.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SteamAPI {
    private static final String STATTRAK = encodeString("StatTrak™ ");
    private static final String URL = "https://steamcommunity.com/market/priceoverview/?appid=730&currency=6&market_hash_name=";

    public static String[] makeRequest(String marketHash, String condition, boolean statTrak) {
        String url = URL + (statTrak ? STATTRAK : "") + convertName(marketHash) + encodeString(getCondition(condition));
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            JSONObject obj = JsonUtil.stringToJSON(doc.text());
            if(url.contains(encodeString("★"))) {
                String lowestPrice = (String) obj.get("lowest_price");
                return new String[]{lowestPrice};
            } else {
                String lowestPrice = (String) obj.get("lowest_price");
                String medianPrice = (String) obj.get("median_price");
                String volume = (String) obj.get("volume");
                return new String[]{lowestPrice, medianPrice, volume};
            }
        } catch (IOException e) {
            System.out.println(url);
            return null;
        }
    }

    public static String[] makeRequest(String marketHash, boolean statTrak) {
        String url = URL + "★ " + (statTrak ? STATTRAK : "") + encodeString(marketHash);
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            JSONObject obj = JsonUtil.stringToJSON(doc.text());
            String lowestPrice = (String) obj.get("lowest_price");
            return new String[] {lowestPrice};
        } catch (IOException e) {
//            System.out.println(url);
            return null;
        }
    }
    public static String[] makeRequest(String marketHash) {
        String url = URL + encodeString(marketHash);
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            JSONObject obj = JsonUtil.stringToJSON(doc.text());
            String lowestPrice = (String) obj.get("lowest_price");
            String medianPrice = (String) obj.get("median_price");
            String volume = (String) obj.get("volume");
            return new String[]{lowestPrice, medianPrice, volume};
        } catch (IOException e) {
//            System.out.println(url);
            return null;
        }
    }

    public static String[] makeRequest(String marketHash, String condition) {
        String url = URL + encodeString(marketHash) + encodeString(getCondition(condition));
        try {
            Document doc = Jsoup.connect(url).ignoreContentType(true).get();
            JSONObject obj = JsonUtil.stringToJSON(doc.text());
            String lowestPrice = (String) obj.get("lowest_price");
            return new String[]{lowestPrice};
        } catch (IOException e) {
//            System.out.println(url);
            return null;
        }
    }
    private static String encodeString(String stringToEncode) {
        return URLEncoder.encode(stringToEncode, StandardCharsets.UTF_8);
    }

    private static String decodeString(String stringToDecode) {
        return URLDecoder.decode(stringToDecode, StandardCharsets.UTF_8);
    }

    public static String getCondition(String condition) {
        return switch (condition) {
            case "factory_new" -> " (Factory New)";
            case "minimal_wear" -> " (Minimal Wear)";
            case "field_tested" -> " (Field-Tested)";
            case "well_worn" -> " (Well-Worn)";
            case "battle_scarred" -> " (Battle-Scarred)";
            default -> null;
        };
    }
    private static String convertName(String marketHash) {
        if(StringUtils.containsIgnoreCase(marketHash, "dragon king")) {
            marketHash = marketHash.replace("dragon king", "龍王 (Dragon King)");
        } else if (StringUtils.containsIgnoreCase(marketHash, "karambit") || StringUtils.containsIgnoreCase(marketHash, "bayonet") || StringUtils.containsIgnoreCase(marketHash, "bowie")
                || StringUtils.containsIgnoreCase(marketHash, "gut") || StringUtils.containsIgnoreCase(marketHash, "falchion") || StringUtils.containsIgnoreCase(marketHash, "butterfly")
                || StringUtils.containsIgnoreCase(marketHash, "hunstman") || StringUtils.containsIgnoreCase(marketHash, "navaja") || StringUtils.containsIgnoreCase(marketHash, "paracord")
                || StringUtils.containsIgnoreCase(marketHash, "skeleton") || StringUtils.containsIgnoreCase(marketHash, "daggers") || StringUtils.containsIgnoreCase(marketHash, "survival knife")
                || StringUtils.containsIgnoreCase(marketHash, "talon") || StringUtils.containsIgnoreCase(marketHash, "ursus") || StringUtils.containsIgnoreCase(marketHash, "nomad")
                || StringUtils.containsIgnoreCase(marketHash, "stiletto") || StringUtils.containsIgnoreCase(marketHash, "flip")) {
            marketHash = "★ " + marketHash;
        }
        return encodeString(marketHash);
    }
}
