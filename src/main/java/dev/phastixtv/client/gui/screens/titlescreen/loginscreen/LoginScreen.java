package dev.phastixtv.client.gui.screens.titlescreen.loginscreen;

import dev.phastixtv.client.Mizu;
import dev.phastixtv.client.util.login.AuthenticatorManager;
import dev.phastixtv.mixin.MinecraftClientAccessor;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.EditBox;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.session.Session;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Optional;
import java.util.UUID;


public class LoginScreen extends Screen {
    @Getter
    private TextFieldWidget emailField;
    @Getter
    private TextFieldWidget passwordField;
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final Screen last;

    public LoginScreen(Screen last) {
        super(Text.literal("RegisterScreen"));
        this.last = last;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void init() {
            emailField = new TextFieldWidget(this.textRenderer, this.width / 2- 100, 66, 200, 20, Text.literal("E-Mail Adresse"));
            emailField.setFocused(true);
            addDrawable(emailField);
            emailField.setMaxLength(100);
            emailField.setFocused(true);
            addSelectableChild(emailField);

            // Password Field
            passwordField = new TextFieldWidget(this.textRenderer, this.width / 2- 100, 106, 200, 20, Text.literal("Passwort"));
            passwordField.setFocused(true);
            addDrawable(passwordField);
            passwordField.setRenderTextProvider((text, int_1) -> {
                String stars = "";
                for(int i = 0; i < text.length(); i++)
                    stars += "*";
                return OrderedText.styledForwardsVisitedString(stars, Style.EMPTY);
            });

            passwordField.setMaxLength(258);
            addSelectableChild(passwordField);
            setFocused(emailField);

            // Login Button
            this.addDrawableChild(ButtonWidget.builder(Text.literal("Login"), (button) -> {
                login();
            }).dimensions(this.width / 2 - 100, this.height / 4 + 96 + 18, 200, 20).build());

            // Cancel Button
            this.addDrawableChild(ButtonWidget.builder(Text.literal("Abbrechen"), (button) -> {
                client.setScreen(last);
            }).dimensions(this.width / 2 - 100, this.height / 4 + 120 + 18, 200, 20).build());
    }

    public void login() {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        MicrosoftAuthResult result = null;
        try {
            result = authenticator.loginWithCredentials(emailField.getText(), passwordField.getText());
            Mizu.minecraftClient.setScreen(last);
        } catch (Exception e) {
            passwordField.setText("");
            return;
        }
        assert result !=null;

        String uuidString = result.getProfile().getId();
        long msb = Long.parseUnsignedLong(uuidString, 0, 16, 16);
        long lsb = Long.parseUnsignedLong(uuidString, 16, 32, 16);
        UUID uuid = new UUID(msb, lsb);

        Session session = new Session(
                result.getProfile().getName(),
                uuid,
                result.getAccessToken(),
                Optional.empty(),
                Optional.empty(),
                Session.AccountType.LEGACY
        );
        ((MinecraftClientAccessor) Mizu.minecraftClient).setSession(session);

    }

    @Override
    public void close() {
        client.setScreen(last);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, title, width / 2, 17, 16777215);
        context.drawText(this.textRenderer, Text.literal("E-Mail Adresse:"), this.width / 2 - 100, 53, 10526880,true);
        context.drawText(this.textRenderer, Text.literal("Passwort:"), this.width / 2 - 100, 94, 10526880,true);
        this.emailField.render(context, mouseX, mouseY, delta);
        this.passwordField.render(context, mouseX, mouseY, delta);
    }


    @Override
    public void resize(MinecraftClient client, int width, int height) {
        String s1 = emailField.getText();
        String s2 = passwordField.getText();
        init();
        emailField.setText(s1);
        passwordField.setText(s2);
    }
}