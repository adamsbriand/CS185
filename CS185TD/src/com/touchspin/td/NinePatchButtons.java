package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class NinePatchButtons extends NinePatch {
        private static NinePatchButtons instance;
        
        private NinePatchButtons(){
                super(new Texture(Gdx.files.internal("img/menu/Btn9SliceUp.png")), 20, 20, 20, 20);
        }
        
        public static NinePatchButtons getInstance(){
                if(instance == null){
                        instance = new NinePatchButtons();
                }
                return instance;
        }
}