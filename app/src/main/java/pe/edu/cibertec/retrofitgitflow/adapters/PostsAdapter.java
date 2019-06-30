package pe.edu.cibertec.retrofitgitflow.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.cibertec.retrofitgitflow.Post;
import pe.edu.cibertec.retrofitgitflow.R;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.LayoutPost> {

    List<Post> items;

    public PostsAdapter(List<Post> items) {

        this.items = items;
    }

    @NonNull
    @Override
    public PostsAdapter.LayoutPost onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.template_post, viewGroup, false);

        LayoutPost layoutPost = new LayoutPost(view);
        return layoutPost;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.LayoutPost layoutCountry, final int position) {
        layoutCountry.tvTitle.setText(items.get(position).getTitle());
        layoutCountry.tvText.setText(items.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class LayoutPost extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvText;

        public LayoutPost(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvText = itemView.findViewById(R.id.tvText);

        }
    }
}
