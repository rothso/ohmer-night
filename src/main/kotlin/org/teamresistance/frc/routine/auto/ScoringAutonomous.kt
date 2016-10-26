package org.teamresistance.frc.routine.auto

import edu.wpi.first.wpilibj.networktables.NetworkTable
import org.strongback.command.CommandGroup
import org.teamresistance.frc.defense.CrossCheval
import org.teamresistance.frc.defense.Defense
import org.teamresistance.frc.subsystem.antlersnorfler.AntlerSnorfler
import org.teamresistance.frc.subsystem.antlersnorfler.LowerAntlers
import org.teamresistance.frc.subsystem.antlersnorfler.RaiseAntlerSnorfler
import org.teamresistance.frc.subsystem.antlersnorfler.spin.ResumeSnorflerSpin
import org.teamresistance.frc.subsystem.drive.DriveTimed
import org.teamresistance.frc.subsystem.drive.DriveTrain
import org.teamresistance.frc.subsystem.drive.RotateToHeading
import org.teamresistance.frc.subsystem.shooter.Shoot
import org.teamresistance.frc.subsystem.shooter.Shooter

class ScoringAutonomous(drive: DriveTrain, antlerSnorfler: AntlerSnorfler, shooter: Shooter,
                        defense: Defense, gate: Int, goal: Int, contoursTable: NetworkTable) : CommandGroup() {

  companion object {
    const private val DRIVE_SPEED = 0.65
    const private val DEFENSE_DRIVE_TIME = 1.25

    // [gate][goal]
    private val LINE_DRIVE_TIMES = arrayOf(
        doubleArrayOf(2.25, 1.17, 0.00),
        doubleArrayOf(0.82, 1.17, 0.00),
        doubleArrayOf(0.00, 1.17, 0.50),
        doubleArrayOf(0.00, 1.17, 1.20))

    private val LINE_HEADINGS = arrayOf(-30, -90, -150)

    // [gate][goal]
    private val GOAL_DRIVE_TIMES = arrayOf(
        doubleArrayOf(0.00, 1.03, 0.00),
        doubleArrayOf(0.50, 0.47, 0.00),
        doubleArrayOf(0.00, 0.09, 0.93),
        doubleArrayOf(0.00, 0.25, 0.28))
  }

  init {
    // For now, crash if defense is not Cheval
    val crossDefense = when (defense) {
      Defense.CHEVAL -> CrossCheval(antlerSnorfler, drive)
      else -> throw UnsupportedOperationException("Auto defense $defense is not supported.")
    }

    sequentially(
        simultaneously(
            // Drive to defenses
            DriveTimed(drive, DRIVE_SPEED, DEFENSE_DRIVE_TIME),
            LowerAntlers(antlerSnorfler)
        ),
        crossDefense,
        simultaneously(
            // Drive to line
            DriveTimed(drive, DRIVE_SPEED, LINE_DRIVE_TIMES[gate][goal]),
            RaiseAntlerSnorfler(antlerSnorfler)
        ),
        simultaneously(
            RotateToHeading(drive, LINE_HEADINGS[goal]),
            RaiseAntlerSnorfler(antlerSnorfler)
        ),
        // Drive to goal
        DriveTimed(drive, DRIVE_SPEED, GOAL_DRIVE_TIMES[gate][goal]),
        simultaneously(
            ResumeSnorflerSpin(antlerSnorfler),
            LockTarget(drive, contoursTable)
        ),
        Shoot(shooter)
    )
  }
}