package com.fwdekker.randomness.ui;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.util.Collections;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


/**
 * A helper class for using {@link ButtonGroup}s.
 */
public final class ButtonGroupHelper {
    /**
     * Private constructor to prevent instantiation.
     */
    private ButtonGroupHelper() {
    }


    /**
     * Executes a consumer for each button in a group.
     *
     * @param group    the group of buttons
     * @param consumer the function to apply to each button
     */
    public static void forEach(final ButtonGroup group, final Consumer<AbstractButton> consumer) {
        final Enumeration<AbstractButton> buttons = group.getElements();

        while (buttons.hasMoreElements()) {
            consumer.accept(buttons.nextElement());
        }
    }

    /**
     * Returns the action command of the currently selected button, or {@code null} if no button is selected.
     *
     * @param group a {@code ButtonGroup}
     * @return the {@code String} value of the currently selected button, or {@code null} if no button is selected
     */
    public static String getValue(final ButtonGroup group) {
        return Collections.list(group.getElements()).stream()
            .filter(AbstractButton::isSelected)
            .map(AbstractButton::getActionCommand)
            .findFirst()
            .orElse(null);
    }

    /**
     * Sets the currently selected button to the button with the given action command.
     *
     * @param group a {@code ButtonGroup}
     * @param value an action command
     */
    public static void setValue(final ButtonGroup group, final String value) {
        final AbstractButton targetButton = Collections.list(group.getElements()).stream()
            .filter(button -> button.getActionCommand().equals("\0".equals(value) ? "" : value))
            .findFirst()
            .orElseThrow(() ->
                new NoSuchElementException("Could not find a button with action command `" + value + "`."));

        targetButton.setSelected(true);
    }

    /**
     * Sets the currently selected button to the button with the given action command.
     *
     * @param group a {@code ButtonGroup}
     * @param value an {@code Object} of which {@link #toString()} returns an action command
     */
    public static void setValue(final ButtonGroup group, final Object value) {
        setValue(group, value.toString());
    }
}
