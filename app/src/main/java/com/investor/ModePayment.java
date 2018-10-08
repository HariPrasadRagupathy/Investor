package com.investor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.investor.presenter.ModePaymentPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.ModePaymentContractor;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModePayment extends BaseActivity implements ModePaymentContractor.view {


    private static final String TAG = "check";
    @BindView(R.id.mpa_sp_duration)
    Spinner mpaSpDuration;
    @BindView(R.id.mpa_et_reference_no)
    EditText mpaEtReferenceNo;
    @BindView(R.id.mpa_iv_proof)
    AppCompatImageView mpaIvProof;
    @BindView(R.id.mpa_bt_signin)
    Button mpaBtSignin;
    private Uri mCropImageUri;
    private String paymentMode;
    private ModePaymentPresenter presenter;
    private String encodedImage = "";

    @Override
    protected int setLayout() {
        return R.layout.activity_mode_payment;
    }

    @Override
    protected String setTitle() {
        return "Mode Of Payment";
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {

        presenter = new ModePaymentPresenter(this, this);
        setContent();

    }

    private void setContent() {
        if (checkInternet())
            presenter.getPaymentModeList();
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
    }


    @OnClick(R.id.mpa_iv_proof)
    public void onUploadClicked() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .start(this);
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);

            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                // ((ImageButton) findViewById(R.id.quick_start_cropped_image)).setImageURI(result.getUri());
                mpaIvProof.setImageURI(result.getUri());
                // savefile(result.getUri());
                try {
                    final InputStream imageStream = getContentResolver().openInputStream(result.getUri());
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    mpaIvProof.setImageBitmap(selectedImage);
                    encodedImage = encodeImage(selectedImage);
                    Log.e("encode", encodedImage);
                    //  Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        /*switch (requestCode) {
            case android.: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
*/
    }

    void savefile(Uri sourceuri) {
        String sourceFilename = sourceuri.getPath();
        String destinationFilename = Environment.getExternalStorageDirectory().getPath() + File.separatorChar + "abc.jpg";
        Log.e("imageUpload", sourceFilename);
        Log.e("imageUpload", destinationFilename);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(destinationFilename, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);
            Log.e("imageUpload", "saved");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paymentModeList(final List<String> paymentListResponses) {
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, paymentListResponses);

        mpaSpDuration.setAdapter(dataAdapter);

        mpaSpDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                paymentMode = paymentListResponses.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                //    ((TextView) view).setTextColor(Color.WHITE);
            }
        });

        // Apply the adapter to the spinner
        mpaSpDuration.setAdapter(dataAdapter);
    }

    @Override
    public void paymentModeStatus(int mode, int status, String message) {
        if (mode == 1) {
            if (status == 1) {
                showCustomDialog("Success", message, R.drawable.happy, 2, 2);
                //   finish();
            } else

                showCustomDialog("Failed", message, R.drawable.cancel_investment, 2, 2);
        } else if (mode == 2) {

        }
    }


    @OnClick(R.id.mpa_bt_signin)
    public void submit() {
        logLargeString(encodedImage);
        //  writeToFile(encodedImage,"hari");
        if (checkInternet())
            presenter.uploadProof(getIntent().getStringExtra("orderId"), paymentMode, mpaEtReferenceNo.getText().toString(), encodedImage);
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
    }

    public boolean writeToFile(String dataToWrite, String fileName) {

        String directoryPath =
                Environment.getExternalStorageDirectory()
                        + File.separator
                        + "LOGS"
                        + File.separator;

        Log.d(TAG, "Dumping " + fileName + " At : " + directoryPath);

        // Create the fileDirectory.
        File fileDirectory = new File(directoryPath);

        // Make sure the directoryPath directory exists.
        if (!fileDirectory.exists()) {

            // Make it, if it doesn't exist
            if (fileDirectory.mkdirs()) {
                // Created DIR
                Log.i(TAG, "Log Directory Created Trying to Dump Logs");
            } else {
                // FAILED
                Log.e(TAG, "Error: Failed to Create Log Directory");
                return false;
            }
        } else {
            Log.i(TAG, "Log Directory Exist Trying to Dump Logs");
        }

        try {
            // Create FIle Objec which I need to write
            File fileToWrite = new File(directoryPath, fileName + ".txt");

            // ry to create FIle on card
            if (fileToWrite.createNewFile()) {
                //Create a stream to file path
                FileOutputStream outPutStream = new FileOutputStream(fileToWrite);
                //Create Writer to write STream to file Path
                OutputStreamWriter outPutStreamWriter = new OutputStreamWriter(outPutStream);
                // Stream Byte Data to the file
                outPutStreamWriter.append(dataToWrite);
                //Close Writer
                outPutStreamWriter.close();
                //Clear Stream
                outPutStream.flush();
                //Terminate STream
                outPutStream.close();
                return true;
            } else {
                Log.e(TAG, "Error: Failed to Create Log File");
                return false;
            }

        } catch (IOException e) {
            Log.e("Exception", "Error: File write failed: " + e.toString());
            e.fillInStackTrace();
            return false;
        }
    }
}
