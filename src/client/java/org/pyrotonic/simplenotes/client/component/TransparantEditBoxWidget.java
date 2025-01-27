package org.pyrotonic.simplenotes.client.component;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.EditBoxWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.pyrotonic.simplenotes.client.SimplenotesClient;

public class TransparantEditBoxWidget extends EditBoxWidget {

    // variable is used for saving the text of the editbox when the window size changes

    public TransparantEditBoxWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text placeholder, Text message) {
        super(textRenderer, x, y, width, height, placeholder, message);
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            Identifier identifier = SimplenotesClient.EDIT_BOX_TEXTURES.get(this.isNarratable(), this.isFocused());
            context.drawGuiTexture(RenderLayer::getGuiTextured, identifier, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            context.enableScissor(this.getX() + 1, this.getY() + 1, this.getX() + this.width - 1, this.getY() + this.height - 1);
            context.getMatrices().push();
            context.getMatrices().translate(0.0, -this.getScrollY(), 0.0);
            this.renderContents(context, mouseX, mouseY, delta);
            context.getMatrices().pop();
            context.disableScissor();
            this.renderOverlay(context);
        }
        // super.renderWidget(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            setText("");
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
