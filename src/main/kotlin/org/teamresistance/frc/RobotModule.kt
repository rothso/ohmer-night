package org.teamresistance.frc

import dagger.Module
import dagger.Provides
import org.teamresistance.frc.subsystem.antlersnorfler.AntlerSnorfler
import org.teamresistance.frc.subsystem.drive.DriveTrain
import org.teamresistance.frc.subsystem.shooter.Shooter
import javax.inject.Singleton

@Module
class RobotModule {

  @Provides @Singleton
  fun provideAutoDelegate(antlerSnorfler: AntlerSnorfler, drive: DriveTrain, shooter: Shooter): AutonomousDelegate {
    return AutonomousDelegate(antlerSnorfler, drive, shooter)
  }

  @Provides @Singleton
  fun provideTeleopDelegate(antlerSnorfler: AntlerSnorfler, shooter: Shooter): TeleopDelegate {
    return TeleopDelegate(shooter, antlerSnorfler)
  }
}