package com.demigodsrpg.infractions.spigot.util;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.Options;
import com.rosaloves.bitlyj.Bitly;
import com.rosaloves.bitlyj.Url;

import java.net.URI;
import java.net.URL;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

public class UrlUtil {
    private static Options OPTIONS;

    public static void reg(Backend backend) {
        if (OPTIONS == null) {
            UrlUtil.OPTIONS = backend.getOptions();
        }
    }

    /**
     * Converts a Url into a bit.ly shortened Url.
     *
     * @return String
     */
    public static String shortenUrl(String input) {
        if (!OPTIONS.useBitly()) return input;
        if (!input.startsWith("http://") && !input.startsWith("https://")) input = ("http://" + input);
        Bitly.Provider bitly = as(OPTIONS.bitlyUser(), OPTIONS.bitlyKey());
        Url shortUrl = bitly.call(shorten(input));
        return shortUrl.getShortUrl();
    }

    public static boolean isValidUrl(String input) {
        if (!input.startsWith("http://") && !input.startsWith("https://")) {
            input = ("http://" + input);
        }
        try {
            URI uri = new URI(input);
            URL url = uri.toURL();
            java.net.URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
