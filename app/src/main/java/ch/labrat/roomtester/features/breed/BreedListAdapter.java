package ch.labrat.roomtester.features.breed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ch.labrat.roomtester.R;

public class BreedListAdapter extends RecyclerView.Adapter<BreedListAdapter.BreedViewHolder> {

    //this context we will use to inflate the layout
    private Context context;

    //we are storing all the breeds in a list
    private List<Breed> breedList;

    private int selectedPosition;

    private OnBreedAdapterItemClickListener onBreedAdapterItemClickListener;
    public interface OnBreedAdapterItemClickListener { void onItemClicked(int position);}

    class BreedViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;

        private BreedViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }
    }

    //getting the context
    public BreedListAdapter(Context context, OnBreedAdapterItemClickListener onBreedAdapterItemClickListener, int selectedPosition) {
        this.context = context;
        this.onBreedAdapterItemClickListener = onBreedAdapterItemClickListener;
        this.selectedPosition = selectedPosition;
    }

    @Override
    public BreedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.breed_list_item, parent, false);
        return new BreedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BreedViewHolder holder, int position) {

        if (breedList != null) {
            //getting the product of the specified position
            Breed breed = breedList.get(position);
            //binding the data with the viewholder views
            holder.name.setText( breed.getName());

            if (selectedPosition != -1)
                holder.itemView.setSelected(position == selectedPosition);

            holder.itemView.setOnClickListener(v -> {
                selectedPosition = holder.getAdapterPosition();
                onBreedAdapterItemClickListener.onItemClicked(selectedPosition);

                //here you inform view that something was change - view will be invalidated
                notifyDataSetChanged();
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No Breed");
        }
    }

    void setBreeds(List<Breed> breeds){
        breedList = breeds;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (breedList != null)
            return breedList.size();
        else return 0;
    }

    public Breed getItem(int position) {
        return breedList.get(position);
    }
}
