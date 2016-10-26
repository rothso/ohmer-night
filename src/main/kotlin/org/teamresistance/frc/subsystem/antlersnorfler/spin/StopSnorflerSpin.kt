package org.teamresistance.frc.subsystem.antlersnorfler.spin

import org.strongback.command.Command

class StopSnorflerSpin(private val spinner: SnorflerSpinner) : Command(spinner) {

  override fun execute(): Boolean {
    spinner.stopSpinning()
    return true
  }
}