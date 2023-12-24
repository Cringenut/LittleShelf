package com.example.littleshelf.Repositories;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuggestionsRepository {
    private final MutableLiveData<List<String>> suggestions = new MutableLiveData<>();
    private String originalName;

    public SuggestionsRepository(String name) {
        // Set suggestions from current name of the Grocery (Later would be creating using suggestions fromm database)
        this.suggestions.setValue(new ArrayList<>(Collections.singletonList(name)));
        this.originalName = name;
    }

    public MutableLiveData<List<String>> getSuggestions() {
        return suggestions;
    }

    // Used when new Grocery name is chosen
    public void setOriginalName(String name) {
        this.originalName = name;
    }

    public void setSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        List<String> newSuggestions = new ArrayList<>();
        // Place a charSequence as the first element
        if (i > 0) {
            newSuggestions.add(0, charSequence.toString());
        }
        // If no text is presented show the original name as only suggestion
        else {
            newSuggestions.add(0, originalName);
        }

        suggestions.setValue(newSuggestions);
    }
}
