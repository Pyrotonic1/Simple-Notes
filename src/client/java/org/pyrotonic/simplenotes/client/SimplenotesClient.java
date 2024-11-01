package org.pyrotonic.simplenotes.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.pyrotonic.simplenotes.Simplenotes;
import org.pyrotonic.simplenotes.client.screen.NameNote;
import org.pyrotonic.simplenotes.client.screen.NoteList;
import org.pyrotonic.simplenotes.client.screen.TestScreen;

import java.io.File;

public class SimplenotesClient implements ClientModInitializer {
    public static boolean IsCreated;
    public static boolean IsIngame;
    public static final Boolean IS_DEV_ENVIRONMENT = FabricLoader.getInstance().isDevelopmentEnvironment();
    public static KeyBinding OpenMenuKeybind;
    public static KeyBinding QuickCreateKeybind;
    public static KeyBinding TestScreenKeybind;
    public static final String KEY_OPEN_TESTSCREEN = "key.simplenotes.opentest";
    public static final String KEY_CREATE_NOTE = "key.simplenotes.createnote";
    public static final String KEY_CATEGORY_SIMPLENOTES = "key.category.simplenotes.notes";
    public static final String KEY_OPEN_NOTE_SELECTOR = "key.simplenotes.noteselector";
    public static final String MAIN_DIRECTORY_PATH = "simplenotes/";
    public static final String NOTE_DIRECTORY_PATH = "simplenotes/notes/";
    public static final ButtonTextures MAIN_MENU_BUTTON_TEXTURE = new ButtonTextures(
        Identifier.of("simplenotes", "main_menu/unfocused"),
        Identifier.of("simplenotes", "main_menu/focused")
    );

    @Override
    public void onInitializeClient() {
        if (IS_DEV_ENVIRONMENT) {
            TestScreenKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                    KEY_OPEN_TESTSCREEN,
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_G,
                    KEY_CATEGORY_SIMPLENOTES
            ));`
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                while (TestScreenKeybind.wasPressed()) {
                    IsIngame = true;
                    client.setScreen(new TestScreen());
                }
            });
        }
        OpenMenuKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_NOTE_SELECTOR,
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
                IsCreated = true;
                client.setScreen(new NameNote());
            }
        });
        File NoteDirectory = new File(NOTE_DIRECTORY_PATH);
        if (NoteDirectory.mkdirs()) {
            Simplenotes.LOGGER.info("Directories created!");
        }
    }
}
