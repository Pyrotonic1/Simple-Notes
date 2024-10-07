package org.pyrotonic.simplenotes.client.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.TextAreaComponent;
import io.wispforest.owo.ui.component.TextBoxComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.pyrotonic.simplenotes.Simplenotes;
import org.pyrotonic.simplenotes.client.NoteDataHandler;

public class EditNote extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }
    public static NoteDataHandler CreateNote() {
        NoteDataHandler note;
        if (MainMenu.isCreateNote) {
            note = new NoteDataHandler("", "");
            NoteDataHandler.emptyFields();
        } else {
            note = new NoteDataHandler(NoteDataHandler.readNote(NoteList.Filename), NoteList.Filename);
        }
        return note;
    }
    @Override
    protected void build(FlowLayout rootComponent) {

        TextAreaComponent TextArea = Components.textArea(Sizing.fixed(200), Sizing.fixed(200), CreateNote().getContent());
        TextBoxComponent FilenameBox = Components.textBox(Sizing.fixed(116), CreateNote().getFilename());
        Component SaveButton = Components.button(Text.literal("Save & Exit"), buttonComponent -> {
            NoteDataHandler.saveContent(TextArea.getText(), FilenameBox.getText());
            client.setScreen(new MainMenu());
            Simplenotes.LOGGER.info("Saved Successfully!");
        });
        TextArea.margins(Insets.of(5));
        SaveButton.margins(Insets.of(6));
        FilenameBox.margins(Insets.of(5));
        rootComponent
                .surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        FlowLayout NameNSave = (FlowLayout) Containers.horizontalFlow(Sizing.content(), Sizing.content())
            .child(FilenameBox)
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
