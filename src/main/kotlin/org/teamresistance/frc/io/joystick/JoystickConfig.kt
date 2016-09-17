package org.teamresistance.frc.io.joystick

object JoystickConfig {
  const val JOYSTICK_LEFT = 0
  const val JOYSTICK_RIGHT = 1
  const val JOYSTICK_CODRIVER = 2

  object Left {
    const val BUTTON_DRIVE_MODE = 3
    const val BUTTON_DRIVE_REVERSE = 1
  }

  object Right {
    const val BUTTON_ANGLE_HOLD = 1
  }

  object CoDriver {
    const val BUTTON_SHOOT = 1
    const val BUTTON_SHOOT_OVERRIDE = 11
    const val BUTTON_ANTLER = 4
    const val BUTTON_SNORFLER = 3
    const val BUTTON_SNORFLER_REVERSE = 10
    const val BUTTON_FOOT_TOGGLE = 5
    const val BUTTON_LIFTER_TILT = 6
    const val BUTTON_LIFTER_POSITION = 9
    const val BUTTON_PORTCULLIS = 7
    const val BUTTON_DRAWBRIDGE = 8
    const val BUTTON_CANCEL = 2
  }
}