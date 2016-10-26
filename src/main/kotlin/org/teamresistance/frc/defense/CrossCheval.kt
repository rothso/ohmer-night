package org.teamresistance.frc.defense

import org.strongback.command.WorkaroundCommandGroup
import org.teamresistance.frc.subsystem.antlersnorfler.AntlerSnorfler
import org.teamresistance.frc.subsystem.antlersnorfler.LowerAntlers
import org.teamresistance.frc.subsystem.antlersnorfler.RaiseAntlerSnorfler
import org.teamresistance.frc.subsystem.drive.DriveTimed
import org.teamresistance.frc.subsystem.drive.DriveTrain

class CrossCheval(antlerSnorfler: AntlerSnorfler, drive: DriveTrain) : WorkaroundCommandGroup() {

  companion object {
    const private val CROSS_DELAY_SECONDS = 2.0
    const private val CROSS_SPEED_ENTRANCE = 0.8
    const private val CROSS_SPEED_MIDWAY = 0.8
  }

  init {
    sequentially(
        simultaneously(
            RaiseAntlerSnorfler(antlerSnorfler),
            DriveTimed(drive, CROSS_SPEED_ENTRANCE, CROSS_DELAY_SECONDS)
        ),
        DriveTimed(drive, CROSS_SPEED_MIDWAY, 1.75 /* ?? */),
        LowerAntlers(antlerSnorfler)
    )
  }
}