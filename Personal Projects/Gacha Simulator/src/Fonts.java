package src;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class Fonts {

    static Font baseFont = new Font("Arial", Font.PLAIN, 12);

    static Font mainTitleFont = createFont("MainTitle.ttf", 100f);
    static Font mainSubFont = createFont("MainTitle.ttf", 48f);
    
    static Font minecraftTitleFont = createFont("MinecraftTitle.ttf", 128);
    static Font minecraftSubFont = createFont("MinecraftRegular.ttf", 48);
    
    static Font genshinTitleFont = createFont("GenshinTitle.ttf", 120);
    static Font genshinSubFont = createFont("GenshinRegular.ttf", 36);
        
        
    
    protected static Font createFont(String fontName, float fontSize) {

        try {
            baseFont = Font.createFont(Font.TRUETYPE_FONT, new File("Documents/" + fontName))
                    .deriveFont(fontSize);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        return baseFont;
    }
    
        
    
}
