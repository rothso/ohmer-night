package org.teamresistance.frc.defense

import org.strongback.command.CommandGroup
import org.strongback.components.Solenoid
import org.teamresistance.frc.subsystem.antler.Antlers
import org.teamresistance.frc.subsystem.antler.LowerAntlers
import org.teamresistance.frc.subsystem.antler.RaiseAntlers
import org.teamresistance.frc.subsystem.drive.DriveTimed
import org.teamresistance.frc.subsystem.drive.DriveTrain

class CrossCheval(antlers: Antlers, drive: DriveTrain) : CommandGroup() {

  companion object {
    const private val CROSS_DELAY_SECONDS = 2.0
    const private val CROSS_SPEED_ENTRANCE = 0.8
    const private val CROSS_SPEED_MIDWAY = 0.8
  }

  init {
    sequentially(
        simultaneously(
            RaiseAntlers(antlers),
            DriveTimed(drive, CROSS_SPEED_ENTRANCE, CROSS_DELAY_SECONDS)
        ),
        DriveTimed(drive, CROSS_SPEED_MIDWAY, 1.75 /* ?? */),
        LowerAntlers(antlers)
    )
  }
}