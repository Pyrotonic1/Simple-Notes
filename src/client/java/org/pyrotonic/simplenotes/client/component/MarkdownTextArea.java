package org.pyrotonic.simplenotes.client.component;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.EditBoxWidget;
import net.minecraft.text.Text;

public class MarkdownTextArea  extends EditBoxWidget {

    public MarkdownTextArea(int width, int height, Text placeholder, Text message) {
        super(MinecraftClient.getInstance().textRenderer, 0, 0, width, height, placeholder, message);
    }
}
