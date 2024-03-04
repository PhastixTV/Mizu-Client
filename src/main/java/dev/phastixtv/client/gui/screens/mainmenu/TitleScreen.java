package dev.phastixtv.client.gui.screens.mainmenu;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.util.font.IFont;
import dev.phastixtv.client.util.render.color.ColorUtil;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class TitleScreen extends Screen {
    public TitleScreen() {
        super(Text.literal("I am a title screen!"));
    }
    public final String[] BUTTONS = {"Singleplayer", "Multiplayer", "Realms", "Options", "Login", "Quit"};
    public final ArrayList<GuiButton> buttonList = new ArrayList<GuiButton>();
    public Identifier skin = new Identifier("textures/entity/player/wide/steve.png");
    public void init() {
        buttonList.clear();
        int initHeight = this.height / 2;
        int objHeight = 50;
        int objWidth = 50;
        int xMid = this.width / 2;

        buttonList.add(new GuiButton(0, xMid - 150, initHeight, objWidth, objHeight, BUTTONS[0]));
        buttonList.add(new GuiButton(1, xMid - 90, initHeight, objWidth, objHeight, BUTTONS[1]));
        buttonList.add(new GuiButton(2, xMid - 30, initHeight, objWidth, objHeight, BUTTONS[2]));
        buttonList.add(new GuiButton(3, xMid + 30, initHeight, objWidth, objHeight, BUTTONS[3]));
        buttonList.add(new GuiButton(4, xMid + 90, initHeight, objWidth, objHeight, BUTTONS[4]));
        buttonList.add(new GuiButton(5, xMid + 150, initHeight, objWidth, objHeight, BUTTONS[5]));

        skin = MinecraftClient.getInstance().getSkinProvider().getSkinTextures(new GameProfile(this.client.getSession().getUuidOrNull(), "cskin")).texture();
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.drawTexture(Mizu.TILESCREEN_BACKGROUND, 0, 0, 20 * mouseX / this.width,  20 * mouseY / this.height, this.width + 20 * mouseX / this.width, this.height + 20 * mouseY / this.height, this.width + 40, this.height + 40);
        context.fillGradient(0, 0, this.width, this.height, 0x00000000, 0xff000000);


        String version = "v" + Mizu.version + " - MC " + SharedConstants.getGameVersion().getName();
        context.fill(0, 0, IFont.CONSOLAS.getStringWidth(version) + 4, IFont.CONSOLAS.getFontHeight() + 2, 0x90000000);
        IFont.CONSOLAS.drawString(context.getMatrices(), version, 1, 2, 0xFFFFFF, 1);

        RenderSystem.enableBlend();
        context.drawTexture(Mizu.TTILESCREEN_TITLE, this.width / 2 - 110, this.height / 2 - 160, 0, 0, 220, 220, 220, 220);
        RenderSystem.disableBlend();

        for(GuiButton b : buttonList) {
            b.drawButton(context, mouseX, mouseY);
        }



        int renderScale = 2 * this.height / 100;
        RenderSystem.setShaderTexture(0, skin);
        context.drawTexture(skin, 2, this.height - 8 * renderScale - 2, 8 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 64 * renderScale, 64 * renderScale);
        context.drawTexture(skin, 2, this.height - 8 * renderScale - 2, 8 * renderScale, 8 * renderScale, 40 * renderScale, 8 * renderScale, 8 * renderScale, 8 * renderScale, 64 * renderScale, 64 * renderScale);
        IFont.CONSOLAS.drawString(context.getMatrices(), this.client.getSession().getUsername(), 8 * renderScale + 3, this.height - IFont.CONSOLAS.getFontHeight() - 2, ColorUtil.getRainbow(4, 0.8f, 1), 1);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(int i = 0; i < 6; i++) {
            String b = BUTTONS[i];
            GuiButton guiButton = buttonList.get(i);
            float x = guiButton.x;
            float y = guiButton.y;



            if(mouseX >= x - guiButton.width / 2 && mouseY >= y && mouseX <= x + guiButton.width / 2 && mouseY <= y + 60) {
                this.client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                switch(b) {
                    case "Singleplayer":
                        this.client.setScreen(new SelectWorldScreen(this));
                        break;
                    case "Multiplayer":
                        Screen screen = this.client.options.skipMultiplayerWarning ? new MultiplayerScreen(this) : new MultiplayerWarningScreen(this);
                        this.client.setScreen(screen);
                        break;
                    case "Realms":
                        this.client.setScreen(new RealmsMainScreen(this));
                        break;
                    case "Options":
                        this.client.setScreen(new OptionsScreen(this, this.client.options));
                        break;
                    case "Login":
                        this.client.setScreen(new LoginScreen(this));
                        break;
                    case "Quit":
                        this.client.scheduleStop();
                        break;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
