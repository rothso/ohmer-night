package org.teamresistance.frc.io.hardware

object HardwareConfig {
  // Drive motors ----------------------------------------------
  const val DRIVE_MOTOR_CHANNEL_LEFT = 0
  const val DRIVE_MOTOR_CHANNEL_RIGHT = 1
  const val DRIVE_MOTOR_INVERTED_LEFT = true
  const val DRIVE_MOTOR_INVERTED_RIGHT = true

  // Snorfler --------------------------------------------------
  const val SNORFLER_MOVE_SOLENOID_CHANNEL = 3

  const val SNORFLER_SPIN_MOTOR_CHANNEL = 3

  const val SNORFLER_BALL_SENSOR_CHANNEL = 0
  const val SNORFLER_BALL_SENSOR_INVERTED = true

  // Shooter ---------------------------------------------------
  const val SHOOTER_SOLENOID_CHANNEL = 5

  // Antlers ---------------------------------------------------
  const val ANTLERS_SOLENOID_CHANNEL = 1

  // Lifter ----------------------------------------------------
  const val LIFTER_TILT_SOLENOID_CHANNEL = 2

  const val LIFTER_POSITION_MOTOR_CHANNEL = 2
  const val LIFTER_POSITION_MOTOR_INVERTED = true

  const val LIFTER_SWITCH_CHANNEL_TOP = 3
  const val LIFTER_SWITCH_CHANNEL_MIDDLE = 4
  const val LIFTER_SWITCH_CHANNEL_BOTTOM = 5
  const val LIFTER_SWITCH_INVERTED_TOP = false
  const val LIFTER_SWITCH_INVERTED_MIDDLE = true
  const val LIFTER_SWITCH_INVERTED_BOTTOM = true

  // Flipper ---------------------------------------------------
  const val FLIPPER_SOLENOID_CHANNEL = 4
  const val FLIPPER_SWITCH_CHANNEL_TOP = 1
  const val FLIPPER_SWITCH_CHANNEL_BOTTOM = 2
  const val FLIPPER_SWITCH_INVERTED_TOP = true
  const val FLIPPER_SWITCH_INVERTED_BOTTOM = true

  // Compressor ------------------------------------------------
  const val COMPRESSOR_RELAY_CHANNEL = 0

  // Lights ----------------------------------------------------
  const val BRIGHT_LIGHTS_PWM_CHANNEL = 9
  const val LIGHTS_SOLENOID_CHANNEL_LIFTER_1 = 6
  const val LIGHTS_SOLENOID_CHANNEL_LIFTER_2 = 0
  const val LIGHTS_SOLENOID_CHANNEL_SNORFLER = 7
}

