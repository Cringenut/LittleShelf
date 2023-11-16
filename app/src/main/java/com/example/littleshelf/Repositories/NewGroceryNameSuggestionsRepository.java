package com.example.littleshelf.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.littleshelf.Objects.Grocery;

import java.util.ArrayList;
import java.util.List;

public class NewGroceryNameSuggestionsRepository {
    private final MutableLiveData<List<String>>  suggestions = new MutableLiveData<>();

    public MutableLiveData<List<String>> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        List<String> newSuggestions = new ArrayList<>();
        newSuggestions.add(0, charSequence.toString());

        suggestions.setValue(newSuggestions);
    }
}
