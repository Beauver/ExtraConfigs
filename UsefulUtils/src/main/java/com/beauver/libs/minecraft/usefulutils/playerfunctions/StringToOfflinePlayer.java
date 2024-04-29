package com.beauver.libs.minecraft.usefulutils.playerfunctions;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class StringToOfflinePlayer {

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
