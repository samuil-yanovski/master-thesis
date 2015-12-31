package yanovski.master_thesis.data;

import android.content.Context;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Document;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.data.models.Resource;
import yanovski.master_thesis.data.models.Teacher;

/**
 * Created by Samuil on 12/30/2015.
 */
public class LocalDataProvider {
    private static final Context context = MasterThesisApplication.getMainComponent()
        .getContext();

    @Deprecated
    public static List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();

        List<Interest> ilievaInterests =
            createInterests("Софтуерни технологии", "Софтуерни процеси",
                "Гъвкави методи за разработване на софтуер",
                "Управление на качеството и тестване на софтуерни системи",
                "Архитектури, ориентирани към услуги (SOA) и уеб услуги");
        Contacts ilievaContacts = createContacts("02 971 04 00", null, "sylvia@fmi.uni-sofia.bg");
        Teacher ilieva = new Teacher();
        ilieva.interests = ilievaInterests;
        ilieva.contact = ilievaContacts;
        ilieva.avatar = "http://www.fmi.uni-sofia.bg/lecturers/softeng/sylvia/Photo";
        ilieva.name = "Силвия Илиева";
        teachers.add(ilieva);

        List<Interest> petrovInterests =
            createInterests("Софтуерни инженерство", "Оперативна съвместимост", "Програмиране ",
                "Системи и средства за електронно обучение", "Системи за оценяване");
        Contacts petrovContacts =
            createContacts("02 8161-572", "milen.petrov", "milenp@fmi.uni-sofia.bg");
        Teacher petrov = new Teacher();
        petrov.interests = petrovInterests;
        petrov.contact = petrovContacts;
        petrov.avatar = "http://www.fmi.uni-sofia.bg/lecturers/softeng/milenp/Photo";
        petrov.name = "Милен Петров";
        teachers.add(petrov);

        return teachers;
    }

    @Deprecated
    private static List<Interest> createInterests(String... names) {
        List<Interest> interests = new ArrayList<>();

        for (String name : names) {
            Interest interest = new Interest();
            interest.name = name;
            interests.add(interest);
        }

        return interests;
    }

    private static Contacts createContacts(String phone, String skype, String email) {
        Contacts contacts = new Contacts();
        contacts.phone = phone;
        contacts.skype = skype;
        contacts.email = email;

        return contacts;
    }

    public static List<Document> getAllDocuments() {
        List<Document> documents = new ArrayList<>();

        documents.add(createDocument(R.string.doc_assignment, R.string.doc_assignment_description));
        documents.add(createDocument(R.string.doc_plagiarism));
        documents.add(createDocument(R.string.doc_defense));
        documents.add(createDocument(R.string.doc_credits, R.string.doc_credits_description));
        documents.add(createDocument(R.string.doc_assignment_protocol,
            R.string.doc_assignment_protocol_description));
        documents.add(createDocument(R.string.doc_thesis));
        documents.add(createDocument(R.string.doc_cd));
        documents.add(createDocument(R.string.doc_summary));

        return documents;
    }

    public static List<Resource> getAllResources() {
        List<Resource> resources = new ArrayList<>();

        resources.add(createResource(R.string.res_instructions, R.string.res_instructions_url));
        resources.add(createResource(R.string.res_graduates, R.string.res_graduates_url));
        resources.add(createResource(R.string.res_review, R.string.res_review_url));
        resources.add(createResource(R.string.res_template, R.string.res_template_url));

        return resources;
    }

    private static Document createDocument(@StringRes int titleId) {
        Document doc = new Document();
        doc.title = context.getString(titleId);
        return doc;
    }

    private static Document createDocument(@StringRes int titleId, @StringRes int descriptionId) {
        Document doc = new Document();
        doc.title = context.getString(titleId);
        doc.description = context.getString(descriptionId);
        return doc;
    }

    private static Resource createResource(@StringRes int titleId, @StringRes int urlId) {
        Resource res = new Resource();
        res.title = context.getString(titleId);
        res.url = context.getString(urlId);
        return res;
    }
}
