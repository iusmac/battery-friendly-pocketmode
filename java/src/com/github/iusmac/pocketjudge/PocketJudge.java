/*
 * Copyright (C) 2022 iusmac <iusico.maxim@libero.it>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.github.iusmac.pocketjudge;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

public class PocketJudge {
    private static final String TAG = "PocketJudge";
    private static final boolean DEBUG = false;

    public static final String KEY_POCKET_JUDGE_SWITCH = "key_pocket_judge";
    private static final String POCKET_BRIDGE_INPOCKET_FILE = "/sys/kernel/pocket_judge/inpocket";

    public static void startService(final Context context) {
        PocketJudgeUtils.startService(context, PocketJudgeService.class);
    }

    public static void stopService(final Context context) {
        PocketJudgeUtils.stopService(context, PocketJudgeService.class);
    }

    public static boolean isSafeDoorTriggered() {
        String raw = PocketJudgeUtils.readLine(POCKET_BRIDGE_INPOCKET_FILE);
        return "2".equals(raw);
    }

    public static void setInPocket(boolean active) {
        PocketJudgeUtils.writeValue(POCKET_BRIDGE_INPOCKET_FILE, active ? "1" : "0");
    }
}
