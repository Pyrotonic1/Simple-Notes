package org.pyrotonic.recordium.client.component;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.pyrotonic.recordium.client.RecordiumClient;

public class TransparantButtonWidget extends ButtonWidget {
    public TransparantButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION_SUPPLIER);
    }


    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        // context.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.getHeight(), -1862270976);
        Identifier identifier = RecordiumClient.TRANSPARANT_BUTTON_TEXTURES.get(this.active, this.isSelected());
        context.drawGuiTexture(RenderLayer::getGuiTextured, identifier, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        int i = this.active ? 16777215 : 10526880;
        this.drawMessage(context, minecraftClient.textRenderer, i | MathHelper.ceil(this.alpha * 255.0F) << 24);
    }

}
