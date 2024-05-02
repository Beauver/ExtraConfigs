package com.beauver.libs.minecraft.usefulutils.converters;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerConverter {

    public static OfflinePlayer playerToOfflinePlayer(Player player){
        return Bukkit.getOfflinePlayer(player.getUniqueId());
    }

    public static Player offlinePlayerToPlayer(OfflinePlayer player){
        return Bukkit.getPlayer(player.getUniqueId());
    }

    public static Player getPlayerObj(String[] args){
        if(args.length == 0){ return null; }
        String playerName = args[0];
        return Bukkit.getPlayer(playerName);
    }

    public static Player getPlayerObj(String args){
        return Bukkit.getPlayer(args);
    }

    public static Player getPlayerObjIncludeErrors(Player player, String[] args){
        if(args.length == 0){
            player.sendRichMessage("<red>Please specify a player to use this command.</red>");
            return null;
        }
        String playerName = args[0];
        return getPlayerObjIncludeErrors(player, playerName);
    }

    public static Player getPlayerObjIncludeErrors(Player player, String args){
        Player player1 = Bukkit.getPlayer(args);
        if(player1 == null){
            player.sendRichMessage("<red>Player could not be found. Are they offline?</red>");
            return null;
        }
        return player1;
    }

    public static OfflinePlayer getOfflinePlayerObj(String[] args){
        if(args.length == 0){ return null; }
        String playerName = args[0];
        return Bukkit.getOfflinePlayer(playerName);
    }

    public static OfflinePlayer getOfflinePlayerObj(String args){
        return Bukkit.getOfflinePlayer(args);
    }

    public static OfflinePlayer getOfflinePlayerObjIncludeErrors(Player player, String[] args){
        if(args.length == 0){
            player.sendRichMessage("<red>Please specify a player to use this command.</red>");
            return null;
        }
        String playerName = args[0];
        return Bukkit.getOfflinePlayer(playerName);
    }

}
