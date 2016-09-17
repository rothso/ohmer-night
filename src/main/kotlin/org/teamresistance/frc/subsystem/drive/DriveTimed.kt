package org.teamresistance.frc.subsystem.drive

import org.teamresistance.frc.routine.TimedCommand

class DriveTimed(private val drive: DriveTrain, private val speed: Double, seconds: Double) : TimedCommand(seconds, drive) {
  override fun executeDefinite() = drive.arcade(speed, 0.0)
}