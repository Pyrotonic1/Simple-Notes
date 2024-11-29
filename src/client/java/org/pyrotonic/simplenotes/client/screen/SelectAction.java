package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.client.SimplenotesClient;
import org.pyrotonic.simplenotes.client.utils.IngameChecks;

public class SelectAction extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        LabelComponent Label = Components.label(Text.literal("Select an action:"));
        ButtonComponent RenameNote = Components.button(Text.literal("Rename Note"), buttonComponent -> {
            SimplenotesClient.IsCreated = false;
            assert client != null;
            client.setScreen(new RenameNote());
        });
        ButtonComponent OpenNote = Components.button(Text.literal("Open Note"), buttonComponent -> {
            assert client != null;
            client.setScreen(new EditNote());
        });
        ButtonComponent Back = Components.button(Text.literal("Back"), buttonComponent -> {
            assert client != null;
            client.setScreen(new NoteList());
        });
        ButtonComponent Delete = Components.button(Text.literal("Delete Note"), buttonComponent -> {
            assert client != null;
            client.setScreen(new DeleteNote());
        });

        RenameNote.horizontalSizing(Sizing.fixed(70));
        OpenNote.horizontalSizing(Sizing.fixed(70));
        Back.horizontalSizing(Sizing.fixed(70));
        Delete.horizontalSizing(Sizing.fixed(70));
        Label.margins(Insets.of(5));
        Back.margins(Insets.of(5));
        OpenNote.margins(Insets.of(5));
        RenameNote.margins(Insets.of(5));
        Delete.margins(Insets.of(5));

        FlowLayout Container = (FlowLayout) Containers.verticalFlow(Sizing.content(), Sizing.content())
                .padding(Insets.of(15))
                .surface(Surface.DARK_PANEL)
                .verticalAlignment(VerticalAlignment.CENTER)
                .horizontalAlignment(HorizontalAlignment.CENTER);
        Container.child(Label);
        Container.child(OpenNote);
        Container.child(RenameNote);
        Container.child(Delete);
        Container.child(Back);

        rootComponent
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER)
            .surface(IngameChecks.getSurface());

        rootComponent.child(Container);
    }
}
