/*
 * Clock - Digital clock with alarm
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

#include <SPI.h>
#include <RealTimeClock.h>

// Change to your version: the following include will automatically create the proper 'touscruino' object
#define TOUSCRUINO_VERSION 1
#include <ArduRCT_TouScruino.h>

#include "RealTimeClock.h"

#define DIGIT_THICKNESS 7
#define DIGIT_WIDTH 30
#define DIGIT_HEIGHT 80
#define DIGIT_STYLE GRAPHICS_STYLE_ADVANCED

#define SECONDS_WIDTH 120
#define SECONDS_HEIGHT 5

#define COLUMN_X 79
#define ALARM_STRING_X 30
#define ALARM_X 84
#define DATE_X 12

#define TIME_Y 0
#define DATE_Y 89
#define ALARM_Y 114
#define SECONDS_Y 105

RealTimeClock rtc(2012, DECEMBER, 6, THURSDAY, 10, 28);
    
uint8_t timeDigit[] = { 0xFF, 0xFF, 0xFF, 0xFF, 0xFF };
uint8_t timeDigit_x[] = { 0, 38, 88, 130 };
    
void setup() {
    touscruino.begin(WHITE, BLACK, FONT_SMALL, FONT_PLAIN, NO_OVERLAY);
    touscruino.fillScreen(BLACK);
    touscruino.setRotation(GRAPHICS_ROTATION_90);
     
    rtc.setAlarmTime(THURSDAY, 10, 30, RTC_ALARM_ON);

    drawDateAndTime();
}

void loop() {
    delay(5);
    
    // this will adjust the time for the rtc, if required
    // returns true if a value changed
    // can be called as often as wanted, but at least once per second
    if (!rtc.update()) return;

	// redraw some elements of the screen
    drawDateAndTime();
	// draw the alarm signal
	if (rtc.isAlarmRinging()) drawAlarmSignal((rtc.getSecond() % 2) == 0 ? BLACK : RED);
	else if (rtc.isAlarmSnoozing()) drawAlarmSignal((rtc.getSecond() % 2) == 0 ? BLACK : ORANGE);
	else drawAlarmSignal(BLACK);
}

void drawDateAndTime() {
    // draw hour and minutes, digit by digit
    for (uint8_t i=0; i<4; i++) {
        if (timeDigit[i] != rtc.getTimeDigit(i)) {
            // erase the digit
            touscruino.drawBigDigit(timeDigit[i], timeDigit_x[i], 0, DIGIT_WIDTH, DIGIT_HEIGHT, BLACK, DIGIT_THICKNESS, DIGIT_STYLE);
            // draw the new one
            if ((i != 0) || (timeDigit[i] != 0)) touscruino.drawBigDigit(rtc.getTimeDigit(i), timeDigit_x[i], 0, DIGIT_WIDTH, DIGIT_HEIGHT, WHITE, DIGIT_THICKNESS, DIGIT_STYLE);
        }
    }
    // blink the seconds
    if ((rtc.getSecond() % 2) == 0) drawColumn(COLUMN_X, 0, DIGIT_HEIGHT, BLACK, DIGIT_THICKNESS);
    else drawColumn(COLUMN_X, 0, DIGIT_HEIGHT, WHITE, DIGIT_THICKNESS);
    
    // draw a progress bar for the seconds
    if (rtc.getSecond() == 0) touscruino.fillRectangle((touscruino.getWidth() - SECONDS_WIDTH)/2, SECONDS_Y, SECONDS_WIDTH, SECONDS_HEIGHT, BLACK);
    else touscruino.fillRectangle((touscruino.getWidth() - SECONDS_WIDTH)/2, SECONDS_Y, rtc.getSecond() * SECONDS_WIDTH / 60, SECONDS_HEIGHT, YELLOW);

    // if date as changed, redraw the date and alarm 
    if (timeDigit[4] != rtc.getDay()) {
        touscruino.drawString(rtc.getDateAsString(RTC_WITH_DAY_OF_WEEK), DATE_X, DATE_Y, WHITE, FONT_MEDIUM, FONT_BOLD, NO_OVERLAY);
        if (rtc.isAlarmOn(rtc.getDayOfWeek())) {
            touscruino.drawString("Alarm", ALARM_STRING_X, ALARM_Y, WHITE, FONT_MEDIUM, FONT_BOLD, NO_OVERLAY);
            touscruino.drawString(rtc.getAlarmTimeAsString(rtc.getDayOfWeek()), ALARM_X, ALARM_Y, WHITE, FONT_MEDIUM, FONT_BOLD, NO_OVERLAY);
        } else {
            touscruino.fillRectangle(ALARM_STRING_X, ALARM_Y, 12*touscruino.getFontCharWidth(FONT_MEDIUM), touscruino.getFontHeight(FONT_MEDIUM), BLACK);
        }
    }

    // save the information for next redraw
    for (uint8_t i=0; i<4; i++) timeDigit[i] = rtc.getTimeDigit(i);
    timeDigit[4] = rtc.getDay();  
}

void drawColumn(uint16_t x, uint16_t y, uint16_t height, uint16_t color, uint16_t  thickness) {
    uint16_t ht = thickness/2;
    touscruino.fillRectangle(x - ht , y + height/3 - ht, thickness, thickness, color); 
    touscruino.fillRectangle(x - ht , y + 2*height/3 - ht, thickness, thickness, color); 
}

void drawAlarmSignal(int color) {
	touscruino.fillRectangle(touscruino.getWidth()-touscruino.getHeight()+ALARM_Y, ALARM_Y, touscruino.getHeight()-ALARM_Y-2, touscruino.getHeight()-ALARM_Y-2, color);
}

/**
 * Uncomment the following flock and replace touscruino.drawBigDigit by drawBigDigit in the code above
 **/
/*
void drawBigDigit(uint8_t d, uint16_t x, uint16_t y, uint16_t width, uint16_t height, uint16_t color, uint8_t thickness, uint8_t style) {
    // we draw rounded digits
    // assume that height > 2*width
    // thickness should be odd
    uint16_t hw = (width - 2 * thickness)/2;
    uint16_t hh = (height - 2 * thickness)/2;
    uint16_t ht = thickness/2;
    uint16_t r = hw + ht;
    uint16_t cx = x + thickness + hw;
    uint16_t c1y = y + thickness + hw - 1, 
        c2y = y + ht + hh - hw, 
        c3y = y + hh + ht + 1, 
        c4y = y + hh + hw + thickness, 
        c5y = y - thickness + height - hw,
        c6y = y + thickness + 2 * hw + 1,
        c9y = y - thickness + height - 2 * hw - 2;
    uint16_t l1x = x + ht,
        l2x = x + width - ht;
    uint16_t l1y = y + ht,
        l2y = y + height - ht - 1;

    switch (d) {
        case 0:
            touscruino.drawArc(cx, c1y, r, GRAPHICS_ARC_N, color, thickness);
            touscruino.drawArc(cx, c5y, r, GRAPHICS_ARC_S, color, thickness);
            touscruino.drawLine(l1x, c1y, l1x, c5y, color, thickness);
            touscruino.drawLine(l2x, c1y, l2x, c5y, color, thickness);
            break;
        case 1:
            touscruino.drawArc(l1x, l1y, r, GRAPHICS_ARC_SE, color, thickness);
            touscruino.drawLine(cx, l1y, cx, l2y, color, thickness);
            touscruino.drawLine(l1x, l2y, l2x, l2y, color, thickness);
            break;
        case 2:
            touscruino.drawArc(cx, c1y, r, GRAPHICS_ARC_N, color, thickness);
            touscruino.drawArc(cx, c2y, r, GRAPHICS_ARC_SE, color, thickness);
            touscruino.drawArc(cx, c4y, r, GRAPHICS_ARC_NW, color, thickness);
            touscruino.drawLine(l2x, c1y, l2x, c2y, color, thickness);
            touscruino.drawLine(l1x, c4y, l1x, l2y, color, thickness);
            touscruino.drawLine(l1x, l2y, l2x, l2y, color, thickness);
            break;
        case 3:
            touscruino.drawArc(cx, c1y, r, GRAPHICS_ARC_N, color, thickness);
            touscruino.drawArc(cx, c2y, r, GRAPHICS_ARC_SE, color, thickness);
            touscruino.drawArc(cx, c4y, r, GRAPHICS_ARC_NE, color, thickness);
            touscruino.drawArc(cx, c5y, r, GRAPHICS_ARC_S, color, thickness);
            touscruino.drawLine(l2x, c1y, l2x, c2y, color, thickness);
            touscruino.drawLine(l2x, c4y, l2x, c5y, color, thickness);
            break;
        case 4:
            touscruino.drawLine(cx, l1y, l1x, c4y, color, thickness);
            touscruino.drawLine(l1x, c4y, l2x, c4y, color, thickness);
            touscruino.drawLine(cx, c3y, cx, l2y, color, thickness);
            break;
        case 5:
            touscruino.drawArc(cx, c4y, r, GRAPHICS_ARC_NE, color, thickness);
            touscruino.drawArc(cx, c5y, r, GRAPHICS_ARC_S, color, thickness);
            touscruino.drawLine(l2x, c4y, l2x, c5y, color, thickness);
            touscruino.drawLine(l1x, l1y, l2x, l1y, color, thickness);
            touscruino.drawLine(l1x, l1y, l1x, c3y, color, thickness);
            touscruino.drawLine(l1x, c3y, cx, c3y, color, thickness);
            break;
        case 6:
            touscruino.drawArc(cx, c4y, r, GRAPHICS_ARC_NE, color, thickness);
            touscruino.drawArc(cx, c5y, r, GRAPHICS_ARC_S, color, thickness);
            touscruino.drawArc(l2x-ht, c6y, r*2, GRAPHICS_ARC_NW, color, thickness);
            touscruino.drawLine(l2x, c4y, l2x, c5y, color, thickness);
            touscruino.drawLine(l1x, c3y, cx, c3y, color, thickness);
            touscruino.drawLine(l1x, c2y, l1x, c5y, color, thickness);
            break;
        case 7:
            touscruino.drawArc(l2x, c3y, r, GRAPHICS_ARC_NW, color, thickness);
            touscruino.drawLine(l2x, l1y, l2x, c3y-r, color, thickness);
            touscruino.drawLine(l1x, l1y, l2x, l1y, color, thickness);
            touscruino.drawLine(cx, c3y, cx, l2y, color, thickness);
            break;
        case 8:
            touscruino.drawArc(cx, c1y, r, GRAPHICS_ARC_N, color, thickness);
            touscruino.drawArc(cx, c2y, r, GRAPHICS_ARC_S, color, thickness);
            touscruino.drawArc(cx, c4y, r, GRAPHICS_ARC_N, color, thickness);
            touscruino.drawArc(cx, c5y, r, GRAPHICS_ARC_S, color, thickness);
            touscruino.drawLine(l1x, c1y, l1x, c2y, color, thickness);
            touscruino.drawLine(l1x, c4y, l1x, c5y, color, thickness);
            touscruino.drawLine(l2x, c1y, l2x, c2y, color, thickness);
            touscruino.drawLine(l2x, c4y, l2x, c5y, color, thickness);
            break;
        case 9:
            touscruino.drawArc(cx, c2y, r, GRAPHICS_ARC_SW, color, thickness);
            touscruino.drawArc(cx, c1y, r, GRAPHICS_ARC_N, color, thickness);
            touscruino.drawArc(l1x+ht, c9y, r*2, GRAPHICS_ARC_SE, color, thickness);
            touscruino.drawLine(cx, c3y, l2x, c3y, color, thickness);
            touscruino.drawLine(l2x, c1y, l2x, c9y, color, thickness);
            touscruino.drawLine(l1x, c1y, l1x, c2y, color, thickness);
            break;
    }
}
*/