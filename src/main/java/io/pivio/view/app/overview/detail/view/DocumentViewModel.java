package io.pivio.view.app.overview.detail.view;

import io.pivio.view.app.overview.detail.serverresponse.Document;
import io.pivio.view.app.overview.detail.serverresponse.License;
import io.pivio.view.app.overview.detail.serverresponse.Provides;
import io.pivio.view.app.overview.detail.serverresponse.SoftwareDependency;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;

public class DocumentViewModel {

    public Document document;
    public ServiceViewModel service = new ServiceViewModel();
    public List<SoftwareDependency> softwareDependencies = new ArrayList<>();

    public DocumentViewModel(Document document) {
        this.document = document;
        setupServiceViewModel();
    }

    public String getShortName() {
        return document.short_name != null ? "(" + document.short_name + ")" : "";
    }

    public void setupServiceViewModel() {
        for (Provides provide : document.service.provides) {
            service.provides.add(new ProvidesModel(provide));
        }
        this.softwareDependencies = document.software_dependencies;
    }

    public List<String> getConsolidatedLicenses() {
        List<String> result = new ArrayList<>();
        for (SoftwareDependency software_dependency : softwareDependencies) {
            for (License license : software_dependency.licenses) {
                if (!result.contains(license.name)) {
                    result.add(license.name);
                }
            }
        }
        sort(result);
        return result;
    }

    public List<SoftwareDependency> getSortedDependencies() {
        sort(softwareDependencies);
        return softwareDependencies;
    }
}
