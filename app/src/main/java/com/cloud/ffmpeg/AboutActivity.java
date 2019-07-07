package com.cloud.ffmpeg;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cloud.ffmpeg.bean.ItemVersion;
import com.cloud.ffmpeg.cmd.FFmepgCmd;
import com.cloud.ffmpeg.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Author : xiaoyunfei
 * Create Time : 2019-07-07 15:25
 * Package Name : com.cloud.ffmpeg
 * Description :
 */
public class AboutActivity extends AppCompatActivity {

    private ListView versionListView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        versionListView = findViewById(R.id.versionListView);

        String version = FFmepgCmd.getInstance().readVersions();

        Type type = new TypeToken<List<ItemVersion>>(){}.getType();
        List<ItemVersion> versionInfo = GsonUtils.fromJson(version, type);

        ArrayList<HashMap<String,String>>mapArrayList = new ArrayList<>();

        for (ItemVersion itemVersion : versionInfo) {
            HashMap<String,String> map = new HashMap<>();
            map.put("name",itemVersion.getName());
            map.put("version",itemVersion.getVersion());
            mapArrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mapArrayList,
                android.R.layout.simple_list_item_2,new String[]{"name","version"},
                new int[]{android.R.id.text1,android.R.id.text2});


        versionListView.setAdapter(simpleAdapter);
    }
}
