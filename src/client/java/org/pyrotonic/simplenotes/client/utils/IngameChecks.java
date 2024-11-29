package org.pyrotonic.simplenotes.client.utils;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.core.Component;
import io.wispforest.owo.ui.core.Insets;
import io.wispforest.owo.ui.core.Sizing;
import io.wispforest.owo.ui.core.Surface;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.pyrotonic.simplenotes.client.SimplenotesClient;
import org.pyrotonic.simplenotes.client.screen.MainMenu;

public class IngameChecks {

    public static Surface getSurface() {
        if (SimplenotesClient.IsIngame){
            return Surface.VANILLA_TRANSLUCENT;
        }
        else {
            return Surface.OPTIONS_BACKGROUND;
        }
    }

    public static Component getNoteListBackButton() {
        String string;
        Screen screen;
        if (SimplenotesClient.IsIngame) {
            string = "Exit";
            screen = null;
        }
        else {
            string = "Back";
            screen = new MainMenu();
        }
        return Components.button(Text.literal(string), buttonComponent -> {
            assert SimplenotesClient.client != null;
            SimplenotesClient.client.setScreen(screen);
        }).horizontalSizing(Sizing.fixed(50)).margins(Insets.of(5));
    }
}
