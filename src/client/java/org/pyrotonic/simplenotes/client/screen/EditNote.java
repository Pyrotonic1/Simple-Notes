package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.TextAreaComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import org.pyrotonic.simplenotes.client.NoteDataHandler;
import org.pyrotonic.simplenotes.client.SimplenotesClient;

public class EditNote extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    public static String decideLabelContent(String filename) {
        if (filename.isEmpty()) {
            return "Editing a file with no name";
        } else {
            return "Editing: " + '"' + filename + '"';
        }
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        NoteDataHandler Note = new NoteDataHandler(NoteDataHandler.readNote(NoteList.Filename), NoteList.Filename);
        TextAreaComponent TextArea = Components.textArea(Sizing.fixed(220), Sizing.fixed(200),  NoteDataHandler.readNote(Note.getFilename()));
        Component FilenameLabel = Components.label(Text.literal(decideLabelContent(Note.getFilename().replace(".txt", "")))).horizontalSizing(Sizing.fixed(130));
        Component SaveButton = Components.button(Text.literal("Save & Exit"), buttonComponent -> {
            NoteDataHandler.saveContent(TextArea.getText(), Note.getFilename());
            assert client != null;
            if (SimplenotesClient.IsIngame) {
                client.setScreen(null);
            } else {
                client.setScreen(new MainMenu());
            }
        });
        TextArea.margins(Insets.of(5));
        SaveButton.margins(Insets.of(6));
        FilenameLabel.margins(Insets.of(11));
        rootComponent
                .surface(Surface.OPTIONS_BACKGROUND)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        FlowLayout NameNSave = (FlowLayout) Containers.horizontalFlow(Sizing.content(), Sizing.content())
            .child(FilenameLabel)
            .child(SaveButton)
            .verticalAlignment(VerticalAlignment.TOP)
            .sizing(Sizing.content(), Sizing.fixed(40));
        Component EditBox =
            Containers.grid(Sizing.content(), Sizing.content(), 2, 2)
                .child(NameNSave, 0, 0)
                .child(TextArea, 1, 0)
                .padding(Insets.of(15))
                .surface(Surface.DARK_PANEL)
                .id("text-box");

        rootComponent.child(EditBox);
    }
}
