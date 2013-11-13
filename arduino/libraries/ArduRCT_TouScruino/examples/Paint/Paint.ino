/*
 * Paint - Demonstration of use of the touchscreen
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

#include <Wire.h>
#include <SPI.h>
#include <ArduRCT_Graphics.h>
#include <ArduRCT_EventManager.h>

// Change to your version: the following include will automatically create the proper 'Touscruino' object
#define TOUSCRUINO_VERSION 2
#include <ArduRCT_Touscruino.h>

#define PEN_1 1
#define PEN_2 2
#define PEN_3 3
#define COLOR_1 4
#define COLOR_2 5
#define COLOR_3 6
#define COLOR_4 7
#define BLANK 8

#define PEN_GROUP 1
#define COLOR_GROUP 2

ArduRCT_GraphicsUIScreen screen;
ArduRCT_GraphicsUIOption pen1(PEN_1, &uiDrawer, &uiHandler, PEN_GROUP);
ArduRCT_GraphicsUIOption pen2(PEN_2, &uiDrawer, &uiHandler, PEN_GROUP);
ArduRCT_GraphicsUIOption pen3(PEN_3, &uiDrawer, &uiHandler, PEN_GROUP);
ArduRCT_GraphicsUIOption color1(COLOR_1, &uiDrawer, &uiHandler, COLOR_GROUP);
ArduRCT_GraphicsUIOption color2(COLOR_2, &uiDrawer, &uiHandler, COLOR_GROUP);
ArduRCT_GraphicsUIOption color3(COLOR_3, &uiDrawer, &uiHandler, COLOR_GROUP);
ArduRCT_GraphicsUIOption color4(COLOR_4, &uiDrawer, &uiHandler, COLOR_GROUP);
ArduRCT_GraphicsUIButton blank(BLANK, &uiDrawer, &uiHandler);

ArduRCT_EventHandler touchpanelHandler(EVENT_TOUCHPANEL, EVENT_ANY_VALUE, 0, 0, &handleTouchPanel);

uint16_t penColor = RED;
uint8_t penSize = 3;
int16_t penX;
int16_t penY;

void setup() {
    // prepare the screen
    Touscruino.begin(BLACK, WHITE, FONT_SMALL, FONT_PLAIN, NO_OVERLAY);
    // enable the GraphicsUI, required for the UI elements to react
    // this should be done before any other event handler registration 
    // to ensure the UI has the first go at any event
    Touscruino.enableGraphicsUI();
    
    // add a screen
    Touscruino.addScreen(&screen);
    // add the screen elements
    screen.addElement(&pen1, 0, 0, 28, 30);
    screen.addElement(&pen2, GUI_ROP, GUI_SAP, GUI_SAP, GUI_SAP);
    screen.addElement(&pen3, GUI_ROP, GUI_SAP, GUI_SAP, GUI_SAP);
    screen.setElementValue(PEN_1, GRAPHICS_UI_SELECTED);
    screen.addElement(&color1, GUI_ROPWM, GUI_SAP, GUI_SAP, GUI_SAP);
    screen.addElement(&color2, GUI_ROP, GUI_SAP, GUI_SAP, GUI_SAP);
    screen.addElement(&color3, GUI_ROP, GUI_SAP, GUI_SAP, GUI_SAP);
    screen.addElement(&color4, GUI_ROP, GUI_SAP, GUI_SAP, GUI_SAP);
    screen.setElementValue(COLOR_1, GRAPHICS_UI_SELECTED);
    screen.addElement(&blank, GUI_ROPWM, GUI_SAP, GUI_SAP, GUI_SAP);
    
    // draw the screen
    Touscruino.setGraphicsUIScreen(&screen);

    // register an EventHandler for the panel
    Touscruino.registerEventHandler(&touchpanelHandler);
}

void loop() {
    Touscruino.manageEvents();
}

int8_t handleTouchPanel(uint8_t eventType, uint8_t z, int16_t x, int16_t y) {
    if (eventType == EVENT_TOUCHPANEL_PRESSED) {
        // if the pen is pressed on the screen, draw a big dot at the pen place
        Touscruino.fillRectangle(x-penSize/2, y-penSize/2, penSize, penSize, penColor);
    } else if (eventType == EVENT_TOUCHPANEL_DRAGGED) {
        // if the pen is dragged on the screen, 
        // draw a line connecting the last dot drawn and the current pen place
        Touscruino.drawLine(penX, penY, x, y, penColor, penSize);
    } else return EVENT_HANDLING_EXIT;
    penX = x;
    penY = y;
    return EVENT_HANDLING_EXIT;
}

void uiDrawer(uint8_t id, uint8_t state, int16_t value, int16_t x, int16_t y, uint16_t width, uint16_t height) {
    uint16_t color = VLIGHT_GREY;
    if ((value == GRAPHICS_UI_SELECTED) || (state == GRAPHICS_UI_SELECTED)) color = LIGHT_GREY;
    Touscruino.fillRectangle(x, y, width, height, color);
    if (id < COLOR_1) {
        Touscruino.drawLine(x+5+(id-1)*3, y+5+(id-1)*3, x+width-10, y+height-10, BLACK, id*3); 
    } else if (id < BLANK) {
        color = RED;
        if (id == COLOR_2) color = BLUE;
        else if (id == COLOR_3) color = GREEN;
        else if (id == COLOR_4) color = BLACK;
        Touscruino.fillRectangle(x+5, y+5, width-10, height-10, color); 
    } else {
        Touscruino.fillRectangle(x+5, y+5, width-10, height-10, WHITE); 
    }
}

bool uiHandler(uint8_t id, int16_t value) {
    if (id < COLOR_1) {
        penSize = id*3;
    } else if (id < BLANK) {
        if (id == COLOR_1) penColor = RED;
        else if (id == COLOR_2) penColor = BLUE;
        else if (id == COLOR_3) penColor = GREEN;
        else if (id == COLOR_4) penColor = BLACK;
    } else {
        Touscruino.fillRectangle(0, screen.getElement(PEN_1)->height+1, Touscruino.getWidth(), Touscruino.getHeight()-screen.getElement(PEN_1)->height, WHITE); 
    }
}