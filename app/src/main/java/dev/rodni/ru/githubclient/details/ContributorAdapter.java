package dev.rodni.ru.githubclient.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.rodni.ru.githubclient.R;
import dev.rodni.ru.githubclient.model.Contributor;

class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>{

    private final List<Contributor> data = new ArrayList<>();

    public ContributorAdapter() {
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ContributorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.view_user_list_item, viewGroup,false);
        return new ContributorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContributorViewHolder contributorViewHolder, int i) {
        contributorViewHolder.bind(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id();
    }

    void setData(List<Contributor> contributors) {
        if (contributors != null) {
            DiffUtil.DiffResult diffResult
                    = DiffUtil.calculateDiff(new ContributorDiffCallback(data, contributors));
            data.clear();
            data.addAll(contributors);
            diffResult.dispatchUpdatesTo(this);
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    static final class ContributorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_name)
        TextView userNameText;
        @BindView(R.id.iv_avatar)
        ImageView avatarImageView;

        public ContributorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Contributor contributor) {
            userNameText.setText(contributor.login());
            Glide.with(avatarImageView.getContext())
                    .load(contributor.avatarUrl())
                    .into(avatarImageView);
        }
    }
}
