package edu.lewisu.cs.example.todofirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ToDoListAdapter extends FirebaseRecyclerAdapter<ToDo, ToDoListAdapter.ToDoHolder> {


    private final TodoListAdapterOnClickHandler clickHandler;


    public interface TodoListAdapterOnClickHandler {
        void onClick(int position);
    }

    //construtor method
    public ToDoListAdapter(@NonNull FirebaseRecyclerOptions<ToDo> options, TodoListAdapterOnClickHandler clickHandler) {
        super(options);
        this.clickHandler = clickHandler;
    }

    @Override
    protected void onBindViewHolder(@NonNull ToDoHolder holder, int position, @NonNull ToDo model) {

        holder.titleTextView.setText(model.getTitle());
    }

    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ToDoHolder(view);

    }






    class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleTextView;

        ToDoHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(adapterPosition);
        }
    }// end of class todoholder

}
