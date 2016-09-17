package org.teamresistance.frc.subsystem.snorfler

import org.teamresistance.frc.routine.TimedCommand

class RaiseSnorfler(private val snorfler: Snorfler) : TimedCommand(RAISE_DELAY_SECONDS, snorfler) {

  companion object {
    const private val RAISE_DELAY_SECONDS = 2.0 // No limit switch, so just wing it
  }

  override fun executeDefinite() {
    snorfler.raise()
  }
}
