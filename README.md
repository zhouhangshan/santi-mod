# 三体坐标广播器（Santi Coordinate Broadcaster）

一个面向 **Minecraft 1.21.1 + NeoForge 21.1.252** 的模组，新增物品「三体坐标广播器」。

## 功能
- 新物品：**三体坐标广播器**，外观为水滴坐标图案。
- **合成**：1 个泥土 → 1 个三体坐标广播器（无序合成）。
- **用法**：手持右键，立即把当前坐标（X / Y / Z 与所在维度）广播到服务器聊天框，所有玩家可见。物品不消耗，可反复使用。
- 物品位于自定义创造标签页「三体」。

## 项目结构
```
santi-coord-broadcaster/
├─ build.gradle / settings.gradle / gradle.properties   构建脚本（NeoForge 21.1.252）
└─ src/main/
   ├─ java/com/santi/         模组代码（物品注册、右键广播、创造标签页）
   └─ resources/
      ├─ META-INF/neoforge.mods.toml   模组元数据
      ├─ assets/santi/                 语言文件、物品模型、32×32 水滴坐标材质
      └─ data/santi/recipe/            泥土合成配方
```

## 自行编译（推荐，2 步）
需要 **JDK 21** 与 **Gradle 8.10+**（或使用项目内 Gradle Wrapper）。

```bash
cd santi-coord-broadcaster
./gradlew build        # 产物在 build/libs/santi-1.0.0.jar
```

> 说明：本项目仓库已指向官方可用的镜像 `https://neoforged.forgecdn.net/releases/`（原 `maven.neoforged.net` 在国内/部分网络不可达），首次构建会自动下载 NeoForge 与 Minecraft 依赖，耗时较长属正常。

## 安装
1. 安装 NeoForge 21.1.252 并启动一次游戏（生成 `mods` 文件夹）。
2. 将 `santi-1.0.0.jar` 放入 `.minecraft/mods`。
3. 启动游戏 → 创造模式搜索「三体坐标广播器」或进入「三体」标签页。
4. 用 1 个泥土在工作台合成，手持右键即可广播坐标。

## 常见问题
- 版本必须为 **1.21.1**，模组加载器必须是 **NeoForge 21.1.x**。
- 若聊天不显示中文，请确认游戏语言为简体中文（已内置 `zh_cn` 语言文件）。

## 更新记录
### 1.0.0（2026-10-24 修复版 v2）
- **修复启动崩溃**：`InvalidModFileException: Missing license (santi-1.0.0.jar)`。
  NeoForge 4.0.44 的 `ModFileInfo` 构造函数读取的是 **TOML 顶层** 的 `license` 键，
  `[[mods]]` 段内的 `license` 不参与该检查。现已在 `modLoader` / `loaderVersion` 之后的顶层
  加入 `license="${mod_license}"`，编译后 jar 内为 `license="MIT"`。
  （v1 修复版误把 license 写进了 `[[mods]]`，因此游戏里仍然报同样的错。）
- 源码与已编译的 `santi-1.0.0.jar` 保持一致：`CoordBroadcasterItem.use()` 使用
  1.21.1 的 `InteractionResultHolder<ItemStack>` 签名（初版 zip 中为 `InteractionResult`，无法编译）。
- 模组功能、注册 ID（`santi`）、物品 ID（`santi:coord_broadcaster`）、材质与配方均未改变，
  对已有世界兼容。

### 验证方式
用 NeoForge 自带的 `fancymodloader` 类（`ModFileParser.copyConfig` + `ModFileInfo`）
按游戏的真实流程解析本 jar：
- 旧 jar：顶层键 `[mods, modLoader, loaderVersion, dependencies]`，`license` 为空 → 抛 `Missing license`；
- 本 jar：顶层键 `[license, mods, modLoader, loaderVersion, dependencies]`，`license = MIT` → 通过。

### 对应 jar 校验
```
文件：santi-1.0.0.jar
大小：10249 字节
SHA-256：46f33e1c050d45dc1807b22ea6ed81654a0223fedfc67d8338cace44e4d167e8
```
