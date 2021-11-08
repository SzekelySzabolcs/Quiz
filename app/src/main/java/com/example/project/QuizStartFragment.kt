package com.example.project

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels


import com.example.project.databinding.FragmentQuizStartBinding


private lateinit var contact:Button
private lateinit var btn:Button
private lateinit var name:EditText
private lateinit var image:ImageView
private lateinit var binding :FragmentQuizStartBinding
class QuizStartFragment : Fragment() {

    val save:Model by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        binding = FragmentQuizStartBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startbtn.setOnClickListener(){
        if(binding.name.length()==0) {
            Toast.makeText(context, "Ird be", Toast.LENGTH_SHORT).show()
        }
            else{
           save.name= binding.name.text.toString()
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentView,QuestionFragment.newInstance())
                ?.commit()

        }
        }

        val imageUp=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            val utiImage=result.data?.data
            binding.imageAvatar.setImageURI(utiImage)
            save.url=utiImage.toString()
        }
        binding.imageAvatar.setOnClickListener {
            val intente=Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            imageUp.launch(intente)
        }
        val nameUp=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            val nameeUp=result.data?.data
            binding.name.setText("$nameeUp")
        }

        binding.contact.setOnClickListener(){
            var intent1=Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            intent1.setType(ContactsContract.Contacts.CONTENT_TYPE);
            nameUp.launch(intent1)
        }

    }

    companion object{
        @JvmStatic
        fun newInstacne() = QuizStartFragment()

    }
}