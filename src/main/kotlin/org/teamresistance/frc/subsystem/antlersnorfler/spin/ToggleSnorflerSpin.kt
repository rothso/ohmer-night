package org.teamresistance.frc.subsystem.antlersnorfler.spin

import org.strongback.command.Command

class ToggleSnorflerSpin(private val spinner: SnorflerSpinner) : Command(spinner) {

  override fun execute(): Boolean {
    if (spinner.isForwardSpinning) spinner.stopSpinning() else spinner.spinForward()
    return true
  }
}