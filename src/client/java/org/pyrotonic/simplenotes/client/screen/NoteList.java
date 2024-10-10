package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
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
            .surface(Surface.VANILLA_TRANSLUCENT)
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER);

        FlowLayout ContainerLayout = (FlowLayout) Containers.verticalFlow(Sizing.content(), Sizing.content())
            .padding(Insets.of(15))
            .surface(Surface.DARK_PANEL)
            .verticalAlignment(VerticalAlignment.CENTER)
            .horizontalAlignment(HorizontalAlignment.CENTER);

        ContainerLayout.child(Components.label(Text.literal("Select a note:")));

        for (int i = 0; i < NoteDataHandler.readFilenames().length; i++) {
            int finalI = i;
            Component NoteButton = Components.button(Text.literal(NoteDataHandler.readFilenames()[i].replace(".txt", "")), buttonComponent -> {
            Filename = NoteDataHandler.readFilenames()[finalI];
            client.setScreen(new EditNote());
            }).margins(Insets.of(5)).sizing(Sizing.content(), Sizing.fixed(20));
            ContainerLayout.child(NoteButton);
            System.out.println(NoteDataHandler.readFilenames()[i]);
        }

        rootComponent.child(ContainerLayout);
    }
}