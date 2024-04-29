package com.beauver.libs.minecraft.usefulutils.schedulers;

import com.beauver.libs.minecraft.usefulutils.UsefulUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public class SimpleSchedule {

    public static int runAsyncLater(Plugin plugin, Consumer<Void> func, int delaySeconds){
        return Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                func.accept(null);
            }
        }, 20L * delaySeconds).getTaskId();
    }

    public static int runAsync(Plugin plugin, Consumer<Void> func){
        return Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                func.accept(null);
            }
        }).getTaskId();
    }

    public static int runLater(Plugin plugin, Consumer<Void> func, int delaySeconds){
        return Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                func.accept(null);
            }
        }, 20L * delaySeconds).getTaskId();
    }

    public static int runLoopAsync(Plugin plugin, Consumer<Void> func, int delaySeconds, int periodSeconds){
        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                func.accept(null);
            }
        }, 20L * delaySeconds, 20L * periodSeconds).getTaskId();
    }

    public static int runLoop(Plugin plugin, Consumer<Void> func, int delaySeconds, int periodSeconds){
        return Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                func.accept(null);
            }
        },20L * delaySeconds, 20L * periodSeconds).getTaskId();
    }
}
