package com.example.agrotips.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.agrotips.R
import com.example.agrotips.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        var isDarkThemeEnabled = false
        var isPortugueseEnabled = false

        val toggleThemeButton: Button = binding.buttonChangeTheme
        toggleThemeButton.setOnClickListener {
            isDarkThemeEnabled = !isDarkThemeEnabled
            applyTheme(isDarkThemeEnabled)
        }
        val toggleLanguageButton: Button = binding.buttonChangeLanguage
        toggleLanguageButton.setOnClickListener {
            isPortugueseEnabled = !isPortugueseEnabled
            changeLanguage(isPortugueseEnabled)
        }
    }

    private fun applyTheme(isDarkThemeEnabled: Boolean) {
        val nightMode = if (isDarkThemeEnabled) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    private fun changeLanguage(isPortugueseEnabled: Boolean) {
        val locale = if (isPortugueseEnabled) {
            Locale("pt")
        } else {
            Locale("")
        }

        val config = Configuration(resources.configuration)
        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)

        requireActivity().recreate()
    }

}
