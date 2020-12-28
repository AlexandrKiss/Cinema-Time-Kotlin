package ua.kiss.cinematimekotlin.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ua.kiss.cinematimekotlin.R
import ua.kiss.cinematimekotlin.api.ApiHelperImpl
import ua.kiss.cinematimekotlin.api.RetrofitBuilder
import ua.kiss.cinematimekotlin.databinding.MoviesListActivityBinding
import ua.kiss.cinematimekotlin.model.Movie
import ua.kiss.cinematimekotlin.ui.adapters.MoviesListAdapter
import ua.kiss.cinematimekotlin.utils.Status
import ua.kiss.cinematimekotlin.utils.ViewModelFactory

class MoviesListActivity : AppCompatActivity() {
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var adapter: MoviesListAdapter
    private lateinit var binding: MoviesListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CustomBar)
        super.onCreate(savedInstanceState)
        binding = MoviesListActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            MoviesListAdapter(
                arrayListOf()
            )
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getAllFilms().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { movies -> renderList(movies) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<Movie>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(MoviesListViewModel::class.java)
    }
}