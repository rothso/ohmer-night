package org.teamresistance.frc.subsystem.drive

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.livewindow.LiveWindow
import org.strongback.Strongback
import org.strongback.command.Requirable
import org.strongback.control.SoftwarePIDController
import org.strongback.drive.TankDrive

class DriveTrain(private val drive: TankDrive, private val ahrs: AHRS) : Requirable {

  companion object {
    const private val TOLERANCE_DEGREES = 5.0

    const private val kP = 0.03
    const private val kI = 0.00
    const private val kD = 0.00
    const private val kF = 181.0
  }

  private val rotationController = let {
    val type = SoftwarePIDController.SourceType.RATE
    val input = { ahrs.yaw.toDouble() }
    val output = { newSpeed: Double -> arcade(0.0, newSpeed) }

    SoftwarePIDController(type, input, output)
        .withGains(kP, kI, kD, kF)
        .withInputRange(-180.0, 180.0)
        .withOutputRange(-1.0, 1.0)
        .withTolerance(TOLERANCE_DEGREES)
        .continuousInputs(true) // Wrap-around; computes the fast path for us
        .apply {
          Strongback.executor().register(this.executable()) // Poll automatically
          Strongback.dataRecorder().register("RotationController", this.basicChannels()) // Logging
          LiveWindow.addActuator("DriveTrain", "RotationController", this) // Interactive tuning
        }
        .enable()
  }
  
  val isAtTargetHeading: Boolean
    get() = rotationController.isWithinTolerance

  fun arcade(driveSpeed: Double, rotateSpeed: Double) {
    drive.arcade(driveSpeed, rotateSpeed)
  }

  fun stop() {
    drive.stop()
  }

  fun setTargetHeading(heading: Double) {
    rotationController.withTarget(heading)
  }
}