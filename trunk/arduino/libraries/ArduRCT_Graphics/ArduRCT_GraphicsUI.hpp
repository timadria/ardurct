/*
 * GraphicsUI - An UI system
 *	Part of Graphics class
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
 
#ifndef GRAPHICS_UI_HPP
#define GRAPHICS_UI_HPP 1

#include <inttypes.h>

// the following defines can be changed to personalise the User Interface

#define GRAPHICS_UI_COLOR_BACKGROUND  WHITE
#define GRAPHICS_UI_COLOR_POPUP_BACKGROUND  0xF6F6
#define GRAPHICS_UI_COLOR_TAB_UNSELECTED  0xAD55
#define GRAPHICS_UI_COLOR_HIGHLIGHTED  RED
#define GRAPHICS_UI_COLOR_SELECTED  GRAPHICS_UI_COLOR_TAB_UNSELECTED
#define GRAPHICS_UI_COLOR_RELEASED  VLIGHT_GREY
#define GRAPHICS_UI_COLOR_GAUGE  0x7C7C
#define GRAPHICS_UI_COLOR_PRESSED  GRAPHICS_UI_COLOR_TAB_UNSELECTED

#define GRAPHICS_UI_ELEMENT_FONT  FONT_MEDIUM
#define GRAPHICS_UI_ELEMENT_FONT_IS_BOLD  true
#define GRAPHICS_UI_ELEMENT_TOP_MARGIN  5
#define GRAPHICS_UI_ELEMENT_LEFT_MARGIN  6

#define GRAPHICS_UI_TAB_FONT  FONT_MEDIUM
#define GRAPHICS_UI_TAB_FONT_IS_BOLD  true
#define GRAPHICS_UI_TAB_TOP_MARGIN  4
#define GRAPHICS_UI_TAB_LEFT_MARGIN  5

#define GRAPHICS_UI_SLIDER_KNOB_SIZE  8
#define GRAPHICS_UI_SCROLLER_ARROW_SIZE  8

// define below should not be modified 
#define GRAPHICS_UI_NO_GROUP  -1

#define GRAPHICS_UI_AUTO_SIZE  0
#define GRAPHICS_UI_SAME_AS_PREVIOUS  0x8000
#define GRAPHICS_UI_WIDTH  0x1000
#define GRAPHICS_UI_HEIGHT  0x1000
#define GRAPHICS_UI_LEFT_OF_PREVIOUS  0x4000
#define GRAPHICS_UI_RIGHT_OF_PREVIOUS  0x2000
#define GRAPHICS_UI_RIGHT_OF_PREVIOUS_WITH_MARGIN  0x3000
#define GRAPHICS_UI_TOP_OF_PREVIOUS  0x4000
#define GRAPHICS_UI_BOTTOM_OF_PREVIOUS  0x2000
#define GRAPHICS_UI_BOTTOM_OF_PREVIOUS_WITH_MARGIN  0x3000

#define GUI_AS      GRAPHICS_UI_AUTO_SIZE
#define GUI_SAP     GRAPHICS_UI_SAME_AS_PREVIOUS
#define GUI_W       GRAPHICS_UI_WIDTH
#define GUI_H       GRAPHICS_UI_HEIGHT
#define GUI_LOP     GRAPHICS_UI_LEFT_OF_PREVIOUS
#define GUI_ROP     GRAPHICS_UI_RIGHT_OF_PREVIOUS
#define GUI_ROPWM   GRAPHICS_UI_RIGHT_OF_PREVIOUS_WITH_MARGIN
#define GUI_TOP     GRAPHICS_UI_TOP_OF_PREVIOUS
#define GUI_BOP     GRAPHICS_UI_BOTTOM_OF_PREVIOUS
#define GUI_BOPWM   GRAPHICS_UI_BOTTOM_OF_PREVIOUS_WITH_MARGIN

#define GRAPHICS_UI_ACTION_UP  1
#define GRAPHICS_UI_ACTION_DOWN  2
#define GRAPHICS_UI_ACTION_RIGHT  3
#define GRAPHICS_UI_ACTION_LEFT  4
#define GRAPHICS_UI_ACTION_MENU  5
#define GRAPHICS_UI_ACTION_ENTER  6
#define GRAPHICS_UI_ACTION_RELEASE  7
#define GRAPHICS_UI_ACTION_TOUCH  8
#define GRAPHICS_UI_ACTION_UNTOUCH  9

#define GRAPHICS_UI_RELEASED  0
#define GRAPHICS_UI_SELECTED  1
#define GRAPHICS_UI_HIGHLIGHTED  2

#define GRAPHICS_UI_SECTION_HEADER  -1
#define GRAPHICS_UI_SECTION_MAIN  0
#define GRAPHICS_UI_SECTION_FOOTER  1

#endif