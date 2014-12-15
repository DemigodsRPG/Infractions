package com.demigodsrpg.infractions.bungee.command;

import com.demigodsrpg.infractions.Backend;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnciteCommand extends BaseCommand {
    private Backend backend;

    public UnciteCommand(Backend backend) {
        super("uncite");
        this.backend = backend;
    }

    @Override
    public CommandResult onCommand(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {

        }
        return CommandResult.PLAYER_ONLY;
    }
}
