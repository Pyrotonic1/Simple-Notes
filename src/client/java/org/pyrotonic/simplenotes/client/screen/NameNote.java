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

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class NameNote extends BaseOwoScreen<FlowLayout> {
    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }
    @Override
    protected void build(FlowLayout rootComponent) {
        final String NameBoxPlaceholder = "Enter Filename Here!";
        TextBoxComponent TextBox = Components.textBox(Sizing.fixed(116), "Enter Filename Here!");
        Component NameBox = Components.button(Text.literal("Create!"), buttonComponent -> {
            if (TextBox.getText() == NameBoxPlaceholder) {
                client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Error"), Text.literal("Give your note a name!"))
                );
            } else {
                CreateNote.Filename = TextBox.getText();
                client.getToastManager().add(
                    SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.literal("Simple Notes - Success"), Text.literal("Note Created!"))
                );
                assert client != null;
                client.setScreen(new CreateNote());
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
            .surface(Surface.OPTIONS_BACKGROUND)
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
