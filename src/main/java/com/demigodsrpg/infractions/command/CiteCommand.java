package com.demigodsrpg.infractions.command;

import com.demigodsrpg.command.type.BaseCommand;
import com.demigodsrpg.command.type.CommandResult;
import com.demigodsrpg.infractions.InfractionsP;
import com.demigodsrpg.infractions.data.Dossier;
import com.demigodsrpg.infractions.data.Record;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CiteCommand extends BaseCommand {
    @Override
    public CommandResult onCommand(CommandSender sender, Command command, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("infractions.mod")) {
                // Does the cite require proof?
                if (InfractionsP.getOptions().requireProof()) {
                    // TODO
                }
                // TODO TESTING START ---
                Dossier record = new Dossier(((Player) sender).getUniqueId().toString());
                Record cite = new Record(10, "HAX", ((Player) sender).getUniqueId(), ((Player) sender).getUniqueId());
                record.saveRecord(cite);

                sender.sendMessage(ChatColor.YELLOW + "You have infracted urself :P");
                // TODO TESTING END ---
                return CommandResult.SUCCESS;
            } else {
                return CommandResult.NO_PERMISSIONS;
            }
        }
        return CommandResult.PLAYER_ONLY;
    }
}
