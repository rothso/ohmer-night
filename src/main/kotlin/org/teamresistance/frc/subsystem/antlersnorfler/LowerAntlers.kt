package org.teamresistance.frc.subsystem.antlersnorfler

import org.strongback.command.Command

class LowerAntlers(private val antlerSnorfler: AntlerSnorfler) : Command(antlerSnorfler) {
  override fun execute(): Boolean = antlerSnorfler.totter().isStopped
}