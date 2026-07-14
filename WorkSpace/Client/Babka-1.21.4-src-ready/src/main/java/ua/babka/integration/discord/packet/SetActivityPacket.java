package ua.babka.integration.discord.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ua.babka.integration.discord.ActivityInfo;
import ua.babka.integration.discord.packet.C2SPacket;
import ua.babka.integration.discord.packet.Opcode;

public class SetActivityPacket
implements C2SPacket {
    private final int pid;
    private final ActivityInfo activityInfo;

    public SetActivityPacket(int pid, ActivityInfo activityInfo) {
        this.pid = pid;
        this.activityInfo = activityInfo;
    }

    @Override
    public Opcode getOpcode() {
        return Opcode.Frame;
    }

    @Override
    public JsonObject toJson() {
        JsonObject argsObject = new JsonObject();
        argsObject.addProperty("pid", (Number)this.pid);
        argsObject.add("activity", (JsonElement)this.activityInfo.toJson());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("cmd", "SET_ACTIVITY");
        jsonObject.add("args", (JsonElement)argsObject);
        return jsonObject;
    }
}

