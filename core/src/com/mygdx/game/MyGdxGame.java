package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.xml.soap.Text;
import java.awt.*;

public class MyGdxGame extends ApplicationAdapter {
    private Label label[] = new Label[50];
    private Table scrollTable;
    private Table table;
    private ScrollPane scroller;
    private String longString = "Look, I was gonna go easy on you and not tell you to give up or do something but probably you don't want to listen";
    private Skin skin;
    private Stage stage;
    float scale = 1f;
    Font font;
    TextButton textButton;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("neon-ui.json"));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        font = new Font("Ariel", Font.BOLD, 10);


        Table root = new Table();
        root.setFillParent(true);
        root.bottom().right();
        stage.addActor(root);

        //Begin layout

        textButton = new TextButton("Increase Font", skin);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (scale <= 2) {
                    scale += 0.2f;
                    for (int i = 0; i < 50; i++)
                        label[i].setFontScale(scale);
                }
            }
        });
        root.add(textButton).align(Align.left);
        textButton = new TextButton("Decrease Font", skin);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (scale >= 1) {
                    scale -= 0.2f;
                    for (int i = 0; i < 50; i++)
                        label[i].setFontScale(scale);
                }
            }
        });
        root.add(textButton).align(Align.right);

        root.row();


        scrollTable = new Table();


        for (int i = 0; i < 50; i++) {
            scrollTable.row();
            label[i] = new Label(longString, skin);
            label[i].setWrap(true);
            label[i].setAlignment(Align.center);
            scrollTable.add(label[i]).minWidth(Gdx.graphics.getWidth() * 2 / 2);
        }
        scroller = new ScrollPane(scrollTable);
        root.add(scroller).height(Gdx.graphics.getHeight() / 2).colspan(2);

        stage.setDebugAll(true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.9f, .9f, .9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}