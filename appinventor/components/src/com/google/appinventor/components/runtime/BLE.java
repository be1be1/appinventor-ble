package com.google.appinventor.components.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.YaVersion;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.content.Context;
import android.app.Activity;


/**
 * BLE provides scanning BLE device and connection
 *
 * By Tony Chan @ PolyU (kwong3513@yahoo.com.hk)
 */
@DesignerComponent(version = YaVersion.BLE_COMPONENT_VERSION,
   description = "Add it later",
   category = ComponentCategory.CONNECTIVITY,
   nonVisible = true,
   iconName = "images/ble.png")
@SimpleObject
@UsesPermissions(permissionNames =
				"android.permission.BLUETOOTH, " +
				"android.permission.BLUETOOTH_ADMIN")

public class BLE extends AndroidNonvisibleComponent implements Component{
	
  //-----------basic-------------------------
  private BluetoothAdapter mBluetoothAdapter;
  private final Activity activity;
  private BluetoothGatt mBluetoothGatt;
  private BluetoothGattService currentser;
  private BluetoothGattCharacteristic currentchar;
  private int device_rssi=0;
  private Handler uiThread;
  private int selectedIndex=0;
  
  //----------list-------------------------
  private String deviceAddressList="";
  private List<BluetoothDevice> mLeDevices;
  private List<BluetoothGattService> mGattService;
  private HashMap<BluetoothDevice, Integer> mLeDevices_rssi;
  
  //---------status------------------------
  private static boolean STATE_CONNECTED = false;
  private static boolean STATE_CHARREAD = false;
  private static boolean STATE_SERREAD = false;
  
  /*//----------for future use---------------
  public final static String ACTION_GATT_CONNECTED =
          "com.example.bluetooth.le.ACTION_GATT_CONNECTED";
  public final static String ACTION_GATT_DISCONNECTED =
          "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED";
  public final static String ACTION_GATT_SERVICES_DISCOVERED =
          "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED";
  public final static String ACTION_DATA_AVAILABLE =
          "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
  public final static String EXTRA_DATA =
          "com.example.bluetooth.le.EXTRA_DATA";
  private BluetoothGatt mBluetoothGatt;
  private String message="";*/
  
  //---------------Value------------------
  private int battery=0;
  private float temperature=0;
  private int find_me=0;
  private int set_find_me=0;
  
  
  
  /**
   * Later
   *
   * Later
   *
   * @param container container, component will be placed in
   */
  public BLE(ComponentContainer container) {
	  super(container.$form());
	  activity = container.$context();
	  mLeDevices=new ArrayList<BluetoothDevice>();
	  mGattService=new ArrayList<BluetoothGattService>();
	  //mChar=new ArrayList<BluetoothGattCharacteristic>();
	  final BluetoothManager bluetoothManager =(BluetoothManager)activity.getSystemService(Context.BLUETOOTH_SERVICE);
      mBluetoothAdapter = bluetoothManager.getAdapter();
      mLeDevices_rssi=new HashMap<BluetoothDevice, Integer>();
      uiThread=new Handler();
  }

//-----------------------------block-------------------------------------------------------------
  /**
   * Later
   */
  @SimpleFunction
  public void ScanDevice() {
	  uiThread.postDelayed(new Runnable() {
          @Override
          public void run() {
        		  mBluetoothAdapter.startLeScan(mLeScanCallback);  		  
          }
      }, 1000);
	  
	 // mBluetoothAdapter.startLeScan(mLeScanCallback);
  }
  
  /**
   * Later
   */
  @SimpleFunction
  public void ConnectDevice(int index) {
	  mBluetoothGatt=mLeDevices.get(index-1).connectGatt(activity, false, mGattCallback);
  }
  
  @SimpleFunction
  public void ReadFindMeValue() {
	  readChar(BLEList.FINDME_CHAR, BLEList.FINDME_SER);
  }
  
  @SimpleFunction
  public void SetFindMeValue(int value) {
	  if(value<=2&&value>=0)
	  {
		  set_find_me=value;
		  mBluetoothGatt.writeCharacteristic(currentchar);
	  }
  }

  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String GetFindMeValue() {
	  if(STATE_CHARREAD)
	  {
		  return Integer.toString(find_me);
	  }
	  else
		  return "Cannot read Find Me Value";
  }
  
  @SimpleFunction
  public void ReadBatteryValue() {
	  readChar(BLEList.BATTERY_LEVEL_CHAR, BLEList.BATTERY_LEVEL_SER);
  }

  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String GetBatteryValue() {
	  if(STATE_CHARREAD)
	  {
		  return Integer.toString(battery);
	  }
	  else
		  return "Cannot read Battery Level";
  }
  
  @SimpleFunction
  public void ReadTemperature() {
	  readChar(BLEList.THERMOMETER_CHAR, BLEList.THERMOMETER_SER);
  }

  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String IsBLEConnected() {
	  if(STATE_CONNECTED)
		  return "True";
	  else
		  return "False";
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String GetTemperature() {
	  if(STATE_CHARREAD)
	  {
		  return Float.toString(temperature);
	  }
	  else
		  return "Cannot read Temperature";
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String DeviceList()
  {
	  deviceAddressList="";
	  List<BluetoothDevice> sortList=new ArrayList<BluetoothDevice>();
	  sortList=mLeDevices;
	  mLeDevices=sorting(sortList);
	  if(!mLeDevices.isEmpty())
	  {
		  for(int i=0;i<mLeDevices.size();i++){
			  if(i!=(mLeDevices.size()-1))
				  deviceAddressList+=mLeDevices.get(i).toString()+" "+mLeDevices.get(i).getName()+" "+Integer.toString(mLeDevices_rssi.get(mLeDevices.get(i)))+",";
			  else
				  deviceAddressList+=mLeDevices.get(i).toString()+" "+mLeDevices.get(i).getName()+" "+Integer.toString(mLeDevices_rssi.get(mLeDevices.get(i)));;
	        }
	  }
	  return deviceAddressList;
  }
  
  @SimpleFunction
  public void SelectedDevice(int i) {
	  if(i<mLeDevices.size())
	  {
		  selectedIndex=(i-1);
	  }
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String SelectedDeviceRssi()
  {
	  return Integer.toString(mLeDevices_rssi.get(mLeDevices.get(selectedIndex)));
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String SelectedDeviceName()
  {
	  return mLeDevices.get(selectedIndex).getName();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String SelectedDeviceAddress()
  {
	  return mLeDevices.get(selectedIndex).getAddress();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public String ConnectedDeviceRssi()
  {
	  return Integer.toString(device_rssi);
  }
  

  @SimpleEvent(description = "")
  public void Connected() {
	  EventDispatcher.dispatchEvent(this, "Connected");
	  }
  
  @SimpleEvent(description = "")
  public void ValueChanged() {
	  /*uiThread.post(new Runnable(){
			@Override
			public void run() {*/
				EventDispatcher.dispatchEvent(this, "ValueChanged");
			//}
			  
		  //});
	  }
  
  @SimpleEvent(description = "")
  public void RssiChanged() {
	  /*uiThread.postDelayed(new Runnable(){
		@Override
		public void run() {*/
			EventDispatcher.dispatchEvent(this, "RssiChanged");
		//}
		  
	  //}, 1000);
  }
  
  //have not tested
  @SimpleEvent(description = "")
  public void ValueRead() {
	  EventDispatcher.dispatchEvent(this, "ValueRead");
  }
  
  
  @SimpleEvent(description = "")
  public void ConnectedDeviceRssiChanged() {
	  uiThread.postDelayed(new Runnable(){
		@Override
		public void run() {
			EventDispatcher.dispatchEvent(BLE.this, "ConnectedDeviceRssiChanged");
			mBluetoothGatt.readRemoteRssi();
		}
		  
	  }, 1000);
	  
	/*  Runnable runnable=new Runnable(){
		   @Override
		   public void run() {
			   uiThread.postDelayed(this, 2000);
		   } 
		};*/
	  }
  
//-------------------------------------------------------------------------------------------------

//-------------------------function-----------------------------------------------------------------
  //sort the device list by RSSI*******
  private List<BluetoothDevice> sorting(List<BluetoothDevice> input)
  {
	  List<BluetoothDevice> deviceList=new ArrayList<BluetoothDevice>();
	  BluetoothDevice small;
	  for(int i=0;i<input.size();i++)
	  {
		  small=input.get(i);
		  if(mLeDevices_rssi.containsKey(small))
		  {
			  for(int j=0;j<input.size();j++)
			  {
				  BluetoothDevice bl2=input.get(j);
				  if(!small.equals(bl2))
				  {
					  if(mLeDevices_rssi.get(small)<=mLeDevices_rssi.get(bl2))
					  {
						  small=bl2;
					  }
				  }
			  }
			  deviceList.add(small);
			  input.remove(small);
		  }
	  }
	  return deviceList;
  }
  
  //add device when scanning
   private void addDevice(BluetoothDevice device, int rssi) {
       if(!mLeDevices.contains(device)) {
           mLeDevices.add(device);
         }
       mLeDevices_rssi.put(device, rssi);
       RssiChanged();
    	   
   }
  
   //read characteristic based on UUID
   private void readChar(UUID char_uuid, UUID ser_uuid)
   {
	   if(STATE_SERREAD&&!mGattService.isEmpty())
		   for(int i=0;i<mGattService.size();i++)
		   {
			   BluetoothGattService check=mGattService.get(i);
			   if(check.getUuid().equals(ser_uuid))
			   {
				   currentser=check;
				   currentchar=currentser.getCharacteristic(char_uuid);
				   mBluetoothGatt.readCharacteristic(currentchar);
				   
			   }
		   }
   }
//--------------------------------------------------------------------------------------------
   
//-----------------------------------callback-------------------------------------------------
  //scan callback
  private BluetoothAdapter.LeScanCallback mLeScanCallback =
          new BluetoothAdapter.LeScanCallback() {
      @Override
      public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord) {
    	  activity.runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  addDevice(device, rssi);
              }
          });
      }
  };
  
  
  // for connect***********************
 private final BluetoothGattCallback mGattCallback =
          new BluetoothGattCallback() {
      @Override
      public void onConnectionStateChange(BluetoothGatt gatt, int status,
              int newState) {
          if (newState == BluetoothProfile.STATE_CONNECTED) {
        	  STATE_CONNECTED=true;
        	  mBluetoothGatt.discoverServices();
        	  mBluetoothGatt.readRemoteRssi();
        	  Connected();
          }
          //else show a message
      }
      
      @Override
      // New services discovered
      public void onServicesDiscovered(BluetoothGatt gatt, int status) {
    	  if (status == BluetoothGatt.GATT_SUCCESS) {
    		  mGattService= (ArrayList<BluetoothGattService>) gatt.getServices();
    		  STATE_SERREAD=true;
              //broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
          }
    	  //else show am message
      }

      @Override
      // Result of a characteristic read operation
      public void onCharacteristicRead(BluetoothGatt gatt,
              BluetoothGattCharacteristic characteristic,
              int status) {
    	  if (status == BluetoothGatt.GATT_SUCCESS) {
    		  if(characteristic.getUuid().equals(BLEList.BATTERY_LEVEL_CHAR))
    		  {
	    		  battery = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
	              STATE_CHARREAD=true;
	              ValueRead();
    		  }
    		  else if(characteristic.getUuid().equals(BLEList.THERMOMETER_CHAR))
    		  {
    			  temperature = characteristic.getFloatValue(BluetoothGattCharacteristic.FORMAT_FLOAT, 0);
	              STATE_CHARREAD=true;
	              ValueRead();
    		  }
    		  else if(characteristic.getUuid().equals(BLEList.FINDME_CHAR))
    		  {
    			  temperature = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
                  STATE_CHARREAD=true;
                  ValueRead();
    		  }
    		  //else get the value[].. add it later
    	  }
    	  //else show am message
      }
      
      @Override
      //Result of a characteristic read operation is changed
      public void onCharacteristicChanged (BluetoothGatt gatt, BluetoothGattCharacteristic characteristic)
      {
    	  if(characteristic.getUuid().equals(BLEList.BATTERY_LEVEL_CHAR))
		  {
    		  battery = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
              STATE_CHARREAD=true;
              ValueChanged();
		  }
		  else if(characteristic.getUuid().equals(BLEList.THERMOMETER_CHAR))
		  {
			  temperature = characteristic.getFloatValue(BluetoothGattCharacteristic.FORMAT_FLOAT, 0);
              STATE_CHARREAD=true;
              ValueChanged();
		  }
		  else if(characteristic.getUuid().equals(BLEList.FINDME_CHAR))
		  {
			  temperature = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
              STATE_CHARREAD=true;
              ValueChanged();
		  }
      }
      
      @Override
      //set value of characteristic
      public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
      {
    	  if(characteristic.getUuid().equals(BLEList.FINDME_CHAR))
    	  {
    		  characteristic.setValue(set_find_me, BluetoothGattCharacteristic.FORMAT_UINT8, 0);
    	  }
    	  //add other characteristic later
      }
      
      @Override
      //get the RSSI
      public void onReadRemoteRssi (BluetoothGatt gatt, int rssi, int status)
      {
    		  device_rssi=rssi;
    		  ConnectedDeviceRssiChanged();
    	  
      }
  };
}
//-------------------------------------------------------------------------------------

