package org.teamresistance.frc

import org.strongback.hardware.Hardware
import org.teamresistance.frc.io.joystick.JoystickConfig.JOYSTICK_CODRIVER
import org.teamresistance.frc.io.joystick.JoystickConfig.JOYSTICK_LEFT
import org.teamresistance.frc.io.joystick.JoystickConfig.JOYSTICK_RIGHT
import org.teamresistance.frc.subsystem.shooter.Shooter

class TeleopDelegate(private val shooter: Shooter) : RobotDelegate {

  override fun beforeInit() {
    val leftJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_LEFT)
    val rightJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_RIGHT)
    val coDriverJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_CODRIVER)

    // brb -> testing Redux
  }
}