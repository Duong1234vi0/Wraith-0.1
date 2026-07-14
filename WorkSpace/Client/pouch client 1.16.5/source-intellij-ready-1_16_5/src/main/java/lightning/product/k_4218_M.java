/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.reflect.TypeToken
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.D_38_f;
import lightning.product.F_2904_S;
import lightning.product.K_1289_S;
import lightning.product.Resource;
import lightning.product.O_3797_X;
import lightning.product.O_728_b;
import lightning.product.ResourceManager;
import lightning.product.V_3137_a;
import lightning.product.V_4423_d;
import lightning.product.ProfilerFiller;
import lightning.product.SoundInstance;
import lightning.product.Y_444_s;
import lightning.product.soundsSoundEventRegistration;
import lightning.product.g_2336_b;
import lightning.product.h_3572_K;
import lightning.product.i_4431_W;
import lightning.product.SimplePreparableReloadListener;
import lightning.product.SoundEventRegistrationSerializer;
import lightning.product.Weighted;
import lightning.product.TickableSoundInstance;
import lightning.product.WeighedSoundEvents;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class k_4218_M
extends SimplePreparableReloadListener<n_1700_B> {
    public static final O_728_b n_1700_B = new O_728_b("meta:missing_sound", 1.0f, 1.0f, 1, O_728_b.n_1700_B.n_1700_B, false, false, 16);
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Gson R_4764_Y = new GsonBuilder().registerTypeHierarchyAdapter(x_282_a.class, (Object)new x_282_a.n_1700_B()).registerTypeAdapter(soundsSoundEventRegistration.class, (Object)new SoundEventRegistrationSerializer()).create();
    private static final TypeToken<Map<String, soundsSoundEventRegistration>> G_564_y = new TypeToken<Map<String, soundsSoundEventRegistration>>(){};
    private final Map<g_2336_b, WeighedSoundEvents> P_1922_E = Maps.newHashMap();
    private final Y_444_s u_1723_Y;

    public k_4218_M(ResourceManager manager, V_4423_d gameSettingsIn) {
        this.u_1723_Y = new Y_444_s(this, gameSettingsIn, manager);
    }

    protected n_1700_B n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        n_1700_B soundhandler$loader = new n_1700_B();
        profilerIn.n_1700_B();
        for (String s : resourceManagerIn.n_1700_B()) {
            profilerIn.n_1700_B(s);
            try {
                for (Resource iresource : resourceManagerIn.R_4764_Y(new g_2336_b(s, "sounds.json"))) {
                    profilerIn.n_1700_B(iresource.R_4764_Y());
                    try (InputStream inputstream = iresource.J_1907_R();
                         InputStreamReader reader = new InputStreamReader(inputstream, StandardCharsets.UTF_8);){
                        profilerIn.n_1700_B("parse");
                        Map<String, soundsSoundEventRegistration> map = i_4431_W.n_1700_B(R_4764_Y, (Reader)reader, G_564_y);
                        profilerIn.J_1907_R("register");
                        for (Map.Entry<String, soundsSoundEventRegistration> entry : map.entrySet()) {
                            soundhandler$loader.n_1700_B(new g_2336_b(s, entry.getKey()), entry.getValue(), resourceManagerIn);
                        }
                        profilerIn.R_4764_Y();
                    }
                    catch (RuntimeException runtimeexception) {
                        J_1907_R.warn("Invalid sounds.json in resourcepack: '{}'", (Object)iresource.R_4764_Y(), (Object)runtimeexception);
                    }
                    profilerIn.R_4764_Y();
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
            profilerIn.R_4764_Y();
        }
        profilerIn.J_1907_R();
        return soundhandler$loader;
    }

    protected void n_1700_B(n_1700_B objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        objectIn.n_1700_B(this.P_1922_E, this.u_1723_Y);
        for (g_2336_b resourcelocation : this.P_1922_E.keySet()) {
            String s;
            WeighedSoundEvents soundeventaccessor = this.P_1922_E.get(resourcelocation);
            if (!(soundeventaccessor.G_564_y() instanceof F_2904_S) || K_1289_S.n_1700_B(s = ((F_2904_S)soundeventaccessor.G_564_y()).w_1484_f())) continue;
            J_1907_R.debug("Missing subtitle {} for event: {}", (Object)s, (Object)resourcelocation);
        }
        if (J_1907_R.isDebugEnabled()) {
            for (g_2336_b resourcelocation1 : this.P_1922_E.keySet()) {
                if (V_3137_a.d_2461_k.R_4764_Y(resourcelocation1)) continue;
                J_1907_R.debug("Not having sound event for: {}", (Object)resourcelocation1);
            }
        }
        this.u_1723_Y.n_1700_B();
    }

    private static boolean n_1700_B(O_728_b sound, g_2336_b soundLocation, ResourceManager resourceManager) {
        g_2336_b resourcelocation = sound.G_564_y();
        if (!resourceManager.J_1907_R(resourcelocation)) {
            J_1907_R.warn("File {} does not exist, cannot add it to event {}", (Object)resourcelocation, (Object)soundLocation);
            return false;
        }
        return true;
    }

    @Nullable
    public WeighedSoundEvents n_1700_B(g_2336_b location) {
        return this.P_1922_E.get(location);
    }

    public Collection<g_2336_b> n_1700_B() {
        return this.P_1922_E.keySet();
    }

    public void n_1700_B(TickableSoundInstance tickableSound) {
        this.u_1723_Y.n_1700_B(tickableSound);
    }

    public void n_1700_B(SoundInstance sound) {
        this.u_1723_Y.R_4764_Y(sound);
    }

    public void n_1700_B(SoundInstance sound, int delay) {
        this.u_1723_Y.n_1700_B(sound, delay);
    }

    public void n_1700_B(h_3572_K activeRenderInfo) {
        this.u_1723_Y.n_1700_B(activeRenderInfo);
    }

    public void J_1907_R() {
        this.u_1723_Y.G_564_y();
    }

    public void R_4764_Y() {
        this.u_1723_Y.R_4764_Y();
    }

    public void G_564_y() {
        this.u_1723_Y.J_1907_R();
    }

    public void n_1700_B(boolean isGamePaused) {
        this.u_1723_Y.n_1700_B(isGamePaused);
    }

    public void P_1922_E() {
        this.u_1723_Y.P_1922_E();
    }

    public void n_1700_B(D_38_f category, float volume) {
        if (category == D_38_f.n_1700_B && volume <= 0.0f) {
            this.R_4764_Y();
        }
        this.u_1723_Y.n_1700_B(category, volume);
    }

    public void J_1907_R(SoundInstance soundIn) {
        this.u_1723_Y.n_1700_B(soundIn);
    }

    public boolean R_4764_Y(SoundInstance sound) {
        return this.u_1723_Y.J_1907_R(sound);
    }

    public void n_1700_B(O_3797_X listener) {
        this.u_1723_Y.n_1700_B(listener);
    }

    public void J_1907_R(O_3797_X listener) {
        this.u_1723_Y.J_1907_R(listener);
    }

    public void n_1700_B(@Nullable g_2336_b id, @Nullable D_38_f category) {
        this.u_1723_Y.n_1700_B(id, category);
    }

    public String u_1723_Y() {
        return this.u_1723_Y.u_1723_Y();
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((n_1700_B)object, s_2107_a, x_2951_U);
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }

    public static class n_1700_B {
        private final Map<g_2336_b, WeighedSoundEvents> n_1700_B = Maps.newHashMap();

        protected n_1700_B() {
        }

        private void n_1700_B(g_2336_b soundLocation, soundsSoundEventRegistration soundList, ResourceManager resourceManager) {
            boolean flag;
            WeighedSoundEvents soundeventaccessor = this.n_1700_B.get(soundLocation);
            boolean bl = flag = soundeventaccessor == null;
            if (flag || soundList.J_1907_R()) {
                if (!flag) {
                    J_1907_R.debug("Replaced sound event location {}", (Object)soundLocation);
                }
                soundeventaccessor = new WeighedSoundEvents(soundLocation, soundList.R_4764_Y());
                this.n_1700_B.put(soundLocation, soundeventaccessor);
            }
            block4: for (final O_728_b sound : soundList.n_1700_B()) {
                final g_2336_b resourcelocation = sound.R_4764_Y();
                soundeventaccessor.n_1700_B(switch (sound.w_1484_f()) {
                    case O_728_b.n_1700_B.n_1700_B -> {
                        if (!k_4218_M.n_1700_B(sound, soundLocation, resourceManager)) continue block4;
                        yield sound;
                    }
                    case O_728_b.n_1700_B.J_1907_R -> new Weighted<O_728_b>(){

                        @Override
                        public int n_1700_B() {
                            WeighedSoundEvents soundeventaccessor1 = n_1700_B.get(resourcelocation);
                            return soundeventaccessor1 == null ? 0 : soundeventaccessor1.n_1700_B();
                        }

                        public O_728_b R_4764_Y() {
                            WeighedSoundEvents soundeventaccessor1 = n_1700_B.get(resourcelocation);
                            if (soundeventaccessor1 == null) {
                                return k_4218_M.n_1700_B;
                            }
                            O_728_b sound1 = soundeventaccessor1.R_4764_Y();
                            return new O_728_b(sound1.R_4764_Y().toString(), sound1.P_1922_E() * sound.P_1922_E(), sound1.u_1723_Y() * sound.u_1723_Y(), sound.n_1700_B(), O_728_b.n_1700_B.n_1700_B, sound1.t_148_a() || sound.t_148_a(), sound1.s_956_w(), sound1.u_2550_I());
                        }

                        @Override
                        public void n_1700_B(Y_444_s engine) {
                            WeighedSoundEvents soundeventaccessor1 = n_1700_B.get(resourcelocation);
                            if (soundeventaccessor1 != null) {
                                soundeventaccessor1.n_1700_B(engine);
                            }
                        }

                        @Override
                        public /* synthetic */ Object J_1907_R() {
                            return this.R_4764_Y();
                        }
                    };
                    default -> throw new IllegalStateException("Unknown SoundEventRegistration type: " + String.valueOf((Object)sound.w_1484_f()));
                });
            }
        }

        public void n_1700_B(Map<g_2336_b, WeighedSoundEvents> soundRegistry, Y_444_s soundManager) {
            soundRegistry.clear();
            for (Map.Entry<g_2336_b, WeighedSoundEvents> entry : this.n_1700_B.entrySet()) {
                soundRegistry.put(entry.getKey(), entry.getValue());
                entry.getValue().n_1700_B(soundManager);
            }
        }
    }
}


