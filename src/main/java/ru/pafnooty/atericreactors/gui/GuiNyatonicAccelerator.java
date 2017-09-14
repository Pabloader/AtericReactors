package ru.pafnooty.atericreactors.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import ru.pafnooty.atericreactors.AtericReactors;
import ru.pafnooty.atericreactors.blocks.tileentity.TileNyatonicAccelerator;

@SideOnly(Side.CLIENT)
public class GuiNyatonicAccelerator extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(AtericReactors.MODID + ":textures/gui/nyatonicAccelerator.png");
    private final TileNyatonicAccelerator tileAccelerator;

    public GuiNyatonicAccelerator(InventoryPlayer inventoryPlayer, TileNyatonicAccelerator accelerator) {
        super(new ContainerNyatonicAccelerator(inventoryPlayer, accelerator));
        this.tileAccelerator = accelerator;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = I18n.format(this.tileAccelerator.getInventoryName(), new Object[0]);
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if (this.tileAccelerator.isFuelLoaded()) {
            this.drawTexturedModalRect(k + 56, l + 36, 176, 0, 14, 14);
        }
        int progressWidth = this.tileAccelerator.getProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, progressWidth + 1, 16);
    }
}
