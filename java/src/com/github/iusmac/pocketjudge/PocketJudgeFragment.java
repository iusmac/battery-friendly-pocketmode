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

package com.github.iusmac.pocketjudge;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;
import androidx.preference.TwoStatePreference;

public class PocketJudgeFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {
    private static final String TAG = "PocketJudge";
    private final boolean DEBUG = false;

    private final String KEY_POCKET_JUDGE_FOOTER = "footer_preference";

    private Context mContext;

    private SharedPreferences mSharedPrefs;

    private TwoStatePreference mPocketJudgePreference;
    private Preference mFooterPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        mContext = getContext();
        mSharedPrefs =
            PreferenceManager.getDefaultSharedPreferences(mContext);

        addPreferencesFromResource(R.xml.pocketjudge_settings);

        mPocketJudgePreference = findPreference(PocketJudge.KEY_POCKET_JUDGE_SWITCH);
        mPocketJudgePreference.setChecked(
                mSharedPrefs.getBoolean(PocketJudge.KEY_POCKET_JUDGE_SWITCH, false));
        mPocketJudgePreference.setOnPreferenceChangeListener(this);

        mFooterPref = findPreference(KEY_POCKET_JUDGE_FOOTER);
        final String title =
                getString(R.string.pocket_judge_demo_title_cancel) + "\n" +
                getString(R.string.pocket_judge_demo_tag_number1) +
                getString(R.string.pocket_judge_demo_tag_cancel) + "\n" +
                getString(R.string.pocket_judge_demo_tag_number2) +
                getString(R.string.pocket_judge_demo_tag_cancel2);
        mFooterPref.setTitle(title);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean isChecked = (Boolean) newValue;
        if (isChecked) {
            PocketJudge.startService(mContext);
        } else {
            PocketJudge.stopService(mContext);
        }
        mSharedPrefs.edit().putBoolean(
                PocketJudge.KEY_POCKET_JUDGE_SWITCH,
                isChecked).apply();
        return true;
    }
}
