package com.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class SusGui extends Screen {
    public SusGui() {
        super(Text.literal("Sus Finder Menu"));
    }

    @Override
    protected void init() {
        // This button toggles the scanner on and off
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Toggle Scanner"), button -> {
            ExampleMod.enabled = !ExampleMod.enabled;
            button.setMessage(Text.literal("Scanner: " + (ExampleMod.enabled ? "§aON" : "§cOFF")));
        }).dimensions(this.width / 2 - 100, this.height / 2 - 10, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}