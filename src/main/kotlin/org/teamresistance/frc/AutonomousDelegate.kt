package org.teamresistance.frc

import edu.wpi.first.wpilibj.networktables.NetworkTable
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.strongback.Strongback
import org.teamresistance.frc.subsystem.antler.Antlers
import org.teamresistance.frc.subsystem.shooter.Shooter
import org.teamresistance.frc.subsystem.snorfler.Snorfler
import org.teamresistance.frc.routine.auto.ScoringAutonomous
import org.teamresistance.frc.subsystem.drive.DriveTrain
import org.teamresistance.frc.defense.Defense

class AutonomousDelegate(
    private val antlers: Antlers,
    private val drive: DriveTrain,
    private val snorfler: Snorfler,
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
    Strongback.submit(ScoringAutonomous(drive, antlers, snorfler, shooter, defense, 0, 0, table))
  }
}