package com.example.littleshelf.Groceries.AddGroceryItem;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.GroceriesRecyclerViewAdapter;
import com.example.littleshelf.Groceries.Activity.GroceriesActivity;
import com.example.littleshelf.Main.Databases.GroceriesDataBaseHelper;
import com.example.littleshelf.Main.Databases.GroceriesNameSuggestionsDataBaseHelper;
import com.example.littleshelf.Main.GroceryItem.GroceryItem;
import com.example.littleshelf.Main.RecyclerView.RecyclerViewFragment;
import com.example.littleshelf.R;
import com.example.littleshelf.Groceries.GroceriesRecyclerView.RecyclerViewOnGroceryItemClickInterface;
import com.example.littleshelf.Groceries.SearchBar.SearchBarFragment;
import java.util.ArrayList;
import java.util.Objects;

public class SuggestionListFragment extends Fragment implements RecyclerViewOnGroceryItemClickInterface {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.g_fragment_suggestion_list, container, false);

        // Create close button
        Button btnBack = view.findViewById(R.id.btnBack);
        GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();
        btnBack.setOnClickListener(btn -> groceriesActivity.getSupportFragmentManager().beginTransaction()
                .remove(this)
                .commit());

        // Create search bar for item names from database
        SearchBarFragment searchBar = new SearchBarFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerSearchBar, searchBar)
                .commit();

        // Create recycler view with all item names
        RecyclerViewFragment recyclerView = new RecyclerViewFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerRecyclerViewReceipts, recyclerView)
                .commit();
        GroceriesRecyclerViewAdapter groceriesRecyclerViewAdapter =
                new GroceriesRecyclerViewAdapter(view.getContext(), searchBar,
                        (ArrayList<GroceryItem>)
                                (new GroceriesNameSuggestionsDataBaseHelper(getContext()).getAllItems()) // Getting all suggestion from database to show them
                        );
        recyclerView.setRecyclerViewAdapter(groceriesRecyclerViewAdapter);
        ((GroceriesRecyclerViewAdapter)recyclerView.getRecyclerViewAdapter()).setAddGroceryItemsListFilter();
        searchBar.setRecyclerView(recyclerView); // Set the recycler view to search inside

        return view;
    }

    @Override
    public void onItemClicked(GroceryItem groceryItem) {
        // Show final add item menu and set items name from the list
        GroceriesActivity groceriesActivity = (GroceriesActivity) requireActivity();
        groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .commit();

        ((AddItemMenuFragment) groceriesActivity.getSupportFragmentManager().findFragmentById(R.id.containerBottomFragment))
                .getGroceryItem().setName(groceryItem.getName());
        groceriesActivity.getSupportFragmentManager()
                .beginTransaction()
                .show(Objects.requireNonNull(groceriesActivity.getSupportFragmentManager()
                        .findFragmentById(R.id.containerBottomFragment)))
                .commit();
    }

}