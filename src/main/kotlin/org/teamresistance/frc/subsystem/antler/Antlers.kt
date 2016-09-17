package org.teamresistance.frc.subsystem.antler

import org.strongback.command.Requirable
import org.strongback.components.Solenoid

class Antlers(private val solenoid: Solenoid) : Requirable {
  val isStopped: Boolean
    get() = solenoid.isStopped

  fun lower(): Antlers {
    solenoid.retract()
    return this
  }

  fun raise(): Antlers {
    solenoid.extend()
    return this
  }
}