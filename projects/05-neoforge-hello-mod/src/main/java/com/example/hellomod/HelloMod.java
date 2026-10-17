package com.example.hellomod;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;

/**
 * 模组入口类。
 *
 * @Mod 注解告诉 NeoForge："这个类是模组 hellomod 的入口"。
 * 游戏启动加载模组时，会（通过反射）创建这个类的对象，并调用下面的构造器。
 * 构造器可以声明一个 IEventBus 参数——加载器会自动把"模组事件总线"传进来。
 */
@Mod(HelloMod.MOD_ID)
public class HelloMod {

    /** 模组 ID：必须与 gradle.properties 的 mod_id 一致。 */
    public static final String MOD_ID = "hellomod";

    public static final Logger LOGGER = LogUtils.getLogger();

    public HelloMod(IEventBus modEventBus) {
        // 1. 把"物品注册表"挂到模组事件总线上。注册真正发生在游戏准备好的合适时机，而不是此刻。
        ModItems.register(modEventBus);

        // 2. 订阅事件：this::commonSetup 是方法引用，会被当作"事件处理器（回调）"
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addToCreativeTab);

        LOGGER.info("Hello from {}! 模组已加载。", MOD_ID);
    }

    /** 通用初始化阶段（客户端和服务端都会触发）。 */
    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("{} 通用初始化完成", MOD_ID);
    }

    /** 创造模式物品栏：把我们的物品放进原版的"原材料"标签页。 */
    private void addToCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RUBY.get());
            event.accept(ModItems.RUBY_SHARD.get());
        }
    }
}
