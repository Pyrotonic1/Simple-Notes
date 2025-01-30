package org.pyrotonic.recordium.client.component;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.EditBox;
import net.minecraft.client.gui.widget.EditBoxWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.pyrotonic.recordium.client.RecordiumClient;

public class TransparantTextFieldWidget extends EditBoxWidget {
    private final TextRenderer textRenderer;
    private final EditBox editBox;

    public TransparantTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text placeholder, Text message) {
        super(textRenderer, x, y, width, height, placeholder, message);
        this.editBox = new EditBox(textRenderer, width - this.getPadding());
        this.textRenderer = MinecraftClient.getInstance().textRenderer;
    }

    @Override
    protected void renderOverlay(DrawContext context) {
        if (this.editBox.hasMaxLength()) {
            int i = this.editBox.getMaxLength();
            Text text = Text.translatable("gui.multiLineEditBox.character_limit", this.editBox.getText().length(), i);
            context.drawTextWithShadow(this.textRenderer, text, this.getX() + this.width - this.textRenderer.getWidth(text), this.getY() + this.height + 4, 10526880);
        }
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            Identifier identifier = RecordiumClient.EDIT_BOX_TEXTURES.get(this.isNarratable(), this.isFocused());
            context.drawGuiTexture(RenderLayer::getGuiTextured, identifier, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            context.enableScissor(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1);
            context.getMatrices().push();
            context.getMatrices().translate(0.0, -this.getScrollY(), 0.0);
            this.renderContents(context, mouseX, mouseY, delta);
            context.getMatrices().pop();
            context.disableScissor();
            this.renderOverlay(context);
        }
    }

    @Override
    protected void renderContents(DrawContext context, int mouseX, int mouseY, float delta) {
        super.renderContents(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            setText("");
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }


}
