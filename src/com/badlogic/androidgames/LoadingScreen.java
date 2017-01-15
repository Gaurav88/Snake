package com.badlogic.androidgames;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
public class LoadingScreen extends Screen {
public LoadingScreen(Game game) {
super(game);
}
@Override
public void update(float deltaTime) {
Graphics g = game.getGraphics();
Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
Assets.pause = g.newPixmap("pause.png", PixmapFormat.ARGB4444);
Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
Assets.headUp = g.newPixmap("headup.png", PixmapFormat.ARGB4444);
Assets.headLeft = g.newPixmap("headleft.png", PixmapFormat.ARGB4444);
Assets.headDown = g.newPixmap("headdown.png", PixmapFormat.ARGB4444);
Assets.headRight = g.newPixmap("headright.png", PixmapFormat.ARGB4444);
Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
Assets.stain1 = g.newPixmap("stain1.png", PixmapFormat.ARGB4444);
Assets.stain2 = g.newPixmap("stain2.png", PixmapFormat.ARGB4444);
Assets.stain3 = g.newPixmap("stain3.png", PixmapFormat.ARGB4444);
Assets.click = game.getAudio().newSound("click.ogg");
Assets.eat = game.getAudio().newSound("eat.ogg");
Assets.bitten = game.getAudio().newSound("bitten.ogg");
Assets.buttons1 = g.newPixmap("buttons1.png", PixmapFormat.ARGB4444);
Assets.line = g.newPixmap("line.png", PixmapFormat.ARGB4444);
Assets.sound = g.newPixmap("sound.png", PixmapFormat.ARGB4444);
Assets.mute = g.newPixmap("mute.png", PixmapFormat.ARGB4444);
Assets.buttonl = g.newPixmap("buttonl.png", PixmapFormat.ARGB4444);
Assets.buttonr = g.newPixmap("buttonr.png", PixmapFormat.ARGB4444);
Assets.cross = g.newPixmap("cross.png", PixmapFormat.ARGB4444);
Assets.black = g.newPixmap("black.png", PixmapFormat.ARGB4444);
Settings.load(game.getFileIO());
game.setScreen(new MainMenuScreen(game));
}

public void present(float deltaTime) {
}

public void pause() {
}

public void resume() {
}

public void dispose() {
}
}