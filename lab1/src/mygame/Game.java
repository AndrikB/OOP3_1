package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Sphere;


/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Game extends SimpleApplication {

  private BulletAppState bulletAppState;
   
     
  private static final Sphere sphere;
 
  private BallShoot ball;
  
  BitmapText ballPhyParametrs;

       
  static{
      /** Initialize the cannon ball geometry */
    sphere = new Sphere(32, 32, 0.4f, true, false);
    sphere.setTextureMode(Sphere.TextureMode.Projected);
  }
     
    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        cam.setLocation(new Vector3f(0, 4f, 0));
        flyCam.setMoveSpeed(30);
        
        ballPhyParametrs=new BitmapText(assetManager.loadFont("Interface/Fonts/Default.fnt"), false);

        CreateFloor creator=new CreateFloor(rootNode, bulletAppState, assetManager);
        KeyInputControl.add_keyTrigger_control(inputManager, actionListener);
        ball=new BallShoot(ballPhyParametrs, assetManager);
        
        CreateFloor.createTextFields(guiFont, assetManager, settings, guiNode);
    }
    
    
    @SuppressWarnings("FieldMayBeFinal")
    private ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean keyPressed, float tpf) {
        
        if (!keyPressed){
            ball.newAction(name, rootNode, cam, bulletAppState);
        }
    }
  };

}
