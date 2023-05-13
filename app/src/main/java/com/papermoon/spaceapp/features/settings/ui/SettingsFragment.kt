package com.papermoon.spaceapp.features.settings.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentSettingsBinding
import com.papermoon.spaceapp.features.commons.readableFileSize.readableFileSize
import com.papermoon.spaceapp.features.settings.vm.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    lateinit var container: ViewGroup

    private val settingsViewModel: SettingsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        setupUi()

        return binding.root
    }

    private fun setupUi() {
        binding.toolbar.root.title = getString(R.string.settings)
        binding.currentLanguage.text = getString(R.string.current_language)

        val darkModeSwitcher = binding.switchDarkMode
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> darkModeSwitcher.isChecked = true
            Configuration.UI_MODE_NIGHT_NO -> darkModeSwitcher.isChecked = false
        }

        binding.cardViewLanguage.setOnClickListener {
            SpaceApp.INSTANCE.router.navigateTo(Screens.languageSelectorScreen())
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                activity!!.recreate()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                activity!!.recreate()
            }
        }

        settingsViewModel.storageUsage.observe(viewLifecycleOwner) {
            binding.tvStorageUsage.text = readableFileSize(it)
        }

        binding.btnClearStorage.setOnClickListener {
            settingsViewModel.clearStorage(requireContext())
            settingsViewModel.updateStorageUsage(requireContext())
        }

        settingsViewModel.trafficUsage.observe(viewLifecycleOwner) {
            binding.tvTrafficUsage.text = readableFileSize(it)
        }

        settingsViewModel.updateStorageUsage(requireContext())
        settingsViewModel.updateTrafficUsage(android.os.Process.myUid())
    }
}
