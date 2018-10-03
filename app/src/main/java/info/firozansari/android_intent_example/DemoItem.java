package info.firozansari.android_intent_example;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public String toString() {
        return description;
    }
}