package com.twosmalpixels.travel_notes.ui.choose_travel_skin

import BaseFragment
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twosmalpixels.travel_notes.R
import com.twosmalpixels.travel_notes.pojo.ToolbarParam
import kotlinx.android.synthetic.main.choose_travel_skin_fragment.*


class ChooseTravelSkinFragment: BaseFragment() {

    private val PICK_IMAGE = 100
    private lateinit var chooseSkinViewModel: ChooseSkinViewModel

    override fun getToolbarParam(): ToolbarParam {
        return ToolbarParam(getString(R.string.choose_skin_b))
    }

    override fun getLayout(): Int {
       return R.layout.choose_travel_skin_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseSkinViewModel = ViewModelProviders.of(requireActivity()).get(ChooseSkinViewModel::class.java)
        content.layoutManager = LinearLayoutManager(context)
        content.adapter = ChooseSkinAdapter(chooseSkinViewModel.getSkinList(), {
            chooseSkinViewModel.shoosenSkin.value = it
            requireActivity().onBackPressed()
        })
        choose_skin_from_galereia.setOnClickListener {
            startOpenGalleryIntent()
        }
    }

    private fun startOpenGalleryIntent(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                chooseSkinViewModel.shoosenSkinBitmap.value = uriToBitmap(uri)
                findNavController().navigate(R.id.action_chooseTravelSkinFragment_to_newTravelFragment)
            }
        }
    }

    private fun uriToBitmap(uri: Uri): Bitmap {
        return MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
    }
}