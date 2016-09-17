package org.teamresistance.frc.subsystem.antler

import org.strongback.command.Command

class RaiseAntlers(private val antlers: Antlers) : Command(antlers) {
  override fun execute(): Boolean = antlers.raise().isStopped
}