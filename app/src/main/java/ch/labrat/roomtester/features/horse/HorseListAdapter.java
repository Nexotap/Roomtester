package ch.labrat.roomtester.features.horse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ch.labrat.roomtester.R;

public class HorseListAdapter extends RecyclerView.Adapter<HorseListAdapter.HorseViewHolder> {

    //this context we will use to inflate the layout
    private Context context;

    //we are storing all the horses in a list
    private List<Horse> horseList;

    private int selectedPosition;

    private OnHorseAdapterItemClickListener onHorseAdapterItemClickListener;
    public interface OnHorseAdapterItemClickListener { void onItemClicked (int position);}

    class HorseViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView owner;

        private HorseViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            owner = itemView.findViewById(R.id.owner);
        }
    }

    //getting the context
    public HorseListAdapter(Context context, OnHorseAdapterItemClickListener onHorseAdapterItemClickListener, int selectedPosition) {
        this.context = context;
        this.onHorseAdapterItemClickListener = onHorseAdapterItemClickListener;
        this.selectedPosition = selectedPosition;
    }

    @Override
    public HorseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.horse_recyclerview_item, parent, false);
        return new HorseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HorseViewHolder holder, int position) {
        //getting the product of the specified position
        Horse horse = horseList.get(position);


        //binding the data with the viewholder views
        holder.name.setText(   String.format("%s (%s)",horse.getName(),horse.getAge()));
        holder.owner.setText(horse.getOwner());

        if (selectedPosition != -1)
            holder.itemView.setSelected(position == selectedPosition);

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            onHorseAdapterItemClickListener.onItemClicked(selectedPosition);

            //here you inform view that something was change - view will be invalidated
            notifyDataSetChanged();
        });
    }

    void setHorses(List<Horse> horses){
        horseList = horses;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (horseList != null)
            return horseList.size();
        else return 0;
    }

    public Horse getItem(int position) {
        return horseList.get(position);
    }
}
