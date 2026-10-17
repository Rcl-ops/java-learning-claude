package com.example.hellomod;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 本模组所有物品的注册表。
 *
 * DeferredRegister（延迟注册器）：先"登记"想注册什么，等 NeoForge 在正确的时机统一注册。
 * DeferredItem<Item> 是一个"持有者"，之后用 .get() 才能拿到真正的物品对象——
 * 注意：不要在注册完成之前调用 .get()。
 */
public final class ModItems {

    /** 物品注册器：注册名会自动带上命名空间，如 hellomod:ruby。 */
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HelloMod.MOD_ID);

    /** 红宝石：稀有度 RARE（名字显示为青色）。 props -> ... 是一个 Lambda，用来定制物品属性。 */
    public static final DeferredItem<Item> RUBY =
            ITEMS.registerSimpleItem("ruby", props -> props.rarity(Rarity.RARE));

    /** 红宝石碎片：默认属性。 */
    public static final DeferredItem<Item> RUBY_SHARD = ITEMS.registerSimpleItem("ruby_shard");

    private ModItems() { }       // 工具类，不允许实例化

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
