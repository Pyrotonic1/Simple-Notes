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
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.pyrotonic.simplenotes.client.SimplenotesClient;
import org.pyrotonic.simplenotes.client.utils.IngameChecks;

public class DeleteNote extends BaseOwoScreen<FlowLayout> {

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        final Surface surface;
        if (!SimplenotesClient.IsIngame) {
            surface = (owoUIDrawContext, parentComponent) -> ROTATING_PANORAMA_RENDERER.render(owoUIDrawContext, this.width, this.height, 255, this.getPanoramaTickDelta());
        } else {surface = IngameChecks.getSurface();}
        NoteDataHandler Note = new NoteDataHandler(NoteDataHandler.readNote(NoteList.Filename), NoteList.Filename);
        LabelComponent Label = Components.label(Text.literal("Are you sure?"));
        ButtonComponent Yes = Components.button(Text.literal("Yes"), buttonComponent -> {
            assert client != null;
            
            if (Note.deleteFile(NoteList.Filename)) {
                client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Success"), Text.literal("Note \"" + NoteList.Filename.replace(".txt", "") + "\" Deleted"))
                );
            } else {
                client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Error"), Text.literal("Note \"" + NoteList.Filename.replace(".txt", "") + "\" Could not be deleted, check logs"))
                );
            }
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
                .surface(surface);
    }
    
}
