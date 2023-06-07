package com.example.agrotips.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.agrotips.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private var isDarkThemeEnabled = false
    private var isPortugueseEnabled = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

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


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
