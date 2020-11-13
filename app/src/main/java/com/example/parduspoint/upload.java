package com.example.parduspoint;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class upload extends AppCompatActivity {
    private ImageButton imgBtnCamera, imgBtnUpload;
    private ImageView imgUpload;
    private EditText etDescription, etPosition;
    private Button Upload, Submit;
    public List<Program> programList;
    String id;
    Merit_Detail_Upload uploadDetail;

    //  private Uri filePath;

    //   private final int PICK_IMAGE_REQUEST = 71;

    //   private int PICK_IMAGE_REQUEST = 1;
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    public static final int GET_FROM_GALLERY = 3;
//    private static final int ImageBack = 1;
//    private StorageReference Folder;




    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;


    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseUser user;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        actionBar.setDisplayHomeAsUpEnabled(true);


        imgBtnCamera = findViewById(R.id.imgBtn_camera);
        imgBtnUpload = findViewById(R.id.imgBtn_file_upload);
        imgUpload = findViewById(R.id.img_upload);
        Submit = findViewById(R.id.btn_submit);
        Upload = findViewById(R.id.btn_upload);
        etDescription = findViewById(R.id.tb_description);
        etPosition = findViewById(R.id.et_jawatan);
        uploadDetail = new Merit_Detail_Upload();
        user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("Merit");
    //        imgBtnUpload.setOnClickListener(this);
//        imgBtnCamera.setOnClickListener(this);

        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadImage();
                uploadDetail.setDescription(etDescription.getText().toString());
                uploadDetail.setPost(etPosition.getText().toString());

                ref.child(id).setValue(uploadDetail);
            }
        });


        Intent intent = getIntent();

        //TextView tvname = findViewById(R.id.tv_program);
        actionBar.setTitle(intent.getStringExtra("programName"));
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.img_upload);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    String currentPhotoPath;

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("ImageFolder/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(upload.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(upload.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
    //@Override
//    public void onClick(View v) {
//        chooseImage();
//        switch (v.getId()) {
//            case R.id.imgBtn_call:
//                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
//
//                    startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
//                }
//                break;
//            case R.id.imgBtn_file_upload:
//                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//
//                break;
////            case R.id.btn_submit:
////                AddDate();
//        }
//    }


//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
//
//    private void getImageFromAlbum(){
//        try{
//            Intent i = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            startActivityForResult(i, RESULT_LOAD_IMAGE);
//        }catch(Exception exp){
//            Log.i("Error",exp.toString());
//        }
//    }
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(currentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }
}