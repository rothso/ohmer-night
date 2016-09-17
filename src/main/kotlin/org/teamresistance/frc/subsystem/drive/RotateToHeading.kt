package org.teamresistance.frc.subsystem.drive

import org.strongback.command.Command

class RotateToHeading(private val drive: DriveTrain, private val heading: Int) : Command(drive) {
  override fun initialize() = drive.setTargetHeading(heading.toDouble())
  override fun execute(): Boolean = drive.isAtTargetHeading
}