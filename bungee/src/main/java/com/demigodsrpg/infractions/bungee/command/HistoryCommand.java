package com.demigodsrpg.infractions.bungee.command;

import com.demigodsrpg.infractions.Backend;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class HistoryCommand extends BaseCommand {
    private Backend backend;

    public HistoryCommand(Backend backend) {
        super("history");
        this.backend = backend;
    }

    @Override
    public CommandResult onCommand(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {

        }
        return CommandResult.PLAYER_ONLY;
    }
}
