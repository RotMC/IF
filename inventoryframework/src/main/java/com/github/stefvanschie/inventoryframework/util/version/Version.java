package com.github.stefvanschie.inventoryframework.util.version;

import com.github.stefvanschie.inventoryframework.exception.UnsupportedVersionException;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

/**
 * The different supported NMS versions
 *
 * @since 0.8.0
 */
public enum Version {
    V1_21_1;

    /**
     * A collection of versions on which modern smithing tables are available.
     */
    private static final Collection<Version> MODERN_SMITHING_TABLE_VERSIONS =
            EnumSet.of(V1_21_1);

    /**
     * A collection of versions on which legacy smithing tables ae available.
     */
    @NotNull
    private static final Collection<@NotNull Version> LEGACY_SMITHING_TABLE_VERSIONS = List.of();

    /**
     * Checks whether modern smithing tables exist on this version. Returns true if they do, otherwise false.
     *
     * @return true if modern smithing tables are available
     * @since 0.10.10
     */
    boolean existsModernSmithingTable() {
        return MODERN_SMITHING_TABLE_VERSIONS.contains(this);
    }

    /**
     * Checks whether legacy smithing tables exist on this version. Returns true if they do, otherwise false.
     *
     * @return true if legacy smithing tables are available
     * @since 0.10.10
     */
    @Contract(pure = true)
    boolean existsLegacySmithingTable() {
        return LEGACY_SMITHING_TABLE_VERSIONS.contains(this);
    }

    /**
     * Gets the version currently being used. If the used version is not supported, an
     * {@link UnsupportedVersionException} will be thrown.
     *
     * @return the version of the current instance
     * @since 0.8.0
     */
    @NotNull
    @Contract(pure = true)
    public static Version getVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];

        return switch (version) {
            case "1.21.1" -> V1_21_1;
            default -> throw new UnsupportedVersionException("The server version provided is not supported");
        };
    }
}
