package com.demigodsrpg.infractions.command;

import com.demigodsrpg.command.type.BaseCommand;
import com.demigodsrpg.command.type.CommandResult;
import com.demigodsrpg.infractions.gui.HistoryGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HistoryCommand extends BaseCommand {
    @Override
    public CommandResult onCommand(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(new HistoryGUI(player).getInventory(0));
        }
        return CommandResult.PLAYER_ONLY;
    }
}
