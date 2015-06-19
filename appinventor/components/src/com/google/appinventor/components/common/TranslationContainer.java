package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public class TranslationContainer {
  private Map<String, String> CompTransMap = new HashMap<String, String>();
  public TranslationContainer() {
        
  //Palette components name
    CompTransMap.put("Basic", "şıºæœ¬");
    CompTransMap.put("Media", "åª’äşı");
    CompTransMap.put("Animation", "şı¨ç”»"); 
    CompTransMap.put("Social", "ç¤¾äº¤şışı");
    CompTransMap.put("Sensors", "ä¼ æşışışı");
    CompTransMap.put("Screen Arrangement", "å±åşıå¸ƒåşı");
    CompTransMap.put("LEGO\u00AE MINDSTORMS\u00AE", "ä¹éşışıºå™¨äººåşıä»¶\u00AE");
    CompTransMap.put("Other stuff", "şı¶äşıä¸œè¥¿");
    CompTransMap.put("Not ready for prime time", "æµ‹èşıä¸­çşıå¥—ä»¶");
    CompTransMap.put("Old stuff", "şı§äşıè¥şı");
        
    //Basic
    CompTransMap.put("Button","şı‰é’®");
    CompTransMap.put("Canvas","şı»åşı");
    CompTransMap.put("CheckBox","å¤éşıæ¡şı");
    CompTransMap.put("Clock","şı¶éşı");
    CompTransMap.put("Image","şı¾åşı");
    CompTransMap.put("Label","ä¾¿ç­¾");
    CompTransMap.put("ListPicker","şı—è¡¨şı‰æ‹©şışı");
    CompTransMap.put("PasswordTextBox","å¯†çşıæ¡şı");
    CompTransMap.put("TextBox","şı‡æœ¬æ¡şı");
    CompTransMap.put("TinyDB","ç»†åşışı°æ®åºşı");
        
    //Media
    CompTransMap.put("Camcorder","şı„åşışışı");
    CompTransMap.put("Camera","şı¸æœº");
    CompTransMap.put("ImagePicker","şı»åşışı‰æ‹©şışı");
    CompTransMap.put("Player","şı­æ”¾şışı");
    CompTransMap.put("Sound","å£°éŸ³");
    CompTransMap.put("VideoPlayer","åª’äşışı­æ”¾şışı");
        
        //Animation
    CompTransMap.put("Ball","şışı");
    CompTransMap.put("ImageSprite","şı¾çşıç²¾çµ");
        
    //Social
    CompTransMap.put("ContactPicker","şı”ç³»ä¿¡æ¯şı‰æ‹©şışı");
    CompTransMap.put("EmailPicker","şı®ä»¶şı‰æ‹©şışı");
    CompTransMap.put("PhoneCall","şıµèşı");
    CompTransMap.put("PhoneNumberPicker","şıµèşışı·çşışı‰æ‹©şışı");
    CompTransMap.put("Texting","ä¿¡æ¯");
    CompTransMap.put("Twitter","Twitter");
        
    //Sensor        
    CompTransMap.put("AccelerometerSensor","şı éşıåº¦äşışıŸå™¨");
    CompTransMap.put("LocationSensor","ä½ç½®ä¼ æşışışı");
    CompTransMap.put("OrientationSensor","şı¹åşıä¼ æşışışı");
        
    //Screen Arrangement
    CompTransMap.put("HorizontalArrangement", "æ°´å¹³şı’åşı");
    CompTransMap.put("TableArrangement", "è¡¨åşışışı");
    CompTransMap.put("VerticalArrangement", "ç«–åşıå¸ƒç½®");
        
    //Lego Mindstorms
    CompTransMap.put("NxtColorSensor", "Nxté¢œè‰²ä¼ æşışışı");
    CompTransMap.put("NxtDirectCommands", "Nxtşı´æ¥şı½ä»¤");
    CompTransMap.put("NxtDrive", "Nxté©±åŠ¨");
    CompTransMap.put("NxtLightSensor", "Nxtşı‰äşışıŸå™¨");
    CompTransMap.put("NxtSoundSensor", "Nxtå£°éŸ³ä¼ æşışışı");
    CompTransMap.put("NxtTouchSensor", "Nxtè§¦æ‘¸ä¼ æşışışı");
    CompTransMap.put("NxtUltrasonicSensor", "Nxtè¶…å£°æ³¢äşışıŸå™¨");
        
    //Other stuff
    CompTransMap.put("ActivityStarter", "æ´»åŠ¨şı¯åŠ¨");
    CompTransMap.put("BarcodeScanner", "şı¡çşışı«æşışışı");
    CompTransMap.put("BluetoothClient", "şıçşıå®¢æˆ·");
    CompTransMap.put("BluetoothServer", "şıçşışıåŠ¡şışı");
    CompTransMap.put("Notifier", "şıšåşıäºşı");
    CompTransMap.put("SpeechRecognizer", "è¯­éŸ³è¯†åˆ«");
    CompTransMap.put("TextToSpeech", "şı‡æœ¬şı°è¯­şışı");
    CompTransMap.put("TinyWebDB", "ç»†åşıç½‘çşışı°æ®åºşı");
    CompTransMap.put("Web", "ç½‘çşı");
    CompTransMap.put("BLE", "BLE");
        
    //Not ready for prime time
    CompTransMap.put("FusiontablesControl","Fusiontablesşı§åˆ¶");
    CompTransMap.put("GameClient","æ¸¸æşıå®¢æˆ·ç«şı");
    CompTransMap.put("SoundRecorder","å£°éŸ³è®°åşışışı");
    CompTransMap.put("Voting","şı•ç¥¨");
    CompTransMap.put("WebViewer","ç½‘é¡µæµèşışışı");       
    };
    
    public String getCorrespondingString(String key) {
      if (CompTransMap.containsKey(key)) {
        return CompTransMap.get(key);
      } else {
        return "Missing name";
      }
    }    
}
