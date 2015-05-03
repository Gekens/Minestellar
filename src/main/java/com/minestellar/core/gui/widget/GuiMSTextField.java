package com.minestellar.core.gui.widget;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;

public class GuiMSTextField extends GuiWidget{

	private String text;
    private boolean isFocused;
    private boolean isEnabled;
    
    public int maxStringLength;
    public int cursorCounter;
    public String actionCommand;
    
    private char[] allowedcharacters;

    public GuiMSTextField(int x, int y, int width, int height, String text){
        super(x, y, width, height);
        isFocused = false;
        isEnabled = true;
        this.text = text;
    }

    public GuiMSTextField setActionCommand(String s){
        actionCommand = s;
        return this;
    }

    public void setText(String s){
        if(s.equals(text))
            return;

        String oldText = text;
        text = s;
        onTextChanged(oldText);
    }

    public void onTextChanged(String oldText){
    }

    public final String getText(){
        return text;
    }
    
    public final boolean isEnabled(){
        return isEnabled;
    }

    public void setEnabled(boolean b){
        isEnabled = b;
        if(!isEnabled && isFocused)
            setFocused(false);
    }
    
    public final boolean isFocused(){
        return isFocused;
    }

    @Override
    public void update(){
        cursorCounter++;
    }

    @Override
    public void keyTyped(char c, int keycode){
        if(!isEnabled || !isFocused)
            return;

        /*if(c == '\t')//tab
        {
            parentGuiScreen.selectNextField();
        }*/
        if(c == '\026'){
            String s = GuiScreen.getClipboardString();
            if(s == null || s.equals(""))
                return;

            for(int i = 0; i < s.length(); i++){
                if(text.length() == maxStringLength)
                    return;

                char tc = s.charAt(i);
                if(canAddChar(tc))
                    setText(text + tc);
            }
        }
        if(keycode == Keyboard.KEY_RETURN){
            setFocused(false);
            sendAction(actionCommand, getText());
        }

        if(keycode == Keyboard.KEY_BACK && text.length() > 0)
            setText(text.substring(0, text.length() - 1));

        if((text.length() < maxStringLength || maxStringLength == 0) && canAddChar(c))
            setText(text + c);
    }

    public boolean canAddChar(char c){
        for(int i = 0; i<allowedcharacters.length; i++){
        	if(allowedcharacters[i] == c){
        		return true;
        	}else{
        		continue;
        	}
        }
        
        return false;
    }

    @Override
    public void mouseClicked(int x, int y, int button){
        if(isEnabled && pointInside(x, y)){
            setFocused(true);
            if(button == 1)
                setText("");
        }
        else
            setFocused(false);
    }

    public void setFocused(boolean focus){
        if(focus == isFocused)
            return;
        isFocused = focus;
        onFocusChanged();
    }

    public void onFocusChanged(){
        if(isFocused)
            cursorCounter = 0;
    }
    
    @Override
    public void draw(int i, int j, float f){
        drawBackground();
        drawText();
    }

    public void drawBackground(){
    	drawRect(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
        drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -16777216);
    }
    
    public String getDrawText(){
        String s = getText();
        if(isEnabled && isFocused && (cursorCounter / 6) % 2 == 0)
            s+="_";
        return s;
    }
    
    public void drawText(){
        drawString(fontRenderer, getDrawText(), x + 4, y + height/2 - 4, getTextColour());
    }

    public int getTextColour(){
        return isEnabled ? 0xe0e0e0 : 0x707070;
    }

    public GuiMSTextField setMaxStringLength(int i){
        maxStringLength = i;
        return this;
    }
    
    public GuiMSTextField setAllowedCharacters(char[] s){
        if(s == null)
            s = ChatAllowedCharacters.allowedCharacters;
        else
            allowedcharacters = s;
        return this;
    }
	
}