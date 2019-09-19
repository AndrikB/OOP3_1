/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import static mygame.BallShoot.ballPhyParametrs;

/**
 *
 * @author blagi
 */
public final class CreateFloor {
    
    
  private Box floor; 
  private RigidBodyControl floor_phy;
  Material floor_mat;
  Material stone_mat;


  
    /** Initialize the floor geometry */
   
    CreateFloor(Node rootNode, BulletAppState bulletAppState, AssetManager assetManager){
        initMaterial(assetManager);
        initFloor(rootNode, bulletAppState);
    }
    
    private void initMaterial(AssetManager assetManager){
        floor = new Box(100f, 0.1f, 100f);
        floor_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey keyFlor = new TextureKey("Textures/Terrain/Pond/Pond.jpg");
        keyFlor.setGenerateMips(true);
        Texture texFlor = assetManager.loadTexture(keyFlor);
        texFlor.setWrap(WrapMode.MirroredRepeat);
        floor_mat.setTexture("ColorMap", texFlor);
        
    }
    
    private void initFloor(Node rootNode, BulletAppState bulletAppState) {  
       
        Geometry floor_geo = new Geometry("Floor", floor);
        floor_geo.setMaterial(floor_mat);
        floor_geo.setLocalTranslation(0, -0.1f, 0);
        rootNode.attachChild(floor_geo);
        /* Make the floor physical with mass 0.0f! */
        floor_phy = new RigidBodyControl(0.0f);
        floor_geo.addControl(floor_phy);
        bulletAppState.getPhysicsSpace().add(floor_phy);
    }
    
    protected static void createTextFields(BitmapFont guiFont, AssetManager assetManager,AppSettings settings, Node guiNode){
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+");        // fake crosshairs :)
        ch.setLocalTranslation( // center
            settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
            settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    
        BallShoot.rewriteBallPhy();
        ballPhyParametrs.setLocalTranslation(300, ballPhyParametrs.getLineHeight(), 0);
        guiNode.attachChild(ballPhyParametrs);
        
    }
    
}
