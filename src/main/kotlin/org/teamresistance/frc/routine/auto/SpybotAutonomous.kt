package org.teamresistance.frc.routine.auto

import org.strongback.command.CommandGroup
import org.teamresistance.frc.subsystem.shooter.Shoot
import org.teamresistance.frc.subsystem.shooter.Shooter

class SpybotAutonomous(shooter: Shooter) : CommandGroup() {

  init {
    sequentially(Shoot(shooter))
  }
}