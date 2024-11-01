package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.HorizontalAlignment;
import io.wispforest.owo.ui.core.OwoUIAdapter;
import io.wispforest.owo.ui.core.VerticalAlignment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.client.component.MarkdownTextArea;

public class TestScreen extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent.horizontalAlignment(HorizontalAlignment.CENTER);
        rootComponent.verticalAlignment(VerticalAlignment.CENTER);

        rootComponent.child(new MarkdownTextArea(100, 100, Text.literal("Test"), Text.empty()));
    }
}
