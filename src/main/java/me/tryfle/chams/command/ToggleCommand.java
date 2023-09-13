package me.tryfle.chams.command;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;
import org.jetbrains.annotations.NotNull;

import static me.tryfle.chams.Main.enabled;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("togglechams");
    }

    @Override
    public void handle(@NotNull String[] args) {
        setEnabled(!enabled);
    }

    public static void setEnabled(boolean value) {
        enabled = value;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[ChamsMod] Chams have been" + (!enabled ? " disabled" : " enabled") + "!"));
    }
}