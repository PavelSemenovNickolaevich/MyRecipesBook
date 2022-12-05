package ru.android.myrecipesbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.android.myrecipesbook.databinding.FragmentProfileBinding
import ru.android.myrecipesbook.repository.FakeFoodRepository
import timber.log.Timber

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var fakeFoodRepository = FakeFoodRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root = binding.root

        Timber.d("This is log for on Create ProfileFragment")

        return root
    }
}