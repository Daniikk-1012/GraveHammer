package com.wgsoft.game.gravehammer.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.wgsoft.game.gravehammer.MyGdxGame;
import com.wgsoft.game.gravehammer.actions.AccelerationAction;
import com.wgsoft.game.gravehammer.actions.BoundsAction;
import com.wgsoft.game.gravehammer.actions.FillParentAction;
import com.wgsoft.game.gravehammer.actions.OutOfBoundsAction;
import com.wgsoft.game.gravehammer.actions.OverlapAction;
import com.wgsoft.game.gravehammer.actions.VelocityAction;
import com.wgsoft.game.gravehammer.actions.bounds.StopBoundsListener;
import com.wgsoft.game.gravehammer.actions.outofbounds.RemoveOutOfBoundsListener;
import com.wgsoft.game.gravehammer.actions.overlap.StopOverlapListener;
import com.wgsoft.game.gravehammer.objects.BooleanHolder;
import com.wgsoft.game.gravehammer.objects.FloatHolder;
import com.wgsoft.game.gravehammer.objects.LootType;
import com.wgsoft.game.gravehammer.objects.PotionBrew;
import com.wgsoft.game.gravehammer.objects.PotionType;
import com.wgsoft.game.gravehammer.objects.ZombieState;
import com.wgsoft.game.gravehammer.objects.ZombieStateHolder;

public class GameScreen implements Screen {
    private static final float MAX_DELTA_TIME = 0.04f;

    private static final float GRAVITY = 1024f;

    private static final float GROUND_HEIGHT = 256f;

    private static final int POPULARIT_DEFAULT = 50;
    private static final int POPULARITY_MAX = 100;
    private static final int POPULARITY_DEATH_LOSS = 30;
    
    private static final float HOUSE_X = 128f;
    private static final float HOUSE_WIDTH = 1024f;
    private static final float HOUSE_HEIGHT = 512f;

    private static final float E_IMAGE_X = 900f;
    private static final float E_IMAGE_Y = 512f;
    private static final float E_IMAGE_SIZE = 80f;

    private static final int PLAYER_HEALTH_MAX = 100;
    private static final float PLAYER_SIZE = 128f;
    private static final float PLAYER_JUMP_IMPULSE = 512f;
    private static final float PLAYER_VELOCITY = 512f;
    private static final float PLAYER_WALK_ANIMATION_FRAME_DURATION = 0.25f;

    private static final float HAMMER_WIDTH = 128f;
    private static final float HAMMER_HEIGHT = 256f;
    private static final float HAMMER_ORIGIN_Y = 64f;
    private static final float HAMMER_ANIMATION_TIME = 0.2f;
    private static final float HAMMER_RETURN_ANIMATION_TIME = 1f;
    private static final float HAMMER_ANGLE_MIN = 90f;
    private static final float HAMMER_ANGLE_MAX = -120f;
    private static final float HIT_PARTICLES_DISTANCE = 150f;
    private static final float HAMMER_TRIGGER_WIDTH = 150f;
    private static final float HAMMER_TRIGGER_HEIGHT = 128f;
    private static final float HAMMER_TRIGGER_DISTANCE = 150f;

    private static final int ZOMBIE_COUNT_MAX_MIN = 3;
    private static final int ZOMBIE_DAMAGE = 20;
    private static final float ZOMBIE_SIZE = 128f;
    private static final float ZOMBIE_VELOCITY = 128f;
    private static final float ZOMBIE_EXIT_TIME = 4f;
    private static final float ZOMBIE_SPAWN_INTERVAL = 1.5f;
    private static final float ZOMBIE_UP_ANIMATION_FRAME_DURATION = 0.75f;
    private static final float ZOMBIE_WALK_ANIMATION_FRAME_DURATION = 0.5f;
    private static final float ZOMBIE_DOWN_IMPULSE = 512f;

    private static final float LOOT_SIZE = 64f;
    private static final float LOOT_IMPULSE = 512f;

    private static final float ICON_SIZE = 64f;
    private static final float ICON_PADDING = 16f;

    private static final int POTION_ITEM_COUNT = 3;
    private static final int MENU_INVENTORY_ROWS = 4;
    private static final int MENU_INVENTORY_COLUMNS = 5;
    private static final float MENU_WIDTH = 1024f;
    private static final float MENU_HEIGHT = 680f;
    private static final float MENU_SECTION_IMAGE_SIZE = 80f;
    private static final float MENU_SECTION_LABEL_PADDING_LEFT = 8f;
    private static final float MENU_SECTION_PADDING = 8f;
    private static final float MENU_SECTION_TABLE_PADDING_LEFT = 16f;
    private static final float MENU_CONTENT_HEIGHT = 650f;
    private static final float MENU_INVENTORY_ITEM_SIZE = 64f;
    private static final float MENU_INVENTORY_ITEM_PADDING = 8f;
    private static final float MENU_ARROW_WIDTH = 16f;
    private static final float MENU_ARROW_HEIGHT = 32f;
    private static final float MENU_SIDE_TABLE_WIDTH = 500f;
    private static final float MENU_SIDE_TABLE_HEIGHT = 500f;
    private static final float MENU_COIN_SIZE = 32f;
    private static final float MENU_COIN_PADDING = 16f;
    private static final float MENU_BUTTON_WIDTH = 112f;
    private static final float MENU_BUTTON_HEIGHT = 56f;
    private static final float MENU_TAVERN_WIDTH = 288f;
    private static final float MENU_TAVERN_HEIGHT = 480f;
    private static final float MENU_INSTRUMENT_WIDTH = 112f;
    private static final float MENU_INSTRUMENT_HEIGHT = 128f;
    private static final float MENU_INSTRUMENT_PADDING = 64f;
    private static final float MENU_KITCHEN_SHELF_WIDTH = 362f;
    private static final float MENU_KITCHEN_SHELF_HEIGHT = 16f;
    private static final float MENU_OFFSET_HEIGHT = 64f;

    private static GameScreen instance;

    public static GameScreen getInstance() {
        if(instance == null) {
            instance = new GameScreen();
        }
        return instance;
    }

    private final Stage uiStage;

    private final Image eImage;
    private final Group gameGroup;
    private final Image groundImage;
    private final Group playerGroup;
    private final VelocityAction playerVelocityAction;

    private final Table menuWrapperTable;
    private final Label inventoryPageLabel;
    private final Image[][] itemImages;
    private final Image[][] itemCursorImages;
    private final Label inventoryItemLabel;
    private final Image inventoryItemImage;
    private final Label menuCostLabel;
    private final TextButton menuButton;
    private final Table barTable;
    private final Table kitchenTable;

    private final Array<Object> inventory;
    private int inventoryPage;
    private int inventoryCursorRow, inventoryCursorColumn;

    private final LootType[] potion;
    private int potionSize;

    private boolean keyA, keyD;
    private boolean keyLeft, keyRight;

    private int popularity = POPULARIT_DEFAULT;
    private int playerHealth = PLAYER_HEALTH_MAX;
    private int money;
    private boolean playerWalks;
    private boolean playerRight = true;
    private float playerWalkAnimationTime;

    private final Actor hammerTriggerActor;
    private boolean hammerHit;
    private float hammerAnimationTime = HAMMER_ANIMATION_TIME;
    private float hammerReturnAnimationTime = HAMMER_RETURN_ANIMATION_TIME;

    private int zombieCount;
    private int zombieKilledCount;
    private int zombieCountMax = ZOMBIE_COUNT_MAX_MIN;
    private final Animation<TextureRegionDrawable> zombieUpAnimation;
    private final Animation<TextureRegionDrawable> zombieWalkAnimation;

    private float zombieSpawnTime;

    private GameScreen() {
        if(Gdx.app.getType() == Application.ApplicationType.WebGL) {
            new FillParentAction();
            new VelocityAction();
            new AccelerationAction();
            new OverlapAction();
            new BoundsAction();
            new OutOfBoundsAction();
        }

        uiStage = new Stage(
                new ScreenViewport(), MyGdxGame.getInstance().getSpriteBatch());
        uiStage.getRoot().setTouchable(Touchable.childrenOnly);

        final Image backgroundImage = new Image(
                MyGdxGame.getInstance().getSkin(), "sky");
        backgroundImage.setTouchable(Touchable.disabled);
        backgroundImage.setFillParent(true);
        backgroundImage.setScaling(Scaling.fill);
        uiStage.addActor(backgroundImage);

        final Image houseImage = new Image(
                MyGdxGame.getInstance().getSkin(), "house");
        houseImage.setX(HOUSE_X);
        houseImage.setSize(HOUSE_WIDTH, HOUSE_HEIGHT);
        houseImage.setTouchable(Touchable.disabled);
        uiStage.addActor(houseImage);

        eImage = new Image(MyGdxGame.getInstance().getSkin(), "e");
        eImage.setPosition(E_IMAGE_X, E_IMAGE_Y);
        eImage.setSize(E_IMAGE_SIZE, E_IMAGE_SIZE);
        eImage.setTouchable(Touchable.disabled);
        eImage.setVisible(false);
        uiStage.addActor(eImage);

        gameGroup = new Group();
        final FillParentAction gameFillParentAction =
            Actions.action(FillParentAction.class);
        gameFillParentAction.setFillHorizontally(true);
        gameFillParentAction.setFillVertically(true);
        gameGroup.addAction(Actions.forever(gameFillParentAction));
        gameGroup.setTouchable(Touchable.disabled);

        groundImage = new Image(
                MyGdxGame.getInstance().getSkin().getTiledDrawable("ground"),
                Scaling.stretch);
        final FillParentAction groundFillParentAction =
            Actions.action(FillParentAction.class);
        groundFillParentAction.setFillHorizontally(true);
        groundImage.addAction(Actions.forever(groundFillParentAction));
        groundImage.setHeight(GROUND_HEIGHT);
        houseImage.setY(groundImage.getTop()); //houseImage
        gameGroup.addActor(groundImage);

        final Array<TextureRegionDrawable> playerWalkAnimationDrawables =
            new Array<TextureRegionDrawable>();
        for(int i = 0; i < MyGdxGame.getInstance().getSkin()
                .getRegions("player/walk").size; i++) {
            playerWalkAnimationDrawables.add(new TextureRegionDrawable(
                        MyGdxGame.getInstance().getSkin()
                        .getRegions("player/walk").get(i)));
        }
        final Animation<TextureRegionDrawable> playerWalkAnimation =
            new Animation<>(
                    PLAYER_WALK_ANIMATION_FRAME_DURATION,
                    playerWalkAnimationDrawables,
                    Animation.PlayMode.LOOP);

        playerGroup = new Group();
        playerGroup.setY(groundImage.getTop());
        playerGroup.setSize(PLAYER_SIZE, PLAYER_SIZE);
        playerVelocityAction =
            Actions.action(VelocityAction.class);
        final AccelerationAction playerAccelerationAction =
            Actions.action(AccelerationAction.class);
        playerAccelerationAction.setAccelerationY(-GRAVITY);
        playerAccelerationAction.setVelocityAction(playerVelocityAction);
        playerGroup.addAction(Actions.forever(playerAccelerationAction));
        playerGroup.addAction(Actions.forever(Actions.run(new Runnable() {
            @Override
            public void run() {
                int direction = 0;
                if(keyA) {
                    direction--;
                }
                if(keyD) {
                    direction++;
                }
                if(keyLeft) {
                    direction--;
                }
                if(keyRight) {
                    direction++;
                }
                if(direction < 0) {
                    playerVelocityAction.setVelocityX(-PLAYER_VELOCITY);
                    playerRight = false;
                } else if(direction > 0) {
                    playerVelocityAction.setVelocityX(PLAYER_VELOCITY);
                    playerRight = true;
                } else {
                    playerVelocityAction.setVelocityX(0f);
                }
            }
        })));
        playerGroup.addAction(Actions.forever(playerVelocityAction));
        final OverlapAction playerOverlapAction =
            Actions.action(OverlapAction.class);
        final StopOverlapListener playerStopOverlapListener =
            new StopOverlapListener();
        playerStopOverlapListener.setListener(
                new StopOverlapListener.Listener() {
                    @Override
                    public boolean onOverlap(Actor actor) {
                        return actor == groundImage;
                    }
                });
        playerStopOverlapListener.setVelocityAction(playerVelocityAction);
        playerOverlapAction.setListener(playerStopOverlapListener);
        playerGroup.addAction(Actions.forever(playerOverlapAction));
        final BoundsAction playerBoundsAction =
            Actions.action(BoundsAction.class);
        final StopBoundsListener playerStopBoundsListener =
            new StopBoundsListener();
        playerStopBoundsListener.setVelocityAction(playerVelocityAction);
        playerBoundsAction.setListener(playerStopBoundsListener);
        playerGroup.addAction(Actions.forever(playerBoundsAction));

        final Image playerImage =
            new Image(MyGdxGame.getInstance().getSkin(), "player/stand");
        playerImage.setHeight(playerGroup.getHeight());
        playerImage.addAction(Actions.forever(Actions.run(new Runnable() {
            @Override
            public void run() {
                if(playerRight) {
                    playerImage.setX(0f);
                    playerImage.setWidth(playerGroup.getWidth());
                } else {
                    playerImage.setX(playerGroup.getWidth());
                    playerImage.setWidth(-playerGroup.getWidth());
                }
                if(playerVelocityAction.getVelocityX() == 0f) {
                    playerImage.setDrawable(MyGdxGame.getInstance().getSkin(),
                            "player/stand");
                } else {
                    playerImage.setDrawable(playerWalkAnimation
                            .getKeyFrame(playerWalkAnimationTime));
                }
            }
        })));
        playerGroup.addActor(playerImage);

        final Image hammerImage =
            new Image(MyGdxGame.getInstance().getSkin(), "hammer");
        hammerImage.setSize(HAMMER_WIDTH, HAMMER_HEIGHT);
        hammerImage.setOrigin(Align.center);
        hammerImage.setOriginY(HAMMER_ORIGIN_Y);
        hammerImage.addAction(Actions.forever(Actions.run(new Runnable() {
            @Override
            public void run() {
                hammerImage.setPosition(playerGroup.getWidth() / 2f
                        - hammerImage.getOriginX(),
                        playerGroup.getHeight() / 2f
                        - hammerImage.getOriginY());
                if(hammerAnimationTime < HAMMER_ANIMATION_TIME) {
                    if(playerRight) {
                        hammerImage.setRotation(Interpolation.pow3In.apply(
                                    HAMMER_ANGLE_MIN,
                                    HAMMER_ANGLE_MAX,
                                    hammerAnimationTime
                                    / HAMMER_ANIMATION_TIME));
                    } else {
                        hammerImage.setRotation(Interpolation.pow2In.apply(
                                    -HAMMER_ANGLE_MIN,
                                    -HAMMER_ANGLE_MAX,
                                    hammerAnimationTime
                                    / HAMMER_ANIMATION_TIME));
                    }
                } else if(hammerReturnAnimationTime
                        < HAMMER_RETURN_ANIMATION_TIME) {
                    if(playerRight) {
                        hammerImage.setRotation(Interpolation.fade.apply(
                                    HAMMER_ANGLE_MAX,
                                    HAMMER_ANGLE_MIN,
                                    hammerReturnAnimationTime
                                    / HAMMER_RETURN_ANIMATION_TIME));
                    } else {
                        hammerImage.setRotation(Interpolation.fade.apply(
                                    -HAMMER_ANGLE_MAX,
                                    -HAMMER_ANGLE_MIN,
                                    hammerReturnAnimationTime
                                    / HAMMER_RETURN_ANIMATION_TIME));
                    }
                } else {
                    if(playerRight) {
                        hammerImage.setRotation(HAMMER_ANGLE_MIN);
                    } else {
                        hammerImage.setRotation(-HAMMER_ANGLE_MIN);
                    }
                }
            }
        })));
        playerGroup.addActor(hammerImage);

        gameGroup.addActor(playerGroup);

        hammerTriggerActor = new Actor();
        hammerTriggerActor.setY(groundImage.getTop());
        hammerTriggerActor.setSize(HAMMER_TRIGGER_WIDTH, HAMMER_TRIGGER_HEIGHT);
        hammerTriggerActor
            .addAction(Actions.forever(Actions.run(new Runnable() {
                @Override
                public void run() {
                    if(playerRight) {
                        hammerTriggerActor.setX(playerGroup.getX(Align.center)
                                + HAMMER_TRIGGER_DISTANCE,
                                Align.center);
                    } else {
                        hammerTriggerActor.setX(playerGroup.getX(Align.center)
                                - HAMMER_TRIGGER_DISTANCE,
                                Align.center);
                    }
                }
            })));
        gameGroup.addActor(hammerTriggerActor);

        gameGroup.addActor(MyGdxGame.getInstance().getHitParticleEffectActor());

        uiStage.addActor(gameGroup);

        final Table hudTable = new Table(MyGdxGame.getInstance().getSkin());
        hudTable.setFillParent(true);
        hudTable.top();
        hudTable.setTouchable(Touchable.disabled);

        final Image heartImage = new Image(
                MyGdxGame.getInstance().getSkin(), "heart");
        hudTable.add(heartImage).size(ICON_SIZE).pad(ICON_PADDING);

        final Label healthLabel = new Label(
                "",
                MyGdxGame.getInstance().getSkin(),
                "regularMedium");
        healthLabel.addAction(Actions.forever(Actions.run(new Runnable() {
            @Override
            public void run() {
                healthLabel.setText(playerHealth + "%");
            }
        })));
        hudTable.add(healthLabel).expandX().left();

        final Label moneyLabel = new Label(
                "",
                MyGdxGame.getInstance().getSkin(),
                "regularMedium");
        moneyLabel.addAction(Actions.forever(Actions.run(new Runnable() {
            @Override
            public void run() {
                moneyLabel.setText(money);
            }
        })));
        hudTable.add(moneyLabel).expandX().right();

        final Image moneyImage = new Image(
                MyGdxGame.getInstance().getSkin(), "coin");
        hudTable.add(moneyImage).size(ICON_SIZE).pad(ICON_PADDING);

        hudTable.row();
        hudTable.add();
        hudTable.add().expandX();

        final Label popularityLabel = new Label(
                "", MyGdxGame.getInstance().getSkin(), "regularMedium");
        popularityLabel.addAction(Actions.forever(Actions.run(new Runnable() {
            @Override
            public void run() {
                popularityLabel.setText(popularity + "%");
            }
        })));
        hudTable.add(popularityLabel).expandX().right();

        final Image popularityImage = new Image(
                MyGdxGame.getInstance().getSkin(), "popularity");
        hudTable.add(popularityImage).size(ICON_SIZE).pad(ICON_PADDING);

        uiStage.addActor(hudTable);

        menuWrapperTable =
            new Table(MyGdxGame.getInstance().getSkin());
        menuWrapperTable.setFillParent(true);
        menuWrapperTable.setVisible(false);

        final Table menuTable = new Table(MyGdxGame.getInstance().getSkin());
        menuTable.setBackground("menu/background");

        final Table sectionTable =
            new Table(MyGdxGame.getInstance().getSkin());
        sectionTable.left().padLeft(MENU_SECTION_TABLE_PADDING_LEFT);

        final Image shopSectionLockedImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/shop");
        sectionTable.add(shopSectionLockedImage)
            .size(112f).pad(MENU_SECTION_PADDING);

        final Table barSectionTable =
            new Table(MyGdxGame.getInstance().getSkin());
        barSectionTable.setBackground("menu/section");
        barSectionTable.setTouchable(Touchable.enabled);

        final Image barSectionImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/bar");
        barSectionImage.setTouchable(Touchable.disabled);
        barSectionTable.add(barSectionImage)
            .size(MENU_SECTION_IMAGE_SIZE, MENU_SECTION_IMAGE_SIZE);

        final Label barSectionLabel = new Label(
                "Bar",
                MyGdxGame.getInstance().getSkin(),
                "regularSemiSmall");
        barSectionLabel.setTouchable(Touchable.disabled);
        barSectionTable.add(barSectionLabel)
            .padLeft(MENU_SECTION_LABEL_PADDING_LEFT);

        sectionTable.add(barSectionTable).pad(MENU_SECTION_PADDING);

        final Table kitchenSectionTable =
            new Table(MyGdxGame.getInstance().getSkin());
        kitchenSectionTable.setBackground("menu/section");
        kitchenSectionTable.setTouchable(Touchable.enabled);

        final Image kitchenSectionImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/kitchen");
        kitchenSectionImage.setTouchable(Touchable.disabled);
        kitchenSectionTable.add(kitchenSectionImage)
            .size(MENU_SECTION_IMAGE_SIZE, MENU_SECTION_IMAGE_SIZE);

        final Label kitchenSectionLabel = new Label(
                "",
                MyGdxGame.getInstance().getSkin(),
                "regularSemiSmall");
        kitchenSectionLabel.setTouchable(Touchable.disabled);
        kitchenSectionTable.add(kitchenSectionLabel)
            .padLeft(MENU_SECTION_LABEL_PADDING_LEFT);

        sectionTable.add(kitchenSectionTable).pad(MENU_SECTION_PADDING);

        final Image bookSectionLockedImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/book");
        sectionTable.add(bookSectionLockedImage)
            .size(112f).pad(MENU_SECTION_PADDING);

        final Image listSectionLockedImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/list");
        sectionTable.add(listSectionLockedImage)
            .size(112f).pad(MENU_SECTION_PADDING);

        barSectionTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                barSectionLabel.setText("Bar");
                kitchenSectionLabel.setText("");

                barTable.setVisible(true);
                kitchenTable.setVisible(false);

                menuButton.setText("Sell");
            }
        });
        kitchenSectionTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                barSectionLabel.setText("");
                kitchenSectionLabel.setText("Kitchen");

                barTable.setVisible(false);
                kitchenTable.setVisible(true);

                if(potionSize == POTION_ITEM_COUNT) {
                    menuButton.setText("Brew");
                } else {
                    menuButton.setText("Throw");
                }
            }
        });

        menuTable.add(sectionTable).growX();

        menuTable.row();

        final Table menuContentTable =
            new Table(MyGdxGame.getInstance().getSkin());

        final Table menuLeftContentTable =
            new Table(MyGdxGame.getInstance().getSkin());

        inventoryPageLabel =
            new Label("", MyGdxGame.getInstance().getSkin(), "regularSmall");
        menuLeftContentTable.add(inventoryPageLabel);

        menuLeftContentTable.row();

        final Table inventoryContentTable =
            new Table(MyGdxGame.getInstance().getSkin());

        final Button inventoryLeftButton = new Button(
                MyGdxGame.getInstance().getSkin(), "inventoryLeft");
        inventoryLeftButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(inventoryPage > 0) {
                    setInventoryPage(inventoryPage - 1);
                }
            }
        });
        inventoryContentTable.add(inventoryLeftButton)
            .size(MENU_ARROW_WIDTH, MENU_ARROW_HEIGHT);

        inventory = new Array<>();
        itemImages = new Image[MENU_INVENTORY_ROWS][MENU_INVENTORY_COLUMNS];
        itemCursorImages =
            new Image[MENU_INVENTORY_ROWS][MENU_INVENTORY_COLUMNS];

        final Table inventoryTable =
            new Table(MyGdxGame.getInstance().getSkin());

        for(int _i = 0; _i < MENU_INVENTORY_ROWS; _i++) {
            final int i = _i;
            for(int _j = 0; _j < MENU_INVENTORY_COLUMNS; _j++) {
                final int j = _j;
                final Image itemBackgroundImage = new Image(
                        MyGdxGame.getInstance().getSkin(), "menu/item");
                itemImages[i][j] = new Image();
                itemImages[i][j].addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        for(int i = 0; i < itemCursorImages.length; i++) {
                            for(
                                    int j = 0;
                                    j < itemCursorImages[i].length;
                                    j++) {
                                itemCursorImages[i][j].setVisible(false);
                            }
                        }
                        itemCursorImages[i][j].setVisible(true);
                        inventoryCursorRow = i;
                        inventoryCursorColumn = j;
                        updateCashItem();
                    }
                });
                itemCursorImages[i][j] = new Image(
                        MyGdxGame.getInstance().getSkin(), "menu/cursor");
                itemCursorImages[i][j].setVisible(false);
                itemCursorImages[i][j].setTouchable(Touchable.disabled);
                inventoryTable.stack(
                        itemBackgroundImage,
                        itemImages[i][j],
                        itemCursorImages[i][j])
                    .size(MENU_INVENTORY_ITEM_SIZE)
                    .pad(MENU_INVENTORY_ITEM_PADDING);
            }
            inventoryTable.row();
        }
        itemCursorImages[0][0].setVisible(true);

        inventoryContentTable.add(inventoryTable);

        final Button inventoryRightButton = new Button(
                MyGdxGame.getInstance().getSkin(), "inventoryRight");
        inventoryRightButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setInventoryPage(inventoryPage + 1);
            }
        });
        inventoryContentTable.add(inventoryRightButton)
            .size(MENU_ARROW_WIDTH, MENU_ARROW_HEIGHT);

        menuLeftContentTable.add(inventoryContentTable);

        menuLeftContentTable.row();

        final Table inventoryItemTable =
            new Table(MyGdxGame.getInstance().getSkin());

        inventoryItemLabel = new Label(
                "",
                MyGdxGame.getInstance().getSkin(),
                "regularSmall");
        inventoryItemTable.add(inventoryItemLabel);

        inventoryItemImage = new Image();
        inventoryItemTable.add(inventoryItemImage)
            .size(MENU_INVENTORY_ITEM_SIZE);

        menuLeftContentTable.add(inventoryItemTable);

        menuLeftContentTable.row();

        final Table sellTable = new Table(MyGdxGame.getInstance().getSkin());

        menuCostLabel =
            new Label("", MyGdxGame.getInstance().getSkin(), "regularSmall");
        sellTable.add(menuCostLabel);

        final Image menuCoinImage =
            new Image(MyGdxGame.getInstance().getSkin(), "coin");
        sellTable.add(menuCoinImage)
            .size(MENU_COIN_SIZE).pad(MENU_COIN_PADDING);

        potion = new LootType[POTION_ITEM_COUNT];

        menuButton = new TextButton(
                "Sell",
                MyGdxGame.getInstance().getSkin(),
                "menu");
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!barSectionLabel.getText().isEmpty()) {
                    final int index = getInventoryIndex(
                            inventoryPage,
                            inventoryCursorRow,
                            inventoryCursorColumn);
                    if(inventory.size > index) {
                        final Object item = inventory.get(index);
                        if(item instanceof LootType) {
                            money += (int)(
                                    ((LootType)item).getCost()
                                    * popularity
                                    / POPULARITY_MAX);
                            popularity += ((LootType)item).getPopularity();
                        } else if(item instanceof PotionType) {
                            money += (int)(
                                    ((PotionType)item).getCost()
                                    * popularity
                                    / POPULARITY_MAX);
                            popularity += ((PotionType)item).getPopularity();
                        }
                        if(popularity > POPULARITY_MAX) {
                            popularity = POPULARITY_MAX;
                        }
                        inventory.removeIndex(index);
                        setInventoryPage(inventoryPage);
                        MyGdxGame.getInstance().playSellSound();
                    }
                } else if(!kitchenSectionLabel.getText().isEmpty()) {
                    if(potionSize == POTION_ITEM_COUNT) {
                        menuButton.setText("Throw");
                        final PotionType potionType = PotionBrew.brew(potion);
                        if(potionType != null) {
                            inventory.add(potionType);
                        }
                        potionSize = 0;
                        setInventoryPage(inventoryPage);
                        MyGdxGame.getInstance().playBrewSound();
                    } else {
                        final int index = getInventoryIndex(
                                inventoryPage,
                                inventoryCursorRow,
                                inventoryCursorColumn);
                        if(inventory.size > index) {
                            final Object item = inventory.get(index);
                            if(item instanceof LootType) {
                                potion[potionSize] = (LootType)item;
                                potionSize++;
                                inventory.removeIndex(index);
                                if(potionSize == POTION_ITEM_COUNT) {
                                    menuButton.setText("Brew");
                                }
                                setInventoryPage(inventoryPage);
                                MyGdxGame.getInstance().playThrowSound();
                            }
                        }
                    }
                }
            }
        });
        sellTable.add(menuButton)
            .size(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);

        menuLeftContentTable.add(sellTable);

        menuContentTable.add(menuLeftContentTable);

        barTable = new Table(MyGdxGame.getInstance().getSkin());

        final Image menuTavernImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/tavern");
        barTable.add(menuTavernImage)
            .size(MENU_TAVERN_WIDTH, MENU_TAVERN_HEIGHT);

        kitchenTable = new Table(MyGdxGame.getInstance().getSkin());
        kitchenTable.setVisible(false);

        final Image cauldronImage =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/cauldron");
        kitchenTable.add(cauldronImage)
            .size(MENU_INSTRUMENT_WIDTH, MENU_INSTRUMENT_HEIGHT);

        kitchenTable.add().width(MENU_INSTRUMENT_PADDING);

        kitchenTable.add().size(MENU_INSTRUMENT_WIDTH, MENU_INSTRUMENT_HEIGHT);

        kitchenTable.row();

        Image menuShelf =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/shelf");
        kitchenTable.add(menuShelf)
            .colspan(3)
            .size(MENU_KITCHEN_SHELF_WIDTH, MENU_KITCHEN_SHELF_HEIGHT);

        kitchenTable.row();

        kitchenTable.add().size(MENU_INSTRUMENT_WIDTH, MENU_INSTRUMENT_HEIGHT);

        kitchenTable.add().width(MENU_INSTRUMENT_PADDING);

        kitchenTable.add().size(MENU_INSTRUMENT_WIDTH, MENU_INSTRUMENT_HEIGHT);

        kitchenTable.row();

        menuShelf =
            new Image(MyGdxGame.getInstance().getSkin(), "menu/shelf");
        kitchenTable.add(menuShelf)
            .colspan(3)
            .size(MENU_KITCHEN_SHELF_WIDTH, MENU_KITCHEN_SHELF_HEIGHT);

        menuContentTable.stack(barTable, kitchenTable)
            .size(MENU_SIDE_TABLE_WIDTH, MENU_SIDE_TABLE_HEIGHT);

        setInventoryPage(0);

        menuTable.add(menuContentTable).growX().height(MENU_CONTENT_HEIGHT);

        menuTable.row();
        menuTable.add().height(MENU_OFFSET_HEIGHT);

        menuWrapperTable.add(menuTable).size(MENU_WIDTH, MENU_HEIGHT);

        uiStage.addActor(menuWrapperTable);

        uiStage.addListener(new InputListener() {
            private boolean inMenu;
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode) {
                    case Input.Keys.SPACE:
                        if(playerVelocityAction.getVelocityY() == 0f
                                && hammerAnimationTime
                                >= HAMMER_ANIMATION_TIME
                                && hammerReturnAnimationTime
                                >= HAMMER_RETURN_ANIMATION_TIME
                                && !inMenu) {
                            playerVelocityAction
                                .setVelocityY(PLAYER_JUMP_IMPULSE);
                            hammerAnimationTime = 0f;
                            MyGdxGame.getInstance().playHitSound();
                        }
                        return true;
                    case Input.Keys.A:
                        if(!inMenu) {
                            keyA = true;
                            playerWalkAnimationTime = 0f;
                        }
                        return true;
                    case Input.Keys.LEFT:
                        if(!inMenu) {
                            keyLeft = true;
                            playerWalkAnimationTime = 0f;
                        }
                        return true;
                    case Input.Keys.D:
                        if(!inMenu) {
                            keyD = true;
                            playerWalkAnimationTime = 0f;
                        }
                        return true;
                    case Input.Keys.RIGHT:
                        if(!inMenu) {
                            keyRight = true;
                            playerWalkAnimationTime = 0f;
                        }
                        return true;
                    case Input.Keys.E:
                        // Only hammerAnimationTime is enough
                        if(zombieCount >= zombieCountMax
                                && zombieKilledCount >= zombieCount
                                && playerGroup.getX() < houseImage.getRight()
                                && playerGroup.getRight() > houseImage.getX()
                                && playerGroup.getY() < houseImage.getTop()
                                && playerGroup.getTop() > houseImage.getY()
                                && hammerAnimationTime
                                >= HAMMER_ANIMATION_TIME) {
                            inMenu = true;
                            keyA = false;
                            keyD = false;
                            keyLeft = false;
                            keyRight = false;
                            playerGroup.setVisible(false);
                            menuWrapperTable.setVisible(true);
                        }
                        return true;
                    case Input.Keys.ESCAPE:
                        inMenu = false;
                        if(zombieCount >= zombieCountMax
                                && zombieKilledCount >= zombieCount) {
                            menuWrapperTable.setVisible(false);
                            eImage.setVisible(false);
                            playerGroup.setVisible(true);
                            MyGdxGame.getInstance().stopBarMusic();
                            MyGdxGame.getInstance().playWaveMusic();
                            newWave();
                        }
                        return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                switch(keycode) {
                    case Input.Keys.SPACE:
                    case Input.Keys.ESCAPE:
                        return true;
                    case Input.Keys.A:
                        keyA = false;
                        return true;
                    case Input.Keys.LEFT:
                        keyLeft = false;
                        return true;
                    case Input.Keys.D:
                        keyD = false;
                        return true;
                    case Input.Keys.RIGHT:
                        keyRight = false;
                        return true;
                }
                return false;
            }
        });
        uiStage.setKeyboardFocus(uiStage.getRoot());

        final Array<TextureRegionDrawable> zombieUpAnimationDrawables =
            new Array<>();
        for(int i = 0;i < MyGdxGame.getInstance().getSkin()
                .getRegions("zombie/up").size; i++) {
            zombieUpAnimationDrawables.add(new TextureRegionDrawable(
                        MyGdxGame.getInstance().getSkin()
                        .getRegions("zombie/up").get(i)));
        }
        zombieUpAnimation = new Animation<>(
                ZOMBIE_UP_ANIMATION_FRAME_DURATION,
                zombieUpAnimationDrawables,
                Animation.PlayMode.LOOP);
        final Array<TextureRegionDrawable> zombieWalkAnimationDrawables =
            new Array<>();
        for(int i = 0;i < MyGdxGame.getInstance().getSkin()
                .getRegions("zombie/walk").size; i++) {
            zombieWalkAnimationDrawables.add(new TextureRegionDrawable(
                        MyGdxGame.getInstance().getSkin()
                        .getRegions("zombie/walk").get(i)));
        }
        zombieWalkAnimation = new Animation<>(
                ZOMBIE_WALK_ANIMATION_FRAME_DURATION,
                zombieWalkAnimationDrawables,
                Animation.PlayMode.LOOP);
    }

    private void newWave() {
        playerHealth = PLAYER_HEALTH_MAX;

        zombieCount = 0;
        zombieKilledCount = 0;
        zombieCountMax++;
        zombieSpawnTime = 0f;
    }

    private void clearArea() {
        gameGroup.clear();
        gameGroup.addActor(groundImage);
        playerHealth = 100;
        playerRight = true;
        playerWalkAnimationTime = 0f;
        playerVelocityAction.setVelocityY(0f);
        playerGroup.setPosition(0f, groundImage.getTop());
        gameGroup.addActor(playerGroup);
        gameGroup.addActor(hammerTriggerActor);
        gameGroup.addActor(MyGdxGame.getInstance().getHitParticleEffectActor());

        hammerAnimationTime = HAMMER_ANIMATION_TIME;
        hammerReturnAnimationTime = HAMMER_RETURN_ANIMATION_TIME;
        
        zombieCount = zombieCountMax;
        zombieKilledCount = zombieCount;
    }
    
    private void setInventoryPage(int page) {
        if(page > getMaxInventoryPage()) {
            page = getMaxInventoryPage();
        }
        for(int i = 0; i < itemImages.length; i++) {
            for(int j = 0; j < itemImages[i].length; j++) {
                final int index = getInventoryIndex(page, i, j);
                if(inventory.size > index) {
                    final Object item = inventory.get(index);
                    if(item instanceof LootType) {
                        itemImages[i][j].setDrawable(
                                MyGdxGame.getInstance().getSkin(),
                                "loot/" + item);
                    } else {
                        itemImages[i][j].setDrawable(
                                MyGdxGame.getInstance().getSkin(),
                                "potion/" + item);
                    }
                } else {
                    itemImages[i][j].setDrawable(null);
                }
            }
        }
        inventoryPage = page;
        inventoryPageLabel.setText((inventoryPage + 1)
                + "/"
                + (getMaxInventoryPage() + 1));
        updateCashItem();
    }

    private int getMaxInventoryPage() {
        return (inventory.size - 1)
                / (MENU_INVENTORY_ROWS * MENU_INVENTORY_COLUMNS);
    }

    private void updateCashItem() {
        inventoryItemImage.setDrawable(
                itemImages[inventoryCursorRow][inventoryCursorColumn]
                .getDrawable());
        final int index = getInventoryIndex(
                inventoryPage, inventoryCursorRow, inventoryCursorColumn);
        if(inventory.size > index) {
            final Object item = inventory.get(index);
            if(item instanceof LootType) {
                inventoryItemLabel.setText(((LootType)item).getDescription());
                menuCostLabel.setText(
                        (int)(
                            ((LootType)item).getCost()
                            * popularity
                            / POPULARITY_MAX));
            } else if(item instanceof PotionType) {
                inventoryItemLabel.setText(((PotionType)item).getDescription());
                menuCostLabel.setText((int)(
                            ((PotionType)item).getCost()
                            * popularity
                            / POPULARITY_MAX));
            }
        } else {
            inventoryItemLabel.setText("No item selected");
            menuCostLabel.setText("0");
        }
    }

    private int getInventoryIndex(int page, int row, int column) {
        return page
            * MENU_INVENTORY_ROWS
            * MENU_INVENTORY_COLUMNS
            + row
            * MENU_INVENTORY_COLUMNS
            + column;
    }

    private void killZombie(ZombieStateHolder zombieState,
            Group zombieGroup,
            VelocityAction zombieVelocityAction,
            AccelerationAction zombieAccelerationAction,
            RepeatAction zombieControlRepeatAction,
            RepeatAction zombieStopOverlapRepeatAction,
            RepeatAction zombieHammerOverlapRepeatAction,
            RepeatAction zombiePlayerOverlapRepeatAction,
            SequenceAction zombieSequenceAction) {
        zombieGroup.removeAction(zombieControlRepeatAction);
        zombieGroup.removeAction(zombieStopOverlapRepeatAction);
        zombieGroup.removeAction(zombieHammerOverlapRepeatAction);
        zombieGroup.removeAction(zombiePlayerOverlapRepeatAction);
        zombieGroup.removeAction(zombieSequenceAction);
        zombieVelocityAction.setVelocityX(0f);
        zombieVelocityAction.setVelocityY(-ZOMBIE_DOWN_IMPULSE);
        zombieState.value = ZombieState.DOWN;

        if(zombieAccelerationAction.getActor() == null){
            zombieGroup.addAction(Actions.forever(zombieAccelerationAction));
        }
        if(zombieVelocityAction.getActor() == null) {
            zombieGroup.addAction(Actions.forever(zombieVelocityAction));
        }

        zombieKilledCount++;

        if(playerHealth <= 0) {
            MyGdxGame.getInstance().playerDeathSound();
            popularity -= POPULARITY_DEATH_LOSS;
            if(popularity < 0) {
                popularity = 0;
            }
            clearArea();
        }
        if(zombieCount >= zombieCountMax && zombieKilledCount >= zombieCount) {
            eImage.setVisible(true);
            MyGdxGame.getInstance().stopWaveMusic();
            MyGdxGame.getInstance().playBarMusic();
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(uiStage);
        MyGdxGame.getInstance().playWaveMusic();
    }

    @Override
    public void render(float _delta) {
        final float delta;
        if(_delta > MAX_DELTA_TIME) {
            delta = MAX_DELTA_TIME;
        } else {
            delta = _delta;
        }
        playerWalkAnimationTime += delta;
        if(hammerAnimationTime < HAMMER_ANIMATION_TIME) {
            hammerAnimationTime += delta;
            if(hammerAnimationTime >= HAMMER_ANIMATION_TIME) {
                hammerHit = true;
                if(playerRight) {
                    MyGdxGame.getInstance().getHitParticleEffectActor()
                        .setPosition(playerGroup.getX(Align.center)
                                + HIT_PARTICLES_DISTANCE,
                                groundImage.getTop());
                } else {
                    MyGdxGame.getInstance().getHitParticleEffectActor()
                        .setPosition(playerGroup.getX(Align.center)
                                - HIT_PARTICLES_DISTANCE,
                                groundImage.getTop());
                }
                MyGdxGame.getInstance().getHitParticleEffectActor().start();
                hammerReturnAnimationTime = hammerAnimationTime
                    - HAMMER_ANIMATION_TIME;
            }
        } else if(hammerReturnAnimationTime < HAMMER_RETURN_ANIMATION_TIME) {
            hammerReturnAnimationTime += delta;
        }
        if(playerVelocityAction.getVelocityX() == 0f) {
            if(playerWalks) {
                playerWalks = false;
                MyGdxGame.getInstance().stopStepMusic();
            }
        } else if(!playerWalks) {
            playerWalks = true;
            MyGdxGame.getInstance().playStepMusic();
        }
        zombieSpawnTime += delta;
        while(zombieCount < zombieCountMax
                && zombieSpawnTime >= ZOMBIE_SPAWN_INTERVAL) {
            final ZombieStateHolder zombieState = new ZombieStateHolder();
            zombieState.value = ZombieState.UP;
            final BooleanHolder zombieRight = new BooleanHolder();
            zombieRight.value = true;

            final FloatHolder zombieAnimationTime = new FloatHolder();

            final Group zombieGroup = new Group();
            zombieGroup.setSize(ZOMBIE_SIZE, ZOMBIE_SIZE);
            zombieGroup.setPosition(
                    MathUtils.random(0f,
                        gameGroup.getWidth() - zombieGroup.getWidth()),
                    0f,
                    Align.left | Align.top);
            final VelocityAction zombieVelocityAction =
                Actions.action(VelocityAction.class);
            final AccelerationAction zombieAccelerationAction =
                Actions.action(AccelerationAction.class);
            zombieAccelerationAction.setAccelerationY(-GRAVITY);
            zombieAccelerationAction.setVelocityAction(zombieVelocityAction);
            final OverlapAction zombieStopOverlapAction =
                Actions.action(OverlapAction.class);
            final StopOverlapListener zombieStopOverlapListener =
                new StopOverlapListener();
            zombieStopOverlapListener.setListener(
                    new StopOverlapListener.Listener() {
                        @Override
                        public boolean onOverlap(Actor actor) {
                            return actor == groundImage;
                        }
                    });
            zombieStopOverlapListener.setVelocityAction(zombieVelocityAction);
            final RunnableAction zombieControlAction =
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(playerGroup.getX(Align.center)
                                > zombieGroup.getX(Align.center)) {
                            zombieRight.value = true;
                            zombieVelocityAction.setVelocityX(ZOMBIE_VELOCITY);
                        } else {
                            zombieRight.value = false;
                            zombieVelocityAction.setVelocityX(-ZOMBIE_VELOCITY);
                        }
                    }
                });
            final RepeatAction zombieControlRepeatAction =
                Actions.forever(zombieControlAction);
            zombieStopOverlapAction.setListener(zombieStopOverlapListener);
            final RepeatAction zombieStopOverlapRepeatAction =
                Actions.forever(zombieStopOverlapAction);
            final OutOfBoundsAction zombieOutOfBoundsAction =
                Actions.action(OutOfBoundsAction.class);
            zombieOutOfBoundsAction
                .setListener(new RemoveOutOfBoundsListener());
            final OverlapAction zombieHammerOverlapAction =
                Actions.action(OverlapAction.class);
            final RepeatAction zombieHammerOverlapRepeatAction =
                Actions.forever(zombieHammerOverlapAction);
            final OverlapAction zombiePlayerOverlapAction =
                Actions.action(OverlapAction.class);
            final RepeatAction zombiePlayerOverlapRepeatAction =
                Actions.forever(zombiePlayerOverlapAction);
            final SequenceAction zombieSequenceAction = Actions.sequence(
                    Actions.moveBy(
                        0f,
                        groundImage.getTop() - zombieGroup.getY(),
                        ZOMBIE_EXIT_TIME),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            zombieGroup.addAction(
                                    Actions.forever(
                                        zombieAccelerationAction));
                            zombieGroup.addAction(
                                    zombieControlRepeatAction);
                            zombieGroup.addAction(
                                    Actions.forever(zombieVelocityAction));
                            zombieGroup.addAction(
                                    zombieStopOverlapRepeatAction);
                            zombieGroup.addAction(
                                    Actions.forever(
                                        zombieOutOfBoundsAction));
                            zombieGroup.addAction(
                                    zombiePlayerOverlapRepeatAction);
                            zombieState.value = ZombieState.ACTIVE;
                            zombieAnimationTime.value = 0f;
                        }
                    }));
            zombieHammerOverlapAction.setListener(new OverlapAction.Listener() {
                @Override
                public boolean onOverlap(Actor actor) {
                    if(hammerHit && actor == hammerTriggerActor) {
                        killZombie(zombieState,
                                zombieGroup,
                                zombieVelocityAction,
                                zombieAccelerationAction,
                                zombieControlRepeatAction,
                                zombieStopOverlapRepeatAction,
                                zombieHammerOverlapRepeatAction,
                                zombiePlayerOverlapRepeatAction,
                                zombieSequenceAction);

                        final LootType lootType = LootType.values()[
                            MathUtils.random(0, LootType.values().length - 1)];

                        final Image lootImage = new Image(
                                MyGdxGame.getInstance().getSkin(),
                                "loot/"
                                + lootType);
                        lootImage.setSize(LOOT_SIZE, LOOT_SIZE);
                        lootImage.setPosition(
                                zombieGroup.getX(Align.center),
                                zombieGroup.getY(Align.center),
                                Align.center);
                        final VelocityAction lootVelocityAction =
                            Actions.action(VelocityAction.class);
                        lootVelocityAction.setVelocityY(LOOT_IMPULSE);
                        final AccelerationAction lootAccelerationAction =
                            Actions.action(AccelerationAction.class);
                        lootAccelerationAction.setAccelerationY(-GRAVITY);
                        lootAccelerationAction.setVelocityAction(
                                lootVelocityAction);
                        lootImage.addAction(
                                Actions.forever(lootAccelerationAction));
                        lootImage.addAction(
                                Actions.forever(lootVelocityAction));
                        final OverlapAction lootStopOverlapAction =
                            Actions.action(OverlapAction.class);
                        final StopOverlapListener lootStopOverlapListener =
                            new StopOverlapListener();
                        lootStopOverlapListener.setListener(
                                new StopOverlapListener.Listener() {
                                    @Override
                                    public boolean onOverlap(Actor actor) {
                                        return actor == groundImage;
                                    }
                                });
                        lootStopOverlapListener.setVelocityAction(
                                lootVelocityAction);
                        lootStopOverlapAction.setListener(
                                lootStopOverlapListener);
                        lootImage.addAction(
                                Actions.forever(lootStopOverlapAction));
                        final OverlapAction lootPlayerOverlapAction =
                            Actions.action(OverlapAction.class);
                        lootPlayerOverlapAction.setListener(
                                new OverlapAction.Listener() {
                                    @Override
                                    public boolean onOverlap(Actor actor) {
                                        if(actor == playerGroup) {
                                            lootImage.clear();
                                            lootImage.remove();
                                            inventory.add(lootType);
                                            setInventoryPage(inventoryPage);
                                            return true;
                                        }
                                        return false;
                                    }
                                });
                        lootImage.addAction(
                                Actions.forever(lootPlayerOverlapAction));
                        gameGroup.addActor(lootImage);
                        return true;
                    }
                    return false;
                }
            });
            zombiePlayerOverlapAction.setListener(new OverlapAction.Listener() {
                @Override
                public boolean onOverlap(Actor actor) {
                    if(actor == playerGroup) {
                        playerHealth -= ZOMBIE_DAMAGE;
                        killZombie(zombieState,
                                zombieGroup,
                                zombieVelocityAction,
                                zombieAccelerationAction,
                                zombieControlRepeatAction,
                                zombieStopOverlapRepeatAction,
                                zombieHammerOverlapRepeatAction,
                                zombiePlayerOverlapRepeatAction,
                                zombieSequenceAction);
                        return true;
                    }
                    return false;
                }
            });
            zombieGroup.addAction(zombieSequenceAction);
            zombieGroup.addAction(zombieHammerOverlapRepeatAction);

            final Image zombieImage = new Image(
                    MyGdxGame.getInstance().getSkin(), "zombie/stand");
            zombieImage.setHeight(zombieGroup.getHeight());
            zombieImage.addAction(Actions.forever(Actions.run(new Runnable() {
                @Override
                public void run() {
                    zombieAnimationTime.value += delta;
                    switch(zombieState.value) {
                        case UP:
                            zombieImage.setDrawable(zombieUpAnimation
                                    .getKeyFrame(zombieAnimationTime.value));
                            break;
                        case ACTIVE:
                            zombieImage.setDrawable(zombieWalkAnimation
                                    .getKeyFrame(zombieAnimationTime.value));
                            break;
                        case DOWN:
                            zombieImage.setDrawable(
                                    MyGdxGame.getInstance().getSkin(),
                                    "zombie/down");
                            break;
                    }
                    if(zombieRight.value) {
                        zombieImage.setX(0f);
                        zombieImage.setWidth(zombieGroup.getWidth());
                    } else {
                        zombieImage.setX(zombieGroup.getWidth());
                        zombieImage.setWidth(-zombieGroup.getWidth());
                    }
                }
            })));
            zombieGroup.addActor(zombieImage);

            gameGroup.addActor(zombieGroup);

            zombieCount++;
            zombieSpawnTime -= ZOMBIE_SPAWN_INTERVAL;
        }
        uiStage.act(delta);
        hammerHit = false;
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
        MyGdxGame.getInstance().stopWaveMusic();
        MyGdxGame.getInstance().stopBarMusic();
    }

    @Override
    public void dispose() {
        uiStage.dispose();
    }
}
