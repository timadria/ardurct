#include "S6D04H0.hpp"

#define S6D04H0_CASET	0x2a
#define S6D04H0_PASET	0x2b
#define S6D04H0_RAMWR	0x2c
#define S6D04H0_RAMRD	0x2e
#define S6D04H0_MADCTL	0x36

#define S6D04H0_R0		0x08
#define S6D04H0_R90		0x38
#define S6D04H0_R180	0x68
#define S6D04H0_R270	0x58

const unsigned char PROGMEM S6D04H0_initialization_code[] = {
	0, 	0x11,			/* SLPOFF: SLeeP OFF */
	0xFE,				/* delay(100) */
	0, 	0x13,			/* NORON: NORmal mode ON */
	0xFE,				/* delay(100) */
	5, 	0xf3, 0x01, 0xff, 0x1f, 0x00, 0x03,
	17, 0xf2, 0x28, 0x60, 0x7f, 0x08, 0x08, 0x00, 0x00, 0x15, 0x48, 0x00, 0x07, 0x01, 0x00, 0x00, 0x94, 0x08, 0x08,
	20, 0xf4, 0x09, 0x00, 0x00, 0x00, 0x21, 0x47, 0x01, 0x02, 0x2A, 0x5D, 0x07, 0x2A, 0x00, 0x07, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	12, 0xf5, 0x00, 0x3c, 0x30, 0x00, 0x00, 0x12, 0x00, 0x00, 0x0d, 0x01, 0x00, 0x00,
	9, 	0xf6, 0x01, 0x01, 0x07, 0x00, 0x02, 0x0c, 0x02, 0x08, 0x03,
	4,	0xf7, 0x01, 0x00, 0x10, 0x00,
	3,	0xf8, 0x99, 0x00, 0x00,
	1,	0xf9, 0x01,
	1, 	0x26, 0x02,		/* GASET: Gamma Set */
	1, 	0x35, 0x00,		/* TEON: Tearing on */
	1,	0x36, 0x00,		/* MADCTL: Memory ADdress ConTroL, B7,B6,B5=0 => no rotation, B3=0 => no BGR filter */
	1, 	0x3a, 0x55, 	/* COLMOD: COLor MODe: 16 bits/pixel */
	4,	0x2a, 0x00, 0x00, 0x00, 0xef,	/* CASET: Column Address SET */
	4,	0x2b, 0x00, 0x00, 0x01, 0x3f, 	/* PASET:Page Address SET */
	0,	0x29, 			/* DISPON:	DISplay ON */
	0,	0x2c,			/* RAMWR: RAM WRite */
	0xFE,				/* delay(100) */
	0xFF				/* End initialization */
};


S6D04H0::S6D04H0(uint8_t port, uint8_t cd, uint8_t wr, uint8_t rd, uint8_t cs, uint8_t reset) {	
	setupScreen(port, cd, wr, rd, cs, reset);
}
 
void S6D04H0::drawPixel(uint16_t x, uint16_t y, uint16_t color, boolean grabAndReleaseBus) {
	if ((x >= _width) || (y >= _height)) return;
	if (grabAndReleaseBus) grabBus();
	fillRectangleNoCheck(x, y, x, y, color);
	if (grabAndReleaseBus) releaseBus();
}


/* ---------------- Protected functions ------------------------ */

void S6D04H0::initScreen(void) {
	uint8_t buffer[32];
	uint16_t i = 0;
	
	// init the screen
	grabBus();
	while (1) {
		memcpy_P(buffer, &(S6D04H0_initialization_code[i]), 32);
		if (buffer[0] == 0xFF) break;
		if (buffer[0] == 0xFE) delay(100);
		else {
			writeCommand(buffer[1]);
			for (uint8_t j=0; j<buffer[0]; j++) writeData(buffer[2+j]);
			i += buffer[0]+1;
		}
		i++;
	}
	releaseBus();
}

// Fill the rectangle lx,ly -> hx, hy
// this functions assumes that lx <= hx and ly <= hy
void S6D04H0::fillRectangleNoCheck(uint16_t lx, uint16_t ly, uint16_t hx, uint16_t hy, uint16_t color) {
	uint32_t nbPixels = (hx-lx+1)*(hy-ly+1);
	_setClippingRectangleNoCheck(lx, hx, ly, hy);
	writeCommand(S6D04H0_RAMWR);
	while (nbPixels-- > 0) write16bData(color);
}

void S6D04H0::drawCharNoCheck(uint8_t chr, uint16_t x, uint16_t y, uint16_t color, uint8_t fontSize, fontDefinition_t *fontDef, uint8_t fontScale, boolean overlay, boolean grabAndReleaseBus) {
	uint8_t pattern[FONT_MAX_PATTERN];
	uint16_t buffer[FONT_MAX_SPACE*2*2];

	uint16_t spaceUsed = fontDef->width*fontDef->height*fontScale*fontScale;
	if (grabAndReleaseBus) grabBus();
	_setClippingRectangleNoCheck(x, y, x+fontDef->width*fontScale, y+fontDef->height*fontScale); 
	if (overlay) {
		writeCommand(S6D04H0_RAMRD);
		read16bDataBuffer(buffer, spaceUsed);
	}
	if (fontSize == 1) memcpy_P(pattern, &font_small[chr - fontDef->firstChar], fontDef->orientation == 0 ? fontDef->height : fontDef->width);
	else if (fontSize == 2) memcpy_P(pattern, &font_medium[chr - fontDef->firstChar], fontDef->orientation == 0 ? fontDef->height : fontDef->width);
	if (!overlay) {
		// prepare the horizontal lines at bottom
		fillRectangleNoCheck(x, y+fontDef->height*fontScale, x+(fontDef->width+fontDef->charSpacing-1)*fontScale, y+(fontDef->height+fontDef->lineSpacing-1)*fontScale, _backgroundColor); 
		// prepare the vertical lines at right
		fillRectangleNoCheck(x+fontDef->width*fontScale, y, x+(fontDef->width+fontDef->charSpacing-1)*fontScale, y+fontDef->height*fontScale, _backgroundColor); 
		_setClippingRectangleNoCheck(x, y, x+fontDef->width*fontScale, y+fontDef->height*fontScale); 
	}
	// prepare the dots
	for (uint8_t l=0; l<fontDef->height; l++) {
		for (uint8_t c=0; c<fontDef->width; c++) {
			if (_charPixelIsOn(fontDef, pattern, l, c)) _bufferScaledPixel(buffer, c, l, fontDef->width+fontDef->charSpacing, color, fontScale);
			else if (!overlay) _bufferScaledPixel(buffer, c, l, fontDef->width+fontDef->charSpacing, _backgroundColor, fontScale);
		}
	}
	// draw the char 
	writeCommand(S6D04H0_RAMWR);
	write16bDataBuffer(buffer, spaceUsed);
	if (grabAndReleaseBus) releaseBus();
}

void S6D04H0::rotate() {
	if (_rotation == SCREEN_ROTATION_0) {
		writeCommand(S6D04H0_MADCTL);
		writeData(S6D04H0_R0);
	} else if (_rotation == SCREEN_ROTATION_90) {
		writeCommand(S6D04H0_MADCTL);
		writeData(S6D04H0_R90);
	} else if (_rotation == SCREEN_ROTATION_180) {
		writeCommand(S6D04H0_MADCTL);
		writeData(S6D04H0_R90);		
	} else if (_rotation == SCREEN_ROTATION_270) {
		writeCommand(S6D04H0_MADCTL);
		writeData(S6D04H0_R90);
	}
}


void S6D04H0::drawPixelNoCheck(uint16_t x, uint16_t y, uint16_t color) {
	fillRectangleNoCheck(x, y, x, y, color);
}

/* ---------------- Private functions -------------------------- */

// define the zone we are going to write to
void S6D04H0::_setClippingRectangleNoCheck(uint16_t lx, uint16_t ly, uint16_t hx, uint16_t hy) {
	writeCommand(S6D04H0_CASET);
	write16bData(lx);
	write16bData(hx);
	writeCommand(S6D04H0_PASET);
	write16bData(ly);
	write16bData(hy);
}


void S6D04H0::_bufferScaledPixel(uint16_t *buffer, uint8_t x, uint8_t y, uint8_t unscaledBufferWidth, uint16_t color, uint8_t scale) {
	if (scale == 1) {
		buffer[y*unscaledBufferWidth+x] = color;
		return;
	}
	for (uint8_t l=0; l<scale; l++) {
		for (uint8_t c=0; c<scale; c++) {
			buffer[(y*scale+l)*unscaledBufferWidth+x*scale+c] = color;
		}
	}
}


bool S6D04H0::_charPixelIsOn(fontDefinition_t *fontDef, uint8_t *pattern, uint8_t l, uint8_t c) {
	if (fontDef->orientation == FONT_ORIENTATION_HORIZONTAL) return (pattern[l*(1+fontDef->width/8)+c/8] & (0x01 << (c & 0x07)));
	else return (pattern[c*(1+fontDef->height/8)+l/8] & (0x01 << (l & 0x07)));
}