package com.demigodsrpg.infractions.spigot;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.spigot.command.CiteCommand;
import com.demigodsrpg.infractions.spigot.command.HistoryCommand;
import com.demigodsrpg.infractions.spigot.command.InfractionsCommand;
import com.demigodsrpg.infractions.spigot.command.UnciteCommand;
import com.demigodsrpg.infractions.spigot.impl.SpigotBackend;
import com.demigodsrpg.infractions.spigot.impl.SpigotOptions;
import com.demigodsrpg.infractions.spigot.util.UrlUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class InfractionsSpigot extends JavaPlugin {
    private static Backend BACKEND;

    @Override
    public void onEnable() {
        // Backend
        BACKEND = new SpigotBackend(new SpigotOptions(this));

        // Register Utils
        UrlUtil.reg(BACKEND);

        // Commands
        getCommand("infractions").setExecutor(new InfractionsCommand(BACKEND));
        getCommand("cite").setExecutor(new CiteCommand(BACKEND));
        getCommand("uncite").setExecutor(new UnciteCommand(BACKEND));
        getCommand("history").setExecutor(new HistoryCommand(BACKEND));
    }

    @Override
    public void onDisable() {

    }

    public static Backend getBackend() {
        return BACKEND;
    }
}
