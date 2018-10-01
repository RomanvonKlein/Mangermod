package com.romanvonklein.manger.gui;

import com.romanvonklein.manger.MangerMod;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class MangerGui extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;
    public static final int GUI_ID = 1;

    private static final ResourceLocation background = new ResourceLocation(MangerMod.MODID, "textures/gui/mangergui.png");

    public MangerGui(Container container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}