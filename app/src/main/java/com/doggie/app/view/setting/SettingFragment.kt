package com.doggie.app.view.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.doggie.app.R


class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel
    private val mDelegate: AppCompatDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

}