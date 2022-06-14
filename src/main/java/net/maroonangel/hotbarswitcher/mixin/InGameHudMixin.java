package net.maroonangel.hotbarswitcher.mixin;

import net.maroonangel.hotbarswitcher.HBS;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    @Unique
    private static PlayerInventory inventory;

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(final CallbackInfo info) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            inventory = player.getInventory();
            if (inventory != null && HBS.switchKey.wasPressed() && !Screen.hasControlDown()) {
                if (Screen.hasShiftDown()) {
                    int selectedSlot = inventory.selectedSlot;
                    int top = selectedSlot + 9;
                    int mid = top + 9;
                    int bot = mid + 9;

                    MinecraftClient.getInstance().interactionManager.clickSlot(0, top, selectedSlot, SlotActionType.SWAP, player);
                    MinecraftClient.getInstance().interactionManager.clickSlot(0, mid, selectedSlot, SlotActionType.SWAP, player);
                    MinecraftClient.getInstance().interactionManager.clickSlot(0, bot, selectedSlot, SlotActionType.SWAP, player);
                } else {
                    int selectedSlot = inventory.selectedSlot;
                    int topSlot = selectedSlot + 27;

                    MinecraftClient.getInstance().interactionManager.clickSlot(0, topSlot, selectedSlot, SlotActionType.SWAP, player);
                }
            }
        }
    }
}
