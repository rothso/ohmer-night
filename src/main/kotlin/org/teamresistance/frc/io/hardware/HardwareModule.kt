package org.teamresistance.frc.io.hardware

import com.kauailabs.navx.frc.AHRS
import dagger.Module
import dagger.Provides
import edu.wpi.first.wpilibj.SPI
import org.strongback.components.Motor
import org.strongback.components.Solenoid
import org.strongback.drive.TankDrive
import org.strongback.hardware.Hardware
import org.teamresistance.frc.io.hardware.HardwareConfig.ANTLERS_SOLENOID_CHANNEL
import org.teamresistance.frc.io.hardware.HardwareConfig.DRIVE_MOTOR_CHANNEL_LEFT
import org.teamresistance.frc.io.hardware.HardwareConfig.DRIVE_MOTOR_CHANNEL_RIGHT
import org.teamresistance.frc.io.hardware.HardwareConfig.DRIVE_MOTOR_INVERTED_LEFT
import org.teamresistance.frc.io.hardware.HardwareConfig.DRIVE_MOTOR_INVERTED_RIGHT
import org.teamresistance.frc.io.hardware.HardwareConfig.SHOOTER_SOLENOID_CHANNEL
import org.teamresistance.frc.io.hardware.HardwareConfig.SNORFLER_SPIN_MOTOR_CHANNEL
import org.teamresistance.frc.subsystem.antler.Antlers
import org.teamresistance.frc.subsystem.drive.DriveTrain
import org.teamresistance.frc.subsystem.shooter.Shooter
import org.teamresistance.frc.subsystem.snorfler.Snorfler
import javax.inject.Singleton

@Module
class HardwareModule {

  // Static helper methods
  companion object {
    private fun solenoid(channel: Int, direction: Solenoid.Direction): Solenoid {
      return Hardware.Solenoids.doubleSolenoid(channel, channel, direction)
    }

    private fun Motor.invertIf(condition: Boolean): Motor {
      return if (condition) this.invert() else this
    }
  }

  @Provides @Singleton
  fun provideDriveTrain(): DriveTrain {
    val leftMotor = Hardware.Motors.victorSP(DRIVE_MOTOR_CHANNEL_LEFT).invertIf(DRIVE_MOTOR_INVERTED_LEFT)
    val rightMotor = Hardware.Motors.victorSP(DRIVE_MOTOR_CHANNEL_RIGHT).invertIf(DRIVE_MOTOR_INVERTED_RIGHT)
    return DriveTrain(TankDrive(leftMotor, rightMotor), AHRS(SPI.Port.kMXP))
  }

  @Provides @Singleton
  fun provideAntlers(): Antlers {
    return Antlers(solenoid(ANTLERS_SOLENOID_CHANNEL, Solenoid.Direction.STOPPED))
  }

  @Provides @Singleton
  fun provideSnorfler(): Snorfler {
    return Snorfler(Hardware.Motors.victorSP(SNORFLER_SPIN_MOTOR_CHANNEL))
  }

  @Provides @Singleton
  fun provideShooter(): Shooter {
    return Shooter(solenoid(SHOOTER_SOLENOID_CHANNEL, Solenoid.Direction.STOPPED))
  }
}