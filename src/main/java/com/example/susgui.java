package com.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SusGui extends Screen {
    public SusGui() {
        super(Text.literal("Sus Finder Menu"));
    }

    @Override
    protected void init() {
        // This button toggles the hack on and off
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Toggle Sus Finder: " + (ExampleMod.enabled ? "§aON" : "§cOFF")), button -> {
            ExampleMod.enabled = !ExampleMod.enabled;
            button.setMessage(Text.literal("Toggle Sus Finder: " + (ExampleMod.enabled ? "§aON" : "§cOFF")));
        })
        .dimensions(this.width / 2 - 100, this.height / 2 - 10, 200, 20)
        .build());
    }
}