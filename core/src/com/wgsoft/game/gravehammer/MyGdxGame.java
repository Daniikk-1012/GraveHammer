package com.wgsoft.game.gravehammer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ParticleEffectActor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.wgsoft.game.gravehammer.screens.GameScreen;

public class MyGdxGame extends Game {
    public static final float WIDTH = 1920f;
    public static final float HEIGHT = 1080f;

    private static MyGdxGame instance;

    public static MyGdxGame getInstance() {
        if(instance == null) {
            instance = new MyGdxGame();
        }
        return instance;
    }

    private SpriteBatch spriteBatch;
    
    private Skin skin;

    private Music waveMusic;
    private Music barMusic;
    private Music stepMusic;

    private Sound hitSound;
    private Sound sellSound;
    private Sound throwSound;
    private Sound brewSound;
    private Sound deathSound;
    
    private ParticleEffectActor hitParticleEffectActor;
    
    private MyGdxGame() {
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        skin = new Skin(Gdx.files.internal("img/skin.skin"));
        skin.getTiledDrawable("ground").setScale(8f);
        skin.get("cash", CheckBox.CheckBoxStyle.class)
            .checkboxOn.setMinWidth(64f);
        skin.get("cash", CheckBox.CheckBoxStyle.class)
            .checkboxOn.setMinHeight(64f);
        skin.get("cash", CheckBox.CheckBoxStyle.class)
            .checkboxOff.setMinWidth(64f);
        skin.get("cash", CheckBox.CheckBoxStyle.class)
            .checkboxOff.setMinHeight(64f);
        skin.get("potion", CheckBox.CheckBoxStyle.class)
            .checkboxOn.setMinWidth(64f);
        skin.get("potion", CheckBox.CheckBoxStyle.class)
            .checkboxOn.setMinHeight(64f);
        skin.get("potion", CheckBox.CheckBoxStyle.class)
            .checkboxOff.setMinWidth(64f);
        skin.get("potion", CheckBox.CheckBoxStyle.class)
            .checkboxOff.setMinHeight(64f);

        waveMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/wave.mp3"));
        waveMusic.setLooping(true);
        barMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/bar.mp3"));
        barMusic.setLooping(true);
        stepMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/step.mp3"));
        stepMusic.setLooping(true);

        hitSound = Gdx.audio.newSound(Gdx.files.internal("snd/hit.mp3"));
        sellSound = Gdx.audio.newSound(Gdx.files.internal("snd/sell.mp3"));
        throwSound = Gdx.audio.newSound(Gdx.files.internal("snd/throw.mp3"));
        brewSound = Gdx.audio.newSound(Gdx.files.internal("snd/brew.mp3"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("snd/death.mp3"));

        hitParticleEffectActor = new ParticleEffectActor(
                Gdx.files.internal("particle/hit"), skin.getAtlas());

        setScreen(GameScreen.getInstance());
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();

        GameScreen.getInstance().dispose();

        hitParticleEffectActor.dispose();

        deathSound.dispose();
        brewSound.dispose();
        throwSound.dispose();
        sellSound.dispose();
        hitSound.dispose();

        stepMusic.dispose();
        barMusic.dispose();
        waveMusic.dispose();

        skin.dispose();

        spriteBatch.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Skin getSkin() {
        return skin;
    }

    public void playWaveMusic() {
        waveMusic.play();
    }

    public void stopWaveMusic() {
        waveMusic.stop();
    }

    public void playBarMusic() {
        barMusic.play();
    }

    public void stopBarMusic() {
        barMusic.stop();
    }

    public void playStepMusic() {
        stepMusic.play();
    }

    public void stopStepMusic() {
        stepMusic.stop();
    }

    public void playHitSound() {
        hitSound.play();
    }

    public void playSellSound() {
        sellSound.play();
    }

    public void playThrowSound() {
        throwSound.play();
    }

    public void playBrewSound() {
        brewSound.play();
    }

    public void playerDeathSound() {
        deathSound.play();
    }

    public ParticleEffectActor getHitParticleEffectActor() {
        return hitParticleEffectActor;
    }
}
