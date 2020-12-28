package ua.kiss.cinematimekotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kiss.cinematimekotlin.databinding.MoviesListItemBinding
import ua.kiss.cinematimekotlin.model.Movie

class MoviesListAdapter(private val movies: ArrayList<Movie>) :
    RecyclerView.Adapter<MoviesListAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: MoviesListItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieListName.text = movie.name
            binding.movieListGenre.text = movie.genre
            Picasso.get().load(movie.img).into(binding.movieListPoster)
            println("name: \"${binding.movieListName.text}\", genre: \"${movie.genre}\", img: \"${movie.img}\"")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MoviesListItemBinding.inflate(inflater)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount(): Int = movies.size

    fun addData(list: List<Movie>) {
        movies.addAll(list)
    }
}