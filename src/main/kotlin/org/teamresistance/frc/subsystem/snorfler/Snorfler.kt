package org.teamresistance.frc.subsystem.snorfler

import org.strongback.command.Requirable
import org.strongback.components.Motor

class Snorfler(private val motor: Motor) : Requirable {

  companion object {
    const private val SNORFLER_UP_SPEED = 1.0
  }

  fun raise() {
    motor.speed = SNORFLER_UP_SPEED
  }

  fun stop() {
    motor.speed = 0.0
  }
}