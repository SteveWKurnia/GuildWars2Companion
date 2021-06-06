package com.example.soa_guildwars2.app.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soa_guildwars2.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_fragment.*

@AndroidEntryPoint
class CharacterFragment: Fragment() {

    private val viewModel: CharacterViewModel by viewModels()

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.character_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterList()
        viewModel.characterDetails.observe(viewLifecycleOwner, getCharactersData)
        setupRecycler()
    }

    private fun setupRecycler() {
        characterAdapter = CharacterAdapter()
        rv_character?.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private var getCharactersData: Observer<MutableList<CharacterModel>> = Observer {
        pb_character?.visibility = View.GONE
        rv_character?.visibility = View.VISIBLE
        characterAdapter.updateItems(it)
    }
}