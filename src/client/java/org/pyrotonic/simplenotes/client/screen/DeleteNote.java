package org.pyrotonic.simplenotes.client.screen;

import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.client.NoteDataHandler;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.LabelComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.HorizontalAlignment;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.OwoUIAdapter;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.core.Surface;
import io.wispforest.owo.ui.core.VerticalAlignment;
import net.minecraft.text.Text;

public class DeleteNote extends BaseOwoScreen<FlowLayout> {

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        NoteDataHandler Note = new NoteDataHandler(NoteDataHandler.readNote(NoteList.Filename), NoteList.Filename);
        LabelComponent Label = Components.label(Text.literal("Are you sure?"));
        ButtonComponent Yes = Components.button(Text.literal("Yes"), buttonComponent -> {
            Note.deleteFile(NoteList.Filename);
            assert client != null;
            client.setScreen(new NoteList());
        });
        ButtonComponent No = Components.button(Text.literal("No"), buttonComponent -> {
            assert client != null;
            client.setScreen(new NoteList());
        });
        FlowLayout Buttons = Containers.horizontalFlow(Sizing.content(), Sizing.content())
                .child(Yes)
                .child(No)
                .gap(15);




        FlowLayout Container = (FlowLayout) Containers.verticalFlow(Sizing.content(), Sizing.content())
                .surface(Surface.DARK_PANEL)
                .padding(Insets.of(15));
        
        Yes.horizontalSizing(Sizing.fixed(26));
        No.horizontalSizing(Sizing.fixed(26));
        Buttons.margins(Insets.of(5));
        Container.child(Label);
        Container.child(Buttons);

        rootComponent
                .child(Container)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER)
                .surface(Surface.OPTIONS_BACKGROUND);
    }
    
}
