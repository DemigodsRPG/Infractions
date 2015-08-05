package com.demigodsrpg.infractions.spigot.command;

import com.demigodsrpg.infractions.Backend;
import com.demigodsrpg.infractions.Infraction;
import com.demigodsrpg.infractions.PlayerRecord;
import net.md_5.bungee.api.ChatColor;
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
                // TODO TESTING START ---
                PlayerRecord record = new PlayerRecord(backend, ((Player) sender).getUniqueId().toString());
                Infraction cite = new Infraction(10, System.currentTimeMillis(), backend.getOptions().serverName(),
                        "HAX", "idk lol", ((Player) sender).getUniqueId(), ((Player) sender).getUniqueId());
                record.giveInfraction(backend, cite);

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
