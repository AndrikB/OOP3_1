/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

/**
 *
 * @author blagi
 */
public class KeyInputControl {
    
    protected static void add_keyTrigger_control(InputManager inputManager, ActionListener actionListener){
      /** Add InputManager action: Left click triggers shooting. */
    inputManager.addMapping("shoot",
            new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    inputManager.addListener(actionListener, "shoot");
    
    inputManager.addMapping("Gravity++",
            new KeyTrigger(KeyInput.KEY_G));
    inputManager.addListener(actionListener, "Gravity++");
    
    inputManager.addMapping("Gravity--",
            new KeyTrigger(KeyInput.KEY_J));
    inputManager.addListener(actionListener, "Gravity--");
      
    
    inputManager.addMapping("Speed++",
            new KeyTrigger(KeyInput.KEY_C));
    inputManager.addListener(actionListener, "Speed++");
    
    inputManager.addMapping("Speed--",
            new KeyTrigger(KeyInput.KEY_V));
    inputManager.addListener(actionListener, "Speed--");
  }
    
    
}
