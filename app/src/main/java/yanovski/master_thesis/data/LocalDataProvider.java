package yanovski.master_thesis.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Account;
import yanovski.master_thesis.data.models.Category;
import yanovski.master_thesis.data.models.Contacts;
import yanovski.master_thesis.data.models.Document;
import yanovski.master_thesis.data.models.Event;
import yanovski.master_thesis.data.models.Interest;
import yanovski.master_thesis.data.models.Resource;
import yanovski.master_thesis.data.models.Teacher;
import yanovski.master_thesis.data.models.Thesis;

/**
 * Created by Samuil on 12/30/2015.
 */
public class LocalDataProvider {
    private static final Context context = MasterThesisApplication.getMainComponent()
        .getContext();

    @Deprecated
    public static Account getAccount() {
        Account currentAccount = new Account();

        Contacts contacts = new Contacts();
        contacts.email = "samuil.yanovski@gmail.com";
        contacts.phone = "0888 88 88 88";
        contacts.skype = "samuil.yanovski";

        currentAccount.name = "Samuil Yanovski";
        currentAccount.contacts = contacts;
        currentAccount.avatar = "https://lh3.googleusercontent.com/-adG2yZyCrOs/AAAAAAAAAAI/AAAAAAAAABc/rhvJ5H2vBAs/s140-p-no/photo.jpg";
        return currentAccount;
    }

    @Deprecated
    public static List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        DateTime today = DateTime.now();
        DateTime nextWeek = today.plusDays(7);
        events.add(createEvent(today, "Завърши 'Календар' екрана"));
        events.add(createEvent(today, "Завърши 'Профил' екраните"));
        events.add(createEvent(nextWeek, "Завърши всички екрани"));

        return events;
    }

    @Deprecated
    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        Category agile = new Category();
        agile.name = "Гъвкави методологии";

        List<Thesis> agileTheses = new ArrayList<>();
        Teacher ilieva = createIlieva();
        Teacher petrov = createPetrov();

        Thesis spem = new Thesis();
        spem.title =
            "SPEM (Software Process Engineering Meta-Model) - базирано моделиране на процеси за разработване на софтуер";
        spem.description =
            "Да се създаде разширение на SPEM за моделиране на софтуерни процеси. Да се разгледат възможностите, предоставени от Eclipse Modeling Framework и Graphical Modeling Framework (for Eclipse), за създаване на спецификации на метамодели. Да се разшири спецификацията на SPEM на ниво метамодел. Да се конфигурира Eclipse Process Framework Composer инструмента да поддържа разширената спецификация.";
        spem.author = ilieva;
        agileTheses.add(spem);

        Thesis is = new Thesis();
        is.title =
            "Изграждане на информационна система, подпомагаща решаването на етични казуси в областта на софтуерно инженерство";
        is.description =
            "Да се разработи информационна система, която ще съдържа: 1) набор от широко приложими етични теории (като деонтология, утилитаризъм и др.); 2) съществуващи етични кодекси в софтуерното инженерство; 3) набор от често срещани етични проблеми и казуси в софтуерното инженерство идентифицирани на базата на литературно проучване и личен опит ";
        is.author = ilieva;
        agileTheses.add(is);

        agile.theses = agileTheses;
        categories.add(agile);


        Category dev = new Category();
        dev.name = "Софтуерни разработки";

        List<Thesis> devTheses = new ArrayList<>();

        Thesis mobile = new Thesis();
        mobile.title =
            "Android базирана платформа за оптимизация, наблюдение и управление на процеса по разработка и защита на дипломна работа";
        mobile.description =
            "Дипломната работа ще включва анализ, проектиране и разработка на система за управление и подпомагане на процеса по разработка и защита на дипломна работа. Проекта включва уеб базирано приложение, предоставящо уеб услуги, както и мобилен клиент предоставящ възможност за комуникация между дипломен ръководител и дипломант.";
        mobile.author = petrov;
        devTheses.add(mobile);

        dev.theses = devTheses;
        categories.add(dev);

        return categories;
    }

    @Deprecated
    public static List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();

        Teacher ilieva = createIlieva();
        teachers.add(ilieva);

        Teacher petrov = createPetrov();
        teachers.add(petrov);

        return teachers;
    }

    @NonNull
    private static Event createEvent(DateTime date, String name) {
        Event event = new Event();
        event.date = date;
        event.name = name;
        return event;
    }

    @NonNull
    private static Teacher createPetrov() {
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
        return petrov;
    }

    @NonNull
    private static Teacher createIlieva() {
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
        return ilieva;
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
