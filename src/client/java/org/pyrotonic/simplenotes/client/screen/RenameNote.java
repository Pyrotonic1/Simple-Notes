package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.TextBoxComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.Simplenotes;
import org.pyrotonic.simplenotes.client.NoteDataHandler;

import java.io.IOException;


public class RenameNote extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }
    @Override
    protected void build(FlowLayout rootComponent) {
        NoteDataHandler Note = new NoteDataHandler(NoteDataHandler.readNote(NoteList.Filename), NoteList.Filename);
        TextBoxComponent TextBox = Components.textBox(Sizing.fixed(116), NoteList.Filename.replace(".txt", ""));
        Component NameBox = Components.button(Text.literal("Rename!"), buttonComponent -> {
            try {
                Note.saveFilename(NoteList.Filename, TextBox.getText());
            } catch (IOException e) {
                Simplenotes.LOGGER.error("An IOException error occurred while renaming the file.");
            }
            assert client != null;
            client.setScreen(new NoteList());
            client.getToastManager().add(
                SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes"), Text.literal("Note \"" + NoteList.Filename.replace(".txt", "") + "\" renamed to \"" + TextBox.getText() + "\""))
            );
            });

        TextBox.margins(Insets.of(5));
        NameBox.margins(Insets.of(5));

        ParentComponent NameDialog = Containers.horizontalFlow(Sizing.content(), Sizing.content())
                .child(TextBox)
                .child(NameBox)
                .surface(Surface.DARK_PANEL);

        rootComponent
            .child(NameDialog)
            .surface(Surface.OPTIONS_BACKGROUND)
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER);
    }
}
