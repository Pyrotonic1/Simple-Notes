package org.pyrotonic.simplenotes.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.pyrotonic.simplenotes.client.screen.CreateNote;
import org.pyrotonic.simplenotes.client.screen.NoteList;

import java.io.File;

public class SimplenotesClient implements ClientModInitializer {
    public static boolean IsIngame;
    public static KeyBinding OpenMenuKeybind;
    public static KeyBinding QuickCreateKeybind;
    public static final String KEY_CREATE_NOTE = "key.simplenotes.createnote";
    public static final String KEY_CATEGORY_SIMPLENOTES = "key.category.simplenotes.notes";
    public static final String KEY_OPEN_MENU = "key.simplenotes.mainmenu";
    public static final String MAIN_DIRECTORY_PATH = "simplenotes/";
    public static final String NOTE_DIRECTORY_PATH = "simplenotes/notes/";
    public static final ButtonTextures MAIN_MENU_BUTTON_TEXTURE = new ButtonTextures(
        Identifier.of("simplenotes", "main_menu/unfocused"),
        Identifier.of("simplenotes", "main_menu/focused")
    );
    @Override
    public void onInitializeClient() {

        OpenMenuKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_MENU,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_SIMPLENOTES
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OpenMenuKeybind.wasPressed()) {
                IsIngame = true;
                client.setScreen(new NoteList());
            }});
        QuickCreateKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            KEY_CREATE_NOTE,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            KEY_CATEGORY_SIMPLENOTES
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (QuickCreateKeybind.wasPressed()) {
                IsIngame = true;
                client.setScreen(new CreateNote());
            }
        });
        File NoteDirectory = new File(NOTE_DIRECTORY_PATH);
        NoteDirectory.mkdirs();
    }
}
