package com.example.mypermission;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MaterialButton PermisosMultiplesBtn;
    private TextView resultTv;

    private static final String TAG = "PERMISSION_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermisosMultiplesBtn = findViewById(R.id.PermisosMultiplesBtn);
        resultTv = findViewById(R.id.resultTv);

        PermisosMultiplesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.CALL_PHONE};

                permissionLauncherMultiple.launch(permissions);

            }
        });
    }

        private ActivityResultLauncher<String[]> permissionLauncherMultiple = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {

                        boolean allAreGranted = true;
                        for(Boolean isGranted : result.values()){
                            Log.d(TAG, "Se ha concedido: "+isGranted);
                            allAreGranted = allAreGranted && isGranted;
                        }

                        if(allAreGranted){
                            multiplePermissionGranted();

                        }
                        else{
                            Log.d(TAG, "Todos o algun permiso fue denegado...");
                            Toast.makeText(MainActivity.this, "Todos o algun permiso fue denegado...", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

        private void multiplePermissionGranted(){

            resultTv.setText("Todos los permisos obtenidos. Has tus tareas...");
        }

}