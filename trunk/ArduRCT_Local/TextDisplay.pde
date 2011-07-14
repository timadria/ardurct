#include <LCD_PCD8544.h>
#include "ArduRCT_Local.h"

LCD_PCD8544 lcdT(LCD_SCE_TEXT_PIN);

uint8_t tdHeader[] = { 
    0x5F, 0x45, 0x5B, 0x40, 0x54, 0x48, 0x54, 0x40, 0x40, 0x50, 0x50, 0x50, 0x58, 0x58, 0x58, 0x5C,
    0x5C, 0x5C, 0x5E, 0x5E, 0x5E, 0x5F, 0x5F, 0x5F, 0x40, 0x40, 0x40, 0x7F, 0x40, 0x40, 0x5F, 0x45, 
	0x5B, 0x40, 0x5F, 0x55, 0x51, 0x40, 0x5F, 0x42, 0x5F, 0x40, 0x40, 0x5F, 0x51, 0x51, 0x51, 0x51, 
	0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 
	0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 0x51, 
	0x51, 0x5F, 0x4E, 0x4E };

uint8_t tdThrottle = 0;
uint8_t tdYaw = 0;
uint8_t tdPitch = 0;
uint8_t tdRoll = 0;

#define TD_SIGNAL_START 9
#define TD_SIGNAL_BAR 3
#define TD_BATTERY_START 44
#define TD_BATTERY_WIDTH 37


void tdSetup() {
    lcdT.begin(2);    
        
    lcdT.setCursor(0, 2);
    lcdT.print("Th");    
    lcdT.setCursor(0, 3);
    lcdT.print("Ya");    
    lcdT.setCursor(0, 4);
    lcdT.print("Pi");    
    lcdT.setCursor(0, 5);
    lcdT.print("Ro");    
}

void tdRefreshSignal(uint8_t signalPct, uint8_t bar, uint8_t strength) {
    if (signalPct >= 17*bar) {
       for (uint8_t i=0; i<TD_SIGNAL_BAR; i++) tdHeader[TD_SIGNAL_START+(bar-1)*TD_SIGNAL_BAR+i] = strength; 
    }
}
    
void tdRefreshSignalAndBattery(uint8_t signalPct, uint8_t batteryPct) {
    uint8_t i;
    
    // draw the signal strength (percentage)
    for (i=0; i<TD_SIGNAL_BAR*5; i++) tdHeader[TD_SIGNAL_START+i] = 0x40; 
    tdRefreshSignal(signalPct, 1, 0x50);
    tdRefreshSignal(signalPct, 2, 0x58);
    tdRefreshSignal(signalPct, 3, 0x5C);
    tdRefreshSignal(signalPct, 4, 0x5E);
    tdRefreshSignal(signalPct, 5, 0x5F);

    // draw the battery level (percentage)
    uint16_t battery = batteryPct * TD_BATTERY_WIDTH / 100;
    for (i=battery; i<TD_BATTERY_WIDTH; i++) tdHeader[TD_BATTERY_START+i] = 0x51;
    for (i=0; i<=battery; i++) tdHeader[TD_BATTERY_START+i] = 0x5F;

    // refresh the header
    lcdT.drawBitmap(tdHeader, 0, 0, 84, 8);    
    
}

void tdRefresh(uint8_t throttle, uint8_t yaw, uint8_t pitch, uint8_t roll) {
    if (throttle != tdThrottle) {
        tdThrottle = throttle;
        lcdT.drawProgressBar(2, 2, 12, throttle, ON);
    }
    if (yaw != tdYaw) {
        tdYaw = yaw;
        lcdT.drawProgressBar(2, 3, 12, yaw, OFF);
    }
    if (pitch != tdPitch) {
        tdPitch = pitch;
        lcdT.drawProgressBar(2, 4, 12, pitch, OFF);
    }
    if (roll != tdRoll) {
        tdRoll = roll;
        lcdT.drawProgressBar(2, 5, 12, roll, OFF);
    }
}