package com.wgsoft.game.gravehammer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.wgsoft.game.gravehammer.MyGdxGame;

public class HtmlScreen implements Screen {
    private static final float BUTTON_PADDING_TOP = 512f;
    private static final float BUTTON_WIDTH = 394f;
    private static final float BUTTON_HEIGHT = 170f;

    private static HtmlScreen instance;

    public static HtmlScreen getInstance() {
        if(instance == null) {
            instance = new HtmlScreen();
        }
        return instance;
    }

    private final Stage uiStage;

    private HtmlScreen() {
        uiStage = new Stage(
                new ScreenViewport(), MyGdxGame.getInstance().getSpriteBatch());
        uiStage.getRoot().setTouchable(Touchable.childrenOnly);

        final Image backgroundImage = new Image(
                MyGdxGame.getInstance().getSkin(), "html/background");
        backgroundImage.setTouchable(Touchable.disabled);
        backgroundImage.setFillParent(true);
        backgroundImage.setScaling(Scaling.fill);
        uiStage.addActor(backgroundImage);

        final Table rootTable = new Table(MyGdxGame.getInstance().getSkin());
        rootTable.setFillParent(true);

        final Button button =
            new Button(MyGdxGame.getInstance().getSkin(), "html");
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.getInstance().initializeSounds();
                MyGdxGame.getInstance().setScreen(TutorialScreen.getInstance());
            }
        });
        rootTable.add(button)
            .size(BUTTON_WIDTH, BUTTON_HEIGHT).padTop(BUTTON_PADDING_TOP);

        uiStage.addActor(rootTable);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    public void render(float delta) {
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if((float)width / height > MyGdxGame.WIDTH / MyGdxGame.HEIGHT) {
            ((ScreenViewport)uiStage.getViewport())
                .setUnitsPerPixel(MyGdxGame.HEIGHT / height);
        } else {
            ((ScreenViewport)uiStage.getViewport())
                .setUnitsPerPixel(MyGdxGame.WIDTH / width);
        }
        uiStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}
