/*
 * XBee - Configuration utilities for XBee through AT command set
 *
 * Copyright (c) 2011 Laurent Wibaux <lm.wibaux@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
 
/**
 *	Versions
 *		1.0		Adapted to Arduino 1.0
 *		0.1		Initial revision
 **/ 
 
#ifndef XBEE_H
#define XBEE_H

#include <inttypes.h>
#include "Arduino.h"
#include "Print.h"

#include "XBee_Configuration.hpp"

#ifdef XBEE_CONFIG_USE_SOFTWARE_SERIAL
#include "../SoftwareSerial/SoftwareSerial.h"
#endif


#define XBEE_BUFFER_LENGTH (8+2)

#define XBEE_INVALID 0xFF

class XBee : public Print {
	public:
		// constructor
		XBee();
		
		// begin by attaching a Serial or a NewSoftSerial
		void begin(HardwareSerial *serial, uint32_t baudrate = XBEE_DEFAULT_BAUD_RATE, uint16_t guardTime = XBEE_DEFAULT_GUARD_TIME);   
#ifdef XBEE_CONFIG_USE_SOFTWARE_SERIAL 
		void begin(SoftwareSerial *softSerial, uint32_t baudrate = XBEE_DEFAULT_BAUD_RATE, uint16_t guardTime = XBEE_DEFAULT_GUARD_TIME);    
#endif
		// Print superclass requirement
		virtual size_t write(uint8_t chr);
		
		// Returns the number of bytes available in the queue
		uint8_t available();
		
		// Returns the next character received
		char read();
		
		// Sets the id of the module, return false if it could not be set
		bool setId(uint8_t *id);
		
		// Gets the id from the module, NULL if not yet valid
		uint8_t *getId();
		
		// Sets the DHSL of the module, return false if it could not be set
		bool setDHDL(uint8_t *dhdl);
		
		// Gets the DHDL from the module, NULL if not yet valid
		uint8_t *getDHDL();
		
		// Gets the SHSL from the module, NULL if not yet valid
		uint8_t *getSHSL();

		// Gets the radio Db strength, 0xFF if not yet valid
		uint8_t getRadioDb();
		
		// Sets the baudrate of the module
		bool setBaudrate(uint32_t baudrate);

		// Sets the guardtime of the module
		bool setGuardTime(uint16_t guardTime);

		// Enable 64bits addressing 
		bool enable64BitsAdressing();
				
		// Process the last received command
		void processCommand();
		
		// True if the module is processing a command
		bool isProcessingCommand();
		
		// True if we entered command mode
		bool enterCommandMode();
		
		// True if we exited command mode
		bool exitCommandMode();
		
		// True if we sent WR command
		bool commitToXBeeFlash();
	
	private:
	
		HardwareSerial *_serial;
#ifdef XBEE_CONFIG_USE_SOFTWARE_SERIAL		
		SoftwareSerial *_softSerial;
#endif
		uint8_t _state;
		uint8_t _command;
		uint32_t _waitUntilTime;
		uint16_t _guardTime;
		uint16_t _temp;
		uint8_t _id[4];
		uint8_t _shsl[16];
		uint8_t _dhdl[16];
		uint8_t _buffer[XBEE_BUFFER_LENGTH];
		uint8_t _counter;
		uint32_t _baudrate;
		uint8_t _radioDb;
		uint8_t _valid;
		bool _isInCommandMode;
};

#endif
