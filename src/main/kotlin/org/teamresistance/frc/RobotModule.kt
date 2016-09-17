package org.teamresistance.frc

import dagger.Module
import dagger.Provides
import org.teamresistance.frc.subsystem.antler.Antlers
import org.teamresistance.frc.subsystem.drive.DriveTrain
import org.teamresistance.frc.subsystem.shooter.Shooter
import org.teamresistance.frc.subsystem.snorfler.Snorfler
import javax.inject.Singleton

@Module
class RobotModule {

  @Provides @Singleton
  fun provideAutoDelegate(antlers: Antlers, drive: DriveTrain, snorfler: Snorfler, shooter: Shooter): AutonomousDelegate {
    return AutonomousDelegate(antlers, drive, snorfler, shooter)
  }

  @Provides @Singleton
  fun provideTeleopDelegate(shooter: Shooter): TeleopDelegate {
    return TeleopDelegate(shooter)
  }
}