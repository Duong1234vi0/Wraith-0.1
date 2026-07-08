# GrimAC Checks Catalog

> Reference for auditing hacked-client modules against GrimAC.
> Source: `AnotherProject/Grim/common/src/main/java/ac/grim/grimac/checks/impl/`
> Generated 2026-05-28. Simulation check is documented separately — see Sprint module work notes.

## Summary Table by Category

| Category | Count | Checks |
|----------|-------|--------|
| Aim | 2 | AimDuplicateLook, AimModulo360 |
| Baritone | 1 | Baritone (DISABLED) |
| BadPackets | 26 | BadPacketsA-Z |
| Breaking | 10 | AirLiquidBreak, FastBreak, FarBreak, InvalidBreak, MultiBreak, NoSwingBreak, PositionBreakA, PositionBreakB, RotationBreak, WrongBreak |
| Chat | 4 | ChatA, ChatB, ChatC, ChatD |
| Combat | 5 | Hitboxes, MultiInteractA, MultiInteractB, Reach, SelfInteract |
| Crash | 9 | CrashA-I |
| Elytra | 9 | ElytraA-I |
| Exploit | 2 | ExploitA, ExploitB |
| Flight | 1 | FlightA |
| GroundSpoof | 1 | NoFall |
| Misc | 4 | ClientBrand, GhostBlockMitigation, Post, TransactionOrder |
| Movement | 1 | NoSlow |
| MultiActions | 7 | MultiActionsA-G |
| PacketOrder | 16 | PacketOrderA-P |
| Prediction | 3 | GroundSpoof, OffsetHandler (Simulation), Phase |
| Scaffolding | 9 | AirLiquidPlace, DuplicateRotPlace, FabricatedPlace, FarPlace, InvalidPlaceA, InvalidPlaceB, MultiPlace, PositionPlace, RotationPlace |
| Sprint | 7 | SprintA-G |
| Timer | 5 | NegativeTimer, TickTimer, Timer, TimerLimit, VehicleTimer |

Total ~122 checks (Simulation documented separately).

---

## Aim Checks

### AimDuplicateLook
- **Name:** AimDuplicateLook
- **Category:** Aim
- **Class path:** `aim/AimDuplicateLook.java:10`
- **Validates:** Identical rotation packets in consecutive ticks.
- **Trigger:** `rotationUpdate.getFrom().equals(rotationUpdate.getTo())` after exempting teleports/vehicle switches/horse interactions.
- **Behavior:** Flags only.
- **False positives:** Network hiccups, teleports, forced rotation changes.
- **Bypass:** Space identical rotations across multiple ticks; jitter pitch/yaw by tiny amount.

### AimModulo360
- **Name:** AimModulo360
- **Category:** Aim
- **Class path:** `aim/AimModulo360.java:12`
- **Validates:** Detects clients wrapping yaw using modulo 360.
- **Trigger:** `abs(deltaXRot) > 320 && abs(lastDeltaYaw) < 30` when yaw in [-360, 360].
- **Behavior:** Decay 0.005, flags only.
- **False positives:** Teleports, vehicle switches.
- **Bypass:** Keep yaw within normal bounds; smaller rotation deltas.

### Baritone (DISABLED)
- **Name:** Baritone
- **Class path:** `baritone/Baritone.java:13`
- **Validates:** Baritone bot pathing pattern (zero yaw delta + small pitch + low divisor).
- **Trigger:** `deltaXRot == 0 && deltaPitch in (0,1) && divisorY < MIN` for 8 consecutive occurrences.
- **Behavior:** Currently disabled per code comment ("patched by Baritone, false with cinematic camera").

---

## Combat Checks

### Reach
- **Class path:** `combat/Reach.java:57`
- **Validates:** Attacks beyond vanilla reach. Accounts for item attack range (1.21.11+), hitbox margin, movement uncertainty.
- **Trigger:** `minDistance > maxReach` (real-time + post-tick). Default vanilla 3.0 + threshold ≈ 3.05.
- **Behavior:** Setback 10; can cancel impossible hits if `cancelImpossibleHits` enabled.
- **False positives:** Boats/shulkers/end crystals (blacklisted). 1.8 clients get +0.1 hitbox margin.
- **Bypass surface:** Stay ≤ 3.05 blocks; abuse blacklisted entities; high-ping desync; hitbox margin exploitation.

### Hitboxes
- **Class path:** `combat/Hitboxes.java:7`
- **Validates:** Stub flagged when entity hitbox cannot be intersected.
- **Trigger:** From Reach when `minDistance == Double.MAX_VALUE`.
- **Behavior:** Setback 10.

### SelfInteract
- **Class path:** `combat/SelfInteract.java:13`
- **Validates:** Player attacking/interacting with self.
- **Trigger:** `cameraEntity.isSelf() && entityId == player.entityID`.
- **Behavior:** Instant ban; cancels packet.
- **Bypass:** None — hard protocol violation.

### MultiInteractA / MultiInteractB
- **Class paths:** `combat/MultiInteractA.java:16`, `combat/MultiInteractB.java:16`
- **Validates:** Multiple entity interactions (A) or cursor changes (B) within one tick.
- **Trigger:** `hasInteracted && (entity != lastEntity || sneaking changed)` (A); `!pos.equals(lastPos)` on INTERACT_AT (B).
- **Behavior:** Flags + cancels if not skipping ticks. Experimental.
- **Bypass:** Space interactions across ticks.

---

## Breaking Checks

### FastBreak
- **Class path:** `breaking/FastBreak.java:27`
- **Validates:** Block break speed vs. vanilla expectation. Tracks damage accumulation + 0.3s delay between non-instant breaks.
- **Trigger:** `blockBreakBalance > 1000` (>1s advantage) or `blockDelayBalance > 1000`.
- **Behavior:** Cancels break; balance clamped to ±transaction ping.
- **Bypass:** Space breaks across ticks; instant-break blocks; ping-based balance abuse.

### FarBreak
- **Class path:** `breaking/FarBreak.java:14`
- **Validates:** Block break beyond reach. Experimental.
- **Trigger:** `min > maxReach²` (block_interaction_range + threshold).
- **Behavior:** Cancels.
- **Bypass:** Stay within reach + threshold; exploit movement uncertainty.

### NoSwingBreak
- **Class path:** `breaking/NoSwingBreak.java:12`
- **Validates:** Break without animation packet. Experimental.
- **Trigger:** `sentBreak && !sentAnimation` end-of-tick.
- **Bypass:** Send animation packet.

### MultiBreak
- **Class path:** `breaking/MultiBreak.java:18`
- **Validates:** Multiple block faces broken in same tick. Experimental.
- **Bypass:** Space breaks; single block per tick.

### WrongBreak
- **Class path:** `breaking/WrongBreak.java:18`
- **Validates:** Impossible break sequences (CANCELLED on wrong block, FINISHED without START).
- **Bypass:** Send proper START_DIGGING.

### RotationBreak
- **Class path:** `breaking/RotationBreak.java:24`
- **Validates:** Break without line-of-sight. Experimental.
- **Trigger:** Ray trace from eye to block fails; `flagBuffer` decays 0.1 per legit hit.
- **Bypass:** Maintain LOS; eye-height uncertainty windows.

### PositionBreakA / PositionBreakB
- **Class paths:** `breaking/PositionBreakA.java:12`, `breaking/PositionBreakB.java:12`
- **Validates:** Break from impossible side of block face (A); CANCELLED face mismatch (B).
- **Bypass:** Use valid face IDs; movement threshold abuse.

### InvalidBreak
- **Class path:** `breaking/InvalidBreak.java:11`
- **Validates:** Impossible face IDs (<0 or >5, except 255 on 1.7).
- **Behavior:** Instant ban.

### AirLiquidBreak
- **Class path:** `breaking/AirLiquidBreak.java:17`
- **Validates:** Breaking unbreakable blocks (air, water, lava, fire ≤1.15.2, hardness=-1).
- **False positives:** Kelp (special-case two START packets per tick); light block + light item.

---

## Scaffolding Checks (Block Place)

### FarPlace
- **Class path:** `scaffolding/FarPlace.java:14`
- **Validates:** Place beyond reach (excludes scaffolding).
- **Trigger:** `min > maxReach²`.

### AirLiquidPlace
- **Class path:** `scaffolding/AirLiquidPlace.java:15`
- **Validates:** Place against air/liquid (no support). Heavily commented for break-then-place edge cases.
- **False positives:** Break+place same tick (mitigated via block history); grass swap; ping desync.
- **Bypass:** Place against valid blocks; exploit block-history timing.

### InvalidPlaceA / InvalidPlaceB
- **Class paths:** `scaffolding/InvalidPlaceA.java:9`, `scaffolding/InvalidPlaceB.java:10`
- **Validates:** Non-finite cursor (NaN/Inf, A); invalid face ID (B).
- **Behavior:** Instant ban path.

### FabricatedPlace
- **Class path:** `scaffolding/FabricatedPlace.java:11`
- **Validates:** Cursor outside [0,1] (or [0,1.5] for lecterns/extended shapes). Float-error tolerant.
- **Bypass:** Stay in bounds; exploit FP precision near 1.0.

### RotationPlace
- **Class path:** `scaffolding/RotationPlace.java:23`
- **Validates:** Place without LOS (excludes scaffolding). Same flagBuffer pattern as RotationBreak.
- **Bypass:** Maintain LOS; tiny rotation adjustments.

### DuplicateRotPlace
- **Class path:** `scaffolding/DuplicateRotPlace.java:9`
- **Validates:** Place with identical rotation deltas. Experimental.
- **Trigger:** `xDiff < 0.0001` when deltaX > 2°.

### MultiPlace
- **Class path:** `scaffolding/MultiPlace.java:17`
- **Validates:** Multiple different placements in one tick.

### PositionPlace
- **Class path:** `scaffolding/PositionPlace.java:10`
- **Validates:** Place against hidden face (geometric).

---

## Chat Checks

### ChatA
- **Class path:** `chat/ChatA.java:13`
- **Validates:** Blank or "/" tab-complete (1.13+).
- **Behavior:** Cancels packet.

### ChatB
- **Class path:** `chat/ChatB.java:17`
- **Validates:** Invalid chat (empty, leading/trailing whitespace, "/"-prefix on 1.19+).

### ChatC
- **Class path:** `chat/ChatC.java:19`
- **Validates:** Chat while moving/sprinting/sneaking/sending input. Experimental.
- **Bypass:** Stop movement before chatting.

### ChatD
- **Class path:** `chat/ChatD.java:12`
- **Validates:** Chat while client setting `chatVisibility == HIDDEN`.

---

## Timer Checks

### Timer
- **Class path:** `timer/Timer.java:12`
- **Validates:** Player clock (transaction ping derived) ahead of real time.
- **Trigger:** `timerBalanceRealTime > System.nanoTime()`.
- **Behavior:** Setback 10; balance clamped to ±clockDrift (120ms default).
- **Bypass:** Match real-time pace; standing still resets balance.

### NegativeTimer
- **Class path:** `timer/NegativeTimer.java:10`
- **Validates:** Player slower than real time, then catching up. Experimental.
- **Trigger:** `timerBalanceRealTime < lastMovementClock - clockDrift`.

### TickTimer / TimerLimit / VehicleTimer
- See follow-up section below — additional timer variants for tick rate, hard limit, and vehicle-specific timing.

---

## Movement / NoSlow

### NoSlow (Grim's check, not the client module)
- **Class path:** `movement/NoSlow.java`
- **Validates:** Confirms slow-item velocity matches expected slowdown.
- **Note:** Most slowdown checking is folded into Simulation. This check exists as a dedicated supplementary path. Our `ModuleNoSlow` GrimTick port targets the Simulation lenience window, not this check directly.

---

## Sprint Checks (SprintA–G)

Sprint checks live at `sprint/SprintA.java` through `sprint/SprintG.java`. They validate consistency between client-reported sprint state and physics:
- Sprint without forward input
- Sprint while sneaking
- Sprint with food ≤ 6
- Sprint with blindness
- Sprint while colliding (the SprintE we already fixed)
- Sprint while using item
- Sprint while crawling
**Module audit notes:** our `ModuleSprint` (after fixes) gates LEGIT mode on `forward && !backward` and on `!horizontalCollision || ignoreCollision`, which lines up with most of these.

---

## Prediction Checks

### Simulation (OffsetHandler)
- **Class path:** `prediction/OffsetHandler.java:14`
- Documented separately. Default flag threshold 0.001, immediate setback 0.1, advantage cap 1, ceiling 4.

### Phase
- **Class path:** `prediction/Phase.java`
- **Validates:** Player is inside a non-passable block (clipping).
- **Bypass:** Stay outside collision boxes; exploit 0.03 movement window.

### GroundSpoof (prediction)
- **Validates:** Client claims onGround inconsistent with server simulation.

---

## GroundSpoof / NoFall
- **Class path:** `groundspoof/NoFall.java`
- **Validates:** Player reports onGround=true while airborne.
- **Bypass:** Send accurate onGround; spoof only at landing (Phase still flags ungrounded vertical drift).

---

## BadPackets, Crash, Elytra, Exploit, Flight, Misc, MultiActions, PacketOrder
The catalog truncated mid-write while the agent was paginating. These categories total ~85 checks. A follow-up agent run will fill in per-check detail. High-level shape:

- **BadPackets A–Z:** invalid/impossible packet states (sneaking flag desync, abilities packet, animation flag, channel, etc.). Mostly instant-fail style — preventable by sending only well-formed packets.
- **Crash A–I:** known client/server crash exploits (chunk send abuse, oversize NBT, malformed pose).
- **Elytra A–I:** elytra physics consistency — gliding without elytra, impossible boost, fireworks abuse.
- **Exploit A/B:** assorted protocol exploits.
- **Flight A:** the dedicated flight check beyond Simulation.
- **MultiActions A–G:** combinations of incompatible actions in one tick (e.g., attack + place + use simultaneously).
- **PacketOrder A–P:** strict ordering of stateful packet sequences (e.g., teleport-confirm before move, abilities before flying state).
- **Misc:** ClientBrand, GhostBlockMitigation, Post (post-tick rotation/digging), TransactionOrder.

---

## Audit Implications for Client Modules

| Module type | Watch for | Mitigation |
|---|---|---|
| KillAura / Reach | combat/Reach (≤3.05), MultiInteractA, Hitboxes | Cap reach 3.0; one entity per tick |
| Sprint | Sprint A–G + Simulation collision velocity | Already gated on collision + forward in LEGIT |
| NoSlow | Simulation isUsingItem branch | GrimTick port consumes window once per 2 ticks |
| Scaffold | Scaffolding/* + Post + RotationPlace | Maintain LOS, valid face IDs, no multi-place |
| Speed/Fly | Simulation + Timer + Phase | Per-server bypass profile required |
| Velocity / NoPush | Simulation knockback handler | Modify position to match received KB |
| Timer-based modules | Timer, NegativeTimer, TickTimer | Balance vs. clockDrift (120ms) |
| AutoBlock / NoSwing / interaction | NoSwingBreak, MultiInteractB, BadPackets | Always send animation packets |
| Phase / clip | Phase, Simulation | 0.03 movement window only; never persist inside a block |

---

## Default Config Knobs (selected)

- Simulation threshold: `0.001`
- Simulation immediate setback: `0.1`
- Simulation max advantage: `1.0`
- Simulation max ceiling: `4.0`
- Simulation decay (per tick): `0.999`
- AimModulo360 decay: `0.005`
- Timer clockDrift: `120ms`
- FastBreak balance cap: `1000ms`
- Reach maxReach: vanilla 3.0 + threshold (~3.05)

## Elytra Checks

### ElytraA
- **Class path:** `elytra/ElytraA.java:12`
- **Validates:** Start gliding while already gliding.
- **Trigger:** `player.isGliding` on START_FLYING_WITH_ELYTRA.
- **Behavior:** Setback, packet cancelled, pose resynced.
- **Bypass:** None — vanilla blocks double-glide.

### ElytraB
- **Class path:** `elytra/ElytraB.java:13`
- **Validates:** Glide initiated without releasing jump or with no jump on next tick.
- **Behavior:** Setback, cancel.
- **Bypass:** Hold jump → release → send movement input properly.

### ElytraC
- **Class path:** `elytra/ElytraC.java:14`
- **Validates:** Multiple glide packets within 2 ticks.
- **Trigger:** `glideThisTick || glideLastTick` consecutively.
- **Behavior:** Setback or flag accumulation.
- **Bypass:** Space glide packets ≥2 ticks apart.

### ElytraD
- **Class path:** `elytra/ElytraD.java:14`
- **Validates:** Gliding without elytra in inventory (1.9+).
- **Trigger:** `!player.canGlide()`.
- **Behavior:** Setback, cancel, pose resync.
- **Bypass:** Carry valid elytra.

### ElytraE
- **Class path:** `elytra/ElytraE.java:14`
- **Validates:** Gliding while in creative/spectator flight (1.9+).
- **Trigger:** `player.isFlying`.
- **Bypass:** Exit flight mode first.

### ElytraF
- **Class path:** `elytra/ElytraF.java:14`
- **Validates:** Gliding while on ground (1.9+).
- **Trigger:** `player.clientClaimsLastOnGround`.
- **Bypass:** Jump first.

### ElytraG
- **Class path:** `elytra/ElytraG.java:15`
- **Validates:** Gliding while levitating (1.16+).
- **Trigger:** Levitation potion active.
- **Bypass:** Wait for effect to expire.

### ElytraH
- **Class path:** `elytra/ElytraH.java:14`
- **Validates:** Gliding while in vehicle (1.9+).
- **Bypass:** Dismount first.

### ElytraI
- **Class path:** `elytra/ElytraI.java:14`
- **Validates:** Gliding in water (1.15+).
- **Trigger:** `player.wasTouchingWater`.
- **Bypass:** Exit water first.

---

## Flight Check

### FlightA
- **Class path:** `flight/FlightA.java:10`
- **Validates:** Sending FLYING packets without flight permission.
- **Trigger:** `WrapperPlayClientPlayerFlying.isFlying(packet) && !player.isFlying`.
- **Behavior:** Flag-only. No setback.
- **Bypass:** None per code comment ("catches 100% of cheaters").

---

## MultiActions Checks

### MultiActionsA
- **Class path:** `multiactions/MultiActionsA.java:15`
- **Validates:** Attack/dig/spectate while using slow item (bow, shield, etc.).
- **Trigger:** `isSlowedByUsingItem()` + ATTACK/INTERACT_ENTITY/SPECTATE_ENTITY/PLAYER_DIGGING.
- **Behavior:** Cancel packet.
- **Bypass:** Release item before attacking; off-hand item slot quirks.

### MultiActionsB
- **Class path:** `multiactions/MultiActionsB.java:12`
- **Validates:** Block-break while using item.
- **Behavior:** Cancel break.
- **Bypass:** Release item before mining (vanilla in 1.7-).

### MultiActionsC
- **Class path:** `multiactions/MultiActionsC.java:16`
- **Validates:** Inventory click while sprinting/sneaking-pre-1.15/moving.
- **Behavior:** Cancel.
- **Bypass:** Stop moving before click; sneak allowed in 1.15+.

### MultiActionsD
- **Class path:** `multiactions/MultiActionsD.java:11`
- **Validates:** Inventory close while moving.
- **Behavior:** Flag-only (cancel would desync).
- **Bypass:** Stop moving before close.

### MultiActionsE
- **Class path:** `multiactions/MultiActionsE.java:15`
- **Validates:** Arm swing while using item.
- **Behavior:** Cancel.
- **Bypass:** Release item; drop-item exempt; false-positive on 1.7.

### MultiActionsF
- **Class path:** `multiactions/MultiActionsF.java:17`
- **Validates:** Block + entity interaction in same tick.
- **Behavior:** Cancel or flag accumulation.
- **Bypass:** Space them ≥1 tick apart.

### MultiActionsG
- **Class path:** `multiactions/MultiActionsG.java:14`
- **Validates:** Acting while rowing boat (1.9+).
- **Trigger:** Action packets while in boat + steering.
- **Bypass:** Stop steering first.

---

## Sprint Checks (detailed)

### SprintA
- **Class path:** `sprint/SprintA.java:12`
- **Validates:** Sprint with food ≤ 6.
- **Trigger:** `player.food <= 6.0F && isSprinting`.
- **Behavior:** Cancel + setback (VL).
- **Bypass:** Eat to 6.5+. Flying/camel exempt.
- **Module impact:** `Sprint.Ignore.HUNGER` is the dedicated hook (`hookSprintIgnoreHunger`). Already wired.

### SprintB
- **Class path:** `sprint/SprintB.java:14`
- **Validates:** Sprint while sneaking/crawling.
- **Trigger:** `isSlowMovement && sneakingSpeedMultiplier < 0.8 && isSprinting`.
- **Behavior:** Setback VL 5.
- **Bypass:** Stop sneaking. Exempt 1.14.2+ except 1.21.4. Exempt during glide-after-flight.

### SprintC
- **Class path:** `sprint/SprintC.java:11`
- **Validates:** Sprint while using item in water (consecutive ticks).
- **Behavior:** Setback VL 5.
- **Bypass:** Release item. Exempt 1.14.2+ except 1.21.4.

### SprintD
- **Class path:** `sprint/SprintD.java:15`
- **Validates:** Sprint while blinded.
- **Trigger:** `hasPotionEffect(BLINDNESS) && isSprinting` and sprint started after blindness applied.
- **Behavior:** Setback VL 5.
- **Bypass:** Sprint *before* blindness lands.
- **Module impact:** `Sprint.Ignore.BLINDNESS` does not have a dedicated mixin yet — only HUNGER and COLLISION do. Currently the toggle is read but unused. Audit follow-up.

### SprintE
- **Class path:** `sprint/SprintE.java:14`
- **Validates:** Sprint while horizontally colliding (the flag we already fixed).
- **Trigger:** `wasHardHorizontalCollision && !startedSprintingThisTick && isSprinting && !nearGlitchyBlock`.
- **Behavior:** Setback VL 5.
- **Bypass:** Stop sprinting on collision; near anvils/chests exempt; vehicle/recent vehicle switch exempt.
- **Module impact:** Fixed via `hookSprintIgnoreCollision` + module-side gate on `player.horizontalCollision`.

### SprintF
- **Class path:** `sprint/SprintF.java:11`
- **Validates:** Sprint while gliding (1.21.4 only).
- **Bypass:** Don't sprint while gliding.

### SprintG
- **Class path:** `sprint/SprintG.java:12`
- **Validates:** Sprint while in water but not swimming.
- **Trigger:** `wasTouchingWater && !wasEyeInWater && !isSwimming && isSprinting`.
- **Bypass:** Submerge head (forces swim state); camel exempt; pre-1.13 exempt.

---

## BadPackets Checks

### BadPacketsA
- **Class path:** `badpackets/BadPacketsA.java:12`
- **Validates:** Duplicate held-item slot changes.
- **Trigger:** `slot == lastSlot`.
- **Behavior:** Cancel.
- **Bypass:** Don't repeat same slot ID.

### BadPacketsB
- **Class path:** `badpackets/BadPacketsB.java:10`
- **Validates:** Ignored rotation set packets via transaction tracking.
- **Behavior:** Flag-only.
- **Bypass:** Accept rotation packets in transaction window.

### BadPacketsC
- **Class path:** `badpackets/BadPacketsC.java:13`
- **Validates:** LEAVE_BED while not in bed. Experimental.
- **Bypass:** Only leave bed when in bed.

### BadPacketsD
- **Class path:** `badpackets/BadPacketsD.java:12`
- **Validates:** Pitch outside [-90, 90].
- **Behavior:** Cancel.
- **Bypass:** Clamp pitch.

### BadPacketsE
- **Class path:** `badpackets/BadPacketsE.java:15`
- **Validates:** Missing position updates for >19-20 ticks.
- **Behavior:** Flag-only.
- **Bypass:** Send position every 19 ticks.

### BadPacketsF
- **Class path:** `badpackets/BadPacketsF.java:12`
- **Validates:** Duplicate sprint state changes.
- **Behavior:** Cancel.
- **Bypass:** Alternate states correctly.

### BadPacketsG
- **Class path:** `badpackets/BadPacketsG.java:12`
- **Validates:** Duplicate sneak state changes.
- **Behavior:** Cancel.

### BadPacketsH
- **Class path:** `badpackets/BadPacketsH.java:16`
- **Validates:** Block-interaction sequence ID jump (1.19+).
- **Trigger:** `sequence != lastSequence + 1`.
- **Bypass:** Increment sequence by 1.

### BadPacketsI
- **Class path:** `badpackets/BadPacketsI.java:12`
- **Validates:** Spoofed flying ability claim.
- **Trigger:** `isFlying() && !player.canFly`.
- **Behavior:** Cancel.

### BadPacketsJ
- **Class path:** `badpackets/BadPacketsJ.java:19`
- **Validates:** USE_ITEM rotation mismatch with current/last tick (1.21+).
- **Behavior:** Flag-only.

### BadPacketsK
- **Class path:** `badpackets/BadPacketsK.java:12`
- **Validates:** SPECTATE outside spectator mode.
- **Behavior:** Cancel.

### BadPacketsL
- **Class path:** `badpackets/BadPacketsL.java:16`
- **Validates:** Non-dig actions with invalid position/face/sequence (must be 0,0,0).
- **Behavior:** Cancel.

### BadPacketsM
- **Class path:** `badpackets/BadPacketsM.java:18`
- **Validates:** PERFORM_RESPAWN while alive. Experimental.

### BadPacketsN
- **Class path:** `badpackets/BadPacketsN.java:8`
- **Validates:** Stub for invalid teleport detection.
- **Behavior:** Setback.

### BadPacketsO
- **Class path:** `badpackets/BadPacketsO.java:16`
- **Validates:** Keep-alive ID not in sent queue.
- **Behavior:** Cancel.

### BadPacketsP
- **Class path:** `badpackets/BadPacketsP.java:15`
- **Validates:** Invalid window-click button/type combo. Experimental.

### BadPacketsQ
- **Class path:** `badpackets/BadPacketsQ.java:13`
- **Validates:** Invalid horse jump-boost (`abs > 100` or wrong entity).
- **Behavior:** Cancel.

### BadPacketsR
- **Class path:** `badpackets/BadPacketsR.java:11`
- **Validates:** Position starvation (no movement >2s). Decay 0.25, experimental.

### BadPacketsS
- **Class path:** `badpackets/BadPacketsS.java:12`
- **Validates:** Rejected window confirmation.
- **Behavior:** Cancel.

### BadPacketsT
- **Class path:** `badpackets/BadPacketsT.java:17`
- **Validates:** Interact-at vector outside player hitbox (±0.3 horiz, -0.0001 to 1.8 vert).
- **Behavior:** Flag-only.

### BadPacketsU
- **Class path:** `badpackets/BadPacketsU.java:18`
- **Validates:** USE_ITEM with non-default position/cursor/sequence (must be -1,-1,-1 / 0,0,0 / 0).
- **Behavior:** Cancel.

### BadPacketsV
- **Class path:** `badpackets/BadPacketsV.java:14`
- **Validates:** Tick movement below threshold. Experimental.

### BadPacketsW
- **Class path:** `badpackets/BadPacketsW.java:8`
- **Validates:** Stub for non-existent entity interactions.

### BadPacketsX
- **Class path:** `badpackets/BadPacketsX.java:13`
- **Validates:** Multiple sprint/sneak actions per tick.
- **Behavior:** Setback. Experimental.

### BadPacketsY
- **Class path:** `badpackets/BadPacketsY.java:12`
- **Validates:** Hotbar slot >8 or <0.
- **Behavior:** Cancel.

### BadPacketsZ
- **Class path:** `badpackets/BadPacketsZ.java:11`
- **Validates:** Multiple PLAYER_INPUT before CLIENT_TICK_END. Experimental.

---

## Crash Checks

### CrashA
- **Class path:** `crash/CrashA.java:11`
- **Validates:** Position values that crash server.
- **Trigger:** `abs(x|z) > 2.9999999E7 || abs(y) > Integer.MAX_VALUE`.
- **Behavior:** Instant ban.

### CrashB
- **Class path:** `crash/CrashB.java:12`
- **Validates:** CREATIVE_INVENTORY_ACTION outside creative.
- **Behavior:** Cancel.

### CrashC
- **Class path:** `crash/CrashC.java:12`
- **Validates:** Non-finite position/rotation (NaN/Inf).
- **Behavior:** Instant ban.

### CrashD
- **Class path:** `crash/CrashD.java:17`
- **Validates:** CLICK_WINDOW on lectern (1.14+).
- **Behavior:** Cancel.

### CrashE
- **Class path:** `crash/CrashE.java:12`
- **Validates:** View distance < 2. Auto-corrects.

### CrashF
- **Class path:** `crash/CrashF.java:13`
- **Validates:** Invalid window-click button/slot (negative).
- **Behavior:** Cancel.

### CrashG
- **Class path:** `crash/CrashG.java:16`
- **Validates:** Negative sequence ID (1.19+).
- **Behavior:** Cancel.

### CrashH
- **Class path:** `crash/CrashH.java:12`
- **Validates:** Oversized tab-complete (>256, or >32500 op).
- **Behavior:** Cancel.

### CrashI
- **Class path:** `crash/CrashI.java:12`
- **Validates:** Bundle slot index < -1.
- **Behavior:** Cancel.

---

## Exploit Checks

### ExploitA
- **Class path:** `exploit/ExploitA.java:18`
- **Validates:** Anvil item-name length (30-50 depending on version).
- **Behavior:** Cancel.

### ExploitB
- **Class path:** `exploit/ExploitB.java:39`
- **Validates:** Book edits (slot/type/pages/title/author/NBT). Title ≤15 chars, ≤100 pages, ≤1023 chars per page.
- **Behavior:** Cancel.

---

## PacketOrder Checks

### PacketOrderA
- **Class path:** `packetorder/PacketOrderA.java:13`
- **Validates:** Window-click QUICK_MOVE vs PICKUP/PICKUP_ALL ordering. Experimental.

### PacketOrderB
- **Class path:** `packetorder/PacketOrderB.java:19`
- **Validates:** Attack with no preceding animation (1.8) or animation after attack (1.9+).

### PacketOrderC
- **Class path:** `packetorder/PacketOrderC.java:18`
- **Validates:** Entity interaction order — INTERACT_AT must precede INTERACT, with matching entity/hand/sneak.

### PacketOrderD
- **Class path:** `packetorder/PacketOrderD.java:14`
- **Validates:** OFF_HAND INTERACT requires preceding MAIN_HAND INTERACT.

### PacketOrderE
- **Class path:** `packetorder/PacketOrderE.java:13`
- **Validates:** HELD_ITEM_CHANGE forbidden during attack/interact/inventory/sneak/sprint/etc.
- **Behavior:** Setback if `packetOrderProcessor.isUsing()`.

### PacketOrderF
- **Class path:** `packetorder/PacketOrderF.java:15`
- **Validates:** Action packets while sprinting/sneaking.
- **Behavior:** Cancel (except PLAYER_DIGGING that would cause noslow).
- **Bypass:** Don't sprint/sneak when sending actions.

### PacketOrderG
- **Class path:** `packetorder/PacketOrderG.java:16`
- **Validates:** Drop/swap/open-inventory during attack/release/right-click/pick/dig.

### PacketOrderH
- **Class path:** `packetorder/PacketOrderH.java:13`
- **Validates:** Sprint/sneak transitions in version-specific order (1.21.2 split).

### PacketOrderI
- **Class path:** `packetorder/PacketOrderI.java:19`
- **Validates:** Input tick order — attack/interact/place/release sequence.

### PacketOrderJ
- **Class path:** `packetorder/PacketOrderJ.java:11`
- **Validates:** Place/USE_ITEM while attacking and not interacting.

### PacketOrderK
- **Class path:** `packetorder/PacketOrderK.java:14`
- **Validates:** Inventory open/close/click order.

### PacketOrderL
- **Class path:** `packetorder/PacketOrderL.java:16`
- **Validates:** Drop while opening inventory or swapping items.

### PacketOrderM
- **Class path:** `packetorder/PacketOrderM.java:14`
- **Validates:** USE_ITEM without preceding INTERACT_ENTITY (face=OTHER variant).

### PacketOrderN
- **Class path:** `packetorder/PacketOrderN.java:13`
- **Validates:** USE_ITEM without preceding PLAYER_BLOCK_PLACEMENT (face=OTHER variant).
- **Behavior:** Resync block.

### PacketOrderO
- **Class path:** `packetorder/PacketOrderO.java:13`
- **Validates:** Non-async packet after flying without CLIENT_TICK_END.

### PacketOrderP
- **Class path:** `packetorder/PacketOrderP.java:13`
- **Validates:** CHUNK_BATCH_ACK transaction ID match.

---

## Misc Checks

### ClientBrand
- **Class path:** `misc/ClientBrand.java:18`
- **Validates:** Client brand string length (>64 or =0); Forge blacklist on 1.18.2-1.19.3.
- **Behavior:** Disconnect if Forge blacklist enabled.

### GhostBlockMitigation
- **Class path:** `misc/GhostBlockMitigation.java:11`
- **Validates:** Place against ghost block (no solid neighbors).
- **Behavior:** Resync. Disabled by `allow-building-on-ghostblocks`.

### Post
- **Class path:** `misc/Post.java:26`
- **Validates:** Action packet after flying packet before transaction (PLAYER_ABILITIES, HELD_ITEM_CHANGE, INTERACT_ENTITY, BLOCK_PLACEMENT, ATTACK, USE_ITEM, PLAYER_DIGGING, CLICK_WINDOW, ANIMATION, ENTITY_ACTION).
- **Behavior:** Flag-only (queued).
- **Bypass:** Send actions before flying or in separate ticks.

### TransactionOrder
- **Class path:** `misc/TransactionOrder.java:7`
- **Validates:** Stub.

---

## Timer Checks (detailed)

### TickTimer
- **Class path:** `timer/TickTimer.java:12`
- **Validates:** Multiple flying packets before CLIENT_TICK_END.
- **Behavior:** Setback (VL 1).

### TimerLimit
- **Class path:** `timer/TimerLimit.java:9`
- **Validates:** Timer balance vs. real time, ping-limited (>1000ms default).
- **Behavior:** Setback (VL 10).

### VehicleTimer
- **Class path:** `timer/VehicleTimer.java:8`
- **Validates:** VEHICLE_MOVE/STEER_VEHICLE rate.
- **Behavior:** Setback (VL 10).

---

## Audit Implications — Updated

Adding these to the audit table from earlier:

| Module type | New checks to watch | Mitigation |
|---|---|---|
| AutoBlock / off-hand swap | PacketOrderD, BadPacketsA/F/G | Send INTERACT_AT → INTERACT, alternate slot IDs, no duplicate sprint/sneak |
| KillAura attack-while-using | MultiActionsA, PacketOrderF, PacketOrderJ, ChatC | Stop sprint/sneak on attack tick; release item first |
| Scaffold / FastPlace | BadPacketsU, GhostBlockMitigation, Post | USE_ITEM at (-1,-1,-1)/0/0; place against solid; no actions after flying |
| Timer modules | Timer, NegativeTimer, TickTimer, TimerLimit, VehicleTimer | One flying per CLIENT_TICK_END; balance < 120ms drift |
| Inventory / Blink | PacketOrderE, MultiActionsC, MultiActionsD | No HELD_ITEM_CHANGE while doing action; no inventory while sprinting |
| ElytraFly / Recast | ElytraA-I (9 checks) | Carry elytra, no levitation/water/vehicle/flight, jump first |
| Sprint (we already fixed) | SprintA-G | Module gates HUNGER/COLLISION; SprintD BLINDNESS toggle currently has no mixin — TODO |
| Crash modules | CrashA-I | Most are instant-ban or cancel; don't send malformed payloads |
