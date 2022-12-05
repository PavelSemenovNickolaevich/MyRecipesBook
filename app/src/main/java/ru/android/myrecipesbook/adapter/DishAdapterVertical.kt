package ru.android.myrecipesbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.android.myrecipesbook.R
import ru.android.myrecipesbook.model.RecipesResponse


class DishAdapterVertical(private val dish: RecipesResponse, listItemDish: Int) :
    RecyclerView.Adapter<DishAdapterVertical.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_vertical_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val current = dish.recipes?.let { it[position] }

        holder.typeOfMeals.text =
            current?.mealType?.let { holder.typeOfMeals.context.getString(it.typeOfMeals) }
        holder.titleRecipe.text = current?.title
        holder.rating.rating = current?.rating?.toFloat() ?: 1.0f
        holder.calories.text = current?.calories.toString()
        holder.timeToCook.text = current?.timeToCook.toString()
        holder.countOfServing.text = current?.portions.toString()
        holder.like.isChecked = current?.isFavorite == true

        Picasso.get()
            .load(current?.imageUr)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return dish.recipes?.size ?: 0
    }

    class DishViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        internal var imageView: ImageView = v.findViewById(R.id.rec_recipe_it)
        internal var typeOfMeals: TextView = v.findViewById(R.id.type_of_meals_it)
        internal var titleRecipe: TextView = v.findViewById(R.id.rec_title_dish_it)
        internal var rating: RatingBar = v.findViewById(R.id.rec_ratingBar_it)
        internal var calories: TextView = v.findViewById(R.id.calories_it)
        internal var timeToCook: TextView = v.findViewById(R.id.time_to_cook_it)
        internal var countOfServing: TextView = v.findViewById(R.id.count_of_serving_it)
        internal var like: AppCompatCheckBox = v.findViewById(R.id.favorite_like_it)

    }
}