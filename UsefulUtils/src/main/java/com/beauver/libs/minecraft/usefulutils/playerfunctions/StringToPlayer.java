package com.beauver.libs.minecraft.usefulutils.playerfunctions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StringToPlayer {

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

}
