package org.teamresistance.frc

import org.strongback.Strongback
import org.strongback.command.CommandGroup
import org.strongback.components.Switch
import org.strongback.hardware.Hardware
import org.teamresistance.frc.io.joystick.JoystickConfig.CoDriver.BUTTON_CANCEL
import org.teamresistance.frc.io.joystick.JoystickConfig.CoDriver.BUTTON_SHOOT
import org.teamresistance.frc.io.joystick.JoystickConfig.CoDriver.BUTTON_SHOOT_OVERRIDE
import org.teamresistance.frc.io.joystick.JoystickConfig.CoDriver.BUTTON_SNORFLER_REVERSE
import org.teamresistance.frc.io.joystick.JoystickConfig.CoDriver.BUTTON_SNORFLER_SPIN
import org.teamresistance.frc.io.joystick.JoystickConfig.CoDriver.BUTTON_TOGGLE_ANTLERS
import org.teamresistance.frc.io.joystick.JoystickConfig.JOYSTICK_CODRIVER
import org.teamresistance.frc.io.joystick.JoystickConfig.JOYSTICK_LEFT
import org.teamresistance.frc.io.joystick.JoystickConfig.JOYSTICK_RIGHT
import org.teamresistance.frc.io.joystick.JoystickConfig.Left.BUTTON_DRIVE_MODE
import org.teamresistance.frc.io.joystick.JoystickConfig.Left.BUTTON_DRIVE_REVERSE
import org.teamresistance.frc.io.joystick.JoystickConfig.Right.BUTTON_ANGLE_HOLD
import org.teamresistance.frc.subsystem.antlersnorfler.AntlerSnorfler
import org.teamresistance.frc.subsystem.antlersnorfler.RaiseAntlerSnorfler
import org.teamresistance.frc.subsystem.antlersnorfler.ToggleAntlerPosition
import org.teamresistance.frc.subsystem.antlersnorfler.spin.ResumeSnorflerSpin
import org.teamresistance.frc.subsystem.antlersnorfler.spin.ReverseSnorflerSpin
import org.teamresistance.frc.subsystem.antlersnorfler.spin.StopSnorflerSpin
import org.teamresistance.frc.subsystem.antlersnorfler.spin.ToggleSnorflerSpin
import org.teamresistance.frc.subsystem.shooter.Shooter
import java.util.function.Supplier

class TeleopDelegate(private val shooter: Shooter, private val antlerSnorfler: AntlerSnorfler) : RobotDelegate {

  companion object {
    // Fluent helper functions
    infix fun Switch.and(otherSwitch: Switch): Switch = Switch.and(this, otherSwitch)
    operator fun Switch.not(): Switch = this.not()
  }

  override fun beforeInit() {
    val leftJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_LEFT)
    val rightJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_RIGHT)
    val coDriverJoystick = Hardware.HumanInterfaceDevices.logitechAttack3D(JOYSTICK_CODRIVER)

    val reactor = Strongback.switchReactor()

    // Drive mode (scaled <-> raw)
    val buttonDriveMode = leftJoystick.getButton(BUTTON_DRIVE_MODE)
    reactor.onTriggeredSubmit(buttonDriveMode, Supplier { TODO("Toggle drive mode") })

    // Reverse (front <-> back)
    val buttonToggleReverse = leftJoystick.getButton(BUTTON_DRIVE_REVERSE)
    reactor.onTriggeredSubmit(buttonToggleReverse, Supplier { TODO("Toggle reverse") })

    // Angle hold
    val buttonAngleHold = rightJoystick.getButton(BUTTON_ANGLE_HOLD)
    reactor.onTriggeredSubmit(buttonAngleHold, Supplier { TODO("Enter angle hold") })
    reactor.onUntriggeredSubmit(buttonAngleHold, Supplier { TODO("Exit angle hold") })

    // Drive, aim, shoot, repeat.
    val buttonShoot = coDriverJoystick.getButton(BUTTON_SHOOT)
    val buttonOverride = coDriverJoystick.getButton(BUTTON_SHOOT_OVERRIDE)
    reactor.onTriggeredSubmit(buttonShoot and !buttonOverride, Supplier { TODO("Enter target mode") })
    reactor.onTriggeredSubmit(buttonShoot and buttonOverride, Supplier { TODO("Shoot now") })

    // Hold snorfler reverse button to reverse the spinner and load balls
    val buttonSpinSnorfler = coDriverJoystick.getButton(BUTTON_SNORFLER_SPIN)
    val buttonReverseSnorfler = coDriverJoystick.getButton(BUTTON_SNORFLER_REVERSE)
    reactor.onTriggeredSubmit(buttonReverseSnorfler, Supplier { ReverseSnorflerSpin(antlerSnorfler) })
    reactor.onUntriggeredSubmit(buttonReverseSnorfler, Supplier { ResumeSnorflerSpin(antlerSnorfler) })
    reactor.onTriggeredSubmit(buttonSpinSnorfler, Supplier { ToggleSnorflerSpin(antlerSnorfler) })

    // Toggle antler-snorfler tilt
    val buttonToggleAntlers = coDriverJoystick.getButton(BUTTON_TOGGLE_ANTLERS)
    reactor.onTriggeredSubmit(buttonToggleAntlers, Supplier { ToggleAntlerPosition(antlerSnorfler) })

    // All the other boring buttons later...

    // Resets the heavy moving things to their home positions
    val buttonReset = coDriverJoystick.getButton(BUTTON_CANCEL)
    reactor.onTriggeredSubmit(buttonReset, Supplier {
      CommandGroup.runSimultaneously(
          RaiseAntlerSnorfler(antlerSnorfler),
          StopSnorflerSpin(antlerSnorfler)
      )
    })
  }
}