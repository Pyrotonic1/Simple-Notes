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
import org.pyrotonic.simplenotes.client.NoteDataHandler;
import org.pyrotonic.simplenotes.client.utils.IngameChecks;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class NameNote extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    static boolean DoesFilenameExist = false;

    @Override
    protected void build(FlowLayout rootComponent) {
        final String NameBoxPlaceholder = "Enter Filename Here!";
        String[] Filenames = NoteDataHandler.readFilenames();
        TextBoxComponent TextBox = Components.textBox(Sizing.fixed(116), "Enter Filename Here!");
        Component NameBox = Components.button(Text.literal("Create!"), buttonComponent -> {
            if (Objects.equals(TextBox.getText(), NameBoxPlaceholder)) {
                assert client != null;
                client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Error"), Text.literal("Give your note a name!"))
                );
            } else {
                for (String filename : Filenames) {
                    if (filename.replace(".txt", "").equals(TextBox.getText())) {
                        DoesFilenameExist = true;
                        break;
                    }
                    DoesFilenameExist = false;
                }
                if (!DoesFilenameExist) {
                    CreateNote.Filename = TextBox.getText();
                    assert client != null;
                    client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Success"), Text.literal("Note Created!"))
                    );
                    assert client != null;
                    client.setScreen(new CreateNote());
                } else {
                    assert client != null;
                    client.getToastManager().add(
                        SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Error"), Text.literal("Note not created; a note already has that name."))
                    );
                }
            }
            });

        TextBox.margins(Insets.of(5));
        NameBox.margins(Insets.of(5));

        ParentComponent NameDialog = Containers.horizontalFlow(Sizing.content(), Sizing.content())
                .child(TextBox)
                .child(NameBox)
                .surface(Surface.DARK_PANEL);

        rootComponent
            .child(NameDialog)
            .surface(IngameChecks.getSurface())
            .horizontalAlignment(HorizontalAlignment.CENTER)
            .verticalAlignment(VerticalAlignment.CENTER);

        TimerTask FocusCheck = new TimerTask() {
            @Override
            public void run() {
                if (TextBox.isFocused() & Objects.equals(TextBox.getText(), NameBoxPlaceholder)) {
                    TextBox.setText("");
                }

            }
        };
        TimerTask UnfocusCheck = new TimerTask() {
            @Override
            public void run() {
                if (!TextBox.isFocused() & Objects.equals(TextBox.getText(), "")) {
                    TextBox.setText(NameBoxPlaceholder);
                }
            }
        };
        Timer Timer = new Timer();
            Timer.schedule(FocusCheck, 0, 250);
            Timer.schedule(UnfocusCheck, 0, 250);
    }
}
