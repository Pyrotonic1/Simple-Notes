package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.client.NoteDataHandler;


public class NoteList extends BaseOwoScreen<FlowLayout> {
    public static String Filename = "";

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }
    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent
            .surface(Surface.OPTIONS_BACKGROUND)
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER);

        FlowLayout ContainerLayout = (FlowLayout) Containers.verticalFlow(Sizing.content(), Sizing.content())
            .padding(Insets.of(15))
            .surface(Surface.DARK_PANEL)
            .verticalAlignment(VerticalAlignment.CENTER)
            .horizontalAlignment(HorizontalAlignment.CENTER);

        ContainerLayout.child(Components.label(Text.literal("Select a note:")));

        FlowLayout ButtonList = (FlowLayout) Containers.verticalFlow(Sizing.content(), Sizing.content()).horizontalAlignment(HorizontalAlignment.CENTER).margins(Insets.of(5));

        for (int i = 0; i < NoteDataHandler.readFilenames().length; i++) {
            int finalI = i;
            Component NoteButton = Components.button(Text.literal(NoteDataHandler.readFilenames()[i].replace(".txt", "")), buttonComponent -> {
            Filename = NoteDataHandler.readFilenames()[finalI];
                assert client != null;
                client.setScreen(new EditNote());
            }).margins(Insets.of((int) 2.5f)).sizing(Sizing.content(), Sizing.fixed(20));
            ButtonList.child(NoteButton);
        }

        ScrollContainer<FlowLayout> ScrollList = Containers.verticalScroll(Sizing.content(), Sizing.fixed(125), ButtonList);

        ContainerLayout.child(ScrollList);
        rootComponent.child(ContainerLayout);
    }
}