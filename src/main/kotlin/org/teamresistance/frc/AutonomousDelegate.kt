package org.teamresistance.frc

import edu.wpi.first.wpilibj.networktables.NetworkTable
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.strongback.Strongback
import org.teamresistance.frc.subsystem.shooter.Shooter
import org.teamresistance.frc.routine.auto.ScoringAutonomous
import org.teamresistance.frc.subsystem.drive.DriveTrain
import org.teamresistance.frc.defense.Defense
import org.teamresistance.frc.subsystem.antlersnorfler.AntlerSnorfler

class AutonomousDelegate(
    private val antlerSnorfler: AntlerSnorfler,
    private val drive: DriveTrain,
    private val shooter: Shooter
) : RobotDelegate {

  private val defenseChooser = SendableChooser().apply {
    Defense.values().forEach { defense -> addObject(defense.humanizedName, defense) }
  }

  override fun beforeInit() {
    SmartDashboard.putData("Autonomous Defense", defenseChooser)
  }

  override fun onInit() {
    val defense = defenseChooser.selected as Defense
    val table = NetworkTable.getTable("Dummy table")

    // Run the scoring autonomous routine
    Strongback.submit(ScoringAutonomous(drive, antlerSnorfler, shooter, defense, 0, 0, table))
  }
}