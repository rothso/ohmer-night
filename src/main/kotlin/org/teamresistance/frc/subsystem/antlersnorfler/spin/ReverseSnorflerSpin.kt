package org.teamresistance.frc.subsystem.antlersnorfler.spin

import org.strongback.command.Command

class ReverseSnorflerSpin(private val spinner: SnorflerSpinner) : Command(spinner) {

  override fun execute(): Boolean {
    spinner.spinReverse()
    return true
  }
}