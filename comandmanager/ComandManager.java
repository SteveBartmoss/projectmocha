package comandmanager;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class ComandManager{

    KeyCombination keyCombCtrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    KeyCombination keyCombCtrlN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
    KeyCombination keyCombCtrlO = new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN);
    KeyCombination keyCombCtrlQ = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);

    public String comandListener(KEyEvent.KEY_PRESSED event){
        if(keyCombCtrlN.match(event)){
            return 'ctrlN';
        }
        if(keyCombCtrlO.match(event)){
            return 'ctrlO';
        }
        if(keyCombCtrlS.match(event)){
            return 'ctrlS'
        }
        if(keyCombCtrlQ.match(event)){
            return 'ctrlQ'
        }
        
    }
}
