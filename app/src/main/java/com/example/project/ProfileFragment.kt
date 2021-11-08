package com.example.project

import android.net.Uri
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.project.databinding.FragmentHomeBinding
import com.example.project.databinding.FragmentProfileBinding
import com.squareup.picasso.Picasso

private lateinit var binding:FragmentProfileBinding
class ProfileFragment : Fragment() {

    val points:Model by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameView.setText(points.name.toString())
        binding.score.setText("Score: "+points.number.toString()+"/"+points.numberQuestion.toString())
        if(points.url.isEmpty()){

        }
        else{
            Picasso.get().load(points.url).into(binding.imageAvatar)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() =ProfileFragment()

    }
}