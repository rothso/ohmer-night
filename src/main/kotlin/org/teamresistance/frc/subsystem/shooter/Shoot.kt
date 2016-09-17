package org.teamresistance.frc.subsystem.shooter

import org.teamresistance.frc.routine.TimedCommand

class Shoot(private val shooter: Shooter) : TimedCommand(SHOOTER_DELAY_SECONDS, shooter) {

  companion object {
    const private val SHOOTER_DELAY_SECONDS = 1.0
  }

  override fun executeDefinite() {
    shooter.shoot()
  }
}