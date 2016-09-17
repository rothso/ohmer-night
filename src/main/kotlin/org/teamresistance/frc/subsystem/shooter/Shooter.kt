package org.teamresistance.frc.subsystem.shooter

import org.strongback.command.Requirable
import org.strongback.components.Solenoid

class Shooter(private val solenoid: Solenoid) : Requirable {

  fun shoot() {
    solenoid.extend().retract()
  }
}