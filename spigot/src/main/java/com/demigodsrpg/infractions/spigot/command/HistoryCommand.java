package com.demigodsrpg.infractions.spigot.command;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.spigot.gui.HistoryGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HistoryCommand extends BaseCommand {
    private Backend backend;

    public HistoryCommand(Backend backend) {
        this.backend = backend;
    }

    @Override
    public CommandResult onCommand(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(new HistoryGUI(player).getInventory(0));
        }
        return CommandResult.PLAYER_ONLY;
    }
}
