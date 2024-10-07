package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class MainMenu extends BaseOwoScreen<FlowLayout> {
    public static Boolean isCreateNote = false;
    public static Boolean isOpenNote = false;
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        Component MenuTitle = Components.texture(Identifier.of("simplenotes:textures/gui/menutitle.png"), 0, 0, 330, 60, 330, 60)
                        .id("menu-title")
                        .positioning(Positioning.relative(50, 25));
        Component CreateNoteButton = Components.button(Text.literal("Create Note"), buttonComponent -> {
            isCreateNote = true;
            isOpenNote = false;
            client.setScreen(new EditNote());
            })
                .id("create-button")
                .positioning(Positioning.relative(35, 75));
        Component OpenNoteButton = Components.button(Text.literal("Open Note"), buttonComponent -> {
            isOpenNote = true;
            isCreateNote = false;
            client.setScreen(new NoteList());
            })
                .id("open-button")
                .positioning(Positioning.relative(50, 75));
        Component ExitButton = Components.button(Text.literal("Exit"), buttonComponent -> {
                    client.setScreen(null);})
                .id("exit-button")
                .horizontalSizing(Sizing.fixed(58))
                .positioning(Positioning.relative(65, 75));

        rootComponent
                .child(MenuTitle)
                .child(CreateNoteButton)
                .child(OpenNoteButton)
                .child(ExitButton)
                .surface(Surface.VANILLA_TRANSLUCENT)
                .verticalAlignment(VerticalAlignment.CENTER)
                .horizontalAlignment(HorizontalAlignment.CENTER);

    }
}
