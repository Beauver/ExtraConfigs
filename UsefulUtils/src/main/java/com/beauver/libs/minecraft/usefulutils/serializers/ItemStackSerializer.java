package com.beauver.libs.minecraft.usefulutils.serializers;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemStackSerializer {

    public static String serializeItemStack(ItemStack item) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
        dataOutput.writeObject(item);
        dataOutput.close();
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    public static ItemStack deserializeItemStack(String serializedItem) throws IOException, ClassNotFoundException {
        byte[] serializedData = Base64.getDecoder().decode(serializedItem);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(serializedData);
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
        ItemStack deserializedItem = (ItemStack) dataInput.readObject();
        dataInput.close();
        return deserializedItem;
    }

}
