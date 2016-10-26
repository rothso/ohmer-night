package org.teamresistance.frc.subsystem.antlersnorfler

import org.strongback.command.Command

class ToggleAntlerPosition(private val antlerSnorfler: AntlerSnorfler) : Command(antlerSnorfler) {

  override fun execute(): Boolean {
    if (antlerSnorfler.isTeetered) {
      antlerSnorfler.totter()
    } else {
      if (!antlerSnorfler.hasBall) {
        antlerSnorfler.teeter()
      }
    }

    return antlerSnorfler.isStopped
  }
}