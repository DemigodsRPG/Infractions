package com.demigodsrpg.infractions.bungee.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public abstract class BaseCommand extends Command {

    public BaseCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        CommandResult result = onCommand(sender, args);
        switch (result) {
            case SUCCESS:
            case QUIET_ERROR:
                break;
            case INVALID_SYNTAX:
                sender.sendMessage(new ComponentBuilder(
                        "Invalid syntax, please try again.").color(
                        ChatColor.RED).create());
                break;
            case NO_PERMISSIONS:
                sender.sendMessage(new ComponentBuilder(
                        "You don't have the permissions to use this command.").color(
                        ChatColor.RED).create());
                break;
            case CONSOLE_ONLY:
                sender.sendMessage(new ComponentBuilder(
                        "This command is for the console only.").color(
                        ChatColor.RED).create());
                break;
            case PLAYER_ONLY:
                sender.sendMessage(new ComponentBuilder(
                        "This command can only be used by a player.").color(
                        ChatColor.RED).create());
                break;
            case ERROR:
                sender.sendMessage(new ComponentBuilder(
                        "An error occurred, please check the console.").color(
                        ChatColor.RED).create());
                break;
            case UNKNOWN:
            default:
                sender.sendMessage(new ComponentBuilder(
                        "The command can't run for some unknown reason.").color(
                        ChatColor.RED).create());
        }
    }

    public abstract CommandResult onCommand(CommandSender sender, String[] args);
}
