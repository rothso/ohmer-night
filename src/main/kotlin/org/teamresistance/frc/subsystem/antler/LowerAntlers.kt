package org.teamresistance.frc.subsystem.antler

import org.strongback.command.Command

class LowerAntlers(private val antlers: Antlers) : Command(antlers) {
  override fun execute(): Boolean = antlers.lower().isStopped
}