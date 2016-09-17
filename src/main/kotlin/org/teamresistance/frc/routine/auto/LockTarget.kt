package org.teamresistance.frc.routine.auto

import edu.wpi.first.wpilibj.networktables.NetworkTable
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import org.strongback.command.Command
import org.teamresistance.frc.subsystem.drive.DriveTrain

class LockTarget(private val drive: DriveTrain, private val contoursTable: NetworkTable) : Command() {

  companion object {
    const private val KEY_AREAS = "area"
    const private val KEY_CENTERXS = "centerX"
    const private val KEY_WIDTHS = "width"
    const private val SCREEN_WIDTH = 320
    const private val DRIVE_SPEED = 0.38

    private val CENTER_THRESHOLD = -0.08..0.08
    private val TARGET_SPEED_RANGE = -0.5..0.5
  }

  override fun execute(): Boolean {
    val areas = contoursTable.getNumberArray(KEY_AREAS, emptyArray())
    val centers = contoursTable.getNumberArray(KEY_CENTERXS, emptyArray())

    // Verify the arrays are parallel
    if (areas.size != centers.size) {
      return false
    }

    // Get largest object or abort if no objects detected
    val maxArea = areas.max() ?: return false
    val index = areas.indexOf(maxArea)

    val rawCenterX = centers[index]!!
    val centerX = rawCenterX / (SCREEN_WIDTH / 2) - 1
    val width = contoursTable.getNumberArray(KEY_WIDTHS, emptyArray())[index]!!

    SmartDashboard.putNumber("CenterX (Raw)", rawCenterX)
    SmartDashboard.putNumber("CenterX (Scaled)", centerX)
    SmartDashboard.putNumber("Width", width)

    val canShoot = centerX in CENTER_THRESHOLD
    SmartDashboard.putBoolean("Can shoot?", canShoot)

    return if (canShoot) {
      drive.stop()
      true // Target acquired!
    } else {
      val speed = (DRIVE_SPEED * Math.signum(centerX)).coerceIn(TARGET_SPEED_RANGE)
      drive.arcade(speed, 0.0)
      false // Keep driving
    }
  }
}