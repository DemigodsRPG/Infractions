package com.demigodsrpg.infractions.chitchat;

import com.demigodsrpg.chitchat.tag.PlayerTag;
import com.demigodsrpg.infractions.Options;
import com.demigodsrpg.infractions.data.Dossier;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ReputationTag extends PlayerTag {
    private final int MAX_SCORE;
    private final int[] RANGES;
    private final List<TextComponent> SMILIES;
    private final String[] SMILE_STR;
    private final ChatColor[] COLORS;

    public ReputationTag(Options options) {
        MAX_SCORE = options.maxScore();
        RANGES = new int[]{MAX_SCORE / 6, MAX_SCORE / 3, MAX_SCORE * 2 / 3, MAX_SCORE};
        SMILIES = new ArrayList<>();
        SMILE_STR = new String[]{":(", ":/", ":)", ":D", ":3"};
        COLORS = new ChatColor[]{ChatColor.RED, ChatColor.GRAY, ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.AQUA};
        for (int i = 0; i < SMILE_STR.length; i++) {
            SMILIES.add(createSmiley(i));
        }
    }

    @Override
    public String getName() {
        return "Reputation";
    }

    @Override
    public int getPriority() {
        return 1;
    }

    public TextComponent getComponentFor(Player tagSource) {
        // Player data
        Dossier record = new Dossier(tagSource.getUniqueId().toString());
        int score = record.getScore();

        // Declare the smiley
        TextComponent smiley = null;

        // Iterate until you find the right one
        int count = 0;
        for (int range : RANGES) {
            if (range >= score) {
                smiley = new TextComponent(SMILIES.get(count));
                break;
            }
            count++;
        }

        if (smiley == null) {
            smiley = new TextComponent(SMILIES.get(count));
        }

        smiley.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Rep: " + score / MAX_SCORE * 100 + "%").color(COLORS[count]).create()));
        smiley.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/history " + tagSource.getName()));
        return smiley;
    }

    private TextComponent createSmiley(int index) {
        TextComponent smiley = new TextComponent("[");
        smiley.setColor(ChatColor.DARK_GRAY);
        addExtras(smiley, new ComponentBuilder(SMILE_STR[index]).color(COLORS[index]).create());
        TextComponent end = new TextComponent("]");
        end.setColor(ChatColor.DARK_GRAY);
        smiley.addExtra(end);
        return smiley;
    }

    private void addExtras(TextComponent component, BaseComponent[] extras) {
        for (BaseComponent extra : extras) {
            component.addExtra(extra);
        }
    }
}
