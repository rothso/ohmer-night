package org.teamresistance.frc.routine

import org.strongback.command.Command
import org.strongback.command.Requirable

abstract class TimedCommand(seconds: Double, vararg requirements: Requirable) : Command(seconds, *requirements) {

  final override fun execute(): Boolean {
    executeDefinite()
    return false // command will time out on its own
  }

  protected abstract fun executeDefinite()
}