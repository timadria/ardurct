package com.google.code.ardurct.libraries.eventManager;

public interface IEventDefines {
	
	public static int EVENT_MANAGER_CYCLE = 20;

	public static int EVENT_REPEATING_CYLES = 10;
	public static int EVENT_FAST_REPEATING_CYLES = 3;
	
	public final static int EVENT_ANY_VALUE = 0xFF;
	public final static int EVENT_NO_VALUE = 0xFF;
	
	public final static int EVENT_SYSTEM_TICK = 0x10;

	public final static int EVENT_TIME = 0x20;	
	public final static int EVENT_TIME_SECOND = 0x22;
	public final static int EVENT_TIME_MINUTE = 0x23;
	public final static int EVENT_TIME_HOUR = 0x24;
	public final static int EVENT_TIME_DAY = 0x25;
	public final static int EVENT_TIME_MONTH = 0x26;
	public final static int EVENT_TIME_YEAR = 0x27;	
	public final static int EVENT_TIME_ALARM = 0x2F;
	
	public final static int EVENT_SWITCH = 0x30;	
	public final static int EVENT_SWITCH_PRESSED = 0x31;
	public final static int EVENT_SWITCH_RELEASED = 0x32;
	public final static int EVENT_SWITCH_REPEATING = 0x33;

	public final static int EVENT_TOUCHPANEL = 0x40;	
	public final static int EVENT_TOUCHPANEL_PRESSED = 0x41;	
	public final static int EVENT_TOUCHPANEL_REPEAT_PRESSED = 0x42;
	public final static int EVENT_TOUCHPANEL_DRAGGED = 0x43;	
	public final static int EVENT_TOUCHPANEL_RELEASED = 0x44;	

	public final static int EVENT_ANALOG = 0x50;	
	public final static int EVENT_ANALOG_DECREASE = 0x51;	
	public final static int EVENT_ANALOG_INCREASE = 0x52;	

	public final static int EVENT_ENCODER = 0x60;	
	public final static int EVENT_ENCODER_DECREASE = 0x61;	
	public final static int EVENT_ENCODER_INCREASE = 0x62;	

	public final static int TOUCHPANEL_NO_TOUCH = 0xFFFF;
	public final static int TOUCHPANEL_TOLERANCE = 2;
	
	public final static int ANALOG_NO_VALUE = 0xFFFF;
	public final static int ANALOG_AVERAGING = 2;
	public final static int ANALOG_TOLERANCE = 4;
	public final static int ANALOG_RESOLUTION_16B = 65536;
	public final static int ANALOG_RESOLUTION_12B = 1024;
	public final static int ANALOG_RESOLUTION_8B = 256;
	public final static int ANALOG_RESOLUTION_100 = 100;
	public final static int ANALOG_RESOLUTION_4B = 16;
	public final static int ANALOG_HARDWARE_RESOLUTION = ANALOG_RESOLUTION_12B;
	public static final int ANALOG_MAX_AVERAGING = 6;
	
	public final static int EVENT_STATE_DEBOUNCING_UP = 0;
	public final static int EVENT_STATE_RELEASED = 2;
	public final static int EVENT_STATE_IDLE = 3;
	public final static int EVENT_STATE_DEBOUNCING_DOWN = 4;
	public final static int EVENT_STATE_PRESSED = 6;
	public final static int EVENT_STATE_PRESSED_MOTIONLESS = 7;
	public final static int EVENT_STATE_DRAGGED = 8;
	public final static int EVENT_STATE_REPEATING = 20;
	public final static int EVENT_STATE_FAST_REPEATING = 80;
	
	public final static int EVENT_HANDLING_DONE = 1;
	public final static int EVENT_HANDLING_EXIT = -1;
	public final static int EVENT_HANDLING_VOID = 0;
	
	public final static int RTC_HOUR10   = 0;
	public final static int RTC_HOUR1    = 1;
	public final static int RTC_MINUTE10 = 2;
	public final static int RTC_MINUTE1  = 3;
	public final static int RTC_SECOND10 = 4;
	public final static int RTC_SECOND1  = 5;

	public final static int SUNDAY    =  0;
	public final static int MONDAY    =  1;
	public final static int TUESDAY   =  2;
	public final static int WEDNESDAY =  3;
	public final static int THURSDAY  =  4;
	public final static int FRIDAY    =  5;
	public final static int SATURDAY  =  6;

	public final static int JANUARY    = 1;
	public final static int FEBRUARY   = 2;
	public final static int MARCH      = 3;
	public final static int APRIL      = 4;
	public final static int MAY        = 5;
	public final static int JUNE       = 6;
	public final static int JULY       = 7;
	public final static int AUGUST     = 8;
	public final static int SEPTEMBER  = 9;
	public final static int OCTOBER    = 10;
	public final static int NOVEMBER   = 11;
	public final static int DECEMBER   = 12;

	public final static boolean RTC_WITH_DAY_OF_WEEK = true;
	public final static boolean RTC_WITHOUT_DAY_OF_WEEK = false;
	public final static boolean RTC_WITH_SECONDS = true;
	public final static boolean RTC_WITHOUT_SECONDS = false;

	public final static int RTC_ALARM_ON = 0x80;
	public final static int RTC_ALARM_OFF = 0x00;

}
