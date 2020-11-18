package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import id.ac.ui.cs.mobileprogramming.jeremy.memento.databinding.ProfileRowBinding;


import java.util.List;

public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {
    private List<Profiles> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public ProfilesAdapter(List<Profiles> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Profiles getProfileAt(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ProfileRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_row, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.name.setText(list.get(position).getNameProfile());
        holder.binding.nickname.setText(list.get(position).getNickNameProfile());
        //Log.d("HELLO",Integer.toString(list.get(position).getImg()));
        holder.binding.image.setImageBitmap(BitmapFactory.decodeFile(list.get(position).getImg()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProfileRowBinding binding;

        public ViewHolder(@NonNull ProfileRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }
}

