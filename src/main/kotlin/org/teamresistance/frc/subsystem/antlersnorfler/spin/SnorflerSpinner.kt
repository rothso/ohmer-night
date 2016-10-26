package org.teamresistance.frc.subsystem.antlersnorfler.spin

import org.strongback.command.Requirable

interface SnorflerSpinner : Requirable {
  val isForwardSpinning: Boolean

  fun spinReverse()
  fun spinForward()
  fun stopSpinning()
}