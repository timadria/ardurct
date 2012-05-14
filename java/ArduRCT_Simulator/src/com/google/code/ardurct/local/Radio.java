package com.google.code.ardurct.local;

import com.google.code.ardurct.libraries.Eeprom;
import com.google.code.ardurct.libraries.XBee;
import com.google.code.ardurct.libraries.touchscreen.ArduinoFirmware;

public class Radio extends ArduinoFirmware {
	
	public static final int RADIO_MAX_CONNECTION_RETRIES = 100;
	
	public static final int LOCAL_ADDRESS = 30;
	public static final int REMOTE_1_ADDRESS = 31;
	public static final int REMOTE_2_ADDRESS = 32;
	public static final int REMOTE_3_ADDRESS = 33;

	public static final int RADIO_STATUS_ERROR = -1;
	public static final int RADIO_STATUS_BAUDRATE_ERROR = -2;
	public static final int RADIO_STATUS_CONNECTION_ERROR = -3;
	public static final int RADIO_STATUS_UNDEFINED = 0;
	public static final int RADIO_STATUS_FINALIZING = 110;
	public static final int RADIO_STATUS_READY = 100;
	public static final int RADIO_STATUS_ENTERING_COMMAND_MODE = 1;
	public static final int RADIO_STATUS_GETTING_PAN_ID = 6;
	public static final int RADIO_STATUS_CHECKING_ADDRESS = 10;
	public static final int RADIO_STATUS_GETTING_LOCAL_ADDRESS = 15;
	public static final int RADIO_STATUS_GETTING_REMOTE_ADDRESS = 20;
	public static final int RADIO_STATUS_CONFIGURING_MODULE = 25;
	public static final int RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_REMOTE = 35;
	public static final int RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_LOCAL = 36;
	public static final int RADIO_STATUS_SEND_TEST_TO_REMOTE = 90;
	public static final int RADIO_STATUS_RECEIVE_TEST_FROM_REMOTE = 95;
	
 	static final int RADIO_CONFIG_INIT = 0;
	static final int RADIO_CONFIG_ENTER_CMD_MODE = 10;
	static final int RADIO_CONFIG_GET_SHSL = 20;
	static final int RADIO_CONFIG_SET_BD = 30;
	static final int RADIO_CONFIG_SET_ID = 40;
	static final int RADIO_CONFIG_SET_DHDL = 50;
	static final int RADIO_CONFIG_SET_64B = 60;
	static final int RADIO_CONFIG_WRITE = 70;
	static final int RADIO_CONFIG_EXIT_CMD_MODE = 80;
	
	private static final int RADIO_LOCAL_EEPROM_ADDRESS = EEND-95;
	private static final int RADIO_REMOTE_1_EEPROM_ADDRESS = EEND-103;
	private static final int RADIO_REMOTE_2_EEPROM_ADDRESS = EEND-111;
	private static final int RADIO_REMOTE_3_EEPROM_ADDRESS = EEND-119;
		
	public XBee xBee = new XBee();
	
	public int radioStatus;
	public int radioConfigurationStatus;
	public int[] radioPanId = { '0', '0', '0', '0' };
	public int radioBaudrate;
	public int radioConnectionRetries;
	public int[] radioLocalAddress = new int[8];
	public int[] radioRemote1Address = new int[8];
	public int[] radioRemote2Address = new int[8];
	public int[] radioRemote3Address = new int[8];
	
	public void radioSetup(int baudrate) {
		xBee.begin(Serial1, baudrate);
	    radioStatus = RADIO_STATUS_UNDEFINED;
	    radioConfigurationStatus = 0;
	    radioConnectionRetries = 0;
	    radioBaudrate = baudrate;
	    readModuleAddressInEeprom(radioLocalAddress, RADIO_LOCAL_EEPROM_ADDRESS);
	    readModuleAddressInEeprom(radioRemote1Address, RADIO_REMOTE_1_EEPROM_ADDRESS);
	    readModuleAddressInEeprom(radioRemote2Address, RADIO_REMOTE_2_EEPROM_ADDRESS);
	    readModuleAddressInEeprom(radioRemote3Address, RADIO_REMOTE_3_EEPROM_ADDRESS);
	}
	
	public void radioProcess() {	
		int[] buffer = new int[20];
		
		if (radioStatus <= RADIO_STATUS_ERROR) return;

		// process pending commands
	    xBee.processCommand();
	    if (xBee.isProcessingCommand()) return;

	    switch (radioStatus) {
	    		
	    	case RADIO_STATUS_READY:
	    		radioProcessData();
	    		break;
	    		
	    	case RADIO_STATUS_UNDEFINED:
	    		xBee.enterCommandMode();
	    		radioStatus = RADIO_STATUS_ENTERING_COMMAND_MODE;
	    		break;
	    		
	    	case RADIO_STATUS_ENTERING_COMMAND_MODE:
	    		if (!xBee.isInCommandMode()) {
	    			radioStatus = RADIO_STATUS_BAUDRATE_ERROR;
	    			return;
	    		}
	    		xBee.getId();
	    		radioStatus = RADIO_STATUS_GETTING_PAN_ID;
	    		break;
	    		
	    	case RADIO_STATUS_GETTING_PAN_ID:
	    	    copyArray(xBee.getId(), radioPanId, 4);
	    		setPanIdLabels();
	    		xBee.getSHSL();
	    	    if (!radioAddressIsDefined(radioLocalAddress)){
	    	    	// never configured, get the local address
	    	    	radioStatus = RADIO_STATUS_GETTING_LOCAL_ADDRESS;
	    	    } else if (!radioAddressIsDefined(radioRemote1Address)){
	    	    	// remote radio not known, get its address
	    	    	radioStatus = RADIO_STATUS_GETTING_REMOTE_ADDRESS;
	    	    } else {
	    	    	// we test which radio is plugged in and maybe configure it
	    	    	radioStatus = RADIO_STATUS_CHECKING_ADDRESS;
	    	    }
	    		break;

	    	case RADIO_STATUS_GETTING_LOCAL_ADDRESS:
	    		hexStringToArray(xBee.getSHSL(), radioLocalAddress, 16);
	    		// save it for future use
	    		writeModuleAddressInEeprom(radioLocalAddress, RADIO_LOCAL_EEPROM_ADDRESS);
	    		// ask to switch the module
	    		radioStatus = RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_REMOTE;
	    		break;

	    	case RADIO_STATUS_GETTING_REMOTE_ADDRESS:
	    		hexStringToArray(xBee.getSHSL(), buffer, 16);
	    		if (arrayEqual(buffer, radioLocalAddress, 8)) {
	    			// ask to switch the module, as it is still the same
		    		radioStatus = RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_REMOTE;
		    		return;
	    		} 
    			// save it in the next slot available
	    		if (!radioAddressIsDefined(radioRemote1Address)) {
	    			copyArray(buffer, radioRemote1Address, 8);
	    			writeModuleAddressInEeprom(radioRemote1Address, RADIO_REMOTE_1_EEPROM_ADDRESS);
	    		} else if (!radioAddressIsDefined(radioRemote2Address)) {
	    			copyArray(buffer, radioRemote2Address, 8);
	    			writeModuleAddressInEeprom(radioRemote2Address, RADIO_REMOTE_2_EEPROM_ADDRESS);
	    		} else {
	    			copyArray(buffer, radioRemote3Address, 8);
	    			writeModuleAddressInEeprom(radioRemote3Address, RADIO_REMOTE_3_EEPROM_ADDRESS);	    			
	    		}
	    		// write the configuration
	    		radioStatus = RADIO_STATUS_CONFIGURING_MODULE;
	    		radioConfigurationStatus = RADIO_CONFIG_INIT;
	    		
	    	case RADIO_STATUS_CONFIGURING_MODULE:
	    		radioConfigureModule();
	    		break;
	    		
	    	case RADIO_STATUS_CHECKING_ADDRESS:
	    		hexStringToArray(xBee.getSHSL(), buffer, 16);
	    		if (arrayEqual(buffer, radioLocalAddress, 8)) {
	    			// we are on local XBee, check the configuration
	    			if (xBee.getDHDL() == null) return;
	    			hexStringToArray(xBee.getDHDL(), buffer, 16);
	    			if (arrayEqual(buffer, radioRemote1Address, 8)) {
		    			// if DHDL is remote, test the connection
	    				radioConnectionRetries = 0;
		    			radioStatus = RADIO_STATUS_SEND_TEST_TO_REMOTE;
		    			xBee.exitCommandMode();
		    		} else {
			    		radioStatus = RADIO_STATUS_CONFIGURING_MODULE;
			    		radioConfigurationStatus = RADIO_CONFIG_INIT;		    			
		    		}
	    		} else if (arrayEqual(buffer, radioRemote1Address, 8) || 
	    					arrayEqual(buffer, radioRemote2Address, 8) ||
	    					arrayEqual(buffer, radioRemote3Address, 8)) {
	    			radioStatus = RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_LOCAL;
	    		} else if (!radioAddressIsDefined(radioRemote2Address) || !radioAddressIsDefined(radioRemote3Address)) {
	    			// we are on a new remote, get its details
	    			radioStatus = RADIO_STATUS_GETTING_REMOTE_ADDRESS;
	    		} else {
	    			radioStatus = RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_LOCAL;
	    		}
	    		break;
	    	

	    	case RADIO_STATUS_SEND_TEST_TO_REMOTE:
	    		if ((radioConnectionRetries % 10) == 0) xBee.print("$TX\n");
	    		radioStatus = RADIO_STATUS_RECEIVE_TEST_FROM_REMOTE;
	    		break;
	    		
	    	case RADIO_STATUS_RECEIVE_TEST_FROM_REMOTE:
	    		radioConnectionRetries ++;
	    		radioStatus = RADIO_STATUS_CONNECTION_ERROR;
	    		if (xBee.available() >= 4) {
	    			if ((xBee.read() == '$') && (xBee.read() == 'R') && (xBee.read() == 'X') && (xBee.read() == '\n')) {
	    				radioStatus = RADIO_STATUS_FINALIZING;
	    			}
	    		}
	    		if ((radioStatus == RADIO_STATUS_CONNECTION_ERROR) && (radioConnectionRetries > RADIO_MAX_CONNECTION_RETRIES)) 
	    			radioStatus = RADIO_STATUS_ERROR;
	    		break;

	    	case RADIO_STATUS_FINALIZING:
	    	default:
	    		break;

	    }
	}

	public void radioConfigureModule() {
		int[] buffer = new int[20];
		
		switch (radioConfigurationStatus) {
			case RADIO_CONFIG_INIT:
				if (!xBee.isInCommandMode()) {
					xBee.enterCommandMode();
					return;
				}

			case RADIO_CONFIG_GET_SHSL:
				if (xBee.getSHSL() == null) return;
				radioConfigurationStatus = RADIO_CONFIG_SET_BD;
				xBee.setBaudrate(radioBaudrate);
				break;
				
			case RADIO_CONFIG_SET_BD:
				radioConfigurationStatus = RADIO_CONFIG_SET_ID;
				xBee.setId(radioPanId);
				break;
				
			case RADIO_CONFIG_SET_ID:
				radioConfigurationStatus = RADIO_CONFIG_SET_64B;
				xBee.enable64BitsAdressing();
				break;

			case RADIO_CONFIG_SET_64B:
				radioConfigurationStatus = RADIO_CONFIG_SET_DHDL;
				hexStringToArray(xBee.getSHSL(), buffer, 16);
				if (arrayEqual(buffer, radioLocalAddress, 8)) {
					// we are in local module, we have to set radioRemoteAddress_1 as DHDL
					arrayToHexString(radioRemote1Address, buffer, 8);
				} else {
					// we are in remote module, we have to set radioLocalAddress as DHDL
					arrayToHexString(radioLocalAddress, buffer, 8);					
				}
				xBee.setDHDL(buffer);
				break;

			case RADIO_CONFIG_SET_DHDL:
				radioConfigurationStatus = RADIO_CONFIG_WRITE;
				xBee.commitToXBeeFlash();
				break;

			case RADIO_CONFIG_WRITE:
				radioConfigurationStatus = RADIO_CONFIG_EXIT_CMD_MODE;
				xBee.exitCommandMode();
				break;

			case RADIO_CONFIG_EXIT_CMD_MODE:
				hexStringToArray(xBee.getSHSL(), buffer, 16);
				if (arrayEqual(buffer, radioRemote1Address, 8)) {
					radioStatus = RADIO_STATUS_REQUESTING_MODULE_SWITCH_TO_LOCAL;
				} else {
    				radioConnectionRetries = 0;
					radioStatus = RADIO_STATUS_SEND_TEST_TO_REMOTE;
				}
				break;
		}

	}
	
	public void radioProcessData() {	
	}
	
	public void setPanIdLabels() {
		// Overriden by ArduRCT_Local 
	}
	
	public void radioCommitChanges() {
		radioConfigurationStatus = RADIO_CONFIG_INIT;
		radioStatus = RADIO_STATUS_CONFIGURING_MODULE;
	}
	
	public void readModuleAddressInEeprom(int[] address, int eepromAddress) {
		for (int i=0; i<8; i++) address[i] = Eeprom.read_uint8_t(eepromAddress + i);
	}
	
	public void writeModuleAddressInEeprom(int[] address, int eepromAddress) {
		for (int i=0; i<8; i++) Eeprom.write_uint8_t(eepromAddress + i, address[i]);
	}
	
	public int[] arrayToHexString(int[] array, int[] hexString, int arraySize) {
		hexString[arraySize*2] = 0;
		for (int i=0; i<arraySize; i++) {
			hexString[i*2] = (array[i] >> 4) + '0';
			if (hexString[i*2] > '9') hexString[i*2] += 'A' - '0' - 10;
			hexString[i*2+1] = (array[i] & 0x0F) + '0';
			if (hexString[i*2+1] > '9') hexString[i*2+1] += 'A' - '0' - 10;
		}
		return hexString;
	}
	
	public void hexStringToArray(int[] hexString, int[] array, int hexSize) {
		for (int i=0; i<hexSize/2; i++) {
			array[i] = hexString[i*2] - (hexString[i*2] > '9' ? 'A'-10 : '0');
			array[i] = (array[i] << 4) + hexString[i*2+1] - (hexString[i*2+1] > '9' ? 'A'-10 : '0');
		}
	}
	
	public boolean arrayEqual(int[] array1, int[] array2, int size) {
		for (int i=0; i<size; i++) {
			if (array1[i] != array2[i]) return false;
		}
		return true;
	}

	public boolean radioAddressIsDefined(int[] address) {
		for (int i=0; i<8; i++) {
			if (address[i] != 0xFF) return true;
		}
		return false;
	}
	
	public boolean copyArray(int[] array1, int[] array2, int size) {
		for (int i=0; i<size; i++) array2[i] = array1[i];
		return true;
	}

	public void setup() {}
	public void loop() { }

}
