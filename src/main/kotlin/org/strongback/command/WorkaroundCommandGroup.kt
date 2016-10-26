package org.strongback.command

import org.strongback.annotation.Experimental

/**
 * Workaround for #81: Nesting CommandGroups breaks scheduler
 * https://github.com/strongback/strongback-java/issues/81
 */
@Experimental
open class WorkaroundCommandGroup : CommandGroup() {
  override fun getCommands(): Array<out Command> = (super.getRoot() as CommandGroup).commands
}