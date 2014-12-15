package com.demigodsrpg.infractions.bungee;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.bungee.command.*;
import com.demigodsrpg.infractions.bungee.impl.BungeeBackend;
import com.demigodsrpg.infractions.bungee.impl.BungeeConfig;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.io.File;

public class InfractionsBungee extends Plugin {

    // -- ENABLE & DISABLE -- //

    @Override
    public void onEnable() {
        PluginManager manager = ProxyServer.getInstance().getPluginManager();

        // Config
        BungeeConfig config = new BungeeConfig(this, new File(getDataFolder(), "config.yml"));

        // Backend
        Backend backend = new BungeeBackend(config);

        // Commands
        manager.registerCommand(this, new InfractionsCommand());
        manager.registerCommand(this, new CiteCommand(backend));
        manager.registerCommand(this, new UnciteCommand(backend));
        manager.registerCommand(this, new HistoryCommand(backend));
        manager.registerCommand(this, new ClearHistoryCommand(backend));
    }

    @Override
    public void onDisable() {

    }
}
