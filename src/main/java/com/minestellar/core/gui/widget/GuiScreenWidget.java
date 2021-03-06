package com.minestellar.core.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.minestellar.core.MinestellarCore;

import java.awt.*;
import java.util.ArrayList;

public abstract class GuiScreenWidget extends GuiScreen implements IGuiActionListener {

    public ArrayList<GuiWidget> widgets = new ArrayList<GuiWidget>();

    public int xSize, ySize, guiTop, guiLeft;

    public GuiScreenWidget() {
        this(176, 166);
    }

    public GuiScreenWidget(int xSize, int ySize) {
        super();
        this.xSize = xSize;
        this.ySize = ySize;
    }

    /**
     * Returns half the dimension
     */

    public int getMid(int dimension) {
        return dimension / 2;
    }

    @Override
    public void initGui() {
        guiTop = (height - ySize) / 2;
        guiLeft = (width - xSize) / 2;
        if (!widgets.isEmpty())
            resize();
    }

    public void reset() {
        widgets.clear();
        initGui();
        addWidgets();
        resize();
    }

    @Override
    public void setWorldAndResolution(Minecraft mc, int i, int j) {
        boolean init = this.mc == null;
        super.setWorldAndResolution(mc, i, j);
        if(init) {
            addWidgets();
            resize();
        }
    }

    public void add(GuiWidget widget) {
        widgets.add(widget);
        widget.onAdded(this);
    }

    @Override
    public void drawScreen(int mousex, int mousey, float f) {
        GL11.glTranslated(guiTop, guiLeft, 0);
        drawBackground();
        try{
            for (GuiWidget widget : widgets)
                widget.draw(mousex - guiTop, mousey - guiLeft, f);
        }catch(Exception e ){
            MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
            MinestellarCore.log.fatal(e.getCause());
        }
        drawForeground();
        GL11.glTranslated(-guiTop, -guiLeft, 0);
    }

    public abstract void drawBackground();

    public abstract void drawForeground();

    @Override
    protected void mouseClicked(int x, int y, int button) {
        super.mouseClicked(x, y, button);
        try{
            for (GuiWidget widget : widgets)
                widget.mouseClicked(x - guiTop, y - guiLeft, button);
        }catch(Exception e ){
            MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
            MinestellarCore.log.fatal(e.getCause());
        }
    }

    @Override
    protected void mouseMovedOrUp(int x, int y, int button) {
        super.mouseMovedOrUp(x, y, button);
        try{
            for (GuiWidget widget : widgets)
                widget.mouseMovedOrUp(x - guiTop, y - guiLeft, button);
        }catch(Exception e ){
            MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
            MinestellarCore.log.fatal(e.getCause());
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        super.mouseClickMove(x, y, button, time);
        try{
            for (GuiWidget widget : widgets)
                widget.mouseDragged(x - guiTop, y - guiLeft, button, time);
        }catch(Exception e ){
            MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
            MinestellarCore.log.fatal(e.getCause());
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (mc.currentScreen == this)
            try{
                for (GuiWidget widget : widgets)
                    widget.update();
            }catch(Exception e ){
                MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
                MinestellarCore.log.fatal(e.getCause());
            }
    }

    @Override
    public void keyTyped(char c, int keycode) {
        super.keyTyped(c, keycode);
        try{
            for (GuiWidget widget : widgets)
                widget.keyTyped(c, keycode);
        }catch(Exception e ){
            MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
            MinestellarCore.log.fatal(e.getCause());
        }
    }

    @Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        if (i != 0) {
            Point p = GuiDraw.getMousePosition();
            int scroll = i > 0 ? 1 : -1;
            try{
                for (GuiWidget widget : widgets)
                    widget.mouseScrolled(p.x, p.y, scroll);
            }catch(Exception e ){
                MinestellarCore.log.fatal("Crash caught! Report this to the mod author!");
                MinestellarCore.log.fatal(e.getCause());
            }
        }
    }

    @Override
    public abstract void actionPerformed(String ident, Object... params);

    public void resize() {
    }

    public abstract void addWidgets();
}
