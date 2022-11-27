package ru.android.myrecipesbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.RecyclerView
import ru.android.myrecipesbook.model.Recipe

class DishAdapterHorizontal(private val dish: List<Recipe>, listItemDish: Int) :
    RecyclerView.Adapter<DishAdapterHorizontal.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_horizontal_dish, parent, false)
        return DishViewHolder(view)
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

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val current = dish[position]
        current.resourceLinkImage?.let { holder.imageView.setImageResource(it) }
        holder.typeOfMeals.text = current.meals?.let { holder.typeOfMeals.context.getString(it.typeOfMeals) }
        holder.titleRecipe.text = current.name
        holder.rating.rating = current.rating!!.toFloat()
        holder.calories.text = current.calories.toString()
        holder.timeToCook.text = current.timeForCooking.toString()
        holder.countOfServing.text = current.numOfServings.toString()
        holder.like.isChecked = current.isFavoriteDish == true
    }

    override fun getItemCount(): Int {
        return dish.size
    }

}