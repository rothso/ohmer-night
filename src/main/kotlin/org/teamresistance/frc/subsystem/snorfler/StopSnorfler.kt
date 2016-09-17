package org.teamresistance.frc.subsystem.snorfler

import org.strongback.command.Command

class StopSnorfler(private val snorfler: Snorfler) : Command() {

  override fun execute(): Boolean {
    snorfler.stop()
    return true
  }
}
