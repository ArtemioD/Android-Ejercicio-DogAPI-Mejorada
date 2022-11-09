package com.artemiod.dogapimejorada.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.artemiod.dogapimejorada.R
import com.artemiod.dogapimejorada.databinding.FragmentDogBinding
import com.artemiod.dogapimejorada.viewmodel.DogViewModel


class DogFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var binding: FragmentDogBinding
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogBinding.inflate(inflater, container, false)
        loadingBreedsList()
        // Permite que el enlace de datos observe LiveData con el ciclo de vida de este fragmento
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // variable de xml vinculamos con viewModel
        binding.viewModel = viewModel

        // quere decir haga lo suguente con el objeto que pasamos por parametro
        with(binding.recyclerViewDog) {
            adapter = DogAdapter()
            layoutManager = LinearLayoutManager(requireContext())
        }
        observer()
    }

    private fun observer() {
        viewModel.breedsList.observe(viewLifecycleOwner) { newList ->
            val adapter = ArrayAdapter(requireContext(), R.layout.breeds_list, newList)

            with(binding.autoCompleteTextView) {
                setAdapter(adapter)
                // implementamos la Interfas con el metodo onItemClick
                onItemClickListener = this@DogFragment // el contexto
            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        Toast.makeText(requireContext(), item, Toast.LENGTH_LONG).show()
        viewModel.getDogPhotosList(item)
    }

    private fun loadingBreedsList() {
        val list = resources.getStringArray(R.array.breeds_list)
        viewModel.setBreedsList(list)
    }

}