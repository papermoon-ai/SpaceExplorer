package com.papermoon.spaceapp.features.celestialBodyDetail.ui

import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.papermoon.spaceapp.R
import com.papermoon.spaceapp.SpaceApp
import com.papermoon.spaceapp.databinding.FragmentCelestialBodyDetailBinding
import com.papermoon.spaceapp.domain.model.celestialbody.CelestialBody
import com.papermoon.spaceapp.domain.model.celestialbody.Period
import com.papermoon.spaceapp.features.MainActivity
import com.papermoon.spaceapp.features.commons.adapter.BaseViewPagerImageAdapter
import com.papermoon.spaceapp.features.commons.bundle.serializable
import java.text.DecimalFormat

class CelestialBodyFragment : Fragment() {

    private var _binding: FragmentCelestialBodyDetailBinding? = null
    private val binding: FragmentCelestialBodyDetailBinding
        get() = _binding!!

    private val celestialBody: CelestialBody
        get() = arguments!!.serializable(CELESTIAL_BODY_DATA)!!

    private var imageFullscreen = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelestialBodyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setUiValues()
        setupToolbar()

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressedCallback()
                }
            })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        val adapter = BaseViewPagerImageAdapter(celestialBody.images) {
            if (!imageFullscreen) {
                setPageViewerFullscreen()
            } else {
                with(binding.tvCelestialBodyImageDescription) {
                    visibility = if (visibility == View.GONE) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }
        }
        binding.viewPagerCelestialBody.adapter = adapter
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar

        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressedCallback()
        }
    }

    private fun setPageViewerFullscreen() {
        binding.toolbar.title = ""
        binding.collapsingToolBar.isTitleEnabled = false

        binding.nestedScrollViewCelestialBody.visibility = View.GONE
        binding.appBarCelestialBody.layoutParams.height = LayoutParams.MATCH_PARENT

        disableToolbarScrolling()
        hideImageIndicators()

        imageFullscreen = true
    }

    private fun setPageViewerNormalSize() {
        binding.toolbar.title = celestialBody.englishName
        binding.collapsingToolBar.isTitleEnabled = true

        binding.nestedScrollViewCelestialBody.visibility = View.VISIBLE
        binding.appBarCelestialBody.layoutParams.height = resources.getDimension(R.dimen.big_image_height).toInt()

        permitToolbarScrolling()
        showImageIndicators()
        binding.tvCelestialBodyImageDescription.visibility = View.GONE

        imageFullscreen = false
    }

    private fun setUiValues() {
        val formatter = DecimalFormat("#.####")
        with(binding) {
            tvCelestialBodyName.text = celestialBody.englishName

            if (celestialBody.discoverDate == null) {
                cardViewCelestialBodyDiscovery.visibility = View.GONE
            } else {
                tvCelestialBodyDiscoveryDate.text = celestialBody.discoverDate
                tvCelestialBodyDiscoverer.text = celestialBody.discoveredBy
            }

            tvCelestialBodySatellites.text = celestialBody.satelliteCount.toString()
            tvCelestialBodyArea.text = getString(
                R.string.description_area,
                formatter.format(celestialBody.characteristics.area)
            )
            tvCelestialBodyTemperature.text = getString(
                R.string.description_temperatures,
                getTemperatureString(celestialBody.characteristics.minTemperature),
                getTemperatureString(celestialBody.characteristics.maxTemperature)
            )
            tvCelestialBodyOrbitalSpeed.text = getString(
                R.string.description_orbital_speed,
                formatter.format(celestialBody.characteristics.avgOrbitalSpeed)
            )
            tvCelestialBodyRotationAxis.text = getPeriodString(celestialBody.characteristics.rotationAroundAxis)
            tvCelestialBodyRotationSun.text = getPeriodString(celestialBody.characteristics.rotationAroundSun)
            tvCelestialBodyGravity.text = getString(
                R.string.description_gravity,
                formatter.format(celestialBody.characteristics.gravity)
            )
            tvCelestialBodyDensity.text = getString(
                R.string.description_density,
                formatter.format(celestialBody.characteristics.density)
            )
            tvCelestialBodyDescription.text = celestialBody.description
            tvCelestialBodyCounter.text = getString(
                R.string.label_counter, 1, celestialBody.images.size
            )

            btnCelestialBodyOpenWeb.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, celestialBody.wikiUrl.toUri())
                startActivity(intent)
            }

            if (celestialBody.images.size > 1) {
                TabLayoutMediator(
                    tabLayoutCelestialBodyIndicator.root,
                    viewPagerCelestialBody
                ) { _, _ ->
                }.attach()
            } else {
                tvCelestialBodyCounter.visibility = View.GONE
            }

            toolbar.title = celestialBody.englishName
            viewPagerCelestialBody.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    tvCelestialBodyImageDescription.text = celestialBody.images[position].description
                    tvCelestialBodyCounter.text = getString(
                        R.string.label_counter, position + 1, celestialBody.images.size
                    )
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }
            })
        }
    }

    private fun permitToolbarScrolling() {
        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL + AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    }

    private fun disableToolbarScrolling() {
        val scrollingToolbarParams = binding.collapsingToolBar.layoutParams as AppBarLayout.LayoutParams
        scrollingToolbarParams.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
    }

    private fun showImageIndicators() {
        if (celestialBody.images.size > 1) {
            binding.tabLayoutCelestialBodyIndicator.root.visibility = View.VISIBLE
            binding.tvCelestialBodyCounter.visibility = View.VISIBLE
        }
    }

    private fun hideImageIndicators() {
        if (celestialBody.images.size > 1) {
            binding.tabLayoutCelestialBodyIndicator.root.visibility = View.GONE
            binding.tvCelestialBodyCounter.visibility = View.GONE
        }
    }

    private fun onBackPressedCallback() {
        if (imageFullscreen) {
            setPageViewerNormalSize()
        } else {
            SpaceApp.INSTANCE.router.exit()
        }
    }

    private fun getTemperatureString(temperature: Double): String {
        val formatter = DecimalFormat("#.##")
        return if (temperature > 0) {
            "+${formatter.format(temperature)}"
        } else {
            formatter.format(temperature)
        }
    }

    private fun getPeriodString(period: Period): String {
        val stringBuilder = StringBuilder()
        if (period.years != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.years,
                    period.years,
                    period.years
                )
            )
            stringBuilder.append(" ")
        }
        if (period.days != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.days,
                    period.days,
                    period.days
                )
            )
            stringBuilder.append(" ")
        }
        if (period.hours != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.hours,
                    period.hours,
                    period.hours
                )
            )
            stringBuilder.append(" ")
        }
        if (period.minutes != 0) {
            stringBuilder.append(
                resources.getQuantityString(
                    R.plurals.minutes,
                    period.minutes,
                    period.minutes
                )
            )
        }
        return stringBuilder.toString()
    }

    companion object {
        private const val CELESTIAL_BODY_DATA = "CelestialBodyData"
        fun getNewInstance(celestialBody: CelestialBody): CelestialBodyFragment {
            return CelestialBodyFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CELESTIAL_BODY_DATA, celestialBody)
                }
            }
        }
    }
}
