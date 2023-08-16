package lineageos.providers;

import android.content.ContentResolver;
import android.provider.Settings;

import java.util.List;

/**
 * LineageSettings contains Lineage specific preferences in System, Secure, and Global.
 */
public final class LineageSettings {
    /**
     * System settings, containing miscellaneous Lineage system preferences. This table holds simple
     * name/value pairs. There are convenience functions for accessing individual settings entries.
     */
    public static final class System extends Settings.NameValueTable {
        // region Methods

        /** @hide */
        public static int getIntForUser(ContentResolver cr, String name, int def, int userId) {
            return 0;
        }

        /** @hide */
        public static boolean putIntForUser(ContentResolver cr, String name, int value,
                int userId) {
            return false;
        }

        // endregion

        // region System Settings

        /**
         * Whether or not volume button music controls should be enabled to seek media tracks
         * 0 = 0ff, 1 = on
         */
        public static final String VOLBTN_MUSIC_CONTROLS = "volbtn_music_controls";

        /**
         * Whether to wake the screen with the volume keys, the value is boolean.
         * 0 = 0ff, 1 = on
         */
        public static final String VOLUME_WAKE_SCREEN = "volume_wake_screen";

        // endregion
    }
}
