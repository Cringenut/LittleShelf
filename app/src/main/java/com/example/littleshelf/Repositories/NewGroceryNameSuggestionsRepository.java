package com.example.littleshelf.Repositories;

import android.graphics.Paint;

import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewGroceryNameSuggestionsRepository {
    private final MutableLiveData<List<String>> suggestions = new MutableLiveData<>();
    private String originalName;

    public NewGroceryNameSuggestionsRepository(String name) {
        this.suggestions.setValue(new ArrayList<>(Collections.singletonList(name)));
        this.originalName = name;
    }

    public MutableLiveData<List<String>> getSuggestions() {
        return suggestions;
    }

    public void setOriginalName(String name) {
        this.originalName = name;
    }

    public void setSuggestions(CharSequence charSequence, int i, int i1, int i2) {
        List<String> newSuggestions = new ArrayList<>();
        if (i > 0) {
            newSuggestions.add(0, charSequence.toString());
        }
        else {
            newSuggestions.add(0, originalName);
        }

        suggestions.setValue(newSuggestions);
    }
}
