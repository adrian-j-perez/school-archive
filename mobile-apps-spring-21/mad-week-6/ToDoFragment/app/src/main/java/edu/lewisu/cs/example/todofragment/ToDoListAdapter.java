package edu.lewisu.cs.example.todofragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// going to map the data to the recycle view
public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoHolder> {
    private List<ToDo> mToDos;

    public interface ToDoListAdapterOnClickHandler{
        void onClick(ToDo todo);
    }

    private final ToDoListAdapterOnClickHandler mClickHandler;

    public ToDoListAdapter(ToDoListAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    // the following three following method came from the extend requirement
    @NonNull
    @Override
    public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ToDoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoHolder holder, int position) {
        if (mToDos != null){
            ToDo current = mToDos.get(position);
            holder.mTitleTextView.setText(current.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if(mToDos != null){
            return mToDos.size();
        }
        return 0;
    }

    public void setToDos (List<ToDo> toDos){
        mToDos = toDos;
    }


    // this create the view holder to handle the clicks
    class ToDoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private final TextView mTitleTextView;

        public ToDoHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.title_text_view);
            itemView.setOnClickListener(this);

        }

        public  void onClick(View v){
            int adapterPosition  =  getAdapterPosition();
            ToDo toDo  = mToDos.get(adapterPosition);
            mClickHandler.onClick(toDo);
        }

    }//end  of <ToDoHolder> class


}//end of class
