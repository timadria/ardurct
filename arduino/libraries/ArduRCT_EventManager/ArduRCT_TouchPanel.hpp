/*
 * TouchPanel - Manages a touch panel 
 *
 * Copyright (c) 2010-2012 Laurent Wibaux <lm.wibaux@gmail.com>
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
 
#ifndef ARDURCT_TOUCH_PANEL
#define ARDURCT_TOUCH_PANEL 1

#if ARDUINO >= 100
 #include "Arduino.h"
 #include "Print.h"
#else
 #include "WProgram.h"
#endif

#include <inttypes.h>
#include "ArduRCT_EventManager_Configuration.hpp"

#define TOUCHPANEL_ROTATION_0   0
#define TOUCHPANEL_ROTATION_90  1
#define TOUCHPANEL_ROTATION_180 2
#define TOUCHPANEL_ROTATION_270 3

typedef struct {
	int32_t a;
	int32_t b;
	int32_t divider;
} tsCalibrationEquation_t;

class ArduRCT_TouchPanel {
	
	public:
	
#ifdef TOUCHPANEL_MATRIX_CALIBRATION
		ArduRCT_TouchPanel(uint8_t interruptPin = TOUCHPANEL_EVENT_PIN, uint8_t dragTrigger = TOUCHPANEL_DRAG_TRIGGER, 
                uint16_t width = TOUCHPANEL_WIDTH, uint16_t height = TOUCHPANEL_HEIGHT, uint16_t calibrationMatrixEepromAddress = 0xFFFF);
#else
		ArduRCT_TouchPanel(uint8_t interruptPin = TOUCHPANEL_EVENT_PIN, uint8_t dragTrigger = TOUCHPANEL_DRAG_TRIGGER, 
                uint16_t width = TOUCHPANEL_WIDTH, uint16_t height = TOUCHPANEL_HEIGHT);
#endif
		int8_t getPenXYZ(uint16_t *x = 0, uint16_t *y = 0, int8_t *z = 0);
		
		uint8_t update();
		
		bool isPenPressed();
		
		bool isPenPressedMotionless();
		
		bool isPenReleased();
		
		bool isPenDragged();
		
		int16_t getPenX();
		
		int16_t getPenY();
		
		int8_t getPenZ();

        void setRotation(uint8_t rotation);

#ifdef TOUCHPANEL_MATRIX_CALIBRATION
        uint8_t getCalibrationCrossNumber();

        bool isCalibrating();
        
        void getCalibrationCrossXY(uint8_t number, uint16_t *x, uint16_t *y);
        
        void deleteCalibration();
#endif

    private:
		uint8_t _state;
        uint8_t _rotation;
		int16_t _penX;
		int16_t _penY;
		int8_t _penZ;
        int16_t _touchX;
        int16_t _touchY;
        int8_t _touchZ;
        uint8_t _calibrationStatus;
        uint8_t _interruptPin;
        uint8_t _dragTrigger;
        uint16_t _width;
        uint16_t _height;
        void (*_graphicsRotationHandler)(int16_t *x, int16_t *y);
#ifdef TOUCHPANEL_MATRIX_CALIBRATION
        uint16_t _calibrationMatrixEepromAddress;
        tsCalibrationEquation_t _xCalibrationEquation;
        tsCalibrationEquation_t _yCalibrationEquation;
        uint16_t _calibrationX[3];
        uint16_t _calibrationY[3];
#endif
		int16_t _getTouchX();
		
		int16_t _getTouchY();
		
		int8_t _getTouchZ();
        
        void _adjustTouchWithCalibration();

        uint8_t _calibrate();
};

#endif
