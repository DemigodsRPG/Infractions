package com.demigodsrpg.infractions.bungee.command;

import com.demigodsrpg.infractions.Backend;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class CiteCommand extends BaseCommand {
    private Backend backend;

    public CiteCommand(Backend backend) {
        super("cite");
        this.backend = backend;
    }

    @Override
    public CommandResult onCommand(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            if (sender.hasPermission("infractions.mod")) {
                // Does the cite require proof?
                if (backend.getConfig().requireProof()) {
                    // TODO
                }
            } else {
                return CommandResult.NO_PERMISSIONS;
            }
        }
        return CommandResult.PLAYER_ONLY;
    }
}
