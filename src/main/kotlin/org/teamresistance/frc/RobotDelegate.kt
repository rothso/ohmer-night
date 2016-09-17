package org.teamresistance.frc

interface RobotDelegate {
  companion object {
    private val NOOP = Unit
  }

  fun beforeInit() = NOOP
  fun onInit() = NOOP
  fun onPeriodic() = NOOP
}