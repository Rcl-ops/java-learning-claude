# NeoForge 最简模组 · 逐步讲解

本文把模组项目当作一份"Java 知识的应用题"来讲：每一处代码都对应你在教程里学过的概念。

## 0. 模组是什么？

Minecraft 是一个 Java 程序。**模组加载器**（NeoForge）在游戏启动时扫描 `mods` 目录里的 jar，读取每个 jar 里的 `neoforge.mods.toml`，找到带 `@Mod` 注解的类并创建它，从而让你的代码参与游戏。你写的代码主要做两件事：

1. **注册内容**（物品、方块、实体……）——告诉游戏"世界里多了这些东西"；
2. **订阅事件**（玩家登录、方块被破坏、创造栏构建……）——在特定时刻执行你的逻辑。

## 1. 模组的"三类文件"

| 类别 | 位置 | 作用 |
|------|------|------|
| 构建配置 | `build.gradle`、`gradle.properties`、`settings.gradle` | 怎样编译、依赖哪个版本的 NeoForge |
| 元数据 | `src/main/templates/META-INF/neoforge.mods.toml` | 模组 ID、名称、版本、依赖 |
| 代码 + 资源 | `src/main/java`、`src/main/resources` | 逻辑 + 贴图/模型/语言 |

### gradle.properties（配置中心）

```properties
minecraft_version=26.1
neo_version=26.1.0.19-beta      # 必须与 Minecraft 版本匹配
mod_id=hellomod                 # 命名空间，必须小写、2~64 位
mod_group_id=com.example.hellomod   # 顶层包名
```

构建时 `build.gradle` 的 `generateModMetadata` 任务把这些值填入 `neoforge.mods.toml` 里的 `${mod_id}` 等占位符，所以**只改一处**即可。
> 唯一的例外：`@Mod("hellomod")` 里的字符串是 Java 代码，需要你**手动**保持和 `mod_id` 一致。

### neoforge.mods.toml（模组身份证）

```toml
modLoader = "javafml"          # 使用 Java 语言加载器
license = "${mod_license}"

[[mods]]                        # [[ ]] 表示"表的数组"：一个 jar 可以包含多个模组
modId = "${mod_id}"
version = "${mod_version}"
displayName = "${mod_name}"

[[dependencies.${mod_id}]]      # 声明依赖：NeoForge 与 Minecraft 的版本范围
modId = "neoforge"
type = "required"
versionRange = "[${neo_version},)"
```

## 2. HelloMod.java —— 入口类

```java
@Mod(HelloMod.MOD_ID)
public class HelloMod {
    public static final String MOD_ID = "hellomod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HelloMod(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addToCreativeTab);
        LOGGER.info("Hello from {}! 模组已加载。", MOD_ID);
    }
    ...
}
```

逐点解读（括号内是对应的 Java 章节）：

| 代码 | Java 知识 |
|------|-----------|
| `@Mod(...)` | **注解**（19）：元数据，加载器用**反射**读取它并找到入口类 |
| `public static final String MOD_ID` | **常量**：`static final` + 全大写命名（02、09） |
| `LogUtils.getLogger()` | 静态工厂方法；日志用 `{}` 占位符，避免字符串拼接（21） |
| 构造器 `HelloMod(IEventBus modEventBus)` | **构造器**（09）。参数由加载器**注入**：你声明需要什么，它就给你什么 |
| `modEventBus.addListener(this::commonSetup)` | **方法引用**（15）作为**回调**；这是**观察者模式**（23）：事件总线 = 发布/订阅 |
| `private void commonSetup(FMLCommonSetupEvent event)` | 方法参数的类型（事件类）决定它订阅哪种事件，**泛型**与多态的应用（10、12） |
| `event.getTabKey() == CreativeModeTabs.INGREDIENTS` | 对"键"对象用 `==` 比较是可以的，因为它们是**单例常量**，而不是普通字符串比较（03） |

**两条事件总线**：*模组事件总线*（`modEventBus`）用于加载阶段的事件（注册、初始化、创造栏构建）；*游戏事件总线*（`NeoForge.EVENT_BUS`）用于游戏运行时事件（玩家、方块、实体……）。把监听器挂错总线是新手最常见的错误之一。

## 3. ModItems.java —— 注册物品

```java
public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HelloMod.MOD_ID);

public static final DeferredItem<Item> RUBY =
        ITEMS.registerSimpleItem("ruby", props -> props.rarity(Rarity.RARE));

public static final DeferredItem<Item> RUBY_SHARD = ITEMS.registerSimpleItem("ruby_shard");
```

| 概念 | 说明 |
|------|------|
| **注册表（Registry）** | 游戏里每种内容（物品、方块……）都在对应的注册表里，以 `命名空间:名字` 为键，如 `hellomod:ruby`、`minecraft:apple` |
| **DeferredRegister** | "延迟注册器"：你先登记"我要注册什么"，NeoForge 在注册事件的正确时机再真正执行。**泛型**（12）：`DeferredRegister<T>` 针对不同注册表 |
| **DeferredItem\<Item\>** | 一个"持有者"，类似 `Supplier<Item>`（15）。在注册完成之前调用 `.get()` 会出错，所以只在事件处理方法等"稍后"的地方用 |
| `props -> props.rarity(Rarity.RARE)` | **Lambda**（15）：接收默认的 `Item.Properties`，返回修改后的；这里用了**构建者/链式调用**风格 |
| `private ModItems() {}` | 工具类禁止实例化（09） |

> **为什么要这么麻烦（延迟）？** 游戏对注册顺序和时机有严格要求，过早/过晚创建对象会出 bug。`DeferredRegister` 把"何时执行"交给框架，你只需要声明"是什么"。

## 4. 资源文件（物品不只是 Java 代码）

一个物品要在游戏里"看起来像样"，需要几份 JSON 与图片（均以 `assets/<mod_id>/` 开头）：

```
items/ruby.json          客户端物品定义：这个物品用哪个模型
models/item/ruby.json    模型：继承 minecraft:item/generated（"平面 2D 物品"），贴图是 layer0
textures/item/ruby.png   16×16 的 PNG 贴图
lang/en_us.json          "item.hellomod.ruby": "Ruby"
lang/zh_cn.json          "item.hellomod.ruby": "红宝石"
```

文件名必须与注册名一致（`ruby`）。**翻译键**的规则：`item.<模组ID>.<注册名>`。
如果物品显示成黑紫色方块（缺失贴图）或名称显示成 `item.hellomod.ruby`，几乎一定是这里的路径或名字拼错了——检查日志里的警告。

> 这些 JSON 也可以用**数据生成（Datagen）** 让代码自动产出，当模组变大时就不用手写了。（`./gradlew runData`）

## 5. 构建与发布

```bash
./gradlew build          # 产物：build/libs/hellomod-1.0.0.jar
```

把 jar 放进安装了同版本 NeoForge 的游戏的 `mods/` 目录即可。上传到 Modrinth / CurseForge 时需要注明 Minecraft 版本、加载器（NeoForge）、依赖。

## 6. 常见问题

| 现象 | 可能的原因 / 解决 |
|------|-------------------|
| Gradle 同步失败："no matching toolchains" | 没有 JDK 25；安装 JDK 25 或确认 `settings.gradle` 的 foojay 插件生效 |
| 找不到 `net.neoforged...` 的类 | Gradle 没有同步成功；刷新 Gradle；检查网络/代理 |
| 启动时提示依赖版本不匹配 | `minecraft_version`、`neo_version`、`minecraft_version_range` 三者必须对应 |
| 物品没出现在创造栏 | 监听器没有挂到模组事件总线；`event.getTabKey()` 判断的标签页不对 |
| 物品名称显示为 `item.hellomod.ruby` | 缺少 lang 文件或键名错误 |
| 物品贴图是黑紫方块 | `items/`、`models/item/`、`textures/item/` 的路径或文件名不一致 |
| 服务端启动崩溃 | 在通用代码里引用了仅客户端的类（如 `Minecraft`、渲染类）；客户端代码要放进 `@Mod(dist = Dist.CLIENT)` 的类或客户端事件里 |
| 编译错误：方法/类不存在 | 你的 Minecraft 版本与教程不同，API 变了；查看对应版本文档与 Porting Primer |
| 首次导入非常慢 | 正常，Gradle 要下载并反编译 Minecraft；可能需要十几分钟到一小时 |

## 7. 挑战：继续扩展（由易到难）

> 以下代码是"思路示例"，API 细节随版本变化（尤其是 26.x），请以 [官方文档](https://docs.neoforged.net/) 对应版本为准。

### 挑战 1：自定义物品属性

```java
public static final DeferredItem<Item> LUCKY_RUBY = ITEMS.registerSimpleItem("lucky_ruby",
        props -> props.stacksTo(16).rarity(Rarity.EPIC).fireResistant());
```
记得为它增加 `items/`、`models/item/`、`textures/item/` 和 lang 条目。

### 挑战 2：自定义 `Item` 子类（右键执行逻辑）

继承 `Item` 并重写使用方法（版本不同，方法签名可能有细微区别，建议在 IDE 中打开 `Item` 类查看 `use` 方法）：

```java
public class MagicRubyItem extends Item {
    public MagicRubyItem(Properties properties) { super(properties); }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {                       // 逻辑应只在服务端执行
            // 例如：给玩家发一条消息、治疗、传送……
        }
        return InteractionResult.SUCCESS;
    }
}
// 注册：带构造器引用，这就是为什么 registerItem 要接收一个"工厂"
public static final DeferredItem<MagicRubyItem> MAGIC_RUBY = ITEMS.registerItem("magic_ruby", MagicRubyItem::new);
```
这里综合运用了：**继承与重写**（10）、`@Override`、**构造器引用**（15）、**泛型**（12）。
关键概念：**逻辑端（客户端/服务端）**——玩家看到的世界在客户端，权威的游戏状态在服务端，修改世界的逻辑必须在服务端进行，并用 `level.isClientSide()` 区分。

### 挑战 3：食物

给物品添加食物属性与可食用组件（`Item.Properties#food` 等，见官方 Items / Consumables 文档）。

### 挑战 4：自己的创造模式标签页

用 `DeferredRegister<CreativeModeTab>` 注册 `CreativeModeTab.builder()...`（见官方 Items → Custom Creative Tabs），并添加翻译键 `itemGroup.hellomod.example`。

### 挑战 5：合成配方与战利品

在 `data/hellomod/recipe/`（数据包 JSON）添加合成配方：例如 4 个红宝石碎片合成 1 个红宝石。配方 JSON 的格式在 1.21.x → 26.x 间有变化，请参考官方 Recipes 文档，或让 Datagen 生成。

### 挑战 6：第一个方块

`DeferredRegister.Blocks` + `registerSimpleBlock("ruby_block", props -> props.strength(3.0f))` + 方块状态 JSON、模型、方块物品（`registerSimpleBlockItem`）、战利品表（让它掉落自己）。

### 挑战 7：监听游戏事件

在游戏事件总线订阅事件，例如玩家登录时打印日志：

```java
// 在构造器里：NeoForge.EVENT_BUS.addListener(this::onPlayerLogin);
private void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
    LOGGER.info("欢迎 {}", event.getEntity().getName().getString());
}
```

## 8. Java 知识 ↔ 模组开发 对照表

| Java 知识 | 在模组里的体现 |
|-----------|----------------|
| 类与对象、`static`、构造器 | 入口类、注册器常量、事件对象 |
| 继承与多态 | `extends Item/Block`，重写 `use`、`useOn` 等 |
| 接口与函数式接口 | `Supplier`、事件监听器、`UnaryOperator<Item.Properties>` |
| Lambda 与方法引用 | `props -> ...`、`this::commonSetup`、`MyItem::new` |
| 泛型 | `DeferredRegister<T>`、`DeferredHolder<R, T>`、`Registry<T>` |
| 注解与反射 | `@Mod`、`@SubscribeEvent`、`@EventBusSubscriber` |
| 设计模式 | 观察者（事件总线）、工厂（注册工厂）、建造者（`Properties`、`CreativeModeTab.builder()`） |
| 集合与 Stream | 处理物品栏、实体列表、方块列表 |
| 异常处理与日志 | 日志、防御式编程，避免在游戏线程里抛出未处理异常 |
| 并发 | 服务器线程 vs 客户端线程，网络包处理需要 `enqueueWork` 回到主线程 |
| 构建工具 | Gradle、`gradle.properties` 变量替换、依赖与工具链 |

## 9. 学习资源

- NeoForge 官方文档：<https://docs.neoforged.net/>（选对你的 Minecraft 版本）
- 模组生成器：<https://neoforged.net/mod-generator/>；版本查询：<https://projects.neoforged.net/neoforged/neoforge>
- NeoForged Discord（官方社区，可提问）
- 阅读开源模组的源码是进步最快的方式：从只有几个类的小模组开始。

> 最后的提醒：**遇到的问题，先读日志。** 模组加载器与 Gradle 的错误信息通常会准确告诉你缺了什么、哪个文件有问题。
