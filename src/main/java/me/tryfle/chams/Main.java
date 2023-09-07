package me.tryfle.chams;

import me.tryfle.chams.command.ToggleCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.RenderLivingEvent;
import net.weavemc.loader.api.event.SubscribeEvent;
import net.weavemc.loader.api.event.EventBus;
import org.lwjgl.opengl.GL11;
public class Main implements ModInitializer {

    public static boolean enabled = false;

    @Override
    public void preInit() {
        System.out.println("[ChamsMod] Chams Mod has been loaded!");
        CommandBus.register(new ToggleCommand());
        EventBus.subscribe(RenderLivingEvent.Pre.class, this::onPreLivingRender);
        EventBus.subscribe(RenderLivingEvent.Post .class, this::onPostLivingRender);

    }

    public static void setEnabled(boolean value) {
        enabled = value;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "[ChamsMod] Chams have been" + (!enabled ? " disabled" : " enabled") + "!"));

    }

    @SubscribeEvent
    public void onPreLivingRender(RenderLivingEvent.Pre event) {
        if (event.getEntity() != Minecraft.getMinecraft().thePlayer) {
            if (enabled) {
                GL11.glEnable(32823);
                GL11.glPolygonOffset(1.0F, -1100000.0F);
            } else {
                GL11.glDisable(32823);
                GL11.glPolygonOffset(0.0F, 0.0F);
            }
        }
    }

    @SubscribeEvent
    public void onPostLivingRender(RenderLivingEvent.Post event) {
        if (event.getEntity() != Minecraft.getMinecraft().thePlayer) {
            if (enabled) {
                GL11.glEnable(32823);
                GL11.glPolygonOffset(1.0F, 1100000.0F);
            } else {
                GL11.glDisable(32823);
                GL11.glPolygonOffset(0.0F, 0.0F);
            }
        }
    }
}