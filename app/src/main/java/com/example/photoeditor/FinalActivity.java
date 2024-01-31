package com.example.photoeditor;

import static com.example.photoeditor.MainActivity.imgUri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.example.photoeditor.databinding.ActivityFinalBinding;

public class FinalActivity extends AppCompatActivity {
    ActivityFinalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFinalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (imgUri != null) {
            Log.d("FinalActivity", "Received Image Uri: " + imgUri.toString());
            Intent dsPhotoEditorIntent = new Intent(this, DsPhotoEditorActivity.class);
            dsPhotoEditorIntent.setData(imgUri);
            dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "PhotoEditor");
        int[] toolsToHide = {DsPhotoEditorActivity.TOOL_ORIENTATION,DsPhotoEditorActivity.TOOL_FILTER,DsPhotoEditorActivity.TOOL_FRAME,DsPhotoEditorActivity.TOOL_CONTRAST,DsPhotoEditorActivity.TOOL_EXPOSURE,DsPhotoEditorActivity.TOOL_WARMTH,DsPhotoEditorActivity.TOOL_ROUND,DsPhotoEditorActivity.TOOL_VIGNETTE,DsPhotoEditorActivity.TOOL_SATURATION,DsPhotoEditorActivity.TOOL_SHARPNESS,DsPhotoEditorActivity.TOOL_PIXELATE};
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide);
            startActivityForResult(dsPhotoEditorIntent, 200);
        }
        else {
            Log.e("FinalActivity", "No Image Uri Received");
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case 200:

                    Uri outputUri = data.getData();

                    // handle the result uri as you want, such as display it in an imageView;

                    binding.imgView.setImageURI(outputUri);

                    break;

            }

        }

    }
}