package com.papermoon.spaceapp.features.settings.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.Screens
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentSettingsBinding
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.stringFormat.readableFileSize
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
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
                (activity as MainActivity).setDarkTheme()
            } else {
                (activity as MainActivity).setLightTheme()
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
