package com.windbridget.w_videoplayer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListVideo extends AppCompatActivity {
    Button m_history;
    ListView listView;
    ArrayList<Video> listVideo,history;
    VideoAdapter adapter;
    AsyncHttpClient client;
    RequestParams params;
    Intent intent,history_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listvideo);

        client = new AsyncHttpClient();
        initImageLoader(this);

        m_history = (Button) findViewById(R.id.btn_history);
        history = new ArrayList<>();
        m_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history_intent = new Intent(ListVideo.this, History.class);
                history_intent.putExtra("video",history);
                startActivity(history_intent);
            }
        });

        listView = (ListView) findViewById(R.id.list_video);

        listVideo = new ArrayList<>();
        query("funix");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(ListVideo.this, VideoPlayer.class);
                intent.putExtra("videoid", listVideo.get(i).getVideoId());
                intent.putExtra("title", listVideo.get(i).getTitle());
                startActivity(intent);
                history.add(listVideo.get(i));
            }
        });

    }

    public void query(String query){

        params = new RequestParams();
        params.put("type","video");
        params.put("part","snippet");
        params.put("maxResults","50");
        params.put("order", "date");
        params.put("key", Config.SEARCH_API_KEY);
        params.put("q", query);

        String url = "https://www.googleapis.com/youtube/v3/search";

        client.get(url, params, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject data = new JSONObject(responseString);
                    JSONArray array = data.getJSONArray("items");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        JSONObject id = obj.getJSONObject("id");
                        String videoId = id.getString("videoId");
                        JSONObject snippet = obj.getJSONObject("snippet");
                        String title = snippet.getString("title");
                        JSONObject thumbnails = snippet.getJSONObject("thumbnails");
                        JSONObject default1 = thumbnails.getJSONObject("default");
                        String url = default1.getString("url");
                        Video video = new Video(videoId, title, url);

                        listVideo.add(video);
                    }

                    adapter = new VideoAdapter(ListVideo.this, listVideo);

                    listView.setAdapter(adapter);

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }


        });
    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

}