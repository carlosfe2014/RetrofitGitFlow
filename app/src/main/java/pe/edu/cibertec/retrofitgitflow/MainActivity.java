package pe.edu.cibertec.retrofitgitflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.adapters.PostsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView tvResult;
    RecyclerView rvPosts;
    List<Post> posts;

    PostsAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPosts = findViewById(R.id.rvPosts);

        callService();
    }

    private void callService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    tvResult.setText("Code: " + response.code());
                } else {
                    posts = response.body();
                    postAdapter = new PostsAdapter(posts);
                    rvPosts.setAdapter(postAdapter);
                    rvPosts.setLayoutManager
                            (new LinearLayoutManager(MainActivity.this));
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvResult.setText(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
