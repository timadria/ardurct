/*
 * GraphicsUIButton - a clickable button
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
 
#ifndef GRAPHICS_UI_BUTTON_HPP
#define GRAPHICS_UI_BUTTON_HPP 1

#include <inttypes.h>

class ArduRCT_GraphicsUIButton : public ArduRCT_GraphicsUIElement {
    
    public:
        ArduRCT_GraphicsUIButton();
        
        ArduRCT_GraphicsUIButton(uint8_t id, char *label, bool (*actionHandler)(uint8_t elementId, uint8_t state, int16_t value));
        
        ArduRCT_GraphicsUIButton(uint8_t id, void (*drawHandler)(uint8_t id, uint8_t state, int16_t value, int16_t x, int16_t y, uint16_t width, uint16_t height), 
                bool (*actionHandler)(uint8_t elementId, uint8_t state, int16_t value));

        virtual void autoSize(ArduRCT_Graphics *graphics);

        virtual void placeLabel(ArduRCT_Graphics *graphics);

        virtual void draw(ArduRCT_Graphics *graphics, int16_t uiX, int16_t uiY, uint16_t uiWidth);

        virtual ArduRCT_GraphicsUIElement *press();

        virtual bool release();
        
    protected:
        char *_text;
        uint8_t _textX;
        uint8_t _textY;
        
        void _init(uint8_t id, uint8_t group, char *label, void (*drawHandler)(uint8_t id, uint8_t state, int16_t value, int16_t x, int16_t y, uint16_t width, uint16_t height), 
            bool (*actionHandler)(uint8_t elementId, uint8_t state, int16_t value));

        virtual uint16_t _drawBorder(ArduRCT_Graphics *graphics, int16_t uiX, int16_t uiY, uint16_t color);
}; 

#endif