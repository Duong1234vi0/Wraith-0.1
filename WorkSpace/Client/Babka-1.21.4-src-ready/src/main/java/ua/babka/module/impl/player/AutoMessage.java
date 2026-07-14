package ua.babka.module.impl.player;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.world.World;
import ua.babka.Babka;
import ua.babka.event.EventAttack;
import ua.babka.event.EventPacket;
import ua.babka.event.EventPlayerUpdate;
import ua.babka.event.EventPopTotem;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AttackAura;
import ua.babka.module.setting.BooleanSetting;

@ModuleInformation(moduleName="AutoMessage", moduleDesc="Рассылка сообщений в чат", moduleCategory=ModuleCategory.PLAYER)
public class AutoMessage
extends Module {
    private final BooleanSetting onKill = new BooleanSetting("Убийстве", true);
    private final BooleanSetting onTotem = new BooleanSetting("Тотеме", false);
    private final BooleanSetting showClient = new BooleanSetting("Упоминать чит", true).setVisible(this.onKill::getValue);
    private static final String[] killMessages = new String[]{" ! щас ебу твою телку а ты просто смотришь by Бабка Клиент Free", " ! твоя телка уже со мной)))) by Бабка Клиент Free", " ! дцп ребенок почему на спавне? by Бабка Клиент Free", " ! залетаю в мечеть на намазик by Бабка Клиент Free", " ! она дёрти дёрти дёрти скачай чит Бабка Клиент Free", " ! хуевая попытка, лузер by Бабка Клиент Free", " ! как же много ты лузаешь а знаешь почему потому что ты без Бабка Клиент Free", " ! я скачал лучший фри чит и выебал твою мать  в жопу , by Бабка Клиент Free", " ! практикуйся, даун ебаный by Бабка Клиент Free", " ! до встречи, пидор by Бабка Клиент Free"};
    private static final String[] totemMessages = new String[]{"! {name} тотем не спасет, пидор, в следующий раз выебем by Бабка Клиент Free", "! {name} повезло, но скоро нахуй вынесем by Бабка Клиент Free", "! {name} тотем засчитан, жди ебли by Бабка Клиент Free", "! {name} хорошо держишься, но все равно лох by Бабка Клиент Free", "! {name} следующий тотем последний, сука by Бабка Клиент Free", "! {name} gg, но я тебя все равно выебу by Бабка Клиент Free", "! {name} тотем сработал, а ты все равно нуб by Бабка Клиент Free", "! {name} запасай тотемы, пидор by Бабка Клиент Free", "! {name} удача на твоей стороне, пока я не выебал by Бабка Клиент Free", "! {name} gg за тотем, лузер by Бабка Клиент Free"};
    private final Random random = new Random();
    private LivingEntity lastTarget = null;
    private String pendingMessage = null;
    private long pendingMessageTime = 0L;
    private long lastMessageTime = 0L;
    private int lastKillIndex = -1;
    private int lastTotemIndex = -1;
    private int lastPickedIndex = -1;

    private LivingEntity getAuraTarget() {
        AttackAura aura = Babka.getInstance().getModuleStorage().get(AttackAura.class);
        return aura != null && aura.isEnabled() ? aura.getTarget() : null;
    }

    private void sendOrQueue(String msg, long now) {
        if (now - this.lastMessageTime >= 3000L) {
            this.mc.player.networkHandler.sendChatMessage(msg);
            this.lastMessageTime = now;
            this.pendingMessage = null;
        } else {
            this.pendingMessage = msg;
            this.pendingMessageTime = now;
        }
    }

    private void triggerKill(LivingEntity killed, long now) {
        if (killed == null) {
            return;
        }
        String name = killed.getName().getString();
        Object msg = this.pickMessage(killMessages, this.lastKillIndex);
        this.lastKillIndex = this.lastPickedIndex;
        if (!this.showClient.getValue()) {
            msg = this.stripClient((String)msg);
        }
        msg = "!-" + name + (String)msg;
        this.sendOrQueue((String)msg, now);
        this.lastTarget = null;
    }

    @Subscribe
    private void onAttack(EventAttack e2) {
        if (!this.isEnabled() || !this.onKill.getValue()) {
            return;
        }
        if (!(e2.getEntity() instanceof PlayerEntity) || e2.getEntity() == this.mc.player) {
            return;
        }
        this.lastTarget = (LivingEntity)e2.getEntity();
    }

    @Subscribe
    private void onPacket(EventPacket e2) {
        LivingEntity living;
        Entity entity;
        EntityStatusS2CPacket packet;
        if (!this.isEnabled() || !this.onKill.getValue()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (e2.getType() != EventPacket.Type.RECEIVE) {
            return;
        }
        Packet<?> packet2 = e2.getPacket();
        if (packet2 instanceof EntityStatusS2CPacket && (packet = (EntityStatusS2CPacket)packet2).getStatus() == 3 && (entity = packet.getEntity((World)this.mc.world)) instanceof LivingEntity && (living = (LivingEntity)entity) == this.lastTarget) {
            this.triggerKill(living, System.currentTimeMillis());
        }
    }

    @Subscribe
    private void onTotemEvent(EventPopTotem e2) {
        if (!this.isEnabled() || !this.onTotem.getValue()) {
            return;
        }
        if (this.mc.player == null) {
            return;
        }
        PlayerEntity target = e2.getPlayer();
        if (target == null || target == this.mc.player) {
            return;
        }
        LivingEntity auraTarget = this.getAuraTarget();
        if (auraTarget == null || target != auraTarget) {
            return;
        }
        long now = System.currentTimeMillis();
        String name = target.getName().getString();
        String msg = this.pickMessage(totemMessages, this.lastTotemIndex);
        this.lastTotemIndex = this.lastPickedIndex;
        if (!this.showClient.getValue()) {
            msg = this.stripClient(msg);
        }
        msg = msg.replace("! {name}", name);
        this.sendOrQueue(msg, now);
    }

    @Subscribe
    private void onUpdate(EventPlayerUpdate e2) {
        if (!this.isEnabled()) {
            return;
        }
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.pendingMessage != null && now - this.pendingMessageTime >= 3000L) {
            this.mc.player.networkHandler.sendChatMessage(this.pendingMessage);
            this.lastMessageTime = now;
            this.pendingMessage = null;
        }
        if (!this.onKill.getValue() || this.lastTarget == null) {
            return;
        }
        if (this.lastTarget.getHealth() <= 0.0f || !this.lastTarget.isAlive()) {
            this.triggerKill(this.lastTarget, now);
        }
    }

    private String pickMessage(String[] pool, int exclude) {
        int idx;
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i2 = 0; i2 < pool.length; ++i2) {
            indices.add(i2);
        }
        while ((idx = ((Integer)indices.get(this.random.nextInt(indices.size()))).intValue()) == exclude && indices.size() > 1) {
        }
        this.lastPickedIndex = idx;
        return pool[idx];
    }

    private String stripClient(String msg) {
        return msg.replace(" by Бабка Клиент Free", "");
    }
}

