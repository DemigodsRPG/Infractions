package com.demigodsrpg.infractions.spigot.command;

import com.demigodsrpg.infractions.Backend;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnciteCommand extends BaseCommand {
    private Backend backend;

    public UnciteCommand(Backend backend) {
        this.backend = backend;
    }

    @Override
    public CommandResult onCommand(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {

        }
        return CommandResult.PLAYER_ONLY;
    }
}
