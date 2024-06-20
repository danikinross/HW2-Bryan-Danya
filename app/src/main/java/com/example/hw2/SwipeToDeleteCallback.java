package com.example.hw2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private MessageAdapter adapter;

    public SwipeToDeleteCallback(MessageAdapter a) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        adapter = a;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Message message = adapter.getMessageAtPosition(position);

        // Delete from Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Messages").document(message.ID).delete()
                .addOnSuccessListener(aVoid -> {
                    // Remove from adapter
                    adapter.deleteMessage(position);
                })
                .addOnFailureListener(e -> {
                    // Handle failure
                    // Optionally add the message back to the adapter
                    adapter.notifyItemChanged(position);
                });
    }
}