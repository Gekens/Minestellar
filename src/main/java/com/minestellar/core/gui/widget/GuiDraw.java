package com.minestellar.core.gui.widget;

import com.minestellar.core.util.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiDraw{
	public static class GuiHook extends Gui{
        public void setZLevel(float f) {
            zLevel = f;
        }

        public float getZLevel() {
            return zLevel;
        }

        public void incZLevel(float f) {
            zLevel += f;
        }

        @Override
        public void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6) {
            super.drawGradientRect(par1, par2, par3, par4, par5, par6);
        }
    }

    public static final GuiHook gui = new GuiHook();
    public static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    public static TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;

    public static void drawRect(int x, int y, int w, int h, int colour) {
        drawGradientRect(x, y, w, h, colour, colour);
    }

    public static void drawGradientRect(int x, int y, int w, int h, int colour1, int colour2) {
        gui.drawGradientRect(x, y, x + w, y + h, colour1, colour2);
    }

    public static void drawTexturedModalRect(int x, int y, int tx, int ty, int w, int h) {
        gui.drawTexturedModalRect(x, y, tx, ty, w, h);
    }

    public static void drawString(String text, int x, int y, int colour, boolean shadow) {
        if (shadow)
            fontRenderer.drawStringWithShadow(text, x, y, colour);
        else
            fontRenderer.drawString(text, x, y, colour);
    }

    public static void drawString(String text, int x, int y, int colour) {
        drawString(text, x, y, colour, true);
    }

    public static void drawStringC(String text, int x, int y, int w, int h, int colour, boolean shadow) {
        drawString(text, x + (w - getStringWidth(text)) / 2, y + (h - 8) / 2, colour, shadow);
    }

    public static void drawStringC(String text, int x, int y, int w, int h, int colour) {
        drawStringC(text, x, y, w, h, colour, true);
    }

    public static void drawStringC(String text, int x, int y, int colour, boolean shadow) {
        drawString(text, x - getStringWidth(text) / 2, y, colour, shadow);
    }

    public static void drawStringC(String text, int x, int y, int colour) {
        drawStringC(text, x, y, colour, true);
    }

    public static void drawStringR(String text, int x, int y, int colour, boolean shadow) {
        drawString(text, x - getStringWidth(text), y, colour, shadow);
    }

    public static void drawStringR(String text, int x, int y, int colour) {
        drawStringR(text, x, y, colour, true);
    }


    public static int getStringWidth(String s) {
        if (s == null || s.equals(""))
            return 0;
        return fontRenderer.getStringWidth(EnumChatFormatting.getTextWithoutFormattingCodes(s));
    }

    public static void drawCentered(String s, int x, int y, int colour) {
        fontRenderer.drawString(s, x - getStringWidth(s) / 2, y, colour);
    }
    
    public static Dimension displaySize() {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        return new Dimension(res.getScaledWidth(), res.getScaledHeight());
    }

    public static Dimension displayRes() {
        Minecraft mc = Minecraft.getMinecraft();
        return new Dimension(mc.displayWidth, mc.displayHeight);
    }

    public static Point getMousePosition(int eventX, int eventY) {
        Dimension size = displaySize();
        Dimension res = displayRes();
        return new Point(eventX * size.width / res.width, size.height - eventY * size.height / res.height - 1);
    }

    public static Point getMousePosition() {
        return getMousePosition(Mouse.getX(), Mouse.getY());
    }

    public static void drawTip(int x, int y, String text) {
        drawMultilineTip(x, y, Collections.singletonList(text));
    }

    /**
     * Append a string in the tooltip list with TOOLTIP_LINESPACE to have a small gap between it and the next line
     */
    public static final String TOOLTIP_LINESPACE = "\u00A7h";
    /**
     * Have a string in the tooltip list with TOOLTIP_HANDLER + getTipLineId(handler) for a custom handler
     */
    public static final String TOOLTIP_HANDLER = "\u00A7x";
    private static List<ITooltipLineHandler> tipLineHandlers = new ArrayList<>();

    public interface ITooltipLineHandler
    {
        Dimension getSize();

        void draw(int x, int y);
    }

    public static int getTipLineId(ITooltipLineHandler handler) {
        tipLineHandlers.add(handler);
        return tipLineHandlers.size()-1;
    }

    public static ITooltipLineHandler getTipLine(String line) {
        if(!line.startsWith(TOOLTIP_HANDLER))
            return null;
        return tipLineHandlers.get(Integer.parseInt(line.substring(2)));
    }

    public static void drawMultilineTip(int x, int y, List<String> list) {
        if (list.isEmpty())
            return;

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        RenderHelper.disableStandardItemLighting();

        int w = 0;
        int h = -2;
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            ITooltipLineHandler line = getTipLine(s);
            Dimension d = line != null ?
                    line.getSize() :
                    new Dimension(getStringWidth(s), list.get(i).endsWith(TOOLTIP_LINESPACE) && i + 1 < list.size() ? 12 : 10);
            w = Math.max(w, d.width);
            h += d.height;
        }

        if (x < 8) x = 8;
        else if (x > displaySize().width - w - 8) {
            x -= 24 + w;//flip side of cursor
            if(x < 8) x = 8;
        }
        y = (int) MathHelper.clip(y, 8, displaySize().height - 8 - h);

        gui.incZLevel(300);
        drawTooltipBox(x - 4, y - 4, w + 7, h + 7);
        for (String s : list) {
            ITooltipLineHandler line = getTipLine(s);
            if(line != null) {
                line.draw(x, y);
                y += line.getSize().height;
            }
            else {
                fontRenderer.drawStringWithShadow(s, x, y, -1);
                y += s.endsWith(TOOLTIP_LINESPACE) ? 12 : 10;
            }
        }

        tipLineHandlers.clear();
        gui.incZLevel(-300);

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.enableGUIStandardItemLighting();
    }

    public static void drawTooltipBox(int x, int y, int w, int h) {
        int bg = 0xf0100010;
        drawGradientRect(x + 1, y, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + h, w - 1, 1, bg, bg);
        drawGradientRect(x + 1, y + 1, w - 1, h - 1, bg, bg);//center
        drawGradientRect(x, y + 1, 1, h - 1, bg, bg);
        drawGradientRect(x + w, y + 1, 1, h - 1, bg, bg);
        int grad1 = 0x505000ff;
        int grad2 = 0x5028007F;
        drawGradientRect(x + 1, y + 2, 1, h - 3, grad1, grad2);
        drawGradientRect(x + w - 1, y + 2, 1, h - 3, grad1, grad2);

        drawGradientRect(x + 1, y + 1, w - 1, 1, grad1, grad1);
        drawGradientRect(x + 1, y + h - 1, w - 1, 1, grad2, grad2);
    }
    
    /**
     * Draws a texture of size bigger than 256x256
     */
    
    public static void drawNonStandartTexturedRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight){
        float f = 1F / (float) textureWidth;
        float f1 = 1F / (float) textureHeight;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double) (x), (double) (y + height), 0, (double) ((float) (u) * f), (double) ((float) (v + height) * f1));
        tessellator.addVertexWithUV((double) (x + width), (double) (y + height), 0, (double) ((float) (u + width) * f), (double) ((float) (v + height) * f1));
        tessellator.addVertexWithUV((double) (x + width), (double) (y), 0, (double) ((float) (u + width) * f), (double) ((float) (v) * f1));
        tessellator.addVertexWithUV((double) (x), (double) (y), 0, (double) ((float) (u) * f), (double) ((float) (v) * f1));
        tessellator.draw();
    }
    
    /**
     * Draws a texture for the whole screen, even <b>bigger than 256x256</b>
     */
    
    public static void drawFullscreenImage(int width, int height) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)height, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)width, (double)height, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)width, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
    }
    
    public static void drawLine(int x1, int y1, int x2, int y2, float thickness, int red, int green, int blue, int alpha){
    	GL11.glColor4f(red, green, blue, alpha);
    	GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(thickness);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

    	
    	GL11.glBegin(GL11.GL_LINES);
    	GL11.glVertex2i(x1, x1);
    	GL11.glVertex2i(x2, y2);
    	GL11.glEnd();
    	
    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    
    public static void drawLine(int x1, int y1, int x2, int y2, float thickness, int colour){
    	float f3 = (float)(colour >> 24 & 255) / 255.0F;
        float f = (float)(colour >> 16 & 255) / 255.0F;
        float f1 = (float)(colour >> 8 & 255) / 255.0F;
        float f2 = (float)(colour & 255) / 255.0F;
    	
    	GL11.glColor4f(f, f1, f2, f3);
    	GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(thickness);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

    	
    	GL11.glBegin(GL11.GL_LINES);
    	GL11.glVertex2i(x1, x1);
    	GL11.glVertex2i(x2, y2);
    	GL11.glEnd();

    	GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    
    /**
     * Draws a circle with the center in P(x,y)
     * 
     * @param x The x coordinate of the center
     * @param y The y coordinate of the center
     * @param radius The radius of the circle
     * @param colour The color of the circle
     * @param definition How often it draws a line. The higher it is the more smooth it looks. You'll see definition/2 lines
     * @param isFilled Whether or not this circle is filled
     */
    
    public static void drawCircle(int x, int y, int radius, int definition, int colour, boolean isFilled){
    	float f3 = (float)(colour >> 24 & 255) / 255.0F;
        float f = (float)(colour >> 16 & 255) / 255.0F;
        float f1 = (float)(colour >> 8 & 255) / 255.0F;
        float f2 = (float)(colour & 255) / 255.0F;
    	
    	GL11.glColor4f(f, f1, f2, f3);
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		Tessellator tes = Tessellator.instance;
		if(isFilled)
			tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		else
			tes.startDrawing(GL11.GL_LINES);
		
        double end = Math.PI * 2.0;
        double incr = end / definition;
        for (double theta = -incr; theta < end; theta += incr) {
        	tes.addVertex(x + (radius * Math.cos(-theta)), y + (radius * Math.sin(-theta)), 0.0);
        }
        tes.draw();
		
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    
    /**
     * Draws a ellipse with the center in P(x,y)
     * 
     * @param x The x coordinate of the center
     * @param y The y coordinate of the center
     * @param radiusX The radius on the "x" axis of the circle
     * @param radiusY The radius on the "y" axis of the circle
     * @param colour The color of the ellipse
     * @param definition How often it draws a line. The higher it is the more smooth it looks. You'll see definition/2 lines
     * @param isFilled Whether or not this ellipse is filled
     */
    
    public static void drawEllipse(int x, int y, int radiusX, int radiusY, int definition, int colour, boolean isFilled){
    	float f3 = (float)(colour >> 24 & 255) / 255.0F;
        float f = (float)(colour >> 16 & 255) / 255.0F;
        float f1 = (float)(colour >> 8 & 255) / 255.0F;
        float f2 = (float)(colour & 255) / 255.0F;
    	
    	GL11.glColor4f(f, f1, f2, f3);
		GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
		
        Tessellator tes = Tessellator.instance;
		if(isFilled)
			tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		else
			tes.startDrawing(GL11.GL_LINES);
		
        double end = Math.PI * 2.0;
        double incr = end / definition;
        for(double theta = -incr; theta < end; theta += incr){
        	tes.addVertex(x + (radiusX * Math.cos(-theta)), y + (radiusY * Math.sin(-theta)), 0.0);
        }
        tes.draw();
		
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    
    /**
	 * Fills the array used to describe an ellipse
	 * 
	 * @param a The a coefficient of the ellipse
	 * @param b The b coefficient of the ellipse
	 * @param array The array to be filled
	 */

	public static void fillEllipseCoordsArray(double a, double b, ArrayList<Point2D.Double> array){
		double y = 0.0D;
		for(double x = a; x >= -a; x -= 0.05){
			if(x == 0.0D){
				continue;
			}
			y = -Math.sqrt((b*b)*(-((x*x)/(a*a))+1));
			array.add(new Point2D.Double(x, y));
		}
		for(double x = -a; x <= a; x += 0.05){
			if(x == 0.0D){
				continue;
			}
			y = Math.sqrt((b*b)*(-((x*x)/(a*a))+1));
			array.add(new Point2D.Double(x, y));
		}
	}
    
}