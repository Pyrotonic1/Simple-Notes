package org.pyrotonic.recordium.client.component;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;

import static org.pyrotonic.recordium.client.RecordiumClient.client;

public class TextWidget implements Drawable, Element, Narratable, Selectable {
    private static final TextRenderer textRenderer = client.textRenderer;
    private boolean focused;
    private final float scale;
    private final String text;
    private final int x;
    private final int y;

    public TextWidget(int x, int y, float scale, String text) {
        this.text = text;
        this.scale = scale;
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        MatrixStack matrices = context.getMatrices();
        matrices.push();
        matrices.scale(this.scale, this.scale, this.scale);
        context.drawText(textRenderer, this.text, this.x, this.y, Colors.WHITE, true);
        matrices.pop();
    }

    @Override
    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    @Override
    public boolean isFocused() {
        return this.focused;
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {}

    @Override
    public SelectionType getType() {
        return SelectionType.NONE;
    }
}
