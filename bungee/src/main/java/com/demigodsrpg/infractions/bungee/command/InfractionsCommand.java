package com.demigodsrpg.infractions.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class InfractionsCommand extends BaseCommand {
    public InfractionsCommand() {
        super("infractions");
    }

    @Override
    public CommandResult onCommand(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {

        }
        return CommandResult.PLAYER_ONLY;
    }
}
