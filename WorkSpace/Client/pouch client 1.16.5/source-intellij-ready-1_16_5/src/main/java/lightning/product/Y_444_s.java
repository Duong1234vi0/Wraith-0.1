/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package lightning.product;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_1630_i;
import lightning.product.A_4115_X;
import lightning.product.D_38_f;
import lightning.product.I_4576_W;
import lightning.product.I_4684_w;
import lightning.product.J_4805_f;
import lightning.product.M_1336_P;
import lightning.product.O_3797_X;
import lightning.product.O_728_b;
import lightning.product.AudioStream;
import lightning.product.ResourceManager;
import lightning.product.V_3137_a;
import lightning.product.V_4423_d;
import lightning.product.SoundEvent;
import lightning.product.SoundInstance;
import lightning.product.Z_4720_K;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.h_3572_K;
import lightning.product.k_4218_M;
import lightning.product.m_4536_S;
import lightning.product.o_3492_Q;
import lightning.product.SoundEngineExecutor;
import lightning.product.TickableSoundInstance;
import lightning.product.WeighedSoundEvents;
import lightning.product.u_530_F;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Y_444_s {
    private static final Marker n_1700_B = MarkerManager.getMarker((String)"SOUNDS");
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Set<g_2336_b> R_4764_Y = Sets.newHashSet();
    private final k_4218_M G_564_y;
    private final V_4423_d P_1922_E;
    private boolean u_1723_Y;
    private final J_4805_f v_4262_N = new J_4805_f();
    private final o_3492_Q w_1484_f = this.v_4262_N.R_4764_Y();
    private final I_4684_w t_148_a;
    private final SoundEngineExecutor s_956_w = new SoundEngineExecutor();
    private final I_4576_W u_2550_I = new I_4576_W(this.v_4262_N, this.s_956_w);
    private int M_588_G;
    private final Map<SoundInstance, I_4576_W.n_1700_B> P_4830_p = Maps.newHashMap();
    private final Multimap<D_38_f, SoundInstance> h_1847_R = HashMultimap.create();
    private final List<TickableSoundInstance> Q_4569_t = Lists.newArrayList();
    private final Map<SoundInstance, Integer> M_182_A = Maps.newHashMap();
    private final Map<SoundInstance, Integer> t_1786_h = Maps.newHashMap();
    private final List<O_3797_X> multiplayerClientSuggestionProvider = Lists.newArrayList();
    private final List<TickableSoundInstance> w_1457_N = Lists.newArrayList();
    private final List<O_728_b> Y_601_j = Lists.newArrayList();

    public Y_444_s(k_4218_M sndHandlerIn, V_4423_d optionsIn, ResourceManager resourceManagerIn) {
        this.G_564_y = sndHandlerIn;
        this.P_1922_E = optionsIn;
        this.t_148_a = new I_4684_w(resourceManagerIn);
    }

    public void n_1700_B() {
        R_4764_Y.clear();
        for (SoundEvent soundevent : V_3137_a.d_2461_k) {
            g_2336_b resourcelocation = soundevent.n_1700_B();
            if (this.G_564_y.n_1700_B(resourcelocation) != null) continue;
            J_1907_R.warn("Missing sound for event: {}", (Object)V_3137_a.d_2461_k.J_1907_R(soundevent));
            R_4764_Y.add(resourcelocation);
        }
        this.J_1907_R();
        this.v_4262_N();
    }

    private synchronized void v_4262_N() {
        if (!this.u_1723_Y) {
            try {
                this.v_4262_N.n_1700_B();
                this.w_1484_f.R_4764_Y();
                this.w_1484_f.n_1700_B(this.P_1922_E.n_1700_B(D_38_f.n_1700_B));
                this.t_148_a.n_1700_B(this.Y_601_j).thenRun(this.Y_601_j::clear);
                this.u_1723_Y = true;
                J_1907_R.info(n_1700_B, "Sound engine started");
            }
            catch (RuntimeException runtimeexception) {
                J_1907_R.error(n_1700_B, "Error starting SoundSystem. Turning off sounds & music", (Throwable)runtimeexception);
            }
        }
    }

    private float n_1700_B(@Nullable D_38_f category) {
        return category != null && category != D_38_f.n_1700_B ? this.P_1922_E.n_1700_B(category) : 1.0f;
    }

    public void n_1700_B(D_38_f category, float volume) {
        if (this.u_1723_Y) {
            if (category == D_38_f.n_1700_B) {
                this.w_1484_f.n_1700_B(volume);
            } else {
                this.P_4830_p.forEach((sound, channelEntry) -> {
                    float f = this.w_1484_f((SoundInstance)sound);
                    channelEntry.n_1700_B((A_1630_i source) -> {
                        if (f <= 0.0f) {
                            source.u_1723_Y();
                        } else {
                            source.J_1907_R(f);
                        }
                    });
                });
            }
        }
    }

    public void J_1907_R() {
        if (this.u_1723_Y) {
            this.R_4764_Y();
            this.t_148_a.n_1700_B();
            this.v_4262_N.J_1907_R();
            this.u_1723_Y = false;
        }
    }

    public void n_1700_B(SoundInstance sound) {
        I_4576_W.n_1700_B channelmanager$entry;
        if (this.u_1723_Y && (channelmanager$entry = this.P_4830_p.get(sound)) != null) {
            channelmanager$entry.n_1700_B(A_1630_i::u_1723_Y);
        }
    }

    public void R_4764_Y() {
        if (this.u_1723_Y) {
            this.s_956_w.J_1907_R();
            this.P_4830_p.values().forEach(channelEntry -> channelEntry.n_1700_B(A_1630_i::u_1723_Y));
            this.P_4830_p.clear();
            this.u_2550_I.J_1907_R();
            this.M_182_A.clear();
            this.Q_4569_t.clear();
            this.h_1847_R.clear();
            this.t_1786_h.clear();
            this.w_1457_N.clear();
        }
    }

    public void n_1700_B(O_3797_X listener) {
        this.multiplayerClientSuggestionProvider.add(listener);
    }

    public void J_1907_R(O_3797_X listener) {
        this.multiplayerClientSuggestionProvider.remove(listener);
    }

    public void n_1700_B(boolean isGamePaused) {
        if (!isGamePaused) {
            this.w_1484_f();
        }
        this.u_2550_I.n_1700_B();
    }

    private void w_1484_f() {
        ++this.M_588_G;
        this.w_1457_N.stream().filter(SoundInstance::P_1922_E).forEach(this::R_4764_Y);
        this.w_1457_N.clear();
        for (TickableSoundInstance itickablesound : this.Q_4569_t) {
            if (!itickablesound.P_1922_E()) {
                this.n_1700_B((SoundInstance)itickablesound);
            }
            itickablesound.R_4764_Y();
            if (itickablesound.multiplayerClientSuggestionProvider()) {
                this.n_1700_B((SoundInstance)itickablesound);
                continue;
            }
            float f = this.w_1484_f(itickablesound);
            float f1 = this.v_4262_N(itickablesound);
            e_2866_D vector3d = new e_2866_D(itickablesound.h_1847_R(), itickablesound.Q_4569_t(), itickablesound.M_182_A());
            I_4576_W.n_1700_B channelmanager$entry = this.P_4830_p.get(itickablesound);
            if (channelmanager$entry == null) continue;
            channelmanager$entry.n_1700_B((A_1630_i source) -> {
                source.J_1907_R(f);
                source.n_1700_B(f1);
                source.n_1700_B(vector3d);
            });
        }
        Iterator<Map.Entry<SoundInstance, I_4576_W.n_1700_B>> iterator = this.P_4830_p.entrySet().iterator();
        while (iterator.hasNext()) {
            int i;
            Map.Entry<SoundInstance, I_4576_W.n_1700_B> entry = iterator.next();
            I_4576_W.n_1700_B channelmanager$entry1 = entry.getValue();
            SoundInstance isound = entry.getKey();
            float f2 = this.P_1922_E.n_1700_B(isound.w_1484_f());
            if (f2 <= 0.0f) {
                channelmanager$entry1.n_1700_B(A_1630_i::u_1723_Y);
                iterator.remove();
                continue;
            }
            if (!channelmanager$entry1.n_1700_B() || (i = this.t_1786_h.get(isound).intValue()) > this.M_588_G) continue;
            if (Y_444_s.P_1922_E(isound)) {
                this.M_182_A.put(isound, this.M_588_G + isound.u_2550_I());
            }
            iterator.remove();
            J_1907_R.debug(n_1700_B, "Removed channel {} because it's not playing anymore", (Object)channelmanager$entry1);
            this.t_1786_h.remove(isound);
            try {
                this.h_1847_R.remove((Object)isound.w_1484_f(), (Object)isound);
            }
            catch (RuntimeException runtimeException) {
                // empty catch block
            }
            if (!(isound instanceof TickableSoundInstance)) continue;
            this.Q_4569_t.remove(isound);
        }
        Iterator<Map.Entry<SoundInstance, Integer>> iterator1 = this.M_182_A.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<SoundInstance, Integer> entry1 = iterator1.next();
            if (this.M_588_G < entry1.getValue()) continue;
            SoundInstance isound1 = entry1.getKey();
            if (isound1 instanceof TickableSoundInstance) {
                ((TickableSoundInstance)isound1).R_4764_Y();
            }
            this.R_4764_Y(isound1);
            iterator1.remove();
        }
    }

    private static boolean G_564_y(SoundInstance sound) {
        return sound.u_2550_I() > 0;
    }

    private static boolean P_1922_E(SoundInstance sound) {
        return sound.t_148_a() && Y_444_s.G_564_y(sound);
    }

    private static boolean u_1723_Y(SoundInstance sound) {
        return sound.t_148_a() && !Y_444_s.G_564_y(sound);
    }

    public boolean J_1907_R(SoundInstance soundIn) {
        if (!this.u_1723_Y) {
            return false;
        }
        return this.t_1786_h.containsKey(soundIn) && this.t_1786_h.get(soundIn) <= this.M_588_G ? true : this.P_4830_p.containsKey(soundIn);
    }

    public void R_4764_Y(SoundInstance p_sound) {
        Z_4720_K eventSound = new Z_4720_K(p_sound, 1.0f);
        A_4115_X.n_1700_B(eventSound);
        SoundInstance sound = eventSound.J_1907_R();
        float factor = eventSound.R_4764_Y();
        if (this.u_1723_Y && !eventSound.n_1700_B() && sound.P_1922_E()) {
            WeighedSoundEvents accessor = sound.n_1700_B(this.G_564_y);
            g_2336_b resloc = sound.u_1723_Y();
            if (accessor == null) {
                if (R_4764_Y.add(resloc)) {
                    J_1907_R.warn(n_1700_B, "Unable to play unknown soundEvent: {}", (Object)resloc);
                }
                return;
            }
            O_728_b snd = sound.v_4262_N();
            if (snd == k_4218_M.n_1700_B) {
                if (R_4764_Y.add(resloc)) {
                    J_1907_R.warn(n_1700_B, "Unable to play empty soundEvent: {}", (Object)resloc);
                }
                return;
            }
            float origVol = sound.M_588_G() * factor;
            float attenuationDist = Math.max(origVol, 1.0f) * (float)snd.u_2550_I();
            D_38_f category = sound.w_1484_f();
            float clampedVol = this.w_1484_f(sound) * factor;
            float clampedPitch = this.v_4262_N(sound);
            SoundInstance.n_1700_B attenType = sound.t_1786_h();
            boolean isGlobal = sound.s_956_w();
            if (clampedVol == 0.0f && !sound.G_564_y()) {
                J_1907_R.debug(n_1700_B, "Skipped playing sound {}, volume was zero.", (Object)sound.u_1723_Y());
                return;
            }
            e_2866_D pos = new e_2866_D(sound.h_1847_R(), sound.Q_4569_t(), sound.M_182_A());
            if (!this.multiplayerClientSuggestionProvider.isEmpty()) {
                boolean notify;
                boolean bl = notify = isGlobal || attenType == SoundInstance.n_1700_B.n_1700_B || this.w_1484_f.n_1700_B().v_4262_N(pos) < (double)(attenuationDist * attenuationDist);
                if (notify) {
                    for (O_3797_X l : this.multiplayerClientSuggestionProvider) {
                        l.n_1700_B(sound, accessor);
                    }
                } else {
                    J_1907_R.debug(n_1700_B, "Did not notify listeners of soundEvent: {}, it is too far away to hear", (Object)resloc);
                }
            }
            if (this.w_1484_f.J_1907_R() <= 0.0f) {
                J_1907_R.debug(n_1700_B, "Skipped playing soundEvent: {}, master volume was zero", (Object)resloc);
                return;
            }
            boolean repeat = sound.t_148_a() && sound.u_2550_I() == 0;
            boolean streaming = snd.t_148_a();
            CompletableFuture<I_4576_W.n_1700_B> future = this.u_2550_I.n_1700_B(streaming ? J_4805_f.R_4764_Y.J_1907_R : J_4805_f.R_4764_Y.n_1700_B);
            I_4576_W.n_1700_B entry = future.join();
            if (entry == null) {
                J_1907_R.warn("Failed to create new sound handle");
                return;
            }
            J_1907_R.debug(n_1700_B, "Playing sound {} for event {}", (Object)snd.R_4764_Y(), (Object)resloc);
            this.t_1786_h.put(sound, this.M_588_G + 20);
            this.P_4830_p.put(sound, entry);
            this.h_1847_R.put((Object)category, (Object)sound);
            entry.n_1700_B((A_1630_i source) -> {
                source.n_1700_B(clampedPitch);
                source.J_1907_R(clampedVol);
                if (attenType == SoundInstance.n_1700_B.J_1907_R) {
                    source.R_4764_Y(attenuationDist);
                } else {
                    source.w_1484_f();
                }
                source.n_1700_B(repeat && !streaming);
                source.n_1700_B(pos);
                source.J_1907_R(isGlobal);
            });
            if (!streaming) {
                this.t_148_a.n_1700_B(snd.G_564_y()).thenAccept(buffer -> entry.n_1700_B((A_1630_i src) -> {
                    src.n_1700_B((m_4536_S)buffer);
                    src.R_4764_Y();
                }));
            } else {
                this.t_148_a.n_1700_B(snd.G_564_y(), repeat).thenAccept(stream -> entry.n_1700_B((A_1630_i src) -> {
                    src.n_1700_B((AudioStream)stream);
                    src.R_4764_Y();
                }));
            }
            if (sound instanceof TickableSoundInstance) {
                this.Q_4569_t.add((TickableSoundInstance)sound);
            }
        }
    }

    public void n_1700_B(TickableSoundInstance tickableSound) {
        this.w_1457_N.add(tickableSound);
    }

    public void n_1700_B(O_728_b soundIn) {
        this.Y_601_j.add(soundIn);
    }

    private float v_4262_N(SoundInstance soundIn) {
        return u_530_F.n_1700_B(soundIn.P_4830_p(), 0.5f, 2.0f);
    }

    private float w_1484_f(SoundInstance soundIn) {
        return u_530_F.n_1700_B(soundIn.M_588_G() * this.n_1700_B(soundIn.w_1484_f()), 0.0f, 1.0f);
    }

    public void G_564_y() {
        if (this.u_1723_Y) {
            this.u_2550_I.n_1700_B((Stream<A_1630_i> sourceStream) -> sourceStream.forEach(A_1630_i::G_564_y));
        }
    }

    public void P_1922_E() {
        if (this.u_1723_Y) {
            this.u_2550_I.n_1700_B((Stream<A_1630_i> sourceStream) -> sourceStream.forEach(A_1630_i::P_1922_E));
        }
    }

    public void n_1700_B(SoundInstance sound, int delay) {
        this.M_182_A.put(sound, this.M_588_G + delay);
    }

    public void n_1700_B(h_3572_K renderInfo) {
        if (this.u_1723_Y && renderInfo.w_1484_f()) {
            e_2866_D vector3d = renderInfo.J_1907_R();
            M_1336_P vector3f = renderInfo.P_4830_p();
            M_1336_P vector3f1 = renderInfo.h_1847_R();
            this.s_956_w.execute(() -> {
                this.w_1484_f.n_1700_B(vector3d);
                this.w_1484_f.n_1700_B(vector3f, vector3f1);
            });
        }
    }

    public void n_1700_B(@Nullable g_2336_b soundName, @Nullable D_38_f category) {
        if (category != null) {
            for (SoundInstance isound : this.h_1847_R.get((Object)category)) {
                if (soundName != null && !isound.u_1723_Y().equals(soundName)) continue;
                this.n_1700_B(isound);
            }
        } else if (soundName == null) {
            this.R_4764_Y();
        } else {
            for (SoundInstance isound1 : this.P_4830_p.keySet()) {
                if (!isound1.u_1723_Y().equals(soundName)) continue;
                this.n_1700_B(isound1);
            }
        }
    }

    public String u_1723_Y() {
        return this.v_4262_N.G_564_y();
    }
}


