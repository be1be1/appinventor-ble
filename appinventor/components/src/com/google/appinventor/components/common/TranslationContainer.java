package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public class TranslationContainer {
  private Map<String, String> CompTransMap = new HashMap<String, String>();
  public TranslationContainer() {
        
  //Palette components name
    CompTransMap.put("Basic", "���本");
    CompTransMap.put("Media", "媒���");
    CompTransMap.put("Animation", "���画"); 
    CompTransMap.put("Social", "社交����");
    CompTransMap.put("Sensors", "传�������");
    CompTransMap.put("Screen Arrangement", "屏���布���");
    CompTransMap.put("LEGO\u00AE MINDSTORMS\u00AE", "乐������器人���件\u00AE");
    CompTransMap.put("Other stuff", "������东西");
    CompTransMap.put("Not ready for prime time", "测���中���套件");
    CompTransMap.put("Old stuff", "���������");
        
    //Basic
    CompTransMap.put("Button","���钮");
    CompTransMap.put("Canvas","������");
    CompTransMap.put("CheckBox","复������");
    CompTransMap.put("Clock","������");
    CompTransMap.put("Image","������");
    CompTransMap.put("Label","便签");
    CompTransMap.put("ListPicker","���表���择����");
    CompTransMap.put("PasswordTextBox","密������");
    CompTransMap.put("TextBox","���本���");
    CompTransMap.put("TinyDB","细������据���");
        
    //Media
    CompTransMap.put("Camcorder","����������");
    CompTransMap.put("Camera","���机");
    CompTransMap.put("ImagePicker","���������择����");
    CompTransMap.put("Player","���放����");
    CompTransMap.put("Sound","声音");
    CompTransMap.put("VideoPlayer","媒������放����");
        
        //Animation
    CompTransMap.put("Ball","����");
    CompTransMap.put("ImageSprite","������精灵");
        
    //Social
    CompTransMap.put("ContactPicker","���系信息���择����");
    CompTransMap.put("EmailPicker","���件���择����");
    CompTransMap.put("PhoneCall","������");
    CompTransMap.put("PhoneNumberPicker","���������������择����");
    CompTransMap.put("Texting","信息");
    CompTransMap.put("Twitter","Twitter");
        
    //Sensor        
    CompTransMap.put("AccelerometerSensor","������度������器");
    CompTransMap.put("LocationSensor","位置传�������");
    CompTransMap.put("OrientationSensor","������传�������");
        
    //Screen Arrangement
    CompTransMap.put("HorizontalArrangement", "水平������");
    CompTransMap.put("TableArrangement", "表�������");
    CompTransMap.put("VerticalArrangement", "竖���布置");
        
    //Lego Mindstorms
    CompTransMap.put("NxtColorSensor", "Nxt颜色传�������");
    CompTransMap.put("NxtDirectCommands", "Nxt���接���令");
    CompTransMap.put("NxtDrive", "Nxt驱动");
    CompTransMap.put("NxtLightSensor", "Nxt���������器");
    CompTransMap.put("NxtSoundSensor", "Nxt声音传�������");
    CompTransMap.put("NxtTouchSensor", "Nxt触摸传�������");
    CompTransMap.put("NxtUltrasonicSensor", "Nxt超声波������器");
        
    //Other stuff
    CompTransMap.put("ActivityStarter", "活动���动");
    CompTransMap.put("BarcodeScanner", "����������������");
    CompTransMap.put("BluetoothClient", "������客户");
    CompTransMap.put("BluetoothServer", "���������务����");
    CompTransMap.put("Notifier", "���������");
    CompTransMap.put("SpeechRecognizer", "语音识别");
    CompTransMap.put("TextToSpeech", "���本���语����");
    CompTransMap.put("TinyWebDB", "细���网������据���");
    CompTransMap.put("Web", "网���");
    CompTransMap.put("BLE", "BLE");
        
    //Not ready for prime time
    CompTransMap.put("FusiontablesControl","Fusiontables���制");
    CompTransMap.put("GameClient","游���客户���");
    CompTransMap.put("SoundRecorder","声音记�������");
    CompTransMap.put("Voting","���票");
    CompTransMap.put("WebViewer","网页浏�������");       
    };
    
    public String getCorrespondingString(String key) {
      if (CompTransMap.containsKey(key)) {
        return CompTransMap.get(key);
      } else {
        return "Missing name";
      }
    }    
}
