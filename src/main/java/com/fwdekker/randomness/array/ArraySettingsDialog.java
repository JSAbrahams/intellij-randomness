package com.fwdekker.randomness.array;

import com.fwdekker.randomness.SettingsDialog;
import com.fwdekker.randomness.ValidationException;
import com.fwdekker.randomness.ui.ButtonGroupHelper;
import com.fwdekker.randomness.ui.JLongSpinner;
import com.intellij.openapi.ui.ValidationInfo;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;


/**
 * Dialog for settings of random array generation.
 */
@SuppressFBWarnings("NP_NULL_ON_SOME_PATH") // Initialized by UI framework
public final class ArraySettingsDialog extends SettingsDialog<ArraySettings> {
    private JPanel contentPane;
    private JLongSpinner countSpinner;
    private ButtonGroup bracketsGroup;
    private ButtonGroup separatorGroup;
    private JCheckBox spaceAfterSeparatorCheckBox;


    /**
     * Constructs a new {@code StringSettingsDialog} that uses the singleton {@code StringSettings} instance.
     */
    /* default */ ArraySettingsDialog() {
        this(ArraySettings.getInstance());
    }

    /**
     * Constructs a new {@code StringSettingsDialog} that uses the given {@code StringSettings} instance.
     *
     * @param settings the settings to manipulate with this dialog
     */
    /* default */ ArraySettingsDialog(final @NotNull ArraySettings settings) {
        super(settings);

        init();
        loadSettings();
    }


    @Override
    protected JComponent createCenterPanel() {
        return contentPane;
    }

    /**
     * Initialises custom UI components.
     * <p>
     * This method is called by the scene builder at the start of the constructor.
     */
    @SuppressWarnings("PMD.UnusedPrivateMethod") // Method used by scene builder
    private void createUIComponents() {
        countSpinner = new JLongSpinner(1, 1, Integer.MAX_VALUE);
    }


    @Override
    public void loadSettings(final @NotNull ArraySettings settings) {
        countSpinner.setValue(settings.getCount());
        ButtonGroupHelper.setValue(bracketsGroup, settings.getBrackets());
        ButtonGroupHelper.setValue(separatorGroup, settings.getSeparator());
        spaceAfterSeparatorCheckBox.setSelected(settings.isSpaceAfterSeparator());
    }

    @Override
    @SuppressFBWarnings("NP_NULL_ON_SOME_PATH") // Verified by annotation
    public void saveSettings(final @NotNull ArraySettings settings) {
        settings.setCount(Math.toIntExact(countSpinner.getValue()));
        settings.setBrackets(ButtonGroupHelper.getValue(bracketsGroup));
        settings.setSeparator(ButtonGroupHelper.getValue(separatorGroup));
        settings.setSpaceAfterSeparator(spaceAfterSeparatorCheckBox.isSelected());
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        try {
            countSpinner.validateValue();
        } catch (final ValidationException e) {
            return new ValidationInfo(e.getMessage(), e.getComponent());
        }

        return null;
    }
}
