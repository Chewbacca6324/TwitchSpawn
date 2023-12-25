//
// Created by BONNe
// Copyright - 2023
//


package net.programmer.igoodie.twitchspawn.mixin.forge;


import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.loading.ForgeLoadingOverlay;
import net.programmer.igoodie.twitchspawn.events.TwitchSpawnClientGuiEvent;


/**
 * This mixin is used to trigger the {@link TwitchSpawnClientGuiEvent#FINISH_LOADING_OVERLAY} event.
 */
@Mixin(value = ForgeLoadingOverlay.class, remap = false)
public class MixinForgeLoadingOverlay
{
    @Shadow
    @Final
    private Minecraft minecraft;


    @Inject(method = "render",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/Minecraft;setOverlay(Lnet/minecraft/client/gui/screens/Overlay;)V",
            shift = At.Shift.AFTER))
    private void overlayRemoveEvent(CallbackInfo callbackInfo)
    {
        TwitchSpawnClientGuiEvent.FINISH_LOADING_OVERLAY.invoker().removeOverlay(this.minecraft, this.minecraft.screen);
    }
}