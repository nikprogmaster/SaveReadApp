package com.example.savereadapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button extButton;
    private String filename = "myfile";
    private String fileContants = "I'm glad to see you today!";
    private int REQUEST_CODE;
    private List<String> filesList;
    private RecyclerView recyclerView;
    private FoldersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        extButton = findViewById(R.id.external);
        recyclerView = findViewById(R.id.recycler);

        extButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getExtStorage();

            }
        });


        /*saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (FileOutputStream fos = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE)){
                    fos.write(editText.getText().toString().getBytes());
                    Toast.makeText(MainActivity.this, "File saved", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = getApplicationContext().openFileInput(filename);
                    InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                    StringBuilder stringBuilder = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(inputStreamReader)){
                        String line = reader.readLine();
                        while (line != null){
                            stringBuilder.append(line).append('\n');
                            line = reader.readLine();
                        }
                        textView.setText(stringBuilder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                }
            }
        });*/
    }

    private void getExtStorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "RATIONABLE", Toast.LENGTH_SHORT).show();
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        Log.i("REQUEST", "" + REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show();
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                File f = Environment.getExternalStorageDirectory();
                String[] filesArray = f.list();
                filesList = Arrays.asList(filesArray);
                initRecycler();
            }
        } else {
            Toast.makeText(this, "Невозможно загрузить данные с вешнего хранилища", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initRecycler() {
        adapter = new FoldersAdapter(filesList);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
    }
}
