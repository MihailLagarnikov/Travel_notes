package com.twosmalpixels.travel_notes.ui.you_travels

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.core.extension.setNonEmptyText
import com.twosmalpixels.travel_notes.core.repositoriy.IFairbaseStorageBase
import com.twosmalpixels.travel_notes.core.repositoriy.InnerImage
import com.twosmalpixels.travel_notes.core.repositoriy.getInnerDrawable
import com.twosmalpixels.travel_notes.core.repositoriy.isInnerImage
import com.twosmalpixels.travel_notes.pojo.TravelsItem
import com.twosmalpixels.travel_notes.ui.new_travel.INewTravelsUseCase
import kotlinx.android.synthetic.main.you_travels_item.view.*
import org.koin.java.standalone.KoinJavaComponent


class YouTravelsAdapter( val storage: FirebaseStorage,
                        val listener: (TravelsItem) -> Unit): RecyclerView.Adapter<YouTravelsAdapter.ViewHolder>() {

    val listTravelItems = ArrayList<TravelsItem>()
    private val iFairbaseStorageBase by KoinJavaComponent.inject(
        IFairbaseStorageBase::class.java)

    fun setNewList(newList: ArrayList<TravelsItem>){
        listTravelItems.clear()
        listTravelItems.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.you_travels_item, parent, false))
    }

    override fun getItemCount() = listTravelItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(listTravelItems.get(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindData(travelsItem: TravelsItem){
            itemView.setOnClickListener { listener.invoke(travelsItem) }

            itemView.run {
                title_item.text = travelsItem.title
                data_travel_item.setNonEmptyText(travelsItem.data)
                person_travel_item.setNonEmptyText(travelsItem.person)
                if (isInnerImage(travelsItem.imageUrl)){
                    loadUrl(context.getDrawable(getInnerDrawable(travelsItem.imageUrl))!!, image_travel_item, context)
                }else{
                   iFairbaseStorageBase.loadBitMap(travelsItem.imageUrl, storage, image_travel_item)
                }

            }
        }


        private fun loadUrl(drawable: Drawable, imageView: ImageView, context: Context){
            Glide.with(context)
                .load(drawable)
                .into(imageView)
                .onLoadFailed(context.getDrawable(InnerImage.LOAD_ERROR.drawable))
        }
    }
}