package org.teamresistance.frc

import edu.wpi.first.wpilibj.IterativeRobot
import org.strongback.Strongback
import javax.inject.Inject

class Robot : IterativeRobot() {
  @Inject lateinit var autonomousDelegate: AutonomousDelegate
  @Inject lateinit var teleopDelegate: TeleopDelegate

  override fun robotInit() {
    // Configure Strongback logging verbosity; just for now, only record commands.
    Strongback.configure().recordNoEvents().recordNoData().initialize()

    // Instantiate hardware components, inject our delegates
    DaggerRobotComponent.builder().build().inject(this)

    autonomousDelegate.beforeInit()
    teleopDelegate.beforeInit()
  }

  override fun autonomousInit() {
    // Start Strongback for the first time
    Strongback.start()
    autonomousDelegate.onInit()
  }

  override fun disabledInit() {
    // Flush and kill commands
    Strongback.disable()
  }
}

