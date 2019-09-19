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
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Sphere;
import com.jme3.texture.Texture;

/**
 *
 * @author blagi
 */
public class BallShoot {
    Material ballMat;
    
  private RigidBodyControl    ballPhy;
  private static final Sphere sphere;
  
  private static int ballGravity=-5;
  private static int ballSpeed=20;
    
  static BitmapText  ballPhyParametrs;
  
    static{
    /** Initialize the cannon ball geometry */
    sphere = new Sphere(32, 32, 0.4f, true, false);
    sphere.setTextureMode(Sphere.TextureMode.Projected);
    }
    
    BallShoot(BitmapText ballPhyParametrs, AssetManager assetManager){
        BallShoot.ballPhyParametrs=ballPhyParametrs;
        initMaterial(assetManager);
    }
    
    private void initMaterial(AssetManager assetManager){
        ballMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
        key.setGenerateMips(true);
        Texture tex = assetManager.loadTexture(key);
        ballMat.setTexture("ColorMap", tex);
    }
    
    public void newAction(String name, Node rootNode, Camera cam, BulletAppState bulletAppState){
        switch (name) {
            case "shoot":
                newShoot(rootNode, cam, bulletAppState);
                break;
            case "Gravity++":
                if(ballGravity!=0)ballGravity++;
                rewriteBallPhy();
                break;
            case "Gravity--":
                ballGravity--;
                rewriteBallPhy();
                break;
            case "Speed++":
                ballSpeed++;
                rewriteBallPhy();
                break;
            case "Speed--":
                ballSpeed--;
                rewriteBallPhy();
                break;
            default:
                break;
        }
    }
    
    private void newShoot(Node rootNode, Camera cam, BulletAppState bulletAppState){
        Geometry ball_geo = new Geometry("cannon ball", sphere);
        ball_geo.setMaterial(ballMat);
        rootNode.attachChild(ball_geo);
        /** Position the cannon ball  */
        ball_geo.setLocalTranslation(cam.getLocation());
        /** Make the ball physcial with a mass > 0.0f */
        ballPhy = new RigidBodyControl(20f);


        /** Add physical ball to physics space. */
        ball_geo.addControl(ballPhy);
        bulletAppState.getPhysicsSpace().add(ballPhy); 

        ballPhy.setGravity(new Vector3f(0, ballGravity, 0)); 
        /** Accelerate the physcial ball to shoot it. */
        ballPhy.setLinearVelocity(cam.getDirection().mult(ballSpeed));
    }
    
    protected static void rewriteBallPhy(){
      ballPhyParametrs.setText("gravity: "+ballGravity+";  speed: "+ballSpeed+";"); 
    }
}
