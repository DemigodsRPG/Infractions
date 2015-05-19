package com.demigodsrpg.infractions.spigot.command;

import com.demigodsrpg.infractions.Backend;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CiteCommand extends BaseCommand {
    private Backend backend;

    public CiteCommand(Backend backend) {
        this.backend = backend;
    }

    @Override
    public CommandResult onCommand(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("infractions.mod")) {
                // Does the cite require proof?
                if (backend.getOptions().requireProof()) {
                    // TODO
                }
            } else {
                return CommandResult.NO_PERMISSIONS;
            }
        }
        return CommandResult.PLAYER_ONLY;
    }
}
