package com.example.findher.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.findher.RecyclerViewChat.UserAdapter;
import com.example.findher.R;
import com.example.findher.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DiscussionsFragment extends Fragment
{
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private ArrayList<User> allUsers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view = LayoutInflater.from(inflater.getContext()).inflate(R.layout.discussion_list,container,false);
        recyclerView = view.findViewById(R.id.recyclerView_all_discussion);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allUsers = new ArrayList<>();

        readUsers();

        return view;
    }

    private void readUsers()
    {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allUsers.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);

                    if(!user.getuId().equals(firebaseUser.getUid()))
                    {
                        allUsers.add(user);
                    }
                }

                userAdapter = new UserAdapter(getContext(),allUsers);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
