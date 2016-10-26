package org.teamresistance.frc.subsystem.antlersnorfler

import org.strongback.command.Command

class LowerSnorfler(private val antlerSnorfler: AntlerSnorfler) : Command(antlerSnorfler) {
  override fun execute(): Boolean = antlerSnorfler.teeter().isStopped
}