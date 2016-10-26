package org.teamresistance.frc.subsystem.antlersnorfler

import org.strongback.command.Requirable
import org.strongback.components.Motor
import org.strongback.components.Solenoid
import org.strongback.components.Switch
import org.teamresistance.frc.subsystem.antlersnorfler.spin.SnorflerSpinner

class AntlerSnorfler private constructor(
    private val antlers: Antlers,
    private val snorfler: Snorfler,
    private val ballSensor: Switch)
: Requirable, SnorflerSpinner by snorfler {

  constructor(
      antlerSolenoid: Solenoid,
      snorflerMotor: Motor,
      snorflerSolenoid: Solenoid,
      ballSensor: Switch
  ) : this(Antlers(antlerSolenoid), Snorfler(snorflerMotor, snorflerSolenoid), ballSensor)

  val isTeetered: Boolean
    get() = antlers.isRaised

  val isStopped: Boolean
    get() = antlers.isStopped

  val hasBall: Boolean
    get() = ballSensor.isTriggered

  fun teeter(): AntlerSnorfler {
    snorfler.lower()
    antlers.raise()
    return this
  }

  fun totter(): AntlerSnorfler {
    snorfler.raise()
    antlers.lower()
    return this
  }

  fun raiseBoth(): AntlerSnorfler {
    antlers.raise()
    snorfler.raise()
    return this
  }

  private class Antlers(private val solenoid: Solenoid) {
    val isStopped: Boolean
      get() = solenoid.isStopped

    val isRaised: Boolean
      get() = solenoid.isExtending

    fun lower() {
      solenoid.extend()
    }

    fun raise() {
      solenoid.retract()
    }
  }

  private class Snorfler(private val motor: Motor, private val solenoid: Solenoid) : SnorflerSpinner {

    companion object {
      const private val BOULDER_LOAD_SPEED = -1.0
      const private val FORWARD_SPIN_SPEED = 1.0
    }

    override var isForwardSpinning = false
      private set

    fun raise() {
      solenoid.retract()
    }

    fun lower() {
      solenoid.extend()
    }

    override fun spinReverse() {
      motor.speed = BOULDER_LOAD_SPEED
    }

    override fun spinForward() {
      motor.speed = FORWARD_SPIN_SPEED
      isForwardSpinning = true
    }

    override fun stopSpinning() {
      motor.speed = 0.0
      isForwardSpinning = false
    }
  }
}