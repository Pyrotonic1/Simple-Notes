package org.pyrotonic.simplenotes.mixin;


import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.text.Text;
import org.pyrotonic.simplenotes.client.SimplenotesClient;
import org.pyrotonic.simplenotes.client.screen.MainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "addNormalWidgets")
    private void addCustomButton(int y, int spacingY, CallbackInfoReturnable<Integer> cir) {
        ButtonTextures ButtonIcon = SimplenotesClient.MAIN_MENU_BUTTON_TEXTURE;
        this.addDrawableChild(new TexturedButtonWidget((this.width / 2 - 122), y + spacingY, 20, 20, ButtonIcon, button -> {
            assert client != null;
            assert client.currentScreen != null;
            SimplenotesClient.IsIngame = false;
            client.setScreen(new MainMenu());
        }));
    }
}
