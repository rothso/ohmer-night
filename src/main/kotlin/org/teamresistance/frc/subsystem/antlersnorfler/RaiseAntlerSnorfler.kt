package org.teamresistance.frc.subsystem.antlersnorfler

import org.strongback.command.Command

class RaiseAntlerSnorfler(private val antlerSnorfler: AntlerSnorfler) : Command(antlerSnorfler) {
  override fun execute(): Boolean = antlerSnorfler.raiseBoth().isStopped
}