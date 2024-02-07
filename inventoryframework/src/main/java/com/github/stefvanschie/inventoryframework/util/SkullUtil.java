package com.github.stefvanschie.inventoryframework.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

/**
 * A utility class for working with skulls
 *
 * @since 0.5.0
 */
public final class SkullUtil {

    /**
     * A private constructor to ensure this class isn't instantiated
     *
     * @since 0.5.0
     */
    private SkullUtil() {}

    /**
     * Gets a skull from the specified id. The id is the value from the textures.minecraft.net website after the last
     * '/' character.
     *
     * @param id the skull id
     * @return the skull item
     * @since 0.5.0
     */
    @NotNull
    public static ItemStack getSkull(@NotNull String id) throws MalformedURLException {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = Objects.requireNonNull(item.getItemMeta());
        setSkull((SkullMeta) itemMeta, id);
        item.setItemMeta(itemMeta);
        return item;
    }

    /**
     * Sets the skull of an existing {@link ItemMeta} from the specified id.
     * The id is the value from the textures.minecraft.net website after the last '/' character.
     *
     * @param meta the meta to change
     * @param id the skull id
     */
    public static void setSkull(@NotNull SkullMeta meta, @NotNull String id) throws MalformedURLException {
        final PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        final PlayerTextures textures = profile.getTextures();

        textures.setSkin(new URL("http://textures.minecraft.net/texture/" + id));
        profile.setTextures(textures);

        meta.setOwnerProfile(profile);
    }
}
