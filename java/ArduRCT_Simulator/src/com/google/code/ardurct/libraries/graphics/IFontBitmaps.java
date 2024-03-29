package com.google.code.ardurct.libraries.graphics;

class fontDefinition_t {
	public int width;
	public int height;
	public int charSpacing;
	public int lineSpacing;
	public int firstChar;
	public int lastChar;
	public int orientation;
	
	fontDefinition_t(int width,	int height, int charSpacing, int lineSpacing, 
			int firstChar, int lastChar, int orientation) {
		this.width = width;
		this.height = height;
		this.charSpacing = charSpacing;
		this.lineSpacing = lineSpacing;
		this.firstChar = firstChar;
		this.lastChar = lastChar;
		this.orientation = orientation;
	}
}

public interface IFontBitmaps {
	
	public static final int FONT_LAST_DEF = 2;				/* last definition */

	public static final int FONT_MAX_SPACE = (9*14);		/* width x height of the biggest font including the charSpacing and lineSpacing */
	public static final int FONT_MAX_PATTERN = 12;	

	fontDefinition_t fontDefinition_small = new fontDefinition_t(5, 7, 1, 1, 0x20, 0x7e, 1);

	public int font_small[][] = {
		{ 0x00, 0x00, 0x00, 0x00, 0x00 },		/*   - 0x20 - 32 */
		{ 0x00, 0x00, 0x5f, 0x00, 0x00 },		/* ! - 0x21 - 33 */
		{ 0x00, 0x07, 0x00, 0x07, 0x00 },		/* " - 0x22 - 34 */
		{ 0x14, 0x7f, 0x14, 0x7f, 0x14 },		/* # - 0x23 - 35 */
		{ 0x24, 0x2a, 0x7f, 0x2a, 0x12 },		/* $ - 0x24 - 36 */
		{ 0x23, 0x13, 0x08, 0x64, 0x62 },		/* % - 0x25 - 37 */
		{ 0x36, 0x49, 0x55, 0x22, 0x50 },		/* & - 0x26 - 38 */
		{ 0x00, 0x05, 0x03, 0x00, 0x00 },		/* ' - 0x27 - 39 */
		{ 0x00, 0x1c, 0x22, 0x41, 0x00 },		/* ( - 0x28 - 40 */
		{ 0x00, 0x41, 0x22, 0x1c, 0x00 },		/* ) - 0x29 - 41 */
		{ 0x14, 0x08, 0x3e, 0x08, 0x14 },		/* * - 0x2a - 42 */
		{ 0x08, 0x08, 0x3e, 0x08, 0x08 },		/* + - 0x2b - 43 */
		{ 0x00, 0x50, 0x30, 0x00, 0x00 },		/* , - 0x2c - 44 */
		{ 0x08, 0x08, 0x08, 0x08, 0x08 },		/* - - 0x2d - 45 */
		{ 0x00, 0x60, 0x60, 0x00, 0x00 },		/* . - 0x2e - 46 */
		{ 0x20, 0x10, 0x08, 0x04, 0x02 },		/* / - 0x2f - 47 */
		{ 0x3e, 0x51, 0x49, 0x45, 0x3e },		/* 0 - 0x30 - 48 */
		{ 0x00, 0x42, 0x7f, 0x40, 0x00 },		/* 1 - 0x31 - 49 */
		{ 0x42, 0x61, 0x51, 0x49, 0x46 },		/* 2 - 0x32 - 50 */
		{ 0x21, 0x41, 0x45, 0x4b, 0x31 },		/* 3 - 0x33 - 51 */
		{ 0x18, 0x14, 0x12, 0x7f, 0x10 },		/* 4 - 0x34 - 52 */
		{ 0x27, 0x45, 0x45, 0x45, 0x39 },		/* 5 - 0x35 - 53 */
		{ 0x3c, 0x4a, 0x49, 0x49, 0x30 },		/* 6 - 0x36 - 54 */
		{ 0x01, 0x71, 0x09, 0x05, 0x03 },		/* 7 - 0x37 - 55 */
		{ 0x36, 0x49, 0x49, 0x49, 0x36 },		/* 8 - 0x38 - 56 */
		{ 0x06, 0x49, 0x49, 0x29, 0x1e },		/* 9 - 0x39 - 57 */
		{ 0x00, 0x36, 0x36, 0x00, 0x00 },		/* : - 0x3a - 58 */
		{ 0x00, 0x56, 0x36, 0x00, 0x00 },		/* ; - 0x3b - 59 */
		{ 0x08, 0x14, 0x22, 0x41, 0x00 },		/* < - 0x3c - 60 */
		{ 0x14, 0x14, 0x14, 0x14, 0x14 },		/* = - 0x3d - 61 */
		{ 0x00, 0x41, 0x22, 0x14, 0x08 },		/* > - 0x3e - 62 */
		{ 0x02, 0x01, 0x51, 0x09, 0x06 },		/* ? - 0x3f - 63 */
		{ 0x32, 0x49, 0x79, 0x41, 0x3e },		/* @ - 0x40 - 64 */
		{ 0x7e, 0x11, 0x11, 0x11, 0x7e },		/* A - 0x41 - 65 */
		{ 0x7f, 0x49, 0x49, 0x49, 0x36 },		/* B - 0x42 - 66 */
		{ 0x3e, 0x41, 0x41, 0x41, 0x22 },		/* C - 0x43 - 67 */
		{ 0x7f, 0x41, 0x41, 0x22, 0x1c },		/* D - 0x44 - 68 */
		{ 0x7f, 0x49, 0x49, 0x49, 0x41 },		/* E - 0x45 - 69 */
		{ 0x7f, 0x09, 0x09, 0x09, 0x01 },		/* F - 0x46 - 70 */
		{ 0x3e, 0x41, 0x49, 0x49, 0x7a },		/* G - 0x47 - 71 */
		{ 0x7f, 0x08, 0x08, 0x08, 0x7f },		/* H - 0x48 - 72 */
		{ 0x00, 0x41, 0x7f, 0x41, 0x00 },		/* I - 0x49 - 73 */
		{ 0x20, 0x40, 0x41, 0x3f, 0x01 },		/* J - 0x4a - 74 */
		{ 0x7f, 0x08, 0x14, 0x22, 0x41 },		/* K - 0x4b - 75 */
		{ 0x7f, 0x40, 0x40, 0x40, 0x40 },		/* L - 0x4c - 76 */
		{ 0x7f, 0x02, 0x0c, 0x02, 0x7f },		/* M - 0x4d - 77 */
		{ 0x7f, 0x04, 0x08, 0x10, 0x7f },		/* N - 0x4e - 78 */
		{ 0x3e, 0x41, 0x41, 0x41, 0x3e },		/* O - 0x4f - 79 */
		{ 0x7f, 0x09, 0x09, 0x09, 0x06 },		/* P - 0x50 - 80 */
		{ 0x3e, 0x41, 0x51, 0x21, 0x5e },		/* Q - 0x51 - 81 */
		{ 0x7f, 0x09, 0x19, 0x29, 0x46 },		/* R - 0x52 - 82 */
		{ 0x46, 0x49, 0x49, 0x49, 0x31 },		/* S - 0x53 - 83 */
		{ 0x01, 0x01, 0x7f, 0x01, 0x01 },		/* T - 0x54 - 84 */
		{ 0x3f, 0x40, 0x40, 0x40, 0x3f },		/* U - 0x55 - 85 */
		{ 0x1f, 0x20, 0x40, 0x20, 0x1f },		/* V - 0x56 - 86 */
		{ 0x3f, 0x40, 0x38, 0x40, 0x3f },		/* W - 0x57 - 87 */
		{ 0x63, 0x14, 0x08, 0x14, 0x63 },		/* X - 0x58 - 88 */
		{ 0x07, 0x08, 0x70, 0x08, 0x07 },		/* Y - 0x59 - 89 */
		{ 0x61, 0x51, 0x49, 0x45, 0x43 },		/* Z - 0x5a - 90 */
		{ 0x00, 0x7f, 0x41, 0x41, 0x00 },		/* [ - 0x5b - 91 */
		{ 0x02, 0x04, 0x08, 0x10, 0x20 },		/* \ - 0x5c - 92 */
		{ 0x00, 0x41, 0x41, 0x7f, 0x00 },		/* ] - 0x5d - 93 */
		{ 0x04, 0x02, 0x01, 0x02, 0x04 },		/* ^ - 0x5e - 94 */
		{ 0x40, 0x40, 0x40, 0x40, 0x40 },		/* _ - 0x5f - 95 */
		{ 0x00, 0x01, 0x02, 0x04, 0x00 },		/* ` - 0x60 - 96 */
		{ 0x20, 0x54, 0x54, 0x54, 0x78 },		/* a - 0x61 - 97 */
		{ 0x7f, 0x48, 0x44, 0x44, 0x38 },		/* b - 0x62 - 98 */
		{ 0x38, 0x44, 0x44, 0x44, 0x20 },		/* c - 0x63 - 99 */
		{ 0x38, 0x44, 0x44, 0x48, 0x7f },		/* d - 0x64 - 100 */
		{ 0x38, 0x54, 0x54, 0x54, 0x18 },		/* e - 0x65 - 101 */
		{ 0x08, 0x7e, 0x09, 0x01, 0x02 },		/* f - 0x66 - 102 */
		{ 0x38, 0x44, 0x44, 0x54, 0x34 },		/* g - 0x67 - 103 */
		{ 0x7f, 0x08, 0x04, 0x04, 0x78 },		/* h - 0x68 - 104 */
		{ 0x00, 0x44, 0x7d, 0x40, 0x00 },		/* i - 0x69 - 105 */
		{ 0x20, 0x40, 0x44, 0x3d, 0x00 },		/* j - 0x6a - 106 */
		{ 0x7f, 0x10, 0x28, 0x44, 0x00 },		/* k - 0x6b - 107 */
		{ 0x00, 0x41, 0x7f, 0x40, 0x00 },		/* l - 0x6c - 108 */
		{ 0x7c, 0x04, 0x18, 0x04, 0x78 },		/* m - 0x6d - 109 */
		{ 0x7c, 0x08, 0x04, 0x04, 0x78 },		/* n - 0x6e - 110 */
		{ 0x38, 0x44, 0x44, 0x44, 0x38 },		/* o - 0x6f - 111 */
		{ 0x7c, 0x14, 0x14, 0x14, 0x08 },		/* p - 0x70 - 112 */
		{ 0x08, 0x14, 0x14, 0x18, 0x7c },		/* q - 0x71 - 113 */
		{ 0x7c, 0x08, 0x04, 0x04, 0x08 },		/* r - 0x72 - 114 */
		{ 0x48, 0x54, 0x54, 0x54, 0x20 },		/* s - 0x73 - 115 */
		{ 0x04, 0x3f, 0x44, 0x40, 0x20 },		/* t - 0x74 - 116 */
		{ 0x3c, 0x40, 0x40, 0x20, 0x7c },		/* u - 0x75 - 117 */
		{ 0x1c, 0x20, 0x40, 0x20, 0x1c },		/* v - 0x76 - 118 */
		{ 0x3c, 0x40, 0x30, 0x40, 0x3c },		/* w - 0x77 - 119 */
		{ 0x44, 0x28, 0x10, 0x28, 0x44 },		/* x - 0x78 - 120 */
		{ 0x0c, 0x50, 0x50, 0x50, 0x3c },		/* y - 0x79 - 121 */
		{ 0x44, 0x64, 0x54, 0x4c, 0x44 },		/* z - 0x7a - 122 */
		{ 0x00, 0x08, 0x36, 0x41, 0x00 },		/* { - 0x7b - 123 */
		{ 0x00, 0x00, 0x7f, 0x00, 0x00 },		/* | - 0x7c - 124 */
		{ 0x00, 0x41, 0x36, 0x08, 0x00 },		/* } - 0x7d - 125 */
		{ 0x10, 0x08, 0x08, 0x10, 0x08 },		/* ~ - 0x7e - 126 */
	};

	// a 7x12 font occupying 9x14 space per char, (2px between chars and 2px between lines)
	// starts at ascii code 0x20 and finishes at 0x7d
	// each byte represents a HORIZONTAL line in the pattern
	fontDefinition_t fontDefinition_medium = new fontDefinition_t(7, 12, 2, 2, 0x20, 0x7e, 0);

	public int font_medium[][] = {
		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/*   - 0x20 - 32 */
		{ 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x00, 0x08, 0x00, 0x00 },		/* ! - 0x21 - 33 */
		{ 0x14, 0x14, 0x14, 0x14, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* " - 0x22 - 34 */
		{ 0x00, 0x14, 0x14, 0x7f, 0x14, 0x14, 0x7f, 0x14, 0x14, 0x00, 0x00, 0x00 },		/* # - 0x23 - 35 */
		{ 0x08, 0x7c, 0x0a, 0x0a, 0x0a, 0x1c, 0x28, 0x28, 0x28, 0x1f, 0x08, 0x00 },		/* $ - 0x24 - 36 */
		{ 0x42, 0x45, 0x25, 0x12, 0x08, 0x08, 0x24, 0x52, 0x51, 0x21, 0x00, 0x00 },		/* % - 0x25 - 37 */
		{ 0x0e, 0x11, 0x11, 0x11, 0x0e, 0x04, 0x4a, 0x51, 0x21, 0x5e, 0x00, 0x00 },		/* & - 0x26 - 38 */
		{ 0x08, 0x08, 0x08, 0x04, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* ' - 0x27 - 39 */
		{ 0x10, 0x08, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x04, 0x08, 0x10, 0x00 },		/* ( - 0x28 - 40 */
		{ 0x04, 0x08, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x08, 0x04, 0x00 },		/* ) - 0x29 - 41 */
		{ 0x00, 0x00, 0x00, 0x14, 0x08, 0x3e, 0x08, 0x14, 0x00, 0x00, 0x00, 0x00 },		/* * - 0x2a - 42 */
		{ 0x00, 0x00, 0x00, 0x08, 0x08, 0x3e, 0x08, 0x08, 0x00, 0x00, 0x00, 0x00 },		/* + - 0x2b - 43 */
		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x18, 0x10, 0x10, 0x08 },		/* , - 0x2c - 44 */
		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x3e, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* - - 0x2d - 45 */
		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x18, 0x18, 0x00, 0x00 },		/* . - 0x2e - 46 */
		{ 0x40, 0x40, 0x20, 0x10, 0x08, 0x08, 0x04, 0x02, 0x01, 0x01, 0x00, 0x00 },		/* / - 0x2f - 47 */
		{ 0x3e, 0x61, 0x51, 0x51, 0x49, 0x49, 0x45, 0x45, 0x43, 0x3e, 0x00, 0x00 },		/* 0 - 0x30 - 48 */
		{ 0x08, 0x0c, 0x0a, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x3e, 0x00, 0x00 },		/* 1 - 0x31 - 49 */
		{ 0x3f, 0x40, 0x40, 0x40, 0x40, 0x3e, 0x01, 0x01, 0x01, 0x7f, 0x00, 0x00 },		/* 2 - 0x32 - 50 */
		{ 0x3e, 0x41, 0x40, 0x40, 0x40, 0x3c, 0x40, 0x40, 0x41, 0x3e, 0x00, 0x00 },		/* 3 - 0x33 - 51 */
		{ 0x01, 0x21, 0x21, 0x21, 0x21, 0x7f, 0x20, 0x20, 0x20, 0x20, 0x00, 0x00 },		/* 4 - 0x34 - 52 */
		{ 0x7f, 0x01, 0x01, 0x01, 0x01, 0x3f, 0x40, 0x40, 0x41, 0x3e, 0x00, 0x00 },		/* 5 - 0x35 - 53 */
		{ 0x3e, 0x01, 0x01, 0x01, 0x01, 0x3f, 0x41, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* 6 - 0x36 - 54 */
		{ 0x7f, 0x40, 0x40, 0x40, 0x20, 0x10, 0x08, 0x08, 0x08, 0x08, 0x00, 0x00 },		/* 7 - 0x37 - 55 */
		{ 0x3e, 0x41, 0x41, 0x41, 0x41, 0x3e, 0x41, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* 8 - 0x38 - 56 */
		{ 0x3e, 0x41, 0x41, 0x41, 0x41, 0x7e, 0x40, 0x40, 0x40, 0x3e, 0x00, 0x00 },		/* 9 - 0x39 - 57 */
		{ 0x00, 0x00, 0x00, 0x18, 0x18, 0x00, 0x00, 0x18, 0x18, 0x00, 0x00, 0x00 },		/* : - 0x3a - 58 */
		{ 0x00, 0x00, 0x00, 0x00, 0x18, 0x18, 0x00, 0x00, 0x18, 0x10, 0x10, 0x08 },		/* ; - 0x3b - 59 */
		{ 0x00, 0x20, 0x10, 0x08, 0x04, 0x02, 0x04, 0x08, 0x10, 0x20, 0x00, 0x00 },		/* < - 0x3c - 60 */
		{ 0x00, 0x00, 0x00, 0x00, 0x7f, 0x00, 0x7f, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* = - 0x3d - 61 */
		{ 0x00, 0x02, 0x04, 0x08, 0x10, 0x20, 0x10, 0x08, 0x04, 0x02, 0x00, 0x00 },		/* > - 0x3e - 62 */
		{ 0x1c, 0x22, 0x41, 0x40, 0x20, 0x10, 0x08, 0x08, 0x00, 0x08, 0x00, 0x00 },		/* ? - 0x3f - 63 */
		{ 0x3e, 0x23, 0x49, 0x55, 0x55, 0x55, 0x55, 0x39, 0x03, 0x3e, 0x00, 0x00 },		/* @ - 0x40 - 64 */
		{ 0x08, 0x14, 0x22, 0x41, 0x41, 0x41, 0x7f, 0x41, 0x41, 0x41, 0x00, 0x00 },		/* A - 0x41 - 65 */
		{ 0x3f, 0x41, 0x41, 0x41, 0x41, 0x3f, 0x41, 0x41, 0x41, 0x3f, 0x00, 0x00 },		/* B - 0x42 - 66 */
		{ 0x3e, 0x41, 0x41, 0x01, 0x01, 0x01, 0x01, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* C - 0x43 - 67 */
		{ 0x3f, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3f, 0x00, 0x00 },		/* D - 0x44 - 68 */
		{ 0x7f, 0x01, 0x01, 0x01, 0x01, 0x1f, 0x01, 0x01, 0x01, 0x7f, 0x00, 0x00 },		/* E - 0x45 - 69 */
		{ 0x7f, 0x01, 0x01, 0x01, 0x01, 0x1f, 0x01, 0x01, 0x01, 0x01, 0x00, 0x00 },		/* F - 0x46 - 70 */
		{ 0x3e, 0x01, 0x01, 0x01, 0x01, 0x61, 0x41, 0x41, 0x41, 0x7e, 0x00, 0x00 },		/* G - 0x47 - 71 */
		{ 0x41, 0x41, 0x41, 0x41, 0x41, 0x7f, 0x41, 0x41, 0x41, 0x41, 0x00, 0x00 },		/* H - 0x48 - 72 */
		{ 0x7f, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x7f, 0x00, 0x00 },		/* I - 0x49 - 73 */
		{ 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x40, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* J - 0x4a - 74 */
		{ 0x21, 0x11, 0x09, 0x05, 0x03, 0x05, 0x09, 0x11, 0x21, 0x41, 0x00, 0x00 },		/* K - 0x4b - 75 */
		{ 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x7f, 0x00, 0x00 },		/* L - 0x4c - 76 */
		{ 0x41, 0x63, 0x63, 0x55, 0x55, 0x49, 0x49, 0x41, 0x41, 0x41, 0x00, 0x00 },		/* M - 0x4d - 77 */
		{ 0x41, 0x43, 0x45, 0x45, 0x49, 0x49, 0x51, 0x51, 0x61, 0x41, 0x00, 0x00 },		/* N - 0x4e - 78 */
		{ 0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* O - 0x4f - 79 */
		{ 0x3f, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3f, 0x01, 0x01, 0x01, 0x00, 0x00 },		/* P - 0x50 - 80 */
		{ 0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x49, 0x49, 0x49, 0x3e, 0x08, 0x0c },		/* Q - 0x51 - 81 */
		{ 0x3f, 0x41, 0x41, 0x41, 0x41, 0x3f, 0x03, 0x0d, 0x11, 0x61, 0x00, 0x00 },		/* R - 0x52 - 82 */
		{ 0x3e, 0x41, 0x01, 0x01, 0x01, 0x3e, 0x40, 0x40, 0x41, 0x3e, 0x00, 0x00 },		/* S - 0x53 - 83 */
		{ 0x7f, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x00, 0x00 },		/* T - 0x54 - 84 */
		{ 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* U - 0x55 - 85 */
		{ 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x22, 0x14, 0x08, 0x00, 0x00 },		/* V - 0x56 - 86 */
		{ 0x41, 0x41, 0x41, 0x49, 0x49, 0x55, 0x55, 0x63, 0x63, 0x41, 0x00, 0x00 },		/* W - 0x57 - 87 */
		{ 0x41, 0x41, 0x22, 0x14, 0x08, 0x08, 0x14, 0x22, 0x41, 0x41, 0x00, 0x00 },		/* X - 0x58 - 88 */
		{ 0x41, 0x41, 0x22, 0x14, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x00, 0x00 },		/* Y - 0x59 - 89 */
		{ 0x7f, 0x40, 0x20, 0x10, 0x08, 0x08, 0x04, 0x02, 0x01, 0x7f, 0x00, 0x00 },		/* Z - 0x5a - 90 */
		{ 0x18, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x18, 0x00 },		/* [ - 0x5b - 91 */
		{ 0x01, 0x01, 0x02, 0x04, 0x08, 0x08, 0x10, 0x20, 0x40, 0x40, 0x00, 0x00 },		/* \ - 0x5c - 92 */
		{ 0x18, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x10, 0x18, 0x00 },		/* ] - 0x5d - 93 */
		{ 0x08, 0x14, 0x22, 0x41, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* ^ - 0x5e - 94 */
		{ 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x7f, 0x00 },		/* _ - 0x5f - 95 */
		{ 0x08, 0x08, 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* ` - 0x60 - 96 */
		{ 0x00, 0x00, 0x00, 0x3e, 0x40, 0x40, 0x7e, 0x41, 0x41, 0x7e, 0x00, 0x00 },		/* a - 0x61 - 97 */
		{ 0x01, 0x01, 0x01, 0x1d, 0x23, 0x41, 0x41, 0x41, 0x21, 0x1f, 0x00, 0x00 },		/* b - 0x62 - 98 */
		{ 0x00, 0x00, 0x00, 0x3c, 0x42, 0x01, 0x01, 0x01, 0x42, 0x3c, 0x00, 0x00 },		/* c - 0x63 - 99 */
		{ 0x40, 0x40, 0x40, 0x5c, 0x62, 0x41, 0x41, 0x41, 0x42, 0x7c, 0x00, 0x00 },		/* d - 0x64 - 100 */
		{ 0x00, 0x00, 0x00, 0x1e, 0x21, 0x41, 0x3f, 0x01, 0x41, 0x3e, 0x00, 0x00 },		/* e - 0x65 - 101 */
		{ 0x38, 0x04, 0x04, 0x04, 0x04, 0x3f, 0x04, 0x04, 0x04, 0x04, 0x00, 0x00 },		/* f - 0x66 - 102 */
		{ 0x00, 0x00, 0x00, 0x3c, 0x42, 0x41, 0x41, 0x41, 0x62, 0x5c, 0x40, 0x3e },		/* g - 0x67 - 103 */
		{ 0x01, 0x01, 0x01, 0x3d, 0x43, 0x41, 0x41, 0x41, 0x41, 0x41, 0x00, 0x00 },		/* h - 0x68 - 104 */
		{ 0x00, 0x08, 0x00, 0x0f, 0x08, 0x08, 0x08, 0x08, 0x08, 0x7f, 0x00, 0x00 },		/* i - 0x69 - 105 */
		{ 0x00, 0x40, 0x00, 0x78, 0x40, 0x40, 0x40, 0x40, 0x40, 0x41, 0x42, 0x3c },		/* j - 0x6a - 106 */
		{ 0x01, 0x01, 0x41, 0x41, 0x21, 0x11, 0x1f, 0x21, 0x41, 0x41, 0x00, 0x00 },		/* k - 0x6b - 107 */
		{ 0x0f, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x7f, 0x00, 0x00 },		/* l - 0x6c - 108 */
		{ 0x00, 0x00, 0x00, 0x22, 0x55, 0x49, 0x49, 0x49, 0x49, 0x49, 0x00, 0x00 },		/* m - 0x6d - 109 */
		{ 0x00, 0x00, 0x00, 0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x00, 0x00 },		/* n - 0x6e - 110 */
		{ 0x00, 0x00, 0x00, 0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* o - 0x6f - 111 */
		{ 0x00, 0x00, 0x00, 0x1e, 0x21, 0x41, 0x41, 0x41, 0x23, 0x1d, 0x01, 0x01 },		/* p - 0x70 - 112 */
		{ 0x00, 0x00, 0x00, 0x3c, 0x42, 0x41, 0x41, 0x41, 0x62, 0x5c, 0x40, 0x40 },		/* q - 0x71 - 113 */
		{ 0x00, 0x00, 0x00, 0x3d, 0x43, 0x01, 0x01, 0x01, 0x01, 0x01, 0x00, 0x00 },		/* r - 0x72 - 114 */
		{ 0x00, 0x00, 0x00, 0x7e, 0x01, 0x01, 0x3f, 0x40, 0x40, 0x3f, 0x00, 0x00 },		/* s - 0x73 - 115 */
		{ 0x04, 0x04, 0x04, 0x3f, 0x04, 0x04, 0x04, 0x04, 0x04, 0x78, 0x00, 0x00 },		/* t - 0x74 - 116 */
		{ 0x00, 0x00, 0x00, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e, 0x00, 0x00 },		/* u - 0x75 - 117 */
		{ 0x00, 0x00, 0x00, 0x41, 0x41, 0x41, 0x41, 0x22, 0x14, 0x08, 0x00, 0x00 },		/* v - 0x76 - 118 */
		{ 0x00, 0x00, 0x00, 0x41, 0x41, 0x49, 0x5d, 0x55, 0x22, 0x22, 0x00, 0x00 },		/* w - 0x77 - 119 */
		{ 0x00, 0x00, 0x00, 0x41, 0x22, 0x14, 0x08, 0x14, 0x22, 0x41, 0x00, 0x00 },		/* x - 0x78 - 120 */
		{ 0x00, 0x00, 0x00, 0x41, 0x41, 0x41, 0x41, 0x41, 0x22, 0x1c, 0x08, 0x08 },		/* y - 0x79 - 121 */
		{ 0x00, 0x00, 0x00, 0x7f, 0x20, 0x10, 0x08, 0x04, 0x02, 0x7f, 0x00, 0x00 },		/* z - 0x7a - 122 */
		{ 0x30, 0x08, 0x08, 0x08, 0x08, 0x04, 0x08, 0x08, 0x08, 0x08, 0x30, 0x00 },		/* { - 0x7b - 123 */
		{ 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x00, 0x00 },		/* | - 0x7c - 124 */
		{ 0x0c, 0x10, 0x10, 0x10, 0x10, 0x20, 0x10, 0x10, 0x10, 0x10, 0x0c, 0x00 },		/* } - 0x7d - 125 */
		{ 0x0c, 0x12, 0x12, 0x0c, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 },		/* � - 0x7e - 126 */
	};

	fontDefinition_t fontDefinition_big = new fontDefinition_t(1, 1, 1, 1, 0x00, 0x00, 1);

	public int font_big[][] = {
		{ 0 }
	};
}
