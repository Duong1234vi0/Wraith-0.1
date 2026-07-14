/*
 * Decompiled with CFR 0.152.
 */
package net.optifine;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import lightning.product.B_3871_I;
import lightning.product.BlockGetter;
import lightning.product.F_3565_Q;
import lightning.product.K_4074_S;
import lightning.product.L_3848_p;
import lightning.product.MyceliumBlock;
import lightning.product.S_3826_o;
import lightning.product.T_2915_h;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.g_2336_b;
import lightning.product.GrassBlock;
import lightning.product.p_1168_n;
import net.optifine.Config;
import net.optifine.model.BlockModelUtils;
import net.optifine.util.PropertiesOrdered;

public class BetterGrass {
    private static boolean betterGrass = true;
    private static boolean betterGrassPath = true;
    private static boolean betterMycelium = true;
    private static boolean betterPodzol = true;
    private static boolean betterGrassSnow = true;
    private static boolean betterMyceliumSnow = true;
    private static boolean betterPodzolSnow = true;
    private static boolean grassMultilayer = false;
    private static B_3871_I spriteGrass = null;
    private static B_3871_I spriteGrassSide = null;
    private static B_3871_I spriteGrassPath = null;
    private static B_3871_I spriteGrassPathSide = null;
    private static B_3871_I spriteMycelium = null;
    private static B_3871_I spritePodzol = null;
    private static B_3871_I spriteSnow = null;
    private static boolean spritesLoaded = false;
    private static S_3826_o modelCubeGrass = null;
    private static S_3826_o modelGrassPath = null;
    private static S_3826_o modelCubeGrassPath = null;
    private static S_3826_o modelCubeMycelium = null;
    private static S_3826_o modelCubePodzol = null;
    private static S_3826_o modelCubeSnow = null;
    private static boolean modelsLoaded = false;
    private static final String TEXTURE_GRASS_DEFAULT = "block/grass_block_top";
    private static final String TEXTURE_GRASS_SIDE_DEFAULT = "block/grass_block_side";
    private static final String TEXTURE_GRASS_PATH_DEFAULT = "block/grass_path_top";
    private static final String TEXTURE_GRASS_PATH_SIDE_DEFAULT = "block/grass_path_side";
    private static final String TEXTURE_MYCELIUM_DEFAULT = "block/mycelium_top";
    private static final String TEXTURE_PODZOL_DEFAULT = "block/podzol_top";
    private static final String TEXTURE_SNOW_DEFAULT = "block/snow";
    private static final Random RANDOM = new Random(0L);

    public static void updateIcons(L_3848_p textureMap) {
        spritesLoaded = false;
        modelsLoaded = false;
        BetterGrass.loadProperties(textureMap);
    }

    public static void update() {
        if (spritesLoaded) {
            modelCubeGrass = BlockModelUtils.makeModelCube(spriteGrass, 0);
            if (grassMultilayer) {
                S_3826_o ibakedmodel = BlockModelUtils.makeModelCube(spriteGrassSide, -1);
                modelCubeGrass = BlockModelUtils.joinModelsCube(ibakedmodel, modelCubeGrass);
            }
            modelGrassPath = BlockModelUtils.makeModel("grass_path", spriteGrassPathSide, spriteGrassPath);
            modelCubeGrassPath = BlockModelUtils.makeModelCube(spriteGrassPath, -1);
            modelCubeMycelium = BlockModelUtils.makeModelCube(spriteMycelium, -1);
            modelCubePodzol = BlockModelUtils.makeModelCube(spritePodzol, 0);
            modelCubeSnow = BlockModelUtils.makeModelCube(spriteSnow, -1);
            modelsLoaded = true;
        }
    }

    private static void loadProperties(L_3848_p textureMap) {
        betterGrass = true;
        betterGrassPath = true;
        betterMycelium = true;
        betterPodzol = true;
        betterGrassSnow = true;
        betterMyceliumSnow = true;
        betterPodzolSnow = true;
        spriteGrass = textureMap.P_1922_E(new g_2336_b(TEXTURE_GRASS_DEFAULT));
        spriteGrassSide = textureMap.P_1922_E(new g_2336_b(TEXTURE_GRASS_SIDE_DEFAULT));
        spriteGrassPath = textureMap.P_1922_E(new g_2336_b(TEXTURE_GRASS_PATH_DEFAULT));
        spriteGrassPathSide = textureMap.P_1922_E(new g_2336_b(TEXTURE_GRASS_PATH_SIDE_DEFAULT));
        spriteMycelium = textureMap.P_1922_E(new g_2336_b(TEXTURE_MYCELIUM_DEFAULT));
        spritePodzol = textureMap.P_1922_E(new g_2336_b(TEXTURE_PODZOL_DEFAULT));
        spriteSnow = textureMap.P_1922_E(new g_2336_b(TEXTURE_SNOW_DEFAULT));
        spritesLoaded = true;
        String s = "optifine/bettergrass.properties";
        try {
            g_2336_b resourcelocation = new g_2336_b(s);
            if (!Config.hasResource(resourcelocation)) {
                return;
            }
            InputStream inputstream = Config.getResourceStream(resourcelocation);
            if (inputstream == null) {
                return;
            }
            boolean flag = Config.isFromDefaultResourcePack(resourcelocation);
            if (flag) {
                Config.dbg("BetterGrass: Parsing default configuration " + s);
            } else {
                Config.dbg("BetterGrass: Parsing configuration " + s);
            }
            PropertiesOrdered properties = new PropertiesOrdered();
            properties.load(inputstream);
            inputstream.close();
            betterGrass = BetterGrass.getBoolean(properties, "grass", true);
            betterGrassPath = BetterGrass.getBoolean(properties, "grass_path", true);
            betterMycelium = BetterGrass.getBoolean(properties, "mycelium", true);
            betterPodzol = BetterGrass.getBoolean(properties, "podzol", true);
            betterGrassSnow = BetterGrass.getBoolean(properties, "grass.snow", true);
            betterMyceliumSnow = BetterGrass.getBoolean(properties, "mycelium.snow", true);
            betterPodzolSnow = BetterGrass.getBoolean(properties, "podzol.snow", true);
            grassMultilayer = BetterGrass.getBoolean(properties, "grass.multilayer", false);
            spriteGrass = BetterGrass.registerSprite(properties, "texture.grass", TEXTURE_GRASS_DEFAULT, textureMap);
            spriteGrassSide = BetterGrass.registerSprite(properties, "texture.grass_side", TEXTURE_GRASS_SIDE_DEFAULT, textureMap);
            spriteGrassPath = BetterGrass.registerSprite(properties, "texture.grass_path", TEXTURE_GRASS_PATH_DEFAULT, textureMap);
            spriteGrassPathSide = BetterGrass.registerSprite(properties, "texture.grass_path_side", TEXTURE_GRASS_PATH_SIDE_DEFAULT, textureMap);
            spriteMycelium = BetterGrass.registerSprite(properties, "texture.mycelium", TEXTURE_MYCELIUM_DEFAULT, textureMap);
            spritePodzol = BetterGrass.registerSprite(properties, "texture.podzol", TEXTURE_PODZOL_DEFAULT, textureMap);
            spriteSnow = BetterGrass.registerSprite(properties, "texture.snow", TEXTURE_SNOW_DEFAULT, textureMap);
        }
        catch (IOException ioexception) {
            Config.warn("Error reading: " + s + ", " + ioexception.getClass().getName() + ": " + ioexception.getMessage());
        }
    }

    public static void refreshIcons(L_3848_p textureMap) {
        spriteGrass = BetterGrass.getSprite(textureMap, spriteGrass.s_956_w());
        spriteGrassSide = BetterGrass.getSprite(textureMap, spriteGrassSide.s_956_w());
        spriteGrassPath = BetterGrass.getSprite(textureMap, spriteGrassPath.s_956_w());
        spriteGrassPathSide = BetterGrass.getSprite(textureMap, spriteGrassPathSide.s_956_w());
        spriteMycelium = BetterGrass.getSprite(textureMap, spriteMycelium.s_956_w());
        spritePodzol = BetterGrass.getSprite(textureMap, spritePodzol.s_956_w());
        spriteSnow = BetterGrass.getSprite(textureMap, spriteSnow.s_956_w());
    }

    private static B_3871_I getSprite(L_3848_p textureMap, g_2336_b loc) {
        B_3871_I textureatlassprite = textureMap.J_1907_R(loc);
        if (textureatlassprite == null || textureatlassprite instanceof F_3565_Q) {
            Config.warn("Missing BetterGrass sprite: " + String.valueOf(loc));
        }
        return textureatlassprite;
    }

    private static B_3871_I registerSprite(Properties props, String key, String textureDefault, L_3848_p textureMap) {
        g_2336_b resourcelocation;
        String s = props.getProperty(key);
        if (s == null) {
            s = textureDefault;
        }
        if (!Config.hasResource(resourcelocation = new g_2336_b("textures/" + s + ".png"))) {
            Config.warn("BetterGrass texture not found: " + String.valueOf(resourcelocation));
            s = textureDefault;
        }
        g_2336_b resourcelocation1 = new g_2336_b(s);
        return textureMap.P_1922_E(resourcelocation1);
    }

    public static List getFaceQuads(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, b_257_Y facing, List quads) {
        if (facing != b_257_Y.J_1907_R && facing != b_257_Y.n_1700_B) {
            if (!modelsLoaded) {
                return quads;
            }
            T_2915_h block = blockState.J_1907_R();
            if (block instanceof MyceliumBlock) {
                return BetterGrass.getFaceQuadsMycelium(blockAccess, blockState, blockPos, facing, quads);
            }
            if (block instanceof p_1168_n) {
                return BetterGrass.getFaceQuadsGrassPath(blockAccess, blockState, blockPos, facing, quads);
            }
            if (block == a_3742_W.M_588_G) {
                return BetterGrass.getFaceQuadsPodzol(blockAccess, blockState, blockPos, facing, quads);
            }
            if (block == a_3742_W.s_956_w) {
                return BetterGrass.getFaceQuadsDirt(blockAccess, blockState, blockPos, facing, quads);
            }
            return block instanceof GrassBlock ? BetterGrass.getFaceQuadsGrass(blockAccess, blockState, blockPos, facing, quads) : quads;
        }
        return quads;
    }

    private static List getFaceQuadsMycelium(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, b_257_Y facing, List quads) {
        boolean flag;
        T_2915_h block = blockAccess.getBlockState(blockPos.up()).J_1907_R();
        boolean bl = flag = block == a_3742_W.l_697_B || block == a_3742_W.X_290_I;
        if (Config.isBetterGrassFancy()) {
            if (flag) {
                if (betterMyceliumSnow && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == a_3742_W.X_290_I) {
                    return modelCubeSnow.n_1700_B(blockState, facing, RANDOM);
                }
            } else if (betterMycelium && BetterGrass.getBlockAt(blockPos.down(), facing, blockAccess) == a_3742_W.A_2714_y) {
                return modelCubeMycelium.n_1700_B(blockState, facing, RANDOM);
            }
        } else if (flag) {
            if (betterMyceliumSnow) {
                return modelCubeSnow.n_1700_B(blockState, facing, RANDOM);
            }
        } else if (betterMycelium) {
            return modelCubeMycelium.n_1700_B(blockState, facing, RANDOM);
        }
        return quads;
    }

    private static List getFaceQuadsGrassPath(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, b_257_Y facing, List quads) {
        if (!betterGrassPath) {
            return quads;
        }
        if (Config.isBetterGrassFancy()) {
            return BetterGrass.getBlockAt(blockPos.down(), facing, blockAccess) == a_3742_W.InvManager ? modelGrassPath.n_1700_B(blockState, facing, RANDOM) : quads;
        }
        return modelGrassPath.n_1700_B(blockState, facing, RANDOM);
    }

    private static List getFaceQuadsPodzol(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, b_257_Y facing, List quads) {
        boolean flag;
        T_2915_h block = BetterGrass.getBlockAt(blockPos, b_257_Y.J_1907_R, blockAccess);
        boolean bl = flag = block == a_3742_W.l_697_B || block == a_3742_W.X_290_I;
        if (Config.isBetterGrassFancy()) {
            c_1514_x blockpos;
            K_4074_S blockstate;
            if (flag) {
                if (betterPodzolSnow && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == a_3742_W.X_290_I) {
                    return modelCubeSnow.n_1700_B(blockState, facing, RANDOM);
                }
            } else if (betterPodzol && (blockstate = blockAccess.getBlockState(blockpos = blockPos.down().offset(facing))).J_1907_R() == a_3742_W.M_588_G) {
                return modelCubePodzol.n_1700_B(blockState, facing, RANDOM);
            }
        } else if (flag) {
            if (betterPodzolSnow) {
                return modelCubeSnow.n_1700_B(blockState, facing, RANDOM);
            }
        } else if (betterPodzol) {
            return modelCubePodzol.n_1700_B(blockState, facing, RANDOM);
        }
        return quads;
    }

    private static List getFaceQuadsDirt(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, b_257_Y facing, List quads) {
        T_2915_h block = BetterGrass.getBlockAt(blockPos, b_257_Y.J_1907_R, blockAccess);
        return block == a_3742_W.InvManager && betterGrassPath && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == a_3742_W.InvManager ? modelCubeGrassPath.n_1700_B(blockState, facing, RANDOM) : quads;
    }

    private static List getFaceQuadsGrass(BlockGetter blockAccess, K_4074_S blockState, c_1514_x blockPos, b_257_Y facing, List quads) {
        boolean flag;
        T_2915_h block = blockAccess.getBlockState(blockPos.up()).J_1907_R();
        boolean bl = flag = block == a_3742_W.l_697_B || block == a_3742_W.X_290_I;
        if (Config.isBetterGrassFancy()) {
            if (flag) {
                if (betterGrassSnow && BetterGrass.getBlockAt(blockPos, facing, blockAccess) == a_3742_W.X_290_I) {
                    return modelCubeSnow.n_1700_B(blockState, facing, RANDOM);
                }
            } else if (betterGrass && BetterGrass.getBlockAt(blockPos.down(), facing, blockAccess) == a_3742_W.t_148_a) {
                return modelCubeGrass.n_1700_B(blockState, facing, RANDOM);
            }
        } else if (flag) {
            if (betterGrassSnow) {
                return modelCubeSnow.n_1700_B(blockState, facing, RANDOM);
            }
        } else if (betterGrass) {
            return modelCubeGrass.n_1700_B(blockState, facing, RANDOM);
        }
        return quads;
    }

    private static T_2915_h getBlockAt(c_1514_x blockPos, b_257_Y facing, BlockGetter blockAccess) {
        c_1514_x blockpos = blockPos.offset(facing);
        return blockAccess.getBlockState(blockpos).J_1907_R();
    }

    private static boolean getBoolean(Properties props, String key, boolean def) {
        String s = props.getProperty(key);
        return s == null ? def : Boolean.parseBoolean(s);
    }
}



