package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.client.SimplenotesClient;

public class MainMenu extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }
    @Override
    protected void build(FlowLayout rootComponent) {
        Component MenuTitle = Components.texture(Identifier.of("simplenotes", "menutitle.png"), 460, 90, 460, 90, 460, 90)
                        .id("menu-title")
                        .positioning(Positioning.relative(50, 25));

        FlowLayout MenuButtons = (FlowLayout) Containers.horizontalFlow(Sizing.content(), Sizing.content())
            .gap(20)
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER);

        Component CreateNoteButton = Components.button(Text.literal("Create Note"), buttonComponent -> {
                assert client != null;
                client.setScreen(new NameNote());
            })
                .id("create-button")
                .horizontalSizing(Sizing.fixed(78));
        Component OpenNoteButton = Components.button(Text.literal("Note Selector"), buttonComponent -> {
                SimplenotesClient.IsCreated = true;
                assert client != null;
                client.setScreen(new NoteList());
            })
                .id("open-button")
                .horizontalSizing(Sizing.fixed(78));
        Component ExitButton = Components.button(Text.literal("Exit"), buttonComponent -> {
                assert client != null;
                client.setScreen(null);})
                .id("exit-button")
                .horizontalSizing(Sizing.fixed(78));


        MenuButtons
            .child(CreateNoteButton)
            .child(OpenNoteButton)
            .child(ExitButton)
            .positioning(Positioning.relative(50, 75));

        rootComponent
                .child(MenuTitle)
                .child(MenuButtons)
                .surface(Surface.OPTIONS_BACKGROUND)
                .verticalAlignment(VerticalAlignment.CENTER)
                .horizontalAlignment(HorizontalAlignment.CENTER);

    }
}
