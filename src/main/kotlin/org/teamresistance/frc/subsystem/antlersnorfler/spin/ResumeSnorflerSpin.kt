package org.teamresistance.frc.subsystem.antlersnorfler.spin

import org.strongback.command.Command

class ResumeSnorflerSpin(private val spinner: SnorflerSpinner) : Command(spinner) {

  override fun execute(): Boolean {
    if (spinner.isForwardSpinning) spinner.spinForward() else spinner.stopSpinning()
    return true
  }
}
