package com.wgsoft.game.gravehammer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.wgsoft.game.gravehammer.MyGdxGame;

public class TutorialScreen implements Screen {
    private static final float SMOOZ_WIDTH = 112f;
    private static final float SMOOZ_HEIGHT = 128f;
    private static final float SMOOZ_AMPLITUDE = 16f;
    private static final float SMOOZ_TIME = 2f;
    
    private static final float BUTTON_WIDTH = 112f;
    private static final float BUTTON_HEIGHT = 56f;

    private static final float MESSAGE_ANIMATION_TIME = 0.05f;

    private static TutorialScreen instance;

    public static TutorialScreen getInstance() {
        if(instance == null) {
            instance = new TutorialScreen();
        }
        return instance;
    }

    private final Stage uiStage;

    private final Label messageLabel;

    private final Queue<String> messages;
    private String message;
    private int messageLetterCount;
    private float messageAnimationTime;

    private TutorialScreen() {
        uiStage = new Stage(
                new ScreenViewport(),
                MyGdxGame.getInstance().getSpriteBatch());
        uiStage.getRoot().setTouchable(Touchable.childrenOnly);

        final Image smoozImage =
            new Image(MyGdxGame.getInstance().getSkin(), "smooz");
        smoozImage.setSize(SMOOZ_WIDTH, SMOOZ_HEIGHT);
        smoozImage.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                smoozImage.setPosition(
                        uiStage.getWidth() / 2f,
                        uiStage.getHeight() / 2f,
                        Align.center);
            }
        }));
        smoozImage.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                smoozImage.addAction(
                        Actions.sequence(
                            Actions.moveToAligned(
                                uiStage.getWidth()
                                / 2f
                                + MathUtils.random(
                                    -SMOOZ_AMPLITUDE,
                                    SMOOZ_AMPLITUDE),
                                uiStage.getHeight()
                                / 2f
                                + MathUtils.random(
                                    -SMOOZ_AMPLITUDE,
                                    SMOOZ_AMPLITUDE),
                                Align.center,
                                SMOOZ_TIME,
                                Interpolation.fade),
                            Actions.run(this)));
            }
        }));
        uiStage.addActor(smoozImage);

        final Table rootTable = new Table(MyGdxGame.getInstance().getSkin());
        rootTable.setFillParent(true);

        messageLabel = new Label(
                "",
                MyGdxGame.getInstance().getSkin(),
                "regularSemiSmall");
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);
        rootTable.add(messageLabel).growX();

        rootTable.row();
        rootTable.add().expandY();
        rootTable.row();

        final TextButton skipButton = new TextButton(
                "Okay",
                MyGdxGame.getInstance().getSkin(),
                "button");
        skipButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(messageLetterCount < message.length()) {
                    messageLetterCount = message.length();
                    updateMessageLabel();
                } else {
                    popMessage();
                }
            }
        });
        rootTable.add(skipButton).size(BUTTON_WIDTH, BUTTON_HEIGHT);

        uiStage.addActor(rootTable);

        messages = new Queue<>();
        addMessage("Oh, You have arrived HAMMly. My name is Smooz! Nice to meet You, but what is your name, Hammly? Anyway, it doesn't matter. Congratulations on buying this bar, but...");
        addMessage("...it seems like the locals are not very happy with this!");
        addMessage(null);
        addMessage("SMASH THE ZOMBIE!");
        addMessage("WOOOOOOOOOW! HOW BIG IS YOUR HAMMER!");
        addMessage("Now I undestand that this will not a problem for You. By the way, in case You are interested, the previous owner of this smoothie bar was selling a slightly non-common smoothie... ");
        addMessage("Come on and try to make some smoothie too, HAMMly!");
        addMessage(null);
        addMessage("Walk to the door of Your bar and press 'E' key, then go to Your kitchen and try to make smoothie using Smooz, mixing different ingridients. After that - SELL IT!");
        addMessage("And here is Your profit, Hammly! Well, I suppose You can cope further without me! Destroy zombies, make smoothie from their parts and sell it to Your customers! And remember, Hammly, the power of development is in experimentation! So experiment, try different combinations, create new smoothies, get more moeny and bring more popularity to the establishment!");
        addMessage(null);
        addMessage("");
    }

    public void addMessage(String message) {
        messages.addLast(message);
    }
    
    private void popMessage() {
        messageLabel.setText("");

        message = messages.removeFirst();
        messageLetterCount = 0;
        messageAnimationTime = 0f;

        if(message == null) {
            GameScreen.getInstance().setTutorialText(messages.removeFirst());
            MyGdxGame.getInstance().setScreen(GameScreen.getInstance());
        }
    }

    private void updateMessageLabel() {
        messageLabel.setText(message.substring(0, messageLetterCount));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(uiStage);
        popMessage();
    }

    @Override
    public void render(float delta) {
        messageAnimationTime += delta;
        while(messageAnimationTime >= MESSAGE_ANIMATION_TIME
                && messageLetterCount < message.length()) {
            messageLetterCount++;
            updateMessageLabel();
            messageAnimationTime -= MESSAGE_ANIMATION_TIME;
        }
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
