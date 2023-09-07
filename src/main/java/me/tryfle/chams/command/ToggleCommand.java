package me.tryfle.chams.command;

import me.tryfle.chams.Main;
import net.weavemc.loader.api.command.Command;
import org.jetbrains.annotations.NotNull;
import static me.tryfle.chams.Main.enabled;

public class ToggleCommand extends Command {
    public ToggleCommand() {
        super("togglechams");
    }

    @Override
    public void handle(@NotNull String[] args) {
        Main.setEnabled(!enabled);
    }
}