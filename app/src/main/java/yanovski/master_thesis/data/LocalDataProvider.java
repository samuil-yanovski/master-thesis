package yanovski.master_thesis.data;

import android.content.Context;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import yanovski.master_thesis.MasterThesisApplication;
import yanovski.master_thesis.R;
import yanovski.master_thesis.data.models.Document;
import yanovski.master_thesis.data.models.Resource;

/**
 * Created by Samuil on 12/30/2015.
 */
public class LocalDataProvider {
    private static final Context context = MasterThesisApplication.getMainComponent()
        .getContext();

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
