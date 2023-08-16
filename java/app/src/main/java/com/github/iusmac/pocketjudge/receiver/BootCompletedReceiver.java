/*
 * MIT License
 *
 * Copyright (c) 2021 Trần Mạnh Cường <maytinhdibo>
 *               2022 iusmac <iusico.maxim@libero.it>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.iusmac.pocketjudge.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.github.iusmac.pocketjudge.PocketJudge;

import static com.github.iusmac.pocketjudge.BuildConfig.DEBUG;

public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String TAG = "PocketJudge";

    @Override
    public void onReceive(final Context context, Intent intent) {
        final SharedPreferences sharedPrefs =
            PreferenceManager.getDefaultSharedPreferences(context);

        final boolean isEnabled =
            sharedPrefs.getBoolean(PocketJudge.KEY_POCKET_JUDGE_SWITCH, false);
        if (isEnabled) {
            PocketJudge.startService(context);
        }
        if (DEBUG) Log.d(TAG, "Started. Service is enabled: " +
                Boolean.valueOf(isEnabled).toString());
    }
}
