package ua.babka.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AmbientEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ua.babka.Babka;
import ua.babka.combat.BestPoint;
import ua.babka.combat.GCDFixer;
import ua.babka.combat.NeuroAuraSystem;
import ua.babka.combat.PredictUtils;
import ua.babka.combat.RaytraceUtil;
import ua.babka.combat.RotationUtil;
import ua.babka.core.Rotation;
import ua.babka.event.EventChangeSprint;
import ua.babka.event.EventGameUpdate;
import ua.babka.event.EventTick;
import ua.babka.event.EventTickEnd;
import ua.babka.friend.FriendRepository;
import ua.babka.module.Module;
import ua.babka.module.ModuleCategory;
import ua.babka.module.ModuleInformation;
import ua.babka.module.impl.combat.AntiBot;
import ua.babka.module.impl.combat.PacketCriticals;
import ua.babka.module.impl.movement.AirStuck;
import ua.babka.module.impl.movement.ElytraTarget;
import ua.babka.module.impl.player.FreeCamera;
import ua.babka.module.setting.BooleanSetting;
import ua.babka.module.setting.ModeListSetting;
import ua.babka.module.setting.ModeSetting;
import ua.babka.module.setting.SliderSetting;
import ua.babka.module.setting.ValueUnit;
import ua.babka.ui.component.RotationComponent;
import ua.babka.util.ChatUtil;
import ua.babka.util.Instance;
import ua.babka.util.math.StopWatch;
import ua.babka.util.player.SimulatedPlayer;

@ModuleInformation(moduleName="AttackAura", moduleDesc="бьет женщин и детей", moduleCategory=ModuleCategory.COMBAT)
public class AttackAura
extends Module {
    public final ModeSetting rotation = new ModeSetting("Ротация", "RW", "RW", "ReallyWorld", "HW", "Neuro");
    public final ModeSetting rotationBehavior = new ModeSetting("Поведение ротации", "Плавная", "Плавная", "Снапы").setVisible(() -> !this.rotation.is("ReallyWorld") && !this.rotation.is("Neuro"));
    public final SliderSetting HWAcceleration = new SliderSetting("HW: Ускорение", ValueUnit.abbreviation(""), 0.004, 0.001, 0.05, 0.001).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWMaxAccelerationElytra = new SliderSetting("HW: Макс. элитра", ValueUnit.abbreviation(""), 0.17, 0.01, 1.0, 0.01).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWMinAccelerationElytra = new SliderSetting("HW: Мин. элитра", ValueUnit.abbreviation(""), -0.02, -1.0, 0.0, 0.01).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWMaxAccelerationGround = new SliderSetting("HW: Макс. земля", ValueUnit.abbreviation(""), 0.18, 0.01, 1.0, 0.01).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWMinAccelerationGround = new SliderSetting("HW: Мин. земля", ValueUnit.abbreviation(""), -0.01, -1.0, 0.0, 0.01).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWDecelerationFastYaw = new SliderSetting("HW: Торможение (резкий)", ValueUnit.abbreviation(""), 0.1, 0.01, 1.0, 0.01).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWDecelerationSlow = new SliderSetting("HW: Торможение (плавный)", ValueUnit.abbreviation(""), 0.01, 0.001, 0.5, 0.001).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWYawThreshold = new SliderSetting("HW: Порог рыскания", ValueUnit.abbreviation(""), 80.0, 0.0, 180.0, 1.0).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWRaytraceDistElytra = new SliderSetting("HW: Дист. трейс элитра", ValueUnit.abbreviation(""), 1488.0, 0.0, 3000.0, 1.0).setVisible(() -> this.rotation.is("HW"));
    public final SliderSetting HWRaytraceDistGround = new SliderSetting("HW: Дист. трейс земля", ValueUnit.abbreviation(""), 999.0, 0.0, 3000.0, 1.0).setVisible(() -> this.rotation.is("HW"));
    private final ModeListSetting targets = new ModeListSetting("Таргеты", new BooleanSetting("Игроки", true), new BooleanSetting("Голые", true), new BooleanSetting("Монстры", false), new BooleanSetting("Животные", false));
    private final ModeListSetting dontAttackIf = new ModeListSetting("Не бить если", new BooleanSetting("Кушаешь", false), new BooleanSetting("Через стены", false), new BooleanSetting("В контейнере", false));
    private final ModeSetting targetPriority = new ModeSetting("Приоритет", "FOV", "FOV", "Мало здоровья", "По дистанции");
    public final List<String> priorityTargets = new ArrayList<String>();
    public final SliderSetting distance = new SliderSetting("Дистанция", ValueUnit.countable("блок", "блока", "блоков"), 3.0, 2.0, 6.0, 0.1f);
    private final SliderSetting preRotation = new SliderSetting("Пре дистанция", ValueUnit.countable("блок", "блока", "блоков"), 1.5, 0.0, 3.0, 0.1f);
    public final BooleanSetting raycastCheck = new BooleanSetting("Проверка на наведение", false);
    private final BooleanSetting wallBypass = new BooleanSetting("Обход стен RW", false);
    private ElytraTarget elytraTarget;
    private final BooleanSetting breakShield = new BooleanSetting("Ломать щит (только с хотбара)", true);
    public final ModeSetting moveFix = new ModeSetting("Коррекция движения", "Сфокусированная", "Нет", "Сфокусированная");
    public final BooleanSetting onlyCriticals = new BooleanSetting("Только криты", true);
    public final BooleanSetting smartCrits = new BooleanSetting("Умные криты", true);
    public final BooleanSetting logYawPitch = new BooleanSetting("Логировать Yaw/Pitch", false);
    private LivingEntity target;
    public static LivingEntity lastTarget;
    public int ticksToAttack;
    private int razvorotikTicks;
    private boolean back;
    public float speedAcceleration;
    public float obhod;
    public static long lastPhysicalMoveTime;
    private final StopWatch turnaroundTimer = new StopWatch();
    public float preddict;
    public float lastYaw;
    public float lastPitch;
    private int targetLossTimer;
    private final NeuroAuraSystem neuroSystem = new NeuroAuraSystem();
    private final StopWatch attackTimer = new StopWatch();

    public ElytraTarget getElytraTarget() {
        if (this.elytraTarget == null) {
            this.elytraTarget = Instance.get(ElytraTarget.class);
        }
        return this.elytraTarget;
    }

    public NeuroAuraSystem getNeuroSystem() {
        return this.neuroSystem;
    }

    @Subscribe
    private void onGameUpdate(EventGameUpdate e2) {
        boolean playerOnElytra;
        if (this.mc.player == null || this.target == null) {
            return;
        }
        if (this.dontAttackIf.isEnabled("Кушаешь") && this.mc.player.isUsingItem()) {
            return;
        }
        Babka.getInstance().getModuleStorage().setRandomness(1.0f);
        if (this.rotationBehavior.is("Снапы") && !this.rotation.is("ReallyWorld")) {
            boolean isReadyToAttack;
            boolean bl = isReadyToAttack = this.mc.player.getAttackCooldownProgress(1.0f) >= 0.95f && this.ticksToAttack <= 1;
            if (!isReadyToAttack) {
                return;
            }
        }
        if ((playerOnElytra = this.mc.player.isGliding()) && (this.elytraTarget == null || !this.elytraTarget.isEnabled())) {
            return;
        }
        boolean isNeuro = this.rotation.is("Neuro");
        switch (this.rotation.getValue()) {
            case "RW": {
                this.updateRWRotation(this.target);
                break;
            }
            case "ReallyWorld": {
                this.updateReallyWorldRotation(this.target);
                break;
            }
            case "HW": {
                this.updateHWRotation(this.target);
                break;
            }
            case "Neuro": {
                this.updateNeuroRotation(this.target);
                if (this.neuroSystem.isRecording()) {
                    this.neuroSystem.recordTick(this.target, this.mc.player.getYaw(), this.mc.player.getPitch());
                }
                if (!this.neuroSystem.isShowStats() || this.mc.player.age % 20 != 0) break;
                this.mc.player.sendMessage((Text)Text.literal((String)this.neuroSystem.getStatusString()), true);
            }
        }
    }

    @Subscribe
    private void onChangeSprint(EventChangeSprint e2) {
        if (this.canStopSprinting()) {
            e2.setSprinting(false);
        }
    }

    @Subscribe
    private void onUpdate(EventTick ignored) {
        if (this.mc.player == null || this.mc.world == null) {
            return;
        }
        if (this.ticksToAttack > 0) {
            --this.ticksToAttack;
        }
        if (this.razvorotikTicks > 0) {
            --this.razvorotikTicks;
        }
        this.updateTarget();
        if (this.target != null) {
            PacketCriticals packetCriticals;
            lastTarget = this.target;
            if (this.canStopSprinting()) {
                this.mc.player.setSprinting(false);
            }
            boolean packetCritsActive = (packetCriticals = Babka.getInstance().getModuleStorage().get(PacketCriticals.class)) != null && packetCriticals.isEnabled() && this.mc.player.hasStatusEffect(StatusEffects.SLOW_FALLING);
            AirStuck airStuck = Babka.getInstance().getModuleStorage().get(AirStuck.class);
            if ((!packetCritsActive || airStuck != null && airStuck.isEnabled()) && this.canAttack()) {
                this.performAttack();
            }
        } else {
            this.speedAcceleration = 0.0f;
            this.razvorotikTicks = 0;
            this.targetLossTimer = 0;
        }
    }

    @Subscribe
    private void onTickEnd(EventTickEnd ignored) {
        boolean packetCritsActive;
        if (this.mc.player == null || this.mc.world == null || this.target == null) {
            return;
        }
        PacketCriticals packetCriticals = Babka.getInstance().getModuleStorage().get(PacketCriticals.class);
        boolean bl = packetCritsActive = packetCriticals != null && packetCriticals.isEnabled() && this.mc.player.hasStatusEffect(StatusEffects.SLOW_FALLING);
        if (packetCritsActive && this.canAttack()) {
            boolean onAirStuck;
            AirStuck airStuck = Babka.getInstance().getModuleStorage().get(AirStuck.class);
            boolean bl2 = onAirStuck = airStuck != null && airStuck.isEnabled();
            if (onAirStuck || this.mc.player.fallDistance > 0.0f && this.mc.player.fallDistance < 1.0f) {
                this.performAttack();
            }
        }
    }

    private void performAttack() {
        boolean onAirStuck;
        if (this.target == null) {
            return;
        }
        AirStuck airStuck = Babka.getInstance().getModuleStorage().get(AirStuck.class);
        boolean bl = onAirStuck = airStuck != null && airStuck.isEnabled();
        if (onAirStuck) {
            airStuck.pauseForAttack();
            double x2 = this.mc.player.getX();
            double y2 = this.mc.player.getY();
            double z2 = this.mc.player.getZ();
            this.mc.getNetworkHandler().sendPacket((Packet)new PlayerMoveC2SPacket.PositionAndOnGround(x2, y2 + 0.0625, z2, false, false));
            this.mc.getNetworkHandler().sendPacket((Packet)new PlayerMoveC2SPacket.PositionAndOnGround(x2, y2, z2, false, false));
        }
        if (this.breakShield.getValue() && this.target instanceof PlayerEntity) {
            this.breakShield(this.target);
        }
        this.mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(new PlayerInput(false, false, false, false, false, false, false)));
        if (this.wallBypass.getValue()) {
            this.spoofWallBypass(this.target);
        }
        this.mc.interactionManager.attackEntity((PlayerEntity)this.mc.player, (Entity)this.target);
        this.mc.player.swingHand(Hand.MAIN_HAND);
        this.mc.getNetworkHandler().sendPacket((Packet)new PlayerInputC2SPacket(this.mc.player.input.playerInput));
        this.attackTimer.reset();
        if (this.logYawPitch.getValue()) {
            long yawBits = (long)Float.floatToRawIntBits(this.target.getYaw()) & 0xFFFFFFFFL;
            long pitchBits = (long)Float.floatToRawIntBits(this.target.getPitch()) & 0xFFFFFFFFL;
            ChatUtil.send((Object)(yawBits + "." + pitchBits));
        }
        if (onAirStuck || this.mc.player.isGliding()) {
            this.ticksToAttack = 10;
        } else {
            boolean isWeapon;
            ItemStack held = this.mc.player.getMainHandStack();
            boolean bl2 = isWeapon = !held.isEmpty() && (held.getItem() instanceof SwordItem || held.getItem() instanceof AxeItem || held.getItem() instanceof ShovelItem);
            this.ticksToAttack = !isWeapon ? (this.mc.player.isOnGround() ? 10 : 6) : 2;
        }
    }

    private boolean isValidEntity(Entity entity) {
        PlayerEntity p2;
        LivingEntity living;
        ClientPlayerEntity player;
        LivingEntity le;
        if (!entity.isAlive()) {
            return false;
        }
        if (entity instanceof LivingEntity && (le = (LivingEntity)entity).getHealth() <= 0.0f) {
            return false;
        }
        Object object = player = Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer != null ? Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer : this.mc.player;
        if (entity == Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer) {
            return false;
        }
        if (entity instanceof ClientPlayerEntity) {
            return false;
        }
        if (entity instanceof ArmorStandEntity) {
            return false;
        }
        if (entity instanceof LivingEntity && AntiBot.checkBot(living = (LivingEntity)entity)) {
            return false;
        }
        if (entity instanceof PlayerEntity && (p2 = (PlayerEntity)entity).getArmor() != 0 && !this.targets.isEnabled("Игроки")) {
            return false;
        }
        if (entity instanceof PlayerEntity && (p2 = (PlayerEntity)entity).getArmor() == 0 && !this.targets.isEnabled("Голые")) {
            return false;
        }
        if ((entity instanceof HostileEntity || entity instanceof Monster || entity instanceof AmbientEntity) && !this.targets.isEnabled("Монстры")) {
            return false;
        }
        if ((entity instanceof PassiveEntity || entity instanceof FishEntity) && !this.targets.isEnabled("Животные")) {
            return false;
        }
        if (entity instanceof PlayerEntity && !FriendRepository.shouldAttack(p2 = (PlayerEntity)entity)) {
            return false;
        }
        return player.getEyePos().distanceTo(BestPoint.getNearestPoint(entity)) <= (player.isGliding() ? 50.0 : this.distance.getValue() + this.preRotation.getValue());
    }

    public boolean canAttack() {
        boolean anyElytra;
        Vec3d eyes;
        BlockHitResult hit;
        if (this.target == null) {
            return false;
        }
        if (this.dontAttackIf.isEnabled("Кушаешь") && this.mc.player.isUsingItem()) {
            return false;
        }
        if (this.dontAttackIf.isEnabled("Через стены") && (hit = this.mc.world.raycast(new RaycastContext(eyes = this.mc.player.getEyePos(), this.target.getBoundingBox().getCenter(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)this.mc.player))).getType() == HitResult.Type.BLOCK) {
            return false;
        }
        if (this.dontAttackIf.isEnabled("В контейнере") && this.mc.currentScreen instanceof HandledScreen) {
            return false;
        }
        ClientPlayerEntity player = Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer != null ? Babka.getInstance().getModuleStorage().get(FreeCamera.class).fakePlayer : this.mc.player;
        boolean playerElytra = this.mc.player.isGliding();
        boolean targetElytra = this.target.isGliding();
        boolean bl = anyElytra = playerElytra || targetElytra;
        if (!Babka.getInstance().getIdealHitUtils().cooldownIsReached(false)) {
            return false;
        }
        if (this.ticksToAttack > 0) {
            return false;
        }
        if (anyElytra) {
            if (this.elytraTarget == null || !this.elytraTarget.isEnabled()) {
                return false;
            }
            return this.elytraTarget.canAttack(this.target);
        }
        AirStuck airStuck = Babka.getInstance().getModuleStorage().get(AirStuck.class);
        float attackDist = (float)this.distance.getValue();
        if (airStuck != null && airStuck.isEnabled() && airStuck.useKillAuraDistance.getValue()) {
            attackDist = airStuck.killAuraDistance.getFloatValue();
        }
        if (!RaytraceUtil.rayTrace(player.getRotationVector(), attackDist, this.target.getBoundingBox()) && this.raycastCheck.getValue()) {
            return false;
        }
        if (player.getEyePos().distanceTo(BestPoint.getNearestPoint((Entity)this.target)) > (double)(attackDist - 0.2f)) {
            return false;
        }
        if (airStuck != null && airStuck.isEnabled()) {
            return true;
        }
        return Babka.getInstance().getIdealHitUtils().canCritical();
    }

    public boolean canStopSprinting() {
        if (this.target == null) {
            return false;
        }
        if (!Babka.getInstance().getIdealHitUtils().cooldownIsReached(true)) {
            return false;
        }
        if (this.ticksToAttack > 0) {
            return false;
        }
        return SimulatedPlayer.simulateLocalPlayer((int)1).fallDistance != 0.0f;
    }

    private void spoofWallBypass(LivingEntity tgt) {
        if (tgt == null || this.mc.world == null) {
            return;
        }
        try {
            BlockHitResult result;
            Vec3d eye = this.mc.player.getEyePos();
            Vec3d aimPoint = tgt.getPos().add(0.0, (double)(tgt.getHeight() / 1.2f) + (tgt.getY() - tgt.prevY), 0.0);
            Vec3d dir = aimPoint.subtract(eye).normalize();
            Vec3d start = eye;
            for (int i2 = 0; i2 < 2 && (result = this.mc.world.raycast(new RaycastContext(start, aimPoint, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, (Entity)this.mc.player))).getType() == HitResult.Type.BLOCK; ++i2) {
                BlockHitResult blockHit = result;
                BlockPos pos = blockHit.getBlockPos();
                Direction face = blockHit.getSide();
                this.mc.getNetworkHandler().sendPacket((Packet)new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.START_DESTROY_BLOCK, pos, face, 0));
                this.mc.getNetworkHandler().sendPacket((Packet)new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, pos, face, 0));
                Vec3d hit = blockHit.getPos();
                start = new Vec3d(hit.x + dir.x * 0.05, hit.y + dir.y * 0.05, hit.z + dir.z * 0.05);
                if (!(start.squaredDistanceTo(aimPoint) < 0.0025)) {
                    continue;
                }
                break;
            }
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    private void breakShield(LivingEntity targetEntity) {
        if (targetEntity == null || !(targetEntity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity targetPlayer = (PlayerEntity)targetEntity;
        if (!targetPlayer.isBlocking() && !targetPlayer.isUsingItem()) {
            return;
        }
        PlayerInventory inventory = this.mc.player.getInventory();
        int axeSlot = -1;
        for (int i2 = 0; i2 < 9; ++i2) {
            ItemStack stack = inventory.getStack(i2);
            if (stack.isEmpty() || !(stack.getItem() instanceof AxeItem)) continue;
            axeSlot = i2;
            break;
        }
        if (axeSlot == -1) {
            return;
        }
        int oldSlot = this.mc.player.getInventory().selectedSlot;
        if (axeSlot != oldSlot) {
            this.mc.player.getInventory().selectedSlot = axeSlot;
            this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(axeSlot));
        }
        for (int i3 = 0; i3 < 4; ++i3) {
            this.mc.interactionManager.attackEntity((PlayerEntity)this.mc.player, (Entity)targetEntity);
            this.mc.player.swingHand(Hand.MAIN_HAND);
            try {
                Thread.sleep(35L);
                continue;
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
        }
        if (axeSlot != oldSlot) {
            this.mc.player.getInventory().selectedSlot = oldSlot;
            this.mc.player.networkHandler.sendPacket((Packet)new UpdateSelectedSlotC2SPacket(oldSlot));
        }
    }

    private void updateTarget() {
        LivingEntity best = null;
        Vec3d eyePos = this.mc.player.getEyePos();
        Vec3d lookVec = this.mc.player.getRotationVec(1.0f);
        LivingEntity priorityTarget = null;
        for (Entity entity : this.mc.world.getEntities()) {
            PlayerEntity player;
            if (!(entity instanceof LivingEntity)) continue;
            LivingEntity living = (LivingEntity)entity;
            if (!this.isValidEntity(entity) || !(entity instanceof PlayerEntity) || !this.priorityTargets.contains((player = (PlayerEntity)entity).getNameForScoreboard())) continue;
            priorityTarget = living;
            break;
        }
        if (priorityTarget != null) {
            best = priorityTarget;
        } else {
            double bestScore = this.targetPriority.is("FOV") ? -1.0 : Double.MAX_VALUE;
            for (Entity entity : this.mc.world.getEntities()) {
                double dist;
                if (!(entity instanceof LivingEntity)) continue;
                LivingEntity living = (LivingEntity)entity;
                if (!this.isValidEntity(entity)) continue;
                if (this.targetPriority.is("FOV")) {
                    Vec3d targetVec = BestPoint.getNearestPoint(entity).subtract(eyePos).normalize();
                    double dot = lookVec.dotProduct(targetVec);
                    if (!(dot > bestScore)) continue;
                    bestScore = dot;
                    best = living;
                    continue;
                }
                if (this.targetPriority.is("Мало здоровья")) {
                    double health = living.getHealth();
                    if (!(health < bestScore)) continue;
                    bestScore = health;
                    best = living;
                    continue;
                }
                if (!this.targetPriority.is("По дистанции") || !((dist = eyePos.distanceTo(BestPoint.getNearestPoint(entity))) < bestScore)) continue;
                bestScore = dist;
                best = living;
            }
        }
        if (this.target != null && !this.isValidEntity((Entity)this.target)) {
            if (this.target.isRemoved() || this.target.getHealth() <= 0.0f) {
                this.target = best;
            } else {
                --this.targetLossTimer;
                if (this.targetLossTimer <= 0) {
                    this.target = best;
                }
            }
        } else {
            this.targetLossTimer = 40;
            if (this.target == null) {
                this.target = best;
            }
        }
    }

    private void updateRWRotation(LivingEntity target) {
        if (target == null) {
            return;
        }
        boolean overtaking = this.elytraTarget != null && this.elytraTarget.isEnabled() && this.elytraTarget.predictate.getValue() && this.mc.player.isGliding() && target.isGliding() && target.getVelocity().horizontalLengthSquared() > 0.001;
        Vec3d targetPoint = overtaking ? PredictUtils.predict(target, this.elytraTarget.predictValue.getValue()).add(0.0, (target.getBoundingBox().maxY - target.getBoundingBox().minY) * 0.8, 0.0) : target.getEyePos();
        Rotation angle = new Rotation(RotationUtil.sample(targetPoint));
        float targetYaw = angle.getYaw();
        float targetPitch = angle.getPitch();
        float deltaYaw = MathHelper.wrapDegrees((float)(targetYaw - this.lastYaw));
        float deltaPitch = targetPitch - this.lastPitch;
        float newYaw = this.lastYaw + deltaYaw;
        float newPitch = this.lastPitch + deltaPitch;
        float gcd = GCDFixer.getGCDValue();
        newYaw -= (newYaw - this.lastYaw) % gcd;
        newPitch -= (newPitch - this.lastPitch) % gcd;
        newPitch = MathHelper.clamp((float)newPitch, (float)-90.0f, (float)90.0f);
        Rotation RWRot = new Rotation(newYaw, newPitch);
        RotationComponent.update(RWRot, 360.0f, 360.0f, 360.0f, 360.0f, 0, 1, false);
        this.lastYaw = RWRot.getYaw();
        this.lastPitch = RWRot.getPitch();
    }

    private void updateReallyWorldRotation(LivingEntity target) {
        boolean bothGliding;
        if (target == null) {
            return;
        }
        boolean overtaking = this.elytraTarget != null && this.elytraTarget.isEnabled() && this.elytraTarget.predictate.getValue() && this.mc.player.isGliding() && target.isGliding() && target.getVelocity().horizontalLengthSquared() > 0.001;
        Vec3d targetPoint = overtaking ? PredictUtils.predict(target, this.elytraTarget.predictValue.getValue()).add(0.0, (target.getBoundingBox().maxY - target.getBoundingBox().minY) * 0.45, 0.0) : this.resolveMultipoint(target, target.getBoundingBox().getCenter(), this.distance.getValue());
        Rotation angle = new Rotation(RotationUtil.sample(targetPoint));
        float targetYaw = angle.getYaw();
        float targetPitch = angle.getPitch();
        float deltaYaw = MathHelper.wrapDegrees((float)(targetYaw - this.lastYaw));
        float deltaPitch = targetPitch - this.lastPitch;
        float absDeltaYaw = Math.abs(deltaYaw);
        float absDeltaPitch = Math.abs(deltaPitch);
        float yawSpeed = absDeltaYaw / (absDeltaYaw + 8.0f);
        float pitchSpeed = absDeltaPitch / (absDeltaPitch + 5.0f);
        boolean bl = bothGliding = this.mc.player.isGliding() && target.isGliding();
        if (bothGliding) {
            yawSpeed = Math.min(yawSpeed * 1.15f, 0.98f);
            pitchSpeed = Math.min(pitchSpeed * 1.1f, 0.98f);
        }
        float gcd = GCDFixer.getGCDValue();
        float newYaw = this.lastYaw + deltaYaw * yawSpeed;
        float newPitch = this.lastPitch + deltaPitch * pitchSpeed;
        newYaw -= (newYaw - this.lastYaw) % gcd;
        newPitch -= (newPitch - this.lastPitch) % gcd;
        float jitterPhase = (float)(System.currentTimeMillis() % 2000L) / 2000.0f * (float)Math.PI * 2.0f;
        float jitterScale = MathHelper.clamp((float)(absDeltaYaw / 30.0f), (float)0.1f, (float)0.6f);
        float yawJitter = (float)(Math.sin(jitterPhase) * (double)gcd * (double)0.3f * (double)jitterScale + Math.sin(jitterPhase * 2.7f + 1.3f) * (double)gcd * (double)0.15f * (double)jitterScale);
        float pitchJitter = (float)Math.sin(jitterPhase * 1.7f + 2.1f) * gcd * 0.12f;
        newYaw += yawJitter;
        newPitch += pitchJitter;
        newYaw -= (newYaw - this.lastYaw) % gcd;
        newPitch -= (newPitch - this.lastPitch) % gcd;
        newPitch = MathHelper.clamp((float)newPitch, (float)-90.0f, (float)90.0f);
        Rotation rot = new Rotation(newYaw, newPitch);
        RotationComponent.update(rot, 360.0f, 360.0f, 360.0f, 360.0f, 0, 1, false);
        this.lastYaw = rot.getYaw();
        this.lastPitch = rot.getPitch();
    }

    private void updateHWRotation(LivingEntity target) {
        if (target == null) {
            return;
        }
        boolean overtaking = this.elytraTarget != null && this.elytraTarget.isEnabled() && this.elytraTarget.predictate.getValue() && this.mc.player.isGliding() && target.isGliding() && target.getVelocity().horizontalLengthSquared() > 0.001;
        Vec3d overtake = overtaking ? PredictUtils.predict(target, this.elytraTarget.predictValue.getValue()) : null;
        Vec3d point = overtake != null && this.elytraTarget != null && this.elytraTarget.isEnabled() ? overtake : this.resolveMultipoint(target, target.getBoundingBox().getCenter(), this.distance.getValue());
        Rotation targetAngle = new Rotation(RotationUtil.sample(point));
        Box box = target.getBoundingBox();
        float radYaw = (float)Math.toRadians(this.lastYaw);
        float radPitch = (float)Math.toRadians(this.lastPitch);
        Vec3d aimVec = new Vec3d(-Math.sin(radYaw) * Math.cos(radPitch), -Math.sin(radPitch), Math.cos(radYaw) * Math.cos(radPitch));
        boolean isElytra = this.mc.player.isGliding() && target.isGliding();
        float maxAcc = isElytra ? this.HWMaxAccelerationElytra.getFloatValue() : this.HWMaxAccelerationGround.getFloatValue();
        float minAcc = isElytra ? this.HWMinAccelerationElytra.getFloatValue() : this.HWMinAccelerationGround.getFloatValue();
        float rayDist = isElytra ? this.HWRaytraceDistElytra.getFloatValue() : this.HWRaytraceDistGround.getFloatValue();
        float yawDiff = Math.abs(MathHelper.wrapDegrees((float)(targetAngle.getYaw() - this.lastYaw)));
        boolean rayhit = RaytraceUtil.rayTrace(aimVec, rayDist, box);
        if (this.back) {
            float decel = yawDiff > this.HWYawThreshold.getFloatValue() ? this.HWDecelerationFastYaw.getFloatValue() : this.HWDecelerationSlow.getFloatValue();
            this.speedAcceleration -= decel;
            if (this.speedAcceleration <= minAcc) {
                this.speedAcceleration = minAcc;
                this.back = false;
            }
        } else {
            this.speedAcceleration += this.HWAcceleration.getFloatValue();
            if (this.speedAcceleration >= maxAcc || rayhit) {
                if (this.speedAcceleration > maxAcc) {
                    this.speedAcceleration = maxAcc;
                }
                this.back = true;
            }
        }
        float RW = Math.max(this.speedAcceleration, 0.0f);
        float t2 = Math.min(RW, 1.0f);
        float pitchT = Math.min(t2 * 1.5f, 1.0f);
        float yawStep = MathHelper.wrapDegrees((float)(targetAngle.getYaw() - this.lastYaw));
        float pitchStep = targetAngle.getPitch() - this.lastPitch;
        float newYaw = this.lastYaw + yawStep * t2;
        float newPitch = this.lastPitch + pitchStep * pitchT;
        float gcd = GCDFixer.getGCDValue();
        newYaw -= (newYaw - this.lastYaw) % gcd;
        newPitch -= (newPitch - this.lastPitch) % gcd;
        newPitch = MathHelper.clamp((float)newPitch, (float)-90.0f, (float)90.0f);
        Rotation rot = new Rotation(newYaw, newPitch);
        RotationComponent.update(rot, 360.0f, 360.0f, 360.0f, 360.0f, 0, 1, false);
        this.lastYaw = rot.getYaw();
        this.lastPitch = rot.getPitch();
    }

    private void updateNeuroRotation(LivingEntity target) {
        if (!this.neuroSystem.isUsingNeuro()) {
            Vec2f perfectRot = RotationUtil.sample(target.getEyePos());
            float targetYaw = perfectRot.x;
            float targetPitch = perfectRot.y;
            float diffYaw = MathHelper.wrapDegrees((float)(targetYaw - this.lastYaw));
            float diffPitch = targetPitch - this.lastPitch;
            float speed = MathHelper.clamp((float)(18.0f - Math.abs(diffYaw) * 0.12f), (float)2.0f, (float)18.0f);
            float newYaw = this.lastYaw + Math.copySign(Math.min(Math.abs(diffYaw), speed + (float)Math.random() * 3.0f), diffYaw);
            float newPitch = this.lastPitch + Math.copySign(Math.min(Math.abs(diffPitch), speed * 0.6f + (float)Math.random()), diffPitch);
            float gcd = GCDFixer.getGCDValue();
            newYaw -= (newYaw - this.lastYaw) % gcd;
            newPitch -= (newPitch - this.lastPitch) % gcd;
            newPitch = MathHelper.clamp((float)newPitch, (float)-90.0f, (float)90.0f);
            this.lastYaw = newYaw;
            this.lastPitch = newPitch;
            RotationComponent.update(new Rotation(newYaw, newPitch), 360.0f, 360.0f, 360.0f, 360.0f, 0, 1, false);
            return;
        }
        boolean isIdle = this.attackTimer.finished(300.0);
        Rotation neuroRot = this.neuroSystem.getNeuroRotation(target, isIdle);
        if (neuroRot != null) {
            float yawDiff = RotationUtil.getAngleDifference(neuroRot.getYaw(), this.lastYaw);
            float pitchDiff = Math.abs(neuroRot.getPitch() - this.lastPitch);
            float baseSpeed = MathHelper.clamp((float)(22.0f - Math.abs(yawDiff) * 0.08f), (float)1.5f, (float)22.0f);
            float yawSpeed = baseSpeed + (float)(Math.random() * 6.0);
            float pitchSpeed = pitchDiff / 30.0f * 20.0f + (float)(Math.random() * 4.0);
            RotationComponent.update(neuroRot, yawSpeed, pitchSpeed, (float)(Math.random() * 45.0 + 5.0), 1, 1);
            this.lastYaw = neuroRot.getYaw();
            this.lastPitch = neuroRot.getPitch();
        }
    }

    private Vec3d resolveMultipoint(LivingEntity target, Vec3d point, double range) {
        return target == null ? point : BestPoint.getNearestVisiblePoint((Entity)target, point, range);
    }

    @Override
    public void onEnable() {
        this.target = null;
        this.razvorotikTicks = 0;
        this.targetLossTimer = 0;
        Babka.getInstance().getModuleStorage().setSpeedAcceleration(0.0f);
        if (this.elytraTarget == null) {
            this.elytraTarget = Instance.get(ElytraTarget.class);
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.target = null;
        this.ticksToAttack = 0;
        this.speedAcceleration = 0.0f;
        this.razvorotikTicks = 0;
        Babka.getInstance().getModuleStorage().setSpeedAcceleration(0.0f);
        Babka.getInstance().getModuleStorage().setRandomness(1.0f);
        super.onDisable();
    }

    public LivingEntity getTarget() {
        return this.target;
    }
}

