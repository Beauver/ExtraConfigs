package com.beauver.libs.minecraft.extraconfigs;

import it.unimi.dsi.fastutil.Pair;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public final class ExtraConfigs extends JavaPlugin {
    private final static HashMap<String, Pair<File, FileConfiguration>> configs = new HashMap<>();

    /**
     Creates/Initializes the config file.
     If you have the config file in /resources/{name.yml} it will automatically copy the details inside that file.
     @params mainPlugin; Your main plugin file
     @params configFileName; The name of your configuration file (without the .yml extension)
     **/
    public static void initConfig(Plugin mainPlugin, String configFileName){
        File configFile;
        File pluginFolder = mainPlugin.getDataFolder();
        if(!pluginFolder.exists()){
            boolean madeDirs = pluginFolder.mkdirs();
            if(!madeDirs){
                mainPlugin.getLogger().warning("ExtraConfig | Could not create plugin folder");
                throw new RuntimeException("Could not create plugin folder");
            }
        }
        configFile = new File(pluginFolder, configFileName + ".yml");
        configs.put(configFileName, Pair.of(configFile, null));

        if(!configFile.exists()){
            try{
                boolean madeFile = configFile.createNewFile();
                if(!madeFile){
                    mainPlugin.getLogger().warning("ExtraConfig | Could not create config file");
                    throw new RuntimeException("Could not create config file");
                }
                loadDefaultValues(mainPlugin, configFileName, configFile);
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        loadConfig(configFileName);
    }

    /**
     * Loads the already given variables in the {configname.yml} file.
     * @params mainPlugin; Your main plugin file
     * @params configFileName; the name of the config file (without the .yml extension)
     * @params configFile; the config file itself
     */
    private static void loadDefaultValues(Plugin mainPlugin, String configFileName, File configFile){
        try (InputStream defaultConfigStream = mainPlugin.getResource(configFileName + ".yml")) {
            if (defaultConfigStream != null) {
                Files.copy(defaultConfigStream, configFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                configs.put(configFileName, Pair.of(configFile, YamlConfiguration.loadConfiguration(configFile)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the config to initialize it.
     * @params configFileName; the name of the config file (without the .yml extension)
     */
    private static void loadConfig(String configFileName){
        Pair<File, FileConfiguration> configFile = configs.get(configFileName);
        configs.remove(configFileName);
        configs.put(configFileName, Pair.of(configFile.left(), YamlConfiguration.loadConfiguration(configFile.left())));
    }

    /**
     * Reloads the config file
     * @params configFileName; the name of the config file (without the .yml extension)
     * @return boolean, true if file exists, false if not
     */
    public static boolean reloadConfig(String configFileName){
        if(configs.get(configFileName) == null){
            return false;
        }
        loadConfig(configFileName);
        return true;
    }

    /**
     * @params configFileName without extension
     * @return the config yml file
     */
    public static Pair<File, FileConfiguration> getConfig(String configFileName){
        return configs.get(configFileName);
    }
}
