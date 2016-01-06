package yanovski.master_thesis.utils;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import yanovski.master_thesis.data.models.Person;
import yanovski.master_thesis.ui.HomeActivity;
import yanovski.master_thesis.ui.StudentsActivity;

/**
 * Created by Samuil on 1/6/2016.
 */
public class PersonHelper {

    @Inject
    public PersonHelper() {
    }

    public void enterApp(Activity activity, Person person) {
        switch (person.getType()) {
            case STUDENT: {
                Intent intent = new Intent(activity, HomeActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            }
            case TEACHER: {
                Intent intent = new Intent(activity, StudentsActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            }
        }
    }
}
