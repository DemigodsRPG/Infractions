/*
 * Copyright 2015 Demigods RPG
 * Copyright 2015 Alexander Chauncey
 * Copyright 2015 Alex Bennett
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demigodsrpg.infractions.gui;

import com.demigodsrpg.infractions.InfractionsP;
import com.demigodsrpg.infractions.data.*;
import com.google.common.collect.ImmutableMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class HistoryGUI implements InventoryGUI {
    private final String INVENTORY_NAME;
    private final List<Inventory> INVENTORY_LIST;
    private final ImmutableMap<Integer, String> FUNCTION_MAP;

    public HistoryGUI(final Player player) {
        // Inventory name
        INVENTORY_NAME = player.getName() + "'s History";

        // Dossier of player
        Dossier dossier = new Dossier(player.getUniqueId().toString());

        // FUNCTION MAP
        ImmutableMap.Builder<Integer, String> builder = ImmutableMap.builder();

        for (int i = 0; i < 18; i++) {
            builder.put(i, SlotFunction.NO_FUNCTION);
        }

        builder.put(25, SlotFunction.PREVIOUS_PAGE);
        builder.put(26, SlotFunction.NEXT_PAGE);

        FUNCTION_MAP = builder.build();

        // INVENTORY LIST
        INVENTORY_LIST = new ArrayList<>();
        List<ItemStack> items = new ArrayList<>();
        int count = 0, icount = 0;
        Iterator<Record> infractions = dossier.getInfractions().iterator();
        while (infractions.hasNext()) {
            Record infraction = infractions.next();
            final int value = infraction.getValue();
            final String reason = infraction.getReason();
            final Proof proof = infraction.getProof();
            final Date time = infraction.getDate();
            final String issuer = Bukkit.getOfflinePlayer(infraction.getIssuerId()).getName();

            items.add(count, new ItemStack(getMaterial(value), 1) {
                {
                    ItemMeta meta = getItemMeta();
                    meta.setDisplayName(reason);
                    meta.setLore(Arrays.asList(ChatColor.YELLOW + "Value: " +
                                    ChatColor.LIGHT_PURPLE + "-" + value + "%", time.toGMTString(), "Proof: " + proof,
                            "Issuer: " + issuer));
                    setItemMeta(meta);
                }
            });

            count++;

            if (count % 19 == 0 || !infractions.hasNext()) {
                Inventory inventory = Bukkit.createInventory(player, 27, INVENTORY_NAME + " " + icount);
                for (int i = 0; i < items.size(); i++) {
                    inventory.setItem(i, items.get(i));
                }
                if (icount > 0) {
                    inventory.setItem(25, new ItemStack(Material.PAPER, 1) {
                        {
                            ItemMeta meta = getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "< PREV");
                            setItemMeta(meta);
                        }
                    });
                }
                if (infractions.hasNext()) {
                    inventory.setItem(26, new ItemStack(Material.PAPER, 1) {
                        {
                            ItemMeta meta = getItemMeta();
                            meta.setDisplayName(ChatColor.GOLD + "NEXT >");
                            setItemMeta(meta);
                        }
                    });
                }

                items.clear();
                count = 0;

                INVENTORY_LIST.add(inventory);
                icount++;
            }
        }
    }

    @Override
    public Inventory getInventory(Integer... inventory) {
        if (INVENTORY_LIST.size() < 1) {
            return null;
        }
        if (inventory.length == 0) {
            return INVENTORY_LIST.get(0);
        }
        return INVENTORY_LIST.get(inventory[0]);
    }

    @Override
    public String getFunction(int slot) {
        if (FUNCTION_MAP.containsKey(slot)) {
            return FUNCTION_MAP.get(slot);
        }
        return SlotFunction.NO_FUNCTION;
    }

    private Material getMaterial(int value) {
        double ratio = value / InfractionsP.getOptions().maxScore();
        if (ratio == 1) {
            return Material.SKULL_ITEM;
        }
        if (ratio >= .75) {
            return Material.ROTTEN_FLESH;
        }
        if (ratio >= .5) {
            return Material.POISONOUS_POTATO;
        }
        if (ratio >= .25) {
            return Material.BROWN_MUSHROOM;
        }
        return Material.RED_MUSHROOM;
    }
}
