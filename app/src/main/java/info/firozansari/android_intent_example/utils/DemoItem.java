package info.firozansari.android_intent_example.utils;

import android.content.Intent;

import androidx.annotation.NonNull;

public class DemoItem {

    public String description;
    public Intent intent;

    public DemoItem(String description, Intent intent) {
        this.description = description;
        this.intent = intent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}