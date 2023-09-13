package me.tryfle.chams;

import me.tryfle.chams.command.ToggleCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.EventBus;
import net.weavemc.loader.api.event.RenderLivingEvent;
import net.weavemc.loader.api.event.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Main implements ModInitializer {

    public static boolean enabled = false;

    @Override
    public void preInit() {
        System.out.println("[ChamsMod] Chams Mod has been loaded!");
        CommandBus.register(new ToggleCommand());
        EventBus.subscribe(RenderLivingEvent.Pre.class, this::onPreLivingRender);
        EventBus.subscribe(RenderLivingEvent.Post.class, this::onPostLivingRender);

    }

    @SubscribeEvent
    public void onPreLivingRender(RenderLivingEvent.Pre event) {
        if (event.getEntity() instanceof EntityPlayer && event.getEntity() != Minecraft.getMinecraft().thePlayer) {
            if (enabled) {
                GL11.glEnable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glPolygonOffset(1.0F, -1100000.0F);
            }
        }
    }

    @SubscribeEvent
    public void onPostLivingRender(RenderLivingEvent.Post event) {
        if (event.getEntity() instanceof EntityPlayer && event.getEntity() != Minecraft.getMinecraft().thePlayer) {
            if (enabled) {
                GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
                GL11.glPolygonOffset(1.0F, 1100000.0F);
            }
        }
    }
}
