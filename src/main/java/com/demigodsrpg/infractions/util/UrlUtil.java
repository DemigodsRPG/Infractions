package com.demigodsrpg.infractions.util;

import com.demigodsrpg.infractions.InfractionsP;
import com.rosaloves.bitlyj.Bitly;
import com.rosaloves.bitlyj.Url;

import java.net.URI;
import java.net.URL;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

public class UrlUtil {
    /**
     * Converts a Url into a bit.ly shortened Url.
     *
     * @return String
     */
    public static String shortenUrl(String input) {
        if (!InfractionsP.getOptions().useBitly()) return input;
        if (!input.startsWith("http://") && !input.startsWith("https://")) input = ("http://" + input);
        Bitly.Provider bitly = as(InfractionsP.getOptions().bitlyUser(), InfractionsP.getOptions().bitlyKey());
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
