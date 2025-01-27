package org.pyrotonic.simplenotes.client.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.pyrotonic.simplenotes.client.NoteDataHandler;
import org.pyrotonic.simplenotes.client.SimplenotesClient;
import org.pyrotonic.simplenotes.client.component.TextWidget;
import org.pyrotonic.simplenotes.client.component.TransparantButtonWidget;
import org.pyrotonic.simplenotes.client.component.TransparantTextFieldWidget;

import java.io.IOException;
import java.util.Objects;

public class RenameScreen extends Screen {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final TextRenderer textRenderer = client.textRenderer;

    private final String filename;

    private TextWidget textWidget;
    private TransparantTextFieldWidget textField;
    private TransparantButtonWidget saveButton;

    protected RenameScreen(String filename) {
        super(Text.translatable("screen.simplenotes.renamenote"));
        this.filename = filename;
    }

    private void initComponents() {
        Text text = Text.translatable(SimplenotesClient.KEY_RENAMENOTE_TEXTWIDGET);
        int textWidth = textRenderer.getWidth(text) / 2;
        textWidget = new TextWidget((width / 2) - textWidth, (height / 2) - 50, 1.0f, text.getString());

        textField = new TransparantTextFieldWidget(textRenderer, ((width / 2) - 80) - 33, (height / 2) + 1, 144, 18, Text.translatable(SimplenotesClient.KEY_TEXTFIELD_PLACEHOLDER), Text.empty());

        NoteDataHandler Note = new NoteDataHandler(NoteDataHandler.readNote(filename), filename);
        saveButton = new TransparantButtonWidget(((textField.getX() + textField.getWidth()) + 5), (this.height / 2), 75, 20, Text.translatable(SimplenotesClient.RENAME_BUTTON), button -> {
            try {
                if (Objects.equals(filename, textField.getText())) {
                    client.getToastManager().clear();
                    client.getToastManager().add(
                            SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.translatable(SimplenotesClient.TOAST_FAILURE), Text.translatable(SimplenotesClient.TOAST_RENAME_DUPLICATE, filename)));
                } else if (Note.saveFilename(filename, textField.getText())) {
                    client.getToastManager().clear();
                    client.getToastManager().add(
                            SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.translatable(SimplenotesClient.TOAST_SUCCESS), Text.translatable(SimplenotesClient.TOAST_RENAME_SUCCESS, filename, textField.getText())));
                    client.setScreen(new NoteEditorScreen(false, textField.getText()));
                } else if (!Note.saveFilename(filename, textField.getText())) {
                    client.getToastManager().clear();
                    client.getToastManager().add(
                            SystemToast.create(this.client, SystemToast.Type.NARRATOR_TOGGLE, Text.translatable(SimplenotesClient.TOAST_FAILURE), Text.translatable(SimplenotesClient.TOAST_RENAME_FAILURE, filename, textField.getText())));
                    client.setScreen(new NoteEditorScreen(false, textField.getText()));
                }
            } catch (IOException ignored) {}
        });
    }

    @Override
    protected void init() {
        initComponents();

        this.addDrawableChild(textWidget);
        this.addDrawableChild(textField);
        this.addDrawableChild(saveButton);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.textField.setText("");
            this.client.setScreen(new NoteEditorScreen(false, filename));
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
