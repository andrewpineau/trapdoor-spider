package com.andrewpineau.trapdoorspider.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    //texture atlases
    public static TextureAtlas backgrounds;
    public static TextureAtlas buttons;
    public static TextureAtlas objects;
    public static TextureAtlas text;

    //texture regions
    public static TextureRegion spiderStand, spiderJump, spiderDrop, spiderDead;
    public static TextureRegion platformOpen, platformClose;
    public static TextureRegion fireball1, fireball2, fireball3;
    public static TextureRegion splashBackground, homeBackground, gameBackground, 
    							highScoreBackground;
    public static TextureRegion playButton, jumpButton, dropButton, highScoreButton, 
    							homeButton, shareButton, leaderboardButton;
    public static TextureRegion gameOverText, newHighScoreText;

    public static Animation fireballAnim;

    public static BitmapFont smallFont, largeFont;

    public static Sound mainSound, jumpSound, openSound, gameOverSound;


    public static void load(){
        //texture atlases
        backgrounds = new TextureAtlas("data/backgrounds.txt");
        buttons = new TextureAtlas("data/buttons.txt");
        objects = new TextureAtlas("data/objects.txt");
        text = new TextureAtlas("data/text.txt");

        //getting images from sprite sheets using atlas
        splashBackground = backgrounds.findRegion("logo");
        splashBackground.flip(false, true);
        homeBackground = backgrounds.findRegion("menu");
        homeBackground.flip(false, true);
        gameBackground = backgrounds.findRegion("background");
        highScoreBackground = backgrounds.findRegion("highscorebackground");
        highScoreBackground.flip(false, true);

        playButton = buttons.findRegion("playbutton");
        playButton.flip(false, true);
        highScoreButton = buttons.findRegion("highscoresbutton");
        highScoreButton.flip(false, true);
        homeButton = buttons.findRegion("homebutton");
        homeButton.flip(false, true);
        jumpButton = buttons.findRegion("jumpbutton");
        jumpButton.flip(false, true);
        dropButton = buttons.findRegion("dropbutton");
        dropButton.flip(false, true);
        shareButton = buttons.findRegion("sharebutton");
        shareButton.flip(false, true);

        leaderboardButton = buttons.findRegion("leaderboardbutton");
        leaderboardButton.flip(false, true);

        spiderStand = objects.findRegion("spiderstand");
        spiderStand.flip(false, true);
        spiderJump = objects.findRegion("spiderjump");
        spiderJump.flip(false, true);
        spiderDrop = objects.findRegion("spiderfall");
        spiderDrop.flip(false, true);
        spiderDead = objects.findRegion("spiderdead");
        spiderDead.flip(false, true);

        platformOpen = objects.findRegion("trapdooropen");
        platformOpen.flip(false, true);
        platformClose = objects.findRegion("trapdoorclose");
        platformClose.flip(false, true);

        fireball1 = objects.findRegion("fireball1");
        fireball2 = objects.findRegion("fireball2");
        fireball3 = objects.findRegion("fireball3");

        TextureRegion [] fireballs = {fireball1, fireball2, fireball3};
        fireballAnim = new Animation(0.06f, fireballs); //each frame is 0.06 seconds long
        fireballAnim.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        gameOverText = text.findRegion("gameover");
        gameOverText.flip(false, true);
        newHighScoreText = text.findRegion("newhighscore");
        newHighScoreText.flip(false, true);

        smallFont = new BitmapFont(Gdx.files.internal("data/slkscr.fnt"), true);
        smallFont.setColor(new Color(1, 1, 1, 1));
        smallFont.setScale(1f, 1f);

        largeFont = new BitmapFont(Gdx.files.internal("data/slkscr.fnt"), true);
        largeFont.setColor(new Color(1, 1, 1, 1));
        largeFont.setScale(2f, 2f);

        mainSound = Gdx.audio.newSound(Gdx.files.internal("data/main.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("data/jump.mp3"));
        openSound = Gdx.audio.newSound(Gdx.files.internal("data/open.mp3"));
        gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/gameover.mp3"));

    }

    public static void dispose(){
        backgrounds.dispose();
        buttons.dispose();
        objects.dispose();
        text.dispose();

        smallFont.dispose();
        largeFont.dispose();

        mainSound.dispose();
        openSound.dispose();
        jumpSound.dispose();
        gameOverSound.dispose();

    }

}
