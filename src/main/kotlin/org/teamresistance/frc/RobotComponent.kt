package org.teamresistance.frc

import dagger.Component
import org.teamresistance.frc.io.hardware.HardwareModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    RobotModule::class,
    HardwareModule::class))
interface RobotComponent {
  fun inject(robot: Robot)
}